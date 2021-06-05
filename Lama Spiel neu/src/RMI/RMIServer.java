package RMI;
import fachlicheExceptions.*;
/*
* @author Nick Jochum
* Zentraler Server des Spiels. Alle aktiven Clients haben eine Referenz auf RMIServer-Instanz, über die sie mit anderen
* Clients kommunizieren können
*/
public class RMIServer implements RMIServerIF{

    @Override
    public void registriereClient(RMIClientIF client) {
        /*
        * Wird von RMIClientIF-Instanz beim Verbindungsaufbau aufgerufen. Server speichert RMIClientIF-Referenz um Nach-
        * richten an Client senden zu können
        * @params RMIServerIF != null also ein Objekt der Klasse RMIClient, BotSchwer oder BotEinfach
        */
    }

    @Override
    public boolean benutzerdatenPruefen(String benutzername, String passwort) {
        /*
        * Überprüft ob ein Benutzer mit Benutzername benutzername und Passwort passwort registgriert ist
        * @param benutzername String != null
        * @param passwort String != null
        * @return true, wenn Benutzer mit Benutzername benutzername und Passwort passwort registgriert ist, sonst false
        */
        return false;
    }

    @Override
    public void benutzerRegistrieren(String benutzername, String passwort) throws benutzerNameVergebenException {
        /*
        * Registriert einen Benutzer mit dem übergebenen Benutzernamen und Passwort
        * @param benutzername String != null, der nicht mit dem Präfix "Bot" anfängt
        * @param passwort String != null
        * @throws benutzerNameVergebenException wenn ein anderer Benutzer sich bereits mit dem Benutzernamen
        *   benutzername registriert hat oder wenn benutzername mit dem Präfix "Bot" beginnt
        */

    }

    @Override
    public void benutzerLoeschen(String benutzername) throws ungueltigerBenutzernameException {
        /*
        * Löscht alle Daten des Benutzers mit dem übergebenen Benutzernamen, also sowohl aus der intern verwalteten
        * Benutzerdaten- als auch aus der intern verwalteten Bestenliste-Istanz
        * @param benutzername String != null
        * @throws ungueltigerBenutzernameException wenn zuvor kein Benutzer mit dem Benutzernamen benutzername
        *   registriert wurde
        */

    }

    @Override
    public void sendeChatnachricht(String benutzername, int spielraumID, String nachricht)
            throws ungueltigeSpielraumIDException, ungueltigerBenutzernameException {
        /*
        * Überträgt die übergebene Nachricht an alle gespeicherten RMIClientsIF-Instanzen, die sich im übergebenen
        * Spielraum befinden
        * @param benutzername String != null
        * @param spielraumID Integer != null. spielraumID = 0 adressiert die Lobby
        * @param nachricht String != null
        * @throws ungueltigerSpielraumIDException wenn kein Spielraum mit der ID spielraumID existiert
        * @throws ungueltigerBenutzernameException wenn sich im Spielraum mit der ID spielraumID kein Spieler mit dem
        *   Benutzernamen benutzernamen befindet.
        */

    }

    @Override
    public void spielraumBeitreten(String benutzername, int spielraumID)
            throws spielraumVollException, ungueltigerBenutzernameException, ungueltigeSpielraumIDException {
        /*
        * fügt den Spieler mit dem übegebenen Benutzernamen dem Spielraum mit der ID spielraumID hinzu
        * @param benutzername String != null
        * @param spielraumID Integer != null. spielraumID = 0 adressiert die Lobby
        * @throws spielraumVollException wenn sich im Spielraum mit der ID spielraumID bereits sechs RMIClientIF-
        *   Instanzen befinden, außer spielraumID = 0
        * @throws ungueltigerBenutzernameException wenn keine RMIClientIF-Instanz mit dem Benutzernamen benutzernamen
        *   registriert zuvor registriert wurde
        * @throws ungueltigerSpielraumIDException wenn kein Spielraum mit der ID spielraumID existiert
        */


    }

