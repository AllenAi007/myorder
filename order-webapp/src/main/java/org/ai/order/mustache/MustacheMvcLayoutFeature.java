package org.ai.order.mustache;

import org.glassfish.jersey.server.mvc.MvcFeature;
import org.glassfish.jersey.server.mvc.mustache.MustacheMvcFeature;

import javax.ws.rs.ConstrainedTo;
import javax.ws.rs.RuntimeType;
import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.FeatureContext;

/**
 * Created by hua.ai on 2016/4/17.
 */
@ConstrainedTo(RuntimeType.SERVER)
public class MustacheMvcLayoutFeature extends MustacheMvcFeature {

    @Override
    public boolean configure(final FeatureContext context) {
        final Configuration config = context.getConfiguration();

        if (!config.isRegistered(MustacheLayoutProcessor.class)) {
            // Template Processor.
            context.register(MustacheLayoutProcessor.class);

            // MvcFeature.
            if (!config.isRegistered(MvcFeature.class)) {
                context.register(MvcFeature.class);
            }

            return true;
        }
        return false;
    }
}
