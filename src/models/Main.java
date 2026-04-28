package models;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
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
            System.out.print("Scelta: ");

            scelta = scanner.nextInt();
            scanner.nextLine();

            switch (scelta) {

                case 1:
                    System.out.println("Tipo risorsa: 1=Libro, 2=Ebook, 3=Rivista");
                    int tipo = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Titolo: ");
                    String titolo = scanner.nextLine();

                    System.out.print("Anno: ");
                    int anno = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Codice: ");
                    String codice = scanner.nextLine();

                    switch (tipo) {
                        case 1:
                            System.out.print("Autore: ");
                            String autore = scanner.nextLine();
                            biblioteca.aggiungiRisorsa(new Libro(titolo, anno, codice, autore));
                            break;

                        case 2:
                            System.out.print("Formato (PDF/EPUB...): ");
                            String formato = scanner.nextLine();
                            biblioteca.aggiungiRisorsa(new Ebook(titolo, anno, codice, formato));
                            break;

                        case 3:
                            System.out.print("Numero rivista: ");
                            int numero = scanner.nextInt();
                            biblioteca.aggiungiRisorsa(new Rivista(titolo, anno, codice, numero));
                            break;

                        default:
                            System.out.println("Tipo non valido.");
                    }
                    break;

                case 2:
                    System.out.print("Inserisci codice della risorsa da rimuovere: ");
                    String codRim = scanner.nextLine();

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
                    System.out.print("Nome utente: ");
                    String nome = scanner.nextLine();

                    System.out.print("ID utente: ");
                    String id = scanner.nextLine();

                    biblioteca.aggiungiUtente(new Utente(nome, id));
                    break;

                case 4:
                    biblioteca.stampaInventario();
                    break;

                case 5:
                    System.out.print("Titolo da cercare: ");
                    String ricerca = scanner.nextLine();
                    biblioteca.cercaPerTitolo(ricerca);
                    break;

                case 6:
                    System.out.println("Uscita...");
                    break;

                default:
                    System.out.println("Scelta non valida.");

            }
        } while (scelta != 6);

        scanner.close();
    }
}