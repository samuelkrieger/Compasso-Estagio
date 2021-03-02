package br.com.product.response;

import org.springframework.http.HttpStatus;

public class ProductNotFoundResponse extends Response<Void> {
    public ProductNotFoundResponse(String message) {
        super("product-not-found", message, HttpStatus.NOT_FOUND, null);
    }
}
