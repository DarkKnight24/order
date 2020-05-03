package com.movie.order.controller;

import com.movie.order.entity.Order;
import com.movie.order.service.OrderService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @作者 ：soul
 * @创建日期 ：Created in 2020/5/3 17:28
 * @描述：
 */
@Controller
@RequestMapping("order")
@Api(tags = "订单服务")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @ResponseBody
    @PostMapping("insert")
    public Object insertOrder(Order order) {
        return orderService.insertSelective(order) > 0;
    }

    @ResponseBody
    @PostMapping("update")
    public Object updateOrder(Order order) {
        return orderService.updateByPrimaryKeySelective(order);
    }

    @ResponseBody
    @GetMapping("all")
    public Object getAllByParam() {
        return null;
    }
}
