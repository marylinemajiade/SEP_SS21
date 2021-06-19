
import Spiel.Chipstapel;
import Spiel.Spielrunde;
import SpielLobby.Lobby;
import fachlicheExceptions.stapelLeerException;
import fachlicheExceptions.ungueltigerBenutzernameException;
import fachlicheExceptions.ungueltigerChipException;
import fachlicheExceptions.ungueltigerSpielzugException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * @author Catharina Helten
 */

public class ChipstapelTest {




    private int white;
    private int black;
    private int punkte;
    Spielrunde spielrunde;
    String benutzername;
    Chipstapel chipstapel;


    @BeforeEach
    void setup() throws ungueltigerBenutzernameException, stapelLeerException {
     spielrunde = new Spielrunde(1, new Lobby());
     benutzername = spielrunde.anDerReihe();
     chipstapel = spielrunde.getChipstapel(benutzername);
     chipstapel.setWeiss(5);
     chipstapel.setSchwarz(3);

    }

    @Test
    void getWeiss() throws ungueltigerChipException, ungueltigerSpielzugException, ungueltigerBenutzernameException, stapelLeerException {
        white = chipstapel.getWeiss();
        assertEquals(5,white);
        spielrunde.chipAbgeben(false, benutzername);
        assertEquals(white-1,chipstapel.getWeiss());
    }

    @Test
    void getSchwarz() throws ungueltigerChipException, ungueltigerSpielzugException, ungueltigerBenutzernameException, stapelLeerException {
        black = chipstapel.getSchwarz();
        spielrunde.chipAbgeben(true, benutzername);
        assertEquals(black-1,chipstapel.getSchwarz());
    }

    @Test
    void getPunkte() throws ungueltigerChipException, ungueltigerSpielzugException, ungueltigerBenutzernameException, stapelLeerException {
         punkte = chipstapel.getPunkte();
         spielrunde.chipAbgeben(true, benutzername);
         assertEquals(punkte-10, chipstapel.getPunkte());
         spielrunde.chipAbgeben(false, benutzername);
         assertEquals(punkte-11, chipstapel.getPunkte());
    }

    @Test
     void setWeiss(){
        assertEquals(35, chipstapel.getPunkte());
        chipstapel.setWeiss(2);
        assertEquals(32, chipstapel.getPunkte());
     }

    @Test
     void setSchwarz(){
        assertEquals(35, chipstapel.getPunkte());
        chipstapel.setSchwarz(0);
        assertEquals(5, chipstapel.getPunkte());
    }

}
