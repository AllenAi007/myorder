package org.ai.order.resource;

import org.ai.order.model.Order;
import org.glassfish.jersey.server.mvc.Template;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collections;
import java.util.List;

/**
 * Created by hua.ai on 2016/4/22.
 */
@Path("order")
public class OrderResource {

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Template(name = "/table")
    public List<Order> get() {
        return Collections.emptyList();
    }
}
