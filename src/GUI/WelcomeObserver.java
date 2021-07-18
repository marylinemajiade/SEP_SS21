package GUI;

import RMI.Event;
import RMI.ObserverI;
import RMI.SerializableConsumer;
import Spiel.Spielrunde;
import SpielLobby.Lobby;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.function.Consumer;

public class WelcomeObserver extends UnicastRemoteObject implements ObserverI, Remote {
    private WelcomeController welcomeController;

    public WelcomeObserver(WelcomeController welcomeController) throws RemoteException {
        this.welcomeController = welcomeController;
    }

    @Override
    public String getHash() {
        return "welcome-" + welcomeController.getRmiClient().benutzername;
    }

    @Override
    public void call(Event event) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                var list = welcomeController.getSpielrauemeList().getItems();
                if(event.getName().equals("spielraum")){
                    var pr = (Spielrunde) event.getPayload();
                    Button button = new Button("Join");

                    button.setOnAction(event -> {
                        System.out.println("Spielraum "+pr.getRaumId()+" joined");
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("Lobby.fxml"));
                        Parent root = null;
                        try {
                            loader.setController(new LobbyController(pr));
                            root = loader.load();
                            Scene scene = new Scene(root);
                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                            stage.setTitle("Lobby");
                            stage.setScene(scene);
                            stage.show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    });
                    var h = new HBox(new Text("Spielraum "+pr.getRaumId()), button);
                    h.setAlignment(Pos.CENTER);
                    h.setSpacing(100);
                    list.add(h);
                }

            }
        });
    }
}
