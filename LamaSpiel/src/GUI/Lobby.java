package GUI;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Lobby extends Application {

    @Override
    public void start(Stage stage) {
        Label headerText = new Label("Willkommen bei LAMA");

        BorderPane borderpane = new BorderPane();


        Button beitretenButton = new Button("Spielraum beitreten");
        Button erstellenButton = new Button("Spielraum erstellen");
        Button spielraum = new Button("Spielraum 1");
        spielraum.setPrefSize(100,100);
        BorderPane.setAlignment(spielraum, Pos.TOP_RIGHT);
        borderpane.setRight(spielraum);


        borderpane.setPrefSize(1000,1000);
        borderpane.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 5;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: rgba(159,23,243,0.66);");


        borderpane.setBottom(beitretenButton);
        BorderPane.setAlignment(erstellenButton, Pos.TOP_LEFT);
        borderpane.setCenter(erstellenButton);
        BorderPane.setAlignment(spielraum, Pos.TOP_RIGHT);
        borderpane.setRight(spielraum);


        Scene scene = new Scene(borderpane);
        stage.setScene(scene);
        stage.setTitle("LAMA Spiel Lobby");
        stage.show();
    }


    public static void main(String[] args){
        launch(args);
    }
}
