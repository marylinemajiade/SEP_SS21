package Spiel;

public class Chipstapel {
    private int white;
    private int black;
    public int getWeiss(){
        return white;
    }

    public int getSchwarz(){
        return black;
    }

    public int getPunkte(){
        // schwarz 10, weiß 1
        return white + 10*black;
    }

    public void setWeiss(int anzahlchips){
        this.white = anzahlchips;
    }

    public void setSchwarz(int anzahlchips){
    this.black = anzahlchips;
    }
}
