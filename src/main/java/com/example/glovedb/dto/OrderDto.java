package com.example.glovedb.dto;

import lombok.Builder;
import lombok.Data;

import java.sql.Date;
import java.util.List;
@Data
@Builder
public class OrderDto {
    private int id;
    private Date date;
    private int cost;
    private List<ProductDto> products;

}
