package Tests;


import RMI.*;
import RMI.RMIServer;
import Spiel.Spielrunde;
import fachlicheExceptions.benutzerNameVergebenException;
import fachlicheExceptions.ungueltigerBenutzernameException;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import java.rmi.RemoteException;

import static org.junit.jupiter.api.Assertions.*;

class RMIServerTest {

    @org.junit.jupiter.api.Test
    void registriereClient() throws RemoteException {
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
    void benutzerdatenPruefen() {
        RMIServer rmiserver = new RMIServer();
        try {
            rmiserver.benutzerRegistrieren("LAMAKönig123", "s3cr3tP4ssw0rd");
        }catch(Exception ignored){};
        assertTrue(rmiserver.benutzerdatenPruefen("LAMAKönig123", "s3cr3tP4ssw0rd"));
        assertFalse(rmiserver.benutzerdatenPruefen("LAMAKönig123", "s3cr3tP4ssword"));
        assertFalse(rmiserver.benutzerdatenPruefen("LAMAKönig", "s3cr3tP4ssw0rd"));
        assertFalse(rmiserver.benutzerdatenPruefen("LAMAKönig", "s3cr3tP4ssword"));
    }

    @org.junit.jupiter.api.Test
    void benutzerRegistrieren() {
        RMIServer rmiserver = new RMIServer();
        try {
            rmiserver.benutzerRegistrieren("Andi", "12345678");

        }catch(benutzerNameVergebenException ignored){}
        assertThrows(fachlicheExceptions.ungueltigerBenutzernameException.class,
                ()->{rmiserver.benutzerRegistrieren("Andi", "dfadfadfad");});
        assertThrows(fachlicheExceptions.ungueltigerBenutzernameException.class,
                ()->{rmiserver.benutzerRegistrieren("Bot", "34523452345");});
        assertThrows(fachlicheExceptions.ungueltigerBenutzernameException.class,
                ()->{rmiserver.benutzerRegistrieren("Bot122345", "5345345");});
        assertThrows(fachlicheExceptions.ungueltigerBenutzernameException.class,
                ()->{rmiserver.benutzerRegistrieren("BotderBaumeister", "WendyundBot");});
    }

    @org.junit.jupiter.api.Test
    void benutzerLoeschen() throws benutzerNameVergebenException, ungueltigerBenutzernameException {
        RMIServer rmiserver = new RMIServer();
            rmiserver.benutzerRegistrieren("LamaKönig123", "dorwssaP");
        assertTrue(rmiserver.benutzerdatenPruefen("LamaKönig123", "dorwssaP"));
        rmiserver.benutzerLoeschen("LamaKönig123");
        assertFalse(rmiserver.benutzerdatenPruefen("LamaKönig123", "dorwssaP"));
        try{rmiserver.benutzerRegistrieren("LamaKönig123", "dsfasdfasdf");}
        catch(benutzerNameVergebenException ignored){fail("Benutzername sollte wieder frei sein");}

    }

    @org.junit.jupiter.api.Test
    void sendeChatnachricht() {
        RMIServer rmiserver = new RMIServer();
        RMIClientIF client1 = mock(RMIClientIF.class);
        RMIClientIF client2 = mock(RMIClientIF.class);
        RMIClientIF client3 = mock(RMIClientIF.class);
        RMIClientIF client4 = mock(RMIClientIF.class);
        rmiserver.registriereClient(client1);
        rmiserver.registriereClient(client2);
        rmiserver.registriereClient(client3);
        rmiserver.registriereClient(client4);
    }

    @org.junit.jupiter.api.Test
    void spielraumBeitreten() {
        RMIServer rmiserver = new RMIServer();
    }

    @org.junit.jupiter.api.Test
    void spielraumVerlassen() {
        RMIServer rmiserver = new RMIServer();
    }

    @org.junit.jupiter.api.Test
    void botHinzufuegen() {
        RMIServer rmiserver = new RMIServer();
    }

    @org.junit.jupiter.api.Test
    void botEntfernen() {
        RMIServer rmiserver = new RMIServer();
    }

    @org.junit.jupiter.api.Test
    void spielStarten() {
        RMIServer rmiserver = new RMIServer();
    }

    @org.junit.jupiter.api.Test
    void chipsTauschen() {
        RMIServer rmiserver = new RMIServer();
    }

    @org.junit.jupiter.api.Test
    void chipAbgeben() {
        RMIServer rmiserver = new RMIServer();
    }

    @org.junit.jupiter.api.Test
    void karteAblegen() {
        RMIServer rmiserver = new RMIServer();
    }

    @org.junit.jupiter.api.Test
    void karteZiehen() {
        RMIServer rmiserver = new RMIServer();

    }

    @org.junit.jupiter.api.Test
    void aussteigen() {
        RMIServer rmiserver = new RMIServer();
    }

}