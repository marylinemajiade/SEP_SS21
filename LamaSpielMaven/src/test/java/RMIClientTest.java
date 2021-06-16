import Konto.Benutzer;
import Konto.BenutzerVerwalten;
import RMI.RMIClient;
import RMI.RMIServerIF;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.rmi.RemoteException;

import static org.junit.jupiter.api.Assertions.*;

class RMIClientTest {

    RMIServerIF rmiserver;
    Benutzer benutzer =new Benutzer();


    @BeforeEach
    void setUp() throws RemoteException {
        System.out.println("Instantiierung der Klasse RMIClient");
        RMIClient rmiclient = new RMIClient(rmiserver,benutzer.getBenutzername());
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