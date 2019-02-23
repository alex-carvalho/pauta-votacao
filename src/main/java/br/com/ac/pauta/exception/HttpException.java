package br.com.ac.pauta.exception;

import org.springframework.http.HttpStatus;

/**
 * @author Alex Carvalho
 */
public abstract class HttpException extends RuntimeException {

    public HttpException(String message) {
        super(message);
    }

    public abstract HttpStatus getHttpStatus();
}
