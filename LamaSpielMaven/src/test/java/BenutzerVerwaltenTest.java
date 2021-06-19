
import Konto.Benutzer;
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
        //Die Klasse "Benutzer" enthält paar Methoden, die getestet werden müssen, um ein höhes Coverage zu erreichen
        //da die Methoden in der Klasse BenutzerVerwalten kein Objekt "Benutzer" als Parameter nehmen, werden die Tests getrennt ausgeführt
        Benutzer samu = new Benutzer("Samu","Samu123");
        samu.setBenutzername("Samou");
        samu.setPasswort("Samu1234");
        assertEquals("Samu1234",samu.getPasswort());
        assertEquals("Samou",samu.getBenutzername());
        assertEquals("Benutzer{benutzername='Samou', passwort='Samu1234'}", samu.toString());

        benutzerVerwalten.benutzerRegistrieren("Laura","Laurita");

        assertFalse(benutzerVerwalten.returnAllPlayer().isEmpty());
        assertEquals(1,benutzerVerwalten.returnAllPlayer().size());
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

    /* Test brauchenwir nicht... Veränderung auf die Methode benutzerLoeschen
    @Test
    void ungueltigerBenutzernameExceptionTest() throws benutzerNameVergebenException {
        benutzerVerwalten.benutzerRegistrieren("Samu","samu123");
        assertFalse(benutzerVerwalten.returnAllPlayer().isEmpty());
        assertEquals(1,benutzerVerwalten.returnAllPlayer().size());
        assertThrows(ungueltigerBenutzernameException.class , () -> benutzerVerwalten.benutzerLoeschen("Samu1"));
    }
     */

    @Test
    void benutzerdatenPruefen() throws benutzerNameVergebenException {
        benutzerVerwalten.benutzerRegistrieren("Samu","samu123");
        benutzerVerwalten.benutzerRegistrieren("Laura","Laurita");
        assertEquals(2,benutzerVerwalten.returnAllPlayer().size());
        assertEquals(true,benutzerVerwalten.benutzerdatenPruefen("Samu","samu123"));
        assertEquals(false,benutzerVerwalten.benutzerdatenPruefen("Laura","Laurita1"));

    }

}