package org.ai.order;

import com.sun.net.httpserver.HttpServer;
import org.ai.order.filter.AuthenticationFilter;
import org.ai.order.provider.ExceptionMapper;
import org.ai.order.resource.HomeResource;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.mvc.mustache.MustacheMvcFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

/**
 * Created by hua.ai on 2016/4/16.
 */
@ApplicationPath("")
public class WebApplication extends ResourceConfig {

    private static final Logger LOG = LoggerFactory.getLogger(WebApplication.class);

    public WebApplication() {


        packages(HomeResource.class.getPackage().getName());
        register(LoggingFilter.class);
        register(AuthenticationFilter.class);
        register(MustacheMvcFeature.class);
        property(MustacheMvcFeature.TEMPLATE_BASE_PATH, "views/mustache");
        register(JacksonFeature.class);
        register(ExceptionMapper.class);

        // Optionally remove existing handlers attached to j.u.l root logger
        SLF4JBridgeHandler.removeHandlersForRootLogger();  // (since SLF4J 1.6.5)

        // add SLF4JBridgeHandler to j.u.l's root logger, should be done once during
        // the initialization phase of your application
        SLF4JBridgeHandler.install();
    }

    public static void main(String args[]) {
        startJdkHttpServer();
    }

    public static void startJdkHttpServer() {
        URI baseUri = UriBuilder.fromUri("http://localhost/").port(80).build();
        HttpServer server = JdkHttpServerFactory.createHttpServer(baseUri, new WebApplication());
        LOG.info("Server started on : {}", baseUri.toString());
    }

}
