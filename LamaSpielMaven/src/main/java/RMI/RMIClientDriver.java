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
        Registry registry = LocateRegistry.getRegistry("LAPTOP-AM7GPH86",1099);
        RMIServerIF rmiserver = (RMIServerIF) registry.lookup("LamaServer");

        Scanner sc = new Scanner(System.in);
        String benutzername="Dummy";
        boolean benutzerregistriert = false;
        while(!benutzerregistriert) {
            System.out.println("bitte gewünschter Benutzername eingeben:");
            benutzername = sc.nextLine();
            System.out.println("bitte gewünschtes Passwort eingeben: ");
            String passwort = sc.nextLine();
                try {
                    rmiserver.benutzerRegistrieren(benutzername, passwort);
                    benutzerregistriert = true;
                } catch (benutzerNameVergebenException e){System.out.print("Benutzername bereits vergeben");}
        }
        RMIClient client = new RMIClient(rmiserver,benutzername);
        rmiserver.registriereClient(client);
        System.out.print("Erfolgreich registriert!\n");
        while(true){
            String nachricht = sc.nextLine();
            try {
                rmiserver.sendeChatnachricht(benutzername, 0, nachricht);
            }catch(Exception ignored){}
        }


    }
}
