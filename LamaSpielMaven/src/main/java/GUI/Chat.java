package GUI;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


import RMI.RMIClient;

import java.rmi.RemoteException;

public class Chat extends Application {

    private String benutzernameClient;
    private String nachrichtGesendet;
    private String nachrichtErhalten;
    private RMIClient client;

    public TextField inputArea = new TextField();

    public TextArea outputArea = new TextArea();

    @Override
    public void start(Stage stage) {

        Label headerText = new Label("Willkommen " + benutzernameClient + "! Chatte jetzt mit anderen Spielern");
        Label input = new Label("Nachricht: ");
        Button sendenButton = new Button("senden");
        sendenButton.setOnAction(actionEvent -> {
            try {
                input(inputArea.getText());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });

        BorderPane borderpane = new BorderPane();
        borderpane.setPrefSize(400,400);
        borderpane.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: #b817f3;");

        borderpane.setRight(sendenButton);
        borderpane.setTop(headerText);
        borderpane.setLeft(input);
        borderpane.setBottom(outputArea);
        borderpane.setCenter(inputArea);
        BorderPane.setAlignment(headerText, Pos.BOTTOM_CENTER);
        BorderPane.setAlignment(input, Pos.TOP_LEFT);
        Scene scene = new Scene(borderpane);
        stage.setScene(scene);
        stage.setTitle("LAMA Chat");
        stage.show();
    }

    // Wird vom Server aufgerufen mit den neuen Nachrichten
    public void writeOutput(String benutzernameSpieler, String nachrichtErhalten){
        this.outputArea.appendText(benutzernameSpieler + ": " + nachrichtErhalten + "\n");
    }


    // Ruft uebertrageChatnachricht auf um die Nachricht zu übertragen
    public void input(String nachrichtGesendet) throws RemoteException {
        client.uebertrageChatnachricht(benutzernameClient, nachrichtGesendet);
        writeOutput(benutzernameClient, nachrichtGesendet);
    }

    public static void main(String[] args){
        launch(args);
    }
}



