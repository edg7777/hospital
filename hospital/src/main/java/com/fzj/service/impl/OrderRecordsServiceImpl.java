package com.fzj.service.impl;

import com.fzj.mapper.OrderRecordsMapper;
import com.fzj.pojo.OrderRecords;
import com.fzj.service.OrderRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderRecordsServiceImpl implements OrderRecordsService {
    @Autowired
    OrderRecordsMapper orderRecordsMapper;
    @Override
    public List<OrderRecords> findOrderByUserId(Integer userId) {
        return orderRecordsMapper.findOrderByUserId(userId);
    }

    @Override
    public void saveOrderRecord(OrderRecords orderRecords) {
        orderRecordsMapper.saveOrderRecord(orderRecords);
    }

    @Override
    public void deleteOrder(Integer id) {
        orderRecordsMapper.deleteOrder(id);
    }

    @Override
    public OrderRecords findOrderById(Integer id) {
        return orderRecordsMapper.findOrderById(id);
    }

    @Override
    public void updateOrderRecords(OrderRecords orderRecords) {
        orderRecordsMapper.updateOrderRecords(orderRecords);
    }

    @Override
    public List<OrderRecords> findAllOrder() {
        return orderRecordsMapper.findAllOrder();
    }
}
