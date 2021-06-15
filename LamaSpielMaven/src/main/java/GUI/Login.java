package GUI;

import fachlicheExceptions.benutzerNameVergebenException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import Konto.BenutzerVerwalten;


/**
@author Vanessa Stein
 */

public class Login extends Application {

    public static void main(String[] args){
        Application.launch(args);
    }

    BenutzerVerwalten bv = new BenutzerVerwalten();


    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("Login");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25,25,25,25));

        Text scenetitle = new Text("Willkommen bei LAMA");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0,2,1);

        Label userName = new Label("Benutzername");
        grid.add(userName, 0, 1);

        TextField userTextField = new TextField();
        grid.add(userTextField,1,1);

        Label password = new Label("Passwort:");
        grid.add(password,0,2);

        PasswordField pwBox = new PasswordField();
        grid.add(pwBox,1,2);

        Button button = new Button("Anmelden");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(button);
        grid.add(hbBtn, 1, 4);

        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if(bv.benutzerdatenPruefen(userTextField.getText(), pwBox.getText())){
                    Lobby guiLobby = new Lobby();
                    guiLobby.start(stage);
                } else {
                    actiontarget.setFill(Color.FIREBRICK);
                    actiontarget.setText("Ungültige Kombination");
                }
            }
        });

        Button button2 = new Button("Registrieren");
        HBox hbBtn2 = new HBox(10);
        hbBtn2.setAlignment(Pos.BOTTOM_LEFT);
        hbBtn2.getChildren().add(button2);
        grid.add(hbBtn2, 0, 4);

        final Text actiontarget2 = new Text();
        grid.add(actiontarget2, 1, 6);

        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    bv.benutzerRegistrieren(userTextField.getText(), pwBox.getText());
                } catch (benutzerNameVergebenException e) {
                    e.printStackTrace();
                    actiontarget.setFill(Color.FIREBRICK);
                    actiontarget.setText("Name bereits vergeben");
                };
            }
        });


        Scene scene = new Scene(grid,300,275);
        stage.setScene(scene);
        stage.show();

    }
}
