package br.uem.apoioarestaurante.client;

import br.uem.apoioarestaurante.util.MVCGroupUtil;
import br.uem.apoioarestaurante.util.WindowUtil;
import griffon.core.artifact.GriffonView;
import griffon.inject.MVCMember;
import griffon.metadata.ArtifactProviderFor;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.codehaus.griffon.runtime.javafx.artifact.AbstractJavaFXGriffonView;

import javax.annotation.Nonnull;
import java.util.Collections;

/**
 * @author Maicon
 */
@ArtifactProviderFor(GriffonView.class)
public class ClientMaintenanceView extends AbstractJavaFXGriffonView {
    @FXML
    private Label nameLabel;

    @FXML
    private TextField nameTextField;

    @FXML
    private Label cpfLabel;

    @FXML
    private TextField cpfTextField;

    @FXML
    private Label birthDateLabel;

    @FXML
    private DatePicker birthDateDataPicker;

    @FXML
    private Label phoneLabel;

    @FXML
    private TextField phoneTextField;

    @FXML
    private Label addressLabel;

    @FXML
    private TextField addressTextField;

    private ClientMaintenanceController controller;

    private ClientModel model;

    @MVCMember
    public void setController(@Nonnull ClientMaintenanceController controller) {
        this.controller = controller;
    }

    public void setModel() {
        this.model = (ClientModel) getApplication().getMvcGroupManager().findGroup(MVCGroupUtil.CLIENT_NAME).getModel();
    }

    @Override
    public void initUI() {
        setModel();

        Stage stage = (Stage) getApplication()
                .createApplicationContainer(Collections.<String, Object>emptyMap());
        stage.setTitle(getApplication().getConfiguration().getAsString("application.title"));
        stage.setScene(init());

        stage.sizeToScene();
        getApplication().getWindowManager().attach(WindowUtil.CLIENT_MAINTENANCE, stage);
        stage.show();
    }

    private void bindProperties() {
        model.nameProperty().bindBidirectional(nameTextField.textProperty());
        model.cpfProperty().bindBidirectional(cpfTextField.textProperty());
        model.birthDateProperty().bindBidirectional(birthDateDataPicker.valueProperty());
        model.phoneNumberProperty().bindBidirectional(phoneTextField.textProperty());
        model.addressProperty().bindBidirectional(addressTextField.textProperty());
    }

    // build the UI
    private Scene init() {
        Scene scene = new Scene(new Group());
        scene.setFill(Color.WHITE);

        Node node = loadFromFXML();
        bindProperties();
        if (node instanceof Parent) {
            scene.setRoot((Parent) node);
        } else {
            ((Group) scene.getRoot()).getChildren().addAll(node);
        }
        connectActions(node, controller);
        connectMessageSource(node);

        return scene;
    }
}
