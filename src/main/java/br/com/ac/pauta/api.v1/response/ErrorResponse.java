package br.com.ac.pauta.api.v1.response;

import java.util.Objects;

/**
 * @author Alex Carvalho
 */
public class ErrorResponse {

    private final String message;
    private final String detalhe;

    public ErrorResponse(String message, String detalhe) {
        this.message = message;
        this.detalhe = detalhe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ErrorResponse that = (ErrorResponse) o;
        return Objects.equals(message, that.message) &&
                Objects.equals(detalhe, that.detalhe);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, detalhe);
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "message='" + message + '\'' +
                ", detalhe='" + detalhe + '\'' +
                '}';
    }
}
