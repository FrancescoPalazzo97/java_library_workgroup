import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        Biblioteca biblioteca = new Biblioteca("Biblioteca Centrale");

        int scelta;

        do {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Aggiungi Risorsa");
            System.out.println("2. Rimuovi Risorsa");
            System.out.println("3. Registra Utente");
            System.out.println("4. Mostra Inventario");
            System.out.println("5. Cerca per Titolo");
            System.out.println("6. Esci");

            scelta = leggiIntero("Scelta: ", 1, 6);

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
                    System.out.println("Uscita...");
                    break;
            }

        } while (scelta != 6);

        scanner.close();
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