package com.bernarsk.productservice.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDAO {
    private String name;

    private String description;

    private BigDecimal price;

    private Integer quantity;
}
