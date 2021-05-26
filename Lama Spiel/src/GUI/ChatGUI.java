package GUI;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
public class ChatGUI extends Application{

    Button button1;


    @Override
    public void start(Stage primaryStage) throws Exception {
    primaryStage.setTitle("LAMA Chat");


    Button button1 = new Button("senden");



    StackPane layout = new StackPane();
    layout.getChildren().add(button1);




    Scene scene = new Scene(layout, 300,250);
    primaryStage.setScene(scene);
    primaryStage.show();

    }

    public static void main (String[] args){
       launch(args);
    }
}
