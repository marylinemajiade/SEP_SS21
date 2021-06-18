
import Spiel.Chipstapel;
import Spiel.Spielrunde;
import fachlicheExceptions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import SpielLobby.Lobby;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

//import static junit.framework.TestCase.assertFalse;
import static org.junit.jupiter.api.Assertions.*;


public class SpielrundeTest{

    /**
     * @author Catharina Helten
     * @author Vanessa Stein
     */

    private int spielraumId;
    private HashMap<String, ArrayList<Integer>> handkarten = new HashMap<>();
    private Spielrunde spielrunde;
    private Stack<Integer> ablagestapel = new Stack<>();
    private Stack<Integer> nachziehstapel= new Stack<>();
    private Chipstapel chipstapel;



    String benutzername;
    @BeforeEach
    void setup(){
        spielrunde = new Spielrunde(1,new Lobby());
        benutzername = spielrunde.anDerReihe();
    }


      @Test
    void getRaumId() {
        assertEquals(1, spielrunde.getRaumId());
        Spielrunde spielrunde2 = new Spielrunde(2,new Lobby());
        assertEquals(2, spielrunde2.getRaumId());
      }


    @Test
    void karteAblegen() throws ungueltigerBenutzernameException, stapelLeerException {


        ablagestapel = spielrunde.getAblagestapel();


        assertTrue(handkarten.isEmpty());
        assertTrue(ablagestapel.empty());

        handkarten.get(benutzername).add(1);
        handkarten.get(benutzername).add(4);
        handkarten.get(benutzername).add(5);

        assertEquals(3, handkarten.size());

        spielrunde.karteAblegen(benutzername, 1);

        assertEquals(2, handkarten.size());
        assertFalse(handkarten.get(benutzername).contains(1));
        assertEquals(1, ablagestapel.size());
        assertEquals(1, ablagestapel.peek());

        spielrunde.karteAblegen(benutzername, 4);
        spielrunde.karteAblegen(benutzername, 5);
        assertTrue(handkarten.isEmpty());
        assertEquals(3, ablagestapel.size());
        assertEquals(5, ablagestapel.peek());
        try{
            spielrunde.karteAblegen(benutzername, 6);
        } catch (stapelLeerException ignored){}
    }


    @Test
    void karteZiehen() throws stapelLeerException, ungueltigerBenutzernameException, ungueltigeKarteException {
        nachziehstapel = spielrunde.getNachziehstapel();
        assertTrue(nachziehstapel.isEmpty());
        assertTrue(handkarten.isEmpty());

        nachziehstapel.add(1);
        nachziehstapel.add(3);
        nachziehstapel.add(4);
        assertEquals(3, nachziehstapel.size());

        spielrunde.karteZiehen(benutzername);

        assertEquals(2, nachziehstapel.size());
        assertEquals(3, nachziehstapel.peek());
        assertTrue(handkarten.get(benutzername).contains(4));
        assertEquals(1, handkarten.size());

        spielrunde.karteZiehen(benutzername);
        spielrunde.karteZiehen(benutzername);
        assertTrue(nachziehstapel.empty());
        assertTrue(handkarten.get(benutzername).contains(3));
        assertTrue(handkarten.get(benutzername).contains(1));
        assertEquals(3, handkarten.size());
        try{
            spielrunde.karteZiehen(benutzername);
        } catch (stapelLeerException ignored){}
    }



    @Test
    void aussteigen() throws ungueltigerSpielzugException, ungueltigerBenutzernameException {
        spielrunde.aussteigen(benutzername);
        assertFalse(spielrunde.anDerReihe() == benutzername);
    }

    @Test
    void chipsTauschen() throws ungueltigerBenutzernameException, stapelLeerException, ungueltigerSpielzugException, ungueltigerChipException {

        chipstapel = spielrunde.getChipstapel(benutzername);
        chipstapel.setWeiss(15);
        chipstapel.setSchwarz(2);
        assertEquals(35, chipstapel.getPunkte());

        spielrunde.chipsTauschen(true,benutzername);
        assertEquals(35, chipstapel.getPunkte());
        assertEquals(1, chipstapel.getSchwarz());
        assertEquals(25, chipstapel.getWeiss());

        spielrunde.chipsTauschen(false,benutzername);
        assertEquals(35, chipstapel.getPunkte());
        assertEquals(15, chipstapel.getWeiss());
        assertEquals(2, chipstapel.getSchwarz());

        spielrunde.chipsTauschen(false,benutzername);
        assertEquals(35, chipstapel.getPunkte());
        assertEquals(5, chipstapel.getWeiss());
        assertEquals(3, chipstapel.getSchwarz());
    }


    @Test
    void chipAbgeben() throws ungueltigerBenutzernameException, stapelLeerException, ungueltigerSpielzugException, ungueltigerChipException {
        chipstapel = spielrunde.getChipstapel(benutzername);
        chipstapel.setWeiss(6);
        chipstapel.setSchwarz(2);
        assertEquals(26, chipstapel.getPunkte());

        spielrunde.chipAbgeben(true,benutzername);
        assertEquals(16, chipstapel.getPunkte());
        assertEquals(1, chipstapel.getSchwarz());
        assertEquals(6, chipstapel.getWeiss());

        spielrunde.chipAbgeben(false,benutzername);
        assertEquals(15, chipstapel.getPunkte());
        assertEquals(5, chipstapel.getWeiss());
        assertEquals(1, chipstapel.getSchwarz());
    }


