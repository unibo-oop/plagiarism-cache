package view.gamescreen;

import com.jfoenix.controls.JFXButton;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
import view.ViewImpl;
/**
 * 
 * This stage will be shown when a player win the game.
 * It will be set as stage in GameScreen Application.
 * @see GameScreen
 *
 */
public class VictoryScreen extends BorderPane {

    private static final double SPACING = 15;
    private static final double INSETS = 7;
    private static final double VICTORYIMG_SCALEFACT = 0.7;

    private static final double HEIGHT = Screen.getPrimary().getBounds().getHeight();

    /**
     * 
     * Class constructor.
     * 
     */
    public VictoryScreen() {
        /* Main Window */
        final VBox mainPane = new VBox();
        final Label victoryMsg = new Label("Congratulations " + ViewImpl.getIstance().getPlayerList().getHead().getName() + "!");
        final ImageView victoryImg = new ImageView(VictoryScreen.class.getResource("/images/victory.png").toExternalForm());
        victoryImg.setFitHeight(HEIGHT * VICTORYIMG_SCALEFACT);
        victoryImg.setPreserveRatio(true);
        victoryMsg.setFont(new Font(32));
        mainPane.setAlignment(Pos.TOP_CENTER);

        /* Footer Area */
        final HBox buttonsContainer = new HBox(SPACING);
        final JFXButton toMainMenu = new JFXButton("To Main Menu");
        final JFXButton exit = new JFXButton("Exit");
        toMainMenu.setMaxWidth(Double.MAX_VALUE);
        exit.setMaxWidth(Double.MAX_VALUE);
        buttonsContainer.setPadding(new Insets(INSETS));
        toMainMenu.getStyleClass().add("victory-btn");
        exit.getStyleClass().add("victory-btn");

        toMainMenu.setOnMouseClicked(e -> {
            ((Stage) ((JFXButton) e.getSource()).getScene().getWindow()).close();
            Platform.runLater(new Runnable() {
                public void run() {
                    new view.mainmenu.MainMenu().start(new Stage());
                }
            });
        });

        exit.setOnMouseClicked(e -> {
            Platform.exit();
            System.exit(0);
        });

        HBox.setHgrow(toMainMenu, Priority.ALWAYS);
        HBox.setHgrow(exit, Priority.ALWAYS);
        mainPane.getChildren().addAll(victoryImg, victoryMsg);
        buttonsContainer.getChildren().addAll(toMainMenu, exit);
        this.setCenter(mainPane);
        this.setBottom(buttonsContainer);

        this.getStylesheets().add("/Style.css");
    }
}
