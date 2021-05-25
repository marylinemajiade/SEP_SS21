package Benutzer;

import java.net.PasswordAuthentication;

/**
 * This Class is for creating a new account in the game.
 *
 * @author Hamza Bariane
 *
 */

public class Konto {

    private String benutzername;
    private String passwort;

    /**
     * this is the constructor fro the class Konto.
     * @param benutzername username for the player.
     * @param passwort the password to login.
     */
    public Konto(String benutzername, String passwort) { // Konstrukt für die Klasse Konto, dafür brauchen wir die Parameter benutzername, und passwort
        this.benutzername = benutzername;
        this.passwort = passwort;
    }

    /**
     * These are the Setters and Getters for the Parameters: benutzername and passwort.
     * (they are declared as private, to prevent access from other classes)
     *
     */
    public String getBenutzername() {
        return this.benutzername;
    }

    public String getPasswort() {
        return this.passwort;
    }

    public void setBenutzername(String benutzername) {
        this.benutzername = benutzername;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    /**
     * This methode is used to check the validity of the information given by the Player before the login.
     * @param benutzername username for the player.
     * @param passwort the password to login.
     * @return a boolean, comparing the login-information with the information given during the Registration.
     */
    public boolean überprüfeEingabe(String benutzername, String passwort){
        return false;
    }


}
