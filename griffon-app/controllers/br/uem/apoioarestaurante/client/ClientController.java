package br.uem.apoioarestaurante.client;

import br.uem.apoioarestaurante.util.MVCGroupUtil;
import br.uem.apoioarestaurante.util.WindowUtil;
import griffon.core.artifact.GriffonController;
import griffon.core.controller.ControllerAction;
import griffon.core.mvc.MVCGroup;
import griffon.core.mvc.MVCGroupManager;
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
            MVCGroupManager manager = application.getMvcGroupManager();
            MVCGroup group = manager.findGroup(MVCGroupUtil.CLIENT_MAINTENANCE_CREATE_NAME);

            if (group == null) {
                manager.createMVC(MVCGroupUtil.CLIENT_MAINTENANCE_CONFIG_ID,
                        MVCGroupUtil.CLIENT_MAINTENANCE_CREATE_NAME,
                        (Map) map().e("model", getMvcGroup().getModel()));
            } else {
                group.destroy();
                manager.createMVC(MVCGroupUtil.CLIENT_MAINTENANCE_CONFIG_ID,
                        MVCGroupUtil.CLIENT_MAINTENANCE_CREATE_NAME,
                        (Map) map().e("model", getMvcGroup().getModel()));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @ControllerAction
    @Threading(Threading.Policy.INSIDE_UITHREAD_ASYNC)
    public void updateClient() {
        try {
            MVCGroupManager manager = application.getMvcGroupManager();
            MVCGroup group = manager.findGroup(MVCGroupUtil.CLIENT_MAINTENANCE_UPDATE_NAME);

            if (group == null) {
                application.getMvcGroupManager()
                        .createMVC(MVCGroupUtil.CLIENT_MAINTENANCE_CONFIG_ID,
                                MVCGroupUtil.CLIENT_MAINTENANCE_UPDATE_NAME,
                                (Map) map().e("model", getMvcGroup().getModel()));
            } else {
                group.destroy();
                manager.createMVC(MVCGroupUtil.CLIENT_MAINTENANCE_CONFIG_ID,
                        MVCGroupUtil.CLIENT_MAINTENANCE_UPDATE_NAME,
                        (Map) map().e("model", getMvcGroup().getModel()));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @ControllerAction
    @Threading(Threading.Policy.INSIDE_UITHREAD_ASYNC)
    public void deleteClient() {
        model.delete();
    }

    @ControllerAction
    @Threading(Threading.Policy.INSIDE_UITHREAD_ASYNC)
    public void back() {
        application.getWindowManager().hide(WindowUtil.CLIENT);
        application.getWindowManager().show(WindowUtil.MAIN_PAGE);
    }
}
