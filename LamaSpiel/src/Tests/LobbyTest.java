package Tests;

import SpielLobby.Lobby;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class LobbyTest {
    /**
     * @author Maryline Majiade
     */

    @InjectMocks
    //Instanz of the class under test
    private Lobby lobby;
    //Intanz of the dependencies

    @Test
    void getSpielraum_Ids() {
        //given
        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(0);
        expected.add(1);
        expected.add(2);
        expected.add(3);
        expected.add(4);
        when(this.lobby.getSpielraum_Ids()).thenReturn(expected);
        //when
        ArrayList<Integer> actual = lobby.getSpielraum_Ids();

        //then
        assertEquals(expected,actual);

    }

    @Test
    void spielRaumLoeschen() {

        //given

        //when

        //then
    }

    @Test
    void spielRaumHinzufuegen() {

        //given

        //when

        //then
    }

    @Test
    void getSpieler() {

        //given

        //when

        //then
    }

    @Test
    void spielraumBeitreten() {

        //given

        //when

        //then
    }

    @Test
    void spielraumVerlassen() {

        //given

        //when

        //then
    }
}