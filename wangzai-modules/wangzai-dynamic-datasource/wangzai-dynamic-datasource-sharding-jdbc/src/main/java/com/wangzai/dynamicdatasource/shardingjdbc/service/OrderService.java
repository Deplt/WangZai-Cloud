package com.wangzai.dynamicdatasource.shardingjdbc.service;

import java.math.BigDecimal;
import java.util.List;

public interface OrderService {
    int addOrder(Long orderId, BigDecimal price, Long userId, String status);

    List queryOrder(List<Long> orderIds);
}
