package it.unibo.aknightstale.views;

import it.unibo.aknightstale.controllers.interfaces.MainMenuController;
import it.unibo.aknightstale.views.interfaces.MainMenuView;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;

import java.util.Objects;

public class MainMenuViewImpl extends BaseView<MainMenuController> implements MainMenuView {
    @FXML
    private VBox vBox;

    public MainMenuViewImpl() {
        super("Main Menu");
    }

    @SuppressWarnings("PMD.UnusedPrivateMethod") // False positive (used by JavaFX)
    @FXML
    private void initialize() {
        vBox.setBackground(
                new Background(
                        new BackgroundImage(
                                new Image(Objects.requireNonNull(getClass().getResourceAsStream("mainMenuBackground.png"))),
                                BackgroundRepeat.NO_REPEAT,
                                BackgroundRepeat.NO_REPEAT,
                                BackgroundPosition.CENTER,
                                new BackgroundSize(1, 1, true, true, false, false)
                        )
                )
        );
    }

    /**
     * Action when new game button is clicked. Opens the game map view.
     */
    @FXML
    protected final void onNewGameClicked() {
        this.getController().showMapView();
    }

    /**
     * Action when exit button is clicked. Closes the window when clicked.
     */
    @FXML
    protected final void onExitButtonClicked() {
        this.getController().closeView();
    }

    /**
     * Action when scoreboard button is clicked. Shows the scoreboard view.
     */
    @FXML
    protected final void onScoreboardButtonClicked() {
        this.getController().showScoreboard();
    }
}
