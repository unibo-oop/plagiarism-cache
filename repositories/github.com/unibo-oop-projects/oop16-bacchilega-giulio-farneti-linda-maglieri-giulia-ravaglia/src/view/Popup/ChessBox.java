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
 * Author : Giulia Maglieri.
 *
 */
public final class ChessBox {
    /**
     * 
     */
      private ChessBox() { }
      /**
       * 
       */
    public static void show() {
        Stage stage = new Stage();
            Scene scene;
            StackPane s = new StackPane();
            HBox hbox = new HBox(Utilities.SPACE_HBOX1);
            VBox vbox = new VBox(10);
            scene = new Scene(s, Utilities.POPUP_WIDTH, Utilities.POPUP_HEIGHT);
            Label label1 = new Label("Player");
            label1.setId("label1");
            Label label2 = new Label();
            label2.setText(Winner.winner());
            Label label3 = new Label("Checkmated");
            label3.setId("label3");
            hbox.getChildren().addAll(label1, label2, label3);
            hbox.setAlignment(Pos.CENTER);
            Image gifVictory = new Image("/image/alert.gif");
            ImageView ivGif = new ImageView(gifVictory);
            ivGif.setFitWidth(Utilities.IMAGE_WIDTH);
            ivGif.setFitHeight(Utilities.IMAGE_HEIGHT);
            vbox.getChildren().addAll(ivGif, hbox);
            vbox.setAlignment(Pos.CENTER);
            s.getChildren().add(vbox);
            scene.getStylesheets().addAll(ChessBox.class.getResource("/view/checkmated.css").toExternalForm());
            PauseTransition pause = new PauseTransition(Duration.millis(Utilities.DURATION2));
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


