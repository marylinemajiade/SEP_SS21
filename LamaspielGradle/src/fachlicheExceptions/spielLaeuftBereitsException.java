package fachlicheExceptions;

//Wird geworfen, wenn ein Spiel zu starten versucht wird, welches bereits läuft
public class spielLaeuftBereitsException extends Exception{
    public spielLaeuftBereitsException (String errorMessage) {
        super(errorMessage);
    }
}
