package RMI;

import Highscore.Bestenliste;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * This Class
 */

public class RMIClient extends UnicastRemoteObject implements RMIClientIF {


    public RMIClient() throws RemoteException {
        super();
    }

    /*@Override
    public void aktualisiereSpielstatus(Spielraum spielraum) throws RemoteException {

    }*/

    /**
     * with this methode the player will be able to send a message in the chat.
     * @param benutzername name of player that will send the message.
     * @param nachricht the actual message.
     * @throws RemoteException
     */
    @Override
    public void uebertrageChatnachricht(String benutzername, String nachricht) throws RemoteException {

    }

    /**
     * This Methode returns the name of the player.
     * @return name of player.
     * @throws RemoteException
     */
    @Override
    public String getBenutzername() throws RemoteException {
        return null;
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

    @Override
    public void setSpielraum() throws RemoteException {

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
