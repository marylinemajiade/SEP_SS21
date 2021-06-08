package RMI;

import Highscore.Bestenliste;
import fachlicheExceptions.*;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIClientIF extends Remote {

    void aktualisiereSpielstatus(Spielraum spielraum) throws RemoteException;

    void uebertrageChatnachricht(String benutzername, String nachricht) throws RemoteException, ungueltigerBenutzernameException, ZustellungNachrichtNichtMoeglichException ;

    String getBenutzername() throws RemoteException, ungueltigerBenutzernameException;

    void akutalisiereBestenliste(Bestenliste bestenliste) throws RemoteException;

    void setSpielraum() throws RemoteException;

    boolean isBot() throws RemoteException;

}
