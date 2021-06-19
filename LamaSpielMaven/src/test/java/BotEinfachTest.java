import Bot.BotEinfach;
import Konto.Benutzer;
import RMI.RMIServerIF;
import Spiel.Spielrunde;
import SpielLobby.Lobby;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import Highscore.Bestenliste;
import RMI.*;
import RMI.RMIServer;
import Spiel.Spielrunde;
import SpielLobby.Lobby;
import fachlicheExceptions.*;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;

import Konto.*;
import static org.junit.jupiter.api.Assertions.*;


/**
 * @author Maryline Majiade
 */


class BotEinfachTest {

    RMIServerIF rmiserver;
    private BotEinfach botEinfach = new BotEinfach(rmiserver);


    @Test
    void aktualisiereSpielstatus() {


    }

    @Test
    void getBenutzername() {


    }

    @Test
    void akutalisiereBestenliste(){

    }

    @Test
    void aktualisiereSpielraeume(){


    }

    @Test
    void uebertrageChatnachricht(){

    }
    @Test
    void setSpielraum(){

    }

    @Test
    void isBot()throws RemoteException {
        try {
            botEinfach.isBot();
        }catch(Exception ignored){}
        assertTrue(botEinfach.isBot());
    }


}