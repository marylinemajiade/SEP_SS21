package RMI;

import Highscore.Bestenliste;
import Konto.BenutzerVerwalten;
import SpielLobby.Lobby;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.security.AllPermission;
import java.security.CodeSource;
import java.security.PermissionCollection;
import java.security.Policy;

public class RMIServerDriver {
    public static void main(String[] args) {
        class MyPolicy extends Policy {
            @Override
            public PermissionCollection getPermissions(CodeSource codesource) {
                return (new AllPermission()).newPermissionCollection();
            }
        }
        System.setProperty("java.security.policy", "file:./security.policy");



       try {
           //RMIServer-Objekt exportieren
           ObserverManagerI obj2 = new ObserverManager();
           RMIServer obj = new RMIServer(new Bestenliste(),new Lobby() ,new BenutzerVerwalten(),obj2);
           RMIServerIF stub1 = (RMIServerIF) UnicastRemoteObject.exportObject(obj,1099);

           //Registry erstellen und Dienste exportieren
           Registry registry = LocateRegistry.createRegistry(1099);
           registry.rebind("LamaServer", stub1);


           ObserverManagerI stub2 = (ObserverManagerI) UnicastRemoteObject.exportObject(obj2,1099);

           //Registry erstellen und Dienste exportieren
           registry.rebind("om", stub2);
       } catch (Exception e){
           System.out.println ("RMIServer exception: ");
           e.printStackTrace();
       }

    }
}

