package GUI;

import Highscore.Bestenliste;
import Konto.BenutzerVerwalten;
import RMI.*;
import Spiel.Spielrunde;
import SpielLobby.Lobby;
import fachlicheExceptions.benutzerNameVergebenException;
import fachlicheExceptions.ungueltigerBenutzernameException;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.function.Consumer;

public class WelcomeController extends UnicastRemoteObject implements Initializable, Remote {

    private  RMIClient rmiClient;
    @FXML
    private TextField userNameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label errorMessage;
    @FXML
    private ListView spielrauemeList;

    public WelcomeController() throws RemoteException, NotBoundException {
        this.rmiClient = RMIClientDriver.getRmiClient();
    }

    public RMIClient getRmiClient(){
        return rmiClient;
    }
    public ListView getSpielrauemeList(){
        return spielrauemeList;
    }

    @FXML
    public void erstelleSpielraum(ActionEvent event) throws IOException, ungueltigerBenutzernameException {

        rmiClient.erstelleSpielraum();
        //ObservableList<Spielrunde> spielraueme = FXCollections.observableArrayList(rmiClient.getSpielraume());
        //spielrauemeList.setItems(spielraueme);


    }

    public void goToSpielraum(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Spielumgebung.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Spielraum");
        stage.setScene(scene);
        stage.show();
    }

    public void beitreteSpielraum(ActionEvent event) throws IOException {
        spielrauemeList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);//nur einen Spielraum auswählen
        goToSpielraum(event);
    }

    @FXML
    public void loggeBenutzerAus(ActionEvent event) {

        try {
            rmiClient.loescheSpieler();
            goToLogin(event);
        } catch (ungueltigerBenutzernameException | IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void zeigeBestenlisteAn(ActionEvent event) throws IOException, ungueltigerBenutzernameException {
        String name = userNameField.getText();
        String pass = passwordField.getText();


        if (rmiClient.loggeSpielerEin(name, pass)) {
            goToBestenliste(event);
        }

    }


    public void goToBestenliste(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Bestenliste.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Bestenliste");
        stage.setScene(scene);
        stage.show();
    }

    public void goToLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            System.out.println(rmiClient.benutzername);
            var om = new WelcomeObserver(this);
            var that = this;
            RMIClientDriver.getOm().bind(om);
            var sp = RMIClientDriver.getLamaServer().getSpielraeume();
            var list = spielrauemeList.getItems();
                    list.clear();
            for (var i :sp){
                Button button = new Button("Join");

                button.setOnAction(event -> {
                    System.out.println("Spielraum "+i+" joined");
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Lobby.fxml"));
                    Parent root = null;
                    try {
                        loader.setController(new LobbyController(new Spielrunde(i)));
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
                var h = new HBox(new Text("Spielraum "+i), button);
                h.setAlignment(Pos.CENTER);
                h.setSpacing(100);
                list.add(h);
            }
           // RMIClientDriver.getOm().get(rmiClient.benutzername).listen();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
