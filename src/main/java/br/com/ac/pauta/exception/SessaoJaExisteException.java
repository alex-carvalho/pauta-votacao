package br.com.ac.pauta.exception;

import org.springframework.http.HttpStatus;

/**
 * @author Alex Carvalho
 */
public class SessaoJaExisteException extends HttpException {

    public SessaoJaExisteException(String message) {
        super(String.format("Já existe uma sessão de votação para a pauta: %S", message));
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.EXPECTATION_FAILED;
    }
}
