package org.ai.order.resource;

import org.glassfish.jersey.server.mvc.Template;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * Created by hua.ai on 2016/4/24.
 */
@Path("error")
public class ErrorResource {
    @GET
    @Produces(MediaType.TEXT_HTML)
    @Template(name = "/error")
    public ErrorMessage get(@QueryParam("message") String message, @QueryParam("status") String status, @QueryParam("stackTrace") String stackTrace) {

        int statusCode = -1;
        try {
            statusCode = Integer.parseInt(status);
        } catch (Exception e) {
            statusCode = -1;
        }
        ErrorMessage errorMessage = new ErrorMessage(message, statusCode);
        errorMessage.setStackTrace(stackTrace);
        return errorMessage;
    }

    public static final class ErrorMessage {
        private String message;
        private int status;
        private String stackTrace;


        public ErrorMessage(String message, int status) {
            this.message = message;
            this.status = status;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getStackTrace() {
            return stackTrace;
        }

        public void setStackTrace(String stackTrace) {
            this.stackTrace = stackTrace;
        }
    }
}
