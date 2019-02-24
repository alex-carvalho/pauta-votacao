package br.com.ac.pauta.api.v1;

import br.com.ac.pauta.api.v1.request.AbrirSessaoRequest;
import br.com.ac.pauta.api.v1.request.PautaRequest;
import br.com.ac.pauta.api.v1.request.VotoRequest;
import br.com.ac.pauta.api.v1.response.PautaResponse;
import br.com.ac.pauta.domain.Pauta;
import br.com.ac.pauta.domain.Voto;
import br.com.ac.pauta.service.PautaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

/**
 * @author Alex Carvalho
 */
@RestController
@RequestMapping("/v1/pautas")
@Api(description = "Api para gerenciamento de pautas.")
public class PautaApi {

    private final Logger logger = LoggerFactory.getLogger(PautaApi.class);

    private final PautaService pautaService;
    private final ObjectMapper objectMapper;

    public PautaApi(PautaService pautaService, ObjectMapper objectMapper) {
        this.pautaService = pautaService;
        this.objectMapper = objectMapper;
    }

    @GetMapping
    @ApiOperation(value = "Retorna uma lista de todas as pautas.", response = PautaResponse[].class)
    public Flux<PautaResponse> getPautas() {
        logger.info("Buscando todas as pautas.");
        return pautaService.getPautas()
                .map(pauta -> objectMapper.convertValue(pauta, PautaResponse.class))
                .doOnComplete(() -> logger.info("Retornando lista de pautas."));
    }

    @GetMapping("/{idPauta}")
    @ApiOperation(value = "Retorna uma pauta especifica.", response = PautaResponse.class)
    public Mono<PautaResponse> getPauta(@PathVariable("idPauta") String idPauta) {
        logger.info("Bucando pauta {}.", idPauta);
        return pautaService.getPauta(idPauta)
                .map(pauta -> objectMapper.convertValue(pauta, PautaResponse.class))
                .doOnSuccess(it -> logger.info("Retornando pauta especifica."));
    }

    @PostMapping
    @ApiOperation(value = "Cria uma nova pauta.", response = PautaResponse.class)
    public Mono<ResponseEntity<PautaResponse>> criarPauta(@RequestBody @Valid PautaRequest pautaRequest) {
        logger.info("Chamada para criar pauta: {}.", pautaRequest);
        return pautaService
                .criarPauta(objectMapper.convertValue(pautaRequest, Pauta.class))
                .map(pauta -> objectMapper.convertValue(pauta, PautaResponse.class))
                .map(pautaResponse -> ResponseEntity.status(HttpStatus.CREATED).body(pautaResponse))
                .doOnSuccess(pautaResponse -> logger.info("Pauta criada com sucesso!"));
    }

    @PostMapping("/{idPauta}/abrir-sessao")
    @ApiOperation(value = "Inicia uma sessão na pauta especificada.")
    public Mono<ResponseEntity> abrirSessaoVotacao(@PathVariable("idPauta") String idPauta,
                                                   @RequestBody AbrirSessaoRequest abrirSessaoRequest) {
        logger.info("Pauta {} tentativa de abertura de sessão {}.", idPauta, abrirSessaoRequest);
        return pautaService.abrirSessaoVotacao(idPauta, abrirSessaoRequest.getFinalSessao())
                .map(it -> (ResponseEntity) ResponseEntity.ok().build())
                .doOnSuccess(it -> logger.info("Sessão iniciado com sucesso."));
    }

    @PostMapping("/{idPauta}/votar")
    @ApiOperation(value = "Realiza o voto na pauta especificada.")
    public Mono<ResponseEntity> votar(@PathVariable("idPauta") String idPauta,
                                      @RequestBody @Valid VotoRequest votoRequest) {
        logger.info("Pauta {} adicionando voto {}.", idPauta, votoRequest);
        return pautaService.votar(idPauta, objectMapper.convertValue(votoRequest, Voto.class))
                .map(it -> (ResponseEntity) ResponseEntity.ok().build())
                .doOnSuccess(it -> logger.info("Voto adicionado com sucesso."));
    }

}
