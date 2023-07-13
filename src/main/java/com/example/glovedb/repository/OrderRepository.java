package com.example.glovedb.repository;



import com.example.glovedb.entity.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order,Integer> {
    List<Order> findAll();
}
