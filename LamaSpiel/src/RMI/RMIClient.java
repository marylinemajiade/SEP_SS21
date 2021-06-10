package RMI;

import Highscore.Bestenliste;
import Spiel.Spielrunde;
import SpielLobby.Lobby;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RMIClient extends UnicastRemoteObject implements RMIClientIF,Runnable{

    private RMIServerIF rmiserver;

    public RMIClient(RMIServerIF rmiserver) throws RemoteException {
        super();
        this.rmiserver=rmiserver;
    }

    @Override
    public void aktualisiereSpielstatus(Spielrunde spielraum) throws RemoteException {

    }

    @Override
    public void uebertrageChatnachricht(String benutzername, String nachricht) throws RemoteException {

    }

    @Override
    public String getBenutzername() throws RemoteException {
        return null;
    }

    @Override
    public void akutalisiereBestenliste(Bestenliste bestenliste) throws RemoteException {

    }

    @Override
    public void aktualisiereSpielraeume(Lobby lobby) throws RemoteException {

    }

    @Override
    public void setSpielraum(Spielrunde spielrunde) throws RemoteException {

    }



    @Override
    public boolean isBot() throws RemoteException {
        return false;
    }

    @Override
    public void run() {
        try {
            rmiserver.registriereClient(this);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}

