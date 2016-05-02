package org.ai.order.processor;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.internal.util.collection.Value;
import org.glassfish.jersey.server.mvc.Viewable;
import org.glassfish.jersey.server.mvc.spi.AbstractTemplateProcessor;
import org.jvnet.hk2.annotations.Optional;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.nio.charset.Charset;

/**
 * Created by hua.ai on 2016/4/17.
 */
public class MustacheLayoutProcessor extends AbstractTemplateProcessor<Mustache> {


    private final MustacheFactory factory;

    private String layoutTemplate;

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
    }

    @Override
    protected Mustache resolve(final String templatePath, final Reader reader) {
        return factory.compile(reader, templatePath);
    }

    @Override
    public void writeTo(final Mustache mustache, final Viewable viewable, final MediaType mediaType,
                        final MultivaluedMap<String, Object> httpHeaders, final OutputStream out) throws IOException {
        Charset encoding = setContentType(mediaType, httpHeaders);
        mustache.execute(new OutputStreamWriter(out, encoding), viewable.getModel()).flush();
    }

    public String getLayoutTemplate() {
        return layoutTemplate;
    }

    public void setLayoutTemplate(String layoutTemplate) {
        this.layoutTemplate = layoutTemplate;
    }
}
