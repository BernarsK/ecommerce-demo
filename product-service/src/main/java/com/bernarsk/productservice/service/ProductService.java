package com.bernarsk.productservice.service;

import com.bernarsk.productservice.dao.ProductDAO;
import com.bernarsk.productservice.model.Product;
import com.bernarsk.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public void createProduct(ProductDAO productDAO) {
        Product productEntity = modelMapper.map(productDAO, Product.class);
        productRepository.save(productEntity);
    }
}
