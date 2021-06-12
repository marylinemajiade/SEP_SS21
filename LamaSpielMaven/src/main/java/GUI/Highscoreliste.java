package GUI;

import Highscore.Bestenliste;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Highscoreliste extends Application {


    private ListView mylist = new ListView();
    Bestenliste b = new Bestenliste();
    ArrayList liste = b.getTopZehn();


    private void populateData(){

        for(int i=0; i< liste.size(); i++){
            Object x = liste.get(i);
            int y = i+1;
            mylist.getItems().add(y + ".\n" + x.toString());
        }
    }

    @Override
    public void start(Stage stage) throws Exception {

        Scene scene = new Scene(new Group());
        stage.setTitle("Bestenliste");
        stage.setWidth(350);
        stage.setHeight(550);
        Label titleLabel = new Label("TopTen Bestenliste");
        titleLabel.setFont(new Font("Lucida", 20));

        populateData();

        final VBox vbox = new VBox();
        vbox.getChildren().addAll(titleLabel, mylist);
        vbox.setMinHeight(500);

        Group group = ((Group) scene.getRoot());
        group.getChildren().addAll(vbox);
        group.setLayoutX(40);

        stage.setScene(scene);
        stage.show();

    }
    public static void main(String[] args){
        Application.launch(args);
    }
}
