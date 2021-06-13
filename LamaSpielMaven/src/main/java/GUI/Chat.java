package GUI;

import RMI.RMIClient;
import RMI.RMIServer;
import RMI.RMIServerIF;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.rmi.RemoteException;

public  class Chat extends Application  {

    private String benutzernameClient;
    private String nachrichtGesendet;
    private String nachrichtErhalten;
    public RMIClient client;

    public RMIServer rmiserver;

    public TextField inputArea = new TextField();

    public static TextArea outputArea = new TextArea();

    public TextArea benutzernameArea = new TextArea();



    @Override
    public void start(Stage stage) {
        BorderPane borderpane = new BorderPane();
        Label benutzernameText = new Label("Benutzername eintippen und Chat benutzen klicken.");
        Button benutzernameButton = new Button("Chat benutzen");
        borderpane.setBottom(benutzernameArea);
        borderpane.setLeft(benutzernameText);
        borderpane.setRight(benutzernameButton);

        BorderPane.setAlignment(benutzernameText, Pos.TOP_LEFT);
        BorderPane.setAlignment(benutzernameText, Pos.TOP_LEFT);
        borderpane.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: #17f3b5;");

        benutzernameButton.setOnAction(actionEvent -> {

            try {
                setName(benutzernameArea.getText());
            } catch (RemoteException e) {
                e.printStackTrace();
            }

            borderpane.setBottom(null);
            borderpane.setLeft(null);
            borderpane.setRight(null);
            borderpane.setTop(null);


            Label input = new Label("Hallo "+ benutzernameClient + "!\n Chatte hier mit deinen Mitspielern:");
            Button sendenButton = new Button("senden");
            sendenButton.setOnAction(actionEvent2 -> {
                try {
                    input(inputArea.getText());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            });

            borderpane.setPrefSize(400,400);
            borderpane.setStyle("-fx-padding: 10;" +
                    "-fx-border-style: solid inside;" +
                    "-fx-border-width: 2;" +
                    "-fx-border-insets: 5;" +
                    "-fx-border-radius: 5;" +
                    "-fx-border-color: #b817f3;");

            borderpane.setRight(sendenButton);
            borderpane.setLeft(input);
            borderpane.setBottom(outputArea);
            outputArea.setPrefSize(100,100);
            borderpane.setCenter(inputArea);
            inputArea.setPrefSize(100,100);
            BorderPane.setAlignment(input, Pos.TOP_LEFT);
            BorderPane.setAlignment(inputArea, Pos.CENTER);


        });

        Scene scene = new Scene(borderpane);
        stage.setScene(scene);
        stage.setTitle("LAMA Chat");
        stage.show();
    }

    public void writeOutput(String benutzernameSpieler, String nachrichtErhalten){
        outputArea.setText(benutzernameSpieler + ": " + nachrichtErhalten + "\n");
    }

    public void input(String nachrichtGesendet) throws RemoteException {
        RMIClient client = new RMIClient(rmiserver, benutzernameClient);
        client.uebertrageChatnachricht(benutzernameClient, nachrichtGesendet);

    }

    public void setName(String name) throws RemoteException {
     benutzernameClient = name;
    }

    public static void main(String[] args){
        launch(args);
    }
}



