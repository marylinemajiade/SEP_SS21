package RMI;

import Highscore.Bestenliste;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RMIClient extends UnicastRemoteObject implements RMIClientIF {


    public RMIClient() throws RemoteException {
        super();
    }

    @Override
    public void aktualisiereSpielstatus(Spielraum spielraum) throws RemoteException {

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
    public void setSpielraum() throws RemoteException {

    }

    @Override
    public boolean isBot() throws RemoteException {
        return false;
    }
}
