package org.ai.order.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.ai.order.auth.AuthUtils;
import org.ai.order.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.*;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.Response;
import java.io.IOException;

import static javax.ws.rs.core.Response.Status;


/**
 * Created by hua.ai on 2016/4/16.
 */
@PreMatching
public class AuthenticationFilter implements ContainerRequestFilter {

    private static final Logger LOG = LoggerFactory.getLogger(AuthenticationFilter.class);

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        if (LOG.isDebugEnabled()) {
            LOG.debug("Get into AuthenticationFilter");
        }

        String path = requestContext.getUriInfo().getPath();

        if (LOG.isDebugEnabled()) {
            LOG.debug("Attempt to visit page {}", path.equals("") ? "home" : path);
        }
        if (path.startsWith("login") || path.startsWith("assets") || path.startsWith("error") || path.equals("") || path.equals("favicon.ico")) {
            if (LOG.isDebugEnabled()) {
                LOG.debug("Path {} is excluded from auth list", path);
            }
            return;
        }

        Cookie authCookie = requestContext.getCookies().get(AuthUtils.USER_AUTH);

        if (authCookie != null) {
            String userJSON = authCookie.getValue();
            if (LOG.isDebugEnabled()) {
                LOG.debug("Get user json :{}", userJSON);
            }
//            ObjectMapper objectMapper = new ObjectMapper();
//            User user = objectMapper.readValue(userJSON, User.class);
//            if (LOG.isDebugEnabled()) {
//                LOG.debug("Passed to User {}", user);
//            }
//            AuthUtils.setCurrentUser(user);
        } else {
            if (LOG.isDebugEnabled()) {
                LOG.debug("No authorized");
            }
            throw new WebApplicationException("You are not authorized for accessing resource " + (path.equals("") ? "home" : path), Status.UNAUTHORIZED);
        }

    }
}
