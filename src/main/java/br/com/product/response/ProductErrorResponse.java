package br.com.product.response;

import org.springframework.http.HttpStatus;

public class ProductErrorResponse extends Response<Void> {
    public ProductErrorResponse(String message) {
        super("product-error", message, HttpStatus.BAD_REQUEST, null);
    }
}
