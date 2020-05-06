package com.movie.order.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.movie.base.utils.JsonUtil;
import com.movie.base.utils.Page;
import com.movie.order.entity.Order;
import com.movie.order.param.OrderSelectParam;

@SpringBootTest
public class OrderControllerTest {
    
    @Autowired
    private OrderController orderController;
    
    @Test
    public void insertOrder() {
        Order order = new Order();
        order.setOrderPrice(160);
        order.setScheduleId(5L);
        order.setUserId(6L);
        order.setOrderPosition("9排9座,9排10座");
        Object o = orderController.insertOrder(order);
        System.out.println(JsonUtil.toJson(o));
    }
    
    @Test
    public void updateOrder() {
    }
    
    @Test
    public void payOrder() {
    }
    
    @Test
    public void getAllByParam() {
    }
    
    @Test
    public void selectByParam() {
        OrderSelectParam param = new OrderSelectParam();
        param.setUserId(6L);
        Object allByParam = orderController.getAllByParam(param, new Page());
        String s = JsonUtil.toJson(allByParam);
        System.out.println(s);
    }
}
