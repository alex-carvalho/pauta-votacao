package br.com.ac.pauta.domain;

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

    public String getIdEleitor() {
        return idEleitor;
    }

    public OpcaoVoto getOpcaoVoto() {
        return opcaoVoto;
    }
}
