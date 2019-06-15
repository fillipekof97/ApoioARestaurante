package br.uem.apoioarestaurante.order;

import br.uem.apoioarestaurante.util.WindowUtil;
import griffon.core.artifact.GriffonController;
import griffon.core.controller.ControllerAction;
import griffon.inject.MVCMember;
import griffon.metadata.ArtifactProviderFor;
import griffon.transform.Threading;
import org.codehaus.griffon.runtime.core.artifact.AbstractGriffonController;

import javax.annotation.Nonnull;

/**
 * @author Maicon
 */
@ArtifactProviderFor(GriffonController.class)
public class OrderItemProductController extends AbstractGriffonController {

    private OrderModel model;

    @MVCMember
    public void setModel(@Nonnull OrderModel model) {
        this.model = model;
    }

    @ControllerAction
    @Threading(Threading.Policy.INSIDE_UITHREAD_ASYNC)
    public void cancel() {
        getMvcGroup().destroy();
        application.getWindowManager().hide(WindowUtil.ORDER_ITEM_PRODUCT);
    }
}
