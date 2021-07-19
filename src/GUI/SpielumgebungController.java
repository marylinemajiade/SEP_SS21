package GUI;

import RMI.RMIClientDriver;
import Spiel.Spielrunde;
import fachlicheExceptions.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class SpielumgebungController implements Initializable {
    @FXML
    private ListView karten;
    @FXML
    private ListView drawpile;
    @FXML
    private Text weisserchips;
    @FXML
    private Text shwarzerchips;
    @FXML
    private Text punktzahl;
    @FXML
    private Text adr;
    @FXML
    private Button karteziehen;
    @FXML
    private Button aussteigen;
    @FXML
    private Button verlassen;



    private Spielrunde spielrunde;

    public SpielumgebungController(Spielrunde spielrunde){
        this.spielrunde = spielrunde;
    }

    public Spielrunde getSpielrunde() {
        return spielrunde;
    }

    public void updateView() throws RemoteException, ungueltigerBenutzernameException {
        var list = karten.getItems();
        list.clear();
        spielrunde = RMIClientDriver.getLamaServer().getSpielrunde(spielrunde.getRaumId());
        if(!spielrunde.isSpiellaeuft()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Spiel beendet: "+spielrunde.getWinner()+" gewinnt!", ButtonType.YES);
            alert.showAndWait();
            aussteigen.fire();
            return;
        }
        var chip = spielrunde.getChipstapel(RMIClientDriver.getRmiClient().benutzername);
        punktzahl.setText("Meine aktuelle Punktzahl: -"+chip.getPunkte());
        weisserchips.setText("Anzahl wießer Chips: "+chip.getWeiss());
        shwarzerchips.setText("Anzahl schwarzer Chips: "+chip.getSchwarz());
        adr.setText("An der Reihe: "+spielrunde.anDerReihe());
        for(var i: spielrunde.getHandkarten(RMIClientDriver.getRmiClient().benutzername)){
            Button button = new Button("Ablegen");

            button.setOnAction(event -> {
                try {
                    RMIClientDriver.getLamaServer().karteAblegen(i,RMIClientDriver.getRmiClient().benutzername,spielrunde.getRaumId());
                } catch (RemoteException e) {
                    e.printStackTrace();
                } catch (ungueltigerSpielzugException e) {
                    e.printStackTrace();
                } catch (ungueltigeSpielraumIDException e) {
                    e.printStackTrace();
                } catch (ungueltigerBenutzernameException e) {
                    e.printStackTrace();
                } catch (stapelLeerException e) {
                    e.printStackTrace();
                }
            });
            var h = new HBox(new Text("Karte "+i), button);
            h.setAlignment(Pos.CENTER);
            h.setSpacing(200);
            list.add(h);
        }
        drawpile.getItems().clear();
        for(var i: spielrunde.getAblagestapel()){
            drawpile.getItems().add(new Text("Karte "+i));
        }
        drawpile.scrollTo(drawpile.getItems().size());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            RMIClientDriver.getOm().bind(new SpielraumObserver(this));
            karteziehen.setOnAction((ActionEvent event)->{
                try {
                    RMIClientDriver.getLamaServer().karteZiehen(RMIClientDriver.getRmiClient().benutzername, spielrunde.getRaumId());
                } catch (RemoteException e) {
                    e.printStackTrace();
                } catch (ungueltigerSpielzugException e) {
                    e.printStackTrace();
                } catch (ungueltigeSpielraumIDException e) {
                    e.printStackTrace();
                } catch (ungueltigerBenutzernameException e) {
                    e.printStackTrace();
                } catch (stapelLeerException e) {
                    e.printStackTrace();
                } catch (ungueltigeKarteException e) {
                    e.printStackTrace();
                }
            });
            aussteigen.setOnAction((ActionEvent event)->{
                try {
                    RMIClientDriver.getLamaServer().aussteigen(RMIClientDriver.getRmiClient().benutzername, spielrunde.getRaumId());
                    goToWelcomePage(event);
                } catch (RemoteException e) {
                    e.printStackTrace();
                } catch (ungueltigerSpielzugException e) {
                    e.printStackTrace();
                } catch (ungueltigeSpielraumIDException e) {
                    e.printStackTrace();
                } catch (ungueltigerBenutzernameException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            verlassen.setOnAction((ActionEvent event)->{
                try {
                    RMIClientDriver.getLamaServer().spielraumVerlassen(RMIClientDriver.getRmiClient().benutzername, spielrunde.getRaumId());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }catch (ungueltigeSpielraumIDException e) {
                    e.printStackTrace();
                }
            });
           this.updateView();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (ungueltigerBenutzernameException e) {
            e.printStackTrace();
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
