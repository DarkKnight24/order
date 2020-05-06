package com.movie.order.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class Order {
    /**
     * order_id
     */
    private Long orderId;
    
    /**
     * 所属用户编号
     */
    private Long userId;
    
    /**
     * 所属场次编号,通过schedule_id-》场次信息-》放映厅信息+电影信息
     */
    private Long scheduleId;
    
    /**
     * 电影票座位信息
     */
    private String orderPosition;
    
    /**
     * 订单状态 0：退票中 -1：无法退票 1：已支付 2：退票成功
     */
    private Integer orderState;
    
    /**
     * 订单价格
     */
    private Integer orderPrice;
    
    /**
     * 订单支付时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date orderTime;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}