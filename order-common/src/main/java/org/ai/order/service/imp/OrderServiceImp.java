package org.ai.order.service.imp;

import org.ai.order.dao.OrderMapper;
import org.ai.order.model.Order;
import org.ai.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by hua.ai on 2016/4/23.
 */
@Transactional
@Service
public class OrderServiceImp implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    public void insert(Order order) {
        orderMapper.insert(order);
    }

    public void insert(List<Order> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        for (Order order : list) {
            orderMapper.insert(order);
        }
    }

}
