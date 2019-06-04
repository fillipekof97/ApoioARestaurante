package br.uem.apoioarestaurante.mainpage;

import br.uem.apoioarestaurante.util.MVCGroupUtil;
import br.uem.apoioarestaurante.util.WindowUtil;
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
public class MainPageController extends AbstractGriffonController {
    private MainPageModel model;

    @MVCMember
    public void setModel(@Nonnull MainPageModel model) {
        this.model = model;
    }

    @ControllerAction
    @Threading(Threading.Policy.INSIDE_UITHREAD_ASYNC)
    public void client() {
        try {
            MVCGroup clientGroup = application.getMvcGroupManager()
                    .findGroup(MVCGroupUtil.CLIENT);
            if (clientGroup == null) {
                application.getMvcGroupManager().createMVC(MVCGroupUtil.CLIENT_CONFIG_ID, MVCGroupUtil.CLIENT);
                application.getWindowManager().hide(WindowUtil.MAIN_PAGE);
            } else {
                application.getWindowManager().show(WindowUtil.CLIENT);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @ControllerAction
    @Threading(Threading.Policy.INSIDE_UITHREAD_ASYNC)
    public void order() {
//todo
    }
}
