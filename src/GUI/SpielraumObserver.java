package GUI;

import RMI.Event;
import RMI.ObserverI;
import RMI.RMIClientDriver;
import Spiel.Spielrunde;
import fachlicheExceptions.ungueltigerBenutzernameException;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class SpielraumObserver  extends UnicastRemoteObject implements ObserverI, Remote {
    private SpielumgebungController spielumgebungController;

    public SpielraumObserver(SpielumgebungController spielumgebungController) throws RemoteException {
        this.spielumgebungController = spielumgebungController;
    }

    @Override
    public String getHash() {
        return "spielraum-" + spielumgebungController.getSpielrunde().getRaumId()+"-"+ RMIClientDriver.getRmiClient().benutzername;
    }

    @Override
    public void call(Event event) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    if(event.getName().contains("spielraum-"+spielumgebungController.getSpielrunde().getRaumId()))
                    spielumgebungController.updateView();
                } catch (RemoteException e) {
                    e.printStackTrace();
                } catch (ungueltigerBenutzernameException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
