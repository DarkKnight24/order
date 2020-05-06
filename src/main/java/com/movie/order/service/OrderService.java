package com.movie.order.service;

import java.util.List;

import com.movie.order.entity.Order;

public interface OrderService {
    
    int deleteByPrimaryKey(String orderId);
    
    int insert(Order record);
    
    int insertOrUpdate(Order record);
    
    int insertOrUpdateSelective(Order record);
    
    Order insertSelective(Order record);
    
    Order selectByPrimaryKey(String orderId);
    
    int updateByPrimaryKeySelective(Order record);
    
    int updateByPrimaryKey(Order record);
    
    int updateBatch(List<Order> list);
    
    int updateBatchSelective(List<Order> list);
    
    int batchInsert(List<Order> list);
    
}
