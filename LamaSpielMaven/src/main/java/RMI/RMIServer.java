package RMI;

import Bot.BotEinfach;
import Bot.BotSchwer;
import Highscore.Bestenliste;
import Spiel.Spielrunde;
import SpielLobby.Lobby;
import fachlicheExceptions.*;
import Konto.*;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
* @author Nick Jochum
* Zentraler Server des Spiels. Alle aktiven Clients haben eine Referenz auf RMI.RMIServer-Instanz, über die sie mit anderen
* Clients kommunizieren können
*/
public class RMIServer implements RMIServerIF, Serializable {

    //Attribute
    private Bestenliste bestenliste;
    private Lobby lobby;
    private BenutzerVerwalten benutzerdaten;
    private HashMap<Integer,Spielrunde> spielrunden = new HashMap<>();
    private ArrayList<RMIClientIF> clientliste = new ArrayList<>();
    private int naechsteSpielraumID = 1;

    public RMIServer(Bestenliste bestenliste, Lobby lobby, BenutzerVerwalten benutzerverwaltung) throws RemoteException {
        this.bestenliste = bestenliste;
        this.lobby = lobby;
        this.benutzerdaten = benutzerverwaltung;
    }

    /**
     * Vorraussetzung: Benutzer wurde zuvor registriert.
     * Wird von RMIClientIF-Instanz beim Verbindungsaufbau aufgerufen. Server speichert RMIClientIF-Referenz um Nach-
     * richten an Client senden zu können
     * @param client RMIServerIF != null also ein Objekt der Klasse RMIClient, BotSchwer oder BotEinfach
     */
    @Override
    public synchronized void registriereClient(RMIClientIF client) throws RemoteException, ungueltigerBenutzernameException {
        clientliste.add(client);
        System.out.println("Client registriert");
        lobby.spielraumBeitreten(client.getBenutzername(),0);
        for(RMIClientIF c:clientliste){
            c.aktualisiereSpielraeume(lobby);
        }
    }

    /**
     * Überprüft ob ein Benutzer mit Benutzername benutzername und Passwort passwort registgriert ist
     * @param benutzername String != null
     * @param passwort String != null
     * @return true, wenn Benutzer mit Benutzername benutzername und Passwort passwort registgriert ist, sonst false
     */
    @Override
    public synchronized boolean benutzerdatenPruefen(String benutzername, String passwort) {
        return benutzerdaten.benutzerdatenPruefen(benutzername,passwort);
    }

    /**
     * Registriert einen Benutzer mit dem übergebenen Benutzernamen und Passwort
     * @param benutzername String != null, der nicht mit dem Präfix "Bot" anfängt
     * @param passwort String != null
     * @throws benutzerNameVergebenException wenn ein anderer Benutzer sich bereits mit dem Benutzernamen
     *   benutzername registriert hat oder wenn benutzername mit dem Präfix "Bot" beginnt
     */
    @Override
    public synchronized void benutzerRegistrieren(String benutzername, String passwort) throws benutzerNameVergebenException {
        benutzerdaten.benutzerRegistrieren(benutzername,passwort);
    }

    /**
     * Löscht alle Daten des Benutzers mit dem übergebenen Benutzernamen, also sowohl aus der intern verwalteten
     * Benutzerdaten- als auch aus der intern verwalteten Bestenliste-Istanz
     * @param benutzername String != null
     * @throws ungueltigerBenutzernameException wenn zuvor kein Benutzer mit dem Benutzernamen benutzername
     *   registriert wurde
     */
    @Override
    public synchronized void benutzerLoeschen(String benutzername) throws ungueltigerBenutzernameException, RemoteException {

        benutzerdaten.benutzerLoeschen(benutzername);
        bestenliste.eintragLoeschen(benutzername);
        ArrayList<RMIClientIF> neueClientListe = new ArrayList<>();
        for(RMIClientIF client:clientliste){
            if (!client.getBenutzername().equals(benutzername)) neueClientListe.add(client);
        }
        this.clientliste = neueClientListe;
        try {
            for (int id : lobby.getSpielraum_Ids()) spielrunden.get(id).spielraumVerlassen(benutzername);
        }catch (Exception ignored){}
    }

