package br.com.ac.pauta.api.v1.response;

import br.com.ac.pauta.domain.OpcaoVoto;

import java.util.Map;

/**
 * @author Alex Carvalho
 */
public class PautaResponse {

    private String id;
    private String titulo;
    private boolean finalizada;
    private Map<OpcaoVoto, Long> resultadoVotacao;
}