    @Override
    public void spielraumVerlassen(String benutzername, int spielraumID)
            throws ungueltigeSpielraumIDException, ungueltigerBenutzernameException {
        /*
        * entfernt den Spieler mit dem übegebenen Benutzernamen aus dem Spielraum mit der ID spielraumID
        * @param benutzername String != null
        * @param spielraumID Integer != null. spielraumID = 0 adressiert die Lobby
        * @throws ungueltigerBenutzernameException wenn sich im Spielraum mit der ID spielraumID kein Spieler mit dem
        *   Benutzernamen benutzernamen befindet.
        */

    }

    @Override
    public void botHinzufuegen(boolean easybot, int spielraumID)
            throws ungueltigeSpielraumIDException, spielraumVollException {
        /*
        * ertstellt eine neue Bot-Instanz und fügt diese dem Spielraum mit der übergebenen ID hinzu
        * @param easybot Boolean != null wenn true wird eine BotEinfach-Instanz hinzugefügt, wenn false wird eine
        *   BotSchwer-Instanz hinzugefügt
        * @param spielraumID Integer != null
        * @throws ungueltigeSpielraumIDException wenn kein Spielraum mit der ID SpielraumID existiert, oder wenn
        *   spielraumID = 0
        * @throws spielraumVollException wenn sich im Spielraum mit der ID spielraumID bereits sechs RMIClientIF-
        *   Instanzen befinden, außer spielraumID = 0
        */

    }

    @Override
    public void botEntfernen(String botname, int spielraumID)
            throws ungueltigeSpielraumIDException, ungueltigerBenutzernameException {
        /*
        * entfernt einen Bot mit dem übergebenen Benutzernamen botname aus dem Spielraum mit der übergebenen ID
        * @param botname String != null, beginnend mit dem Präfix "Bot"
        * @param spielraumID Int != null
        * @throws ungueltigeSpielraumIDException wenn kein Spielraum mit der ID SpielraumID existiert, oder wenn
        *   spielraumID = 0
        * @throws spielraumVollException wenn sich im Spielraum mit der ID spielraumID bereits sechs RMIClientIF-
        *   Instanzen befinden, außer spielraumID = 0
        * @throws ungueltigerBenutzernameException wenn sich im Spielraum mit der ID spielraumID kein Spieler mit dem
        *   Benutzernamen benutzernamen befindet.
        */

    }

    @Override
    public void spielStarten(int spielraumID) throws ungueltigeSpielraumIDException, spielLaeuftBereitsException {
        /*
        * startet das Spiel im Spielraum mit der übergebenen ID
        * @param spielraumID Int != null
        * @throws ungueltigeSpielraumIDException wenn kein Spielraum mit der ID SpielraumID existiert, oder wenn
        *   spielraumID = 0
        * @throws spielLaeuftBereitsException wenn Spiel zuvor gestartet und noch nicht beendet wurde
        */

    }

    @Override
    public void chipsTauschen(boolean einsergegenzehner, int spielraumID, String benutzername)
            throws ungueltigerSpielzugException, ungueltigeSpielraumIDException, ungueltigerBenutzernameException {
        /*
        * Tauscht entweder 10 weiße Chips gegen ein schwarzen Chip oder umgekehrt, auf der Chipstapel-Instanz der
        *   Spielers mit dem übergebenen Benutzernamen im Spielraum mit der übergebenen ID
        * @param einsergegebzehner Boolean != null, wenn true werden 10 weiße gegen ein schwarzen Chip getauscht,
        *   wenn false, wird ein schwarzer gegen 10 weiße Chips getauscht
        * @param spielraumID Int != null
        * @param benutzername String != null
        * @throws ungueltigerSpielzugexception wenn der Spieler mit Benutzernamen benutzername nicht genug weiße bzw.
        *   schwarze Chips auf seinem Chipstapel hat
        * @throws ungueltigeSpielraumIDException wenn kein Spielraum mit der ID SpielraumID existiert, oder wenn
        *   spielraumID = 0
        * @throws ungueltigerBenutzernameException wenn sich im Spielraum mit der ID spielraumID kein Spieler mit dem
        *   Benutzernamen benutzernamen befindet.
        */

    }

