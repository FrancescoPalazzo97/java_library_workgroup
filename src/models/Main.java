import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Classe principale del programma.
 * Gestisce un menu interattivo a switch per testare tutte le funzionalità
 * della biblioteca digitale. Include robusta gestione degli errori di input:
 *   - InputMismatchException per valori non numerici in campi int
 *   - Controllo stringhe vuote o nulle
 *   - Validazione codici / ID prima di ogni operazione
 */
public class Main {

    // Scanner globale condiviso da tutti i metodi
    private static final Scanner scanner = new Scanner(System.in);

    // =========================================================================
    // ENTRY POINT
    // =========================================================================

    public static void main(String[] args) {

        // Crea la biblioteca e precarica alcuni dati di esempio
        Biblioteca biblioteca = new Biblioteca("Biblioteca Digitale UniData");
        precaricaDati(biblioteca);

        boolean esci = false;

        System.out.println("\n╔══════════════════════════════════════╗");
        System.out.println("║   BIBLIOTECA DIGITALE – Gestione     ║");
        System.out.println("╚══════════════════════════════════════╝");

        // Loop principale: rimane attivo finché l'utente non sceglie "Esci"
        while (!esci) {
            stampaMenu();
            int scelta = leggiIntero("Scelta: ", 0, 9);

            switch (scelta) {
                case 1 -> aggiungiRisorsa(biblioteca);
                case 2 -> biblioteca.stampaInventario();
                case 3 -> cercaRisorsa(biblioteca);
                case 4 -> aggiungiUtente(biblioteca);
                case 5 -> biblioteca.stampaUtenti();
                case 6 -> prestaRisorsa(biblioteca);
                case 7 -> restituisciRisorsa(biblioteca);
                case 8 -> stampaRisorseUtente(biblioteca);
                case 9 -> rimuoviRisorsa(biblioteca);
                case 0 -> {
                    System.out.println("\nArrivederci! Sessione terminata.");
                    esci = true;
                }
                // Questo default non sarà mai raggiunto grazie a leggiIntero(),
                // ma è buona pratica includerlo sempre nello switch
                default -> System.out.println("[ERRORE] Opzione non valida.");
            }
        }

        scanner.close();
    }

    // =========================================================================
    // STAMPA MENU
    // =========================================================================

    /** Stampa il menu principale delle opzioni disponibili. */
    private static void stampaMenu() {
        System.out.println("\n┌─────────────────────────────────────┐");
        System.out.println("│              MENU PRINCIPALE         │");
        System.out.println("├─────────────────────────────────────┤");
        System.out.println("│  1. Aggiungi risorsa                │");
        System.out.println("│  2. Visualizza inventario           │");
        System.out.println("│  3. Cerca risorsa per titolo        │");
        System.out.println("│  4. Aggiungi utente                 │");
        System.out.println("│  5. Visualizza utenti               │");
        System.out.println("│  6. Prendi in prestito una risorsa  │");
        System.out.println("│  7. Restituisci una risorsa         │");
        System.out.println("│  8. Risorse di un utente            │");
        System.out.println("│  9. Rimuovi risorsa dall'inventario │");
        System.out.println("│  0. Esci                            │");
        System.out.println("└─────────────────────────────────────┘");
    }

    // =========================================================================
    // CASO 1 – Aggiunta risorsa (con sotto-menu per tipo)
    // =========================================================================

    /**
     * Gestisce l'aggiunta di una nuova risorsa chiedendo prima il tipo
     * (Libro / Rivista / Ebook) e poi i dati specifici.
     */
    private static void aggiungiRisorsa(Biblioteca biblioteca) {
        System.out.println("\n-- Tipo di risorsa --");
        System.out.println("  1. Libro");
        System.out.println("  2. Rivista");
        System.out.println("  3. Ebook");

        int tipo = leggiIntero("Tipo: ", 1, 3);

        // Campi comuni a tutte le risorse
        String titolo = leggiStringa("Titolo: ");
        int    anno   = leggiIntero("Anno di pubblicazione: ", 0, 2100);
        String codice = leggiStringa("Codice univoco: ");

        Risorsa nuovaRisorsa;

        switch (tipo) {
            case 1 -> {
                // Libro: richiede l'autore
                String autore = leggiStringa("Autore: ");
                nuovaRisorsa = new Libro(titolo, anno, codice, autore);
            }
            case 2 -> {
                // Rivista: richiede il numero di uscita
                int numero = leggiIntero("Numero di uscita: ", 1, Integer.MAX_VALUE);
                nuovaRisorsa = new Rivista(titolo, anno, codice, numero);
            }
            case 3 -> {
                // Ebook: richiede il formato
                System.out.println("Formati disponibili: PDF, EPUB, MOBI, AZW");
                String formato = leggiStringa("Formato: ").toUpperCase();
                // Valida che il formato sia uno di quelli riconosciuti
                while (!formato.equals("PDF")  && !formato.equals("EPUB")
                    && !formato.equals("MOBI") && !formato.equals("AZW")) {
                    System.out.println("[ERRORE] Formato non valido. Scegli tra: PDF, EPUB, MOBI, AZW");
                    formato = leggiStringa("Formato: ").toUpperCase();
                }
                nuovaRisorsa = new Ebook(titolo, anno, codice, formato);
            }
            // Non raggiungibile grazie a leggiIntero(), ma richiesto da Java
            default -> {
                System.out.println("[ERRORE] Tipo non valido.");
                return;
            }
        }

        // Tenta l'inserimento (la biblioteca controlla i duplicati di codice)
        biblioteca.aggiungiRisorsa(nuovaRisorsa);
    }

