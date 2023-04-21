package com.wangzai.dynamicdatasource.shardingjdbc.service.impl;

import com.wangzai.dynamicdatasource.shardingjdbc.mapper.OrderMapper;
import com.wangzai.dynamicdatasource.shardingjdbc.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public int addOrder(Long orderId, BigDecimal price, Long userId, String status){
        return orderMapper.inserOrder(orderId, price, userId, status);
    }

    @Override
    public List queryOrder(List<Long> orderIds) {
        return orderMapper.selectOrderByIds(orderIds);
    }
}
