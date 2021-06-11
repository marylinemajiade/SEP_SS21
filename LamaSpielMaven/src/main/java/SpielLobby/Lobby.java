package SpielLobby;
import Konto.Benutzer;
import fachlicheExceptions.spielraumVollException;
import fachlicheExceptions.ungueltigerBenutzernameException;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Maryline Majiade
 * Die Klasse verwaltet die Lobby des Spiels.
 */
public class Lobby extends UnicastRemoteObject implements LobbyIF{


    protected Lobby() throws RemoteException {
        super();
    }



    public List<Benutzer> getSpieler(int spielraumId){
        return null;
    }

    /**
     * Die Methode erstellt einen neuen Spielraum mit einem Benutzer
     * @return Liste von Spielraum
     */
    public ArrayList<Integer> getSpielraum_Ids(){
        return null;
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
