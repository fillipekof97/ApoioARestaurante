package br.uem.apoioarestaurante.order;

import br.uem.apoioarestaurante.util.WindowUtil;
import griffon.core.artifact.GriffonView;
import griffon.inject.MVCMember;
import griffon.metadata.ArtifactProviderFor;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.codehaus.griffon.runtime.javafx.artifact.AbstractJavaFXGriffonView;

import javax.annotation.Nonnull;
import java.util.Collections;

/**
 * @author Maicon
 */
@ArtifactProviderFor(GriffonView.class)
public class OrderItemProductView extends AbstractJavaFXGriffonView {

    @FXML
    private CheckBox idCheck;

    @FXML
    private CheckBox descriptionCheck;

    @FXML
    private TableView<?> orderItemTableView;

    @FXML
    private TableColumn<?, ?> descriptionColumn;

    @FXML
    private TableColumn<?, ?> idColumn;

    @FXML
    private TableColumn<?, ?> providerColumn;

    @FXML
    private TableColumn<?, ?> productTypeColumn;

    @FXML
    private TableColumn<?, ?> priceColumn;

    @FXML
    private Button searchButton;

    @FXML
    private TextField idSearchTxt;

    @FXML
    private TextField descriptionSearchTxt;

    @FXML
    private Button selectButton;

    @FXML
    private Button cancelButton;

    private OrderItemProductController controller;

    private OrderModel model;

    @MVCMember
    public void setController(@Nonnull OrderItemProductController controller) {
        this.controller = controller;
    }

    @MVCMember
    public void setModel(@Nonnull OrderModel model) {
        this.model = model;
    }

    @Override
    public void initUI() {
        Stage stage = (Stage) getApplication()
                .createApplicationContainer(Collections.<String, Object>emptyMap());
        stage.setTitle(getApplication().getConfiguration().getAsString("application.title"));
        stage.setScene(init());
        stage.setMinHeight(740);
        stage.setMinWidth(1300);

        stage.sizeToScene();
        getApplication().getWindowManager().attach(WindowUtil.ORDER_ITEM_PRODUCT, stage);
        stage.show();
    }

    private Scene init() {
        Scene scene = new Scene(new Group());
        scene.setFill(Color.WHITE);

        Node node = loadFromFXML();
        if (node instanceof Parent) {
            scene.setRoot((Parent) node);
        } else {
            ((Group) scene.getRoot()).getChildren().addAll(node);
        }
        connectActions(node, controller);
        connectMessageSource(node);

        setListners();

        return scene;
    }

    private void setListners() {
        idCheck.selectedProperty().addListener((observable, oldValue, newValue) -> {
            idSearchTxt.setDisable(!newValue);
        });

        descriptionCheck.selectedProperty().addListener((observable, oldValue, newValue) -> {
            descriptionSearchTxt.setDisable(!newValue);
        });
    }
}
