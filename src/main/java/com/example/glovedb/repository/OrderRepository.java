package com.example.glovo.repository;

import com.example.glovo.entity.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order,Integer> {
    List<Order> findAll();
}
