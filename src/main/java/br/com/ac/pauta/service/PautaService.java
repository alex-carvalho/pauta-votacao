package br.com.ac.pauta.service;

import br.com.ac.pauta.domain.Pauta;
import br.com.ac.pauta.domain.Voto;
import br.com.ac.pauta.exception.PautaNaoEncontradaException;
import br.com.ac.pauta.repository.PautaRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;

/**
 * @author Alex Carvalho
 */
@Service
public class PautaService {

    private final PautaRepository pautaRepository;

    public PautaService(PautaRepository pautaRepository) {
        this.pautaRepository = pautaRepository;
    }

    public Mono<Pauta> getPauta(String idPauta) {
        return pautaRepository.findById(idPauta)
                .switchIfEmpty(Mono.defer(() -> Mono.error(new PautaNaoEncontradaException(idPauta))));
    }

    public Flux<Pauta> getPautas() {
        return pautaRepository.findAll();
    }

    public Mono<Pauta> criarPauta(Pauta pauta) {
        return pautaRepository.save(pauta);
    }

    public Mono<Void> abrirSessaoVotacao(String idPauta, Instant finalSessao) {
        return getPauta(idPauta)
                .map(pauta -> pauta.iniciarSessao(finalSessao))
                .flatMap(pautaRepository::save)
                .then();
    }

    public Mono<Void> votar(String idPauta, Voto voto) {
        return getPauta(idPauta)
                .map(pauta -> pauta.votar(voto))
                .flatMap(pautaRepository::save)
                .then();
    }
}
