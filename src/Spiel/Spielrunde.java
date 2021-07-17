package Spiel;

import SpielLobby.Lobby;
import fachlicheExceptions.spielLaeuftBereitsException;
import fachlicheExceptions.ungueltigerBenutzernameException;
import fachlicheExceptions.ungueltigerSpielzugException;
import fachlicheExceptions.zuWenigSpielerException;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

/**
 * Die Klasse verwaltet ein LAMA-Spiel, bietet Getter-Methoden für alle im Spiel benötigten Ressourcen (Karten, Chips)
 * und bietet Methoden um alle Spielzüge gemäß des LAMA-Regelwerks durchzuführen
 *
 * @author Catharina Helten und Nick Jochum
 */

public class Spielrunde extends Chipstapel implements Serializable,spielrundeIF {


    //Attribute
    public ArrayList<String> spielerInRunde = new ArrayList<>();
    private final int spielraumId;
    private HashMap<String,ArrayList<Integer>> handkartenSpieler = new HashMap<>();
    private Stack<Integer> ablagestapel = new Stack<>();
    private Stack<Integer> nachziehstapel = new Stack<>();
    private HashMap<String,Chipstapel> chipstapelSpieler = new HashMap<>();
    private HashSet<String> ausgestiegeneSpieler = new HashSet<>();

    public int getAmZugIndex() {
        return amZugIndex;
    }

    public void setAmZugIndex(int amZugIndex) {
        this.amZugIndex = amZugIndex;
    }

    private int amZugIndex=0;
    private int beginntNaechstenDurchgang = 1;
    private boolean spiellaeuft = false;