    /**
     * Überträgt die übergebene Nachricht an alle gespeicherten RMIClientsIF-Instanzen, die sich im übergebenen
     * Spielraum befinden
     * @param benutzername String != null
     * @param spielraumID Integer != null. spielraumID = 0 adressiert die Lobby
     * @param nachricht String != null
     * @throws ungueltigeSpielraumIDException wenn kein Spielraum mit der ID spielraumID existiert
     * @throws ungueltigerBenutzernameException wenn sich im Spielraum mit der ID spielraumID kein Spieler mit dem
     *   Benutzernamen benutzernamen befindet.
     */
    @Override
    public synchronized void sendeChatnachricht(String benutzername, int spielraumID, String nachricht)
            throws ungueltigeSpielraumIDException, ungueltigerBenutzernameException, RemoteException {
        if(!lobby.getSpieler(spielraumID).contains(benutzername)) throw new ungueltigerBenutzernameException("Kein " +
                "Spieler mit übergebenen Benutzernamen im Spielraum mit der übergebenen ID");
            List<String> benutzernamenInSpielrunde = lobby.getSpieler(spielraumID);
            for (RMIClientIF client: clientliste){
                if(benutzernamenInSpielrunde.contains(client.getBenutzername())) {
                    try {
                        client.uebertrageChatnachricht(benutzername, nachricht);
                    } catch (ZustellungNachrichtNichtMoeglichException ignored) {}
                }
            }
    }

    /**
     * Erstellt einen neuen Spielraum. Der Spieler mit dem übergebenen Benutzername tritt dem erstellten Spielraum bei.
     * @param benutzername String != null
     * @throws RemoteException Server-Objekt nicht erreicht werden kann
     * @throws ungueltigerBenutzernameException Wenn kein Benutzer mit dem angegebenen Benutzername sich in der Lobby
     * befindet
     */
    @Override
    public synchronized void spielraumErstellen(String benutzername) throws RemoteException, ungueltigerBenutzernameException {
        if(!lobby.getSpieler(0).contains(benutzername)) {
            throw new ungueltigerBenutzernameException("Kein Spieler mit dem übegebenen Benutzernamen in der Lobby");
        }
        spielrunden.put(naechsteSpielraumID,new Spielrunde(naechsteSpielraumID,new Lobby()));
        lobby.spielraumHinzufuegen(naechsteSpielraumID);
        naechsteSpielraumID++;
        lobby.spielraumVerlassen(benutzername,0);
        lobby.spielraumBeitreten(benutzername,1);
        for(RMIClientIF client:clientliste){
            try{
                client.aktualisiereSpielraeume(lobby);
            }catch(Exception ignored){}
        }
        for (RMIClientIF client :clientliste){
            if (client.getBenutzername().equals(benutzername)) {
                client.aktualisiereSpielstatus(spielrunden.get(naechsteSpielraumID-1));
            }
        }
    }

