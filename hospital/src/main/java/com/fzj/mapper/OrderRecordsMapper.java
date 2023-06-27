package com.fzj.mapper;

import com.fzj.pojo.OrderRecords;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderRecordsMapper {

    List<OrderRecords> findOrderByUserId(Integer userId);

    void saveOrderRecord(@Param("orderRecords") OrderRecords orderRecords);

    void deleteOrder(@Param("id") Integer id);

    OrderRecords findOrderById(@Param("id") Integer id);

    void updateOrderRecords(@Param("orderRecords")OrderRecords orderRecords);

    List<OrderRecords> findAllOrder();
}
