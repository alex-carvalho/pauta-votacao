package br.com.ac.pauta.api.v1;

import br.com.ac.pauta.api.v1.response.PautaResponse;
import br.com.ac.pauta.config.JacksonConfig;
import br.com.ac.pauta.service.PautaService;
import br.com.ac.pauta.stub.PautaStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class PautaApiTest {

    private WebTestClient webTestClient;

    @Mock
    private PautaService pautaService;
    @Spy
    private JacksonConfig jacksonConfig;

    @BeforeEach
    void setUp() {
        webTestClient = WebTestClient
                .bindToController(new PautaApi(pautaService, jacksonConfig.objectMapper()))
                .configureClient()
                .baseUrl("/v1/pautas")
                .build();
    }

    @Test
    void getPautas() {
        when(pautaService.getPautas()).thenReturn(Flux.just(PautaStub.criarPautaSemSessao()));

        webTestClient.get()
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(PautaResponse.class);
    }

    @Test
    void getPauta() {

        when(pautaService.getPauta(anyString())).thenReturn(Mono.just(PautaStub.criarPautaSemSessao()));

        webTestClient.get()
                .uri("/idPauta")
                .exchange()
                .expectStatus().isOk()
                .expectBody(PautaResponse.class);
    }

    @Test
    void criarPauta() {
        when(pautaService.criarPauta(any())).thenReturn(Mono.just(PautaStub.criarPautaSemSessao()));

        webTestClient.post()
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .syncBody("{}")
                .exchange()
                .expectStatus().isCreated()
                .expectBody(PautaResponse.class);
    }

    @Test
    void abrirSessaoVotacao() {
        when(pautaService.abrirSessaoVotacao(any(), any())).thenReturn(Mono.empty());

        webTestClient.post().uri("/id/abrir-sessao")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .syncBody("{}")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void votar() {
        when(pautaService.votar(any(), any())).thenReturn(Mono.empty());

        webTestClient.post().uri("/id/votar")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .syncBody("{\"idEleitor\":\"1\",\"opcaoVoto\":\"SIM\"}")
                .exchange()
                .expectStatus()
                .isOk();
    }
}