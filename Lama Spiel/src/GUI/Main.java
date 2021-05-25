package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        GridPane grid = new GridPane();
        grid.setId("grid");
        grid.setAlignment(Pos.CENTER) ;
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25,25,25,25));

        Scene scene = new Scene(grid, 300, 275);

        Text scenetitle = new Text("Please login: ");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL,20));
        Label username = new Label("User name: ");
        TextField usertextfield = new TextField();
        Label password = new Label("Password: ");
        PasswordField passwordfield = new PasswordField();

        grid.add(scenetitle, 0,0,2,1);
        grid.add(username, 0,1); //column 0, line 1
        grid.add(usertextfield,1,1);
        grid.add(password,0,2);
        grid.add(passwordfield,1,2);

        Button btn = new Button("Sign in");
        HBox hbtn = new HBox(10); //spacing: 10
        hbtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbtn.getChildren().add(btn);

        grid.add(hbtn,1,4);
        final Text action = new Text();
        grid.add(action,1,6);

        btn.setOnAction(event -> {
            action.setFill(Color.FIREBRICK);
            action.setText("Sign in button pressed");
        });

        scene.getStylesheets().add(Main.class.getResource("Login.css").toExternalForm());

        primaryStage.setTitle("LAMA Spiel");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

