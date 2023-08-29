package com.bernarsk.orderservice.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDAO {
    private Integer customerId;

    private List<OrderDetailsDAO> orderDetailsList;
}
