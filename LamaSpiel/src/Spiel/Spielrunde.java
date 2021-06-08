package Spiel;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Stack;





/**
 * Die Klasse verwaltet die Spielrunde mit den Funktionalitäten des Spielers in Bezug auf Karten/Chips.
 *
 * @author Catharina Helten
 */

public class Spielrunde extends Chipstapel {

    private int spielraumId;
    private HashSet<Integer> handkarten;
    private Stack<Integer> ablagestapel;
    private Stack<Integer> nachziehstapel;
    private Chipstapel chipstapel = new Chipstapel();

    /**
     * Die Methode dient zum Abrufen der Spielraum-ID
     * @return gibt die ID als Integer zurück
     */

    public Spielrunde(int Id){
        this.spielraumId = Id;
    }

    public int getRaumId(){
        return spielraumId;
    }


    /**
     * Die Methode dient zum Karte ablegen eines Spielers
     * @param benutzername Benutzername des Spielers, dessen Karte abgelegt werden soll
     * @param karte Karte, die abgelegt werden soll dargestellt als Integer-Wert
     */
    public void karteAblegen(String benutzername, int karte){
        // throws Spielraum-Exception wenn zu wenig Karten
        try {
            handkarten = getHandkarten(benutzername);
            handkarten.remove(karte);
            ablagestapel = getAblagestapel();
            ablagestapel.push(karte);
        }
        catch (Exception e){

        }
    }


    /**
     * Die Methode dient zum Ziehen einer Karte
     * @param benutzername Benutzername des Spielers, der die Karte zieht
     * @return gibt die gezogene Karte als Integer-Wert zurück
     */
    public int karteZiehen(String benutzername){
        //throws Spielraum-Exception
        try {
            nachziehstapel = getNachziehstapel();
            handkarten = getHandkarten(benutzername);
            int karte = nachziehstapel.pop();
            handkarten.add(karte);
            return karte;
        }
        catch (Exception e){

        }
    return 0;
    }



    /**
     * Die Methode dient zum Aussteigen bei einer Runde
     * @param benutzername Benutzername des Spielers, der aussteigen möchte
     */
    public void aussteigen(String benutzername){

    }




    /**
     * Die Methode dient zum Tauschen von Chips
     * @param zehngegeneins stell dar, ob ein Zehnerchip gegen einen Einserchip getauscht wird oder andersrum
     * @param benutzername Benutzername des Spielers, der die Chips tauschen möchte
     */
    public void chipsTauschen(boolean zehngegeneins, String benutzername) {
        // throws Spielraum-Exception: Stapel ist leer, Chipabgabe nicht möglich ->
        // wenn entweder zu wenig weiße oder zu wenig schwarze
    chipstapel = getChipstapel(benutzername);
    int white = chipstapel.getWeiss();
    int black = chipstapel.getSchwarz();
    try {
        if (zehngegeneins) {
            chipstapel.setSchwarz(black - 1);
            chipstapel.setWeiss(white + 1);
        }
        chipstapel.setWeiss(white - 1);
    }
    catch(Exception e){
        }

    }




    /**
     * Die Methode dient zum Abgeben eines Chips
     * @param zehnerchip stellt dar, ob ein Zehner oder Einserchip abgegeben wird
     * @param benutzername Benutzername des Spielers, der den Chip abgeben möchte
     */
    public void chipAbgeben(boolean zehnerchip, String benutzername){
        // throws Spielraum-Exception: Stapel ist leer, Chipabgabe nicht möglich ->
        // wenn zu wenig weiße oder zu wenig schwarze
        chipstapel = getChipstapel(benutzername);
        int white = chipstapel.getWeiss();
        int black = chipstapel.getSchwarz();
        try {
            if (zehnerchip) {
                chipstapel.setSchwarz(black - 1);
            }
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
        return null;
    }


    /**
     * Die Methode dient zum Starten des Spiels
     */
    public void spielStarten(){

    }


    /**
     * Die Methode dient zur Verwaltung des Handkartenstapels
     * @param benutzername Benutzername des Spielers, dessen Stapel verwaltet wird
     * @return gibt den Stapel als ungeordnetes HashSet aus Integer-Werten der Karten zurück
     */
    public HashSet<Integer> getHandkarten(String benutzername){
        return handkarten;
    }

    /**
     * Die Methode dient zur Verwaltung des Ablagestapels
     * @return gibt den Stapel als Stack aus Integer-Werten der Karten zurück
     */

    public Stack<Integer> getAblagestapel(){
        return ablagestapel;
    }


    /**
     * Die Methode dient zur Verwaltung des Nachziehstapels
     * @return gibt den Stapel als Stack aus Integer-Werten der Karten zurück
     */
    public Stack<Integer> getNachziehstapel(){
        return nachziehstapel;
    }

    /**
     * Die Methode dient zur Verwaltung des Chipstapels
     * @param benutzername Benutzername des Spielers, dessen Chipstapel verwaltet wird
     * @return gibt den Chipstapel mit der richtigen Anzahl weißer und schwarzer Chips zurück
     */

    public Chipstapel getChipstapel(String benutzername){

        int white = chipstapel.getWeiss();
        int black = chipstapel.getSchwarz();
        chipstapel.setWeiss(white);
        chipstapel.setSchwarz(black);
        return chipstapel;
    }



    /**
     * Die Methode dient zum Verlassen des Spielraumes
     * @param benutzername Benutzername des Spielers, der verlassen möchte
     */
    public void spielraumVerlassen(String benutzername){

    }


}
