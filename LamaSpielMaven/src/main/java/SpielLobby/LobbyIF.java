package SpielLobby;

import java.rmi.RemoteException;
import java.rmi.*;

/* Interface für SpielLobby.Lobby. Für die Dokumentation der Methoden siehe SpielLobby.Lobby*/
    public interface LobbyIF{

    //SpielRaum getRoom(int id);


    void sendeChatnachricht(String benutzername, int spielraumID, String nachricht);

    void spielraumErstellen(String benutzername);
    void spielraumBeitreten(String benutzername, int spielraumID);
    void spielraumVerlassen(String benutzername, int spielraumID);

    void botHinzufuegen(boolean easybot, int spielraumID);
    void botEntfernen(String botname, int spielraumID);

    void spielStarten(int spielraumID);
    //HashMap<Integer, SpielRaum> getRooms();

}
