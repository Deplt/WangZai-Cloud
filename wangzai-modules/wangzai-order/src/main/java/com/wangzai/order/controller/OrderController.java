package com.wangzai.order.controller;

import com.google.common.collect.Lists;
import com.wangzai.order.common.Rest;
import com.wangzai.order.model.Order;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/***
 *
 * @since:swagger-bootstrap-ui 1.0
 * @author <a href="mailto:xiaoymin@foxmail.com">xiaoymin@foxmail.com</a>
 * 2019/05/04 11:26
 */
@Api(tags = "订单模块")
@RestController
@RequestMapping("/user")
public class OrderController {

    @Autowired
    private RedisTemplate redisTemplate;


    @ApiOperation(value = "查询订单列表")
    @PostMapping(value = "/list")
    public Rest<List<Order>> list(){
        Rest<List<Order>> rest=new Rest<>();
        List<Order> list= Lists.newArrayList(new Order(),new Order(),new Order(),new Order(),new Order(),new Order());
        rest.setData(list);
        return rest;
    }

    @ApiOperation(value = "根据订单id查询订单详情")
    @GetMapping("/queryById")
    public Rest<Order> queryById(@RequestParam(value = "id") String id){
        Rest<Order> userRest=new Rest<>();
        userRest.setData(new Order());

        return userRest;
    }


    @ApiOperation(value =  "获取所有keys")
    @GetMapping("/getKeys")
    public void testRedis() {
        Set keys = redisTemplate.keys("*");
        System.out.println(keys);
    }


}
