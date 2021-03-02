package br.com.product.response;

import org.springframework.http.HttpStatus;

import br.com.product.domain.Product;

public class ProductCreatedResponse extends Response<Product> {
    public ProductCreatedResponse(Product result) {
        super("product-created", "", HttpStatus.CREATED, result);
    }
}
