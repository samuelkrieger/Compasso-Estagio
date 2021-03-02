package br.com.product.response;

import org.springframework.http.HttpStatus;

public abstract class Response<T> {
    private final String reason;
    private final String message;
    private final HttpStatus status;
    private final T result;

    public Response(final String reason, final String message, final HttpStatus status, final T result) {
        this.reason = reason;
        this.message = message;
        this.status = status;
        this.result = result;
    }

    public String getReason() {
        return reason;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public T getResult() {
        return result;
    }
}
