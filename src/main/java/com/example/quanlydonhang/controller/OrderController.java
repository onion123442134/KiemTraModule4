package com.example.quanlydonhang.controller;

import com.example.quanlydonhang.model.Order;
import com.example.quanlydonhang.model.dto.OrderUpdateDTO;
import com.example.quanlydonhang.service.CategoryService;
import com.example.quanlydonhang.service.OrderService;
import com.example.quanlydonhang.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final CategoryService categoryService;
    private final ProductService productService;

    // ================= LIST =================
    @GetMapping
    public String list(@RequestParam(defaultValue = "0") int page, Model model) {
        model.addAttribute("orders", orderService.findAll(page));
        return "orders/list";
    }

    // ================= FILTER =================
    @GetMapping("/filter")
    public String filter(
            @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") Date from,
            @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") Date to,
            Model model) {

        if (from.after(to)) {
            model.addAttribute("orders", orderService.findAll(0)); // show tất cả order nếu lỗi
            model.addAttribute("error", "Ngày kết thúc phải lớn hơn ngày bắt đầu");
            return "orders/list";
        }

        model.addAttribute("orders", orderService.filter(from, to, 0));
        return "orders/list";
    }

    // ================= TOP =================
    @GetMapping("/top")
    public String top(@RequestParam int limit, Model model) {
        model.addAttribute("orders", orderService.findTop(limit));
        return "orders/list";
    }

    // ================= EDIT (GET) =================
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {

        Order order = orderService.findById(id);

        OrderUpdateDTO dto = new OrderUpdateDTO();
        dto.setNgayMua(order.getNgayMua());
        dto.setSoLuong(order.getSoLuong());
        dto.setProductId(order.getProduct().getMaSp());
        dto.setCategoryId(order.getProduct().getCategory().getMaLoaiSp());

        model.addAttribute("order", order);
        model.addAttribute("dto", dto);
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("products",
                productService.findByCategory(dto.getCategoryId()));

        return "orders/edit";
    }

    // ================= EDIT (POST) =================
    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute("dto") OrderUpdateDTO dto,
                         BindingResult br,
                         Model model) {

        // Validate ngày mua > ngày hiện tại
        if (dto.getNgayMua() != null && dto.getNgayMua().before(new Date())) {
            br.rejectValue("ngayMua", "error.dto", "Ngày mua phải lớn hơn ngày hiện tại");
        }

        if (br.hasErrors()) {
            model.addAttribute("order", orderService.findById(id));
            model.addAttribute("categories", categoryService.findAll());

            if (dto.getCategoryId() != null) {
                model.addAttribute("products",
                        productService.findByCategory(dto.getCategoryId()));
            }

            return "orders/edit";
        }

        orderService.update(id, dto);
        return "redirect:/orders";
    }

}
