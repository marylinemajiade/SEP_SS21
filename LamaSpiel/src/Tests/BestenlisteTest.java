package Tests;

import java.lang.Object.javafx.util.Pair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Die Klasse BestenlisteTest dient zum Testen der Klasse Klasse Bestenliste mit JUnitTests
 *
 * @author Vanessa Stein
 */

class BestenlisteTest {

    private HashMap liste;

    /**
     * Initialisiert eine leere Hashmap vor jeder Testmethode
     */
    @BeforeEach
    void setup() {
        this.liste = new HashMap<String, Integer>();
    }

    /**
     * Testet die Methode eintragHinzufuegen()
     */
    @Test
    void eintragHinzufuegen() {
        assertTrue(liste.isEmpty());
        assertEquals(0, liste.size());

        liste.put("Spieler1", 5);
        assertFalse(liste.isEmpty());
        assertTrue(liste.size() == 1);

        liste.put("Spieler2", 7);
        assertTrue(liste.size() == 2);

        assertTrue(liste.containsKey("Spieler1"));
        assertEquals(5, liste.get("Spieler1"));
        assertTrue(liste.containsKey("Spieler2"));
        assertEquals(7, liste.get("Spieler2"));

        liste.put("Spieler2", 8);
        assertTrue(liste.containsKey("Spieler2"));
        assertEquals(8, liste.get("Spieler2"));
    }

    /**
     * Testet die Methode getScore()
     */
    @Test
    void getScore() {
        assertTrue(liste.isEmpty());
        liste.put("Spieler1", 5);
        assertEquals(5, liste.get("Spieler1"));

        Assertions.assertThrows(NoSuchElementException.class, () -> liste.get("Spieler2"));
    }

    @Test
    void getTopZehn() {
        ArrayList<Pair<String, Integer>> entries = new ArrayList<>();

        liste.put("Spieler1", 5);
        liste.put("Spieler2", 7);
        liste.put("Spieler3", 1);
        liste.put("Spieler4", 3);
        /*
        noch nicht fertig
         */



    }

    /**
     * Testet die Methode eintragloeschen()
     */
    @Test
    void eintragLoeschen() {
        /*
        Stellt sicher, dass liste.size() nicht < 0 wird
         */
        assertTrue(liste.isEmpty());
        assertEquals(0, liste.size());
        liste.remove("Spieler1");
        assertEquals(0, liste.size());
        /*
        Stellt sicher, dass Eintraege für anschließende Tests ordentlich aufgenommen wurden
         */
        liste.put("Spieler1", 5);
        liste.put("Spieler2", 7);
        assertEquals(2, liste.size());
        /*
        Testet, ob liste.size() nach Entfernen eines Eintrags um 1 dekrementiert wird
         */
        liste.remove("Spieler2");
        assertEquals(1, liste.size());
        /*
        Testet, ob Key nach Loeschen wirklich nicht mehr enthalten ist
         */
        assertFalse(liste.containsKey("Spieler2"));
        Assertions.assertThrows(NoSuchElementException.class, () -> liste.get("Spieler2"));


    }
}