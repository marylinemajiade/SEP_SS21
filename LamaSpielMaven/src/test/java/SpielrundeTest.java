
import Spiel.Chipstapel;
import Spiel.Spielrunde;
import fachlicheExceptions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Stack;

//import static junit.framework.TestCase.assertFalse;
import static org.junit.jupiter.api.Assertions.*;


public class SpielrundeTest{

    /**
     * @author Catharina Helten
     */

    private int spielraumId;
    private HashSet<Integer> handkarten;
    private Spielrunde spielrunde;
    private Stack<Integer> ablagestapel;
    private Stack<Integer> nachziehstapel;
    private Chipstapel chipstapel;



    String benutzername;
    @BeforeEach
    void setup(){
        spielrunde = new Spielrunde(1);
        benutzername = spielrunde.anDerReihe();

    }


      @Test
    void getRaumId() {
        assertEquals(1, spielrunde.getRaumId());
        Spielrunde spielrunde2 = new Spielrunde(2);
        assertEquals(2, spielrunde2.getRaumId());
      }


    @Test
    void karteAblegen() throws ungueltigerBenutzernameException, stapelLeerException {

        handkarten = spielrunde.getHandkarten(benutzername);
        ablagestapel = spielrunde.getAblagestapel();


        assertFalse(handkarten.isEmpty());

        handkarten.add(1);

        int anzahlKartenHand = handkarten.size();
        int anzahlKartenAblage = ablagestapel.size();


        spielrunde.karteAblegen(benutzername, 1);

        assertEquals(anzahlKartenHand, handkarten.size());
        assertEquals(anzahlKartenAblage+1, ablagestapel.size());


    }


    @Test
    void karteZiehen() throws stapelLeerException, ungueltigerBenutzernameException, ungueltigeKarteException {
        nachziehstapel = spielrunde.getNachziehstapel();
        handkarten = spielrunde.getHandkarten(benutzername);
        assertFalse(nachziehstapel.isEmpty());
        nachziehstapel.add(1);

         int anzahlKartenNachzieh = nachziehstapel.size();
         int anzahlKartenHand = handkarten.size();


        spielrunde.karteZiehen(benutzername);
        assertEquals(nachziehstapel.size()+1,anzahlKartenNachzieh);
        assertEquals( 1, spielrunde.karteZiehen(benutzername));
        assertEquals(anzahlKartenHand+1, handkarten.size());
    }



    @Test
    void aussteigen() throws ungueltigerSpielzugException, ungueltigerBenutzernameException {
        spielrunde.aussteigen(benutzername);
        assertTrue(spielrunde.anDerReihe() != benutzername);
    }

    @Test
    void chipsTauschen() throws ungueltigerBenutzernameException, stapelLeerException, ungueltigerSpielzugException, ungueltigerChipException {

        chipstapel = spielrunde.getChipstapel(benutzername);
        int punkte = chipstapel.getPunkte();
        spielrunde.chipsTauschen(true,benutzername);
        assertEquals(punkte-1, chipstapel.getPunkte());
        spielrunde.chipsTauschen(false,benutzername);
        assertEquals(punkte-10, chipstapel.getPunkte());
    }


    @Test
    void chipAbgeben() throws ungueltigerBenutzernameException, stapelLeerException, ungueltigerSpielzugException, ungueltigerChipException {
        chipstapel = spielrunde.getChipstapel(benutzername);
        int punkte = chipstapel.getPunkte();
        int weiß = chipstapel.getWeiss();
        int schwarz = chipstapel.getSchwarz();

        spielrunde.chipAbgeben(true,benutzername);
        assertEquals(punkte-10, chipstapel.getPunkte());
        spielrunde.chipAbgeben(false,benutzername);
        assertEquals(punkte-11, chipstapel.getPunkte());

        assertEquals(weiß-1, chipstapel.getWeiss());
        assertEquals(schwarz-1, chipstapel.getSchwarz());
    }


    @Test
    void anDerReihe() throws ungueltigerBenutzernameException {
         assertTrue(spielrunde.anDerReihe() == benutzername);
         spielrunde.spielraumVerlassen(benutzername);
         assertTrue(spielrunde.anDerReihe() != benutzername);
    }

    @Test
    void spielStarten(){
        assertTrue(false);
    }

    @Test
    void getHandkarten(){
        assertTrue(false);
    }

    @Test
    void getAblagestapel(){
        assertTrue(false);
    }

    @Test
    void getNachziehstapel(){
        assertTrue(false);
    }

    @Test
    void getChipstapel(){
        assertTrue(false);
    }

    @Test
    void spielraumVerlassen(){
        assertTrue(false);
    }
}
