package RMI;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.function.Consumer;

public class Observer extends UnicastRemoteObject implements ObserverI {
    private String hash;
    private HashMap<String, Consumer<Event>> consumers = new HashMap<String,Consumer<Event>>();

    public Observer() throws RemoteException {

    }

    @Override
    public String getHash() {
        return this.hash;
    }

    public void setHash(String hash){
        this.hash = hash;
    }


    @Override
    public void call(Event event) {
        for(var c: consumers.values())
            c.accept(event);
        System.out.println(hash+">"+event.getName()+":"+event.getPayload());
    }

}
