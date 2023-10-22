package com.bernarsk.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailsDTO {
    @NotNull(message = "Product ID should not be null")
    private Integer productId;

    @NotNull(message = "Quantity should not be null")
    private Integer quantity;
}
