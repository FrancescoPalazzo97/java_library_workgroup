package models;

import java.util.ArrayList;

public class Utente {
    private String nome;
    private String idUtente;
    private ArrayList<Risorsa> risorseInPrestito;

    public Utente(String nome, String idUtente) {
        this.nome = nome;
        this.idUtente = idUtente;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIdUtente() {
        return this.idUtente;
    }

    public void setIdUtente(String idUtente) {
        this.idUtente = idUtente;
    }

}
