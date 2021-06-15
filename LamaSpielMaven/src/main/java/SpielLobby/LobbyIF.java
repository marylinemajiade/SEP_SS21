package SpielLobby;

import java.rmi.RemoteException;
import java.rmi.*;
import java.util.ArrayList;
import java.util.List;

/* Interface für SpielLobby.Lobby. Für die Dokumentation der Methoden siehe SpielLobby.Lobby*/
    public interface LobbyIF{

    ArrayList<String> getSpieler(int spielraumId);
    ArrayList<Integer> getSpielraum_Ids();
    void spielraumBeitreten(String benutzername, int spielraumID);
    void spielraumVerlassen(String benutzername, int spielraumID);
    void spielraumLoeschen(int spielraumID);
    void spielraumHinzufuegen(int spielraumID);


}
