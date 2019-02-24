package br.com.ac.pauta.domain;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SessaoVotacaoTest {

    @Test
    void iniciarSessaoComValorPadrao() {
        SessaoVotacao sessao = SessaoVotacao.iniciar(null);

        Instant expected = Instant.now().plusSeconds(60).truncatedTo(ChronoUnit.SECONDS);
        assertEquals(expected, sessao.getFim().truncatedTo(ChronoUnit.SECONDS));
    }

    @Test
    void deveEncerrarSessaoAposDataLimite() {
        SessaoVotacao sessao = SessaoVotacao.iniciar(Instant.MIN);

        assertTrue(sessao.isFinalizada());
    }

    @Test
    void adicionarVotoDeveContabilizarResultado() {
        SessaoVotacao sessao = SessaoVotacao.iniciar(Instant.now().plusSeconds(10));

        sessao.adicionarVoto(new Voto("1", OpcaoVoto.SIM));

        assertEquals(Long.valueOf(1L), sessao.getResultadoVotacao().get(OpcaoVoto.SIM));
    }
}