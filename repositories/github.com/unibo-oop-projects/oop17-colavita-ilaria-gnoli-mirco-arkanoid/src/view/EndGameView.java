package view;

import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * View che propone di salvare i punteggi alla fine della partita.
 * 
 * @author Gnoli Mirco
 *
 */
public class EndGameView {
    private Stage root;
    private Stage stage;

    private TextField nameField;
    //se fosse una scene e sopra venisse l'immagine?
    public EndGameView(int score, Stage root) {
        this.root = root;
        this.stage = new Stage();
        stage.setTitle("Salvataggio");
        stage.initModality(Modality.WINDOW_MODAL); //con sto comando non posso tornare alla finestra precedente(quella del gioco) se non ho chiuso questa
        stage.initOwner(root); //da controllare bene cosa fa

        Label scoreLabel = new Label("Punteggio:");
        Label scorePoint = new Label("" + score);
        Label nameLabel = new Label("Inserisci il tuo nome:");
        nameField = new TextField();

        Button save = new Button("Salva");
        save.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(final ActionEvent event) {
                if (!nameField.getText().isEmpty()) {
                    stage.close();
                } else {
                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Attenzione");
                    alert.setHeaderText(null);
                    alert.setContentText("Inserire un nome valido.");
                    alert.show();
                    alert.setX(root.getX() + ((root.getWidth() - alert.getWidth()) / 2));
                    alert.setY(root.getY() + ((root.getHeight() - alert.getHeight()) / 2));
                }
            }
        });

        Button notSave = new Button("Non salvare");
        notSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                stage.close();
            }
        });
        ///////////////////////////////////////////////

        VBox vBox = new VBox();
        vBox.getChildren().addAll(scoreLabel, scorePoint, nameLabel, nameField);
        vBox.setAlignment(Pos.TOP_CENTER);

        HBox hBox = new HBox();
        hBox.getChildren().addAll(save, notSave);

        GridPane pane = new GridPane();
        pane.setPadding(new Insets(10));
        //pane.setVgap(5);
//        pane.setHgap(5);

        pane.add(vBox, 0, 0);
        pane.add(hBox, 0, 1);

        Scene scene = new Scene(pane);
        stage.setScene(scene);

        stage.setResizable(false);
        stage.sizeToScene();
    }

    public Optional<String> show() {
        //tocca fare questo gioco, anche se sporca un pelo il codice, altrimenti non riesco a settare il nuovo stage al centro dell'altro.
        //finchè non mostra lo stage, le coordinate sia dello stage, sia della scena risultano 0
        this.stage.show();
        this.stage.setX(root.getX() + ((root.getWidth() - stage.getWidth()) / 2));
        this.stage.setY(root.getY() + ((root.getHeight() - stage.getHeight()) / 2));

        this.stage.hide();
        this.stage.showAndWait();

        return Optional.of(nameField.getText());
    }
}
