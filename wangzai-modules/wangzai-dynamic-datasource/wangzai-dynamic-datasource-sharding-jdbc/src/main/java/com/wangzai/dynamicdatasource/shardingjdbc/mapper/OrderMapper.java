package com.wangzai.dynamicdatasource.shardingjdbc.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface OrderMapper {

    @Insert("insert into order(order_id, price, user_id, status) values(#{orderId}, #{price}, #{userId}, #{status})")
    public int inserOrder(@Param("orderId")Long orderId, @Param("price")BigDecimal price, @Param("userId")Long userId, @Param("status")String status);

    @Select("<script>" +
            "select * from order t " +
            "where t.order_id in " +
            "<foreach collection='orderIds' open='(' separator=',' close=')' item='id'> " +
            "#{id} " +
            "</foreach>" +
            "</script>")
    List<Map> selectOrderByIds(@Param("orderIds")List<Long> orderIds);
}
