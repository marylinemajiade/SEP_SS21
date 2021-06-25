package Spiel;

import java.util.*;
import SpielLobby.Lobby;
import fachlicheExceptions.*;

/**
 * Die Klasse verwaltet ein LAMA-Spiel, bietet Getter-Methoden für alle im Spiel benötigten Ressourcen (Karten, Chips)
 * und bietet Methoden um alle Spielzüge gemäß des LAMA-Regelwerks durchzuführen
 *
 * @author Catharina Helten und Nick Jochum
 */

public class Spielrunde extends Chipstapel {

    //Attribute
    public ArrayList<String> spielerInRunde = new ArrayList<>();
    private final int spielraumId;
    private final HashMap<String,ArrayList<Integer>> handkartenSpieler = new HashMap<>();
    private final Stack<Integer> ablagestapel = new Stack<>();
    private final Stack<Integer> nachziehstapel = new Stack<>();
    private final HashMap<String,Chipstapel> chipstapelSpieler = new HashMap<>();
    private final HashSet<String> ausgestiegeneSpieler = new HashSet<>();
    private int amZugIndex=0;


    //Konstruktor. Lobby Argument wird nicht mehr verwendet. ID sollte im Sysem global eindeutiger Integerwert != 0 sein
    public Spielrunde(int id, Lobby lobby){
        this.spielraumId = id;
    }


    /**
     * Getter-Methode für SpielraumID
     * @return Integer != 0 und != null
     */
    public int getRaumId(){
        return spielraumId;
    }


    /**
     * Führt den Spielzug "Karte ablegen" gemäß des LAMA-Regelwerks durch
     * @param benutzername Benutzername des Spielers, der den Spielzug durchführt
     * @param karte Integer mit Wert 0,1,2,3,4,5 oder 6, repräsentiert die abzulegende Karte
     * @throws ungueltigerBenutzernameException Wenn kein Spieler mit Benutzernamen benutzername sich in der Spielrunde
     * befindet
     * @throws ungueltigerSpielzugException Wenn der Spielzug gegen die LAMA-Regeln verstößt
     */
    public void karteAblegen(String benutzername, int karte) throws ungueltigerBenutzernameException,
            ungueltigerSpielzugException{
        if(!spielerInRunde.contains(benutzername)) throw new ungueltigerBenutzernameException("Kein Spieler mit " +
                "Übergenen Benutzernamen in der Spielrunde");
        //Teste ob Spielzug gueltig ist
        

        //TODO
    }


    /**
     * Führt den Spielzug "Karte ziehen" gemäß des LAMA-Regelwerks durch
     * @param benutzername Benutzername des Spielers, der den Spielzug durchführt
     * @return Integer mit Wert zwischne 0 und 6, welcher den Wert der gezogenen Karte darstellt
     * @throws ungueltigerBenutzernameException Wenn kein Spieler mit Benutzernamen benutzername sich in der Spielrunde
     * befindet
     * @throws ungueltigerSpielzugException Wenn der Spielzug gegen die LAMA-Regeln verstößt
     */
    public int karteZiehen(String benutzername) throws ungueltigerSpielzugException, ungueltigerBenutzernameException{
        //TODO
    return 0;
    }


    /**
     * Führt den Spielzug "Austeigen" gemäß des LAMA-Regelwerks durch
     * @param benutzername Benutzername des Spielers, der den Spielzug durchführt
     * @throws ungueltigerBenutzernameException Wenn kein Spieler mit Benutzernamen benutzername sich in der Spielrunde
     * befindet
     * @throws ungueltigerSpielzugException Wenn der Spielzug gegen die LAMA-Regeln verstößt
     */
    public void aussteigen(String benutzername) throws ungueltigerSpielzugException, ungueltigerBenutzernameException{
        //TODO
    }


    /**
     * Führt den Spielzug "Chip tauschen" gemäß des LAMA-Regelwerks durch
     * @param zehngegeneins Boolean != null. Wenn true, wird ein 10er Chip gegen 10 1er Chips getauscht, wenn false,
     *                      umgekehrt
     * @param benutzername Benutzername des Spielers, der den Spielzug durchführt
     * @throws ungueltigerBenutzernameException Wenn kein Spieler mit Benutzernamen benutzername sich in der Spielrunde
     * befindet
     * @throws ungueltigerSpielzugException Wenn der Spielzug gegen die LAMA-Regeln verstößtn
     */
    public void chipsTauschen(boolean zehngegeneins, String benutzername) throws ungueltigerBenutzernameException,
            ungueltigerSpielzugException {
        //TODO
    }


    /**
     * Führt den Spielzug "Chip tauschen" gemäß des LAMA-Regelwerks durch
     * @param zehnerchip Boolean != null. Wenn true, wird ein 10er Chip abgegeben, wenn false, ein 1er Chip
     * @param benutzername Benutzername des Spielers, der den Spielzug durchführt
     * @throws ungueltigerBenutzernameException Wenn kein Spieler mit Benutzernamen benutzername sich in der Spielrunde
     *                                          befindet
     * @throws ungueltigerSpielzugException Wenn der Spielzug gegen die LAMA-Regeln verstößt
     */
    public void chipAbgeben(boolean zehnerchip, String benutzername) throws ungueltigerBenutzernameException,
            ungueltigerSpielzugException{
        //TODO
    }