    /**
     * fügt den Spieler mit dem übegebenen Benutzernamen dem Spielraum mit der ID spielraumID hinzu und entfernt ihn
     * aus der Lobby
     * @param benutzername String != null
     * @param spielraumID Integer != null. spielraumID = 0 adressiert die Lobby
     * @throws spielraumVollException wenn sich im Spielraum mit der ID spielraumID bereits sechs RMIClientIF-
     *   Instanzen befinden, außer spielraumID = 0
     * @throws ungueltigerBenutzernameException wenn keine RMIClientIF-Instanz mit dem Benutzernamen benutzernamen
     *   registriert zuvor registriert wurde
     * @throws ungueltigeSpielraumIDException wenn kein Spielraum mit der ID spielraumID existiert
     */
    @Override
    public synchronized void spielraumBeitreten(String benutzername, int spielraumID)
            throws spielraumVollException, ungueltigerBenutzernameException, ungueltigeSpielraumIDException {
        if (!lobby.getSpieler(0).contains(benutzername)) {
            throw new ungueltigerBenutzernameException("Kein Spieler mit dem übegebenen Benutzernamen in der Lobby");
        }
        if(!lobby.getSpielraum_Ids().contains(spielraumID)) {
            throw new ungueltigeSpielraumIDException("Übergebener Spielraum existiert nicht");
        }
        if (lobby.getSpieler(spielraumID).size() < 6) {
            lobby.spielraumVerlassen(benutzername,0);
            lobby.spielraumBeitreten(benutzername, spielraumID);
            for(RMIClientIF client:clientliste){
                try{
                    client.aktualisiereSpielraeume(lobby);
                    if (lobby.getSpieler(spielraumID).contains(client.getBenutzername())){
                        client.aktualisiereSpielstatus(spielrunden.get(spielraumID));
                    }
                }catch(Exception ignored){}
            }
        }else throw new spielraumVollException("Es befinden sich bereits 6 Spieler im gewählten Spielraum");

    }

    /**
     * entfernt den Spieler mit dem übegebenen Benutzernamen aus dem Spielraum mit der ID spielraumID
     * @param benutzername String != null
     * @param spielraumID Integer != null. spielraumID = 0 adressiert die Lobby
     * @throws ungueltigerBenutzernameException wenn sich im Spielraum mit der ID spielraumID kein Spieler mit dem
     *   Benutzernamen benutzernamen befindet.
     */
    @Override
    public synchronized void spielraumVerlassen(String benutzername, int spielraumID)
            throws ungueltigeSpielraumIDException, ungueltigerBenutzernameException {
        if(!lobby.getSpielraum_Ids().contains(spielraumID)) {
            throw new ungueltigeSpielraumIDException("Übergebener Spielraum existiert nicht");
        }
        if (!lobby.getSpieler(spielraumID).contains(benutzername)) {
            throw new ungueltigerBenutzernameException("Kein Spieler mit dem übegebenen Benutzernamen in der Lobby");
        }
        lobby.spielraumVerlassen(benutzername, spielraumID);
        lobby.spielraumBeitreten(benutzername,0);
        for(RMIClientIF client:clientliste){
            try{
                client.aktualisiereSpielraeume(lobby);
                if (lobby.getSpieler(spielraumID).contains(client.getBenutzername())) {
                    client.aktualisiereSpielstatus(spielrunden.get(spielraumID));
                }
            }catch(Exception ignored){}
        }
    }

    /**
     * ertstellt eine neue Bot-Instanz und fügt diese dem Spielraum mit der übergebenen ID hinzu
     * @param easybot Boolean != null wenn true wird eine BotEinfach-Instanz hinzugefügt, wenn false wird eine
     *   BotSchwer-Instanz hinzugefügt
     * @param spielraumID Integer != null
     * @throws ungueltigeSpielraumIDException wenn kein Spielraum mit der ID SpielraumID existiert, oder wenn
     *   spielraumID = 0
     * @throws spielraumVollException wenn sich im Spielraum mit der ID spielraumID bereits sechs RMIClientIF-
     *   Instanzen befinden, außer spielraumID = 0
     */
    @Override
    public synchronized void botHinzufuegen(boolean easybot, int spielraumID)
            throws ungueltigeSpielraumIDException, spielraumVollException, RemoteException, ungueltigerBenutzernameException {
        if(!lobby.getSpielraum_Ids().contains(spielraumID)) {
            throw new ungueltigeSpielraumIDException("Übergebener Spielraum existiert nicht");
        }
        if (lobby.getSpieler(spielraumID).size() > 5) throw new spielraumVollException("Spielraum bereits voll");
        RMIClientIF bot;
        if(easybot) bot = new BotEinfach(this);
        else bot = new BotSchwer(this);
        clientliste.add(bot);
        lobby.spielraumBeitreten(bot.getBenutzername(),spielraumID);
        for(RMIClientIF client:clientliste) {
            try {
                client.aktualisiereSpielraeume(lobby);
                if (lobby.getSpieler(spielraumID).contains(client.getBenutzername())) {
                    client.aktualisiereSpielstatus(spielrunden.get(spielraumID));
                }
            } catch (Exception ignored) {}
        }
    }

