package com.movie.order.service;

import java.util.List;

import com.movie.base.utils.Page;
import com.movie.order.dto.OrderDto;
import com.movie.order.entity.Order;
import com.movie.order.param.OrderSelectParam;

public interface OrderService {
    
    int deleteByPrimaryKey(String orderId);
    
    int insert(Order record);
    
    int insertOrUpdate(Order record);
    
    int insertOrUpdateSelective(Order record);
    
    Order insertSelective(Order record);
    
    OrderDto selectByPrimaryKey(Long orderId);
    
    int updateByPrimaryKeySelective(Order record);
    
    int updateByPrimaryKey(Order record);
    
    int updateBatch(List<Order> list);
    
    int updateBatchSelective(List<Order> list);
    
    int batchInsert(List<Order> list);
    
    Page selectByParam(OrderSelectParam param, Page page);
}
