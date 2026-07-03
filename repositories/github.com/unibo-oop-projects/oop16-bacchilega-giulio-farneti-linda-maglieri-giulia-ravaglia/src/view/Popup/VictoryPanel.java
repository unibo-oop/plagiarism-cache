package view.Popup;
import javafx.animation.PauseTransition;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import view.Utilities.Utilities;
/**
 * VictoryPanel class.
 * Author : Giulia Maglieri
 *
 */
public final class VictoryPanel {
    /**
     * constructor
     */
    private  VictoryPanel() { }
    /**
     * 
     * @param title : String 
     */
    public static void display(final String title) {
        Stage stage = new Stage();
            Scene scene;
            StackPane s = new StackPane();
            HBox hbox = new HBox(8);
            VBox vbox = new VBox(Utilities.SPACE_VBOX);
            Label label1 = new Label("Player");
            Label label2 = new Label();
            Label label3 = new Label("Won");
            Reflection reflection = new Reflection();
            Image gifVictory = new Image("/image/tromba.gif");
            ImageView ivGif = new ImageView(gifVictory);
            reflection.setFraction(Utilities.REFRACTION);
            scene = new Scene(s, Utilities.POPUP_WIDTH, Utilities.POPUP_HEIGHT);
            label1.setEffect(reflection);
            label1.setId("label1");
            label2.setText(Winner.winner());
            label2.setEffect(reflection);
            label3.setEffect(reflection);
            label3.setId("label3");
            hbox.getChildren().addAll(label1, label2, label3);
            hbox.setAlignment(Pos.CENTER);
            vbox.getChildren().addAll(hbox, ivGif);
            vbox.setAlignment(Pos.CENTER);
            s.getChildren().addAll(vbox);
            scene.getStylesheets().addAll(VictoryPanel.class.getResource("/view/victory.css").toExternalForm());
            PauseTransition pause = new PauseTransition(Duration.millis(Utilities.DURATION));
            pause.setOnFinished(e -> { 
                stage.hide(); 
            });
            pause.play();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
    }
}
