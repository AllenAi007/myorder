package org.ai.order.view;

import org.glassfish.jersey.server.mvc.Viewable;

/**
 * Created by hua.ai on 2016/4/16.
 */
public class MustacheView extends Viewable {

    public MustacheView(String templateName) throws IllegalArgumentException {
        super(templateName);
    }

    public MustacheView(String templateName, Object model) throws IllegalArgumentException {
        super(templateName, model);
    }

}
