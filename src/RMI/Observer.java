package RMI;

import java.util.HashMap;
import java.util.function.Consumer;

public class Observer implements ObserverI {
    private String hash;
    private HashMap<String, Consumer<Event>> consumers = new HashMap<String,Consumer<Event>>();
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

    @Override
    public void listen(String name, Consumer<Event> listener) {
        consumers.put(name,listener);
    }

    @Override
    public void unlisten(String name) {
        consumers.remove(name);
    }
}
