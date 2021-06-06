package Konto;

/**
 *
 * this class is for managing player information (creating, deleting Accounts).
 *
 * @author Hamza Bariane
 *
 */
public class Benutzerdaten {

    String benutzername;
    private String Passwort;

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
     * These are the Setter and the Getter for the password.
     * (it is declared as private, to prevent access from other classes).
     *
     */
    public String getPasswort() {
        return Passwort;
    }

    public void setPasswort(String passwort) {
        Passwort = passwort;
    }

}
