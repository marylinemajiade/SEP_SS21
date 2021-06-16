
import Konto.BenutzerVerwalten;
import fachlicheExceptions.benutzerNameVergebenException;
import fachlicheExceptions.ungueltigerBenutzernameException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


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
    void benutzerRegistrieren() throws benutzerNameVergebenException {
        benutzerVerwalten.benutzerRegistrieren("Samu","samu123");
        benutzerVerwalten.benutzerRegistrieren("Laura","Laurita");
        assertFalse(benutzerVerwalten.returnAllPlayer().isEmpty());
        assertEquals(2,benutzerVerwalten.returnAllPlayer().size());
    }

    @Test
    void benutzerNameVergebenExceptionTest() throws benutzerNameVergebenException {
        benutzerVerwalten.benutzerRegistrieren("Samu","samu123");
        assertFalse(benutzerVerwalten.returnAllPlayer().isEmpty());
        assertEquals(1,benutzerVerwalten.returnAllPlayer().size());
        assertThrows(benutzerNameVergebenException.class , () -> benutzerVerwalten.benutzerRegistrieren("Samu","samu12"));
    }

    @Test
    void benutzerLoeschen() throws benutzerNameVergebenException, ungueltigerBenutzernameException {
        benutzerVerwalten.benutzerRegistrieren("Samu","samu123");
        benutzerVerwalten.benutzerRegistrieren("Laura","Laurita");
        benutzerVerwalten.benutzerLoeschen("Samu");
        assertEquals(1,benutzerVerwalten.returnAllPlayer().size());
        assertFalse(benutzerVerwalten.benutzerdatenPruefen("Samu","samu123"));
        benutzerVerwalten.benutzerRegistrieren("Samu","samu123");
        assertEquals(2,benutzerVerwalten.returnAllPlayer().size());
    }

    @Test
    void ungueltigerBenutzernameExceptionTest() throws benutzerNameVergebenException {
        benutzerVerwalten.benutzerRegistrieren("Samu","samu123");
        assertFalse(benutzerVerwalten.returnAllPlayer().isEmpty());
        assertEquals(1,benutzerVerwalten.returnAllPlayer().size());
        assertThrows(ungueltigerBenutzernameException.class , () -> benutzerVerwalten.benutzerLoeschen("Samu1"));
    }

    @Test
    void benutzerdatenPruefen() throws benutzerNameVergebenException {
        benutzerVerwalten.benutzerRegistrieren("Samu","samu123");
        benutzerVerwalten.benutzerRegistrieren("Laura","Laurita");
        assertEquals(2,benutzerVerwalten.returnAllPlayer().size());
        assertEquals(true,benutzerVerwalten.benutzerdatenPruefen("Samu","samu123"));
        assertEquals(false,benutzerVerwalten.benutzerdatenPruefen("Laura","Laurita1"));
    }

}