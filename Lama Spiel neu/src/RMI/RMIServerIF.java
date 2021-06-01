package RMI;

import java.rmi.Remote;

public interface RMIServerIF extends Remote {
    void registriereClient(RMIClientIF client);
    boolean benutzerdatenPruefen(String benutzername, String passwort);
    void benutzerRegistrieren(String benutzername, String passwort);
    void benutzerLoeschen(String benutzername);
    void sendeChatnachricht(String benutzername, int spielraumID, String nachricht);
    void spielraumBeitreten(String benutzername, int spielraumID);
    void spielraumVerlassen(String benutzername, int spielraumID);
    void botHinzufuegen(boolean easybot, int spielraumID);
    void botEntfernen(String botname, int spielraumID);
    void spielStarten(int spielraumID);
    void chipsTauschen(boolean einsergegenzehner, int spielraumID, String benutzername);
    void chipAbgeben(boolean einserchip, String benutzername, int spielraumID);
    void karteAblegen(int karte, String benutzername, int spielraumID);
    int karteZiehen(String benutzername, int spielraumId);
    void aussteigen(String benutzername, int spielraumId);
}
