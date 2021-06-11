package Tests;

import Highscore.Bestenliste;
import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;




class BestenlisteTest {

    @Test
    void eintragHinzufuegen() {

        Bestenliste b = new Bestenliste();

        b.eintragHinzufuegen("Spieler1", false);
        b.eintragHinzufuegen("Spieler2", false);
        assertEquals(1, b.getScore("Spieler1"));
        assertEquals(1, b.getScore("Spieler2"));

        b.eintragHinzufuegen("Spieler2", false);
        assertEquals(2, b.getScore("Spieler2"));

        try {
            b.eintragHinzufuegen("Bot1", true);
            b.getScore("Bot1");
        } catch (NullPointerException | NoSuchElementException ignored){}

    }


    @Test
    void getScore() {

        Bestenliste b = new Bestenliste();

        b.eintragHinzufuegen("Spieler1", false);
        b.eintragHinzufuegen("Spieler1", false);
        b.eintragHinzufuegen("Spieler1", false);
        assertEquals(3, b.getScore("Spieler1"));

        try{
            b.getScore("Spieler2");
        } catch (NullPointerException | NoSuchElementException ignored) {}
    }

    @Test
    void getTopZehn() {

        Bestenliste b = new Bestenliste();

        for (int i = 1; i <= 11; i++) {
            b.eintragHinzufuegen("Spieler1", false);
        }

        for (int i = 1; i <= 10; i++) {
            b.eintragHinzufuegen("Spieler2", false);
        }

        for (int i = 1; i <= 9; i++) {
            b.eintragHinzufuegen("Spieler3", false);
        }

        for (int i = 1; i <= 8; i++) {
            b.eintragHinzufuegen("Spieler4", false);
        }

        for (int i = 1; i <= 7; i++) {
            b.eintragHinzufuegen("Spieler5", false);
        }

        for (int i = 1; i <= 6; i++) {
            b.eintragHinzufuegen("Spieler6", false);
        }

        for (int i = 1; i <= 5; i++) {
            b.eintragHinzufuegen("Spieler7", false);
        }

        for (int i = 1; i <= 4; i++) {
            b.eintragHinzufuegen("Spieler8", false);
        }

        for (int i = 1; i <= 3; i++) {
            b.eintragHinzufuegen("Spieler9", false);
        }

        b.eintragHinzufuegen("Spieler10", false);
        b.eintragHinzufuegen("Spieler10", false);

        b.eintragHinzufuegen("Spieler11", false);


        ArrayList topten = b.getTopZehn();

        assertEquals(10, topten.size());
        assertEquals("Spieler1=11", topten.get(0).toString());
        assertEquals("Spieler2=10", topten.get(1).toString());
        assertEquals("Spieler3=9", topten.get(2).toString());
        assertEquals("Spieler4=8", topten.get(3).toString());
        assertEquals("Spieler5=7", topten.get(4).toString());
        assertEquals("Spieler6=6", topten.get(5).toString());
        assertEquals("Spieler7=5", topten.get(6).toString());
        assertEquals("Spieler8=4", topten.get(7).toString());
        assertEquals("Spieler9=3", topten.get(8).toString());
        assertEquals("Spieler10=2", topten.get(9).toString());

    }


    @Test
    void eintragLoeschen() {

        Bestenliste b = new Bestenliste();

        b.eintragHinzufuegen("Spieler1", false);
        b.eintragHinzufuegen("Spieler2", false);

        b.eintragLoeschen("Spieler2");
        assertEquals(1, b.getScore("Spieler1"));

        try{
            b.getScore("Spieler2");
        } catch (NullPointerException | NoSuchElementException ignored) {}

        try{
            b.eintragLoeschen("Spieler3");
        } catch (NullPointerException | NoSuchElementException ignored) {}

    }
}