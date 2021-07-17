package fachlicheExceptions;

public class zuWenigSpielerException extends Exception{
    public zuWenigSpielerException(String errorMessage) {
        super(errorMessage);
    }

    public zuWenigSpielerException() {
        super("Es befinden sich nicht genug Spieler im Raum, um das Spiel zu starten.");
    }
}
