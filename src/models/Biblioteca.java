package models;

import java.util.ArrayList;

public class Biblioteca {

    private String nome;
    private ArrayList<Risorsa> listaRisorseDisponibili = new ArrayList<>();
    private ArrayList<Utente> listaUtenti = new ArrayList<>();

    public Biblioteca() {
    }

    public Biblioteca(String nome) {
        this.nome = nome;
    }

    String getNome() {
        return this.nome;
    }

    ArrayList<Risorsa> getListaRisorseDisponibili() {
        return this.listaRisorseDisponibili;
    }

    ArrayList<Utente> getListaUtenti() {
        return this.listaUtenti;
    }

    void setNome(String _nome) {
        this.nome = _nome;
    }

    public void aggiungiRisorsa(Risorsa risorsa) {
        for (Risorsa r : listaRisorseDisponibili) {
            if (r.getCodice().equalsIgnoreCase(risorsa.getCodice())) {
                System.out.println("Risorsa -> " + risorsa.getTitolo() + " già presente nella biblioteca - codice: ["
                        + risorsa.getCodice() + "].");
            } else {
                System.out.println("Risorsa -> " + risorsa.getTitolo() + " aggiunta alla biblioteca - codice: ["
                        + risorsa.getCodice() + "]");

            }
        }
    }

    public void rimuoviRisorsa(Risorsa risorsa)
    {
        for (Risorsa r : listaRisorseDisponibili) {
            if (r.getCodice().equalsIgnoreCase(risorsa.getCodice())) {
                System.out.println("Risorsa -> " + risorsa.getTitolo() + " rimossa dalla biblioteca - codice: ["
                        + risorsa.getCodice() + "].");
            } else {
                System.out.println("Risorsa -> " + risorsa.getTitolo() + " non presente nella biblioteca - codice: ["
                        + risorsa.getCodice() + "]");
            }
        }

    }
}
