import Highscore.Bestenliste;
import RMI.RMIServerIF;
import SpielLobby.Lobby;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class BotSchwerTest {

    private RMIServerIF rmiServerIFMock = mock(RMIServerIF.class);
    private Bestenliste bestenlisteMock = mock(Bestenliste.class);
    private Lobby lobbyMock = mock(Lobby.class);


    @BeforeEach
    void setUp() {
    }

    @Test
    void aktualisiereSpielstatus() {
    }

    @Test
    void uebertrageChatnachricht() {
    }

    @Test
    void getBenutzername() {
    }

    @Test
    void akutalisiereBestenliste() {
    }

    @Test
    void aktualisiereSpielraeume() {
    }

    @Test
    void setSpielraum() {
    }

    @Test
    void isBot() {
    }
}