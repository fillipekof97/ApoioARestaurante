package br.uem.apoioarestaurante.client;

import br.uem.apoioarestaurante.util.MVCGroupUtil;
import griffon.core.artifact.GriffonController;
import griffon.core.controller.ControllerAction;
import griffon.core.mvc.MVCGroup;
import griffon.inject.MVCMember;
import griffon.metadata.ArtifactProviderFor;
import griffon.transform.Threading;
import org.codehaus.griffon.runtime.core.artifact.AbstractGriffonController;

import javax.annotation.Nonnull;

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
        MVCGroup clientMainetenanceGroup = application.getMvcGroupManager().findGroup(MVCGroupUtil.CLIENT_MAINTENANCE_CREATE);

        if (clientMainetenanceGroup != null) {
            clientMainetenanceGroup.destroy();
        }

        try {
            application.getMvcGroupManager().findConfiguration(MVCGroupUtil.CLIENT_MAINTENANCE_CONFIG_ID).create(MVCGroupUtil.CLIENT_MAINTENANCE_CREATE);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @ControllerAction
    @Threading(Threading.Policy.INSIDE_UITHREAD_ASYNC)
    public void updateClient() {
        MVCGroup clientMainetenanceGroup = application.getMvcGroupManager()
                .findGroup(MVCGroupUtil.CLIENT_MAINTENANCE_UPDATE);

        if (clientMainetenanceGroup != null) {
            clientMainetenanceGroup.destroy();
        }

        try {
            application.getMvcGroupManager().findConfiguration(MVCGroupUtil.CLIENT_MAINTENANCE_CONFIG_ID)
                    .create(MVCGroupUtil.CLIENT_MAINTENANCE_UPDATE);
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
