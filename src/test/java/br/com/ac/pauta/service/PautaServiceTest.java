package br.com.ac.pauta.service;

import br.com.ac.pauta.domain.OpcaoVoto;
import br.com.ac.pauta.domain.Pauta;
import br.com.ac.pauta.domain.SessaoVotacao;
import br.com.ac.pauta.domain.Voto;
import br.com.ac.pauta.exception.PautaNaoEncontradaException;
import br.com.ac.pauta.exception.SessaoEncerradaException;
import br.com.ac.pauta.exception.SessaoNaoEncontradaException;
import br.com.ac.pauta.repository.PautaRepository;
import br.com.ac.pauta.stub.PautaStub;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PautaServiceTest {


    @InjectMocks
    private PautaService pautaService;
    @Mock
    private PautaRepository pautaRepository;
    @Captor
    private ArgumentCaptor<Pauta> pautaArgumentCaptor;

    @Test
    void buscaPautaSemResultadoDeveRetornarErro() {
        when(pautaRepository.findById(anyString())).thenReturn(Mono.empty());

        assertThrows(PautaNaoEncontradaException.class,
                () -> pautaService.getPauta("123").block()
        );
    }

    @Test
    void getPautas() {
    }

    @Test
    void criarPautaDevePersistirOsDados() {
        pautaService.criarPauta(PautaStub.criarPautaSemSessao());

        verify(pautaRepository, times(1)).save(pautaArgumentCaptor.capture());

        assertEquals(PautaStub.criarPautaSemSessao(), pautaArgumentCaptor.getValue());
    }

    @Test
    void abrirSessaoVotacaoSemDefinicaoDeDataFinal() {
        when(pautaRepository.findById(anyString())).thenReturn(Mono.just(PautaStub.criarPautaSemSessao()));
        when(pautaRepository.save(any())).thenReturn(Mono.just(PautaStub.criarPautaSemSessao()));

        pautaService.abrirSessaoVotacao("1", null).block();

        verify(pautaRepository, times(1)).save(pautaArgumentCaptor.capture());

        assertNotNull(pautaArgumentCaptor.getValue().getSessaoVotacao());
    }

    @Test
    void abrirSessaoVotacaoComDataFinal() {
        when(pautaRepository.findById(anyString())).thenReturn(Mono.just(PautaStub.criarPautaSemSessao()));
        when(pautaRepository.save(any())).thenReturn(Mono.just(PautaStub.criarPautaSemSessao()));

        pautaService.abrirSessaoVotacao("1", Instant.MAX).block();

        verify(pautaRepository, times(1)).save(pautaArgumentCaptor.capture());

        SessaoVotacao sessaoVotacao = pautaArgumentCaptor.getValue().getSessaoVotacao();
        assertEquals(Instant.MAX, sessaoVotacao.getFim());
    }

    @Test
    void votarSemExistirSessaoDeveRetornarErro() {
        when(pautaRepository.findById(anyString())).thenReturn(Mono.just(PautaStub.criarPautaSemSessao()));

        assertThrows(SessaoNaoEncontradaException.class,
                () -> pautaService.votar("1", new Voto("1", OpcaoVoto.SIM)).block()
        );
    }

    @Test
    void votarSessaoEncerradaDeveRetornarErro() {
        when(pautaRepository.findById(anyString())).thenReturn(Mono.just(PautaStub.criarPautaComSessaoEncerrada()));

        assertThrows(SessaoEncerradaException.class,
                () -> pautaService.votar("1", new Voto("1", OpcaoVoto.SIM)).block()
        );
    }
}