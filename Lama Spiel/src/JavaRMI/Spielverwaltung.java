package JavaRMI;

import Spielen.Chips;
import Spielen.Karte;
import Spielen.Spielstatus;

public interface Spielverwaltung {
    Spielstatus getSpielstatus();
    void spielStarten();
    void ziehen();
    void ablegen(Karte karte);
    void aussteigen();
    void tauschechips(Chips chips);
    void chipabgeben(Chips chip);
}
