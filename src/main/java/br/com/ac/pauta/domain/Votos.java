package br.com.ac.pauta.domain;

import br.com.ac.pauta.exception.EleitorJaVotouException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Alex Carvalho
 */
class Votos {

    private List<Voto> valores = new ArrayList<>();

    void adicionar(Voto voto) {
        validarEleitor(voto.getIdEleitor());

        valores.add(voto);
    }

    private void validarEleitor(String idEleitor) {
        if (valores.stream().anyMatch(voto -> voto.getIdEleitor().equals(idEleitor)))
            throw new EleitorJaVotouException(idEleitor);
    }

    Map<OpcaoVoto, Long> getResultadoVotacao() {
        return valores.stream()
                .collect(Collectors.groupingBy(Voto::getOpcaoVoto, Collectors.counting()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Votos votos = (Votos) o;
        return Objects.equals(valores, votos.valores);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valores);
    }

    @Override
    public String toString() {
        return "Votos{" +
                "valores=" + valores +
                '}';
    }
}
