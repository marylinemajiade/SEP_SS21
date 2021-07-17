package RMI;

import java.io.Serializable;
import java.util.function.Consumer;

public interface ObserverI extends Serializable {
    public String getHash();
    public void call(Event event);
    public void listen(String name, Consumer<Event> listener);
    public void unlisten(String name);
}
