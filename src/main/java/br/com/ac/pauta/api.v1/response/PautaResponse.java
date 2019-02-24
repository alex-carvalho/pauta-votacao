package br.com.ac.pauta.api.v1.response;

import br.com.ac.pauta.domain.OpcaoVoto;
import io.swagger.annotations.ApiModelProperty;

import java.util.Map;
import java.util.Objects;

/**
 * @author Alex Carvalho
 */
public class PautaResponse {

    @ApiModelProperty(value = "Identificador da pauta.")
    private String id;
    @ApiModelProperty(value = "Titulo da pauta.")
    private String titulo;
    @ApiModelProperty(value = "Indicativo se a pauta já foi finalizada.")
    private boolean finalizada;
    @ApiModelProperty(value = "Resultado consolidado da votação, exibindo o total por opção.", example = "SIM: 1")
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
