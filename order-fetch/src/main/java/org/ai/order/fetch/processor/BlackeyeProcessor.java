package org.ai.order.fetch.processor;

import org.ai.order.fetch.http.HtmlUtils;
import org.ai.order.fetch.service.BlackEyeReader;
import org.ai.order.model.Order;
import org.ai.order.service.OrderService;
import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

/**
 * Created by hua.ai on 2016/4/9.
 */
public class BlackeyeProcessor {

    private final static Logger LOG = LoggerFactory.getLogger(BlackeyeProcessor.class);

    @Autowired
    private BlackEyeReader blackEyeReader;

    @Autowired
    private OrderService orderService;

    @Value("${blackeye.output}")
    private String output;

    /**
     *
     * @throws Exception
     */
    public void read() throws Exception {
        blackEyeReader.read();
    }

}
