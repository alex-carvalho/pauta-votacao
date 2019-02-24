package br.com.ac.pauta.api.v1.request;

import br.com.ac.pauta.domain.OpcaoVoto;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * @author Alex Carvalho
 */
public class VotoRequest {

    @NotNull
    @ApiModelProperty(value = "Identificador do eleitor.")
    private String idEleitor;
    @NotNull
    @ApiModelProperty(value = "Opção do voto, sim ou não.")
    private OpcaoVoto opcaoVoto;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VotoRequest that = (VotoRequest) o;
        return Objects.equals(idEleitor, that.idEleitor) &&
                opcaoVoto == that.opcaoVoto;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEleitor, opcaoVoto);
    }

    @Override
    public String toString() {
        return "VotoRequest{" +
                "idEleitor='" + idEleitor + '\'' +
                ", opcaoVoto=" + opcaoVoto +
                '}';
    }
}
