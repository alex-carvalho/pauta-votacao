package br.com.ac.pauta.api.v1.request;

import io.swagger.annotations.ApiModelProperty;

import java.time.Instant;
import java.util.Objects;

/**
 * @author Alex Carvalho
 */
public class AbrirSessaoRequest {

    @ApiModelProperty(value = "Data qual a sessão será encerrada. Se não informado será 1 minuto após a criação.")
    private Instant finalSessao;

    public Instant getFinalSessao() {
        return finalSessao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbrirSessaoRequest that = (AbrirSessaoRequest) o;
        return Objects.equals(finalSessao, that.finalSessao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(finalSessao);
    }

    @Override
    public String toString() {
        return "AbrirSessaoRequest{" +
                "finalSessao=" + finalSessao +
                '}';
    }
}
