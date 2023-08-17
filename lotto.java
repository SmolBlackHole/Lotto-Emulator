import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class lotto {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter(System.lineSeparator());

        String[] mainMenuOptions = {
                "Tipps eingeben",
                "Automatische Ziehung (bis 6 Richtige)",
                "Manuelle Ziehung",
                colorize("Beenden", "rot")
        };
        boolean codeIsRunning = true;

        // Initialisiert Tipps mit den Werten 1-6 um mögliche Fehler zu umgehen
        // {null} auch möglich, umgeht aber in manchen Fällen Sicherheitsabfrage
        int[] tipps = { 1, 2, 3, 4, 5, 6 };

        while (codeIsRunning) {
            clearConsole();

            // Menüauswahl abfragen
            int selector = menuSelector(scanner, mainMenuOptions, "Hauptmenü");

            switch (selector) {
                case 0: // Tipps Menü
                    tipps = enterTippsMenu(scanner, tipps);
                    if (tipps != null) {
                        // Zeigt eingegebene Tipps ein
                        showTipps(tipps);
                        System.out.println(colorize("Tipps eingereicht", "gruen"));
                        scanner.nextLine();
                    } else {
                        System.out.println(colorize("Zuerst Tipps eingeben", "rot"));
                        scanner.nextLine();
                    }
                    break;
                case 1: // Automatische Ziehung bis 6 Richtige
                    if (tipps != null) {
                        playAutomatic(tipps, scanner);
                    } else {
                        System.out.println(colorize("Zuerst Tipps eingeben!", "rot"));
                        System.out.print(colorize("Fertig, zurück zum Menü", "gold"));
                        scanner.nextLine();
                        scanner.nextLine(); // zweimal um unverbrauchtes Zeichen zu konsumieren
                    }
                    break;
                case 2: // Manuelle Ziehung
                    if (tipps != null) {
                        playManual(scanner, tipps);
                    } else {
                        System.out.println(colorize("Zuerst Tipps eingeben!", "rot"));
                        System.out.print(colorize("Fertig, zurück zum Menü", "gold"));
                        scanner.nextLine();
                        scanner.nextLine(); // zweimal um unverbrauchtes Zeichen zu konsumieren
                    }
                    break;
                case 3: // Beenden
                    codeIsRunning = false;
                    break;
                default:
                    System.out.println(colorize("Auswahl ungültig", "rot"));
                    scanner.nextLine();
                    break;
            }

        }
        scanner.close();
    }

    private static int[] enterTippsMenu(Scanner scanner, int[] existingTipps) {
        String[] tippsmainMenuOptions = {
                colorize("Tipps eingeben", " "),
                colorize("Tipps ändern", " "),
                colorize("Zufällige Tipps wählen", " "),
                colorize("Tipps anzeigen", " "),
                colorize("Zurück zum Hauptmenü", "rot"),
        };
        int[] tipps;
        boolean isEnteringTipps = true;

        // Überpürfung, ob Tipps bereits vorhanden sind
        if (existingTipps != null) {
            tipps = new int[existingTipps.length];
            System.arraycopy(existingTipps, 0, tipps, 0, existingTipps.length);
        } else {
            tipps = new int[6]; // Neues Array für die Tipps erstellen
        }

        while (isEnteringTipps) {
            clearConsole();
            int selector = menuSelector(scanner, tippsmainMenuOptions, "Tipps-Menü");

            switch (selector) {
                case 0: // Tipps eingabe
                    clearConsole();
                    printTitle("Lotto-Spiel", "gold");
                    System.out.println(colorize("Tipps eingeben", "gruen"));
                    tipps = enterNewTipps(scanner);
                    System.out.print(colorize("Fertig, jetzt einreichen", "gold"));
                    scanner.nextLine();
                    scanner.nextLine();
                    break;
                case 1: // Tipps ändern
                    clearConsole();
                    printTitle("Lotto-Spiel", "gold");
                    System.out.println(colorize("Tipps ändern", "gruen"));
                    tipps = modifyTipps(scanner, tipps);
                    System.out.print(colorize("Fertig, jetzt einreichen", "gold"));
                    scanner.nextLine();
                    break;
                case 2: // Zufällige Tipps generieren
                    clearConsole();
                    printTitle("Lotto-Spiel", "gold");
                    System.out.println(colorize("Zufällige Tipps wählen", "gruen"));
                    tipps = generateRandomTipps();
                    showTipps(tipps);
                    System.out.print(colorize("Fertig, jetzt einreichen", "gold"));
                    scanner.nextLine();
                    scanner.nextLine();
                    break;
                case 3: // Tipps anzeigen
                    clearConsole();
                    printTitle("Lotto-Spiel", "gold");
                    System.out.println(colorize("Tipps anzeigen", "gruen"));
                    showTipps(tipps);
                    System.out.print(colorize("Fertig", "gold"));
                    scanner.nextLine();
                    scanner.nextLine();
                    break;
                case 4: // Zurück zum Hauptmenü
                    clearConsole();
                    boolean invalidTips = false;

                    // Prüfung ob Tipps gültig sind
                    for (int tipp : tipps) {
                        if (tipp < 1 || tipp > 49) {
                            invalidTips = true;
                            break;
                        }
                    }

                    if (invalidTips) {
                        System.out.println(
                                colorize("Tipps fehlerhaft, Zahlen von 1 bis 49", "rot"));
                    }
                    scanner.nextLine();

                    isEnteringTipps = false;
                    break;
                default:
                    System.out.println(colorize("Auswahl ungültig", "rot"));
                    scanner.nextLine();
                    break;
            }
        }
        return tipps;
    }

    private static int[] enterNewTipps(Scanner scanner) {
        int[] tipps = new int[6];
        boolean isValid = true;

        for (int i = 0; i < 6; ++i) {
            isValid = false; // Setze isValid auf false, um die Schleife zu starten

            while (!isValid) {
                System.out.print(colorize("Tipp " + (i + 1) + ": ", "blau"));
                if (scanner.hasNextInt()) {
                    int newTipp = scanner.nextInt();

                    if (newTipp >= 1 && newTipp <= 49) {
                        if (!contains(tipps, newTipp)) {
                            tipps[i] = newTipp;
                            isValid = true; // Setze isValid auf true, um die Schleife zu beenden
                        } else {
                            System.out.println(colorize("Tipp bereits vorhanden, anderen Tipp eingeben.", "rot"));
                        }
                    } else {
                        System.out.println(colorize("Tipps fehlerhaft, Zahlen von 1 bis 49", "rot"));
                    }
                } else {
                    scanner.next(); // Überspringe ungültige Eingabe
                    System.out.println(colorize("Ungültige Eingabe, bitte eine Zahl eingeben.", "rot"));
                }
            }
        }
        return tipps;
    }

    private static int[] modifyTipps(Scanner scanner, int[] tipps) {
        boolean isModifying = true;

        while (isModifying) {
            clearConsole();
            printTitle("Lotto-Spiel", "gold");

            System.out.println(colorize("Tipps ändern", "gruen"));
            for (int i = 0; i < 6; ++i) {
                System.out.println(colorize("Tipp " + (i + 1) + ": " + tipps[i], "blau"));
            }
            System.out.println(colorize("7: Fertig, jetzt einreichen", "gold"));
            System.out.print(colorize("Tipp (1-6) zum Ändern wählen: ", "gelb"));

            if (scanner.hasNextInt()) {
                int tippIndex = scanner.nextInt();
                scanner.nextLine(); // Verwerfe den Rest der Zeile nach der Ganzzahleingabe

                if (tippIndex >= 1 && tippIndex <= 6) {
                    System.out.print(colorize("Neuen Tipp-Wert für " + tippIndex + " eingeben: ", "hellgruen"));

                    if (scanner.hasNextInt()) {
                        int newTippValue = scanner.nextInt();
                        scanner.nextLine(); // Konsumieret restliche Zeichen nach Ganzzahleingabe

                        if (newTippValue >= 1 && newTippValue <= 49 && !contains(tipps, newTippValue)) {
                            // Überprüfung, ob der neue Wert genauso groß wie der alte Wert ist
                            if (tipps[tippIndex - 1] == newTippValue) {
                                System.out.println(colorize(
                                        "Werte identisch, alter Wert wird mit neuem Wert getauscht",
                                        "gelb"));
                            }

                            tipps[tippIndex - 1] = newTippValue;
                            showTipps(tipps);
                        } else {
                            System.out.println(colorize("Ungültiger Tipp-Wert", "rot"));
                        }
                    } else {
                        clearConsole();
                        printTitle("Lotto-Spiel", "gold");
                        System.out.println(colorize("Ungültige Eingabe, bitte eine Zahl eingeben.", "rot"));
                        scanner.nextLine();
                        scanner.nextLine();
                    }
                } else if (tippIndex == 7) {
                    isModifying = false;
                } else {
                    System.out.println(colorize("Auswahl ungültig", "rot"));
                }
            } else {
                clearConsole();
                printTitle("Lotto-Spiel", "gold");
                System.out.println(colorize("Ungültige Eingabe, bitte eine Zahl eingeben.", "rot"));
                scanner.nextLine();
            }
        }
        return tipps;
    }

    private static int[] generateRandomTipps() {
        int[] tipps = new int[6];
        boolean[] usedNumbers = new boolean[49]; // Array zur Verfolgung bereits verwendeter Zahlen

        // Generiert 6 zufällige Tipps
        for (int i = 0; i < 6; ++i) {
            int randomNumber;
            do {
                // Generiert zufällige Zahlen zw. (einschließlich) 1 und 49
                randomNumber = (int) (Math.random() * 49) + 1;
            } while (usedNumbers[randomNumber - 1]); // Prüft, ob Zahl bereits vorhanden ist

            // Fügt Tipp zum Array hinzu und markiert die Zahl als verwendet
            tipps[i] = randomNumber; // generierter Wert wird an der aktuellen Pos i gespeichert
            usedNumbers[randomNumber - 1] = true; // generierter Wert wird in usedNumbers als true markiert
        }
        return tipps;
    }

    private static void showTipps(int[] tipps) { // Zeigt alle Tipps an
        clearConsole();
        printTitle("Lotto-Spiel", "gold");
        System.out.println(colorize("Tipps:", "gold"));
        for (int i = 0; i < 6; ++i) {
            // Zeigt jeden Tipp mit seiner entsprechenden Position an
            System.out.println(colorize("Tipp " + (i + 1) + ": " + tipps[i], "blau"));
        }
    }

    private static void playManual(Scanner scanner, int[] tipps) {
        printTitle("Lotto-Spiel", "gold");
        System.out.println(colorize("Manuelle Ziehungen", "gruen"));

        // Eingabe der Anzahl der zu spielenden Runden
        System.out.print(colorize("Anzahl der zu spielenden Runden eingeben (Zahl): ", "blau"));
        int rounds = 0;
        boolean validInput = false;
        while (!validInput) {
            if (scanner.hasNextInt()) {
                rounds = scanner.nextInt();
                if (rounds > 0) {
                    validInput = true;
                } else {
                    System.out.print(colorize("Eingabe ungültig, nur Zahlen: ", "rot"));
                }
            } else {
                System.out.print(colorize("Eingabe ungültig, nur Zahlen: ", "rot"));
                scanner.next(); // Überspringe ungültige Eingabe
            }
        }
        scanner.nextLine();

        // Array zur Zählung der Treffer in verschiedenen Kategorien initialisieren
        int[] countMatches = new int[7];
        int totalMatches = 0;

        if (rounds <= 10) {
            // Schleife für jede Runde anzeigen
            for (int r = 1; r <= rounds; ++r) {
                System.out.println(colorize("Runde " + r, "gold"));

                // Array für die gezogenen Zahlen initialisieren
                int[] drawnNumbers = new int[6];
                for (int i = 0; i < 6; ++i) {
                    int number;
                    do {
                        number = (int) (Math.random() * 49) + 1;
                    } while (contains(drawnNumbers, number));

                    drawnNumbers[i] = number;
                }

                // Gezogene Zahlen anzeigen
                System.out.print("Gezogene Zahlen: ");
                for (int number : drawnNumbers) {
                    System.out.print(number + " ");
                }
                System.out.println();

                // Anzahl der Treffer ermitteln und anzeigen
                int matches = countMatches(tipps, drawnNumbers);
                System.out.println("Treffer: " + matches);

                // Falls es Treffer gibt, diese anzeigen
                if (matches > 0) {
                    System.out.print("Treffer: ");
                    for (int number : drawnNumbers) {
                        if (contains(tipps, number)) {
                            System.out.print(colorize(number + " ", "gold"));
                        } else {
                            System.out.print(number + " ");
                        }
                    }
                    System.out.println();
                }
                // Treffer zählen und speichern
                ++countMatches[matches];
                totalMatches += matches;

                System.out.println(colorize("==================================", "blau"));

                // Fortsetzen der Schleife oder Anzeigen der Treffer-Übersicht
                if (r < rounds) {
                    System.out.print(colorize("Drücke Enter, um fortzufahren", "gruen"));
                    scanner.nextLine();
                }
            }
        } else {
            // Schleife für alle Runden ohne Anzeige einzelner Runden
            for (int r = 1; r <= rounds; ++r) {
                // Array für die gezogenen Zahlen initialisieren
                int[] drawnNumbers = new int[6];
                for (int i = 0; i < 6; ++i) {
                    int number;
                    do {
                        number = (int) (Math.random() * 49) + 1;
                    } while (contains(drawnNumbers, number));

                    drawnNumbers[i] = number;
                }
                // Treffer zählen und speichern
                int matches = countMatches(tipps, drawnNumbers);
                ++countMatches[matches];
                totalMatches += matches;
            }

            System.out.println(colorize("Treffer-Übersicht (alle Runden)", "gold"));
        }

        int maxDigits = String.valueOf(rounds).length(); // Anzahl der Stellen der höchsten Zahl

        for (int i = 0; i <= 6; ++i) {
            String color = getColorForMatches(i);
            int count = countMatches[i];
            double probability = (count / (double) rounds) * 100;

            // Formatierung der Ausgabe
            String countString = String.valueOf(count);
            String probabilityString = String.format("%.6f", probability);

            int countSpaces = maxDigits - countString.length();
            String spaces = " ".repeat(countSpaces);

            System.out.println(
                    colorize(i + " Treffer: " + countString + spaces + " Runden | " + probabilityString + "%", color));
        }

        System.out.println(colorize(
                "Insgesamt " + totalMatches + " Treffer in " + rounds + " Runden. (Treffer ohne 0, zeigt wie oft, auch in einer Ziehung, zahlen getroffen wurden)",
                "gold"));

        System.out.print(colorize("Drücke Enter, um zum Hauptmenü zurückzukehren", "gruen"));
        scanner.nextLine();
    }

    private static void playAutomatic(int[] tipps, Scanner scanner) {
        clearConsole();
        printTitle("Lotto-Spiel", "gold");
        System.out.println(colorize("Automatisch ziehen lassen", "gruen"));

        int attempts = 0;
        boolean sechser = false;
        ArrayList<int[]> drawnNumbersList = new ArrayList<>();

        while (!sechser) {
            ++attempts;
            int[] drawnNumbers = generateRandomTipps();
            drawnNumbersList.add(drawnNumbers); // Gezogene Zahlen hinzufügen
            int matches = countMatches(tipps, drawnNumbers);

            if (matches == 6) {
                sechser = true;
            }
        }

        System.out.println(colorize("6 Treffer nach " + formatNumber(attempts) + " Ziehungen", "gold"));
        System.out.println(colorize("Preis für alle Tickets: " + formatNumber(attempts * 1.20) + " Euro", "gold"));
        System.out.println(colorize("===========", "gold"));
        System.out.println(colorize("Treffer-Übersicht: ", "gruen"));

        // Array zur Zählung von Treffern in verschiedenen Kategorien
        int[] countMatches = new int[7];
        for (int i = 0; i < attempts; ++i) {
            int matches = countMatches(tipps, drawnNumbersList.get(i));
            ++countMatches[matches];
        }

        // Treffer-Übersicht anzeigen
        int totalDraws = attempts;
        int maxDigits = String.valueOf(totalDraws).length();

        // Finde die maximale Anzahl von Dezimalpunkten in countString
        int maxDecimalPoints = 0;
        for (int i = 0; i <= 6; ++i) {
            int count = countMatches[i];
            String countString = formatNumber(count);
            int decimalPoints = countString.replaceAll("\\d", "").length();
            maxDecimalPoints = Math.max(maxDecimalPoints, decimalPoints);
        }

        for (int i = 0; i <= 6; ++i) {
            String color = getColorForMatches(i);
            int count = countMatches[i];
            double probability = (count / (double) totalDraws) * 100;

            // Formatierung der Ausgabe
            String countString = formatNumber(count);
            String probabilityString = String.format("%.6f", probability);

            int countDecimalPoints = countString.replaceAll("\\d", "").length();
            int countSpaces = maxDigits - countString.replaceAll("\\.", "").length();
            int decimalPointSpaces = maxDecimalPoints - countDecimalPoints;
            String spaces = " ".repeat(countSpaces + decimalPointSpaces);

            System.out.println(
                    colorize(i + " Richtige: " + countString + spaces + " | " + probabilityString + "%", color));
        }

        System.out.print(colorize("Drücke Enter, um fortzufahren", "blau"));
        scanner.nextLine();
        scanner.nextLine();
    }

    private static String formatNumber(double d) {
        DecimalFormat decimalFormat = new DecimalFormat("#,##0");
        return decimalFormat.format(d);
    }

    // Zeigt Treffer-Übersicht in verschiedenen Farben an
    private static String getColorForMatches(int matches) {
        switch (matches) {
            case 0:
                return "rot";
            case 1:
                return "orangerot";
            case 2:
                return "gelborange";
            case 3:
                return "gelb";
            case 4:
                return "hellgruen";
            case 5:
                return "gruen";
            default:
                return "gold";
        }
    }

    private static boolean contains(int[] array, int number) {
        // Überprüft, ob das gegebene Array den angegebenen Wert enthält
        for (int element : array) {
            if (element == number) {
                return true;
            }
        }
        return false;
    }

    private static int countMatches(int[] tipps, int[] drawnNumbers) {
        int count = 0;

        for (int tipp : tipps) {
            if (contains(drawnNumbers, tipp)) {
                count++;
            }
        }
        return count;
    }

    private static void clearConsole() {
        // Löscht Konsolenausgabe (quasi ein "cls") (funktioniert nicht immer)
        System.out.print("\033[H\033[2J"); // ANSI escape code https://stackoverflow.com/questions/55672661/what-this-character-sequence-033h-033j-does-in-c

        System.out.flush();
    }

    private static String colorize(String text, String color) {
        String ansiReset = "\u001B[0m";
        String ansiColor = "";

        switch (color) {
            case "rot":
                ansiColor = "\u001B[31m";
                break;
            case "blau":
                ansiColor = "\u001B[34m";
                break;
            case "gold":
                ansiColor = "\u001B[33m";
                break;
            case "orangerot":
                ansiColor = "\u001B[38;2;255;69;0m";
                break;
            case "gelborange":
                ansiColor = "\u001B[38;2;255;165;0m";
                break;
            case "gelb":
                ansiColor = "\u001B[38;2;255;255;0m";
                break;
            case "hellgruen":
                ansiColor = "\u001B[38;2;0;255;0m";
                break;
            case "gruen":
                ansiColor = "\u001B[38;2;0;128;0m";
                break;
        }

        String formattedText = text
                .replaceAll("ä", "\u00E4")
                .replaceAll("ü", "\u00FC")
                .replaceAll("ö", "\u00F6")
                .replaceAll("Ä", "\u00C4")
                .replaceAll("Ü", "\u00DC")
                .replaceAll("Ö", "\u00D6")
                .replaceAll("ß", "\u00DF");

        if (!ansiColor.isEmpty()) {
            return ansiColor + formattedText + ansiReset;
        } else {
            return formattedText;
        }
    }

    private static void printTitle(String title, String color) {
        // Gibt den Titel mit der angegebenen Farbe aus
        System.out.println(colorize(title, color));
        System.out.println(colorize("===========", color));
    }

    private static int menuSelector(Scanner scanner, String[] options, String menuName) {
        int selection = -1;
        boolean validInput = false;

        while (!validInput) {
            clearConsole();
            printTitle("Lotto-Spiel", "gold");
            System.out.println(colorize(menuName, "gruen"));
            for (int i = 0; i < options.length; i++) {
                System.out.println(colorize((i + 1) + ": " + options[i], ""));
            }
            System.out.print(colorize("Auswahl: ", "blau"));

            if (scanner.hasNextInt()) {
                selection = scanner.nextInt();
                if (selection >= 1 && selection <= options.length) {
                    validInput = true;
                } else {
                    System.out.println(colorize("Ungültige Auswahl", "rot"));
                    scanner.nextLine();
                }
            } else {
                System.out.println(colorize("Ungültige Auswahl", "rot"));
                scanner.nextLine();
            }
        }
        return selection - 1;
    }
}