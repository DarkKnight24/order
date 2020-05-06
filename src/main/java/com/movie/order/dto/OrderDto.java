package com.movie.order.dto;

import com.movie.base.dto.ScheduleDto;
import com.movie.order.entity.Order;

import lombok.Data;

@Data
public class OrderDto extends Order {
    private String orderStatus;
    
    private ScheduleDto scheduleDto;
}
