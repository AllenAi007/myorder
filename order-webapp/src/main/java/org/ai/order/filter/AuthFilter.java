package org.ai.order.filter;

import org.glassfish.jersey.internal.util.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import java.io.IOException;

/**
 * Created by hua.ai on 2016/4/18.
 */
public class AuthFilter implements ContainerRequestFilter, ContainerResponseFilter {

    private static final Logger LOG = LoggerFactory.getLogger(AuthFilter.class);

    final static String USER_AUTH = "USER_AUTH";

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        String userAuth = requestContext.getHeaderString(USER_AUTH);
        if (userAuth == null || userAuth.trim().equals("")) {
            LOG.info("No auth");
            // dologin
            dologin(requestContext);
        } else {
            LOG.info("Authorized token :{}", userAuth);
            String userName = Base64.decodeAsString(userAuth.split(" ")[0].getBytes());
            String password = Base64.decodeAsString(userAuth.split(" ")[1].getBytes());
            LOG.info("User name :{}, password :{}", userName, password);
        }

    }

    public void dologin(ContainerRequestContext requestContext) {
        String userName = "aihua";
        String password = "abc123";
        String token = Base64.encodeAsString(userName.getBytes() + " " + Base64.encodeAsString(password.getBytes()));
        requestContext.getHeaders().add(USER_AUTH, token);
    }

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {

    }
}
