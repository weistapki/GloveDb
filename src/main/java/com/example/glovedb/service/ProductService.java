package com.example.glovedb.service;


import com.example.glovedb.dto.ProductDto;
import com.example.glovedb.entity.Product;
import com.example.glovedb.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public ProductDto addToOrder(ProductDto productDto, int orderId){
        Product product = Product.builder()
                .cost(productDto.getCost())
                .name(productDto.getName())
                .orderId(orderId)
                .build();
        Product save = productRepository.save(product);
        productDto.setId(save.getId());
        return productDto;

    }
}
