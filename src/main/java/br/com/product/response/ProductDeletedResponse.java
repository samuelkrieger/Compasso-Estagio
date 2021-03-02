package br.com.product.response;

import org.springframework.http.HttpStatus;

import br.com.product.domain.Product;

public class ProductDeletedResponse extends Response<Product> {
    public ProductDeletedResponse(Product result) {
        super("product-deleted", "", HttpStatus.OK, result);
    }
}