    // =========================================================================
    // CASO 3 – Ricerca per titolo
    // =========================================================================

    /**
     * Chiede una parola chiave e stampa tutte le risorse il cui titolo
     * la contiene (ricerca parziale, case-insensitive).
     */
    private static void cercaRisorsa(Biblioteca biblioteca) {
        String chiave = leggiStringa("Inserisci titolo (o parte di esso): ");
        ArrayList<Risorsa> risultati = biblioteca.cercaPerTitolo(chiave);

        if (risultati.isEmpty()) {
            System.out.println("[INFO] Nessuna risorsa trovata con \"" + chiave + "\".");
        } else {
            System.out.println("\n-- Risultati ricerca per \"" + chiave + "\" --");
            for (Risorsa r : risultati) {
                r.visualizzaDettagli(); // Polimorfismo: metodo corretto per tipo
            }
            System.out.println("Totale trovate: " + risultati.size());
        }
    }

    // =========================================================================
    // CASO 4 – Aggiunta utente
    // =========================================================================

    /** Chiede nome e ID e registra un nuovo utente nella biblioteca. */
    private static void aggiungiUtente(Biblioteca biblioteca) {
        String nome     = leggiStringa("Nome utente: ");
        String idUtente = leggiStringa("ID utente: ");
        biblioteca.aggiungiUtente(new Utente(nome, idUtente));
    }

    // =========================================================================
    // CASO 6 – Prestito risorsa
    // =========================================================================

    /**
     * Gestisce il prestito di una risorsa a un utente.
     * Verifica che sia l'utente sia la risorsa esistano prima di procedere.
     */
    private static void prestaRisorsa(Biblioteca biblioteca) {
        // Verifica che ci siano utenti e risorse
        if (biblioteca.getListaUtenti().isEmpty()) {
            System.out.println("[INFO] Nessun utente registrato. Aggiungine uno prima (opzione 4).");
            return;
        }
        if (biblioteca.getListaRisorse().isEmpty()) {
            System.out.println("[INFO] Inventario vuoto. Aggiungi risorse prima (opzione 1).");
            return;
        }

        String idUtente = leggiStringa("ID utente: ");
        Utente utente   = biblioteca.cercaUtente(idUtente);

        // Controllo esistenza utente
        if (utente == null) {
            System.out.println("[ERRORE] Nessun utente trovato con ID \"" + idUtente + "\".");
            return;
        }

        String  codice  = leggiStringa("Codice risorsa da prendere in prestito: ");
        Risorsa risorsa = biblioteca.cercaPerCodice(codice);

        // Controllo esistenza risorsa
        if (risorsa == null) {
            System.out.println("[ERRORE] Nessuna risorsa trovata con codice \"" + codice + "\".");
            return;
        }

        // Controlla che l'utente non abbia già questa risorsa in prestito
        for (Risorsa r : utente.getRisorsePrese()) {
            if (r.getCodice().equalsIgnoreCase(codice)) {
                System.out.println("[ERRORE] \"" + utente.getNome()
                                   + "\" ha già in prestito questa risorsa.");
                return;
            }
        }

        utente.prendiInPrestito(risorsa);
    }

    // =========================================================================
    // CASO 7 – Restituzione risorsa
    // =========================================================================

    /**
     * Gestisce la restituzione di una risorsa da parte di un utente.
     * Verifica che l'utente esista e che abbia la risorsa nel suo prestito.
     */
    private static void restituisciRisorsa(Biblioteca biblioteca) {
        if (biblioteca.getListaUtenti().isEmpty()) {
            System.out.println("[INFO] Nessun utente registrato.");
            return;
        }

        String idUtente = leggiStringa("ID utente: ");
        Utente utente   = biblioteca.cercaUtente(idUtente);

        if (utente == null) {
            System.out.println("[ERRORE] Nessun utente trovato con ID \"" + idUtente + "\".");
            return;
        }

        // Mostra prima le risorse che l'utente ha in prestito
        if (utente.getRisorsePrese().isEmpty()) {
            System.out.println("[INFO] " + utente.getNome() + " non ha risorse in prestito.");
            return;
        }

        System.out.println("Risorse in prestito di " + utente.getNome() + ":");
        for (Risorsa r : utente.getRisorsePrese()) {
            System.out.println("  - [" + r.getCodice() + "] " + r.getTitolo());
        }

        String codice = leggiStringa("Codice risorsa da restituire: ");
        utente.restituisci(codice);
    }

