package com.bernarsk.orderservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    @NotNull(message = "Customer ID should not be null")
    private Integer customerId;

    @NotNull(message = "Order details list should not be null")
    private List<OrderDetailsDTO> orderDetailsList;
}