package models;

import java.util.ArrayList;

public class Utente {
    private String nome;
    private String idUtente;
    private ArrayList<Risorsa> risorseInPrestito;

    public Utente(String nome, String idUtente) {
        this.nome = nome;
        this.idUtente = idUtente;
        this.risorseInPrestito = new ArrayList<>();
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

    public ArrayList<Risorsa> getRisorseInPrestito() {
        return this.risorseInPrestito;
    }

    public void setRisorseInPrestito(ArrayList<Risorsa> risorseInPrestito) {
        this.risorseInPrestito = risorseInPrestito;
    }

    public void prendiInPrestito(Risorsa nuovaRisorsa) {
        risorseInPrestito.add(nuovaRisorsa);
    }

    public void restituisci(Risorsa risorsa) {
        boolean risultato = risorseInPrestito.remove(risorsa);
        if (!risultato) {
            System.out.println("Errore: durante l'eliminazione della risorsa");
            return;
        }
        System.out.println("Risorsa \"" + risorsa.getTitolo() + "\" restituita con successo.");
    }

    public void stampaRisorse() {
        System.out.println("=== Risorse in prestito di " + nome + " (ID: " + idUtente + ") ===");
        if (risorseInPrestito.isEmpty()) {
            System.out.println("Nessuna risorsa in prestito.");
            return;
        }
        for (Risorsa risorsa : risorseInPrestito) {
            risorsa.visualizzaDettagli();
        }
    }

}
