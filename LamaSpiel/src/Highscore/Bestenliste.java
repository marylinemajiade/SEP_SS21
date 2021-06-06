package Highscore;


import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * Die Klasse verwaltet die Bestenliste mit der Anzahl der gewonnenen Spiele pro Spieler
 *
 * @author Vanessa Stein
 */
public class Bestenliste {

    public static HashMap<String,Integer> bestenliste = new HashMap<String,Integer>();


    /**
     * Die Methode fügt einen neuen Eintrag zur Bestenliste hinzu oder aktualisiert einen bestehenden Eintrag
     * @param benutzername Benutzername des Spielers, dessen Highscore eingetragen werden soll
     * @param isBot() überprüft, ob Spieler kein Bot ist
     */
    public static void eintragHinzufuegen(String benutzername, boolean isBot){
        if (!isBot){
            if(bestenliste.containsKey(benutzername)){
                bestenliste.replace(benutzername, bestenliste.get(benutzername)+1);
            } else {
                bestenliste.put(benutzername,1);
            }
        }

    }


    /**
     * Die Methode dient zum Abrufen des Highscores eines Spielers
     * @param benutzername Benutzername des Spielers, dessen Score abgerufen werden soll
     * @return gibt die Anzahl der gewonnenen Spiele als Integer zurück
     * @throws NoSuchElementException Wirft Exeption, falls Eintrag nicht existiert
     */
    public Integer getScore(String benutzername) throws NoSuchElementException {
        try {
            return (bestenliste.get(benutzername));
        } catch (NoSuchElementException e){

        }
        return null;
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
     * @throws NoSuchElementException Wirft Exeption, falls Eintrag nicht vorhanden ist
     */
    public static void eintragLoeschen(String benutzername) throws NoSuchElementException{
        try {
            bestenliste.remove(benutzername);
        } catch (NoSuchElementException e){

        }

    }

}

