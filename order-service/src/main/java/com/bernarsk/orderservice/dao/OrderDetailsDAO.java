package com.bernarsk.orderservice.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailsDAO {
    private Integer productId;

    private Integer quantity;

}
