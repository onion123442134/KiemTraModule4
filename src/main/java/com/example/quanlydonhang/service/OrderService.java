package com.example.quanlydonhang.service;

import com.example.quanlydonhang.model.Order;
import com.example.quanlydonhang.model.dto.OrderUpdateDTO;
import org.springframework.data.domain.Page;

import java.util.Date;

public interface OrderService {
    Page<Order> findAll(int page);
    Page<Order> filter(Date from, Date to, int page);
    Page<Order> findTop(int limit);
    Order findById(Long id);
    void update(Long id, OrderUpdateDTO dto);
}