    public Spielrunde remoteSpielrunde;
    //Konstruktor
    public Spielrunde(int id, Lobby lobby) throws RemoteException {
        this.spielraumId = id;
        //this.remoteSpielrunde = (Spielrunde) UnicastRemoteObject.exportObject(this, 1099);
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
                "übergenen Benutzernamen in der Spielrunde");
        //Teste ob Spielzug ungueltig ist
        if (!spielerInRunde.get(amZugIndex).equals(benutzername) ||
                spielerInRunde.size()-ausgestiegeneSpieler.size() < 2 ||
                (ablagestapel.peek() != karte && ablagestapel.peek() + 1 % 7 != karte ) ||
                !handkartenSpieler.get(benutzername).contains(karte) || !spiellaeuft){
            throw new ungueltigerSpielzugException("Spielzug ist ungueltig");
        }
        //Führe Spielzug durch
        handkartenSpieler.get(benutzername).remove(karte);
        ablagestapel.push(karte);
        spielzugAbschliessen();
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
        if(!spielerInRunde.contains(benutzername)) throw new ungueltigerBenutzernameException("Kein Spieler mit " +
                "übergenen Benutzernamen in der Spielrunde");
        //Teste ob Spielzug ungueltig ist
        if(!spielerInRunde.get(amZugIndex).equals(benutzername) || nachziehstapel.empty() || !spiellaeuft){
            throw new ungueltigerSpielzugException("Spielzug ist ungueltig");
        }
        //Führe Spielzug durch
        int karte = nachziehstapel.pop();
        handkartenSpieler.get(benutzername).add(karte);
        spielzugAbschliessen();
        return karte;
    }


    /**
     * Führt den Spielzug "Austeigen" gemäß des LAMA-Regelwerks durch
     * @param benutzername Benutzername des Spielers, der den Spielzug durchführt
     * @throws ungueltigerBenutzernameException Wenn kein Spieler mit Benutzernamen benutzername sich in der Spielrunde
     * befindet
     * @throws ungueltigerSpielzugException Wenn der Spielzug gegen die LAMA-Regeln verstößt
     */
    public void aussteigen(String benutzername) throws ungueltigerSpielzugException, ungueltigerBenutzernameException{
        if(!spielerInRunde.contains(benutzername)) throw new ungueltigerBenutzernameException("Kein Spieler mit " +
                "übergenen Benutzernamen in der Spielrunde");
        //Teste ob Spielzug ungueltig ist
        if(!spielerInRunde.get(amZugIndex).equals(benutzername) || !spiellaeuft){
            throw new ungueltigerSpielzugException("Spielzug ist ungueltig");
        }
        //Führe Spielzug aus
        ausgestiegeneSpieler.add(benutzername);
        spielzugAbschliessen();
    }


    /**
     * Führt den Spielzug "Chip tauschen" gemäß des LAMA-Regelwerks durch
     * @param zehnergegeneiner Boolean != null. Wenn true, wird ein 10er Chip gegen 10 1er Chips getauscht, wenn false,
     *                      umgekehrt
     * @param benutzername Benutzername des Spielers, der den Spielzug durchführt
     * @throws ungueltigerBenutzernameException Wenn kein Spieler mit Benutzernamen benutzername sich in der Spielrunde
     * befindet
     * @throws ungueltigerSpielzugException Wenn der Spielzug gegen die LAMA-Regeln verstößtn
     */
    public void chipsTauschen(boolean zehnergegeneiner, String benutzername) throws ungueltigerBenutzernameException,
            ungueltigerSpielzugException {
        if(!spielerInRunde.contains(benutzername)) throw new ungueltigerBenutzernameException("Kein Spieler mit " +
                "übergenen Benutzernamen in der Spielrunde");
        Chipstapel chipstapel = chipstapelSpieler.get(benutzername);
        //Teste ob Spielzug gueltig ist
        if((chipstapel.getWeiss() < 10 && !zehnergegeneiner) || (chipstapel.getSchwarz()<1 && zehnergegeneiner
                || !spiellaeuft)){
            throw new ungueltigerSpielzugException("Spielzug ist ungueltig");
        }
        //Führe Tausch durch
        if(zehnergegeneiner){
            chipstapel.setSchwarz(chipstapel.getSchwarz() -1);
            chipstapel.setWeiss(chipstapel.getWeiss() + 10);
        }else{
            chipstapel.setSchwarz(chipstapel.getSchwarz() +1);
            chipstapel.setWeiss(chipstapel.getWeiss() -10);
        }
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
        if(!spielerInRunde.contains(benutzername)) throw new ungueltigerBenutzernameException("Kein Spieler mit " +
                "übergenen Benutzernamen in der Spielrunde");
        //Teste ob Spielzug gueltig ist
        Chipstapel chipstapel = chipstapelSpieler.get(benutzername);
        if((zehnerchip && chipstapel.getSchwarz() < 1) ||(!zehnerchip && chipstapel.getWeiss() < 1) || !spiellaeuft
        || !handkartenSpieler.get(benutzername).isEmpty()) {
            throw new ungueltigerSpielzugException("Spielzug ist ungueltig");
        }
        //Gebe Chip ab
        if(zehnerchip) chipstapel.setSchwarz(chipstapel.getSchwarz()-1);
        else chipstapel.setWeiss(chipstapel.getWeiss()-1);
        //Spieldurchgang wird abgeschlossen
        spieldurchgangAbschliessen();

    }


    /**
     * Methode dient um abzufragen, welcher Spieler an der Reihe ist
     * @return Benutzername des Spielers, welcher am Zug ist
     */
    public String anDerReihe(){
        return spielerInRunde.get(amZugIndex);
    }


    /**
     * Die Methode dient zum Starten des Spiels. Es werden alle Karten ausgeteilt und die Chipstapel initialisiert
     * @throws spielLaeuftBereitsException Wenn Spiel bereits gestartet und noch nicht beendet wurde
     * @throws zuWenigSpielerException Wenn weniger als zwei Spieler sich in der Spielrunde befinden
     */
    public void spielStarten() throws spielLaeuftBereitsException, zuWenigSpielerException{
        if (spielerInRunde.size() < 2) throw new zuWenigSpielerException("Nicht genügend Spieler in Spielrunde");
        if(spiellaeuft) throw new spielLaeuftBereitsException("Spiel läuft bereits");
        spiellaeuft= true;
        //Setze Attribute zurück
        handkartenSpieler = new HashMap<>();
        ablagestapel = new Stack<>();
        nachziehstapel = new Stack<>();
        chipstapelSpieler = new HashMap<>();
        ausgestiegeneSpieler = new HashSet<>();
        amZugIndex=0;
        beginntNaechstenDurchgang = 0;
        //Initialisiere Chipstapel
        for (String benutzername :spielerInRunde){
            Chipstapel chipstapel = new Chipstapel();
            chipstapel.setSchwarz(0);
            chipstapel.setWeiss(0);
            chipstapelSpieler.put(benutzername,chipstapel);
        }
        kartenAusteilen();
    }


    /**
     * Getter-Methode für die Handkarten eines bestimmten Spielers
     * @param benutzername Benutzername des Spielers, dessen Handkarten ausgegeben werden sollen
     * @return ArrayList welche Integer zwischen 0 und 6 enthält
     * @throws ungueltigerBenutzernameException Wenn kein Spieler mit Benutzernamen benutzername sich in der Spielrunde
     *                                          befindet
     */
    public ArrayList<Integer> getHandkarten(String benutzername) throws ungueltigerBenutzernameException{
        if(!spielerInRunde.contains(benutzername)) throw new ungueltigerBenutzernameException("Kein Spieler mit " +
                "übergenen Benutzernamen in der Spielrunde");
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
     * Getter-Methode für den handkartenSpieler
     * @return HashMap, welcher die Handkarten von jedem Spieler enthält
     */
    public HashMap<String, ArrayList<Integer>> getHandkartenSpieler() {
        return handkartenSpieler;
    }


    /**
     * Die Methode dient zur Verwaltung des Chipstapels
     * @param benutzername Benutzername des Spielers, dessen Chipstapel verwaltet wird
     * @return gibt den Chipstapel mit der richtigen Anzahl weißer und schwarzer Chips zurück
     * @throws ungueltigerBenutzernameException Wenn kein Spieler mit Benutzernamen benutzername sich in der Spielrunde
     *                                          befindet
     */
    public Chipstapel getChipstapel(String benutzername) throws ungueltigerBenutzernameException{
        if(!spielerInRunde.contains(benutzername)) throw new ungueltigerBenutzernameException("Kein Spieler mit " +
                "übergenen Benutzernamen in der Spielrunde");
        return chipstapelSpieler.get(benutzername);
    }


    /**
     * Entfernt den Benutzer mit übergebenen Benutzernamen aus der Spielrunde. Handkarten und Chipstapel werden eben-
     * falls entfernt.
     * @param benutzername Benutzername des Benutzer, der entfernt werden soll
     * @throws ungueltigerBenutzernameException Wenn kein Spieler mit Benutzernamen benutzername sich in der Spielrunde
     *                                          befindet
     */
    public void spielraumVerlassen(String benutzername) throws ungueltigerBenutzernameException {
        if(!spielerInRunde.contains(benutzername)) throw new ungueltigerBenutzernameException("Kein Spieler mit " +
                "übergenen Benutzernamen in der Spielrunde");
        boolean spielerIstAmZug = (benutzername.equals(spielerInRunde.get(amZugIndex)));
        handkartenSpieler.remove(benutzername);
        chipstapelSpieler.remove(benutzername);
        spielerInRunde.remove(benutzername);
        if (spielerIstAmZug) spielzugAbschliessen();
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
            alleSpielkarten.add(1);             //1-6 repräsentiert den jeweiligen Kartenwert
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

    /**
     * Ermittelt nächsten Spieler, welcher am Zug ist und schließt gegebenenfalls den Spieldurchgang ab.
     */
    private void spielzugAbschliessen(){
        //Wenn Spielrunde abgeschlossen wurde
        if(spielerInRunde.size() == ausgestiegeneSpieler.size() ||
                handkartenSpieler.get(spielerInRunde.get(amZugIndex)).size() == 0){
            //Wenn kein Chip abgegeben werden muss, starte nächsten Durchgang
            Chipstapel chipstapel = chipstapelSpieler.get(spielerInRunde.get(amZugIndex));
            if(handkartenSpieler.get(spielerInRunde.get(amZugIndex)).size() != 0
                    || chipstapel.getWeiss() + chipstapel.getSchwarz() == 0){
                spieldurchgangAbschliessen();
            } //Andernfalls wird Spieldurchgang abgeschlossen, nachdem entsprechender Spieler einen Chip abgegeben hat
        }else{
            //nächster Spieler an der Reihe ermitteln
            amZugIndex = amZugIndex + 1 % spielerInRunde.size();
            while(ausgestiegeneSpieler.contains(spielerInRunde.get(amZugIndex))){
                amZugIndex = amZugIndex + 1 % spielerInRunde.size();
            }
        }
    }

    /**
     * Hilfsmethode. Minuspuktechips werden verteilt und gegebenenfalls wird ein neuer Spieldurchgang gestartet und
     * Karten ausgeteilt. Wird am Ende eines Spieldurchgangs aufgerufen, nachdem ggf. ein Chip abgegeben wurde
     */
    private void spieldurchgangAbschliessen(){
        boolean spielZuEnde = false;
        //Berechne Minuspunkte aller Spieler und füge sie den jeweiligen Chipstapel hinzu
        for(String benutzername: spielerInRunde){
            Chipstapel chipstapel = chipstapelSpieler.get(benutzername);
            ArrayList<Integer> handkarten = handkartenSpieler.get(benutzername);
            int minuspunkte = 0;
            for(Integer handkarte: handkarten){
                if(handkarte == 0) minuspunkte += 10;
                else minuspunkte +=handkarte;
            }
            chipstapel.setSchwarz(chipstapel.getSchwarz() + minuspunkte / 10);
            chipstapel.setWeiss(chipstapel.getWeiss()+ minuspunkte % 10);
            if(chipstapel.getPunkte() >= 40) spielZuEnde = true;
        }
        if(spielZuEnde) spiellaeuft = false;
        else {
            kartenAusteilen();
            amZugIndex= beginntNaechstenDurchgang;
            beginntNaechstenDurchgang = beginntNaechstenDurchgang +1 % spielerInRunde.size();
        }
    }

}
