package SpielLobby;
import Spiel.Spielrunde;
import java.util.*;


/**
 * @author Maryline Majiade, Catharina Helten
 * Die Klasse verwaltet die Lobby des Spiels.
 */
public class Lobby{

    private ArrayList<Integer> spielraum_Ids = new ArrayList<>();
    public ArrayList<Spielrunde> spielrunden = new ArrayList<>();
    private HashMap<Integer,ArrayList<String>> spielerInSpielrunde = new HashMap<Integer,ArrayList<String>>();


    /**
     * Die Methode gibt die Liste aller Spielraum IDs zurück
     * @return Liste von Spielraum IDs
     */

    public ArrayList<Integer> getSpielraum_Ids(){
        return spielraum_Ids;
    }


    /**
     * Die Methode fügt den Spieler mit dem übegebenen Benutzernamen dem Spielraum mit der ID spielraumID hinzu
     * @param benutzername String != null
     * @param spielraumId Integer != null
     */
    public void spielraumBeitreten(String benutzername, int spielraumId){
        spielerInSpielrunde.get(spielraumId).add(benutzername);
        spielerInSpielrunde.get(0).remove(benutzername);
        spielrunden.get(spielraumId).spielerInRunde.add(benutzername);
    }


    /**
     * Die Methode entfernt den Benutzer mit dem Namen benutzername aus dem Spielraum mit dem Id spielraumId
     * @param benutzername Benutzername des Spielers, der entfernt werden muss
     * @param spielraumId Id des Spielraums
     */
    public void spielraumVerlassen(String benutzername, int spielraumId){
        if(spielraum_Ids.contains(spielraumId)) {
            spielerInSpielrunde.get(spielraumId).remove(benutzername);
        }

    }


    /**
     * Die Methode löscht den Spielraum mit dem Id spielraumId
     * @param spielraumID Id des Spielraums, der gelöscht werden muss
     */

    public void spielraumLoeschen(int spielraumID) {
        spielrunden.remove(spielraumID);
        spielraum_Ids.remove(spielraumID);
    }


    /**
     * Die Methode fügt einen Spielraum mit dem Id spielraumId in die Lobby hinzu
     * @param spielraumID Id des Spielraums, der hinzugefügt werden muss
     */

    public void spielraumHinzufuegen(int spielraumID) {
        Spielrunde spielrunde = new Spielrunde(spielraumID, this);
        spielraum_Ids.add(spielraumID);
        spielrunden.add(spielrunde);
        spielerInSpielrunde.put(spielraumID, new ArrayList<String>());
    }

    /**
     * Die Methode gib die Liste aller Spieler zurück, die sich in dem
     * Spielraum mit ID spielraumId befinden
     * @return Liste von den Spielern
     */
    public ArrayList<String> getSpieler(int spielraumId){
        return spielerInSpielrunde.get(spielraumId);
    }

}
