package br.uem.apoioarestaurante.order;

import griffon.core.artifact.GriffonView;
import griffon.inject.MVCMember;
import griffon.metadata.ArtifactProviderFor;
import org.codehaus.griffon.runtime.core.artifact.AbstractGriffonView;

import javax.annotation.Nonnull;

/**
 * @author Maicon
 */
@ArtifactProviderFor(GriffonView.class)
public class OrderView extends AbstractGriffonView {
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

    }
}
