package GUI;

import Highscore.Bestenliste;
import Konto.BenutzerVerwalten;
import RMI.*;
import SpielLobby.Lobby;
import fachlicheExceptions.benutzerNameVergebenException;
import fachlicheExceptions.ungueltigerBenutzernameException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Random;


import javafx.stage.Stage;


public class LoginController {

    private final RMIClient rmiClient;
    @FXML
    private TextField userNameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label errorMessage;

    public LoginController() throws RemoteException, NotBoundException {

        Registry registry = LocateRegistry.getRegistry();
        RMIServerIF server = (RMIServerIF) registry
                .lookup("LamaServer");
        var om = (ObserverManagerI) registry
                .lookup("om"); //client fetches the object from the registry (reference)
        var o = new Observer();
        o.setHash("me"+ (new Random()).nextInt());
        om.bind(o); //register object

        RMIClientDriver.setOm(om);
        RMIClientDriver.setObserver(o);
        RMIClientDriver.setLamaServer(server);
        this.rmiClient = new RMIClient(server);
    }


    @FXML
    public void registriereBenutzer(ActionEvent event) throws IOException {
        String name = userNameField.getText();
        String pass = passwordField.getText();

        try {
            rmiClient.registriereSpieler(name, pass);
            errorMessage.setText("Erfolgreich registriert !");
            errorMessage.setStyle("-fx-text-fill: green");
        } catch (benutzerNameVergebenException e) {
            e.printStackTrace();
            errorMessage.setText("Benutzername schon vergeben");
            errorMessage.setStyle("-fx-text-fill: red");
        }
    }

    @FXML
    public void loggeBenutzerEin(ActionEvent event) throws IOException, ungueltigerBenutzernameException {
        String name = userNameField.getText();
        String pass = passwordField.getText();


        if (rmiClient.loggeSpielerEin(name, pass)) {
            goToWelcomePage(event);
        }

    }

    public void goToWelcomePage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Welcome.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Willkommen Seite");
        stage.setScene(scene);
        stage.show();
    }


}





