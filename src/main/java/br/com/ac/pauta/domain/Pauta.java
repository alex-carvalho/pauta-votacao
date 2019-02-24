package br.com.ac.pauta.domain;

import br.com.ac.pauta.exception.SessaoJaExisteException;
import br.com.ac.pauta.exception.SessaoNaoEncontradaException;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.Map;
import java.util.Objects;
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

    public Pauta() {
    }

    public Pauta(String id, String titulo, SessaoVotacao sessaoVotacao) {
        this.id = id;
        this.titulo = titulo;
        this.sessaoVotacao = sessaoVotacao;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setSessaoVotacao(SessaoVotacao sessaoVotacao) {
        this.sessaoVotacao = sessaoVotacao;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pauta pauta = (Pauta) o;
        return Objects.equals(id, pauta.id) &&
                Objects.equals(titulo, pauta.titulo) &&
                Objects.equals(sessaoVotacao, pauta.sessaoVotacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titulo, sessaoVotacao);
    }

    @Override
    public String toString() {
        return "Pauta{" +
                "id='" + id + '\'' +
                ", titulo='" + titulo + '\'' +
                ", sessaoVotacao=" + sessaoVotacao +
                '}';
    }
}
