package com.example.glovedb.service;


import com.example.glovedb.converter.OrderConverter;
import com.example.glovedb.dto.OrderDto;
import com.example.glovedb.entity.Order;
import com.example.glovedb.entity.Product;
import com.example.glovedb.repository.OrderRepository;
import com.example.glovedb.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderDto getById(int id){
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
