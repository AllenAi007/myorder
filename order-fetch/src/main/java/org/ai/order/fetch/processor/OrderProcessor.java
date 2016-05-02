package org.ai.order.fetch.processor;

import org.ai.order.fetch.http.HtmlUtils;
import org.ai.order.model.Order;
import org.ai.order.service.OrderService;
import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by hua.ai on 2016/4/23.
 */
public class OrderProcessor {

    private final static Logger LOG = LoggerFactory.getLogger(OrderProcessor.class);

    @Autowired
    private OrderService orderService;

    /**
     * Unmarshall the html file
     *
     * @param exchange
     * @throws Exception
     */
    public void unmarshall(Exchange exchange) throws Exception {
        String html = exchange.getIn().getBody(String.class);
        List<Order> list = HtmlUtils.unmarshall(html);
        exchange.getIn().setBody(list);
    }

    /**
     * @param exchange
     * @throws Exception
     */
    public void saveOrders(Exchange exchange) throws Exception {
        List<Order> list = exchange.getIn().getBody(List.class);
        if (list == null || list.isEmpty()) {
            LOG.info("No order found out.");
            return;
        }
        LOG.info("Total {} orders found, saving to database", list.size());
        orderService.insert(list);
        LOG.info("Inserted successfully", list.size());
    }
}
