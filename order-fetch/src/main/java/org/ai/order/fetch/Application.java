package org.ai.order.fetch;

import org.apache.camel.spring.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.MessageFormat;

/**
 * Created by hua.ai on 2016/4/9.
 */
public class Application {

    private static final String SERVICE_ID = "service.id";

    private static String applicationContextUri = "META-INF/spring/{0}/*.xml";

    private static final Logger LOG = LoggerFactory.getLogger(Application.class);


    public static void main(String args[]) throws Exception {
        if (args.length < 1) {
            LOG.error("Usage : start service.id");
            System.exit(1);
            return;
        }
        String serviceId = args[0];
        LOG.info("Start service {}", serviceId);
        String uri = MessageFormat.format(applicationContextUri, serviceId);
        Main main = new Main();
        main.setApplicationContextUri(uri);
        main.run();
    }

}
