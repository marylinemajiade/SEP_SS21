
import Konto.Benutzer;
import SpielLobby.Lobby;
import SpielLobby.Spielraum;
import fachlicheExceptions.ungueltigerBenutzernameException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


/**
 * @author Maryline Majiade
 */


class LobbyTest {

    private Lobby lobby;

    @Test
    void getSpielraum_Ids() {

        ArrayList<Spielraum> spielraums = new ArrayList<>();
        Spielraum raum1 = new Spielraum(1);
        Spielraum raum2 = new Spielraum(2);
        Spielraum raum3 = new Spielraum(3);

        spielraums.add(raum1);
        spielraums.add(raum2);
        spielraums.add(raum3);

        assertNotNull(spielraums);

        ArrayList<Integer> actual = new ArrayList<>();
        actual.add(1);
        actual.add(2);
        actual.add(3);
        ArrayList<Integer> expected = lobby.getSpielraum_Ids();

        assertEquals(expected,actual);

    }


    @Test
    void spielraumBeitreten() {

        Benutzer benutzer1= new Benutzer("Maryline", "testPwd123");
        Benutzer benutzer2= new Benutzer("Tester", "testMyCode99");

        ArrayList<Spielraum> spielraums = new ArrayList<>();
        Spielraum raum1 = new Spielraum(1);
        spielraums.add(raum1);

        lobby.spielraumBeitreten(benutzer1.getBenutzername(),1);
        lobby.spielraumBeitreten(benutzer2.getBenutzername(),1);

        assert(spielraums.get(0).spielerList().size()==2); //Anzahl der Spieler in Raum 1
    }

    @Test
    void spielraumVerlassen() {

        Benutzer benutzer1= new Benutzer("Maryline", "testPwd123");
        Benutzer benutzer2= new Benutzer("Tester", "testMyCode99");

        ArrayList<Spielraum> spielraums = new ArrayList<>();
        Spielraum raum1 = new Spielraum(1);
        spielraums.add(raum1);

        lobby.spielraumBeitreten(benutzer1.getBenutzername(),1);
        lobby.spielraumBeitreten(benutzer2.getBenutzername(),1);
        assertEquals(spielraums.size(),1);
        lobby.spielraumVerlassen("Mary",1);
        assert(spielraums.get(0).spielerList().size()==1); //Anzahl der Spieler in Raum 1

    }



    @Test
    void spielRaumLoeschen() {
        ArrayList<Spielraum> spielraums = new ArrayList<>();
        Spielraum raum1 = new Spielraum(1);
        Spielraum raum2 = new Spielraum(2);
        spielraums.add(raum1);
        spielraums.add(raum2);

        assert(spielraums.size()==2);
        lobby.spielraumLoeschen(2);
        assertEquals(spielraums.size(),1);

        lobby.spielraumLoeschen(1);
        assertEquals(spielraums.size(),0);


    }

    @Test
    void spielraumHinzufuegen(){
        ArrayList<Spielraum> spielraums = new ArrayList<>();
        Spielraum raum1 = new Spielraum(1);
        Spielraum raum2 = new Spielraum(2);
        spielraums.add(raum1);
        spielraums.add(raum2);

        assertEquals(spielraums.size(),2);
        lobby.spielraumHinzufuegen(3);
        assertEquals(spielraums.size(),3);
    }

    @Test
    void getSpieler(){
        Benutzer benutzer1= new Benutzer("Maryline", "testPwd123");
        Benutzer benutzer2= new Benutzer("Tester", "testMyCode99");

        ArrayList<Spielraum> spielraums = new ArrayList<>();
        Spielraum raum1 = new Spielraum(1);
        spielraums.add(raum1);

        lobby.spielraumBeitreten(benutzer1.getBenutzername(),1);
        lobby.spielraumBeitreten(benutzer2.getBenutzername(),1);

        assert(spielraums.get(0).spielerList().size()==2); //Anzahl der Spieler in Raum 1

        ArrayList<String> spielerList = new ArrayList<>();
        spielerList.add(benutzer1.getBenutzername());
        spielerList.add(benutzer2.getBenutzername());

        assertEquals(spielerList,lobby.getSpieler(1));

    }


}