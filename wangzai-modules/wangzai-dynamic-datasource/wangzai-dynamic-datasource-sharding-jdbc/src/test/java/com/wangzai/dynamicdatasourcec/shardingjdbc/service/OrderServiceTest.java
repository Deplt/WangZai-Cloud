package com.wangzai.dynamicdatasourcec.shardingjdbc.service;

import com.wangzai.dynamicdatasource.shardingjdbc.ShardingJdbcApplication;
import com.wangzai.dynamicdatasource.shardingjdbc.service.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {ShardingJdbcApplication.class})
public class OrderServiceTest {
    @Autowired
    private OrderService orderService;

    @Test
    public void insertOrder(){
        for (int i = 0; i < 10; i++) {
            long userId = 2;
            if (i < 5) {
                userId = 1;
            }
            orderService.addOrder((long) i+1, new BigDecimal(i), userId, "0");
        }
    }

    @Test
    public void selectOrder() {
        List<Long> orderIds = new ArrayList<>();
        orderIds.add(1L);
        orderIds.add(3L);
        orderService.queryOrder(orderIds);
    }
}
