package RMI;

import java.rmi.RemoteException;
import java.util.HashMap;

public class ObserverManager implements ObserverManagerI{
    private HashMap<String,ObserverI> observers = new HashMap<String,ObserverI>();

    @Override
    public void bind(ObserverI observer) throws RemoteException {
        observers.put(observer.getHash(), observer);
    }

    @Override
    public void unbind(ObserverI observer) throws RemoteException {
        observers.remove(observer.getHash());
    }

    @Override
    public ObserverI get(String name) throws RemoteException {
        return observers.get(name);
    }

    @Override
    public void dispatch(Event event) throws RemoteException {
        for (var observer: observers.values()){
            observer.call(event);
        }
    }
}
