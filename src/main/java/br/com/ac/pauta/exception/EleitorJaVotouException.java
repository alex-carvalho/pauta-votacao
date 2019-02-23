package br.com.ac.pauta.exception;

import org.springframework.http.HttpStatus;

/**
 * @author Alex Carvalho
 */
public class EleitorJaVotouException extends HttpException {

    public EleitorJaVotouException(String message) {
        super(String.format("Eleitor '%s' já votou.", message));
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.EXPECTATION_FAILED;
    }
}
