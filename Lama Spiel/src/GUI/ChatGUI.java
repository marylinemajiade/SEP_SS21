package GUI;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
public class ChatGUI extends Application{

    // Input-Textfeld zum Schreiben
    private TextField inputArea = new TextField();
    // Output-Textfeld zum Anzeigen der Nachrichten
    private TextArea outputArea = new TextArea();



    @Override
    public void start(Stage stage) throws Exception {


        // Header-Text
        Label headerText = new Label("Chatte hier mit anderen LAMA-Spielern!");
        // weiterer Text, der angezeigt werden soll
        Label input = new Label("Nachricht: ");



        Button sendenButton = new Button("Senden");

        //wenn man auf Senden drückt, soll der Text genommen werden und mit der writeOutput-Methode ausgegeben
        sendenButton.setOnAction(new EventHandler()
        {
            @Override
            public void handle(Event event) {
                writeOutput(inputArea.getText());
            }
        });

        //erzeugt die Fläche mit Größe und Style
        BorderPane borderpane = new BorderPane();

        borderpane.setPrefSize(400, 400);

        borderpane.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: blue;");



        // was soll wo auf der Fläche angezeigt werden
        borderpane.setRight(sendenButton);
        borderpane.setTop(headerText);
        borderpane.setLeft(input);
        borderpane.setBottom(outputArea);
        borderpane.setCenter(inputArea);
        BorderPane.setAlignment(headerText,Pos.BOTTOM_CENTER);
        BorderPane.setAlignment(input,Pos.TOP_LEFT);

        // stage = Fenster, scene = Inhalt auf Fenster
        Scene scene = new Scene(borderpane);

        stage.setScene(scene);

        stage.setTitle("LAMA Chat");

        stage.show();


    }

    private void writeOutput(String msg)
    {
        // hier muss später noch die richtige Spieler-ID eingefügt werden
        this.outputArea.appendText("Spieler " + "1" + ": " + msg + "\n");
    }

    public static void main (String[] args){
       launch(args);
    }
}
