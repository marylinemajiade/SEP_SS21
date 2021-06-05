package fachlicheExceptions;

//Wird geworfen, wenn ein illegaler Spielzug aufgerufen wird
public class ungueltigeSpielraumIDException extends Exception {
    public ungueltigeSpielraumIDException(String errorMessage) {
        super(errorMessage);
    }
}
