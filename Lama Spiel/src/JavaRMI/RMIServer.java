package JavaRMI;
import Spielen.Chips;
import Spielen.Karte;
import Spielen.Spielstatus;
import java.rmi.*;

public class RMIServer implements Spielverwaltung {

    @Override
    public Spielstatus getSpielstatus() {
        return null;
    }

    @Override
    public void spielStarten() {

    }

    @Override
    public void ziehen() {

    }

    @Override
    public void ablegen(Karte karte) {

    }

    @Override
    public void aussteigen() {

    }

    @Override
    public void tauschechips(Chips chips) {

    }

    @Override
    public void chipabgeben(Chips chip) {

    }
}
