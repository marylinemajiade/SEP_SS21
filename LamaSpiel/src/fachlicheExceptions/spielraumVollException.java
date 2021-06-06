package fachlicheExceptions;
//Wird geworfen, wenn versucht wird ein Spielraum beizutreten, der bereits voll ist
public class spielraumVollException extends Exception {
    public spielraumVollException (String errorMessage) {
        super(errorMessage);
    }
}
