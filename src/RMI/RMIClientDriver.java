package RMI;

import fachlicheExceptions.ungueltigerBenutzernameException;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.security.AllPermission;
import java.security.CodeSource;
import java.security.PermissionCollection;
import java.security.Policy;

public class RMIClientDriver {
    private static RMIServerIF lamaServer;
    private static ObserverManagerI om;
    private static ObserverI observer;
    private static RMIClient rmiClient;
    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException, InterruptedException, ungueltigerBenutzernameException {
        // Security Manager einrichten
        class MyPolicy extends Policy {
            @Override
            public PermissionCollection getPermissions(CodeSource codesource) {
                return (new AllPermission()).newPermissionCollection();
            }
        }
        System.setProperty("java.security.policy", "file:./security.policy");



        //Verbindungsaufbau zum Server
        Registry registry = LocateRegistry.getRegistry("localhost",1099);
        RMIServerIF rmiserver = (RMIServerIF) registry.lookup("LamaServer");
        rmiClient = new RMIClient(rmiserver);

    }

    public static RMIServerIF getLamaServer() {
        return lamaServer;
    }

    public static void setLamaServer(RMIServerIF lamaServer) {
        RMIClientDriver.lamaServer = lamaServer;
    }

    public static ObserverManagerI getOm() {
        return om;
    }

    public static void setOm(ObserverManagerI om) {
        RMIClientDriver.om = om;
    }

    public static ObserverI getObserver() {
        return observer;
    }

    public static void setObserver(ObserverI observer) {
        RMIClientDriver.observer = observer;
    }

    public static RMIClient getRmiClient() {
        return rmiClient;
    }

    public static void setRmiClient(RMIClient rmiClient) {
        RMIClientDriver.rmiClient = rmiClient;
    }
}
