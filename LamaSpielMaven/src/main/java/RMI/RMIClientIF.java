package RMI;

import Highscore.Bestenliste;
import Spiel.Spielrunde;
import SpielLobby.Lobby;
import fachlicheExceptions.*;
import java.rmi.Remote;
import java.rmi.RemoteException;

/*Remote Interface für RMI.RMIClient. Für die Dokumentation der Methoden siehe RMI.RMIClient*/
public interface RMIClientIF extends Remote {

    void aktualisiereSpielstatus(Spielrunde spielrunde) throws RemoteException, ungueltigerBenutzernameException;

    void uebertrageChatnachricht(String benutzername, String nachricht) throws RemoteException, ungueltigerBenutzernameException, ZustellungNachrichtNichtMoeglichException ;

    String getBenutzername() throws RemoteException, ungueltigerBenutzernameException;

    void akutalisiereBestenliste(Bestenliste bestenliste) throws RemoteException;

    void aktualisiereSpielraeume(Lobby lobby) throws RemoteException;

    void setSpielraum(Spielrunde spielrunde) throws RemoteException;

    boolean isBot() throws RemoteException, ungueltigerBenutzernameException;

}
