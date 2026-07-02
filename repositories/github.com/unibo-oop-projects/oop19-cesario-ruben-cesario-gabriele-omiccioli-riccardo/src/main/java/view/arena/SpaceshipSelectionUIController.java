package view.arena;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import controller.Binder;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Pair;
import model.entity.EntityID;
import view.AdaptableResolution;
import view.Sound;
import view.image.Loader;
import view.image.ViewComponentUtilities;
import view.menu.MainMenuUIController;

/**
 * Handles the UI of a menu in which the user can digits his name and
 * selects the spaceship to use in the game.
 */
public class SpaceshipSelectionUIController implements Initializable, AdaptableResolution {

    // X and Y positions of selection items in percentage relative to the stage.
    private static final double BACKGROUND_X = 50;
    private static final double BACKGROUND_Y = 50;
    // X and Y positions of the backButton image in percentage relative to the stage.
    private static final double BACK_X = 4;
    private static final double BACK_Y = 7;
    // X and Y positions of the first selectable spaceship in percentage relative to the stage.
    private static final double SELECTION_ONE_X = 34;
    private static final double SELECTION_ONE_Y = 47;
    // X and Y positions of the second selectable spaceship in percentage relative to the stage.
    private static final double SELECTION_TWO_X = 50;
    private static final double SELECTION_TWO_Y = 47;
    // X and Y positions of the third selectable spaceship in percentage relative to the stage.
    private static final double SELECTION_THREE_X = 66;
    private static final double SELECTION_THREE_Y = 47;
    // X and Y positions of the start game item in percentage relative to the stage.
    private static final double START_GAME_X = 50;
    private static final double START_GAME_Y = 70;
    // X and Y positions of the player name panel in percentage relative to the stage.
    private static final double NAME_PANEL_X = 50;
    private static final double NAME_PANEL_Y = 27;
    /** This defines the pattern that the name the user digits has to match to be accepted. */
    private static final Pattern USERNAME_REGEX = Pattern.compile("[a-zA-Z0-9_.-]{3,10}");
    /** This is the error showed to the user if he digits a not valid name and tried to start the game. */
    private static final String ERROR_USERNAME_NOT_VALID = "Please insert a valid name";
    /** This defines the error showed to the user if he hasn't selected a spaceship yet and tried to start the game. */
    private static final String ERROR_NOT_SELECTED_SPACESHIP = "You must select your spaceship first";
    private final Map<Pair<ImageView, EntityID>, Boolean> ships = new HashMap<>();
    @FXML 
    private Pane panel;
    @FXML
    private Rectangle background;
    @FXML
    private ImageView back;
    @FXML
    private ImageView backgroundImage;
    @FXML
    private ImageView firstSelectionItem;
    @FXML
    private ImageView secondSelectionItem;
    @FXML
    private ImageView thirdSelectionItem;
    @FXML
    private ImageView startGame;
    @FXML
    private TextField playerNamePanel;

    /**
     * Initialize the controller after its root element has been completely processed.
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        this.ships.put(new Pair<>(firstSelectionItem, EntityID.FIGHTER), false);
        this.ships.put(new Pair<>(secondSelectionItem, EntityID.JUGGERNAUT), false);
        this.ships.put(new Pair<>(thirdSelectionItem, EntityID.CUTTER), false);
        this.playerNamePanel.setFocusTraversable(false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void draw() {
        ViewComponentUtilities.resizeAndReposition(this.panel, this.background);
        ViewComponentUtilities.resizeAndReposition(this.backgroundImage, BACKGROUND_X, BACKGROUND_Y);
        ViewComponentUtilities.resizeAndReposition(this.back, BACK_X, BACK_Y);
        ViewComponentUtilities.resizeAndReposition(this.firstSelectionItem, SELECTION_ONE_X, SELECTION_ONE_Y);
        ViewComponentUtilities.resizeAndReposition(this.secondSelectionItem, SELECTION_TWO_X, SELECTION_TWO_Y);
        ViewComponentUtilities.resizeAndReposition(this.thirdSelectionItem, SELECTION_THREE_X, SELECTION_THREE_Y);
        ViewComponentUtilities.resizeAndReposition(this.startGame, START_GAME_X, START_GAME_Y);
        ViewComponentUtilities.resizeAndReposition(this.playerNamePanel, NAME_PANEL_X, NAME_PANEL_Y);
    }

    /**
     * Return to the main menu.
     * @throws IOException - if an error occurs during loading
     */
    @FXML
    private void backtoMainMenu() throws IOException {
        ((MainMenuUIController) Loader.loadFXML("layouts/mainMenu.fxml").getController()).draw();
    }

    /**
     * Select the spaceship to use in the game.
     */
    @FXML
    private void selectShip(final MouseEvent event) {
        for (final Entry<Pair<ImageView, EntityID>, Boolean> ship : this.ships.entrySet()) {
            if (ship.getKey().getKey().equals((ImageView) event.getTarget())) {
                ship.getKey().getKey().styleProperty().setValue("-fx-effect: innershadow(gaussian, #00FF00, 100, 0, 0, 0);");
                ship.setValue(true);
            } else {
                ship.getKey().getKey().styleProperty().setValue("-fx-effect: innershadow(gaussian, #000000, 0, 0, 0, 0);");
                ship.getKey().getKey().getStyleClass().add("button");
                ship.setValue(false);
            }
        }
    }

    /**
     * Starts the game if the user types a valid name and has
     * selected one of the listed spaceship.
     * @throws IOException - if an error occurs during loading
     */
    @FXML
    private void startGame() throws IOException {
        if (checkPlayerName(this.playerNamePanel.getText().toString())) {
            final Optional<EntityID> selected = getSelected();
            if (!selected.equals(Optional.empty())) {
                ((ArenaUIController) Loader.loadFXML("layouts/arena.fxml").getController()).initialize();
                Binder.getController().startStageGame(this.playerNamePanel.getText(), selected.get());
                Sound.changeMusic("arenaMusic1");
            } else {
                signalError(ERROR_NOT_SELECTED_SPACESHIP);
            }
        } else {
            signalError(ERROR_USERNAME_NOT_VALID);
        }
    }

    /**
     * Applies an effect when the cursor is on the back item.
     */
    @FXML
    private void onBackMouseEntered() {
        Sound.play("mouseOver");
    }

    /**
     * Check if the user has typed a valid name based on a pattern.
     * @param name
     * @return if the user has typed a valid name
     */
    private boolean checkPlayerName(final String name) {
        return USERNAME_REGEX.asMatchPredicate().test(name);

    }

    /**
     * Return the selected spaceship by the user.
     * @return an Optional contains the selected spaceship. If the user
     * hasn't select any spaceship, Optional.empty() is returned.
     */
    private Optional<EntityID> getSelected() {
        return this.ships.entrySet().stream()
                                    .filter(ship -> ship.getValue())
                                    .map(ship -> ship.getKey().getValue())
                                    .findFirst();
    }

    /**
     * Show an error to he user if he types a wrong name or doesn't
     * selected a spaceship before starting the game.
     * @param error
     */
    private void signalError(final String error) {
        this.playerNamePanel.clear();
        this.panel.requestFocus();
        this.playerNamePanel.setPromptText(error);
    }

}
