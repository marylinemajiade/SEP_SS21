package Highscore;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * Die Klasse verwaltet die Bestenliste mit der Anzahl der gewonnenen Spiele pro Spieler
 *
 * @author Vanessa Stein
 */

public class Bestenliste {

    private HashMap<String,Integer> bestenliste = new HashMap<String,Integer>();

    /**
     * Die Methode fügt einen neuen Eintrag zur Bestenliste hinzu oder aktualisiert den Score eines bestehenden Eintrags
     * @param benutzername Benutzername des Spielers, dessen Highscore eingetragen werden soll
     * @param isBot() überprüft, ob Spieler kein Bot ist
     */
    public void eintragHinzufuegen(String benutzername, boolean isBot) {
        if (!isBot) {
            if (bestenliste.containsKey(benutzername)) {
                bestenliste.replace(benutzername, bestenliste.get(benutzername) + 1);
            } else {
                bestenliste.put(benutzername, 1);
            }
        }
    }


    /**
     * Die Methode dient zum Abrufen des Highscores eines Spielers
     * @param benutzername Benutzername des Spielers, dessen Score abgerufen werden soll
     * @return gibt die Anzahl der gewonnenen Spiele als Integer zurück
     * @throws NullPointerException Wirft Exeption, falls Eintrag nicht existiert
     */
    public Integer getScore(String benutzername) throws NullPointerException {
        try {
            return (bestenliste.get(benutzername));
        } catch (NullPointerException e){
            return null;
        }
    }


    /**
     * Die Methode dient zum Abrufen der zehn Spielernamen mit dem besten Score
     * @return gibt eine ArrayList mit den zehn besten Eintraegen zurueck
     */
    public ArrayList getTopZehn(){
        Stream<Map.Entry<String,Integer>> sorted =
                bestenliste.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue()));

        ArrayList topten = (ArrayList) sorted.limit(10).collect(Collectors.toList());

        return topten;
    }


    /**
     * Die Methode entfernt einen Eintrag aus der Bestenliste, z.B. wenn das zugehoerige Konto geloescht wird
     * @param benutzername Benutzername des Spielers, dessen Eintrag entfernt werden soll
     * @throws NullPointerException Wirft Exeption, falls Eintrag nicht vorhanden ist
     */
    public void eintragLoeschen(String benutzername) throws NullPointerException{
        try {
            bestenliste.remove(benutzername);
        } catch (NullPointerException e){

        }
    }

    /**
     * Getter und Setter der Bestenliste
     * @author Hamza Bariane
     */
    public HashMap<String, Integer> getBestenliste() {
        return bestenliste;
    }

    public void setBestenliste(HashMap<String, Integer> bestenliste) {
        this.bestenliste = bestenliste;
    }

}

