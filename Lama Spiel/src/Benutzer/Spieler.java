package Benutzer;

/**
 * This Class defines all the methods, that a player needs during the game.
 *
 * @author Hamza Bariane
 *
 */

public class Spieler extends Konto{

    public Spieler(String benutzername, String passwort) {
        super(benutzername, passwort);
    }

    void erstelleKonto(String benutzername ,String passwort){

    }

    void löscheKonto(String benutzername, String passwort){

    }

    void spielraumErstellen(int anzahlSpieler){

    }

    void botHinzufügen(){} // wir brauchen dazu ein Typparameter (botType Klasse Bot)

    void botEntfernen(){}// wir brauchen dazu ein Typparameter (botType Klasse Bot)

    void spieleraumBetreten(){}

    void spileraumVerlassen(){}

    void chatBenutzen(){}

}
