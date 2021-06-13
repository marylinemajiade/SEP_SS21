

import RMI.*;
import RMI.RMIServer;
import Spiel.Spielrunde;
import SpielLobby.Lobby;
import fachlicheExceptions.ZustellungNachrichtNichtMoeglichException;
import fachlicheExceptions.benutzerNameVergebenException;
import fachlicheExceptions.ungueltigeSpielraumIDException;
import fachlicheExceptions.ungueltigerBenutzernameException;
import org.junit.jupiter.api.Assertions;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RMIServerTest {

    @org.junit.jupiter.api.Test
    void registriereClient() throws RemoteException, ungueltigerBenutzernameException {
        RMIServer rmiserver = new RMIServer();
        RMIClientIF client1 = mock(RMIClientIF.class);
        RMIClientIF client2 = mock(RMIClientIF.class);
        RMIClientIF client3 = mock(RMIClientIF.class);
        RMIClientIF client4 = mock(RMIClientIF.class);
        rmiserver.registriereClient(client1);
        rmiserver.registriereClient(client2);
        rmiserver.registriereClient(client3);

        try{
            //nach Registrierung befinden sich Clients in der Lobby (id=0) und sollten Nachricht erhalten
            rmiserver.benutzerRegistrieren("benutzername","12345678");
            rmiserver.sendeChatnachricht("benutzername",0,"test");
            Mockito.verify(client1).uebertrageChatnachricht("benutzername","test");
            Mockito.verify(client2).uebertrageChatnachricht("benutzername","test");
            Mockito.verify(client3).uebertrageChatnachricht("benutzername","test");
            Mockito.verify(client4, never()).uebertrageChatnachricht("benutzername","test");
        }catch(Exception ignored){}
    }

    @org.junit.jupiter.api.Test
    void benutzerdatenPruefen() throws RemoteException {
        RMIServer rmiserver = new RMIServer();
        try {
            rmiserver.benutzerRegistrieren("LAMAKönig123", "s3cr3tP4ssw0rd");
        }catch(Exception ignored){}
        assertTrue(rmiserver.benutzerdatenPruefen("LAMAKönig123", "s3cr3tP4ssw0rd"));
        assertFalse(rmiserver.benutzerdatenPruefen("LAMAKönig123", "s3cr3tP4ssword"));
        assertFalse(rmiserver.benutzerdatenPruefen("LAMAKönig", "s3cr3tP4ssw0rd"));
        assertFalse(rmiserver.benutzerdatenPruefen("LAMAKönig", "s3cr3tP4ssword"));
    }

    @org.junit.jupiter.api.Test
    void benutzerRegistrieren() throws RemoteException {
        RMIServer rmiserver = new RMIServer();
        try {
            rmiserver.benutzerRegistrieren("Andi", "12345678");

        }catch(benutzerNameVergebenException ignored){}
        assertThrows(fachlicheExceptions.ungueltigerBenutzernameException.class,
                ()-> rmiserver.benutzerRegistrieren("Andi", "dfadfadfad"));
        assertThrows(fachlicheExceptions.ungueltigerBenutzernameException.class,
                ()->{rmiserver.benutzerRegistrieren("Bot", "34523452345");});
        assertThrows(fachlicheExceptions.ungueltigerBenutzernameException.class,
                ()->{rmiserver.benutzerRegistrieren("Bot122345", "5345345");});
        assertThrows(fachlicheExceptions.ungueltigerBenutzernameException.class,
                ()->{rmiserver.benutzerRegistrieren("BotderBaumeister", "WendyundBot");});
    }

    @org.junit.jupiter.api.Test
    void benutzerLoeschen() throws benutzerNameVergebenException, ungueltigerBenutzernameException, RemoteException {
        RMIServer rmiserver = new RMIServer();
            rmiserver.benutzerRegistrieren("LamaKönig123", "dorwssaP");
        assertTrue(rmiserver.benutzerdatenPruefen("LamaKönig123", "dorwssaP"));
        rmiserver.benutzerLoeschen("LamaKönig123");
        assertFalse(rmiserver.benutzerdatenPruefen("LamaKönig123", "dorwssaP"));
        try{rmiserver.benutzerRegistrieren("LamaKönig123", "dsfasdfasdf");}
        catch(benutzerNameVergebenException ignored){fail("Benutzername sollte wieder frei sein");}

    }

    @org.junit.jupiter.api.Test
    void sendeChatnachricht() throws RemoteException, ungueltigerBenutzernameException, ungueltigeSpielraumIDException,
            ZustellungNachrichtNichtMoeglichException {
        RMIServer rmiserver = new RMIServer();
        RMIClientIF client1 = mock(RMIClientIF.class);
        RMIClientIF client2 = mock(RMIClientIF.class);
        RMIClientIF client3 = mock(RMIClientIF.class);
        rmiserver.registriereClient(client1);
        rmiserver.registriereClient(client2);
        Mockito.when(client1.getBenutzername()).thenReturn("client1");
        Mockito.when(client2.getBenutzername()).thenReturn("client2");
        Mockito.when(client3.getBenutzername()).thenReturn("client3");
        rmiserver.spielraumErstellen("client1");
        rmiserver.sendeChatnachricht("client2", 0, "Test");
        Mockito.verify(client1, never()).uebertrageChatnachricht("client1","Test");
        Mockito.verify(client3).uebertrageChatnachricht("client1","Test");


    }

    @org.junit.jupiter.api.Test
    void spielraumErstellen() throws RemoteException, ungueltigerBenutzernameException {
        RMIServer rmiserver = new RMIServer();
        RMIClientIF client1 = mock(RMIClientIF.class);
        RMIClientIF client2 = mock(RMIClientIF.class);
        RMIClientIF client3 = mock(RMIClientIF.class);
        Mockito.when(client1.getBenutzername()).thenReturn("client1");
        Mockito.when(client2.getBenutzername()).thenReturn("client2");
        Mockito.when(client3.getBenutzername()).thenReturn("client3");
        rmiserver.registriereClient(client1);
        rmiserver.registriereClient(client2);
        rmiserver.registriereClient(client3);
        rmiserver.spielraumErstellen("client1");
        Mockito.verify(client1, never()).aktualisiereSpielraeume(any(Lobby.class));
        Mockito.verify(client2, never()).aktualisiereSpielraeume(any(Lobby.class));
        Mockito.verify(client3, never()).aktualisiereSpielraeume(any(Lobby.class));
        assertThrows(fachlicheExceptions.ungueltigerBenutzernameException.class,
                ()->{rmiserver.spielraumErstellen("client4");});
    }

    @org.junit.jupiter.api.Test
    void spielraumBeitreten() throws RemoteException, ungueltigerBenutzernameException {
        RMIServer rmiserver = new RMIServer();
        RMIClientIF client1 = mock(RMIClientIF.class);
        RMIClientIF client2 = mock(RMIClientIF.class);
        RMIClientIF client3 = mock(RMIClientIF.class);
        Mockito.when(client1.getBenutzername()).thenReturn("client1");
        Mockito.when(client2.getBenutzername()).thenReturn("client2");
        Mockito.when(client3.getBenutzername()).thenReturn("client3");
        rmiserver.registriereClient(client1);
        rmiserver.registriereClient(client2);
        rmiserver.registriereClient(client3);
        rmiserver.spielraumErstellen("client1");
        assertThrows(fachlicheExceptions.ungueltigerBenutzernameException.class,
                ()->{rmiserver.spielraumBeitreten("client4",1);});

    }

    @org.junit.jupiter.api.Test
    void spielraumVerlassen() throws RemoteException {
        RMIServer rmiserver = new RMIServer();
        Lobby lobby = mock(Lobby.class);

    }

    @org.junit.jupiter.api.Test
    void botHinzufuegen() throws RemoteException {
        RMIServer rmiserver = new RMIServer();
    }

    @org.junit.jupiter.api.Test
    void botEntfernen() throws RemoteException{
        RMIServer rmiserver = new RMIServer();
    }

    @org.junit.jupiter.api.Test
    void spielStarten() throws RemoteException{
        RMIServer rmiserver = new RMIServer();
    }

    @org.junit.jupiter.api.Test
    void chipsTauschen() throws RemoteException{
        RMIServer rmiserver = new RMIServer();
    }

    @org.junit.jupiter.api.Test
    void chipAbgeben() throws RemoteException{
        RMIServer rmiserver = new RMIServer();
    }

    @org.junit.jupiter.api.Test
    void karteAblegen() throws RemoteException{
        RMIServer rmiserver = new RMIServer();
    }

    @org.junit.jupiter.api.Test
    void karteZiehen() throws RemoteException{
        RMIServer rmiserver = new RMIServer();

    }

    @org.junit.jupiter.api.Test
    void aussteigen() throws RemoteException{
        RMIServer rmiserver = new RMIServer();
    }

}