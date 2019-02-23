package br.com.ac.pauta.domain;

import br.com.ac.pauta.exception.SessaoEncerradaException;

import java.time.Instant;
import java.util.Map;
import java.util.Optional;

/**
 * @author Alex Carvalho
 */
class SessaoVotacao {

    private Instant fim;
    private Votos votos = new Votos();

    private SessaoVotacao(Instant fim) {
        this.fim = fim;
    }

    static SessaoVotacao iniciar(Instant fim) {
        Instant fimSessao = Optional.ofNullable(fim).orElseGet(() -> Instant.now().plusSeconds(60));
        return new SessaoVotacao(fimSessao);
    }

    Instant getFim() {
        return fim;
    }

    void adicionarVoto(Voto voto) {
        if (Instant.now().isAfter(fim)) throw new SessaoEncerradaException(fim.toString());

        votos.adicionar(voto);
    }

    boolean isFinalizada() {
        return Instant.now().isAfter(fim);
    }

    Map<OpcaoVoto, Long> getResultadoVotacao() {
        return votos.getResultadoVotacao();
    }
}
