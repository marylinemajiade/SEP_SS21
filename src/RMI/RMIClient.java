package RMI;

import Highscore.Bestenliste;
import Konto.Benutzer;
import Konto.BenutzerVerwalten;
import Spiel.Spielrunde;
import SpielLobby.Lobby;
import fachlicheExceptions.benutzerNameVergebenException;
import fachlicheExceptions.ungueltigerBenutzernameException;

import java.beans.JavaBean;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;

import static GUI.Chat.outputArea;

/**
 * This Class
 */

public class RMIClient extends UnicastRemoteObject implements RMIClientIF, Serializable {

    //Attribute
    public  RMIServerIF rmiserver;
    public String benutzername;
    public BenutzerVerwalten benutzerVerwalten = new BenutzerVerwalten();
    public Bestenliste bestenliste = new Bestenliste();
    public HashMap<String,Integer> bestenListe ;

    public RMIClient(RMIServerIF rmiserver) throws RemoteException {
        super();
        this.rmiserver = rmiserver;
    }

    @Override
    public void aktualisiereSpielstatus(Spielrunde spielrunde) throws RemoteException {

    }

    /**
     * with this methode the player will be able to send a message in the chat.
     * @param benutzername name of player who will send the message.
     * @param nachricht the actual message.
     * @throws RemoteException
     */
    @Override
    public void uebertrageChatnachricht(String benutzername, String nachricht) throws RemoteException {
        // System.out.print("["+benutzername+"] "+nachricht+"\n");

        outputArea.setText(benutzername + ": " + nachricht + "\n");

    }

    /**
     * This Methode returns the name of the player.
     * @return name of player.
     * @throws RemoteException
     */
    @Override
    public String getBenutzername() throws RemoteException {
        return benutzername;
    }

    /**
     * This methode refreshes the Bestlist looking for a change in the ranking of Players.
     * it'll be executed after every finished game.
     * @param bestenliste , the bestliste that will be refreshed.
     * @throws RemoteException
     */
    @Override
    public void akutalisiereBestenliste(Bestenliste bestenliste) throws RemoteException {

        bestenliste.getBestenliste().clear();
        for (Benutzer benutzer: benutzerVerwalten.returnAllPlayer()
        ) {
            bestenListe.put(benutzer.getBenutzername(), benutzer.getScore());
        }
        bestenliste.setBestenliste(bestenListe);
    }

    /**
     * This Methode refreshes the Lobby informations, when a player exit the Room,
     * or a new Player join the Room to play a game.
     * also when a Bot is been removed or added to the game
     * @param lobby
     * @throws RemoteException
     */
    @Override
    public void aktualisiereSpielraeume(Lobby lobby) throws RemoteException {

    }

    @Override
    public void setSpielraum(Spielrunde spielrunde) throws RemoteException, ungueltigerBenutzernameException {

        rmiserver.spielraumErstellen(benutzername);

    }

    @Override
    public ArrayList<Spielrunde> getSpielraume() throws RemoteException {
         return rmiserver.getAllSpielraueme();

    }

    /**
     * This Methode checkes if the Player is a Bot or a User(Client).
     * @return true if the Player is a Bot, false otherwise (User).
     * @throws RemoteException
     */
    @Override
    public boolean isBot() throws RemoteException {
        if(benutzername.startsWith("bot"))
            return true;
        else return false;
    }

    @Override
    public void registriereSpieler(String benutzername, String passwort) throws RemoteException, benutzerNameVergebenException {
        Benutzer benutzer1 = new Benutzer(benutzername,passwort);
        rmiserver.benutzerRegistrieren(benutzer1.getBenutzername(),benutzer1.getPasswort());
    }

    @Override
    public boolean loggeSpielerEin(String benutzername, String passwort) throws RemoteException, ungueltigerBenutzernameException {
        Benutzer benutzer1 = new Benutzer(benutzername,passwort);
        this.benutzername = benutzername;
        return rmiserver.benutzerdatenPruefen(benutzer1.getBenutzername(),benutzer1.getPasswort());
    }

    @Override
    public void loescheSpieler() throws RemoteException, ungueltigerBenutzernameException {
        rmiserver.benutzerLoeschen(benutzername);
    }

    @Override
    public void erstelleSpielraum() throws RemoteException, ungueltigerBenutzernameException {
        rmiserver.spielraumErstellen(benutzername);
    }




}
