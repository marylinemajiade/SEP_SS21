package Konto;

import java.util.ArrayList;
import java.util.Collection;

import fachlicheExceptions.*;

/**
 * Diese Klasse dient dazu, Benutzerdaten zu verwalten.
 * Damit können Benutzerkontos erstellt und gelöscht werden.
 *
 * @Author Hamza Bariane
 */

public class BenutzerVerwalten {

    ArrayList<Benutzer> benutzerListe = new ArrayList<Benutzer>();

    /**
     * this methode is used to register a new Player in the game.
     * it saves all the registred player in an Arraylist for easy access.
     * @param benutzername username for the player.
     * @param passwort passwort for the account.
     */
    public void benutzerRegistrieren(String benutzername, String passwort)
            throws benutzerNameVergebenException {

        Benutzer benutzer = new Benutzer(benutzername, passwort);
        boolean vergeben= false;

        for (Benutzer registeredBenutzer  : benutzerListe) {
            if (registeredBenutzer.getBenutzername().equals(benutzer.getBenutzername())){
                vergeben = true;
                throw new benutzerNameVergebenException();
            } else{
                vergeben = false;
            }
        }
        if(vergeben==false){
            benutzerListe.add(benutzer);
        }
    }

    /**
     * This methode is used to delete the player account, using just a username (the player is already registered).
     *
     * @param benutzername username for the player.
     */
    public void benutzerLoeschen(String benutzername)throws ungueltigerBenutzernameException {

        for (Benutzer registeredBenutzer: benutzerListe) {
            if(registeredBenutzer.getBenutzername().equals(benutzername)){
                benutzerListe.remove(registeredBenutzer);
            } else {
                throw new ungueltigerBenutzernameException("Benutzername existiert nicht");
            }
        }
    }

    /**
     * This methode is used to check the validity of the information given by the Player before the login.
     *
     * @param benutzername username for the player.
     * @param passwort the password to login.
     * @return a boolean, comparing the login-information with the information given during the Registration.
     */
    public boolean benutzerdatenPruefen(String benutzername, String passwort){

        boolean registered=false;

        for (Benutzer registeredBenutzer: benutzerListe){
            if(registeredBenutzer.getBenutzername().equals(benutzername)){
                if(registeredBenutzer.getPasswort().equals(passwort)){
                    registered=true;
                }
            }
        }
        return registered;
    }

    /**
     * this methode is just for testing.
     * @return the list of all the Players, who are registered.
     */
    public ArrayList<Benutzer> returnAllPlayer(){
        return (benutzerListe) ;
    }
}

