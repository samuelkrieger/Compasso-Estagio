package br.com.product.error;

public class ProductNotFoundException extends NotFoundException {
    public ProductNotFoundException(Long id) {
        super(String.format("product not found by id=%s", id));
    }
}
