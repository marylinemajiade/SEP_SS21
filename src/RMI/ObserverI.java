package RMI;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.function.Consumer;

public interface ObserverI extends Remote {
    public String getHash() throws RemoteException;
    public void call(Event event) throws RemoteException;
}
