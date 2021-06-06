package Tests;

import Highscore.Bestenliste;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;




class BestenlisteTest {

    public HashMap<String,Integer> liste;

    @BeforeEach
    void setup() {
        Bestenliste bestenliste = new Bestenliste();
        this.liste = Bestenliste.bestenliste;
    }

    @Test
    void eintragHinzufuegen(String spieler, boolean isbot) {
        assertTrue(liste.isEmpty());
        assertEquals(0, liste.size());

        Bestenliste.eintragHinzufuegen("Spieler1", false);
        assertFalse(liste.isEmpty());
        assertEquals(1,liste.size());

        Bestenliste.eintragHinzufuegen("Spieler2", false);
        assertEquals(2, liste.size() );

        assertTrue(liste.containsKey("Spieler1"));
        assertEquals(1, liste.get("Spieler1"));
        assertTrue(liste.containsKey("Spieler2"));
        assertEquals(1, liste.get("Spieler2"));

        Bestenliste.eintragHinzufuegen("Spieler2", false);
        assertTrue(liste.containsKey("Spieler2"));
        assertEquals(2, liste.get("Spieler2"));

        Bestenliste.eintragHinzufuegen("Bot1", true);
        assertFalse(liste.containsKey("Bot1"));

    }


    @Test
    void getScore(String spieler) {
        assertTrue(liste.isEmpty());

        Bestenliste.eintragHinzufuegen("Spieler1", false);
        Bestenliste.eintragHinzufuegen("Spieler1", false);
        Bestenliste.eintragHinzufuegen("Spieler1", false);
        assertEquals(3, liste.get("Spieler1"));

        assertThrows(NoSuchElementException.class, () -> liste.get("Spieler2"));
    }

    @Test
    void getTopZehn() {
        assertTrue(liste.isEmpty());

        for (int i = 1; i <= 11; i++) {
            Bestenliste.eintragHinzufuegen("Spieler1", false);
        }

        for (int i = 1; i <= 10; i++) {
            Bestenliste.eintragHinzufuegen("Spieler2", false);
        }

        for (int i = 1; i <= 9; i++) {
            Bestenliste.eintragHinzufuegen("Spieler3", false);
        }

        for (int i = 1; i <= 8; i++) {
            Bestenliste.eintragHinzufuegen("Spieler4", false);
        }

        for (int i = 1; i <= 7; i++) {
            Bestenliste.eintragHinzufuegen("Spieler5", false);
        }

        for (int i = 1; i <= 6; i++) {
            Bestenliste.eintragHinzufuegen("Spieler6", false);
        }

        for (int i = 1; i <= 5; i++) {
            Bestenliste.eintragHinzufuegen("Spieler7", false);
        }

        for (int i = 1; i <= 4; i++) {
            Bestenliste.eintragHinzufuegen("Spieler8", false);
        }

        for (int i = 1; i <= 3; i++) {
            Bestenliste.eintragHinzufuegen("Spieler9", false);
        }

        Bestenliste.eintragHinzufuegen("Spieler10", false);
        Bestenliste.eintragHinzufuegen("Spieler10", false);

        Bestenliste.eintragHinzufuegen("Spieler11", false);


        ArrayList topten = Bestenliste.getTopZehn();

        assertEquals(10, topten.size());
        assertEquals("Spieler1=11", topten.get(0).toString());
        assertEquals("Spieler2=10", topten.get(1).toString());
        assertEquals("Spieler3=9", topten.get(2).toString());
        assertEquals("Spieler4=8", topten.get(3).toString());
        assertEquals("Spieler5=7", topten.get(4).toString());
        assertEquals("Spieler6=6", topten.get(5).toString());
        assertEquals("Spieler7=5", topten.get(6).toString());
        assertEquals("Spieler8=4", topten.get(7).toString());
        assertEquals("Spieler9=2", topten.get(8).toString());
        assertEquals("Spieler10=1", topten.get(9).toString());

    }


    @Test
    void eintragLoeschen(String spieler) {

        assertTrue(liste.isEmpty());
        assertEquals(0, liste.size());

        Bestenliste.eintragHinzufuegen("Spieler1", false);
        Bestenliste.eintragHinzufuegen("Spieler2", false);
        assertEquals(2, liste.size());

        Bestenliste.eintragLoeschen("Spieler2");
        assertEquals(1, liste.size());

        assertFalse(liste.containsKey("Spieler2"));
        assertThrows(NoSuchElementException.class, () -> Bestenliste.eintragLoeschen("Spieler2"));

    }
}