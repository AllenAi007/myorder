package org.ai.order.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import java.io.IOException;

/**
 * Created by hua.ai on 2016/4/12.
 */
public class EncodingFilter implements ContainerResponseFilter {

    private static final Logger LOG = LoggerFactory.getLogger(EncodingFilter.class);

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        if(LOG.isDebugEnabled()){
            LOG.debug("Get into encoding filter");
        }
        String contentType = responseContext.getHeaderString("Content-Type");
        if (!contentType.contains("charset=")) {
            if(LOG.isDebugEnabled()){
                LOG.debug("Charset=UAT-8");
            }
            responseContext.getHeaders().add("Content-Type", contentType + ";charset=UAT-8");
        }
    }
}
