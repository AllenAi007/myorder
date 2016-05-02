package org.ai.order.resource;

import com.google.common.io.Resources;
import org.glassfish.jersey.server.ContainerRequest;
import org.glassfish.jersey.server.mvc.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by hua.ai on 2016/4/12.
 */
@Path("/")
public class HomeResource {

    private static final Logger LOG = LoggerFactory.getLogger(HomeResource.class);

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String welcome(@Context ContainerRequest request) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("get to home page");
        }
        Map<String, Cookie> cookies = request.getCookies();
        if (cookies.isEmpty()) {
            LOG.info("Not able to find any cookie");
        }
        for (Map.Entry<String, Cookie> entry : cookies.entrySet()) {
            LOG.info("Cookie : {} - {}", entry.getKey(), entry.getValue());
        }
        return "Welcome to Jersey";
    }

    @GET
    @Path("base")
    @Produces(MediaType.TEXT_HTML)
    @Template(name = "/base")
    public String test(@Context ContainerRequest request) {
        Map<String, Cookie> cookies = request.getCookies();
        if (cookies.isEmpty()) {
            LOG.info("Not able to find any cookie");
        }
        while (cookies.entrySet().iterator().hasNext()) {
            Map.Entry<String, Cookie> entry = cookies.entrySet().iterator().next();
            LOG.info(entry.getKey() + " " + entry.getValue());
        }
        return "base";
    }

    @GET
    @Path("master")
    @Produces(MediaType.TEXT_HTML)
    @Template(name = "/master")
    public String master() {
        return "master";
    }

//    @GET
//    @Path("favicon.ico")
//    @Produces(MediaType.APPLICATION_OCTET_STREAM)
//    public byte[] image() {
//        try {
//            return Resources.toByteArray(Resources.getResource("assets/image/favicon.ico"));
//        } catch (IOException e) {
//            throw new WebApplicationException(e);
//        }
//    }
}
