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
        for (Risorsa risorsaEsistente : listaRisorseDisponibili) {
            if (risorsaEsistente.getCodice().equalsIgnoreCase(risorsa.getCodice())) {
                System.out.println("Risorsa \"" + risorsa.getTitolo() + "\" già presente nella biblioteca [" + risorsa.getCodice() + "].");
                return;
            }
        }
        listaRisorseDisponibili.add(risorsa);
        System.out.println("Risorsa \"" + risorsa.getTitolo() + "\" aggiunta alla biblioteca [" + risorsa.getCodice() + "].");
    }

    public void rimuoviRisorsa(Risorsa risorsa) {
        for (Risorsa risorsaEsistente : listaRisorseDisponibili) {
            if (risorsaEsistente.getCodice().equalsIgnoreCase(risorsa.getCodice())) {
                listaRisorseDisponibili.remove(risorsaEsistente);
                System.out.println("Risorsa \"" + risorsa.getTitolo() + "\" rimossa dalla biblioteca [" + risorsa.getCodice() + "].");
                return;
            }
        }
        System.out.println("Risorsa \"" + risorsa.getTitolo() + "\" non presente nella biblioteca [" + risorsa.getCodice() + "].");
    }

    public void aggiungiUtente(Utente utente) {
        for (Utente utenteEsistente : listaUtenti) {
            if (utenteEsistente.getIdUtente().equalsIgnoreCase(utente.getIdUtente())) {
                System.out.println("Utente \"" + utente.getNome() + "\" già registrato [" + utente.getIdUtente() + "].");
                return;
            }
        }
        listaUtenti.add(utente);
        System.out.println("Utente \"" + utente.getNome() + "\" registrato [" + utente.getIdUtente() + "].");
    }

    public void stampaInventario() {
        System.out.println("=== Inventario della Biblioteca: " + nome + " ===");
        if (listaRisorseDisponibili.isEmpty()) {
            System.out.println("Nessuna risorsa disponibile.");
            return;
        }
        for (Risorsa risorsa : listaRisorseDisponibili) {
            risorsa.visualizzaDettagli(); // polimorfismo: chiama l'override corretto per ogni tipo
        }
        System.out.println("-----------------------------");
    }

    public void cercaPerTitolo(String titolo) {
        System.out.println("=== Ricerca per titolo: \"" + titolo + "\" ===");
        boolean trovato = false;
        for (Risorsa risorsa : listaRisorseDisponibili) {
            if (risorsa.getTitolo().equalsIgnoreCase(titolo)) {
                risorsa.visualizzaDettagli();
                trovato = true;
            }
        }
        if (!trovato) {
            System.out.println("Nessuna risorsa trovata con titolo \"" + titolo + "\".");
        }
    }
}