package view.menu;

import controller.base.BaseController;
import controller.menu.MainMenuController;
import controller.menu.MainMenuControllerImpl;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * This is the implementation of {@link MainMenuView} interface.
 */
public class MainMenuViewImpl implements MainMenuView {

    private final MainMenuController menuController;
    private final Stage stage;
    private final Pane pane;
    private final Button play;
    private final Button leaderboard;

    /**
     * MainMenuView's constructor.
     * @param baseController
     *      The instance of {@link BaseController}.
     * @param stage
     *      The main stage.
     */
    public MainMenuViewImpl(final BaseController baseController, final Stage stage) {
        this.menuController = new MainMenuControllerImpl(baseController);
        this.stage = stage;
        this.pane = new Pane();
        this.stage.getScene().setRoot(this.pane);
        this.play = new Button("Play");
        this.leaderboard = new Button("Leaderboard");
    }

    @Override
    public final void showMenu() {
        this.stage.centerOnScreen();
        this.setButtonsPosition();
        this.play.setOnMouseClicked(e -> this.menuController.playHit());
        this.leaderboard.setOnMouseClicked(e -> this.menuController.leaderboardHit());
        this.pane.getChildren().addAll(this.play, this.leaderboard);
    }
    // Sets a fixed position for main menu buttons.
    private void setButtonsPosition() {
        this.play.setTranslateX(this.stage.getWidth() / 4);
        this.play.setTranslateY(this.stage.getHeight() / 2);
        this.leaderboard.setTranslateX(2 * this.stage.getWidth() / 3);
        this.leaderboard.setTranslateY(this.stage.getHeight() / 2);
    }
}
