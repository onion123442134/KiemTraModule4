package com.example.quanlydonhang.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
public class OrderUpdateDTO {

    @NotNull(message = "Vui lòng chọn loại sản phẩm")
    private Long categoryId;

    @NotNull(message = "Vui lòng chọn sản phẩm")
    private Long productId;

    @NotNull(message = "Vui lòng nhập ngày mua")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date ngayMua;

    @NotNull(message = "Vui lòng nhập số lượng")
    @Min(value = 1, message = "Số lượng phải lớn hơn 0")
    private Integer soLuong;
}
