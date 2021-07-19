package GUI;

import RMI.*;
import Spiel.Spielrunde;
import fachlicheExceptions.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class LobbyController extends UnicastRemoteObject implements Initializable, Remote {
    @FXML
    private Button spielStarten;
    @FXML
    private Button lobbyVerlassen;
    @FXML
    private Button botEinfügen;
    @FXML
    private Button textSenden;
    @FXML
    private Button botEntfernen;
    @FXML
    private ListView spieler;
    @FXML
    private ListView messages;

    @FXML
    private TextField nachricht;
    private Spielrunde spielrunde;

    public LobbyController(Spielrunde spielrunde) throws RemoteException {
        this.spielrunde = spielrunde;
    }

    public Button getSpielStarten() {
        return spielStarten;
    }

    /**
     * Button um in die Lobby zu gehen
     *
     * @param event Expects ActionEvent to switch
     * @throws IOException throws input output exception on action event
     */
    public void goToLobby(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Lobby.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setTitle("Lobby");
        stage.setScene(scene);
        stage.show();
    }


    public Spielrunde getSpielrunde() {
        return spielrunde;
    }

    public ListView getSpieler() {
        return spieler;
    }

    public ListView getMessages() {
        return messages;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            RMIClientDriver.getOm().bind(new LobbyObserver(this));
            RMIClientDriver.getOm().dispatch(new Event("lobby-"+spielrunde.getRaumId(),new SpielerStatus(RMIClientDriver.getRmiClient().benutzername, true)));
            RMIClientDriver.getLamaServer().spielraumBeitreten(RMIClientDriver.getRmiClient().benutzername, spielrunde.getRaumId());
            var sp = RMIClientDriver.getLamaServer().getSpieler(spielrunde.getRaumId());
            System.out.println("----"+sp);
            getSpieler().getItems().clear();
            getSpieler().getItems().addAll(sp);
            lobbyVerlassen.setOnAction((ActionEvent event)->{
                Parent root = null;
                try {
                    RMIClientDriver.getOm().dispatch(new Event("lobby-"+spielrunde.getRaumId(),new SpielerStatus(RMIClientDriver.getRmiClient().benutzername, false)));
                    RMIClientDriver.getLamaServer().spielraumVerlassen(RMIClientDriver.getRmiClient().benutzername, spielrunde.getRaumId());
                    root = FXMLLoader.load(getClass().getResource("Welcome.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setTitle("Willkommen Seite");
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException | ungueltigeSpielraumIDException e) {
                    e.printStackTrace();
                }
            });
            textSenden.setOnAction((ActionEvent event)->{
                try {
                    RMIClientDriver.getOm().dispatch(new Event("lobby-"+spielrunde.getRaumId(),new Message(RMIClientDriver.getRmiClient().benutzername, nachricht.getText())));
                    nachricht.clear();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            });
            spielStarten.setOnAction((ActionEvent event)->{
                try {
                    RMIClientDriver.getLamaServer().spielStarten(spielrunde.getRaumId());
                    RMIClientDriver.getOm().dispatch(new Event("lobby-"+spielrunde.getRaumId(),new SpielStarten(spielrunde.getRaumId())));
                } catch (RemoteException e) {
                    e.printStackTrace();
                } catch (ungueltigerBenutzernameException e) {
                    e.printStackTrace();
                } catch (spielLaeuftBereitsException e) {
                    Parent root = null;
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("Spielraum.fxml"));
                        loader.setController(new SpielumgebungController(spielrunde));
                        root = loader.load();
                        Scene scene = new Scene(root);
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setTitle("Spielraum (Spieler: "+RMIClientDriver.getRmiClient().benutzername+")");
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                } catch (ungueltigeSpielraumIDException e) {
                    e.printStackTrace();
                } catch (zuWenigSpielerException e) {
                    e.printStackTrace();
                }
            });
        } catch (RemoteException | spielraumVollException | ungueltigeSpielraumIDException e) {
            e.printStackTrace();
        }
    }
}
