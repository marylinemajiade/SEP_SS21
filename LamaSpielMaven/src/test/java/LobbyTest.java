
import Konto.Benutzer;
import SpielLobby.Lobby;
import SpielLobby.Spielraum;
import fachlicheExceptions.ungueltigerBenutzernameException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;


/**
 * @author Maryline Majiade
 */

@ExtendWith(MockitoExtension.class)
class LobbyTest {

    @InjectMocks
    //Instanz of the class under test
    private Lobby lobby;
    //Intanz of the dependencies
    @Mock
    private Benutzer benutzerMock;
    @Mock
    private Spielraum spielraumMock;



    @Test
    void getSpielraum_Ids() {
        //given
        ArrayList<Spielraum> spielraums = new ArrayList<>();
        Spielraum raum0 = new Spielraum(1);
        Spielraum raum1 = new Spielraum(2);
        Spielraum raum2 = new Spielraum(3);
        Spielraum raum3 = new Spielraum(4);

        spielraums.add(raum0);
        spielraums.add(raum1);
        spielraums.add(raum2);
        spielraums.add(raum3);

        assertNotNull(spielraums);

        //when
        ArrayList<Integer> actual = lobby.getSpielraum_Ids();

        //then
        assertEquals(spielraums.indexOf(1),actual.indexOf(1));

    }


    @Test
    void spielraumBeitreten() {

        Benutzer benutzer= new Benutzer("Maryline", "testPwd123");

        ArrayList<Spielraum> spielraums = new ArrayList<>();
        Spielraum raum1 = new Spielraum(1);
        Spielraum raum2 = new Spielraum(2);
        spielraums.add(raum1);
        spielraums.add(raum2);
        assertNotNull(spielraums);

        lobby.spielraumBeitreten("Maryline", 1);

        //assertEquals(lobby.getSpielraum(1).spielerList().size(),1);
        verify(benutzerMock).getBenutzername();
    }

    @Test
    void spielraumVerlassen() {

        Benutzer benutzer1= new Benutzer("Maryline", "testPwd123");
        Benutzer benutzer2= new Benutzer("Tester", "testMyCode99");

        ArrayList<Spielraum> spielraums = new ArrayList<>();
        Spielraum raum1 = new Spielraum(1);
        Spielraum raum2 = new Spielraum(2);
        spielraums.add(raum1);
        spielraums.add(raum2);

        lobby.spielraumVerlassen(benutzer1.getBenutzername(), 1);
        lobby.spielraumVerlassen(benutzer2.getBenutzername(), 1);

        /*assertEquals(lobby.getSpielraum(1).spielerList().size(),0);
        assert(lobby.getSpielraum(1).spielerList()==null);*/
        verify(benutzerMock).getBenutzername();

    }



    @Test
    void spielRaumLoeschen() {}

    @Test
    void spielraumHinzufuegen(){}

    @Test
    void getSpieler(){}


}