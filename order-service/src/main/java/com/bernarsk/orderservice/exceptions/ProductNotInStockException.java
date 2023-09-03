package com.bernarsk.orderservice.exceptions;

public class ProductNotInStockException extends RuntimeException {

    public ProductNotInStockException(String message) {
        super(message);
    }

}
