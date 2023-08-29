package com.bernarsk.productservice.controller;

import com.bernarsk.productservice.dao.ProductDAO;
import com.bernarsk.productservice.service.ProductService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public void createProduct(@RequestBody ProductDAO productDAO) {
        productService.createProduct(productDAO);
    }


}

