package br.uem.apoioarestaurante.order;

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
public class OrderController extends AbstractGriffonController {

    private OrderModel model;

    @MVCMember
    public void setModel(@Nonnull OrderModel model) {
        this.model = model;
    }

    @ControllerAction
    @Threading(Threading.Policy.INSIDE_UITHREAD_ASYNC)
    public void back() {
        application.getWindowManager().hide(WindowUtil.ORDER);
        application.getWindowManager().show(WindowUtil.MAIN_PAGE);
    }

    @ControllerAction
    @Threading(Threading.Policy.INSIDE_UITHREAD_ASYNC)
    public void newOrder() {
        try {
            MVCGroupManager manager = application.getMvcGroupManager();
            MVCGroup group = manager.findGroup(MVCGroupUtil.ORDER_MAINTENANCE_CREATE_NAME);

            if (group == null) {
                manager.createMVC(MVCGroupUtil.ORDER_MAINTENANCE_CONFIG_ID,
                        MVCGroupUtil.ORDER_MAINTENANCE_CREATE_NAME,
                        (Map) map().e("model", getMvcGroup().getModel()));
            } else {
                group.destroy();
                manager.createMVC(MVCGroupUtil.ORDER_MAINTENANCE_CONFIG_ID,
                        MVCGroupUtil.ORDER_MAINTENANCE_CREATE_NAME,
                        (Map) map().e("model", getMvcGroup().getModel()));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
