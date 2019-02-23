package br.com.ac.pauta.api.v1.request;

import io.swagger.annotations.ApiModelProperty;

import java.time.Instant;

/**
 * @author Alex Carvalho
 */
public class AbrirSessaoRequest {

    @ApiModelProperty(value = "Data qual a sessão será encerrada. Se não informado será 1 minuto após a criação.")
    private Instant finalSessao;

    public Instant getFinalSessao() {
        return finalSessao;
    }
}
