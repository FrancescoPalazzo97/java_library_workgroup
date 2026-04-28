
/**
 * Sottoclasse di Risorsa che rappresenta un Ebook (libro digitale).
 * Applica il principio di EREDITARIETÀ: estende Risorsa aggiungendo
 * l'attributo "formato" (es: PDF, EPUB, MOBI).
 * Applica il POLIMORFISMO tramite l'override di visualizzaDettagli().
 */
public class Ebook extends Risorsa {

    // Attributo privato specifico della sottoclasse
    private String formato; // Formato del file (PDF, EPUB, MOBI, ecc.)

    // -------------------------------------------------------------------------
    // Costruttore
    // -------------------------------------------------------------------------

    public Ebook(String titolo, int annoPubblicazione, String codice, String formato) {
        super(titolo, annoPubblicazione, codice);
        this.formato = formato;
    }

    // -------------------------------------------------------------------------
    // Getter e Setter (Incapsulamento)
    // -------------------------------------------------------------------------

    /** Restituisce il formato del file dell'ebook. */
    public String getFormato() {
        return formato;
    }

    /** Imposta il formato del file dell'ebook. */
    public void setFormato(String formato) {
        this.formato = formato;
    }

    // -------------------------------------------------------------------------
    // Override del metodo della superclasse (Polimorfismo)
    // -------------------------------------------------------------------------

    /**
     * Sovrascrive visualizzaDettagli() per includere anche il formato del file.
     */
    @Override
    public void visualizzaDettagli() {
        super.visualizzaDettagli();
        System.out.println("Tipo    : Ebook");
        System.out.println("Formato : " + formato);
    }
}