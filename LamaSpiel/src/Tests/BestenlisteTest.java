package Tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;




class BestenlisteTest {

    private HashMap<String,Integer> liste;


    @BeforeEach
    void setup() {
        this.liste = new HashMap<String, Integer>();
    }


    @Test
    void eintragHinzufuegen() {
        assertTrue(liste.isEmpty());
        assertEquals(0, liste.size());

        liste.put("Spieler1", 5);
        assertFalse(liste.isEmpty());
        assertEquals(1,liste.size());

        liste.put("Spieler2", 7);
        assertEquals(2, liste.size() );

        assertTrue(liste.containsKey("Spieler1"));
        assertEquals(5, liste.get("Spieler1"));
        assertTrue(liste.containsKey("Spieler2"));
        assertEquals(7, liste.get("Spieler2"));

        liste.put("Spieler2", 8);
        assertTrue(liste.containsKey("Spieler2"));
        assertEquals(8, liste.get("Spieler2"));

    }


    @Test
    void getScore() {
        assertTrue(liste.isEmpty());
        liste.put("Spieler1", 5);
        assertEquals(5, liste.get("Spieler1"));

        Assertions.assertThrows(NoSuchElementException.class, () -> liste.get("Spieler2"));
    }

    @Test
    void getTopZehn() {
        assertTrue(liste.isEmpty());

        liste.put("Spieler1", 5);
        liste.put("Spieler2", 7);
        liste.put("Spieler3", 1);
        liste.put("Spieler4", 3);
        liste.put("Spieler5", 12);
        liste.put("Spieler6", 18);
        liste.put("Spieler7", 0);
        liste.put("Spieler8", 4);
        liste.put("Spieler9", 15);
        liste.put("Spieler10", 6);
        liste.put("Spieler11", 2);

        Stream<Map.Entry<String,Integer>> sorted =
                liste.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue()));

        ArrayList topten = (ArrayList) sorted.limit(10).collect(Collectors.toList());

        assertEquals(10, topten.size());
        assertEquals("Spieler6=18", topten.get(0).toString());
        assertEquals("Spieler9=15", topten.get(1).toString());
        assertEquals("Spieler5=12", topten.get(2).toString());
        assertEquals("Spieler2=7", topten.get(3).toString());
        assertEquals("Spieler10=6", topten.get(4).toString());
        assertEquals("Spieler1=5", topten.get(5).toString());
        assertEquals("Spieler8=4", topten.get(6).toString());
        assertEquals("Spieler4=3", topten.get(7).toString());
        assertEquals("Spieler11=2", topten.get(8).toString());
        assertEquals("Spieler3=1", topten.get(9).toString());

    }


    @Test
    void eintragLoeschen() {

        assertTrue(liste.isEmpty());
        assertEquals(0, liste.size());
        liste.remove("Spieler1");
        assertEquals(0, liste.size());

        liste.put("Spieler1", 5);
        liste.put("Spieler2", 7);
        assertEquals(2, liste.size());

        liste.remove("Spieler2");
        assertEquals(1, liste.size());

        assertFalse(liste.containsKey("Spieler2"));
        Assertions.assertThrows(NoSuchElementException.class, () -> liste.get("Spieler2"));


    }
}