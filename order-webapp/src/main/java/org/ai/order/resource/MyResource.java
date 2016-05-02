package org.ai.order.resource;

import org.glassfish.jersey.server.mvc.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collections;
import java.util.Map;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {

    private static final Logger LOG = LoggerFactory.getLogger(MyResource.class);

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }

    @GET
    @Path("/{name}")
    @Produces(MediaType.TEXT_HTML)
    @Template(name = "/hello")
    public Map hello(@PathParam("name") String name) {
        return Collections.singletonMap("name", name);
    }

    @GET
    @Path("/hello/{test}")
    @Produces(MediaType.TEXT_HTML)
    public Map test(@PathParam("test") String name) {
        String message = "Hello, " + name;
        return Collections.singletonMap("message", message);
    }

    @GET
    @Path("/dotest")
    @Produces(MediaType.TEXT_HTML)
    @Template(name = "/test.ftl")
    public String dotest() {
        String message = "Hello, " + "World";
        return message;
    }

    @GET
    @Path("mustache")
    @Produces(MediaType.TEXT_HTML)
    @Template(name="/base")
    public String mustache(){
        return "Hello";
    }

}
