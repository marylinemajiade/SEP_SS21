package fachlicheExceptions;
//Wird geworfen, wenn eine Methode mit einem ungültigen Benutzernamen aufgerufen wird
public class ungueltigerBenutzernameException extends Exception{
        public ungueltigerBenutzernameException (String errorMessage) {
            super(errorMessage);
        }
}
