package Highscore;
import java.util.HashMap;

/**
 * Die Klasse verwaltet die Bestenliste mit der Anzahl der gewonnenen Spiele pro Spieler
 *
 * @author Vanessa Stein
 */
public class Bestenliste {

    HashMap bestenliste;


    /**
     * Die Methode fügt einen neuen Eintrag zur Bestenliste hinzu oder aktualisiert einen bestehenden Eintrag
     * @param benutzername Benutzername des Spielers, dessen Highscore eingetragen werden soll
     * @param isBot() überprüft, ob Spieler kein Bot ist
     */
    public void eintragHinzufuegen(String benutzername, boolean isBot){

    }


    /**
     * Die Methode dient zum Abrufen des Highscores eines Spielers
     * @param benutzername Benutzername des Spielers, dessen Score abgerufen werden soll
     * @return gibt die Anzahl der gewonnenen Spiele als Integer zurück
     */
    public Integer getScore(String benutzername){
        return null;
    }


    /**
     * Die Methode dient zum Abrufen der zehn Spielernamen mit dem besten Score
     * @return gibt eine HashMap mit den zehn besten Eintraegen zurueck
     */
    public HashMap getTopZehn(){
        return null;
    }


    /**
     * Die Methode entfernt einen Eintrag aus der Bestenliste, z.B. wenn das zugehoerige Konto geloescht wird
     * @param benutzername Benutzername des Spielers, dessen Eintrag entfernt werden soll
     */
    public void eintragLoeschen(String benutzername){

    }
}
