
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

    /*@Test (expected = benutzerNameVergebenException.class )
    void sollBenutzerErstellen() throws EmailVergebenException, benutzerNameVergebenException {
        benutzerVerwalten.benutzerRegistrieren("x","x","xxx");
        assertFalse(benutzerVerwalten.returnAllPlayer().isEmpty());
        assertEquals(1,benutzerVerwalten.returnAllPlayer().size());
    }*/

    @Test
    void benutzerRegistrieren() throws EmailVergebenException, benutzerNameVergebenException {
        benutzerVerwalten.benutzerRegistrieren("Samu","samu123","Samu@gmail.com");
        benutzerVerwalten.benutzerRegistrieren("Laura","Laurita","Laura.schmitt@gmail.com");
        assertFalse(benutzerVerwalten.returnAllPlayer().isEmpty());
        assertEquals(2,benutzerVerwalten.returnAllPlayer().size());
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
    void benutzerdatenPruefen() throws EmailVergebenException, benutzerNameVergebenException {
        benutzerVerwalten.benutzerRegistrieren("Samu","samu123","Samu@gmail.com");
        benutzerVerwalten.benutzerRegistrieren("Laura","Laurita","Laura.schmitt@gmail.com");
        assertEquals(2,benutzerVerwalten.returnAllPlayer().size());
        assertEquals(true,benutzerVerwalten.benutzerdatenPruefen("Samu","samu123"));
        assertEquals(false,benutzerVerwalten.benutzerdatenPruefen("Laura","Laurita1"));
    }

}