package RMI;

import Highscore.Bestenliste;
import Spiel.Spielrunde;
import SpielLobby.Lobby;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import static GUI.Chat.outputArea;

/**
 * This Class
 */

public class RMIClient extends UnicastRemoteObject implements RMIClientIF, Serializable {

    //Attribute
    public RMIServerIF rmiserver;
    String benutzername;


    public RMIClient(RMIServerIF rmiserver,String benutzername) throws RemoteException {
        super();
        this.rmiserver = rmiserver;
        this.benutzername = benutzername;
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
    public void setSpielraum(Spielrunde spielrunde) throws RemoteException {

    }

    /**
     * This Methode checkes if the Player is a Bot or a User(Client).
     * @return true if the Player is a Bot, false otherwise (User).
     * @throws RemoteException
     */
    @Override
    public boolean isBot() throws RemoteException {
        return false;
    }
}
