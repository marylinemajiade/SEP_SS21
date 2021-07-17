package GUI;

import Highscore.Bestenliste;
import Konto.BenutzerVerwalten;
import RMI.*;
import Spiel.Spielrunde;
import SpielLobby.Lobby;
import fachlicheExceptions.benutzerNameVergebenException;
import fachlicheExceptions.ungueltigerBenutzernameException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Random;
import java.util.ResourceBundle;

public class WelcomeController implements Initializable {

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

        Registry registry = LocateRegistry.getRegistry();
        RMIServerIF server = (RMIServerIF) registry
                .lookup("LamaServer");
        var om = (ObserverManagerI) registry
                .lookup("om"); //client fetches the object from the registry (reference)
        var o = new Observer();
        o.setHash("me"+ (new Random()).nextInt());
        om.bind(o); //register object

        RMIClientDriver.setLamaServer(server);
        this.rmiClient = new RMIClient(server);
    }


    @FXML
    public void erstelleSpielraum(ActionEvent event) throws IOException, ungueltigerBenutzernameException {

        rmiClient.erstelleSpielraum();
        ObservableList<Spielrunde> spielraueme = FXCollections.observableArrayList(rmiClient.getSpielraume());
        spielrauemeList.setItems(spielraueme);


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
        RMIClientDriver.getObserver().listen("spielraum",(Event event)->{
            System.out.println(event);
        });
    }
}
