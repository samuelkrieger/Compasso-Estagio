package br.com.product.error;

public abstract class BadRequestException extends RuntimeException {
    public BadRequestException(final String message) {
        super(message);
    }
}
