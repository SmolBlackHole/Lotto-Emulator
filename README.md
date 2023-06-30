# Lotto Emulator

lotto.java ist ein Java-Programm, das ein Lotto-Spiel in der Form einer Konsolen-Applikation implementiert. Es wird dem Benutzer ermöglicht Lotto-Tipps einzugeben, automatische Ziehungen (bis 6 Richtige) oder manuelle Ziehungen (Rundenbasiert) zu spielen.

Das Programm soll zeigen, wie unwahrscheinlich es ist, bei solch einem Glücksspiel, den Jackpot zu erreichen.


## Anleitung

1. lotto.java ausführen
2. Beim Start wird das Hauptmenü angezeigt
3. Auswahl zwischen den Optionen
    3.1 Tipps Eingeben: Hier öffnet sich ein Untermenü mit weiteren Auswahlmöglichkeiten.<br/><br/>
        3.1.1. Tipps eingeben: Manuelle Eingabe von Tipps<br/>
        <br/>
        3.1.2. Tipps ändern: Bearbeitung der Tipps<br/>
        <br/>
        3.1.3. Zufällige Tipps wählen: Es werden zufällig Tipps generiert. Diese können nachträglich geändert werden<br/>
        <br/>
        3.1.4. Tipps anzeigen: Zeigt die aktuellen Tipps an<br/>
        <br/>
        3.1.5. Zurück zum Hauptmenü: Kehr zum Hauptmenü zurück<br/>
        <br/>
    3.2 Automatische Ziehung (bis 6 Richtige):** Es wird automatisch so lange gezogen, bis 6 Richtige Treffer erreicht wurden. Die Treffer-Übersicht berechnet jeden Treffer für jede Kategorie (1-6 Richtige) *1. 

    3.3 Manuelle Ziehung: Hier hat man die Möglichkeit sehen zu können, auf welche Zahlen man setzt, welche gezogen wurden und wie viele Treffer man erreicht hat. Aus Gründen der Übersicht (Konsolenausgabe) wurden die einzelnen 'Runden' auf 10 begrenzt *2.

    3.4 Beenden: Das Programm wird beendet.

4. Beim Abschließen bestimmter Optionen wird man automatisch zum Hauptmenü weitergeleitet.
<br/><br/>
- *1: Bei der automatischen Ziehung wird so lange gezogen, bis es eine 'Runde' gefunden hat, in der alle 6 gezogenen Zahlen mit den eigenen Tipps übereinstimmt. Ist dieser Fall gegeben, wird nachträglich aus einem Array (hier werden alle Runden in einem Array gespeichert) die Treffer-Übersicht generiert. Die Treffer-Übersicht zeigt alle Richtigen aus allen Ziehungen je Kategorie.
- *2: Bei mehr als 10 Runden wird nur die Treffer-Übersicht ausgegeben und nicht mehr die einzelnen Runden.


## Hinweise
* Die Zahlen für die Lotto-Tipps müssen zwischen 1 und 49 liegen.
* Das Programm enthält Farbcodes für die Konsolenausgabe, um den Text farblich hervorzuheben. Diese Farbcodes funktionieren möglicherweise nicht in allen Konsolenumgebungen.
* Eingaben sind mit der Enter-Taste zu bestätigen.
* Beachte, dass das Programm eine Funktion enthält, um die gezogenen Zahlen zufällig (zwischen 1 und 49) zu erzeugen.

### To-Do
* Möglichkeit auch mehr als 10 einzelne Runden bei der manuellen Ziehung auszugeben.

#
### Code: Funktionsweise
Das Programm besteht aus mehreren Methoden, die verschiedene Funktionen ausführen:

* main: Die Hauptmethode, die das Hauptmenü anzeigt und je nach Benutzerwahl verschiedene Aktionen ausführt.'
* enterTippsMenu: 'Methode zum Eingeben von Lotto-Tipps, entweder manuell, durch Ändern vorhandener Tipps oder durch Generieren zufälliger Tipps.
* enterNewTipps: Methode zum Eingeben von neuen Lotto-Tipps.
* modifyTipps: Methode zum Ändern von vorhandenen Lotto-Tipps.
* generateRandomTipps: Methode zum Generieren zufälliger Lotto-Tipps.
* showTipps: Methode zum Anzeigen der Lotto-Tipps.
* playManual: Methode zum Spielen von manuellen Ziehungen mit einer bestimmten Anzahl von Runden.
* countMatches: Methode zum Zählen der Treffer zwischen den Lotto-Tipps und den gezogenen Zahlen.

## Abhängigkeiten
Das Programm verwendet die Standardbibliothek von Java, es sind keine externen Abhängigkeiten erforderlich.


## Contributing

Pull Requests sind willkommen. Für größere Änderungen bitte ein Issue öffnen, um die gewünschten Änderungen zu diskutieren.
Die Tests sollten entsprechend aktualisiert werden.

## License

[MIT](https://choosealicense.com/licenses/mit/)


***Viel Spaß beim Spielen!***
