

import Highscore.Bestenliste;
import RMI.*;
import RMI.RMIServer;
import Spiel.Spielrunde;
import SpielLobby.Lobby;
import fachlicheExceptions.ZustellungNachrichtNichtMoeglichException;
import fachlicheExceptions.benutzerNameVergebenException;
import fachlicheExceptions.ungueltigeSpielraumIDException;
import fachlicheExceptions.ungueltigerBenutzernameException;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;

import Konto.*;
import static org.junit.jupiter.api.Assertions.*;

class RMIServerTest {

    @org.junit.jupiter.api.Test
    void registriereClient() throws RemoteException, ungueltigerBenutzernameException {
        RMIServer rmiserver = new RMIServer(new Bestenliste(),new Lobby() ,new BenutzerVerwalten());
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
        RMIServer rmiserver = new RMIServer(new Bestenliste(),new Lobby() ,new BenutzerVerwalten());
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
        RMIServer rmiserver = new RMIServer(new Bestenliste(),new Lobby() ,new BenutzerVerwalten());
        try {
            rmiserver.benutzerRegistrieren("Andi", "12345678");

        }catch(benutzerNameVergebenException ignored){}
        assertThrows(fachlicheExceptions.ungueltigerBenutzernameException.class,
                ()-> rmiserver.benutzerRegistrieren("Andi", "dfadfadfad"));//die Exception benurzerNameVergeben muss hier geworfen werden
        assertThrows(fachlicheExceptions.ungueltigerBenutzernameException.class,
                ()->{rmiserver.benutzerRegistrieren("Bot", "34523452345");});//hier muss keine Exception geworfen wird, da wir die Name von Bots nicht vor der Registrierung behandlet
        assertThrows(fachlicheExceptions.ungueltigerBenutzernameException.class,
                ()->{rmiserver.benutzerRegistrieren("Bot122345", "5345345");});
        assertThrows(fachlicheExceptions.ungueltigerBenutzernameException.class,
                ()->{rmiserver.benutzerRegistrieren("BotderBaumeister", "WendyundBot");});
    }

    @org.junit.jupiter.api.Test
    void benutzerLoeschen() throws benutzerNameVergebenException, ungueltigerBenutzernameException, RemoteException, ungueltigeSpielraumIDException, ZustellungNachrichtNichtMoeglichException {
        RMIServer rmiserver = new RMIServer(new Bestenliste(),new Lobby() ,new BenutzerVerwalten());
        rmiserver.benutzerRegistrieren("LamaKönig123", "dorwssaP");
        RMIClientIF lamaKoenig123 = mock(RMIClientIF.class);
        Mockito.when(lamaKoenig123.getBenutzername()).thenReturn("LamaKönig123");
        assertTrue(rmiserver.benutzerdatenPruefen("LamaKönig123", "dorwssaP"));
        rmiserver.benutzerLoeschen("LamaKönig123");
        rmiserver.sendeChatnachricht("Benutzer",0,"test");
        Mockito.verify(lamaKoenig123,never()).uebertrageChatnachricht("Benutzername","test");
        assertFalse(rmiserver.benutzerdatenPruefen("LamaKönig123", "dorwssaP"));//Methode in BenutzerVerwalten geändert
        try{rmiserver.benutzerRegistrieren("LamaKönig123", "dsfasdfasdf");}
        catch(benutzerNameVergebenException ignored){fail("Benutzername sollte wieder frei sein");}

    }

    @org.junit.jupiter.api.Test
    void sendeChatnachricht() throws RemoteException, ungueltigerBenutzernameException, ungueltigeSpielraumIDException,
            ZustellungNachrichtNichtMoeglichException, benutzerNameVergebenException {
        Bestenliste bestenliste = mock(Bestenliste.class);
        Lobby lobby = mock(Lobby.class);
        BenutzerVerwalten benutzerVerwalten = mock(BenutzerVerwalten.class);
        RMIServer rmiserver = new RMIServer(bestenliste,lobby,benutzerVerwalten);
        RMIClientIF client1 = mock(RMIClientIF.class);
        RMIClientIF client2 = mock(RMIClientIF.class);
        RMIClientIF client3 = mock(RMIClientIF.class);
        rmiserver.benutzerRegistrieren("client1","123423425");
        rmiserver.benutzerRegistrieren("client2","123423425");
        rmiserver.registriereClient(client1);
        rmiserver.registriereClient(client2);
        Mockito.when(client1.getBenutzername()).thenReturn("client1");
        Mockito.when(client2.getBenutzername()).thenReturn("client2");
        Mockito.when(client3.getBenutzername()).thenReturn("client3");
        Mockito.when(lobby.getSpieler(0)).thenReturn(new ArrayList<>(Arrays.asList("client1","client2")));
        rmiserver.spielraumErstellen("client1");
        rmiserver.sendeChatnachricht("client2", 0, "Test");
        Mockito.verify(client1, never()).uebertrageChatnachricht("client1","Test");
        Mockito.verify(client3).uebertrageChatnachricht("client1","Test");
        assertThrows(fachlicheExceptions.ungueltigerBenutzernameException.class,
                ()->{rmiserver.sendeChatnachricht("client3",0,"Test");});


    }

    @org.junit.jupiter.api.Test
    void spielraumErstellen() throws RemoteException, ungueltigerBenutzernameException {
        RMIServer rmiserver = new RMIServer(new Bestenliste(),new Lobby() ,new BenutzerVerwalten());
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
        RMIServer rmiserver = new RMIServer(new Bestenliste(),new Lobby() ,new BenutzerVerwalten());
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
        RMIServer rmiserver = new RMIServer(new Bestenliste(),new Lobby() ,new BenutzerVerwalten());
        Lobby lobby = mock(Lobby.class);

    }

    @org.junit.jupiter.api.Test
    void botHinzufuegen() throws RemoteException {
        RMIServer rmiserver = new RMIServer(new Bestenliste(),new Lobby() ,new BenutzerVerwalten());
    }

    @org.junit.jupiter.api.Test
    void botEntfernen() throws RemoteException{
        RMIServer rmiserver = new RMIServer(new Bestenliste(),new Lobby() ,new BenutzerVerwalten());
    }

    @org.junit.jupiter.api.Test
    void spielStarten() throws RemoteException{
        RMIServer rmiserver = new RMIServer(new Bestenliste(),new Lobby() ,new BenutzerVerwalten());
    }

    @org.junit.jupiter.api.Test
    void chipsTauschen() throws RemoteException{
        RMIServer rmiserver = new RMIServer(new Bestenliste(),new Lobby() ,new BenutzerVerwalten());
    }

    @org.junit.jupiter.api.Test
    void chipAbgeben() throws RemoteException{
        RMIServer rmiserver = new RMIServer(new Bestenliste(),new Lobby() ,new BenutzerVerwalten());
    }

    @org.junit.jupiter.api.Test
    void karteAblegen() throws RemoteException{
        RMIServer rmiserver = new RMIServer(new Bestenliste(),new Lobby() ,new BenutzerVerwalten());
    }

    @org.junit.jupiter.api.Test
    void karteZiehen() throws RemoteException{
        RMIServer rmiserver = new RMIServer(new Bestenliste(),new Lobby() ,new BenutzerVerwalten());

    }

    @org.junit.jupiter.api.Test
    void aussteigen() throws RemoteException{
        RMIServer rmiserver = new RMIServer(new Bestenliste(),new Lobby() ,new BenutzerVerwalten());
    }

}