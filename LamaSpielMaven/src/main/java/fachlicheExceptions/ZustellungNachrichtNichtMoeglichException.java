package fachlicheExceptions;

//wird geworfen, wenn die Nachricht nicht zugestellt werden konnte.

public class ZustellungNachrichtNichtMoeglichException extends Exception{
    public ZustellungNachrichtNichtMoeglichException (String errorMessage) {super(errorMessage); }
}
