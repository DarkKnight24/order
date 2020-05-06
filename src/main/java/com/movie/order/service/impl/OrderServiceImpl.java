package com.movie.order.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.movie.base.utils.BeanUtil;
import com.movie.base.utils.EnumUtil;
import com.movie.base.utils.Page;
import com.movie.order.dao.OrderMapper;
import com.movie.order.entity.Order;
import com.movie.order.enums.OrderStateEnum;
import com.movie.order.param.OrderSelectParam;
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
        if (orderMapper.insertSelective(record) > 0) {
            return record;
        }
        return null;
    }
    
    @Override
    public Order selectByPrimaryKey(Long orderId) {
        return orderMapper.selectByPrimaryKey(orderId);
    }
    
    @Override
    public int updateByPrimaryKeySelective(Order record) {
        Order order = selectByPrimaryKey(record.getOrderId());
        if (order.getOrderState() != OrderStateEnum.UNPAY.getKey()) {
            return 0;
        }
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
    
    @Override
    public Page selectByParam(OrderSelectParam param, Page page) {
        PageHelper.startPage(page);
        List<Order> orderList = orderMapper.selectByParam(param);
        orderList.forEach(p -> {
            p.setOrderStatus(EnumUtil.get(OrderStateEnum.class, p.getOrderState()).getValue());
        });
        PageInfo pageInfo = new PageInfo(orderList);
        BeanUtil.copyProperties(pageInfo, page);
        page.setList(orderList);
        return page;
    }
}
