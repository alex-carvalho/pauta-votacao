package br.com.ac.pauta.domain;

import java.util.Objects;

/**
 * @author Alex Carvalho
 */
public class Voto {

    private String idEleitor;
    private OpcaoVoto opcaoVoto;

    public Voto() {
    }

    public Voto(String idEleitor, OpcaoVoto opcaoVoto) {
        this.idEleitor = idEleitor;
        this.opcaoVoto = opcaoVoto;
    }

    String getIdEleitor() {
        return idEleitor;
    }

    OpcaoVoto getOpcaoVoto() {
        return opcaoVoto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Voto voto = (Voto) o;
        return Objects.equals(idEleitor, voto.idEleitor) &&
                opcaoVoto == voto.opcaoVoto;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEleitor, opcaoVoto);
    }

    @Override
    public String toString() {
        return "Voto{" +
                "idEleitor='" + idEleitor + '\'' +
                ", opcaoVoto=" + opcaoVoto +
                '}';
    }
}
