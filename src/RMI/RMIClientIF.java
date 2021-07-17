package RMI;

import Highscore.Bestenliste;
import Spiel.Spielrunde;
import SpielLobby.Lobby;
import fachlicheExceptions.ZustellungNachrichtNichtMoeglichException;
import fachlicheExceptions.benutzerNameVergebenException;
import fachlicheExceptions.ungueltigerBenutzernameException;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/*Remote Interface für RMI.RMIClient. Für die Dokumentation der Methoden siehe RMI.RMIClient*/
public interface RMIClientIF extends Remote {

    void aktualisiereSpielstatus(Spielrunde spielrunde) throws RemoteException, ungueltigerBenutzernameException;

    void uebertrageChatnachricht(String benutzername, String nachricht) throws RemoteException, ungueltigerBenutzernameException, ZustellungNachrichtNichtMoeglichException;

    String getBenutzername() throws RemoteException, ungueltigerBenutzernameException;

    void akutalisiereBestenliste(Bestenliste bestenliste) throws RemoteException;

    void aktualisiereSpielraeume(Lobby lobby) throws RemoteException;

    void setSpielraum(Spielrunde spielrunde) throws RemoteException, ungueltigerBenutzernameException;

    ArrayList<Spielrunde> getSpielraume() throws RemoteException;

    boolean isBot() throws RemoteException, ungueltigerBenutzernameException;

    void registriereSpieler(String benutzername, String passwort) throws RemoteException, ungueltigerBenutzernameException, benutzerNameVergebenException;

    boolean loggeSpielerEin(String benutzername, String passwort) throws RemoteException, ungueltigerBenutzernameException;

    void loescheSpieler() throws RemoteException, ungueltigerBenutzernameException;

    void erstelleSpielraum() throws RemoteException, ungueltigerBenutzernameException;


}
