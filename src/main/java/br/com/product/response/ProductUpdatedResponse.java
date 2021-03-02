package br.com.product.response;

import org.springframework.http.HttpStatus;

import br.com.product.domain.Product;

public class ProductUpdatedResponse extends Response<Product> {
    public ProductUpdatedResponse(Product result) {
        super("product-updated", "", HttpStatus.OK, result);
    }
}
