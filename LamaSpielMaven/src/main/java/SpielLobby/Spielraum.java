package SpielLobby;

import GUI.Chat;
import Highscore.Bestenliste;
import Konto.Benutzer;
import Spiel.Chipstapel;
import Spiel.Spielrunde;

import java.util.List;

public class Spielraum {
    Chipstapel chipstapel;
    Bestenliste bestenliste;
    Benutzer benutzer;
    int id;
    boolean started;


    public Spielraum(Integer id) {
        this.chipstapel = new Chipstapel();
        this.bestenliste = new Bestenliste();
        this.benutzer = new Benutzer();
        this.id = id;
        this.started = false;


    }

    public void benutzerHinzufuegen(String benutzername){
        //ToDo
    }

    public List<Benutzer> spielerList(){
        //ToDo
        return null;
    }

    public boolean spielStarten(){
        return false;
    }
    public void spielerEntfernen(String benutzername){
        //ToDo
    }
}
