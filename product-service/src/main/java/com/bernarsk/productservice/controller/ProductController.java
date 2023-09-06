package com.bernarsk.productservice.controller;

import com.bernarsk.productservice.dto.ProductDTO;
import com.bernarsk.productservice.model.Product;
import com.bernarsk.productservice.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    @PutMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody ProductDTO productDTO) {
        Product product = productService.createProduct(productDTO);
        return ResponseEntity.ok(product);
    }

    @GetMapping
    public ResponseEntity<Boolean> isInStock(@RequestParam(name = "productId") List<Long> productIds) {
        return ResponseEntity.ok(productService.isInStock(productIds));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@Valid @PathVariable Long id) {
        if (productService.deleteProductIfExists(id)) {
            return ResponseEntity.ok().build();
        } else {
            log.warn("Product with id {} not found for deletion", id);
            return ResponseEntity.notFound().build();
        }
    }

}

