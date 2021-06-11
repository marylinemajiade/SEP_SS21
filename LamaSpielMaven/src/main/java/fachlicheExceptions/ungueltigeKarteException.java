package fachlicheExceptions;

public class ungueltigeKarteException extends Exception{
    public ungueltigeKarteException(String errorMessage) {
        super(errorMessage);
    }
    public ungueltigeKarteException() {
        super("Die gewählte Karte ist ungueltig.");
    }
}
