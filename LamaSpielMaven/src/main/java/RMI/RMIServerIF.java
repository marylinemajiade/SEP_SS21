package RMI;

import fachlicheExceptions.*;

import java.rmi.Remote;
import java.rmi.RemoteException;

/*Remote Interface für RMI.RMIServer. Für die Dokumentation der Methoden siehe RMI.RMIServer*/
public interface RMIServerIF extends Remote {

    void registriereClient(RMIClientIF client) throws RemoteException, ungueltigerBenutzernameException;

    boolean benutzerdatenPruefen(String benutzername, String passwort) throws RemoteException;

    void benutzerRegistrieren(String benutzername, String passwort) throws RemoteException, benutzerNameVergebenException;

    void benutzerLoeschen(String benutzername) throws RemoteException, ungueltigerBenutzernameException;

    void sendeChatnachricht(String benutzername, int spielraumID, String nachricht)
            throws RemoteException, ungueltigeSpielraumIDException, ungueltigerBenutzernameException;

    void spielraumErstellen(String benutzername) throws RemoteException, ungueltigerBenutzernameException;
    void spielraumBeitreten(String benutzername, int spielraumID)
            throws RemoteException, spielraumVollException, ungueltigerBenutzernameException, ungueltigeSpielraumIDException;

    void spielraumVerlassen(String benutzername, int spielraumID)
            throws RemoteException, ungueltigeSpielraumIDException, ungueltigerBenutzernameException;

    void botHinzufuegen(boolean easybot, int spielraumID)
            throws RemoteException, ungueltigeSpielraumIDException, spielraumVollException, ungueltigerBenutzernameException;

    void botEntfernen(String botname, int spielraumID)
                throws RemoteException, ungueltigeSpielraumIDException, ungueltigerBenutzernameException;

    void spielStarten(int spielraumID)
            throws RemoteException, ungueltigeSpielraumIDException, spielLaeuftBereitsException, zuWenigSpielerException, ungueltigerBenutzernameException;

    void chipsTauschen(boolean einsergegenzehner, int spielraumID, String benutzername)
            throws RemoteException, ungueltigerSpielzugException, ungueltigeSpielraumIDException, ungueltigerBenutzernameException, stapelLeerException, ungueltigerChipException;

    void chipAbgeben(boolean einserchip, String benutzername, int spielraumID)
            throws RemoteException, ungueltigerSpielzugException, ungueltigeSpielraumIDException, ungueltigerBenutzernameException, stapelLeerException, ungueltigerChipException;

    void karteAblegen(int karte, String benutzername, int spielraumID)
            throws RemoteException, ungueltigerSpielzugException, ungueltigeSpielraumIDException, ungueltigerBenutzernameException, stapelLeerException;

    int karteZiehen(String benutzername, int spielraumId)
            throws RemoteException, ungueltigerSpielzugException, ungueltigeSpielraumIDException, ungueltigerBenutzernameException, stapelLeerException, ungueltigeKarteException;

    void aussteigen(String benutzername, int spielraumId)
            throws RemoteException, ungueltigeSpielraumIDException, ungueltigerBenutzernameException, ungueltigerSpielzugException;
}
