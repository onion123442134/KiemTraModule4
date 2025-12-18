package com.example.quanlydonhang.repository;

import com.example.quanlydonhang.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("""
        SELECT o FROM Order o
        WHERE o.ngayMua BETWEEN :from AND :to
    """)
    Page<Order> findByDate(@Param("from") Date from,
                           @Param("to") Date to,
                           Pageable pageable);

    @Query("""
        SELECT o FROM Order o
        ORDER BY (o.product.giaSp * o.soLuong) DESC
    """)
    Page<Order> findTop(Pageable pageable);
}

