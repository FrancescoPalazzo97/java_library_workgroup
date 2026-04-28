/**
 * Classe base che rappresenta una generica risorsa della biblioteca digitale.
 * Applica il principio di INCAPSULAMENTO: tutti gli attributi sono privati
 * e accessibili solo tramite getter e setter pubblici.
 */
public class Risorsa {

    // Attributi privati: non accessibili direttamente dall'esterno
    private String titolo;
    private int    annoPubblicazione;
    private String codice; // Identificativo univoco della risorsa

    // -------------------------------------------------------------------------
    // Costruttore
    // -------------------------------------------------------------------------

    public Risorsa(String titolo, int annoPubblicazione, String codice) {
        this.titolo            = titolo;
        this.annoPubblicazione = annoPubblicazione;
        this.codice            = codice;
    }

    // -------------------------------------------------------------------------
    // Getter e Setter (Incapsulamento)
    // -------------------------------------------------------------------------

    /** Restituisce il titolo della risorsa. */
    public String getTitolo() {
        return titolo;
    }

    /** Imposta il titolo della risorsa. */
    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    /** Restituisce l'anno di pubblicazione. */
    public int getAnnoPubblicazione() {
        return annoPubblicazione;
    }

    /** Imposta l'anno di pubblicazione. */
    public void setAnnoPubblicazione(int annoPubblicazione) {
        this.annoPubblicazione = annoPubblicazione;
    }

    /** Restituisce il codice identificativo univoco. */
    public String getCodice() {
        return codice;
    }

    /** Imposta il codice identificativo univoco. */
    public void setCodice(String codice) {
        this.codice = codice;
    }

    // -------------------------------------------------------------------------
    // Metodi di comportamento
    // -------------------------------------------------------------------------

    /**
     * Visualizza i dettagli principali della risorsa.
     * Questo metodo verrà sovrascritto (overriding) dalle sottoclassi
     * per mostrare informazioni aggiuntive specifiche: POLIMORFISMO.
     */
    public void visualizzaDettagli() {
        System.out.println("-----------------------------");
        System.out.println("Titolo  : " + titolo);
        System.out.println("Anno    : " + annoPubblicazione);
        System.out.println("Codice  : " + codice);
    }
}