package com.example.glovedb.converter;



import com.example.glovedb.dto.OrderDto;
import com.example.glovedb.dto.ProductDto;
import com.example.glovedb.entity.Order;
import com.example.glovedb.entity.Product;

import java.util.List;

public class OrderConverter {
    public static OrderDto toOrderDto(Order order, List<Product> products){
        List<ProductDto> productDtos = products.stream()
                .map(p -> ProductDto.builder()
                        .id(p.getId())
                        .name(p.getName())
                        .cost(p.getCost())
                        .build())
                .toList();
        int sum = productDtos.stream()
                .mapToInt(ProductDto::getCost)
                .sum();
       return OrderDto.builder()
               .id(order.getId())
               .date(order.getDate())
               .cost(sum)
               .products(productDtos)
               .build();

    }
}