package com.movie.order.dao;

import com.movie.order.entity.Order;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface OrderMapper {
    int deleteByPrimaryKey(String orderId);

    int insert(Order record);

    int insertOrUpdate(Order record);

    int insertOrUpdateSelective(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(String orderId);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    int updateBatch(List<Order> list);

    int updateBatchSelective(List<Order> list);

    int batchInsert(@Param("list") List<Order> list);
}