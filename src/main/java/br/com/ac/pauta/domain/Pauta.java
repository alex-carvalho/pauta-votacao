package br.com.ac.pauta.domain;

import br.com.ac.pauta.exception.SessaoJaExisteException;
import br.com.ac.pauta.exception.SessaoNaoEncontradaException;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.Map;
import java.util.Optional;

/**
 * @author Alex Carvalho
 */
@Document
public class Pauta {

    @Id
    private String id;
    private String titulo;
    private SessaoVotacao sessaoVotacao;

    public SessaoVotacao getSessaoVotacao() {
        return sessaoVotacao;
    }

    public Pauta iniciarSessao(Instant fim) {
        if (sessaoVotacao != null) throw new SessaoJaExisteException(id);

        sessaoVotacao = SessaoVotacao.iniciar(fim);
        return this;
    }

    public Pauta votar(Voto voto) {
        if (sessaoVotacao == null) throw new SessaoNaoEncontradaException(id);

        sessaoVotacao.adicionarVoto(voto);
        return this;
    }

    public boolean isFinalizada() {
        return Optional.ofNullable(sessaoVotacao)
                .map(SessaoVotacao::isFinalizada)
                .orElse(false);
    }

    public Map<OpcaoVoto, Long> getResultadoVotacao() {
        return Optional.ofNullable(sessaoVotacao)
                .map(SessaoVotacao::getResultadoVotacao)
                .orElse(null);
    }

}
