package org.ai.order.service;

import org.ai.order.model.Order;

import java.util.List;

/**
 * Created by hua.ai on 2016/4/23.
 */
public interface OrderService {

    public void insert(Order order);

    /**
     * Batch insert.
     * @param list
     */
    public void insert(List<Order> list);
}
