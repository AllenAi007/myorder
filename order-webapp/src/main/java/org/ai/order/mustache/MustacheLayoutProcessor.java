package org.ai.order.mustache;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.internal.util.PropertiesHelper;
import org.glassfish.jersey.internal.util.collection.Value;
import org.glassfish.jersey.server.mvc.MvcFeature;
import org.glassfish.jersey.server.mvc.Viewable;
import org.glassfish.jersey.server.mvc.spi.AbstractTemplateProcessor;
import org.jvnet.hk2.annotations.Optional;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.ws.rs.ConstrainedTo;
import javax.ws.rs.RuntimeType;
import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import java.io.*;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.Map;

/**
 * Created by hua.ai on 2016/4/17.
 */
@ConstrainedTo(RuntimeType.SERVER)
public class MustacheLayoutProcessor extends AbstractTemplateProcessor<Mustache> {

    private final MustacheFactory factory;

    private String layoutTemplate;

    private final static String LAYOUT_TEMPLATE = "LAYOUT_TEMPLATE";

    private final static String BODY_CONTENT = "content";

    /**
     * Create an instance of this processor with injected {@link Configuration config} and
     * (optional) {@link ServletContext servlet context}.
     *
     * @param config         configuration to configure this processor from.
     * @param serviceLocator service locator to initialize template object factory if needed.
     * @param servletContext (optional) servlet context to obtain template resources from.
     */
    @Inject
    public MustacheLayoutProcessor(final Configuration config, final ServiceLocator serviceLocator,
                                   @Optional final ServletContext servletContext) {
        super(config, servletContext, "mustache", "mustache");

        this.factory = getTemplateObjectFactory(serviceLocator, MustacheFactory.class, new Value<MustacheFactory>() {
            @Override
            public MustacheFactory get() {
                return new DefaultMustacheFactory();
            }
        });

        final Map<String, Object> properties = config.getProperties();
        this.layoutTemplate = PropertiesHelper.getValue(properties, LAYOUT_TEMPLATE, "layout/template.mustache", null);

    }

    @Override
    protected Mustache resolve(final String templatePath, final Reader reader) {
        return factory.compile(reader, templatePath);
    }

    @Override
    public void writeTo(final Mustache mustache, final Viewable viewable, final MediaType mediaType,
                        final MultivaluedMap<String, Object> httpHeaders, final OutputStream out) throws IOException {
        Charset encoding = setContentType(mediaType, httpHeaders);

        StringWriter sw = new StringWriter();
        mustache.execute(sw, viewable.getModel()).flush();
        Mustache layout = this.factory.compile(getBasePath() + "/" + layoutTemplate);
        layout.execute(new OutputStreamWriter(out, encoding), Collections.singletonMap(BODY_CONTENT, sw.getBuffer().toString())).flush();
    }

    public String getLayoutTemplate() {
        return layoutTemplate;
    }

    public void setLayoutTemplate(String layoutTemplate) {
        this.layoutTemplate = layoutTemplate;
    }
}
