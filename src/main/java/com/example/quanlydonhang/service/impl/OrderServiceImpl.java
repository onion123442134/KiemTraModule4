package com.example.quanlydonhang.service.impl;

import com.example.quanlydonhang.model.Order;
import com.example.quanlydonhang.model.Product;
import com.example.quanlydonhang.model.dto.OrderUpdateDTO;
import com.example.quanlydonhang.repository.OrderRepository;
import com.example.quanlydonhang.repository.ProductRepository;
import com.example.quanlydonhang.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepo;
    private final ProductRepository productRepo;

    @Override
    public Page<Order> findAll(int page) {
        return orderRepo.findAll(PageRequest.of(page, 5));
    }

    @Override
    public Page<Order> filter(Date from, Date to, int page) {
        return orderRepo.findByDate(from, to, PageRequest.of(page, 5));
    }

    @Override
    public Page<Order> findTop(int limit) {
        return orderRepo.findTop(PageRequest.of(0, limit));
    }

    @Override
    public Order findById(Long id) {
        return orderRepo.findById(id).orElseThrow();
    }

    @Override
    public void update(Long id, OrderUpdateDTO dto) {
        Order o = findById(id);
        Product p = productRepo.findById(dto.getProductId()).orElseThrow();
        o.setNgayMua(dto.getNgayMua());
        o.setSoLuong(dto.getSoLuong());
        o.setProduct(p);
        orderRepo.save(o);
    }
}

