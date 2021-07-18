package RMI;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ObserverManagerI extends Remote {
    public void bind(ObserverI observer) throws RemoteException;
    public void unbind(ObserverI observer) throws RemoteException;
    public ObserverI get(String name) throws RemoteException;
    public void dispatch(Event event) throws RemoteException;

}
