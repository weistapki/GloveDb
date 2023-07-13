package com.example.glovedb.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;


@Table("products")
@Data
@Builder
public class Product {
    @Id
    private int id;
    private String name;
    private int cost;
    private int orderId;
}
