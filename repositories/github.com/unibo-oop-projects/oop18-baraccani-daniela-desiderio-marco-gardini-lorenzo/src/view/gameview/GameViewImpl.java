package view.gameview;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.entitiesutil.Entity;
import model.entitiesutil.EntityMovable;
import view.audio.AudioManager;
import view.audio.AudioTrack;
import view.controller.AbstractController;
import view.inputhandler.Action;
import view.inputhandler.KeyPressedManager;
import view.viewmanager.ViewManager;

/**
 * Implementation of the interface {@link GameView}. It controls the game gui,
 * loading all sprites and intercepting user's input actions for the main
 * {@link Hero}.
 */
public class GameViewImpl extends AbstractController implements Initializable, GameView {

    private static final double HEADER_SCALE = 0.1;
    private static final double FOOTER_SCALE = 0.1;
    private static final double GAME_SCALE = 1 - HEADER_SCALE - FOOTER_SCALE;

    private final KeyPressedManager keyManager;
    private final ViewManager loader;
    private final Stage stage;
    private final AudioManager music;
    private final BorderPane infoHeader;
    private final Pane staticEntities;
    private final Pane movableEntities;
    private final SpritesLoader spritesLoader;
    private final String newPlayerName;
    private double offsetX, offsetY, scale;

    @FXML
    private Label levelNumber;
    @FXML
    private Label playerNameLabel;
    @FXML
    private Label score;
    @FXML
    private Label lives;
    @FXML
    private HBox header;
    @FXML
    private StackPane gameContainer;

    /**
     * Controllers prepares the structures that will be filled by game elements.
     * 
     * 
     * @param loader     The {@link ViewManager} in case of an error, especially for
     *                   {@link IOException}
     * @param stage      The main {@link Stage} to which all sprites, input handler
     *                   and sounds will be associated.
     * @param music      The {@link AudioManager} for regulating sound effects and
     *                   music.
     * @param playerName The current player's name
     */
    public GameViewImpl(final ViewManager loader, final Stage stage, final AudioManager music,
            final String playerName) {
        super(loader);
        this.loader = loader;
        this.keyManager = new KeyPressedManager();
        this.stage = stage;
        this.music = music;
        infoHeader = new BorderPane();
        staticEntities = new Pane();
        movableEntities = new Pane();
        this.spritesLoader = new SpritesLoaderImpl(this.getViewManager());
        this.newPlayerName = playerName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Action> getExecutedActions() {
        return this.keyManager.getActions();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateAll() {
        // Info header
        this.header.maxHeight(this.stage.getScene().getHeight() * HEADER_SCALE);
        this.score.setStyle("-fx-padding:" + ((int) (this.stage.getScene().getHeight() * HEADER_SCALE / 2)
                - Math.round(this.score.getFont().getSize() / 2)));
        this.playerNameLabel.setText(this.getViewManager().getView().getController().getPlayerName());
        this.score.setText("Score: " + this.getViewManager().getView().getController().getScore());
        this.lives.setText("Lives: " + this.getViewManager().getView().getController().getLives());
        this.levelNumber.setText("Level: " + this.getViewManager().getView().getController().getLevelNumber());
        infoHeader.setTop(header);

        // Static entities
        this.staticEntities.getChildren().clear();
        for (final Entity entity : this.loader.getView().getController().getLevelEntities()) {
            if (!entity.getClass().getSuperclass().getSimpleName().equals("EntityMovable")) {
                this.addEntityToContainer(entity, staticEntities);
            }
        }
        // All dynamic entities
        this.update();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        // Movable entities
        this.score.setText("Score: " + this.getViewManager().getView().getController().getScore());
        this.lives.setText("Lives: " + this.getViewManager().getView().getController().getLives());
        this.movableEntities.getChildren().clear();
        final List<Entity> movableEntitiesList = this.loader.getView().getController().getLevelEntities().stream()
                .filter(e -> e instanceof EntityMovable).collect(Collectors.toList());
        for (final Entity entity : movableEntitiesList) {
            if (entity.getClass().getSuperclass().getSimpleName().equals("EntityMovable")) {
                this.addEntityToContainer(entity, this.movableEntities);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        this.stage.show();
        this.stage.setScene(
                new Scene(this.gameContainer, this.stage.getScene().getWidth(), this.stage.getScene().getHeight()));
        this.stage.sizeToScene();
        this.stage.getScene().addEventHandler(KeyEvent.KEY_PRESSED, e -> this.keyManager.press(e));
        this.stage.getScene().addEventHandler(KeyEvent.KEY_RELEASED, e -> this.keyManager.release(e));
        this.stage.setOnCloseRequest(e -> this.getViewManager().getView().getController().stop());
        this.gameContainer.getChildren().clear();
        this.gameContainer.getChildren().add(infoHeader);
        this.gameContainer.getChildren().add(staticEntities);
        this.gameContainer.getChildren().add(movableEntities);
        if (!this.loader.getView().getController().isPaused()) { // New game
            this.getViewManager().getView().getController().startNewGame();
            this.getViewManager().getView().getController().setPlayerName(this.newPlayerName);
        } else {
            this.getViewManager().getView().getController().exitPause(true);
        }
        this.reset();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reset() {
        if (this.music.isFirstPlay() || (!this.music.isOff() && this.music.getAudioTrack() != AudioTrack.MUSIC_LEVEL)) {
            this.music.stop();
            this.music.play(AudioTrack.MUSIC_LEVEL);
        }
        this.calculateScale();
        this.updateAll();
    }

    /*
     * Calculate the scale the game must be drawn with and the offsets. This must be
     * called every time the level changes.
     */
    private void calculateScale() {

        // Calculate window proportions and game proportions
        final double windowHeight = this.stage.getScene().getHeight() * GAME_SCALE; // 10% header + 90% game
        final double windowWidth = this.stage.getScene().getWidth();

        double gameWidth = 0;
        double gameHeight = 0;
        for (final Entity entity : this.loader.getView().getController().getLevelEntities()) {
            if (entity.getX() + entity.getWidth() > gameWidth) {
                gameWidth = entity.getX() + entity.getWidth();
            }
            if (entity.getY() + entity.getHeight() > gameHeight) {
                gameHeight = entity.getY() + entity.getHeight();
            }
        }
        // Analyze proportions and get game scale
        this.scale = (windowWidth / gameWidth);
        if (gameHeight * this.scale > windowHeight) {
            this.scale = windowHeight / gameHeight;
        }

        this.offsetX = Math.abs(gameWidth * this.scale - windowWidth) / 2;
        this.offsetY = this.stage.getHeight() * HEADER_SCALE + Math.abs(gameHeight * this.scale - windowHeight) / 2;
    }

    /*
     * Add entity image to container inside game view.
     */
    private void addEntityToContainer(final Entity entity, final Pane container) {
        final ImageView image = this.spritesLoader.getSprite(entity);
        image.setFitWidth(entity.getWidth() * this.scale);
        image.setFitHeight(entity.getHeight() * this.scale);
        image.setLayoutX(offsetX + entity.getX() * this.scale);
        image.setLayoutY(offsetY + entity.getY() * this.scale);
        container.getChildren().add(image);
    }

}