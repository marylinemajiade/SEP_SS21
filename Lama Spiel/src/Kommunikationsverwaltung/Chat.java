package Kommunikationsverwaltung;

import java.io.Serializable;
import java.util.PriorityQueue;


public class Chat implements Serializable {
    public Nachricht msg;

    public Chat(Nachricht msg) {
        this.msg = msg;
    }

    PriorityQueue<Nachricht> nachrichten = new PriorityQueue<Nachricht>();

    public boolean sendeNachricht(Nachricht msg){
        nachrichten.add(msg);
        return true;
    }

    public Nachricht zeigeNachricht(){
        return nachrichten.peek();
    }

}
