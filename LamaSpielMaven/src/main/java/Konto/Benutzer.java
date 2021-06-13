package Konto;

import java.io.Serializable;

/**
 *
 * Diese Klasse ist für die Benutzerdaten, die jeder Spieler für die Registrierung und Login braucht.
 *
 * @author Hamza Bariane
 *
 */
public class Benutzer implements Serializable {

    private String benutzername;
    private String passwort;

    public Benutzer() {
    }

    public Benutzer(String benutzername, String passwort) {
        this.benutzername = benutzername;
        this.passwort = passwort;
    }
    /**
     * These are the Setters and the Getters for the username, password and email.
     * (they are declared as private, to prevent access from other classes).
     *
     */

    public void setBenutzername(String benutzername) {
        this.benutzername = benutzername;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    public String getBenutzername() {
        return benutzername;
    }

    public String getPasswort() {
        return passwort;
    }

    @Override
    public String toString() {
        return "Benutzer{" +
                "benutzername='" + benutzername + '\'' +
                ", passwort='" + passwort + '\'' +
                '}';
    }
}