    // =========================================================================
    // CASO 8 – Risorse di un utente
    // =========================================================================

    /** Stampa l'elenco delle risorse attualmente in prestito a un utente. */
    private static void stampaRisorseUtente(Biblioteca biblioteca) {
        if (biblioteca.getListaUtenti().isEmpty()) {
            System.out.println("[INFO] Nessun utente registrato.");
            return;
        }

        String idUtente = leggiStringa("ID utente: ");
        Utente utente   = biblioteca.cercaUtente(idUtente);

        if (utente == null) {
            System.out.println("[ERRORE] Nessun utente trovato con ID \"" + idUtente + "\".");
            return;
        }

        utente.stampaRisorseUtente();
    }

    // =========================================================================
    // CASO 9 – Rimozione risorsa dall'inventario
    // =========================================================================

    /** Rimuove una risorsa dall'inventario tramite il suo codice. */
    private static void rimuoviRisorsa(Biblioteca biblioteca) {
        if (biblioteca.getListaRisorse().isEmpty()) {
            System.out.println("[INFO] Inventario già vuoto.");
            return;
        }
        String codice = leggiStringa("Codice della risorsa da rimuovere: ");
        biblioteca.rimuoviRisorsa(codice);
    }

    // =========================================================================
    // DATI DI ESEMPIO (precaricamento)
    // =========================================================================

    /**
     * Inserisce dati di esempio alla creazione della biblioteca
     * per permettere test immediati senza inserimento manuale.
     * Dimostra tutte e tre le regole OOP:
     *   - INCAPSULAMENTO: oggetti creati tramite costruttori pubblici
     *   - EREDITARIETÀ:   Libro, Rivista, Ebook estendono Risorsa
     *   - POLIMORFISMO:   la lista ArrayList<Risorsa> contiene tipi diversi
     */
    private static void precaricaDati(Biblioteca biblioteca) {
        // Risorse di tipi diversi aggiunte alla stessa lista (polimorfismo)
        biblioteca.aggiungiRisorsa(new Libro("Il Nome della Rosa",   1980, "LIB001", "Umberto Eco"));
        biblioteca.aggiungiRisorsa(new Libro("1984",                 1949, "LIB002", "George Orwell"));
        biblioteca.aggiungiRisorsa(new Rivista("National Geographic",2023, "RIV001", 312));
        biblioteca.aggiungiRisorsa(new Rivista("Le Scienze",         2024, "RIV002", 658));
        biblioteca.aggiungiRisorsa(new Ebook("Clean Code",           2008, "EBK001", "PDF"));
        biblioteca.aggiungiRisorsa(new Ebook("The Pragmatic Programmer", 1999, "EBK002", "EPUB"));

        // Due utenti di esempio
        biblioteca.aggiungiUtente(new Utente("Giulia Rossi",   "U001"));
        biblioteca.aggiungiUtente(new Utente("Marco Bianchi",  "U002"));

        System.out.println("\n[SISTEMA] Dati di esempio caricati con successo.");
    }

    // =========================================================================
    // METODI DI INPUT ROBUSTO (gestione edge case)
    // =========================================================================

    /**
     * Legge un intero dall'input dell'utente con:
     *   - Gestione di InputMismatchException (testo invece di numero)
     *   - Controllo del range [min, max]
     *   - Ripetizione automatica finché l'input non è valido
     *
     * @param messaggio il prompt da mostrare all'utente
     * @param min       valore minimo accettabile (incluso)
     * @param max       valore massimo accettabile (incluso)
     * @return intero valido nel range specificato
     */
    private static int leggiIntero(String messaggio, int min, int max) {
        int valore = 0;
        boolean valido = false;

        while (!valido) {
            System.out.print(messaggio);
            try {
                valore = scanner.nextInt();
                scanner.nextLine(); // Consuma il newline residuo dopo nextInt()

                // Controlla che il numero sia nel range accettabile
                if (valore < min || valore > max) {
                    System.out.println("[ERRORE] Inserisci un valore tra " + min + " e " + max + ".");
                } else {
                    valido = true; // Input corretto: esce dal loop
                }

            } catch (InputMismatchException e) {
                // L'utente ha inserito un testo invece di un numero
                System.out.println("[ERRORE] Input non valido. Inserisci un numero intero.");
                scanner.nextLine(); // Svuota il buffer per evitare loop infinito
            }
        }

        return valore;
    }

    /**
     * Legge una stringa non vuota dall'input dell'utente.
     * Ripete la richiesta se l'utente preme invio senza scrivere nulla
     * o inserisce solo spazi bianchi.
     *
     * @param messaggio il prompt da mostrare all'utente
     * @return stringa non vuota e senza spazi iniziali/finali
     */
    private static String leggiStringa(String messaggio) {
        String input = "";

        while (input.isEmpty()) {
            System.out.print(messaggio);
            input = scanner.nextLine().trim(); // trim() rimuove spazi iniziali/finali

            if (input.isEmpty()) {
                System.out.println("[ERRORE] Il campo non può essere vuoto.");
            }
        }

        return input;
    }
}