    /**
     * entfernt einen Bot mit dem übergebenen Benutzernamen botname aus dem Spielraum mit der übergebenen ID
     * @param botname String != null, beginnend mit dem Präfix "Bot"
     * @param spielraumID Int != null
     * @throws ungueltigeSpielraumIDException wenn kein Spielraum mit der ID SpielraumID existiert, oder wenn
     *   spielraumID = 0
     * @throws ungueltigerBenutzernameException wenn sich im Spielraum mit der ID spielraumID kein Spieler mit dem
     *   Benutzernamen benutzernamen befindet.
     */
    @Override
    public synchronized void botEntfernen(String botname, int spielraumID)
            throws ungueltigeSpielraumIDException, ungueltigerBenutzernameException, RemoteException {
        if(!lobby.getSpielraum_Ids().contains(spielraumID)) {
            throw new ungueltigeSpielraumIDException("Übergebener Spielraum existiert nicht");
        }
        lobby.spielraumVerlassen(botname,spielraumID);
        Spielrunde spielrunde = spielrunden.get(spielraumID);
        spielrunde.spielraumVerlassen(botname);
        for(RMIClientIF client:clientliste) {
            try {
                client.aktualisiereSpielraeume(lobby);
                if (lobby.getSpieler(spielraumID).contains(client.getBenutzername())) {
                    client.aktualisiereSpielstatus(spielrunde);
                    client.aktualisiereSpielraeume(lobby);
                }
            } catch (Exception ignored) {}
        }
        RMIClientIF botclient = null;
        for (RMIClientIF client :clientliste){
            if(client.isBot() && client.getBenutzername().equals(botname))botclient = client; break;
        }
        if (botclient != null)clientliste.remove(botclient);
    }

    /**
     * startet das Spiel im Spielraum mit der übergebenen ID
     * @param spielraumID Int != null
     * @throws ungueltigeSpielraumIDException wenn kein Spielraum mit der ID SpielraumID existiert, oder wenn
     *   spielraumID = 0
     * @throws spielLaeuftBereitsException wenn Spiel zuvor gestartet und noch nicht beendet wurde
     */
    @Override
    public synchronized void spielStarten(int spielraumID) throws ungueltigeSpielraumIDException, spielLaeuftBereitsException, zuWenigSpielerException, RemoteException, ungueltigerBenutzernameException {
        if(spielraumID==0 || !lobby.getSpielraum_Ids().contains(spielraumID)){
            throw new ungueltigeSpielraumIDException("Es existiert kein Spielraum mit der übergebenen ID");
        }
        if(lobby.getSpieler(spielraumID).size() <2) throw new zuWenigSpielerException("Nicht genügend Spieler in Spielrunde");
        Spielrunde spielrunde =spielrunden.get(spielraumID);
        spielrunde.spielStarten();
        for(RMIClientIF client: clientliste){
            client.aktualisiereSpielstatus(spielrunde);
        }
    }

