package RMI;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.security.*;

public class RMIServerDriver {
    public static void main(String[] args) {
        class MyPolicy extends Policy {
            @Override
            public PermissionCollection getPermissions(CodeSource codesource) {
                return (new AllPermission()).newPermissionCollection();
            }
        }
        Policy.setPolicy(new MyPolicy());

       try {
           //RMIServer-Objekt exportieren
           RMIServer obj = new RMIServer();
           RMIServerIF stub1 = (RMIServerIF) UnicastRemoteObject.exportObject(obj, 0);

           //Registry erstellen und Dienste exportieren
           Registry registry = LocateRegistry.createRegistry(1099);
           registry.bind("LamaServer", stub1);

       } catch (Exception e){
           System.out.println ("RMIServer exception: ");
           e.printStackTrace();
       }

    }
}

