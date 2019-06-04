package br.uem.apoioarestaurante.client;

import br.uem.apoioarestaurante.util.MVCGroupUtil;
import griffon.core.artifact.GriffonController;
import griffon.core.controller.ControllerAction;
import griffon.inject.MVCMember;
import griffon.metadata.ArtifactProviderFor;
import griffon.transform.Threading;
import org.codehaus.griffon.runtime.core.artifact.AbstractGriffonController;

import javax.annotation.Nonnull;
import java.util.Map;

import static griffon.util.CollectionUtils.map;

/**
 * @author Maicon
 */
@ArtifactProviderFor(GriffonController.class)
public class ClientController extends AbstractGriffonController {
    private ClientModel model;

    @MVCMember
    public void setModel(@Nonnull ClientModel model) {
        this.model = model;
    }

    @ControllerAction
    @Threading(Threading.Policy.INSIDE_UITHREAD_ASYNC)
    public void search() {
        model.consult();
    }

    @ControllerAction
    @Threading(Threading.Policy.INSIDE_UITHREAD_ASYNC)
    public void newClient() {
        try {
            application.getMvcGroupManager()
                    .createMVC(MVCGroupUtil.CLIENT_MAINTENANCE_CONFIG_ID,
                            MVCGroupUtil.CLIENT_MAINTENANCE_CREATE,
                            (Map) map().e("model", getMvcGroup().getModel()));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @ControllerAction
    @Threading(Threading.Policy.INSIDE_UITHREAD_ASYNC)
    public void updateClient() {
        try {
            application.getMvcGroupManager()
                    .createMVC(MVCGroupUtil.CLIENT_MAINTENANCE_CONFIG_ID,
                            MVCGroupUtil.CLIENT_MAINTENANCE_UPDATE,
                            (Map) map().e("model", getMvcGroup().getModel()));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @ControllerAction
    @Threading(Threading.Policy.INSIDE_UITHREAD_ASYNC)
    public void deleteClient() {
        model.delete();
    }
}
