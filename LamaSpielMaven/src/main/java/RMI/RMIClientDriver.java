package RMI;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.security.AllPermission;
import java.security.CodeSource;
import java.security.PermissionCollection;
import java.security.Policy;
import java.util.Scanner;
import fachlicheExceptions.*;

public class RMIClientDriver {
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
        Registry registry = LocateRegistry.getRegistry("131.246.228.177",1099);
        RMIServerIF rmiserver = (RMIServerIF) registry.lookup("LamaServer");


    }
}
