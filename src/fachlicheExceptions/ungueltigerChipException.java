package fachlicheExceptions;

public class ungueltigerChipException extends Exception{
    public ungueltigerChipException(String errorMessage) {
        super(errorMessage);
    }
    public ungueltigerChipException() {
        super("Der gewählte Chip ist ungueltig.");
    }
}
