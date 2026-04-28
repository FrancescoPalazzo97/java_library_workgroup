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

    public String getNome() {
        return this.nome;
    }

    public ArrayList<Risorsa> getListaRisorseDisponibili() {
        return this.listaRisorseDisponibili;
    }

    public ArrayList<Utente> getListaUtenti() {
        return this.listaUtenti;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public Risorsa cercaRisorsaPerCodice(String codice) {
        for (Risorsa risorsa : listaRisorseDisponibili) {
            if (risorsa.getCodice().equalsIgnoreCase(codice)) {
                return risorsa;
            }
        }
        return null;
    }

    public Utente cercaUtente(String idUtente) {
        for (Utente utente : listaUtenti) {
            if (utente.getIdUtente().equalsIgnoreCase(idUtente)) {
                return utente;
            }
        }
        return null;
    }

    public void prestaRisorsa(String idUtente, String codiceRisorsa) {
        Utente utente = cercaUtente(idUtente);
        if (utente == null) {
            System.out.println("Utente con ID \"" + idUtente + "\" non trovato.");
            return;
        }
        Risorsa risorsa = cercaRisorsaPerCodice(codiceRisorsa);
        if (risorsa == null) {
            System.out.println("Risorsa con codice \"" + codiceRisorsa + "\" non disponibile.");
            return;
        }
        listaRisorseDisponibili.remove(risorsa);
        utente.prendiInPrestito(risorsa);
        System.out.println("Risorsa \"" + risorsa.getTitolo() + "\" prestata a " + utente.getNome() + ".");
    }

    public void restituisciRisorsa(String idUtente, String codiceRisorsa) {
        Utente utente = cercaUtente(idUtente);
        if (utente == null) {
            System.out.println("Utente con ID \"" + idUtente + "\" non trovato.");
            return;
        }
        Risorsa risorsa = null;
        for (Risorsa r : utente.getRisorseInPrestito()) {
            if (r.getCodice().equalsIgnoreCase(codiceRisorsa)) {
                risorsa = r;
                break;
            }
        }
        if (risorsa == null) {
            System.out.println(utente.getNome() + " non ha in prestito una risorsa con codice \"" + codiceRisorsa + "\".");
            return;
        }
        utente.restituisci(risorsa);
        listaRisorseDisponibili.add(risorsa);
    }
}