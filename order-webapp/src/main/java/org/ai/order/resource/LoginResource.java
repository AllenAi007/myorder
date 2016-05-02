package org.ai.order.resource;

import org.ai.order.auth.AuthUtils;
import org.ai.order.model.User;
import org.ai.order.service.LoginService;
import org.glassfish.jersey.internal.util.Base64;
import org.glassfish.jersey.server.mvc.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.core.*;
import java.net.URI;

/**
 * Created by hua.ai on 2016/4/12.
 */
@Path("login")
public class LoginResource {

    @Value("${cookie.domain}")
    private String domain;

    @Context
    private UriInfo uriInfo;

    @Context
    private Request request;

    @Context
    private Response response;

    final static String USER_AUTH = "USER_AUTH";

    @Autowired
    private LoginService loginService;

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Template(name = "/login")
    public String get() {
        return "";
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
//    @Template(name = "/home")
    public Response post(@FormParam("userName") String userName, @FormParam("password") String password) {

        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)) {
            throw new WebApplicationException("Empty user name or password given", 401);
        }
        User user = loginService.doLogin(userName, password);
        if (user == null) {
            throw new WebApplicationException("Wrong user name or password given", 401);
        }
        NewCookie userCookie = new NewCookie(AuthUtils.USER_AUTH, user.toString());
        return Response.ok(user).cookie(userCookie).build();
    }

    @POST
    @Path("logout")
    @Produces(MediaType.APPLICATION_JSON)
    public Response logout(@Context Request request) {
        return Response.ok().cookie(null).build();
    }
}
