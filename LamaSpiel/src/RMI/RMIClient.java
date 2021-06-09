//package RMI;
//
//import Highscore.Bestenliste;
//import Spiel.Spielrunde;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//
//import java.rmi.RemoteException;
//import java.rmi.server.UnicastRemoteObject;
//
//public class RMIClient extends UnicastRemoteObject implements RMIClientIF {
//
//    @Mock
//    Integer test;
//
//    public RMIClient() throws RemoteException {
//        super();
//    }
//
//    @Override
//    public void aktualisiereSpielstatus(Spielrunde spielraum) throws RemoteException {
//        test.toString();
//        Mockito.verify(test).toString();
//    }
//
//    @Override
//    public void uebertrageChatnachricht(String benutzername, String nachricht) throws RemoteException {
//
//    }
//
//    @Override
//    public String getBenutzername() throws RemoteException {
//        return null;
//    }
//
//    @Override
//    public void akutalisiereBestenliste(Bestenliste bestenliste) throws RemoteException {
//
//    }
//
//    @Override
//    public void setSpielraum() throws RemoteException {
//
//    }
//
//    @Override
//    public boolean isBot() throws RemoteException {
//        return false;
//    }
//}
