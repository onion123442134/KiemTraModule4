package com.example.quanlydonhang.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "don_hang")
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maDonHang;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date ngayMua;

    @Column(nullable = false)
    private Integer soLuong;

    @ManyToOne
    @JoinColumn(name = "ma_sp", nullable = false)
    private Product product;
}
