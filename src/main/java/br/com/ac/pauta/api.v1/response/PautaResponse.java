package br.com.ac.pauta.api.v1.response;

import br.com.ac.pauta.domain.OpcaoVoto;

import java.util.Map;
import java.util.Objects;

/**
 * @author Alex Carvalho
 */
public class PautaResponse {

    private String id;
    private String titulo;
    private boolean finalizada;
    private Map<OpcaoVoto, Long> resultadoVotacao;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PautaResponse that = (PautaResponse) o;
        return finalizada == that.finalizada &&
                Objects.equals(id, that.id) &&
                Objects.equals(titulo, that.titulo) &&
                Objects.equals(resultadoVotacao, that.resultadoVotacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titulo, finalizada, resultadoVotacao);
    }

    @Override
    public String toString() {
        return "PautaResponse{" +
                "id='" + id + '\'' +
                ", titulo='" + titulo + '\'' +
                ", finalizada=" + finalizada +
                ", resultadoVotacao=" + resultadoVotacao +
                '}';
    }
}
