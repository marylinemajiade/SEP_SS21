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
        Policy.setPolicy(new MyPolicy());

        //Verbindungsaufbau zum Server
        RMIServerIF rmiserver = (RMIServerIF) Naming.lookup("rmi://LAPTOP-AM7GPH86:1099/BK");


    }
}
