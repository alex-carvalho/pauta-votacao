package br.com.ac.pauta.domain;

import br.com.ac.pauta.exception.SessaoEncerradaException;

import java.time.Instant;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * @author Alex Carvalho
 */
public class SessaoVotacao {

    private Instant fim;
    private Votos votos = new Votos();

    private SessaoVotacao(Instant fim) {
        this.fim = fim;
    }

    public static SessaoVotacao iniciar(Instant fim) {
        Instant fimSessao = Optional.ofNullable(fim).orElseGet(() -> Instant.now().plusSeconds(60));
        return new SessaoVotacao(fimSessao);
    }

    public Instant getFim() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessaoVotacao that = (SessaoVotacao) o;
        return Objects.equals(fim, that.fim) &&
                Objects.equals(votos, that.votos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fim, votos);
    }

    @Override
    public String toString() {
        return "SessaoVotacao{" +
                "fim=" + fim +
                ", votos=" + votos +
                '}';
    }
}
