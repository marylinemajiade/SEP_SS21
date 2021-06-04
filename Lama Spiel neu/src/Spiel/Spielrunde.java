package Spiel;

import java.util.HashSet;
import java.util.Stack;

public class Spielrunde {

    private int spielraumId;
    private HashSet<Integer> handkarten;
    private Stack<Integer> ablagestapel;
    private Stack<Integer> nachziehstapel;
    private Chipstapel chipstapel = new Chipstapel();


    public int getRaumId(){
        return spielraumId;
    }

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

    }

    public void aussteigen(String benutzername){

    }

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

    public String anDerReihe(){
        return null;
    }

    public void spielStarten(){

    }

    public HashSet<Integer> getHandkarten(String benutzername){
        return handkarten;
    }

    public Stack<Integer> getAblagestapel(){
        return ablagestapel;
    }

    public Stack<Integer> getNachziehstapel(){
        return nachziehstapel;
    }

    public Chipstapel getChipstapel(String benutzername){

        int white = chipstapel.getWeiss();
        int black = chipstapel.getSchwarz();
        chipstapel.setWeiss(white);
        chipstapel.setSchwarz(black);
        return chipstapel;
    }

    public void spielraumVerlassen(String benutzername){

    }


}
