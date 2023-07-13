package com.example.glovo.service;

import com.example.glovo.converter.OrderConverter;
import com.example.glovo.dto.OrderDto;
import com.example.glovo.entity.Order;
import com.example.glovo.entity.Product;
import com.example.glovo.repository.OrderRepository;
import com.example.glovo.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderDto get(int id){
       return orderRepository.findById(id)
               .map(order -> OrderConverter.toOrderDto(order,productRepository.findAllByOrderId(id)))
               .orElseThrow();
//        Optional<Order> oreder = orderRepository.findById(id);
//        List<Product> products = productRepository.findAllByOrderId(id);
    }
    public List<OrderDto> getAll(){
       return orderRepository.findAll().stream()
               .map(order ->OrderConverter.toOrderDto(order,productRepository.findAllByOrderId(order.getId())))
               .toList();
    }
    public OrderDto save(OrderDto orderDto){
        Order order = Order.builder().date(Date.valueOf(LocalDate.now())).build();
       Order saveOrder = orderRepository.save(order);
        List<Product> products = orderDto.getProducts().stream()
                .map(productDto -> Product.builder()
                        .cost(productDto.getCost())
                        .name(productDto.getName()).orderId(saveOrder.getId())
                        .build())
                .toList();
        products=productRepository.saveAll(products);
        return OrderConverter.toOrderDto(saveOrder,products);
    }
}
