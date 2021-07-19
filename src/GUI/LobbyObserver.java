package GUI;

import RMI.*;
import Spiel.Spielrunde;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class LobbyObserver extends UnicastRemoteObject implements ObserverI, Remote {
    private LobbyController lobbyController;

    public LobbyObserver(LobbyController lobbyController) throws RemoteException {
        this.lobbyController = lobbyController;
    }

    @Override
    public String getHash() {
        return "lobby-" + lobbyController.getSpielrunde().getRaumId() + "-" + RMIClientDriver.getRmiClient().benutzername;
    }

    @Override
    public void call(Event event) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if (event.getName().equals("lobby-" + lobbyController.getSpielrunde().getRaumId())) {
                    if (event.getPayload() instanceof Message) {
                        var m = (Message) event.getPayload();
                        var list = lobbyController.getMessages().getItems();
                        var u = new Text("Spieler: "+m.getUsername());
                        u.setStyle("-fx-text-fill: grey");
                        var v = new VBox(u, new Text(m.getMessage()));
                        list.add(v);
                        lobbyController.getMessages().scrollTo(list.size());
                    }
                    if (event.getPayload() instanceof SpielStarten) {
                        if (((SpielStarten) event.getPayload()).getRaumId() == lobbyController.getSpielrunde().getRaumId())
                            lobbyController.getSpielStarten().fire();
                    } else {
                        var ss = (SpielerStatus) event.getPayload();
                        System.out.println("--55" + lobbyController.getSpieler().getItems());
                        var list = lobbyController.getSpieler().getItems();
                        list.removeAll(ss.getUsername());
                        if (ss.isOnline())
                            list.add(ss.getUsername());
                        lobbyController.getSpieler().setItems(list);
                    }
                }

            }
        });
    }
}
