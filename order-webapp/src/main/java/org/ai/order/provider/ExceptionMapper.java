package org.ai.order.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.*;
import javax.ws.rs.ext.Provider;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URI;

/**
 * Created by hua.ai on 2016/4/24.
 */
@Provider
public class ExceptionMapper implements javax.ws.rs.ext.ExceptionMapper<Throwable> {

    private static final Logger LOG = LoggerFactory.getLogger(ExceptionMapper.class);

    @Context
    private UriInfo uriInfo;

    @Context
    private Request request;

    @Override
    public Response toResponse(Throwable exception) {
        String errorPage = "Unkown Error";
        int status = -1;
        if (exception instanceof WebApplicationException) {
            WebApplicationException webApplicationException = (WebApplicationException) exception;
            status = webApplicationException.getResponse().getStatus();
        }
        String method = request.getMethod();
        String path = uriInfo.getPath();
        if (method.equals("GET") && !path.startsWith("assets")) {
            LOG.error("Error caused.", exception);
            UriBuilder uriBuilder = uriInfo.getBaseUriBuilder().path("/error")
                    .queryParam("message", exception.getMessage())
                    .queryParam("status", status);
            URI uri = uriBuilder.build();
            if (LOG.isDebugEnabled()) {
                LOG.debug("Go to uri {}", uri);
            }
            return Response.seeOther(uri).build();
        }
        return Response.status(status).entity(exception.getMessage()).build();
    }
}
