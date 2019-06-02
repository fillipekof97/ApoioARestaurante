package br.uem.apoioarestaurante.client;

import br.uem.apoioarestaurante.util.MVCGroupUtil;
import br.uem.apoioarestaurante.util.WindowUtil;
import griffon.core.artifact.GriffonController;
import griffon.core.controller.ControllerAction;
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

    public void setModel() {
        this.model = (ClientModel) getApplication().getMvcGroupManager().findGroup(MVCGroupUtil.CLIENT_MAIN).getModel();
    }

    public ClientModel getModel() {
        if (model == null) {
            setModel();
        }

        return model;
    }

    @Override
    public void mvcGroupInit(@Nonnull Map<String, Object> args) {
        super.mvcGroupInit(args);

        if (getMvcGroup().getMvcId().equals(MVCGroupUtil.CLIENT_MAINTENANCE_UPDATE)) {
            getModel().setClientValues();
        }
    }

    @ControllerAction
    @Threading(Threading.Policy.INSIDE_UITHREAD_ASYNC)
    public void save() {
        application.getWindowManager().hide(WindowUtil.CLIENT_MAINTENANCE);

        if (getApplication().getMvcGroupManager()
                .findGroup(MVCGroupUtil.CLIENT_MAINTENANCE_CREATE) != null) {
            getModel().register();
        } else if (getApplication().getMvcGroupManager()
                .findGroup(MVCGroupUtil.CLIENT_MAINTENANCE_UPDATE) != null) {
            getModel().update();
        }
    }

    @ControllerAction
    @Threading(Threading.Policy.INSIDE_UITHREAD_ASYNC)
    public void cancel() {
        getMvcGroup().destroy();
        getApplication().getWindowManager().hide(WindowUtil.CLIENT_MAINTENANCE);
//        application.getWindowManager().show(WindowUtil.CLIENT);
    }
}
