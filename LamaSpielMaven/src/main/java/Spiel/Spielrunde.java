package Spiel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;
import SpielLobby.Lobby;
import fachlicheExceptions.*;



/**
 * Die Klasse verwaltet die Spielrunde mit den Funktionalitäten des Spielers in Bezug auf Karten/Chips.
 *
 * @author Catharina Helten
 */

public class Spielrunde extends Chipstapel {

    ArrayList<String> spielerInRunde;
    private int spielraumId;
    private HashMap<String,ArrayList<Integer>> handkarten = new HashMap<>();
    private Stack<Integer> ablagestapel = new Stack<>();
    private Stack<Integer> nachziehstapel = new Stack<>();
    private HashSet<Integer> spielkarten = new HashSet<>();
    private Chipstapel chipstapel = new Chipstapel();
    private Stack<String> spielReihenfolge = new Stack<>();
    int aktuellerPunktestand = 0;
    String letzterSpieler;
    int Runde = 0;
    Lobby lobby;


    /**
     * Die Methode dient zum Abrufen der Spielraum-ID
     * @return gibt die ID als Integer zurück
     */


    public Spielrunde(int Id, Lobby lobby){
        this.lobby = lobby;
        this.spielraumId = Id;
        this.spielerInRunde = lobby.getSpieler(Id);
    }

    public int getRaumId(){
        return spielraumId;
    }


    /**
     * Die Methode dient zum Karte ablegen eines Spielers
     * @param benutzername Benutzername des Spielers, dessen Karte abgelegt werden soll
     * @param karte Karte, die abgelegt werden soll dargestellt als Integer-Wert
     * @throws stapelLeerException
     */
    public void karteAblegen(String benutzername, int karte) throws stapelLeerException{
        try {
            handkarten.get(benutzername).remove(karte);
            ablagestapel = getAblagestapel();
            ablagestapel.push(karte);
        }
        catch(Exception e){

        }
    }

    /**
     * Die Methode dient zum Ziehen einer Karte
     * @param benutzername Benutzername des Spielers, der die Karte zieht
     * @return gibt die gezogene Karte als Integer-Wert zurück
     * @throws ungueltigeKarteException
     * @throws stapelLeerException
     *
     */
    public int karteZiehen(String benutzername) throws ungueltigeKarteException, stapelLeerException{
        try {
            nachziehstapel = getNachziehstapel();
            int karte = nachziehstapel.pop();
            handkarten.get(benutzername).add(karte);
            return karte;
        }
        catch (Exception e){

        }
    return 0;
    }



    /**
     * Die Methode dient zum Aussteigen bei einer Runde
     * @param benutzername Benutzername des Spielers, der aussteigen möchte
     * @throws ungueltigerSpielzugException
     * @throws ungueltigerBenutzernameException
     */
    public boolean aussteigen(String benutzername) throws ungueltigerSpielzugException, ungueltigerBenutzernameException{
        spielReihenfolge.remove(benutzername);
        handkarten.get(benutzername).clear();
        return true;
    }



    /**
     * Die Methode dient zum Tauschen von Chips
     * @param zehngegeneins stell dar, ob ein Zehnerchip gegen einen Einserchip getauscht wird oder andersrum
     * @param benutzername Benutzername des Spielers, der die Chips tauschen möchte
     * @throws ungueltigerBenutzernameException
     * @throws ungueltigerChipException
     * @throws stapelLeerException
     * @throws ungueltigerSpielzugException
     */
    public void chipsTauschen(boolean zehngegeneins, String benutzername) throws ungueltigerBenutzernameException, ungueltigerChipException, stapelLeerException, ungueltigerSpielzugException {
    int white = chipstapel.getWeiss();
    int black = chipstapel.getSchwarz();
    try {
        if (zehngegeneins) {
            chipstapel.setSchwarz(black - 1);
            chipstapel.setWeiss(white + 10);
        } else {
            chipstapel.setWeiss(white - 10);
            chipstapel.setSchwarz(black + 1);
        }
    } catch(Exception e){
        }

    }


