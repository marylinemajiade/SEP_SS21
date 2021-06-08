package Tests;

import Spiel.Chipstapel;
import Spiel.Spielrunde;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class ChipstapelTest {

    /**
     * @author Catharina Helten
     */



    private int white;
    private int black;
    private int punkte;
    Spielrunde spielrunde;
    String benutzername;
    Chipstapel chipstapel;


    @BeforeEach
    void setup(){
     spielrunde = new Spielrunde(1);
     benutzername = spielrunde.anDerReihe();
     chipstapel = spielrunde.getChipstapel(benutzername);
     chipstapel.setWeiss(5);
     chipstapel.setSchwarz(3);

    }

    @Test
    void getWeiss(){
        white = chipstapel.getWeiss();
        spielrunde.chipAbgeben(false, benutzername);
        assertEquals(white-1,chipstapel.getWeiss());
    }

    @Test
    void getSchwarz(){
        black = chipstapel.getSchwarz();
        spielrunde.chipAbgeben(true, benutzername);
        assertEquals(black-1,chipstapel.getSchwarz());
    }

    @Test
    void getPunkte(){
         punkte = chipstapel.getPunkte();
         spielrunde.chipAbgeben(true, benutzername);
         assertEquals(punkte-10, chipstapel.getPunkte());
         spielrunde.chipAbgeben(false, benutzername);
         assertEquals(punkte-11, chipstapel.getPunkte());
    }

    @Test
     void setWeiss(){
        assertEquals(5, chipstapel.getPunkte());
        chipstapel.setWeiss(2);
        assertEquals(2, chipstapel.getPunkte());
     }

    @Test
     void setSchwarz(){
        assertEquals(35, chipstapel.getPunkte());
        chipstapel.setSchwarz(0);
        assertEquals(5, chipstapel.getPunkte());
    }

}
