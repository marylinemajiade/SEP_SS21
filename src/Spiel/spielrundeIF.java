package Spiel;

import fachlicheExceptions.spielLaeuftBereitsException;
import fachlicheExceptions.ungueltigerBenutzernameException;
import fachlicheExceptions.ungueltigerSpielzugException;
import fachlicheExceptions.zuWenigSpielerException;

import java.rmi.Remote;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public interface spielrundeIF extends Remote {

    void karteAblegen(String benutzername, int karte) throws ungueltigerBenutzernameException, ungueltigerSpielzugException;

    int karteZiehen(String benutzername) throws ungueltigerSpielzugException, ungueltigerBenutzernameException;

    void aussteigen(String benutzername) throws ungueltigerSpielzugException, ungueltigerBenutzernameException;

    void chipsTauschen(boolean zehnergegeneiner, String benutzername) throws ungueltigerBenutzernameException, ungueltigerSpielzugException;

    void chipAbgeben(boolean zehnerchip, String benutzername) throws ungueltigerBenutzernameException,
                    ungueltigerSpielzugException;
    String anDerReihe();


    void spielStarten() throws spielLaeuftBereitsException, zuWenigSpielerException;

    ArrayList<Integer> getHandkarten(String benutzername) throws ungueltigerBenutzernameException;


    Stack<Integer> getAblagestapel();


    Stack<Integer> getNachziehstapel();

    HashMap<String, ArrayList<Integer>> getHandkartenSpieler();
    Chipstapel getChipstapel(String benutzername) throws ungueltigerBenutzernameException;
    void spielraumVerlassen(String benutzername) throws ungueltigerBenutzernameException;



}

