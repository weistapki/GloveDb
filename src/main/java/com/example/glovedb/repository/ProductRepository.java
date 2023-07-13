package com.example.glovo.repository;

import com.example.glovo.entity.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product,Integer> {
    List<Product> findAllByOrderId(int id);
    List<Product> saveAll(List<Product> products);
}
