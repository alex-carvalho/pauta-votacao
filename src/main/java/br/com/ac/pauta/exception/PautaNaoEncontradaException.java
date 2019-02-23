package br.com.ac.pauta.exception;

import org.springframework.http.HttpStatus;

/**
 * @author Alex Carvalho
 */
public class PautaNaoEncontradaException extends HttpException {

    public PautaNaoEncontradaException(String message) {
        super(String.format("Pauta '%s' não encontrada.", message));
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