    /**
     * Tauscht entweder 10 weiße Chips gegen ein schwarzen Chip oder umgekehrt, auf der Chipstapel-Instanz der
     *   Spielers mit dem übergebenen Benutzernamen im Spielraum mit der übergebenen ID
     * @param einsergegenzehner Boolean != null, wenn true werden 10 weiße gegen ein schwarzen Chip getauscht,
     *   wenn false, wird ein schwarzer gegen 10 weiße Chips getauscht
     * @param spielraumID Int != null
     * @param benutzername String != null
     * @throws ungueltigerSpielzugException wenn der Spieler mit Benutzernamen benutzername nicht genug weiße bzw.
     *   schwarze Chips auf seinem Chipstapel hat
     * @throws ungueltigeSpielraumIDException wenn kein Spielraum mit der ID SpielraumID existiert, oder wenn
     *   spielraumID = 0
     * @throws ungueltigerBenutzernameException wenn sich im Spielraum mit der ID spielraumID kein Spieler mit dem
     *   Benutzernamen benutzernamen befindet.
     */
    @Override
    public synchronized void chipsTauschen(boolean einsergegenzehner, int spielraumID, String benutzername)
            throws ungueltigerSpielzugException, ungueltigeSpielraumIDException, ungueltigerBenutzernameException, RemoteException, stapelLeerException, ungueltigerChipException {
        if(spielraumID==0 || !lobby.getSpielraum_Ids().contains(spielraumID)){
            throw new ungueltigeSpielraumIDException("Es existiert kein Spielraum mit der übergebenen ID");
        }
        Spielrunde spielrunde =spielrunden.get(spielraumID);
        spielrunde.chipsTauschen(!einsergegenzehner,benutzername);
        for(RMIClientIF client: clientliste){
            client.aktualisiereSpielstatus(spielrunde);
        }
    }

    /**
     * entfernt eine Chip vom Spieler mit dem übergebenen Benutzernamen im Spielraum mit der übergebenen ID
     * @param einserchip Boolean != null wenn true wird ein weißer Chip, wenn false ein schwarzer Chip entfernt
     * @param benutzername String != null
     * @throws ungueltigerSpielzugException wenn der Spieler mit Benutzernamen benutzername nicht genug weiße bzw.
     *   schwarze Chips auf seinem Chipstapel hat
     * @throws ungueltigeSpielraumIDException wenn kein Spielraum mit der ID SpielraumID existiert, oder wenn
     *   spielraumID = 0
     * @throws ungueltigerBenutzernameException wenn sich im Spielraum mit der ID spielraumID kein Spieler mit dem
     *   Benutzernamen benutzernamen befindet.
     */
    @Override
    public synchronized void chipAbgeben(boolean einserchip, String benutzername, int spielraumID)
            throws ungueltigerSpielzugException, ungueltigeSpielraumIDException, ungueltigerBenutzernameException, stapelLeerException, ungueltigerChipException, RemoteException {
        if(spielraumID==0 || !lobby.getSpielraum_Ids().contains(spielraumID)){
            throw new ungueltigeSpielraumIDException("Es existiert kein Spielraum mit der übergebenen ID");
        }
        Spielrunde spielrunde =spielrunden.get(spielraumID);
        spielrunde.chipAbgeben(!einserchip,benutzername);
        for(RMIClientIF client: clientliste){
            client.aktualisiereSpielstatus(spielrunde);
        }
    }

    /**
     * entfernt eine Karte mit dem übergebenen Wert aus der Hand des Spielers mit Benutzernamen benutzername im
     * Spielraum mit der übergebenen ID
     * @param karte Integer !=0 zwischen 0 und 6, wobei 0 eine Lama-Karte repräsentiert
     * @param benutzername String != null
     * @param spielraumID Integer != null
     * @throws ungueltigerSpielzugException wenn der Spieler mit Benutzernamen benutzername keine Karte mit dem Wert
     *   karte auf der Hand hat, oder der Wert karte nach dem Spielregeln nicht auf zum Wert der obersten Karte auf
     *   den Ablagestapel passt
     * @throws ungueltigeSpielraumIDException wenn kein Spielraum mit der ID SpielraumID existiert, oder wenn
     *   spielraumID = 0
     * @throws ungueltigerBenutzernameException wenn sich im Spielraum mit der ID spielraumID kein Spieler mit dem
     *   Benutzernamen benutzernamen befindet.
     */
    @Override
    public synchronized void karteAblegen(int karte, String benutzername, int spielraumID)
            throws ungueltigerSpielzugException, ungueltigeSpielraumIDException, ungueltigerBenutzernameException, RemoteException, stapelLeerException {
        if(spielraumID==0 || !lobby.getSpielraum_Ids().contains(spielraumID)){
            throw new ungueltigeSpielraumIDException("Es existiert kein Spielraum mit der übergebenen ID");
        }
        if(!lobby.getSpieler(spielraumID).contains(benutzername)) throw new ungueltigerBenutzernameException("Spieler mit angegebenen Benutzernamen befindet sich nicht im Spielraum");
        Spielrunde spielrunde =spielrunden.get(spielraumID);
        spielrunde.karteAblegen(benutzername,karte);
        for(RMIClientIF client: clientliste){
            client.aktualisiereSpielstatus(spielrunde);
        }
    }

