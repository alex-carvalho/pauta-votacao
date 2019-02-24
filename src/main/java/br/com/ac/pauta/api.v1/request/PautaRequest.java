package br.com.ac.pauta.api.v1.request;

import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * @author Alex Carvalho
 */
public class PautaRequest {

    @NotNull
    private String titulo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PautaRequest that = (PautaRequest) o;
        return Objects.equals(titulo, that.titulo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo);
    }

    @Override
    public String toString() {
        return "PautaRequest{" +
                "titulo='" + titulo + '\'' +
                '}';
    }
}
