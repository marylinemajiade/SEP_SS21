package RMI;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class RMIServerDriver {
    public static void main(String[] args) throws RemoteException, MalformedURLException {
        Naming.rebind("RMIServer", new RMIServer());
    }
}
