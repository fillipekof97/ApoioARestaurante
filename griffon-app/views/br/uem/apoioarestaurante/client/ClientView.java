package br.uem.apoioarestaurante.client;

import br.uem.apoioarestaurante.metadata.entities.Client;
import br.uem.apoioarestaurante.util.WindowUtil;
import griffon.core.artifact.GriffonView;
import griffon.inject.MVCMember;
import griffon.metadata.ArtifactProviderFor;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.codehaus.griffon.runtime.javafx.artifact.AbstractJavaFXGriffonView;

import javax.annotation.Nonnull;
import java.time.LocalDate;
import java.util.Collections;

/**
 * @author Maicon
 */
@ArtifactProviderFor(GriffonView.class)
public class ClientView extends AbstractJavaFXGriffonView {
    @FXML
    private TableView<Client> clientsTableView;

    @FXML
    private TableColumn<Client, String> nameColumn;

    @FXML
    private TableColumn<Client, String> cpfColumn;

    @FXML
    private TableColumn<Client, LocalDate> bithDateColumn;

    @FXML
    private TableColumn<Client, String> phoneColumn;

    @FXML
    private TableColumn<Client, String> addressColumn;

    private ClientController controller;

    private ClientModel model;

    @MVCMember
    public void setController(@Nonnull ClientController controller) {
        this.controller = controller;
    }

    @MVCMember
    public void setModel(@Nonnull ClientModel model) {
        this.model = model;
    }

    @Override
    public void initUI() {
        Stage stage = (Stage) getApplication()
                .createApplicationContainer(Collections.<String, Object>emptyMap());
        stage.setTitle(getApplication().getConfiguration().getAsString("application.title"));
        stage.setScene(init());

        stage.sizeToScene();
        getApplication().getWindowManager().attach(WindowUtil.CLIENT, stage);
        stage.show();
    }

    private void bindProperties() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        cpfColumn.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        bithDateColumn.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        model.clientsProperty().bindBidirectional(clientsTableView.itemsProperty());
        clientsTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            model.setClient(newValue);
        });
//        model.clientProperty().bind(clientsTableView.getSelectionModel().selectedItemProperty());
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
