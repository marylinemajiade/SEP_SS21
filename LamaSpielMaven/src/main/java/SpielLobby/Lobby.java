package SpielLobby;
import Bot.BotEinfach;
import Bot.BotSchwer;
import GUI.Chat;
import Konto.Benutzer;
import fachlicheExceptions.spielraumVollException;
import fachlicheExceptions.ungueltigerBenutzernameException;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;


/**
 * @author Maryline Majiade
 * Die Klasse verwaltet die Lobby des Spiels.
 */
public class Lobby implements LobbyIF{

    Benutzer benutzer;
    ArrayList<Benutzer> benutzers;
    ArrayList<Spielraum> spielraums;


    /**
     * Die Methode gib die Liste aller Spieler zurück, die sich in dem
     * Spielraum mit ID spielraumId befinden
     * @return Liste von den Spielern
     */
    public ArrayList<String> getSpieler(int spielraumId){
        return null;
    }

    /**
     * Die Methode gibt die Liste aller Spielraum IDs zurück
     * @return Liste von Spielraum IDs
     */
    public ArrayList<Integer> getSpielraum_Ids(){
        ArrayList<Integer> spielraumsIds = null;
        for(int i=0; i<=spielraums.size(); i++){
            assert spielraumsIds != null;
            spielraumsIds.add(i);
        }
        return spielraumsIds;
    }

    /**
     * Die Methode fügt den Spieler mit dem übegebenen Benutzernamen dem Spielraum mit der ID spielraumID hinzu
     * @param benutzername String != null
     * @param spielraumId Integer != null
     */
    public void spielraumBeitreten(String benutzername, int spielraumId){
        benutzername = benutzer.getBenutzername();
        spielraums.get(spielraumId).benutzerHinzufuegen(benutzername);
    }


    /**
     * Die Methode entfernt den Benutzer mit dem Namen benutzername aus dem Spielraum mit dem Id spielraumId
     * @param benutzername Benutzername des Spielers, der entfernt werden muss
     * @param spielraumId Id des Spielraums
     */
    public void spielraumVerlassen(String benutzername, int spielraumId){

    }

    @Override
    public void spielraumLoeschen(int spielraumID) {

    }

    @Override
    public void spielraumHinzufuegen(int spielraumID) {

    }


}


