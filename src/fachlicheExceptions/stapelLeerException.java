package fachlicheExceptions;

public class stapelLeerException extends Exception{
    public stapelLeerException(String errorMessage) {
        super(errorMessage);
    }
    public stapelLeerException() {
        super("Der Stapel ist leer.");
    }
}
