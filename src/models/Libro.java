package models;

/**
 * Sottoclasse di Risorsa che rappresenta un Libro fisico o digitale.
 * Applica il principio di EREDITARIETÀ: estende Risorsa ereditandone
 * tutti gli attributi e metodi, aggiungendo l'attributo "autore".
 * Applica il POLIMORFISMO tramite l'override di visualizzaDettagli().
 */
public class Libro extends Risorsa {

    // Attributo privato specifico della sottoclasse
    private String autore;

    // -------------------------------------------------------------------------
    // Costruttore
    // -------------------------------------------------------------------------

    public Libro(String titolo, int annoPubblicazione, String codice, String autore) {
        // Richiama il costruttore della classe base Risorsa
        super(titolo, annoPubblicazione, codice);
        this.autore = autore;
    }

    // -------------------------------------------------------------------------
    // Getter e Setter (Incapsulamento)
    // -------------------------------------------------------------------------

    /** Restituisce il nome dell'autore. */
    public String getAutore() {
        return autore;
    }

    /** Imposta il nome dell'autore. */
    public void setAutore(String autore) {
        this.autore = autore;
    }

    // -------------------------------------------------------------------------
    // Override del metodo della superclasse (Polimorfismo)
    // -------------------------------------------------------------------------

    /**
     * Sovrascrive visualizzaDettagli() per includere anche l'autore.
     * Grazie al polimorfismo, quando questo metodo viene chiamato su un
     * riferimento di tipo Risorsa che punta a un Libro, Java esegue
     * automaticamente questa versione del metodo.
     */
    @Override
    public void visualizzaDettagli() {
        super.visualizzaDettagli(); // Richiama la versione della superclasse
        System.out.println("Tipo    : Libro");
        System.out.println("Autore  : " + autore);
    }
}