    @Override
    public void chipAbgeben(boolean einserchip, String benutzername, int spielraumID)
            throws ungueltigerSpielzugException, ungueltigeSpielraumIDException, ungueltigerBenutzernameException {
        /*
         * entfernt eine Chip vom Spieler mit dem übergebenen Benutzernamen im Spielraum mit der übergebenen ID
         * @param einserchip Boolean != null wenn true wird ein weißer Chip, wenn false ein schwarzer Chip entfernt
         * @param benutzername String != null
         * @throws ungueltigerSpielzugexception wenn der Spieler mit Benutzernamen benutzername nicht genug weiße bzw.
         *   schwarze Chips auf seinem Chipstapel hat
         * @throws ungueltigeSpielraumIDException wenn kein Spielraum mit der ID SpielraumID existiert, oder wenn
         *   spielraumID = 0
         * @throws ungueltigerBenutzernameException wenn sich im Spielraum mit der ID spielraumID kein Spieler mit dem
         *   Benutzernamen benutzernamen befindet.
         */

    }

    @Override
    public void karteAblegen(int karte, String benutzername, int spielraumID)
            throws ungueltigerSpielzugException, ungueltigeSpielraumIDException, ungueltigerBenutzernameException {
        /*
        * entfernt eine Karte mit dem übergebenen Wert aus der Hand des Spielers mit Benutzernamen benutzername im
        * Spielraum mit der übergebenen ID
        * @param karte Integer !=0 zwischen 0 und 6, wobei 0 eine Lama-Karte repräsentiert
        * @param benutzername String != null
        * @param spielraumID Integer != null
        * @throws ungueltigerSpielzugexception wenn der Spieler mit Benutzernamen benutzername keine Karte mit dem Wert
        *   karte auf der Hand hat, oder der Wert karte nach dem Spielregeln nicht auf zum Wert der obersten Karte auf
        *   den Ablagestapel passt
        * @throws ungueltigeSpielraumIDException wenn kein Spielraum mit der ID SpielraumID existiert, oder wenn
        *   spielraumID = 0
        * @throws ungueltigerBenutzernameException wenn sich im Spielraum mit der ID spielraumID kein Spieler mit dem
        *   Benutzernamen benutzernamen befindet.
        */

    }

    @Override
    public int karteZiehen(String benutzername, int spielraumID)
            throws ungueltigerSpielzugException, ungueltigeSpielraumIDException, ungueltigerBenutzernameException {
        /*
        * entfernt die oberste Karte vom Nachziehstapel im Spielraum mit der übergebenen ID und fügt diese Karte den
        *   Handkarten des Spielers mit dem übergebenen Benutzernamen hinzu
        * @param benutzername String != null
        * @param spielraumID Integer != null
        * @throws ungueltigerSpielzugexception wenn der Nachziehstapel des Spielraums mit der ID spielraumID
        *   leer ist
        * @throws ungueltigeSpielraumIDException wenn kein Spielraum mit der ID SpielraumID existiert, oder wenn
        *   spielraumID = 0
        * @throws ungueltigerBenutzernameException wenn sich im Spielraum mit der ID spielraumID kein Spieler mit dem
        *   Benutzernamen benutzernamen befindet.
        * @return Integer (Kartenwert) zwischen 0 und 6, wobei 0 ein Lama repräsentiert
        */
        return 0;
    }

    @Override
    public void aussteigen(String benutzername, int spielraumID)
            throws ungueltigeSpielraumIDException, ungueltigerBenutzernameException {

        /* Markiert den Spieler mit dem übergebenen im Spielraum mit der ID spielraumID als ausgestiegen
         * @param benutzername String != null
         * @param spielraumID Integer != null
         * @throws ungueltigeSpielraumIDException wenn kein Spielraum mit der ID SpielraumID existiert, oder wenn
         *   spielraumID = 0
         * @throws ungueltigerBenutzernameException wenn sich im Spielraum mit der ID spielraumID kein Spieler mit dem
         *   Benutzernamen benutzernamen befindet.
         */


    }
}
