package JavaRMI;

import Spielen.Chips;
import Spielen.Karte;
import Spielen.Spielstatus;
import java.rmi.Remote;

/*Remote Interface um Spielzüge an Server zu übermitteln und Clients mit Server synchron zu halten
*@author:Nick Jochum*/
public interface Spielverwaltung extends Remote {
    Spielstatus getSpielstatus();
        /*Liefert eine aktuelle Kopie des von RMIServer verwalteten Spielstatus
        * @return Spielstatus instanz, sollte != null sein*/
    void spielStarten();
        /*Markiert das Spiel als laufend, Karten werden verteilt, ein zufälliger Spieler ist am Zug*/
    void ziehen(String username)throws IllegalArgumentException;
        /*Oberste Karte wird vom Nachziehstapel entfernt und zu den Handkarten des Spielers mit Username username hizu-
        * gefügt.
        * @param Username eines Spielers im Spielraum !=null
        * @throws InvalidArgumentException*/
    void ablegen(Karte karte, String username) throws IllegalArgumentException;
        /*Übergebene Handkarte wird von Hand des Spielers mit Username username entfernt und oben zum Ablagestapel
        * hinzugefügt
        * @param Username eines Spielers im Spielraum !=null, Karte in der Hand des Spielers mit Username username
        * !=null, die gemäß der Spielregeln zur obersten Karte des Ablagestapels passt
        * @throws InvalidArgumentException*/
    void aussteigen(String username)throws IllegalArgumentException, IllegalStateException;
        /*Spieler mit Username username wird als ausgestiegen in der entsprechenden Spielstatus-Instanz markiert
        * @param Username eines Spielers im Spielraum !=null
        * @throws InvalidArgumentException, IllegalStateException */
    void tauschechips(String username,Boolean zehnzueins) throws IllegalStateException;
        /*Spieler mit Username username tauscht 10 1-er Chips gegen ein 10-er Chip oder umgekehrt
        * @param Username eines Spielers im Spielraum !=null,Boolscher Wert != null, wenn true: 10 1er Chips gegen ein
        * 10er Chip wenn false: umgekehrt
        * @throws IllegalStateException */
    void chipabgeben(String username, Boolean einserchip);
        /*Ein Chip wird von den Chips des Spielers mit Username username entfernt
        * @param Username eines Spielers im Spielraum !=null,Boolscher Wert != null, wenn true: gebe 1er-Chip ab, wenn
        * false: gebe 10er-Chip ab*/
}
