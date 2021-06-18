
import Konto.Benutzer;
import Spiel.Spielrunde;
import SpielLobby.Lobby;
import fachlicheExceptions.stapelLeerException;
import fachlicheExceptions.ungueltigerBenutzernameException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.util.ArrayUtils;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


/**
 * @author Maryline Majiade
 */


class LobbyTest {

    private Lobby lobby = new Lobby();
    HashMap<Integer,ArrayList<String>> spielerInSpielrundeTest = new HashMap<>();
    ArrayList<Spielrunde> spielraums = new ArrayList<>();

    @BeforeEach
    void setup(){ //überschreiben der Klassenvariablen mit den  Testvariablen
        lobby.setSpielerInSpielrunde(spielerInSpielrundeTest);
        lobby.setSpielrunden(spielraums);
    }

    @Test
    void getSpielraum_Ids() {

        Spielrunde raum1 = new Spielrunde(1,new Lobby());
        Spielrunde raum2 = new Spielrunde(2,new Lobby());
        Spielrunde raum3 = new Spielrunde(3,new Lobby());

        spielraums.add(raum1);
        spielraums.add(raum2);
        spielraums.add(raum3);

        assertNotNull(spielraums);

        ArrayList<Integer> actual = new ArrayList<>();
        actual.add(1);
        actual.add(2);
        actual.add(3);

        lobby.setSpielraum_Ids(actual);
        ArrayList<Integer> expected = lobby.getSpielraum_Ids();

        assertEquals(expected,actual);

    }


    @Test
    void spielraumBeitreten() {

        Spielrunde spielrunde = new Spielrunde(1, new Lobby());
        lobby.spielraumHinzufuegen(spielrunde.getRaumId());

        Benutzer benutzer= new Benutzer("Tester", "testMyCode99");

        assertFalse(spielerInSpielrundeTest.isEmpty());
        lobby.setSpielerInSpielrunde(spielerInSpielrundeTest);

        ArrayList<String> benutzernamen = new ArrayList<>();
        benutzernamen.add("Maryline");
        benutzernamen.add("Vanessa");
        benutzernamen.add("Nick");

        spielerInSpielrundeTest.put(spielrunde.getRaumId(), benutzernamen);

        assertEquals(1,spielerInSpielrundeTest.size());

        lobby.spielraumBeitreten(benutzer.getBenutzername(),spielrunde.getRaumId());
        assertTrue(benutzernamen.contains(benutzer.getBenutzername()));
        assertEquals(4,benutzernamen.size());

    }

    @Test
    void spielraumVerlassen() {
        Spielrunde spielrunde = new Spielrunde(1, new Lobby());
        lobby.spielraumHinzufuegen(spielrunde.getRaumId());

        Benutzer benutzer= new Benutzer("Nick", "testMyCode99");

        assertFalse(spielerInSpielrundeTest.isEmpty());
        lobby.setSpielerInSpielrunde(spielerInSpielrundeTest);

        ArrayList<String> benutzernamen = new ArrayList<>();
        benutzernamen.add("Maryline");
        benutzernamen.add("Vanessa");
        benutzernamen.add(benutzer.getBenutzername());

        spielerInSpielrundeTest.put(spielrunde.getRaumId(), benutzernamen);

        assertEquals(1,spielerInSpielrundeTest.size());

        lobby.spielraumVerlassen(benutzer.getBenutzername(),spielrunde.getRaumId());
        assertFalse(benutzernamen.contains(benutzer.getBenutzername()));
        assertEquals(2,benutzernamen.size());

    }



    @Test
    void spielRaumLoeschen() {

        ArrayList<Integer> ids = new ArrayList<>();

        Spielrunde raum1 = new Spielrunde(1,new Lobby());
        ids.add(raum1.getRaumId());

        Spielrunde raum2 = new Spielrunde(2,new Lobby());
        ids.add(raum2.getRaumId());

        spielraums.add(raum1);
        spielraums.add(raum2);

        lobby.setSpielraum_Ids(ids);

        lobby.setSpielrunden(spielraums);

        assert(spielraums.size()==2);
        lobby.spielraumLoeschen(2);
        assertEquals(spielraums.size(),1);


        lobby.spielraumLoeschen(1);
        assertEquals(spielraums.size(),0);

    }

    @Test
    void spielraumHinzufuegen(){

        Spielrunde raum1 = new Spielrunde(1,new Lobby());
        Spielrunde raum2 = new Spielrunde(2,new Lobby());
        spielraums.add(raum1);
        spielraums.add(raum2);

        assertEquals(spielraums.size(),2);
        lobby.spielraumHinzufuegen(3);

        assertEquals(3,spielraums.size());
    }

    @Test
    void getSpieler(){
        Benutzer benutzer1= new Benutzer("Maryline", "testPwd123");
        Benutzer benutzer2= new Benutzer("Tester", "testMyCode99");

        Spielrunde raum1 = new Spielrunde(1,new Lobby());
        Spielrunde raum2 = new Spielrunde(2,new Lobby());
        spielraums.add(raum1);
        spielraums.add(raum2);


        lobby.spielraumHinzufuegen(raum1.getRaumId());
        lobby.spielraumHinzufuegen(raum2.getRaumId());


        lobby.spielraumBeitreten(benutzer1.getBenutzername(),1);
        ArrayList<String> raumlist1 = new ArrayList<>();
        raumlist1.add(benutzer1.getBenutzername());

        lobby.spielraumBeitreten(benutzer2.getBenutzername(),2);
        ArrayList<String> raumlist2 = new ArrayList<>();
        raumlist2.add(benutzer2.getBenutzername());


        spielerInSpielrundeTest.put(raum1.getRaumId(), raumlist1);
        spielerInSpielrundeTest.put(raum2.getRaumId(), raumlist2);


        assertEquals(raumlist1,lobby.getSpieler(1));
        System.out.println(lobby.getSpieler(2));

    }


}