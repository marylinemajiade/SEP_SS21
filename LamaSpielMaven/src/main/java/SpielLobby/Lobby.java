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
    Chat chat;
    BotEinfach botEinfach;
    BotSchwer botSchwer;



    /**
     * Die Methode gib die Liste aller Spieler zurück, die sich in dem
     * Spielraum mit ID spielraumId befinden
     * @return Liste von den Spielern
     */
    public List<String> getSpieler(int spielraumId){
        return Collections.singletonList(Arrays.asList(benutzers).toString());
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
     * Die Methode gib den Spielraum mit dem Index id zurück
     * @param id Index von dem Spielraum
     * @return Spielraum mit dem entsprechendem id (Index)
     */

    @Override
    public Spielraum getSpielraum(int id) {
        return spielraums.get(id);
    }



    /**
     * Die Methode erstellt einen neuen Spielraum mit einem Benutzer
     * @param benutzername Benutzername des Spielers, der den Spielraum erstellt
     */
    @Override
    public void spielraumErstellen(String benutzername) {

    }



    /**
     * Die Methode sendet eine Nachricht in dem Lobby Chat
     * @param benutzername Benutzername des Spielers, der die Nachricht sendet
     * @param spielraumID Id des Spielraums
     * @param nachricht Nachricht, die gesendet werden muss
     */
    @Override
    public void sendeChatnachricht(String benutzername, int spielraumID, String nachricht) {

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

    /**
     * Die Methode fügt einen Bot ins Spielraum hinzu und prüft, ob es sich um einen einfachen Bot handelt oder nicht
     * @param boteasy Niveau des Bots
     * @param spielraumID Id des Spielraums
     */
    @Override
    public void botHinzufuegen(boolean boteasy, int spielraumID) {

    }

    /**
     * Die Methode entfernt den Bot mit dem Namen botname aus dem Spielraum mit dem Id spielraumId
     * @param botname Name des Bots, der entfernt werden muss
     * @param spielraumID Id des Spielraums
     */
    @Override
    public void botEntfernen(String botname, int spielraumID) {

    }

    /**
     * Die Methode Startet das Spiel
     * @param spielraumID Id von dem Spielraum
     */
    @Override
    public void spielStarten(int spielraumID) {

    }

}


