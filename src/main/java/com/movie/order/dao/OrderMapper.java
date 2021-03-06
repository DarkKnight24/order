package com.movie.order.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.movie.order.dto.OrderDto;
import com.movie.order.entity.Order;
import com.movie.order.param.OrderSelectParam;

@Mapper
public interface OrderMapper {
    int deleteByPrimaryKey(String orderId);
    
    int insert(Order record);
    
    int insertOrUpdate(Order record);
    
    int insertOrUpdateSelective(Order record);
    
    Long insertSelective(Order record);
    
    Order selectByPrimaryKey(Long orderId);
    
    int updateByPrimaryKeySelective(Order record);
    
    int updateByPrimaryKey(Order record);
    
    int updateBatch(List<Order> list);
    
    int updateBatchSelective(List<Order> list);
    
    int batchInsert(@Param("list") List<Order> list);
    
    List<OrderDto> selectByParam(OrderSelectParam param);
}