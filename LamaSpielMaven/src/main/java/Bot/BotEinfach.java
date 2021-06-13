package Bot;

import Highscore.Bestenliste;
import RMI.RMIClientIF;
import RMI.RMIServerIF;
import Spiel.Spielrunde;
import SpielLobby.Lobby;
import fachlicheExceptions.ZustellungNachrichtNichtMoeglichException;
import fachlicheExceptions.ungueltigerBenutzernameException;

import java.rmi.RemoteException;

public class BotEinfach implements RMIClientIF {
    RMIServerIF server;
    public BotEinfach(RMIServerIF server){
        this.server = server;
    }
    @Override
    public void aktualisiereSpielstatus(Spielrunde spielrunde) throws RemoteException {

    }

    @Override
    public void uebertrageChatnachricht(String benutzername, String nachricht) throws RemoteException, ungueltigerBenutzernameException, ZustellungNachrichtNichtMoeglichException {

    }

    @Override
    public String getBenutzername() throws RemoteException, ungueltigerBenutzernameException {
        return null;
    }

    @Override
    public void akutalisiereBestenliste(Bestenliste bestenliste) throws RemoteException {

    }

    @Override
    public void aktualisiereSpielraeume(Lobby lobby) throws RemoteException {

    }

    @Override
    public void setSpielraum() throws RemoteException {

    }

    @Override
    public boolean isBot() throws RemoteException {
        return false;
    }
}
