package SpielLobby;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lobby {
    private ArrayList<Integer> spielraum_Ids;
    private ArrayList<List<String>> spieler;


    public ArrayList<Integer> getSpielraum_Ids(){
    return spielraum_Ids;
    }

    public void spielRaumLoeschen (int spielraumId){
        spielraum_Ids.remove(spielraumId);
        spieler.remove(spielraumId);
    }

    public void spielRaumHinzufuegen (int spielraumId){
        spielraum_Ids.add(spielraumId);
        spieler.add(Collections.emptyList());

    }

    public List<String> getSpieler(int spielraumId){
        return spieler.get(spielraumId);
    }

    public void spielraumBeitreten(String benutzername, int spielraumId){
       getSpieler(spielraumId).add(benutzername);
    }

    public void spielraumVerlassen(String benutzername, int spielraumId){
        getSpieler(spielraumId).remove(benutzername);
    }

}
