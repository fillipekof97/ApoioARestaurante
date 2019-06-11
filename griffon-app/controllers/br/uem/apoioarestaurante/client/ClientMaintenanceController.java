package br.uem.apoioarestaurante.client;

import br.uem.apoioarestaurante.util.MVCGroupUtil;
import br.uem.apoioarestaurante.util.WindowUtil;
import griffon.core.artifact.GriffonController;
import griffon.core.controller.ControllerAction;
import griffon.inject.MVCMember;
import griffon.metadata.ArtifactProviderFor;
import griffon.transform.Threading;
import org.codehaus.griffon.runtime.core.artifact.AbstractGriffonController;

import javax.annotation.Nonnull;
import java.util.Map;

/**
 * @author Maicon
 */
@ArtifactProviderFor(GriffonController.class)
public class ClientMaintenanceController extends AbstractGriffonController {
    private ClientModel model;

    @MVCMember
    public void setModel(@Nonnull ClientModel model) {
        this.model = model;
    }

    @Override
    public void mvcGroupInit(@Nonnull Map<String, Object> args) {
        super.mvcGroupInit(args);

        if (getMvcGroup().getMvcId().equals(MVCGroupUtil.CLIENT_MAINTENANCE_UPDATE_NAME)) {
            model.setClientValues();
        }
    }

    @ControllerAction
    @Threading(Threading.Policy.INSIDE_UITHREAD_ASYNC)
    public void save() {
        if (application.getMvcGroupManager()
                .findGroup(MVCGroupUtil.CLIENT_MAINTENANCE_CREATE_NAME) != null) {
            model.register();
        } else if (getApplication().getMvcGroupManager()
                .findGroup(MVCGroupUtil.CLIENT_MAINTENANCE_UPDATE_NAME) != null) {
            model.update();
        }

        getMvcGroup().destroy();
        application.getWindowManager().hide(WindowUtil.CLIENT_MAINTENANCE);
    }

    @ControllerAction
    @Threading(Threading.Policy.INSIDE_UITHREAD_ASYNC)
    public void cancel() {
        getMvcGroup().destroy();
        application.getWindowManager().hide(WindowUtil.CLIENT_MAINTENANCE);
    }
}
