package GUI;


import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.rmi.RemoteException;
import java.util.ArrayList;





public class Lobby extends Application {
    public  SpielLobby.Lobby lobby;
    public  ArrayList<String> spieler;
    public ArrayList<Integer> spielräume;
    public ArrayList<Button> Buttons;


    @Override
    public void start(Stage stage) {
        BorderPane borderpane = new BorderPane();
        Button lobbyBeitreten = new Button("Lobby beitreten");
        borderpane.setCenter(lobbyBeitreten);
        borderpane.setPrefSize(1000, 1000);
        borderpane.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 5;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: rgba(255,246,70,0.66);");


        lobbyBeitreten.setOnAction(actionEvent -> {
            try {
                setup();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            borderpane.setCenter(null);
            Buttons = new ArrayList<>();
            Button spielraum1 = new Button();
            Button spielraum2 = new Button();
            Button spielraum3 = new Button();
            Button spielraum4 = new Button();
            Button spielraum5 = new Button();

            Buttons.add(spielraum1);
            Buttons.add(spielraum2);
            Buttons.add(spielraum3);
            Buttons.add(spielraum4);
            Buttons.add(spielraum5);
            FlowPane buttons = new FlowPane() ;
            buttons.getChildren().addAll(Buttons) ;

            for (int i=0; i<spielräume.size(); i++){
                Buttons.get(i).setText("Spielraum " + i);

            }
            borderpane.setRight(buttons);
            BorderPane.setAlignment(buttons , Pos.CENTER_RIGHT) ;



            Label headerText = new Label("Willkommen bei LAMA");
            Button erstellenButton = new Button("Spielraum erstellen");

            borderpane.setPrefSize(1000, 1000);
            borderpane.setStyle("-fx-padding: 10;" +
                    "-fx-border-style: solid inside;" +
                    "-fx-border-width: 5;" +
                    "-fx-border-insets: 5;" +
                    "-fx-border-radius: 5;" +
                    "-fx-border-color: rgba(70,255,89,0.66);");


            BorderPane.setAlignment(erstellenButton, Pos.TOP_LEFT);
            borderpane.setCenter(erstellenButton);



        });
            Scene scene = new Scene(borderpane);
            stage.setScene(scene);
            stage.setTitle("LAMA Spiel Lobby");
            stage.show();

    }

     public void setup() throws RemoteException {
        spielräume = new ArrayList<>();
         spieler = new ArrayList<>();
         lobby = new SpielLobby.Lobby();
         spielräume.add(0);

         // Funktioniert erst wenn getSpielraum_Ids implementiert wurde
         //spielräume = lobby.getSpielraum_Ids();
//Testwerte
         spielräume.add(0);
         spielräume.add(1);


        for (int i : spielräume) {
             for (int j = 0; j < lobby.getSpieler(i).size(); j++) {
                spieler.add(lobby.getSpieler(i).get(j));

             } }
     }





    public static void main(String[] args) {
        launch(args);
    }
}
