package com.bernarsk.productservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    @NotBlank(message = "Name must not be empty and need to have at least one character")
    private String name;

    @NotBlank(message = "Description must not be empty and need to have at least one character")
    private String description;

    @NotNull(message = "Price must not be null")
    private BigDecimal price;

    @NotNull(message = "Quantity must not be null")
    private Integer quantity;
}