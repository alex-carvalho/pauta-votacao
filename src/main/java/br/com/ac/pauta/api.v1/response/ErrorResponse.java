package br.com.ac.pauta.api.v1.response;

/**
 * @author Alex Carvalho
 */
public class ErrorResponse {

    private String message;

    public ErrorResponse(String message) {
        this.message = message;
    }
}
