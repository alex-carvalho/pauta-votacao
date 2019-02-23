package br.com.ac.pauta.exception;

import org.springframework.http.HttpStatus;

/**
 * @author Alex Carvalho
 */
public class SessaoNaoEncontradaException extends HttpException {

    public SessaoNaoEncontradaException(String message) {
        super(String.format("Sessão não encontrada para a pauta: %s.", message));
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
