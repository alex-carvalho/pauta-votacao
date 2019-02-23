package br.com.ac.pauta.repository;

import br.com.ac.pauta.domain.Pauta;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

/**
 * @author Alex Carvalho
 */
public interface PautaRepository extends ReactiveMongoRepository<Pauta, String> {
}
