import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        Biblioteca biblioteca = new Biblioteca("Biblioteca Centrale");
        seedDatiIniziali(biblioteca);

        int scelta;

        do {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Aggiungi Risorsa");
            System.out.println("2. Rimuovi Risorsa");
            System.out.println("3. Registra Utente");
            System.out.println("4. Mostra Inventario");
            System.out.println("5. Cerca per Titolo");
            System.out.println("6. Presta Risorsa");
            System.out.println("7. Restituisci Risorsa");
            System.out.println("8. Risorse in Prestito Utente");
            System.out.println("9. Esci");

            scelta = leggiIntero("Scelta: ", 1, 9);

            switch (scelta) {

                case 1:
                    int tipo = leggiIntero("Tipo risorsa (1=Libro, 2=Ebook, 3=Rivista): ", 1, 3);

                    String titolo = leggiStringaNonVuota("Titolo: ");
                    int anno = leggiIntero("Anno: ", 0, 3000);
                    String codice = leggiStringaNonVuota("Codice: ");

                    switch (tipo) {
                        case 1:
                            String autore = leggiStringaNonVuota("Autore: ");
                            biblioteca.aggiungiRisorsa(new Libro(titolo, anno, codice, autore));
                            break;

                        case 2:
                            String formato = leggiStringaNonVuota("Formato (PDF/EPUB...): ");
                            biblioteca.aggiungiRisorsa(new Ebook(titolo, anno, codice, formato));
                            break;

                        case 3:
                            int numero = leggiIntero("Numero rivista: ", 1, 10000);
                            biblioteca.aggiungiRisorsa(new Rivista(titolo, anno, codice, numero));
                            break;
                    }
                    break;

                case 2:
                    String codRim = leggiStringaNonVuota("Inserisci codice della risorsa da rimuovere: ");

                    Risorsa daRimuovere = null;
                    for (Risorsa r : biblioteca.getListaRisorseDisponibili()) {
                        if (r.getCodice().equalsIgnoreCase(codRim)) {
                            daRimuovere = r;
                            break;
                        }
                    }

                    if (daRimuovere != null) {
                        biblioteca.rimuoviRisorsa(daRimuovere);
                    } else {
                        System.out.println("Risorsa non trovata.");
                    }
                    break;

                case 3:
                    String nome = leggiStringaNonVuota("Nome utente: ");
                    String id = leggiStringaNonVuota("ID utente: ");

                    biblioteca.aggiungiUtente(new Utente(nome, id));
                    break;

                case 4:
                    biblioteca.stampaInventario();
                    break;

                case 5:
                    String ricerca = leggiStringaNonVuota("Titolo da cercare: ");
                    biblioteca.cercaPerTitolo(ricerca);
                    break;

                case 6:
                    String idPrestito = leggiStringaNonVuota("ID utente: ");
                    String codPrestito = leggiStringaNonVuota("Codice risorsa da prestare: ");
                    biblioteca.prestaRisorsa(idPrestito, codPrestito);
                    break;

                case 7:
                    String idResa = leggiStringaNonVuota("ID utente: ");
                    String codResa = leggiStringaNonVuota("Codice risorsa da restituire: ");
                    biblioteca.restituisciRisorsa(idResa, codResa);
                    break;

                case 8:
                    String idStampa = leggiStringaNonVuota("ID utente: ");
                    Utente utente = biblioteca.cercaUtente(idStampa);
                    if (utente == null) {
                        System.out.println("Utente con ID \"" + idStampa + "\" non trovato.");
                    } else {
                        utente.stampaRisorse();
                    }
                    break;

                case 9:
                    System.out.println("Uscita...");
                    break;
            }

        } while (scelta != 9);

        scanner.close();
    }

    private static void seedDatiIniziali(Biblioteca biblioteca) {
        System.out.println("=== Caricamento dati iniziali ===");
        biblioteca.aggiungiRisorsa(new Libro("Il nome della rosa", 1980, "L001", "Umberto Eco"));
        biblioteca.aggiungiRisorsa(new Libro("1984", 1949, "L002", "George Orwell"));
        biblioteca.aggiungiRisorsa(new Ebook("Effective Java", 2018, "E001", "PDF"));
        biblioteca.aggiungiRisorsa(new Rivista("National Geographic", 2024, "R001", 256));
        biblioteca.aggiungiUtente(new Utente("Mario Rossi", "U001"));
        biblioteca.aggiungiUtente(new Utente("Luigi Bianchi", "U002"));
    }

    // =========================
    // METODI DI SUPPORTO
    // =========================

    private static int leggiIntero(String messaggio, int min, int max) {
        int valore = 0;
        boolean valido = false;

        while (!valido) {
            System.out.print(messaggio);
            try {
                valore = scanner.nextInt();
                scanner.nextLine();

                if (valore < min || valore > max) {
                    System.out.println("[ERRORE] Inserisci un valore tra " + min + " e " + max + ".");
                } else {
                    valido = true;
                }

            } catch (InputMismatchException e) {
                System.out.println("[ERRORE] Input non valido. Inserisci un numero intero.");
                scanner.nextLine();
            }
        }
        return valore;
    }

    private static String leggiStringaNonVuota(String messaggio) {
        String input;

        do {
            System.out.print(messaggio);
            input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("[ERRORE] Il campo non può essere vuoto.");
            }

        } while (input.isEmpty());

        return input;
    }
}