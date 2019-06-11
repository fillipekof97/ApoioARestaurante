package br.uem.apoioarestaurante.order;

import br.uem.apoioarestaurante.util.WindowUtil;
import griffon.core.artifact.GriffonView;
import griffon.inject.MVCMember;
import griffon.metadata.ArtifactProviderFor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

        stage.sizeToScene();
        getApplication().getWindowManager().attach(WindowUtil.CLIENT, stage);
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

        return scene;
    }
}
