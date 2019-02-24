package br.com.ac.pauta.domain;

import br.com.ac.pauta.exception.EleitorJaVotouException;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Alex Carvalho
 */
class VotosTest {

    @Test
    void mesmoEleitorVotarMultiplasVezesDeveRetornarErro() {
        Votos votos = new Votos();
        Voto voto = new Voto("12345", OpcaoVoto.SIM);
        Voto voto2 = new Voto("12345", OpcaoVoto.NAO);

        votos.adicionar(voto);

        assertThrows(EleitorJaVotouException.class,
                () -> votos.adicionar(voto2)
        );
    }

    @Test
    void validarResultadoVotacao() {
        Votos votos = new Votos();
        votos.adicionar(new Voto("1", OpcaoVoto.SIM));
        votos.adicionar(new Voto("2", OpcaoVoto.SIM));
        votos.adicionar(new Voto("3", OpcaoVoto.NAO));

        Map<OpcaoVoto, Long> resultadoVotacao = votos.getResultadoVotacao();

        assertAll(
                () -> assertEquals(Long.valueOf(2L), resultadoVotacao.get(OpcaoVoto.SIM)),
                () -> assertEquals(Long.valueOf(1L), resultadoVotacao.get(OpcaoVoto.NAO))
        );
    }
}