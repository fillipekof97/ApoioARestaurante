package br.uem.apoioarestaurante.order;

import griffon.core.artifact.GriffonController;
import griffon.inject.MVCMember;
import griffon.metadata.ArtifactProviderFor;
import org.codehaus.griffon.runtime.core.artifact.AbstractGriffonController;

import javax.annotation.Nonnull;

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
}
