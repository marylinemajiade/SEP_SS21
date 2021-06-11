package RMI;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIClientDriver {
    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        String rmiServerURL = "rmi://localhost/RMIServer";
        RMIServerIF rmiserver = (RMIServerIF) Naming.lookup(rmiServerURL);
        RMIClient client = new RMIClient(rmiserver);
        LocateRegistry.createRegistry(1099);

    }
}
