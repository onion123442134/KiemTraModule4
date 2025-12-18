package com.example.quanlydonhang.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "san_pham")
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maSp;

    private String tenSp;
    private Double giaSp;
    private String tinhTrangSp;

    @ManyToOne
    @JoinColumn(name = "ma_loai_sp")
    private Category category;
}
