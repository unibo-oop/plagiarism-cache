package view.Popup;
import javafx.animation.PauseTransition;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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
 * 
 * Author: Giulia Maglieri.
 *
 */
public final class TimeUp {
    /**
     * 
     */
    private TimeUp() { }
    /**
     * 
     */
    public static void show() {
        Stage stage = new Stage();
            Scene scene;
            StackPane s = new StackPane();
            ImageView iv = new ImageView(new Image("/image/clock.gif"));
            HBox hbox1 = new HBox(Utilities.SPACE_HBOX);
            VBox vbox = new VBox(10);
            Label lab1 = new Label("Time is up!");
            Label lab2 = new Label("Player");
            Label lab3 = new Label(Winner.winner());
            Label lab4 = new Label("Won");
            hbox1.setAlignment(Pos.CENTER);
            vbox.setAlignment(Pos.CENTER);
            scene = new Scene(s, Utilities.POPUP_WIDTH, Utilities.POPUP_HEIGHT);
            lab1.setAlignment(Pos.CENTER);
            hbox1.getChildren().addAll(lab2, lab3, lab4);
            vbox.getChildren().addAll(iv, lab1, hbox1);
            s.getChildren().addAll(vbox);
            scene.getStylesheets().addAll(TimeUp.class.getResource("/view/victory.css").toExternalForm());
            PauseTransition pause = new PauseTransition(Duration.millis(Utilities.DURATION1));
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


