package GUI;

import Highscore.Bestenliste;
import Konto.BenutzerVerwalten;
import RMI.RMIServer;
import Spiel.Chipstapel;
import Spiel.Spielrunde;
import SpielLobby.Lobby;
import fachlicheExceptions.benutzerNameVergebenException;
import fachlicheExceptions.spielraumVollException;
import fachlicheExceptions.ungueltigeSpielraumIDException;
import fachlicheExceptions.ungueltigerBenutzernameException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class Spielumgebung extends Application {

    public Spielumgebung() throws RemoteException {
    }

    public static void main(String[] args){
        Application.launch(args);
    }

    Bestenliste bestenliste = new Bestenliste();
    SpielLobby.Lobby lobby = new SpielLobby.Lobby();
    BenutzerVerwalten benutzerVerwalten = new BenutzerVerwalten();
    RMIServer server = new RMIServer(bestenliste, lobby, benutzerVerwalten);

    private ListView spielerOnline = new ListView();
    ArrayList<String> spielerListe = lobby.getSpieler(0);

    private ListView chatVerlauf = new ListView();
    private ListView chatVerlaufSpielraum = new ListView();

    private ListView scoreListe = new ListView();
    private ListView scoreListeSR = new ListView();
    ArrayList liste = bestenliste.getTopZehn();

    private ListView spielraumListe = new ListView();

    private ListView spielerInSpielraum = new ListView();

    private ListView handkarten = new ListView();
    private ListView handkartenSR = new ListView();



    Spielrunde spielrunde = new Spielrunde(spielraumListe.getSelectionModel().getSelectedIndex()+1, lobby);




    public void populateSpielerListe(){
        /*for(int i=0; i < spielerListe.size();i++){
            spielerOnline.getItems().add(spielerListe.get(i));
        }*/
    }

    public void populateLobbyChat(String name, String nachricht){
        /*for (String s : chat) {
            chatVerlauf.getItems().add(s);
        }*/
        chatVerlauf.getItems().add(name + ": " + nachricht);
    }
    public void populateSpielraumChat(String name, String nachricht){

        chatVerlaufSpielraum.getItems().add(name + ": " + nachricht);
    }

    private void populateBestenliste(){

        for(int i=0; i< liste.size(); i++){
            Object x = liste.get(i);
            int y = i+1;
            scoreListe.getItems().add(y + ".\n" + x.toString());
        }
        for(int i=0; i< liste.size(); i++){
            Object x = liste.get(i);
            int y = i+1;
            scoreListeSR.getItems().add(y + ".\n" + x.toString());
        }
    }

    public void populateSpielraeume(){

    }





    @Override
    public void start(Stage stage) throws Exception {

        /**
         * Fenster erstellen (Login mit GridPane, Rest mit BorderPane)
         */
        stage.setTitle("Login");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25,25,25,25));

        stage.setTitle("Willkommen bei LAMA");
        stage.setWidth(1200);
        stage.setHeight(800);

        BorderPane border = new BorderPane();
        border.setPadding(new Insets(10,10,10,10));

        BorderPane borderSpielraum = new BorderPane();
        borderSpielraum.setPadding(new Insets(10,10,10,10));

        Stage stageBeitritt = new Stage();
        stageBeitritt.setTitle("Spielraum Beitreten");
        stageBeitritt.setWidth(400);
        stageBeitritt.setHeight(500);
        BorderPane borderBeitritt = new BorderPane();

        Stage stageAblegen = new Stage();
        stageAblegen.setTitle("Karte ablegen");
        stageAblegen.setWidth(400);
        stageAblegen.setHeight(500);
        BorderPane borderAblegen = new BorderPane();

        /**
         * Erstellen der verschiedenen Scenes
         */
        Scene sceneLogin = new Scene(grid,300,275);
        Scene sceneLobby = new Scene(border,1200,800);
        Scene sceneBestenliste = new Scene(new Group());
        Scene sceneBestenlisteSR = new Scene(new Group());
        Scene sceneSpielraum = new Scene(borderSpielraum,1200,800);
        Scene sceneBeitritt = new Scene(borderBeitritt, 400, 500);
        Scene sceneAblegen = new Scene(borderAblegen,400, 500);


        /**
         * Login und Registrierung
         */

        Text scenetitleLogin = new Text("Willkommen bei LAMA");
        scenetitleLogin.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitleLogin, 0, 0,2,1);

        Label userName = new Label("Benutzername");
        grid.add(userName, 0, 1);
        TextField userTextField = new TextField();
        grid.add(userTextField,1,1);

        Label password = new Label("Passwort:");
        grid.add(password,0,2);
        PasswordField pwBox = new PasswordField();
        grid.add(pwBox,1,2);

        Button buttonLogin = new Button("Anmelden");
        HBox hboxLogin = new HBox(10);
        hboxLogin.setAlignment(Pos.BOTTOM_RIGHT);
        hboxLogin.getChildren().add(buttonLogin);
        grid.add(hboxLogin, 1, 4);

        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);

        buttonLogin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(server.benutzerdatenPruefen(userTextField.getText(), pwBox.getText())){
                    stage.setScene(sceneLobby);
                    populateSpielerListe();
                    populateSpielraeume();
                    spielerOnline.getItems().add(userTextField.getText());
                    try {
                        server.spielraumBeitreten(userTextField.getText(), 0);
                    } catch (spielraumVollException | ungueltigerBenutzernameException | ungueltigeSpielraumIDException e) {
                        e.printStackTrace();
                    }
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
                    server.benutzerRegistrieren(userTextField.getText(), pwBox.getText());
                    actiontarget.setFill(Color.FORESTGREEN);
                    actiontarget.setText("Registrierung erfolgreich");
                } catch (benutzerNameVergebenException e) {
                    e.printStackTrace();
                    actiontarget.setFill(Color.FIREBRICK);
                    actiontarget.setText("Name bereits vergeben");
                };
            }
        });

        /**
         * Abschnitt für Liste der angemeldeten Spieler
         */
        Label titleLabel = new Label("Gerade online:");
        titleLabel.setFont(new Font("Lucida", 15));

        final VBox vBoxSpieler = new VBox();
        vBoxSpieler.getChildren().addAll(titleLabel, spielerOnline);
        vBoxSpieler.setPrefHeight(600);
        vBoxSpieler.setMaxWidth(200);
        border.setLeft(vBoxSpieler);


        /**
         * Abschnitt für LobbyChat
         */
        Label chatLabel = new Label("Deine Nachricht:  ");
        TextField chatInput = new TextField();
        chatInput.setPrefWidth(750);

        Button sendButton = new Button("Senden");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(sendButton);

        final HBox hboxChat = new HBox();
        hboxChat.getChildren().addAll(chatLabel, chatInput, hbBtn);
        hboxChat.setPrefWidth(900);

        final VBox vboxchat = new VBox();
        vboxchat.getChildren().addAll(chatVerlauf, hboxChat);
        vboxchat.setPrefHeight(200);
        vboxchat.setMaxWidth(900);

        sendButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String nachricht = chatInput.getText();
                chatInput.clear();
                populateLobbyChat(userTextField.getText(), nachricht);
            }
        });


        /**
         * Abschnitt für Bestenliste in Lobby
         */
        Button bestButton = new Button("Bestenliste anzeigen");
        bestButton.setWrapText(true);
        bestButton.setPrefSize(150,150);
        HBox hbBtnBest = new HBox(10);
        hbBtnBest.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtnBest.getChildren().add(bestButton);

        final HBox hboxBottom = new HBox();
        hboxBottom.getChildren().addAll(vboxchat, bestButton);
        hboxBottom.setSpacing(50);
        border.setBottom(hboxBottom);

        Stage stageBestenliste = new Stage();
        stageBestenliste.setTitle("Bestenliste");
        stageBestenliste.setWidth(350);
        stageBestenliste.setHeight(550);
        Label titleLabelBestenliste = new Label("TopTen Bestenliste");
        titleLabelBestenliste.setFont(new Font("Lucida", 15));

        Button backToLobby = new Button("Zurück zur Lobby");
        backToLobby.setWrapText(true);
        backToLobby.setPrefSize(150,150);
        HBox hboxzurueck = new HBox(10);
        hboxzurueck.setAlignment(Pos.BOTTOM_RIGHT);
        hboxzurueck.getChildren().add(backToLobby);

        final VBox vboxBest = new VBox();
        vboxBest.getChildren().addAll(titleLabelBestenliste, scoreListe);
        vboxBest.setMinHeight(500);

        final VBox vboxzurueck = new VBox();
        vboxzurueck.getChildren().addAll(vboxBest, backToLobby);
        vboxzurueck.setSpacing(50);

        Group group = ((Group) sceneBestenliste.getRoot());
        group.getChildren().addAll(vboxzurueck);
        group.setLayoutX(40);

        bestButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                populateBestenliste();
                stage.setScene(sceneBestenliste);
            }
        });

        backToLobby.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stage.setScene(sceneLobby);
            }
        });

        /**
         * Konto löschen
         */
        Button kontoLoeschen = new Button("Konto Löschen");
        kontoLoeschen.setWrapText(true);
        kontoLoeschen.setPrefSize(150,50);
        HBox hboxkonto = new HBox(10);
        hboxkonto.setAlignment(Pos.BOTTOM_RIGHT);
        hboxkonto.getChildren().add(kontoLoeschen);

        kontoLoeschen.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Stage stageSchliessen = (Stage) kontoLoeschen.getScene().getWindow();
                stageSchliessen.close();
                try {
                    server.benutzerLoeschen(userTextField.getText());
                    spielerListe.remove(userTextField.getText());
                } catch (ungueltigerBenutzernameException | RemoteException e) {
                    e.printStackTrace();
                }
            }
        });

        /**
         * Spielraum erstellen
         */
        Button spielraumErstellen = new Button("Spielraum erstellen");
        spielraumErstellen.setWrapText(true);
        spielraumErstellen.setPrefSize(150,50);
        HBox hboxErstellen = new HBox(10);
        hboxErstellen.setAlignment(Pos.BOTTOM_RIGHT);
        hboxErstellen.getChildren().add(spielraumErstellen);

        spielraumErstellen.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                spielraumListe.getItems().add("Spielraum " + (spielraumListe.getItems().size()+1));
                stage.setScene(sceneSpielraum);
                spielerInSpielraum.getItems().add(userTextField.getText());
                try {
                    server.spielraumErstellen(userTextField.getText());
                } catch (RemoteException | ungueltigerBenutzernameException e) {
                    e.printStackTrace();
                }
            }
        });

        /**
         * Ausloggen
         */
        Button logout = new Button("Ausloggen und Beenden");
        logout.setPrefSize(150,50);
        HBox hboxLogout = new HBox(10);
        hboxLogout.setAlignment(Pos.BOTTOM_RIGHT);
        hboxLogout.getChildren().add(logout);

        logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Stage stageSchliessen = (Stage) logout.getScene().getWindow();
                stageSchliessen.close();
                try {
                    server.spielraumVerlassen(userTextField.getText(), 0);
                    spielerListe.remove(userTextField.getText());
                } catch (ungueltigeSpielraumIDException | ungueltigerBenutzernameException e) {
                    e.printStackTrace();
                }
            }
        });

        final VBox buttonsLobby = new VBox();
        buttonsLobby.getChildren().addAll(spielraumErstellen, logout, kontoLoeschen);
        buttonsLobby.setSpacing(50);
        border.setRight(buttonsLobby);

        /**
         * Spielräume anzeigen und beitreten
         */
        Label titleLabelRaeume = new Label("Bestehende Spielräume:");
        titleLabelRaeume.setFont(new Font("Lucida", 15));

        final VBox vBoxSpielraeume = new VBox();
        vBoxSpielraeume.getChildren().addAll(titleLabelRaeume, spielraumListe);
        vBoxSpielraeume.setPrefHeight(600);
        vBoxSpielraeume.setMaxWidth(200);
        border.setCenter(vBoxSpielraeume);

        Button backtoLobby2 = new Button("Zurück zur Lobby");
        backtoLobby2.setPrefSize(150,50);
        HBox hboxbacktoLobby2 = new HBox(10);
        hboxbacktoLobby2.setAlignment(Pos.BOTTOM_RIGHT);
        hboxbacktoLobby2.getChildren().add(backtoLobby2);

        backtoLobby2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stage.setScene(sceneLobby);
                stageBeitritt.close();
            }
        });

        Button weiterzuSpielraum = new Button("Spielraum Beitreten");
        weiterzuSpielraum.setPrefSize(150,50);
        HBox hboxweiter = new HBox(10);
        hboxweiter.setAlignment(Pos.BOTTOM_RIGHT);
        hboxweiter.getChildren().add(weiterzuSpielraum);

        weiterzuSpielraum.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                stage.setScene(sceneSpielraum);
                spielerInSpielraum.getItems().add(userTextField.getText());
                stageBeitritt.close();
            }
        });

        final HBox buttonsBeitritt = new HBox();
        buttonsBeitritt.getChildren().addAll(backtoLobby2, weiterzuSpielraum);
        buttonsBeitritt.setSpacing(100);
        borderBeitritt.setBottom(buttonsBeitritt);

        spielraumListe.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                int Raum_ID = spielraumListe.getSelectionModel().getSelectedIndex();
                Label spielerInSpielraumlabel = new Label("Spieler in Spielraum " + (Raum_ID+1));
                spielerInSpielraumlabel.setFont(new Font("Lucida", 15));
                TextArea outputArea = new TextArea();
                outputArea.setText(spielerInSpielraum.getItems().toString());

                final VBox vBoxBeitritt = new VBox();
                vBoxBeitritt.getChildren().addAll(spielerInSpielraumlabel, outputArea);
                vBoxBeitritt.setSpacing(10);
                vBoxBeitritt.setAlignment(Pos.CENTER);
                vBoxBeitritt.setPrefSize(100,200);
                borderBeitritt.setCenter(vBoxBeitritt);

                stageBeitritt.setScene(sceneBeitritt);
                stageBeitritt.show();
            }
        });


        /**
         * !! Ab hier Abschnitt für Spielraum !!
         */

        /**
         * Abschnitt für SpielraumChat
         */
        Label spielraumchatLabel = new Label("Deine Nachricht:  ");
        TextField spielraumchatInput = new TextField();
        spielraumchatInput.setPrefWidth(750);

        Button sendButtonSpielraum = new Button("Senden");
        HBox hbBtnSpielraum = new HBox(10);
        hbBtnSpielraum.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtnSpielraum.getChildren().add(sendButtonSpielraum);

        final HBox hboxChatSpielraum = new HBox();
        hboxChatSpielraum.getChildren().addAll(spielraumchatLabel, spielraumchatInput, hbBtnSpielraum);
        hboxChatSpielraum.setPrefWidth(900);

        final VBox vboxchatSpielraum = new VBox();
        vboxchatSpielraum.getChildren().addAll(chatVerlaufSpielraum, hboxChatSpielraum);
        vboxchatSpielraum.setPrefHeight(200);
        vboxchatSpielraum.setMaxWidth(900);

        sendButtonSpielraum.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String nachricht = spielraumchatInput.getText();
                spielraumchatInput.clear();
                populateSpielraumChat(userTextField.getText(), nachricht);
            }
        });

        /**
         * Abschnitt für Bestenliste aus Spielraum
         */
        Button bestButtonSR = new Button("Bestenliste anzeigen");
        bestButtonSR.setWrapText(true);
        bestButtonSR.setPrefSize(150,150);
        HBox hbBtnBestSR = new HBox(10);
        hbBtnBestSR.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtnBestSR.getChildren().add(bestButtonSR);

        final HBox hboxBottomSR = new HBox();
        hboxBottomSR.getChildren().addAll(vboxchatSpielraum, bestButtonSR);
        hboxBottomSR.setSpacing(50);
        borderSpielraum.setBottom(hboxBottomSR);

        Stage stageBestenlisteSR = new Stage();
        stageBestenlisteSR.setTitle("Bestenliste");
        stageBestenlisteSR.setWidth(350);
        stageBestenlisteSR.setHeight(550);
        Label titleLabelBestenlisteSR = new Label("TopTen Bestenliste");
        titleLabelBestenlisteSR.setFont(new Font("Lucida", 15));

        Button backToSR = new Button("Zurück zum Spiel");
        backToSR.setWrapText(true);
        backToSR.setPrefSize(150,150);
        HBox hboxzurueckSR = new HBox(10);
        hboxzurueckSR.setAlignment(Pos.BOTTOM_RIGHT);
        hboxzurueckSR.getChildren().add(backToSR);

        final VBox vboxBestSR = new VBox();
        vboxBestSR.getChildren().addAll(titleLabelBestenlisteSR, scoreListeSR);
        vboxBestSR.setMinHeight(500);

        final VBox vboxzurueckSR = new VBox();
        vboxzurueckSR.getChildren().addAll(vboxBestSR, backToSR);
        vboxzurueckSR.setSpacing(50);

        Group group2 = ((Group) sceneBestenlisteSR.getRoot());
        group2.getChildren().addAll(vboxzurueckSR);
        group2.setLayoutX(40);

        bestButtonSR.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                populateBestenliste();
                stage.setScene(sceneBestenlisteSR);
            }
        });

        backToSR.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stage.setScene(sceneSpielraum);
            }
        });

        /**
         * Abschnitt für Spieler in Spielraum
         */
        Label titleLabelInRaum = new Label("Spieler in Spielraum");
        titleLabelInRaum.setFont(new Font("Lucida", 15));

        final VBox vBoxSpielerInRaum = new VBox();
        vBoxSpielerInRaum.getChildren().addAll(titleLabelInRaum, spielerInSpielraum);
        vBoxSpielerInRaum.setPrefHeight(600);
        vBoxSpielerInRaum.setMaxWidth(200);
        borderSpielraum.setLeft(vBoxSpielerInRaum);

        /**
         * Spielraum verlassen
         */
        Button spielraumVerlassen = new Button("Spielraum verlassen");
        spielraumVerlassen.setPrefSize(150,50);
        HBox hboxverlassen = new HBox(10);
        hboxverlassen.setAlignment(Pos.BOTTOM_RIGHT);
        hboxverlassen.getChildren().add(spielraumVerlassen);

        spielraumVerlassen.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stage.setScene(sceneLobby);
                spielerInSpielraum.getItems().remove(userTextField.getText());
                //server.spielraumVerlassen(userTextField.getText(), Raum_ID );
            }
        });

        Button botHinzufuegen = new Button("Bot hinzufügen");
        botHinzufuegen.setPrefSize(150,50);
        HBox hboxaddbot = new HBox(10);
        hboxaddbot.setAlignment(Pos.BOTTOM_RIGHT);
        hboxaddbot.getChildren().add(botHinzufuegen);

        Button botEntfernen = new Button("Bot entfernen");
        botEntfernen.setPrefSize(150,50);
        HBox hboxdelbot = new HBox(10);
        hboxdelbot.setAlignment(Pos.BOTTOM_RIGHT);
        hboxdelbot.getChildren().add(botEntfernen);

        Button spielStarten = new Button("Spiel starten");
        spielStarten.setPrefSize(150,50);
        HBox hboxstart = new HBox(10);
        hboxstart.setAlignment(Pos.BOTTOM_RIGHT);
        hboxstart.getChildren().add(spielStarten);

        final VBox buttonsSR = new VBox();
        buttonsSR.getChildren().addAll(spielStarten, botHinzufuegen, botEntfernen, spielraumVerlassen);
        buttonsSR.setSpacing(20);
        borderSpielraum.setRight(buttonsSR);

        /**
         * Spielfunktionen
         */

        Button ziehen = new Button("Karte ziehen");
        ziehen.setPrefSize(150,50);
        HBox hboxziehen = new HBox(10);
        hboxziehen.setAlignment(Pos.BOTTOM_RIGHT);
        hboxziehen.getChildren().add(ziehen);

        Button ablegen = new Button("Karte ablegen");
        ablegen.setPrefSize(150,50);
        HBox hboxablegen = new HBox(10);
        hboxablegen.setAlignment(Pos.BOTTOM_RIGHT);
        hboxablegen.getChildren().add(ablegen);




        ablegen.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                Label welcheKarte = new Label("Welche Karte möchtest du ablegen?");
                welcheKarte.setFont(new Font("Lucida", 15));

                final VBox vBoxkarteablegen = new VBox();
                vBoxkarteablegen.getChildren().addAll(welcheKarte, handkarten);
                vBoxkarteablegen.setSpacing(10);
                vBoxkarteablegen.setPadding(new Insets(10));
                vBoxkarteablegen.setAlignment(Pos.CENTER);
                vBoxkarteablegen.setPrefSize(100,200);
                borderAblegen.setCenter(vBoxkarteablegen);

                stageAblegen.setScene(sceneAblegen);
                stageAblegen.show();

                handkarten.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        int index = handkarten.getSelectionModel().getSelectedIndex();
                        handkarten.getItems().remove(index);
                        stageAblegen.close();
                        handkartenSR.getItems().remove(index);
                    }
                });

            }
        });

        Button aussteigen = new Button("Aussteigen");
        aussteigen.setPrefSize(150,50);
        HBox hboxaussteigen = new HBox(10);
        hboxaussteigen.setAlignment(Pos.BOTTOM_RIGHT);
        hboxaussteigen.getChildren().add(aussteigen);

        final HBox spielbuttons1 = new HBox();
        spielbuttons1.getChildren().addAll(ziehen, ablegen, aussteigen);
        spielbuttons1.setAlignment(Pos.BOTTOM_CENTER);
        spielbuttons1.setSpacing(20);


        Button weissAbgeben = new Button("Weißen Chip abgeben");
        weissAbgeben.setPrefSize(150,50);
        HBox hboxabgebenweiss = new HBox(10);
        hboxabgebenweiss.setAlignment(Pos.BOTTOM_RIGHT);
        hboxabgebenweiss.getChildren().add(weissAbgeben);

        Button schwarzAbgeben = new Button("Schwarzen Chip abgeben");
        schwarzAbgeben.setPrefSize(150,50);
        HBox hboxabgebenschwarz = new HBox(10);
        hboxabgebenschwarz.setAlignment(Pos.BOTTOM_RIGHT);
        hboxabgebenschwarz.getChildren().add(schwarzAbgeben);

        Button schwarzeintauschen = new Button("Schwarzen Chip tauschen");
        schwarzeintauschen.setPrefSize(160,50);
        HBox hboxschwarztauschen = new HBox(10);
        hboxschwarztauschen.setAlignment(Pos.BOTTOM_RIGHT);
        hboxschwarztauschen.getChildren().add(schwarzeintauschen);

        Button weisseintauschen = new Button("Weiße Chips tauschen");
        weisseintauschen.setPrefSize(150,50);
        HBox hboxweisstauschen = new HBox(10);
        hboxweisstauschen.setAlignment(Pos.BOTTOM_RIGHT);
        hboxweisstauschen.getChildren().add(weisseintauschen);

        final HBox spielbuttons2 = new HBox();
        spielbuttons2.getChildren().addAll(weissAbgeben, schwarzAbgeben, schwarzeintauschen, weisseintauschen);
        spielbuttons2.setAlignment(Pos.BOTTOM_CENTER);
        spielbuttons2.setSpacing(20);
        spielbuttons2.setPadding(new Insets(10));

        final VBox spielbuttons = new VBox();
        spielbuttons.getChildren().addAll(spielbuttons1, spielbuttons2);
        spielbuttons.setAlignment(Pos.BOTTOM_CENTER);
        spielbuttons.setSpacing(20);
        spielbuttons.setPadding(new Insets(10));



        Label schwarzeChips = new Label("Anzahl schwarzer Chips: "/* + spielrunde.getChipstapel(userTextField.getText()).getSchwarz()*/);
        schwarzeChips.setFont(new Font("Lucida", 15));
        Label weisseChips = new Label("Anzahl weißer Chips: "/* + spielrunde.getChipstapel(userTextField.getText()).getWeiss()*/);
        weisseChips.setFont(new Font("Lucida", 15));
        Label punkte = new Label("Meine aktuelle Punktzahl: "/* + spielrunde.getChipstapel(userTextField.getText()).getPunkte()*/);
        punkte.setFont(new Font("Lucida", 15));
        Label anderReihe = new Label("An der Reihe: " /* + spielrunde.anDerReihe()*/);
        anderReihe.setFont(new Font("Lucida", 15));

        Label meineKarten = new Label("Meine Karten:");
        meineKarten.setFont(new Font("lucida", 15));

        final VBox daten = new VBox();
        daten.getChildren().addAll(meineKarten, handkartenSR, punkte, weisseChips, schwarzeChips, anderReihe, spielbuttons);
        daten.setSpacing(10);
        daten.setPadding(new Insets(10));
        daten.setAlignment(Pos.BOTTOM_CENTER);
        borderSpielraum.setCenter(daten);


        stage.setScene(sceneLogin);
        stage.setMaximized(true);
        stage.show();
    }
}
