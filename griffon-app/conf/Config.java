import br.uem.apoioarestaurante.util.MVCGroupUtil;
import griffon.util.AbstractMapResourceBundle;

import javax.annotation.Nonnull;
import java.util.Map;

import static griffon.util.CollectionUtils.map;
import static java.util.Arrays.asList;

public class Config extends AbstractMapResourceBundle {

    @Override
    protected void initialize(@Nonnull Map<String, Object> entries) {
        map(entries)
                .e("application", map()
                        .e("title", "Apoio A Restaurante")
                        .e("startupGroups", asList(MVCGroupUtil.MAINPAGE_CONFIG_ID))
                        .e("autoShutdown", true)
                )
                .e("mvcGroups", map()
                        .e(MVCGroupUtil.MAINPAGE_CONFIG_ID, map()
                                .e("model", "br.uem.apoioarestaurante.mainpage.MainPageModel")
                                .e("view", "br.uem.apoioarestaurante.mainpage.MainPageView")
                                .e("controller", "br.uem.apoioarestaurante.mainpage.MainPageController")
                        )
                        .e(MVCGroupUtil.CLIENT_CONFIG_ID, map()
                                .e("model", "br.uem.apoioarestaurante.client.ClientModel")
                                .e("view", "br.uem.apoioarestaurante.client.ClientView")
                                .e("controller", "br.uem.apoioarestaurante.client.ClientController")
                        )
                        .e(MVCGroupUtil.CLIENT_MAINTENANCE_CONFIG_ID, map()
                                .e("model", "br.uem.apoioarestaurante.client.ClientModel")
                                .e("view", "br.uem.apoioarestaurante.client.ClientMaintenanceView")
                                .e("controller", "br.uem.apoioarestaurante.client.ClientMaintenanceController")
                        )
                        .e(MVCGroupUtil.ORDER_CONFIG_ID, map()
                                .e("model", "br.uem.apoioarestaurante.order.OrderModel")
                                .e("view", "br.uem.apoioarestaurante.order.OrderView")
                                .e("controller", "br.uem.apoioarestaurante.order.OrderController")
                        )
                        .e(MVCGroupUtil.ORDER_MAINTENANCE_CONFIG_ID, map()
                                .e("model", "br.uem.apoioarestaurante.order.OrderModel")
                                .e("view", "br.uem.apoioarestaurante.order.OrderMaintenanceView")
                                .e("controller", "br.uem.apoioarestaurante.order.OrderMaintenanceController")
                        )
                        .e(MVCGroupUtil.ORDER_ITEM_PRODUCT_CONFIG_ID, map()
                                .e("model", "br.uem.apoioarestaurante.order.OrderModel")
                                .e("view", "br.uem.apoioarestaurante.order.OrderItemProductView")
                                .e("controller", "br.uem.apoioarestaurante.order.OrderItemProductController")
                        )
                );
    }
}