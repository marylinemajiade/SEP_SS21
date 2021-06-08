package Konto;

/**
 *
 * this class is for managing player information (creating, deleting Accounts).
 *
 * @author Hamza Bariane
 *
 */
public class Benutzerdaten {

    private String benutzername;
    private String Passwort;

    public Benutzerdaten(String benutzername, String passwort) {
        this.benutzername = benutzername;
        this.Passwort = passwort;
    }

    /**
     * this methode is used to register a new Player in the game.
     *
     * @param benutzername username for the player.
     * @param passwort passwort for the account.
     */
    void benutzerRegistrieren(String benutzername, String passwort){}

    /**
     * This methode is used to delete the player account, using just a username (the player is already connected).
     *
     * @param benutzername username for the player.
     */
    void benutzerLoeschen(String benutzername){}

    /**
     * This methode is used to check the validity of the information given by the Player before the login.
     *
     * @param benutzername username for the player.
     * @param passwort the password to login.
     * @return a boolean, comparing the login-information with the information given during the Registration.
     */
    boolean ueberpruefeBenutzerdaten(String benutzername, String passwort){return false;}

    /**
     * These are the Setters and the Getters for the username and password.
     * (they are declared as private, to prevent access from other classes).
     *
     */

    public String getBenutzername() {
        return benutzername;
    }

    public void setBenutzername(String benutzername) {
        this.benutzername = benutzername;
    }
    public String getPasswort() {
        return Passwort;
    }

    public void setPasswort(String passwort) {
        Passwort = passwort;
    }

}