    /**
     * Methode dient um abzufragen, welcher Spieler an der Reihe ist
     * @return Benutzername des Spielers, welcher am Zug ist
     */
    public String anDerReihe(){
        //TODO
        return "TODO";
    }


    /**
     * Die Methode dient zum Starten des Spiels. Es werden alle Karten ausgeteilt
     * @throws spielLaeuftBereitsException Wenn Spiel bereits gestartet und noch nicht beendet wurde
     * @throws zuWenigSpielerException Wenn weniger als zwei Spieler sich in der Spielrunde befinden
     */
    public void spielStarten() throws spielLaeuftBereitsException, zuWenigSpielerException{
        //TODO
    }


    /**
     * Getter-Methode für die Handkarten eines bestimmten Spielers
     * @param benutzername Benutzername des Spielers, dessen Handkarten ausgegeben werden sollen
     * @return ArrayList welche Integer zwischen 0 und 6 enthält
     * @throws ungueltigerBenutzernameException Wenn kein Spieler mit Benutzernamen benutzername sich in der Spielrunde
     *                                          befindet
     */
    public ArrayList<Integer> getHandkarten(String benutzername) throws ungueltigerBenutzernameException{
        return handkartenSpieler.get(benutzername);
    }


    /**
     * Getter-Methode für den Ablagestapel
     * @return Stack, welcher Integer zwischen 0 und 6 enthält
     */
    public Stack<Integer> getAblagestapel() {
        return ablagestapel;
    }


    /**
     * Getter-Methode für den Nachziehstapel
     * @return Stack, welcher Integer zwischen 0 und 6 enthält
     */
    public Stack<Integer> getNachziehstapel() {
        return nachziehstapel;
    }


    /**
     * Die Methode dient zur Verwaltung des Chipstapels
     * @param benutzername Benutzername des Spielers, dessen Chipstapel verwaltet wird
     * @return gibt den Chipstapel mit der richtigen Anzahl weißer und schwarzer Chips zurück
     * @throws ungueltigerBenutzernameException Wenn kein Spieler mit Benutzernamen benutzername sich in der Spielrunde
     *                                          befindet
     */
    public Chipstapel getChipstapel(String benutzername) throws ungueltigerBenutzernameException{
        //TODO
        return new Chipstapel();
    }


    /**
     * Entfernt den Benutzer mit übergebenen Benutzernamen aus der Spielrunde. Handkarten und Chipstapel werden eben-
     * falls entfernt.
     * @param benutzername Benutzername des Benutzer, der entfernt werden soll
     * @throws ungueltigerBenutzernameException Wenn kein Spieler mit Benutzernamen benutzername sich in der Spielrunde
     *                                          befindet
     */
    public void spielraumVerlassen(String benutzername) throws ungueltigerBenutzernameException {
        //TODO
    }


    /**
     * Hilfsmethode. Mischt Karten, teilt sie an die Spieler aus und erstellt Ablagestapel und Nachziehstapel gemäß
     * des LAMA-Regelwerks
     */
    private void kartenAusteilen(){
        ArrayList<Integer> alleSpielkarten = new ArrayList<>();
        //Erzeuge alle Karten
        for(int i = 0;i<8;i++){
            alleSpielkarten.add(0);             //0 repräsentiert eine Lama-Karte
            alleSpielkarten.add(1);             //1-6 repräsentiert jeweiligen Kartenwert
            alleSpielkarten.add(2);
            alleSpielkarten.add(3);
            alleSpielkarten.add(4);
            alleSpielkarten.add(5);
            alleSpielkarten.add(6);
        }
        //Karten mischen
        Random random = new Random();
        for(int i = 0; i< alleSpielkarten.size()-1; i++){
            int rndindex= random.nextInt(alleSpielkarten.size()-1);
            int temp=alleSpielkarten.get(i);
            alleSpielkarten.set(i,alleSpielkarten.get(rndindex));
            alleSpielkarten.set(rndindex,temp);
        }

        int indexNaechsteKarte= 0;
        //Karten austeilen
        for(String benutzername: spielerInRunde){
            ArrayList<Integer> ausgeteilteHandkarten = new ArrayList<>();
            for (int i = 0 ; i < 6; i++){
                ausgeteilteHandkarten.add(alleSpielkarten.get(indexNaechsteKarte++));
            }
            handkartenSpieler.put(benutzername,ausgeteilteHandkarten);
        }
        ablagestapel.push(indexNaechsteKarte++);
        while (indexNaechsteKarte < alleSpielkarten.size()-1){
            nachziehstapel.push(alleSpielkarten.get(indexNaechsteKarte++));
        }
    }


    private void spielzugAbschliessen(){
        //Wenn Spielrunde abgeschlossen wurde
        if(spielerInRunde.size() == ausgestiegeneSpieler.size() ||
                handkartenSpieler.get(spielerInRunde.get(amZugIndex)).size() == 0){
            //TODO Minuspunkte verteilen, nächste Runde starten
        }else{
            //nächster Spieler an der Reihe ermitteln
            amZugIndex = amZugIndex + 1 % spielerInRunde.size();
            while(ausgestiegeneSpieler.contains(spielerInRunde.get(amZugIndex))){
                amZugIndex = amZugIndex + 1 % spielerInRunde.size();
            }

        }
    }

}
