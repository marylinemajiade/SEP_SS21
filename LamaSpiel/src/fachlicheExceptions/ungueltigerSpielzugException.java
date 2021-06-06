package fachlicheExceptions;

//Wird geworfen, wenn ein illegaler Spielzug aufgerufen wird

public class ungueltigerSpielzugException extends Exception{
    public ungueltigerSpielzugException (String errorMessage) {
        super(errorMessage);
    }
}
