package Konto;

import java.util.ArrayList;
import fachlicheExceptions.*;

/**
 * Diese Klasse dient dazu, Benutzerdaten zu verwalten.
 * Damit können Benutzerkontos erstellt und gelöscht werden.
 *
 * @Author Hamza Bariane
 */

public class BenutzerVerwalten {

    ArrayList<Benutzer> benutzerListe = new ArrayList<>();

    /**
     * this methode is used to register a new Player in the game.
     * it saves all the registred player in an Arraylist for easy access.
     * @param benutzername username for the player.
     * @param passwort passwort for the account.
     * @param email email
     */
    void benutzerRegistrieren (String benutzername, String passwort, String email)
            throws benutzerNameVergebenException, EmailVergebenException {

        Benutzer benutzer = new Benutzer(benutzername, passwort, email);

        for (Benutzer registeredBenutzer  : benutzerListe
             ) {
            if (registeredBenutzer.getBenutzername().equals(benutzer.getBenutzername())){
                throw new benutzerNameVergebenException();
            } else if(registeredBenutzer.getEmail().equals(benutzer.getEmail())){
                throw new EmailVergebenException();
            } else{
                benutzerListe.add(benutzer);
            }
        }
    }

    /**
     * This methode is used to delete the player account, using just a username (the player is already registered).
     *
     * @param benutzername username for the player.
     */
    void benutzerLoeschen(String benutzername){

        for (Benutzer registeredBenutzer: benutzerListe
             ) {
            if(registeredBenutzer.getBenutzername().equals(benutzername)){
                benutzerListe.remove(registeredBenutzer);
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
    boolean benutzerdatenPruefen(String benutzername, String passwort){

        boolean registered=false;

        for (Benutzer registeredBenutzer: benutzerListe){
            if(registeredBenutzer.getBenutzername().equals(benutzername)
                    && registeredBenutzer.getPasswort().equals(passwort)){
                registered=true;
            } else {registered=false;}
        }
        return registered;
    }

    ArrayList returnAllPlayer (){
        return benutzerListe;
    }
}
