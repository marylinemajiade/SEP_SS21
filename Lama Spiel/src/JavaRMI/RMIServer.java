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
    public void ziehen(String username) throws IllegalArgumentException {

    }

    @Override
    public void ablegen(Karte karte, String username) throws IllegalArgumentException {

    }

    @Override
    public void aussteigen(String username) throws IllegalArgumentException, IllegalStateException {

    }

    @Override
    public void tauschechips(String username, Boolean zehnzueins) throws IllegalStateException {

    }

    @Override
    public void chipabgeben(String username, Boolean einserchip) {

    }

}