    /**
     * Die Methode dient zum Abgeben eines Chips
     * @param zehnerchip stellt dar, ob ein Zehner oder Einserchip abgegeben wird
     * @param benutzername Benutzername des Spielers, der den Chip abgeben möchte
     * @throws ungueltigerBenutzernameException
     * @throws ungueltigerChipException
     * @throws stapelLeerException
     * @throws ungueltigerSpielzugException
     */
    public void chipAbgeben(boolean zehnerchip, String benutzername) throws ungueltigerBenutzernameException, ungueltigerChipException, stapelLeerException, ungueltigerSpielzugException{
        chipstapel = getChipstapel(benutzername);
        int white = chipstapel.getWeiss();
        int black = chipstapel.getSchwarz();
        try {
            if (zehnerchip) {
                chipstapel.setSchwarz(black - 1);
            }else
            chipstapel.setWeiss(white - 1);
        }
        catch(Exception e){
        }
    }


    /**
     * Die Methode dient zum Abrufen welcher Spieler an der Reihe ist
     * @return gibt den Benutzernamen des Spielers zurück
     */
    public String anDerReihe(){
        return spielReihenfolge.pop();
    }


    /**
     * Die Methode dient zum Starten des Spiels
     * @throws spielLaeuftBereitsException
     */
    public boolean spielStarten() throws spielLaeuftBereitsException, zuWenigSpielerException{
        // Spielerreihenfolge
        spielReihenfolge.addAll(spielerInRunde);
        //Spielkarten mischen
        for (int i = 1; i<=7; i++) {
            for (int j = 1; j <= 8; j++) {
                spielkarten.add(i);
            }
        }

        //Nachziehstapel
        for(int karte:spielkarten){
         nachziehstapel.push(karte);
        }

        //Handkarten verteilen
        for(int i = 0; i<6; i++) {
        for (String name:spielerInRunde){
                handkarten.get(name).set(i,nachziehstapel.pop());
            }
        }
        //Ablagestapel oberste Karte von Nachziehstapel
        ablagestapel.push(nachziehstapel.pop());

        //Runde läuft bis einer -40 Punkte hat
     //  while (aktuellerPunktestand != -40){

     //   letzterSpieler = anDerReihe();
      // }

        return true;
    }


    /**
     * Die Methode dient zur Verwaltung des Handkartenstapels
     * @param benutzername Benutzername des Spielers, dessen Stapel verwaltet wird
     * @return gibt den Stapel als ungeordnetes HashSet aus Integer-Werten der Karten zurück
     * @throws stapelLeerException
     * @throws ungueltigerBenutzernameException
     */
    public ArrayList<Integer> getHandkarten(String benutzername) throws stapelLeerException, ungueltigerBenutzernameException{
        return handkarten.get(benutzername);
    }


    /**
     * Die Methode dient zur Verwaltung des Ablagestapels
     * @return gibt den Stapel als Stack aus Integer-Werten der Karten zurück
     * @throws stapelLeerException
     */

    public Stack<Integer> getAblagestapel() {
        return ablagestapel;
    }


    /**
     * Die Methode dient zur Verwaltung des Nachziehstapels
     * @return gibt den Stapel als Stack aus Integer-Werten der Karten zurück
     * @throws stapelLeerException
     */
    public Stack<Integer> getNachziehstapel() throws stapelLeerException{
        return nachziehstapel;
    }

    /**
     * Die Methode dient zur Verwaltung des Chipstapels
     * @param benutzername Benutzername des Spielers, dessen Chipstapel verwaltet wird
     * @return gibt den Chipstapel mit der richtigen Anzahl weißer und schwarzer Chips zurück
     * @throws stapelLeerException
     * @throws ungueltigerBenutzernameException
     */

    public Chipstapel getChipstapel(String benutzername) throws stapelLeerException, ungueltigerBenutzernameException{
        int white = chipstapel.getWeiss();
        int black = chipstapel.getSchwarz();
        chipstapel.setWeiss(white);
        chipstapel.setSchwarz(black);
        return chipstapel;
    }



    /**
     * Die Methode dient zum Verlassen des Spielraumes
     * @param benutzername Benutzername des Spielers, der verlassen möchte
     * @throws ungueltigerBenutzernameException
     */
    public void spielraumVerlassen(String benutzername) throws ungueltigerBenutzernameException {
        spielerInRunde.remove(benutzername);
        lobby.getSpielerInSpielrunde().get(spielraumId).remove(benutzername);
        lobby.getSpielerInSpielrunde().get(0).add(benutzername);
    }


}
