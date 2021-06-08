package Spiel;
/**
 * Die Klasse verwaltet den Chipstapel aus weißen und schwarzen Chips eines Spielers.
 *
 * @author Catharina Helten
 */
class Chipstapel {
    private int white;
    private int black;


    public int getWeiss(){
        return white;
    }

    public int getSchwarz(){
        return black;
    }

    public int getPunkte(){
        return white + 10*black;
    }

    public void setWeiss(int anzahlchips){
        this.white = anzahlchips;
    }

    public void setSchwarz(int anzahlchips){
    this.black = anzahlchips;
    }
}