    @Test
    void anDerReihe() throws ungueltigerBenutzernameException {
         assertEquals(spielrunde.anDerReihe(),benutzername);
         spielrunde.spielraumVerlassen(benutzername);
         assertNotEquals(spielrunde.anDerReihe(), benutzername);
    }

    @Test
    void spielStarten() throws RemoteException, spielLaeuftBereitsException, zuWenigSpielerException {

        Lobby lobby = new Lobby();
        assertEquals(1, spielrunde.getRaumId());
        assertTrue(lobby.getSpieler(1).isEmpty());
        try{
            spielrunde.spielStarten();
        } catch (zuWenigSpielerException ignored){}

        lobby.spielraumBeitreten("Spieler1", 1);
        try{
            spielrunde.spielStarten();
        } catch (zuWenigSpielerException ignored){}
        lobby.spielraumBeitreten("Spieler2", 1);
        assertEquals(true, spielrunde.spielStarten());


    }

    @Test
    void getHandkarten() throws ungueltigerBenutzernameException, stapelLeerException, ungueltigeKarteException {


        assertTrue(handkarten.isEmpty());

        handkarten.get(benutzername).add(1);
        handkarten.get(benutzername).add(3);
        handkarten.get(benutzername).add(5);

        assertEquals(handkarten.size(), 3);
        assertTrue(handkarten.get(benutzername).contains(1));
        assertTrue(handkarten.get(benutzername).contains(3));
        assertTrue(handkarten.get(benutzername).contains(5));
        assertFalse(handkarten.get(benutzername).contains(2));
        assertFalse(handkarten.get(benutzername).contains(4));
        assertFalse(handkarten.get(benutzername).contains(6));

        spielrunde.karteAblegen(benutzername,5);
        assertEquals(handkarten.size(), 2);
        assertFalse(handkarten.get(benutzername).contains(5));
    }

    @Test
    void getAblagestapel() throws stapelLeerException {

        ablagestapel = spielrunde.getAblagestapel();
        assertTrue(ablagestapel.empty());

        spielrunde.karteAblegen(benutzername, 5);
        spielrunde.karteAblegen(benutzername, 4);
        spielrunde.karteAblegen(benutzername, 3);
        assertEquals(ablagestapel.size(), 3);
        assertEquals(3, ablagestapel.peek());
        ablagestapel.pop();
        assertEquals(4, ablagestapel.peek());
        assertEquals(ablagestapel.size(), 2);
        ablagestapel.pop();
        ablagestapel.pop();
        assertTrue(ablagestapel.empty());

    }

    @Test
    void getNachziehstapel() throws stapelLeerException, ungueltigeKarteException {

        nachziehstapel = spielrunde.getNachziehstapel();
        assertTrue(nachziehstapel.empty());

        nachziehstapel.push(5);
        nachziehstapel.push(4);
        nachziehstapel.push(2);

        assertEquals(2, nachziehstapel.peek());
        assertEquals(nachziehstapel.size(),3);
        spielrunde.karteZiehen(benutzername);
        assertEquals(nachziehstapel.size(),2);
        assertEquals(4, nachziehstapel.peek());

        spielrunde.karteZiehen(benutzername);
        spielrunde.karteZiehen(benutzername);
        assertTrue(nachziehstapel.empty());
        try {
            spielrunde.karteZiehen(benutzername);
        } catch (stapelLeerException ignored){}

    }

    @Test
    void getChipstapel() throws ungueltigerBenutzernameException, stapelLeerException {

        chipstapel = spielrunde.getChipstapel(benutzername);
        assertEquals(chipstapel.getWeiss(), 0);
        assertEquals(chipstapel.getSchwarz(), 0);

        chipstapel.setWeiss(6);
        chipstapel.setSchwarz(2);
        assertEquals(chipstapel.getWeiss(),6);
        assertEquals(chipstapel.getSchwarz(),2);
        assertEquals(chipstapel.getPunkte(), 26);

    }

    @Test
    void spielraumVerlassen() throws RemoteException, ungueltigerBenutzernameException {

        Lobby lobby = new Lobby();

        assertEquals(1, spielrunde.getRaumId());
        lobby.spielraumBeitreten("Spieler1", 1);
        lobby.spielraumBeitreten("Spieler2", 1);
        lobby.spielraumBeitreten("Spieler3", 1);
        assertEquals( 3, lobby.getSpieler(1).size());
        assertTrue(lobby.getSpieler(1).contains("Spieler1"));
        assertTrue(lobby.getSpieler(1).contains("Spieler2"));
        assertTrue(lobby.getSpieler(1).contains("Spieler3"));

        spielrunde.spielraumVerlassen("Spieler2");
        assertEquals( 2, lobby.getSpieler(1).size());
        assertFalse(lobby.getSpieler(1).contains("Spieler2"));

        assertTrue(lobby.getSpieler(0).contains("Spieler2"));
    }
}
