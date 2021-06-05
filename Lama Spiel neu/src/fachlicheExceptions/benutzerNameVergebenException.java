package fachlicheExceptions;

//Wird geworfen, wenn Benutzer mit einem bereits existierenden Benutzernamen zu registrieren

public class benutzerNameVergebenException extends Exception{
    public benutzerNameVergebenException(String errorMessage) {
        super(errorMessage);
    }
}
