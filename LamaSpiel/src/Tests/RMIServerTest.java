package Tests;


import RMI.RMIClientIF;
import RMI.RMIServer;
import Spiel.Spielrunde;
import org.junit.Assert;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;
import java.rmi.RemoteException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class RMIServerTest {

    @org.junit.jupiter.api.Test
    void registriereClient() throws RemoteException {
        
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
    void benutzerRegistrieren() throws RemoteException {
        Spielrunde sp = mock(Spielrunde.class);
        RMIServer server = new RMIServer();
        sp.getHandkarten("Test");
        Mockito.verify(sp).getHandkarten("Test");
    }

    @org.junit.jupiter.api.Test
    void benutzerLoeschen() {
    }

    @org.junit.jupiter.api.Test
    void sendeChatnachricht() {
    }

    @org.junit.jupiter.api.Test
    void spielraumBeitreten() {
    }

    @org.junit.jupiter.api.Test
    void spielraumVerlassen() {
    }

    @org.junit.jupiter.api.Test
    void botHinzufuegen() {
    }

    @org.junit.jupiter.api.Test
    void botEntfernen() {
    }

    @org.junit.jupiter.api.Test
    void spielStarten() {
    }

    @org.junit.jupiter.api.Test
    void chipsTauschen() {
    }

    @org.junit.jupiter.api.Test
    void chipAbgeben() {
    }

    @org.junit.jupiter.api.Test
    void karteAblegen() {
    }

    @org.junit.jupiter.api.Test
    void karteZiehen() {

    }

    @org.junit.jupiter.api.Test
    void aussteigen() {
    }

    @org.junit.jupiter.api.Test
    void getBestenliste() {
    }

    @org.junit.jupiter.api.Test
    void getLobby() {
    }

    @org.junit.jupiter.api.Test
    void getSpielrunden() {
    }

    @org.junit.jupiter.api.Test
    void getBenutzerdaten() {
    }
}