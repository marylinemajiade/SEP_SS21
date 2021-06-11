package fachlicheExceptions;

public class EmailVergebenException extends Exception{
    public EmailVergebenException(String errorMessage) {
        super(errorMessage);
    }

    public EmailVergebenException() {
        super("Diese Email ist bereits verwendet");
    }
}
