
import SpielLobby.Lobby;
import fachlicheExceptions.ungueltigerBenutzernameException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


/**
 * @author Catharina Helten
 */


class LobbyTest {

    private Lobby lobbyTest = new Lobby();


    @BeforeEach
    void setup(){ //überschreiben der Klassenvariablen mit den  Testvariablen
        lobby.setSpielerInSpielrunde(spielerInSpielrundeTest);
        lobby.setSpielrunden(spielraums);
    }

    @Test
    void getSpielraumIds() {
        assertEquals(3, lobbyTest.getSpielraum_Ids().size());
    }



    @Test
    void spielraumBeitreten(){


        lobbyTest.spielraumBeitreten("Vanessa",1);

        lobbyTest.spielraumBeitreten("Nick",1);

        assertTrue(lobbyTest.getSpieler(1).contains("Vanessa"));

        assertTrue(lobbyTest.getSpieler(1).contains("Nick"));

        assertFalse(lobbyTest.getSpieler(0).contains("Vanessa"));

        assertFalse(lobbyTest.getSpieler(0).contains("Nick"));

    }

    @Test
    void spielraumVerlassen() throws ungueltigerBenutzernameException {

        lobbyTest.spielrunden.get(1).spielraumVerlassen("Vanessa");

        ArrayList<String> spielerIn1 = lobbyTest.getSpieler(1);

        assertTrue(spielerIn1.isEmpty());

        ArrayList<String> spielerInLobby = lobbyTest.getSpieler(0);

        assertTrue(spielerInLobby.contains("Vanessa"));

    }

    @Test
    void spielraumLoeschen() {
        assertEquals(3,lobbyTest.spielrunden.size());

        assertEquals(3,lobbyTest.getSpielraum_Ids().size());

        lobbyTest.spielraumLoeschen(0);

        assertEquals(2,lobbyTest.spielrunden.size());

        assertEquals(2,lobbyTest.getSpielraum_Ids().size());

        assertTrue(lobbyTest.getSpieler(0).isEmpty());
    }

    @Test
    void spielraumHinzufuegen() {
        assertEquals(3,lobbyTest.spielrunden.size());

        assertEquals(3,lobbyTest.getSpielraum_Ids().size());

        lobbyTest.spielraumHinzufuegen(3);

        assertEquals(4,lobbyTest.spielrunden.size());

        assertEquals(4,lobbyTest.getSpielraum_Ids().size());

        assertTrue(lobbyTest.getSpieler(3).isEmpty());
    }

    @Test
    void getSpieler() {
     lobbyTest.spielraumBeitreten("Vanessa", 1);

     assertTrue(lobbyTest.getSpieler(1).contains("Vanessa"));

     lobbyTest.spielraumBeitreten("Catharina", 2);

     assertTrue(lobbyTest.getSpieler(2).contains("Catharina"));

     assertFalse(lobbyTest.getSpieler(1).contains("Catharina"));

    }

}
