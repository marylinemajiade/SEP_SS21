
import Konto.Benutzer;
import Konto.BenutzerVerwalten;
import fachlicheExceptions.EmailVergebenException;
import fachlicheExceptions.benutzerNameVergebenException;
import fachlicheExceptions.ungueltigerBenutzernameException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author Hamza Bariane
 */

class BenutzerVerwaltenTest {

    private BenutzerVerwalten benutzerVerwalten;

    @BeforeEach
    void setUp() {
        System.out.println("Instatiierung der Klasse BenutzerVerwalten");
        benutzerVerwalten = new BenutzerVerwalten();
    }

    @Test
    void benutzerRegistrieren() throws EmailVergebenException, benutzerNameVergebenException {
        benutzerVerwalten.benutzerRegistrieren("Samu","samu123","Samu@gmail.com");
        benutzerVerwalten.benutzerRegistrieren("Laura","Laurita","Laura.schmitt@gmail.com");
        assertFalse(benutzerVerwalten.returnAllPlayer().isEmpty());
        assertEquals(2,benutzerVerwalten.returnAllPlayer().size());
    }

    @Test
    void benutzerNameVergebenExceptionTest() throws EmailVergebenException, benutzerNameVergebenException {
        benutzerVerwalten.benutzerRegistrieren("Samu","samu123","Samu@gmail.com");
        assertFalse(benutzerVerwalten.returnAllPlayer().isEmpty());
        assertEquals(1,benutzerVerwalten.returnAllPlayer().size());
        assertThrows(benutzerNameVergebenException.class , () -> benutzerVerwalten.benutzerRegistrieren("Samu","samu12","Samu1@gmail.com"));
    }

    @Test
    void EmailVergebenExceptionTest() throws EmailVergebenException, benutzerNameVergebenException {
        benutzerVerwalten.benutzerRegistrieren("Samu","samu123","Samu@gmail.com");
        assertFalse(benutzerVerwalten.returnAllPlayer().isEmpty());
        assertEquals(1,benutzerVerwalten.returnAllPlayer().size());
        assertThrows(EmailVergebenException.class , () -> benutzerVerwalten.benutzerRegistrieren("Samu1","samu12","Samu@gmail.com"));
    }

    @Test
    void benutzerLoeschen() throws EmailVergebenException, benutzerNameVergebenException, ungueltigerBenutzernameException {
        benutzerVerwalten.benutzerRegistrieren("Samu","samu123","Samu@gmail.com");
        benutzerVerwalten.benutzerRegistrieren("Laura","Laurita","Laura.schmitt@gmail.com");
        benutzerVerwalten.benutzerLoeschen("Samu");
        assertEquals(1,benutzerVerwalten.returnAllPlayer().size());
        benutzerVerwalten.benutzerRegistrieren("Samu","samu123","Samu@gmail.com");
        assertEquals(2,benutzerVerwalten.returnAllPlayer().size());
    }

    @Test
    void ungueltigerBenutzernameExceptionTest() throws EmailVergebenException, benutzerNameVergebenException {
        benutzerVerwalten.benutzerRegistrieren("Samu","samu123","Samu@gmail.com");
        assertFalse(benutzerVerwalten.returnAllPlayer().isEmpty());
        assertEquals(1,benutzerVerwalten.returnAllPlayer().size());
        assertThrows(ungueltigerBenutzernameException.class , () -> benutzerVerwalten.benutzerLoeschen("Samu1"));
    }

    @Test
    void benutzerdatenPruefen() throws EmailVergebenException, benutzerNameVergebenException {
        benutzerVerwalten.benutzerRegistrieren("Samu","samu123","Samu@gmail.com");
        benutzerVerwalten.benutzerRegistrieren("Laura","Laurita","Laura.schmitt@gmail.com");
        assertEquals(2,benutzerVerwalten.returnAllPlayer().size());
        assertEquals(true,benutzerVerwalten.benutzerdatenPruefen("Samu","samu123"));
        assertEquals(false,benutzerVerwalten.benutzerdatenPruefen("Laura","Laurita1"));
    }

}