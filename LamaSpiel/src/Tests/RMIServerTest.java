package Tests;

import RMI.RMIClient;
import RMI.RMIClientIF;
import RMI.RMIServer;
import org.junit.Assert;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class RMIServerTest {

    @org.junit.jupiter.api.Test
    void registriereClient() {
        RMIServer rmiserver = new RMIServer();
        RMIClientIF client1 = new RMIClient();
        RMIClientIF client2 = new RMIClient();
        rmiserver.registriereClient(client1);
        ArrayList<RMIClientIF> clientListe = rmiserver.getRMIClients();
        boolean clientGefunden = false;
        for (RMIClientIF client: clientListe){
            if (client == client1) {
                clientGefunden = true;
                break;
            }
        }
        assertTrue(clientGefunden);
        clientGefunden=false;
        for (RMIClientIF client: clientListe){
            if (client == client2) {
                clientGefunden = true;
                break;
            }
        }
        assertTrue(clientGefunden);
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