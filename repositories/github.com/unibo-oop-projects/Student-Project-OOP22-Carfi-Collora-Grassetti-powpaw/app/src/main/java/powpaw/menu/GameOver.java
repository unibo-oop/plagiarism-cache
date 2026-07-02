package powpaw.menu;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import powpaw.core.controller.StaticGameState;
import powpaw.player.controller.api.PlayerController;
import powpaw.world.controller.ScreenController;

/**
 * GameOver view.
 * 
 * @author Simone Collorà
 */
public class GameOver extends VBox {

    private static final int GAP = 15;
    private static final int DIVIDE = 4;
    private static final int MAXWIDTHB = 300;
    private static final int MAXHEIGHTB = 70;

    /**
     * GameOver create a VBox as with gridPane Text appeared a little decentralized.
     * 
     * @param controller in order to get the player who won and set his color on the
     *                   win text
     */
    public GameOver(final PlayerController controller) {
        final Button newGame;
        final Button exit;
        final Text gameOver;

        final BackgroundSize size = new BackgroundSize(ScreenController.SIZE_HD_W, ScreenController.SIZE_HD_H, true,
                true, true, false);
        setBackground(new Background(new BackgroundImage(new Image("/background_menu.png"), BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                size)));
        setAlignment(Pos.CENTER);
        final int winnerNumber = controller.getPlayerObservable().getAttackController().checkDeath().get().getNumber();
        gameOver = new Text("P" + winnerNumber + " WIN");
        newGame = new Button("NEW GAME");
        exit = new Button("EXIT");
        setSpacing(GAP);
        newGame.prefWidthProperty().bind(widthProperty().divide(DIVIDE));
        newGame.prefHeightProperty().bind(heightProperty().divide(DIVIDE));
        exit.prefWidthProperty().bind(widthProperty().divide(DIVIDE));
        exit.prefHeightProperty().bind(heightProperty().divide(DIVIDE));
        newGame.setMaxSize(MAXWIDTHB, MAXHEIGHTB);
        exit.setMaxSize(MAXWIDTHB, MAXHEIGHTB);
        gameOver.setStyle("-fx-font: 70 arial;");
        gameOver.setFill(winnerNumber == 1 ? Color.RED : Color.BLUE);
        gameOver.setTextAlignment(TextAlignment.CENTER);
        getChildren().addAll(gameOver, newGame, exit);
        newGame.setOnAction(e -> StaticGameState.getGameStateView().showCharacterCreation());
        exit.setOnAction(e -> {
            Platform.exit();
        });
    }
}
