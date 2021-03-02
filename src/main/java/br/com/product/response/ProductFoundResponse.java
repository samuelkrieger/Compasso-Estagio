package br.com.product.response;

import org.springframework.http.HttpStatus;

import br.com.product.domain.Product;

public class ProductFoundResponse extends Response<Product> {
    public ProductFoundResponse(Product result) {
        super("product-found", "", HttpStatus.OK, result);
    }
}
