package com.movie.order.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.movie.base.dto.ScheduleDto;
import com.movie.base.interfaces.ScheduleClient;
import com.movie.base.utils.BeanUtil;
import com.movie.base.utils.EnumUtil;
import com.movie.base.utils.JsonUtil;
import com.movie.base.utils.Page;
import com.movie.order.dao.OrderMapper;
import com.movie.order.dto.OrderDto;
import com.movie.order.entity.Order;
import com.movie.order.enums.OrderStateEnum;
import com.movie.order.param.OrderSelectParam;
import com.movie.order.service.OrderService;

@Service
@Transactional(rollbackFor = Exception.class)
public class OrderServiceImpl implements OrderService {
    
    @Resource
    private OrderMapper orderMapper;
    
    @Autowired
    private ScheduleClient scheduleClient;
    
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
    public OrderDto selectByPrimaryKey(Long orderId) {
        Order order = orderMapper.selectByPrimaryKey(orderId);
        checkPayState(order);
        OrderDto orderDto = new OrderDto();
        BeanUtil.copyProperties(order, orderDto);
        orderDto.setScheduleDto(getScheduleDto(orderDto.getScheduleId()));
        Date createTime = orderDto.getCreateTime();
        long time = 1000 * 60 * 30 - System.currentTimeMillis() + createTime.getTime();
        if (time <= 0) {
            return orderDto;
        }
        orderDto.setMinute((int)(time / (1000 * 60)));
        orderDto.setSecond((int)(time - (time / (1000 * 60)) * 60 * 1000) / 1000);
        return orderDto;
    }
    
    @Override
    public int updateByPrimaryKeySelective(Order record) {
        Order order = selectByPrimaryKey(record.getOrderId());
        checkPayState(order);
        if (record.getOrderState() == OrderStateEnum.PAYMENT_COMPLETE.getKey()
            && order.getOrderState() != OrderStateEnum.UNPAY.getKey()) {
            return 0;
        }
        if (record.getOrderState() == OrderStateEnum.REFUNDING.getKey()
            && order.getOrderState() != OrderStateEnum.PAYMENT_COMPLETE.getKey()) {
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
        List<OrderDto> orderList = orderMapper.selectByParam(param);
        orderList.forEach(p -> {
            checkPayState(p);
            p.setOrderStatus(EnumUtil.get(OrderStateEnum.class, p.getOrderState()).getValue());
            p.setScheduleDto(getScheduleDto(p.getScheduleId()));
        });
        PageInfo pageInfo = new PageInfo(orderList);
        BeanUtil.copyProperties(pageInfo, page);
        page.setList(orderList);
        return page;
    }
    
    private ScheduleDto getScheduleDto(Long scheduleId) {
        Object detail = scheduleClient.detail(scheduleId);
        Map<String, Object> map = JsonUtil.fromJsonAsMap(Object.class, JsonUtil.toJson(detail));
        Object data = map.get("data");
        ScheduleDto scheduleDto = JsonUtil.fromJson(JsonUtil.toJson(data), ScheduleDto.class);
        return scheduleDto;
    }
    
    private void checkPayState(Order order) {
        if (order.getOrderState() == OrderStateEnum.UNPAY.getKey()) {
            if ((order.getCreateTime().getTime() + 1000 * 60 * 30) <= System.currentTimeMillis()) {
                order.setOrderState(OrderStateEnum.ORDER_FAIL.getKey());
                updateByPrimaryKeySelective(order);
            }
        }
    }
    
    public static void main(String[] args) {
        long time = 1000 * 60 * 29 + 55 * 1000;
        System.out.println(time / (1000 * 60));
        System.out.println((time - (time / (1000 * 60)) * 60 * 1000) / 1000);
    }
}
