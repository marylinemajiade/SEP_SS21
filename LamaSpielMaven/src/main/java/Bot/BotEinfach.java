package Bot;

import Highscore.Bestenliste;
import RMI.RMIClientIF;
import RMI.RMIServerIF;
import Spiel.Spielrunde;
import SpielLobby.Lobby;
import fachlicheExceptions.ZustellungNachrichtNichtMoeglichException;
import fachlicheExceptions.ungueltigerBenutzernameException;

import java.rmi.RemoteException;


/**
 * @author Maryline Majiade
 */

public class BotEinfach implements RMIClientIF {

    static int botId=0;
    RMIServerIF server;
    public BotEinfach(RMIServerIF server){
        this.server = server;
    }

    /**
     * Die Methode überprüft, ob der Bot an der Reihe ist und dann ggf.
     * einen gültigen Spielzug an das Server-Objekt weiter gibt
     * @param spielrunde Spielraums
     */
    @Override
    public void aktualisiereSpielstatus(Spielrunde spielrunde) throws RemoteException {
        try {
            if(spielrunde.anDerReihe().equals(getBenutzername())){ //prüft ob Bot an der Reihe ist

                if(spielrunde.getHandkarten(getBenutzername()).size()<1){ //falls ja, prüft Anzahl der Handkarten
                    spielrunde.aussteigen(getBenutzername());
                    spielrunde.getPunkte();
                }
                spielrunde.karteAblegen(getBenutzername(),spielrunde.getHandkarten(getBenutzername()).get(0));
            }
        }

        catch(Exception e){

        }

    }


    /**
     * Die Methode liefert den Name vom aktuellem Bot
     * @return Botname
     */
    @Override
    public String getBenutzername() throws RemoteException, ungueltigerBenutzernameException {
        botId++;
        return "Bot"+botId;
    }

    /**
     * Die Methode aktualisiert die Bestenliste indem sie den Highscore
     * vom Bot in die Liste hinzufügt
     * @param bestenliste Bestenliste die aktualisiert werden muss
     */
    @Override
    public void akutalisiereBestenliste(Bestenliste bestenliste) throws RemoteException {
        try {

            bestenliste.eintragHinzufuegen(getBenutzername(),true);

        }

        catch(Exception e){

        }


    }

    /**
     * Die Methode aktualisiert den Spielraum in der Lobby wenn ein Bot beitritt
     * @param lobby Lobby
     */
    @Override
    public void aktualisiereSpielraeume(Lobby lobby) throws RemoteException {
        try{

            lobby.spielraumBeitreten(getBenutzername(), lobby.getSpielraum_Ids().get(0));

        }

        catch(Exception e){

        }

    }

    @Override
    public void uebertrageChatnachricht(String benutzername, String nachricht) throws RemoteException, ungueltigerBenutzernameException, ZustellungNachrichtNichtMoeglichException {

    }

    @Override
    public void setSpielraum(Spielrunde spielrunde) throws RemoteException {

    }


    /**
     * Die Methode gibt an, die Intanz ein Bot ist oder nicht
     * @return boolean
     */
    @Override
    public boolean isBot() throws RemoteException {
        return true;
    }
}
