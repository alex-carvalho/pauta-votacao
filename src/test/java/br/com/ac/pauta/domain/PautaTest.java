package br.com.ac.pauta.domain;

import br.com.ac.pauta.stub.PautaStub;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PautaTest {

    @Test
    void sessaoFinalizada() {
        Pauta pauta = PautaStub.criarPautaComSessaoEncerrada();

        assertTrue(pauta.isFinalizada());
    }

    @Test
    void sessaoAberta() {
        Pauta pauta = PautaStub.criarPautaComSessaoAberta();

        assertFalse(pauta.isFinalizada());
    }

    @Test
    void novaPautaNaoDevePossuirResultados() {
        assertNull(new Pauta().getResultadoVotacao());
    }

    @Test
    void pautaDeveContabilizarOsResultados() {
        Pauta pauta = PautaStub.criarPautaComSessaoAberta();
        pauta.votar(new Voto("1", OpcaoVoto.SIM));

        assertEquals(Long.valueOf(1L), pauta.getResultadoVotacao().get(OpcaoVoto.SIM));
    }
}