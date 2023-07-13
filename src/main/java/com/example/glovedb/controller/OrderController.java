package com.example.glovo.controller;

import com.example.glovo.dto.OrderDto;
import com.example.glovo.dto.ProductDto;
import com.example.glovo.entity.Order;
import com.example.glovo.service.OrderService;
import com.example.glovo.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@AllArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final ProductService productService;
    @GetMapping("/{id}")
    public OrderDto get(@PathVariable int id){
        return orderService.get(id);
    }
    @GetMapping
    public List<OrderDto> getAll(){
        return orderService.getAll();
    }
    @PostMapping
    public OrderDto add(@RequestBody OrderDto order){
        return orderService.save(order);
    }
    @PostMapping("/{id}/product")
    public ProductDto add(@PathVariable int id,@RequestBody ProductDto order){
        // TODO: 30.06.2023 add service
        return productService.addToOrder(order,id);
    }
}
