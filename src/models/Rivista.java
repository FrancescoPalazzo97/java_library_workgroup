
/**
 * Sottoclasse di Risorsa che rappresenta una Rivista periodica.
 * Applica il principio di EREDITARIETÀ: estende Risorsa aggiungendo
 * l'attributo "numero" (numero di uscita della rivista).
 * Applica il POLIMORFISMO tramite l'override di visualizzaDettagli().
 */
public class Rivista extends Risorsa {

    // Attributo privato specifico della sottoclasse
    private int numero; // Numero di uscita della rivista

    // -------------------------------------------------------------------------
    // Costruttore
    // -------------------------------------------------------------------------

    public Rivista(String titolo, int annoPubblicazione, String codice, int numero) {
        super(titolo, annoPubblicazione, codice);
        this.numero = numero;
    }

    // -------------------------------------------------------------------------
    // Getter e Setter (Incapsulamento)
    // -------------------------------------------------------------------------

    /** Restituisce il numero di uscita della rivista. */
    public int getNumero() {
        return numero;
    }

    /** Imposta il numero di uscita della rivista. */
    public void setNumero(int numero) {
        this.numero = numero;
    }

    // -------------------------------------------------------------------------
    // Override del metodo della superclasse (Polimorfismo)
    // -------------------------------------------------------------------------

    /**
     * Sovrascrive visualizzaDettagli() per includere anche il numero di uscita.
     */
    @Override
    public void visualizzaDettagli() {
        super.visualizzaDettagli();
        System.out.println("Tipo    : Rivista");
        System.out.println("Numero  : " + numero);
    }
}