    /**
     * entfernt die oberste Karte vom Nachziehstapel im Spielraum mit der übergebenen ID und fügt diese Karte den
     *   Handkarten des Spielers mit dem übergebenen Benutzernamen hinzu
     * @param benutzername String != null
     * @param spielraumID Integer != null
     * @throws ungueltigerSpielzugException wenn der Nachziehstapel des Spielraums mit der ID spielraumID
     *   leer ist
     * @throws ungueltigeSpielraumIDException wenn kein Spielraum mit der ID SpielraumID existiert, oder wenn
     *   spielraumID = 0
     * @throws ungueltigerBenutzernameException wenn sich im Spielraum mit der ID spielraumID kein Spieler mit dem
     *   Benutzernamen benutzernamen befindet.
     * @return Integer (Kartenwert) zwischen 0 und 6, wobei 0 ein Lama repräsentiert
     */
    @Override
    public synchronized int karteZiehen(String benutzername, int spielraumID)
            throws ungueltigerSpielzugException, ungueltigeSpielraumIDException, ungueltigerBenutzernameException, stapelLeerException, ungueltigeKarteException, RemoteException {
        if(spielraumID==0 || !lobby.getSpielraum_Ids().contains(spielraumID)){
            throw new ungueltigeSpielraumIDException("Es existiert kein Spielraum mit der übergebenen ID");
        }
        if(!lobby.getSpieler(spielraumID).contains(benutzername)) throw new ungueltigerBenutzernameException("Spieler mit angegebenen Benutzernamen befindet sich nicht im Spielraum");
        Spielrunde spielrunde =spielrunden.get(spielraumID);
        int karte = spielrunde.karteZiehen(benutzername);
        for(RMIClientIF client: clientliste){
            client.aktualisiereSpielstatus(spielrunde);
        }
        return karte;
    }

    /**
     * Markiert den Spieler mit dem übergebenen im Spielraum mit der ID spielraumID als ausgestiegen
     * @param benutzername String != null
     * @param spielraumID Integer != null
     * @throws ungueltigeSpielraumIDException wenn kein Spielraum mit der ID SpielraumID existiert, oder wenn
     *   spielraumID = 0
     * @throws ungueltigerBenutzernameException wenn sich im Spielraum mit der ID spielraumID kein Spieler mit dem
     *   Benutzernamen benutzernamen befindet.
     */
    @Override
    public synchronized void aussteigen(String benutzername, int spielraumID)
            throws ungueltigeSpielraumIDException, ungueltigerBenutzernameException, RemoteException, ungueltigerSpielzugException {
        if (spielraumID == 0 || !lobby.getSpielraum_Ids().contains(spielraumID)) {
            throw new ungueltigeSpielraumIDException("Es existiert kein Spielraum mit der übergebenen ID");
        }
        if (!lobby.getSpieler(spielraumID).contains(benutzername))
            throw new ungueltigerBenutzernameException("Spieler mit angegebenen Benutzernamen befindet sich nicht im Spielraum");
        Spielrunde spielrunde = spielrunden.get(spielraumID);
        spielrunde.aussteigen(benutzername);
        for (RMIClientIF client : clientliste) {
            client.aktualisiereSpielstatus(spielrunde);
        }
    }
}
