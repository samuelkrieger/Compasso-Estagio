package br.com.product.controller.handler;

import br.com.product.error.BadProductException;
import br.com.product.error.ProductNotFoundException;
import br.com.product.response.ProductErrorResponse;
import br.com.product.response.ProductNotFoundResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProductExceptionsHandler {

    @ExceptionHandler(BadProductException.class)
    public ResponseEntity<ProductErrorResponse> handleBadRequest(BadProductException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ProductErrorResponse(ex.getMessage()));
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ProductNotFoundResponse> handleNotFound(ProductNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ProductNotFoundResponse(ex.getMessage()));
    }

}
