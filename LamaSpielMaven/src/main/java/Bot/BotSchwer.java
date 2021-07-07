package Bot;

import GUI.Chat;
import Highscore.Bestenliste;
import RMI.RMIClientIF;
import RMI.RMIServerIF;
import Spiel.Spielrunde;
import SpielLobby.Lobby;
import fachlicheExceptions.ZustellungNachrichtNichtMoeglichException;
import fachlicheExceptions.ungueltigerBenutzernameException;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;


/**
 * @author Maryline Majiade
 */

public class BotSchwer implements RMIClientIF {

    static int botId=0;
    RMIServerIF server;
    public BotSchwer(RMIServerIF server){
        this.server = server;
    }

    /**
     * Die Methode überprüft, ob der Bot an der Reihe ist und dann ggf.
     * einen gültigen Spielzug an das Server-Objekt weiter gibt
     * @param spielrunde Spielraums
     */
    @Override
    public void aktualisiereSpielstatus(Spielrunde spielrunde) throws RemoteException {
        int k = spielrunde.getAblagestapel().peek();
        try {
            if(spielrunde.anDerReihe().equals(getBenutzername())){ //prüft ob Bot an der Reihe ist

                if(spielrunde.getHandkarten(getBenutzername()).size()<1){ //falls ja, prüft Anzahl der Handkarten
                    server.aussteigen(getBenutzername(), spielrunde.getRaumId());
                    spielrunde.getPunkte();
                }
                else{
                    for(int i=0; i<=spielrunde.getHandkarten(getBenutzername()).size(); i++){
                        int handkarte = spielrunde.getHandkarten(getBenutzername()).get(i);
                        int indexOfHandkarte = spielrunde.getHandkarten(getBenutzername()).indexOf(handkarte);
                        if(k==handkarte || k+1==handkarte || k==0){ //Bedingungen fürs Ablegen einer Handkarte
                            server.karteAblegen(handkarte, getBenutzername(), spielrunde.getRaumId());
                            spielrunde.getHandkarten(getBenutzername()).remove(indexOfHandkarte); //remove abgelegte Karte von den Handkarten
                            //Wechsele Spieler am Zug
                            spielerWechsel(spielrunde);

                        }
                        else {
                            HashMap<String, ArrayList<Integer>> hashMapKarten = spielrunde.getHandkartenSpieler();
                            Set<String> spielerNamen = hashMapKarten.keySet();
                            List<String> stringsList = new ArrayList<>(spielerNamen);
                            int countKarten = 0;
                            for(int j=0; i<=stringsList.size(); i++){
                                if(!stringsList.get(j).equals(getBenutzername())){ //prüft ob der Spieler an der aktuellen Position kein Bot ist
                                    countKarten = countKarten + hashMapKarten.get(stringsList.get(j)).size(); //zählt Anzahl der Handkarten vom aktuellen Spieler
                                }
                            }
                            if(countKarten<=1){
                                server.aussteigen(getBenutzername(), spielrunde.getRaumId());
                            }
                            else {  //Nachziehstapel leer
                                if(!spielrunde.getNachziehstapel().isEmpty()) {
                                    server.karteZiehen(getBenutzername(), spielrunde.getRaumId());
                                }
                                else {
                                    server.aussteigen(getBenutzername(), spielrunde.getRaumId());
                                }

                            }
                            //Wechsele Spieler am Zug
                            spielerWechsel(spielrunde);
                        }
                    }

                }
            }
        }

        catch(Exception e){

        }
    }

    /**
     * Die Methode wechsele der Spieler, der am Zug ist
     * @param spielrunde Spielrunde
     */
    private void spielerWechsel(Spielrunde spielrunde) {
        int updateZugIndex = spielrunde.getAmZugIndex();
        if(updateZugIndex>= spielrunde.spielerInRunde.size()) {
            updateZugIndex=0;
            spielrunde.setAmZugIndex(updateZugIndex);
        }
        else{
            updateZugIndex += 1;
            spielrunde.setAmZugIndex(updateZugIndex);
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
     * Die Methode aktualisiert den Spielraum in der Lobby wenn ein Bot einen Spielraum beitritt
     * @param lobby Lobby
     */
    @Override
    public void aktualisiereSpielraeume(Lobby lobby) throws RemoteException {
        try{

            server.spielraumBeitreten(getBenutzername(), lobby.getSpielraum_Ids().get(0));

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
    public boolean isBot() throws RemoteException, ungueltigerBenutzernameException {
        return getBenutzername().contains("Bot");
    }
}
