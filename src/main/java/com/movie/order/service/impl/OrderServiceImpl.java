package com.movie.order.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.movie.order.dao.OrderMapper;
import com.movie.order.entity.Order;
import com.movie.order.service.OrderService;

@Service
@Transactional(rollbackFor = Exception.class)
public class OrderServiceImpl implements OrderService {
    
    @Resource
    private OrderMapper orderMapper;
    
    @Override
    public int deleteByPrimaryKey(String orderId) {
        return orderMapper.deleteByPrimaryKey(orderId);
    }
    
    @Override
    public int insert(Order record) {
        return orderMapper.insert(record);
    }
    
    @Override
    public int insertOrUpdate(Order record) {
        return orderMapper.insertOrUpdate(record);
    }
    
    @Override
    public int insertOrUpdateSelective(Order record) {
        return orderMapper.insertOrUpdateSelective(record);
    }
    
    @Override
    public Order insertSelective(Order record) {
        long orderId = orderMapper.insertSelective(record);
        record.setOrderId(orderId);
        return record;
    }
    
    @Override
    public Order selectByPrimaryKey(String orderId) {
        return orderMapper.selectByPrimaryKey(orderId);
    }
    
    @Override
    public int updateByPrimaryKeySelective(Order record) {
        return orderMapper.updateByPrimaryKeySelective(record);
    }
    
    @Override
    public int updateByPrimaryKey(Order record) {
        return orderMapper.updateByPrimaryKey(record);
    }
    
    @Override
    public int updateBatch(List<Order> list) {
        return orderMapper.updateBatch(list);
    }
    
    @Override
    public int updateBatchSelective(List<Order> list) {
        return orderMapper.updateBatchSelective(list);
    }
    
    @Override
    public int batchInsert(List<Order> list) {
        return orderMapper.batchInsert(list);
    }
    
}
