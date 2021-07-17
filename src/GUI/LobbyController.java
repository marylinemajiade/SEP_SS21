package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LobbyController {
    @FXML
    private Button spielStarten;
    @FXML
    private Button lobbyVerlassen;
    @FXML
    private Button botEinfügen;
    @FXML
    private Button textSenden;
    @FXML
    private Button botEntfernen;

    @FXML
    private TextField nachricht;


    /**
     * Button um in die Lobby zu gehen
     *
     * @param event Expects ActionEvent to switch
     * @throws IOException throws input output exception on action event
     */
    public void goToLobby(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Lobby.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setTitle("Lobby");
        stage.setScene(scene);
        stage.show();
    }


}
