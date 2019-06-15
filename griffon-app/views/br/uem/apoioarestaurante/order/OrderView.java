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
public class OrderView extends AbstractJavaFXGriffonView {

    @FXML
    private TableView<?> ordersTableView;

    @FXML
    private TableColumn<?, ?> idColumn;

    @FXML
    private TableColumn<?, ?> typeColumn;

    @FXML
    private TableColumn<?, ?> userColumn;

    @FXML
    private TableColumn<?, ?> clientColumn;

    @FXML
    private TableColumn<?, ?> tableColumn;

    @FXML
    private TableColumn<?, ?> totalColumn;

    @FXML
    private TableColumn<?, ?> openDateColumn;

    @FXML
    private TableColumn<?, ?> closeDate;

    @FXML
    private Button newOrderButton;

    @FXML
    private Button updateOrderButton;

    @FXML
    private Button deleteOrderButton;

    @FXML
    private Button terminateOrderButton;

    @FXML
    private Button backButton;

    @FXML
    private CheckBox idCheck;

    @FXML
    private CheckBox userCheck;

    @FXML
    private CheckBox clientCheck;

    @FXML
    private CheckBox tableCheck;

    @FXML
    private Button searchButton;

    @FXML
    private TextField orderIdSearchTxt;

    @FXML
    private TextField clientSearchTxt;

    @FXML
    private TextField userSearchTxt;

    @FXML
    private TextField tableSearchTxt;

    private OrderController controller;

    private OrderModel model;

    @MVCMember
    public void setController(@Nonnull OrderController controller) {
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
        getApplication().getWindowManager().attach(WindowUtil.ORDER, stage);
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
            orderIdSearchTxt.setDisable(!newValue);
        });

        userCheck.selectedProperty().addListener((observable, oldValue, newValue) -> {
            userSearchTxt.setDisable(!newValue);
        });

        clientCheck.selectedProperty().addListener((observable, oldValue, newValue) -> {
            clientSearchTxt.setDisable(!newValue);
        });

        tableCheck.selectedProperty().addListener((observable, oldValue, newValue) -> {
            tableSearchTxt.setDisable(!newValue);
        });
    }
}
