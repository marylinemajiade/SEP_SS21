package RMI;

import SpielLobby.Lobby;

import java.io.Serializable;

public class Event implements Serializable {
    private String name;
    private Serializable payload;

    public Event(String name, Serializable payload) {
        this.name = name;
        this.payload = payload;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Serializable getPayload() {
        return payload;
    }

    public void setPayload(Serializable payload) {
        this.payload = payload;
    }
}
