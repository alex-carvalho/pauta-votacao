package br.com.ac.pauta.exception;

import org.springframework.http.HttpStatus;

/**
 * @author Alex Carvalho
 */
public class SessaoEncerradaException extends HttpException {

    public SessaoEncerradaException(String message) {
        super(String.format("A sessão já está encerrada desde: %s.", message));
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.EXPECTATION_FAILED;
    }
}
