package com.example.glovedb.controller;


import com.example.glovedb.dto.OrderDto;
import com.example.glovedb.dto.ProductDto;
import com.example.glovedb.service.OrderService;
import com.example.glovedb.service.ProductService;
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
        return orderService.getById(id);
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
    public ProductDto add(@PathVariable int id, @RequestBody ProductDto order){
        return productService.addToOrder(order,id);
    }
}
