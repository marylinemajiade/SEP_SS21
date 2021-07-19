package RMI;

import Spiel.Spielrunde;

import java.io.Serializable;

public class SpielrundeUpdated implements Serializable {
    private String name;
    private Spielrunde spielrunde;

    public SpielrundeUpdated(String name, Spielrunde spielrunde) {
        this.name = name;
        this.spielrunde = spielrunde;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Spielrunde getSpielrunde() {
        return spielrunde;
    }
}
