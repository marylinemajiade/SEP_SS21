package RMI;

import fachlicheExceptions.*;

import java.rmi.Remote;
/*Remote Interface für RMIServer. Für die Dokumentation der Methoden siehe RMIServer*/
public interface RMIServerIF extends Remote {

    void registriereClient(RMIClientIF client);

    boolean benutzerdatenPruefen(String benutzername, String passwort);

    void benutzerRegistrieren(String benutzername, String passwort) throws benutzerNameVergebenException;

    void benutzerLoeschen(String benutzername) throws ungueltigerBenutzernameException;

    void sendeChatnachricht(String benutzername, int spielraumID, String nachricht)
            throws ungueltigeSpielraumIDException, ungueltigerBenutzernameException;

    void spielraumBeitreten(String benutzername, int spielraumID)
            throws spielraumVollException, ungueltigerBenutzernameException, ungueltigeSpielraumIDException;

    void spielraumVerlassen(String benutzername, int spielraumID)
            throws ungueltigeSpielraumIDException, ungueltigerBenutzernameException;

    void botHinzufuegen(boolean easybot, int spielraumID)
            throws ungueltigeSpielraumIDException, spielraumVollException;

    void botEntfernen(String botname, int spielraumID)
                throws ungueltigeSpielraumIDException, ungueltigerBenutzernameException;

    void spielStarten(int spielraumID)
            throws ungueltigeSpielraumIDException, spielLaeuftBereitsException;

    void chipsTauschen(boolean einsergegenzehner, int spielraumID, String benutzername)
            throws ungueltigerSpielzugException, ungueltigeSpielraumIDException, ungueltigerBenutzernameException;

    void chipAbgeben(boolean einserchip, String benutzername, int spielraumID)
            throws ungueltigerSpielzugException, ungueltigeSpielraumIDException, ungueltigerBenutzernameException;

    void karteAblegen(int karte, String benutzername, int spielraumID)
            throws ungueltigerSpielzugException, ungueltigeSpielraumIDException, ungueltigerBenutzernameException;

    int karteZiehen(String benutzername, int spielraumId)
            throws ungueltigerSpielzugException, ungueltigeSpielraumIDException, ungueltigerBenutzernameException;

    void aussteigen(String benutzername, int spielraumId)
            throws ungueltigeSpielraumIDException, ungueltigerBenutzernameException ;
}
