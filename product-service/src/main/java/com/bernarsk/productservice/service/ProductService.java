package com.bernarsk.productservice.service;

import com.bernarsk.productservice.dto.ProductDTO;
import com.bernarsk.productservice.model.Product;
import com.bernarsk.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);

    }

    public Product createProduct(ProductDTO productDTO) {
        Product productEntity = modelMapper.map(productDTO, Product.class);
        return productRepository.save(productEntity);
    }

    public Boolean isInStock(List<Long> productIds) {
        for (Long v : productIds) {
            Optional<Product> product = productRepository.findById(v);
            if (product.isPresent()) {
                Integer quantity = product.get().getQuantity();
                // change later to check for client request quantity to match actual quantity
                if (quantity > 0) {
                    log.info("Product is in stock!");
                    continue;
                }
                log.info("Product is not in stock!");
            } else {
                log.info("Product by ID specified is not found!");
            }
            return false;
        }
        // if everything passes, return true
        return true;
    }

    public boolean deleteProductIfExists(Long id){
        Optional<Product> product = getProductById(id);
        if(product.isPresent()) {
            log.info("Deleting Product by id " + id);
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
