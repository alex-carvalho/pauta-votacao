package br.com.ac.pauta.stub;

import br.com.ac.pauta.domain.Pauta;
import br.com.ac.pauta.domain.SessaoVotacao;

import java.time.Instant;

public class PautaStub {

    public static Pauta criarPautaSemSessao() {
        return new Pauta("id", "nome", null);
    }

    public static Pauta criarPautaComSessaoEncerrada() {
        SessaoVotacao sessaoVotacao = SessaoVotacao.iniciar(Instant.now().minusSeconds(10));
        return new Pauta("id", "nome", sessaoVotacao);
    }

    public static Pauta criarPautaComSessaoAberta() {
        SessaoVotacao sessaoVotacao = SessaoVotacao.iniciar(null);
        return new Pauta("id", "nome", sessaoVotacao);
    }
}
