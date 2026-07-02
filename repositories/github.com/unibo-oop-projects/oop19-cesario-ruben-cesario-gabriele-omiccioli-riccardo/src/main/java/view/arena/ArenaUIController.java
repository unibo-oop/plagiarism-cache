package view.arena;

import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import controller.Binder;
import controller.stagehandler.StageHandlerImpl;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import model.Stage;
import model.entity.EntityID;
import model.entity.MovingEntity;
import model.ship.basic.BasicEnemyShip;
import model.ship.basic.BasicPlayerShip;
import utilities.math.Point2D;
import utilities.math.Point2DImpl;
import view.AdaptableResolution;
import view.Sound;
import view.display.ScreenUtilities;
import view.image.ViewComponentUtilities;
import view.image.Loader.ImageID;
import view.menu.MainMenuUIController;

/**
 * The Controller related to the arena.fxml GUI.
 */
public final class ArenaUIController implements AdaptableResolution {

    // X and Y positions of the score label in percentage relative to the stage.
    private static final double SCORE_X = 50;
    private static final double SCORE_Y = 1;

    // X and Y positions of the level label in percentage relative to the stage.
    private static final double LEVEL_X = 82;
    private static final double LEVEL_Y = 97;

    // X and Y positions of the speed bar in percentage relative to the stage.
    private static final double SPEED_X = 12;
    private static final double SPEED_Y = 97;

    // X and Y positions of the health bar in percentage relative to the stage.
    private static final double HEALTH_X = 50;
    private static final double HEALTH_Y = 97;

    // X and Y positions of the pause background image in percentage relative to the stage.
    private static final double PAUSE_BKG_X = 50;
    private static final double PAUSE_BKG_Y = 50;

    // X and Y positions of the pauseText image in percentage relative to the stage.
    private static final double PAUSE_TEXT_X = 50;
    private static final double PAUSE_TEXT_Y = 40;

    // X and Y positions of the mainMenuButton image in percentage relative to the stage.
    private static final double MAIN_BTN_X = 40;
    private static final double MAIN_BTN_Y = 60;

    // X and Y positions of the resumeButton image in percentage relative to the stage.
    private static final double RESUME_BTN_X = 62;
    private static final double RESUME_BTN_Y = 60;

    // Offset of the thrusters relative to the ImageView of the entities.
    private static final double THRUSTERS_OFFSET = 55 * ScreenUtilities.getCurrentScaleFactor();

    // Offset of the crosshair relative to the ImageView of the player.
    private static final double CROSSHAIR_OFFSET = 250 * ScreenUtilities.getCurrentScaleFactor();

    // Offset of the life bar relative to the ImageView of the enemies.
    private static final double ENEMY_LIFE_OFFSET = 80 * ScreenUtilities.getCurrentScaleFactor();

    @FXML
    private Pane panel;

    @FXML
    private Rectangle background;

    @FXML
    private Label score;

    @FXML
    private Label level;

    @FXML
    private ProgressBar speed;

    @FXML
    private ProgressBar health;

    @FXML
    private ImageView pauseBackground;

    @FXML
    private ImageView pauseText;

    @FXML
    private ImageView mainMenuButton;

    @FXML
    private ImageView resumeButton;

    // When it is necessary to load an ImageView it can be taken from this map which acts as a cache, thus improving performance
    private static final Map<ImageID, ImageView> CACHE = new EnumMap<>(ImageID.class);

    private static final Map<EntityID, ImageID> PLAYER_IMAGES = new EnumMap<>(EntityID.class);
    private static final Map<EntityID, ImageID> ENEMY_IMAGES = new EnumMap<>(EntityID.class);

    // The list of entities ImageViews to be displayed
    private final List<ImageView> list = new ArrayList<>();

    // ImageViews list of graphics to be displayed
    private final List<ImageView> graphics = new ArrayList<>();

    // Nodes list of graphics to be displayed
    private final List<Node> nodes = new ArrayList<>();

    static {
        initializeData();
    }

    public void initialize() {
        Binder.getView().setArenaUIController(this);
        draw();
    }

    @FXML
    private void mainMenuButtonClicked() {
        try {
            ((MainMenuUIController) view.image.Loader.loadFXML("layouts/mainMenu.fxml").getController()).draw();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Binder.getController().endGame();
    }

    @FXML
    private void resumeButtonClicked() {
        drawPauseMenu(false);
        Binder.getController().resumeGame();
    }

    @FXML
    private void buttonMouseEntered() {
        Sound.play("mouseOver");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void draw() {
        ViewComponentUtilities.resizeAndReposition(panel, background);
        background.setStrokeWidth(background.getStrokeWidth() * ScreenUtilities.getCurrentScaleFactor());
        Font.loadFont(ClassLoader.getSystemResource("fonts/roboclonesemistraight.ttf").toString(), 0);
        ViewComponentUtilities.resizeAndReposition(score, SCORE_X, SCORE_Y);
        ViewComponentUtilities.resizeAndReposition(level, LEVEL_X, LEVEL_Y);
        ViewComponentUtilities.resizeAndReposition(speed, SPEED_X, SPEED_Y);
        ViewComponentUtilities.resizeAndReposition(health, HEALTH_X, HEALTH_Y);

        ViewComponentUtilities.resizeAndReposition(pauseBackground, PAUSE_BKG_X, PAUSE_BKG_Y);
        ViewComponentUtilities.resizeAndReposition(pauseText, PAUSE_TEXT_X, PAUSE_TEXT_Y);
        ViewComponentUtilities.resizeAndReposition(mainMenuButton, MAIN_BTN_X, MAIN_BTN_Y);
        ViewComponentUtilities.resizeAndReposition(resumeButton, RESUME_BTN_X, RESUME_BTN_Y);
        drawPauseMenu(false);

        Binder.getView().reset();
    }

    /**
     * Updates each game element in the panel with the correct position and angle according to the entities in the stage.
     * @param stage The game stage
     */
    public void refresh(final Stage stage) {
        // Removing elements from the panel
        list.forEach((component) -> panel.getChildren().remove(component));
        graphics.forEach((component) -> panel.getChildren().remove(component));
        nodes.forEach((component) -> panel.getChildren().remove(component));

        // Clearing all the lists
        list.clear();
        graphics.clear();
        nodes.clear();

        Set.copyOf(stage.enemies()).forEach((enemy) -> {
            // Refreshing enemy
            if (Binder.getView().isEasterEggs()) {
                refreshImageView(enemy, ImageID.SPACESHIP_EASTER_EGG);
            } else {
                refreshImageView(enemy, ENEMY_IMAGES.get(enemy.getID()));
            }
            // Refreshing enemy thruster
            entityCenteredImageView(enemy, ImageID.OBJECT_ENEMY_THRUSTER, THRUSTERS_OFFSET, 180, enemy.getSpeed().module() / ((BasicEnemyShip) enemy).getMaxSpeed());
            // Refreshing enemy life progress bar
            entityCenteredProgressBar(enemy, ENEMY_LIFE_OFFSET, ((BasicEnemyShip) enemy).getHealth() / ((BasicEnemyShip) enemy).getMaxHealth());
        });

        Set.copyOf(stage.projectiles()).forEach((projectile) -> {
            // Refreshing projectile
            refreshImageView(projectile, ImageID.PROJECTILE_ENEMY_1);
        });

        // Refreshing player
        refreshImageView(stage.player(), PLAYER_IMAGES.get(stage.player().getID()));
        // Refreshing player thruster
        entityCenteredImageView(stage.player(), ImageID.OBJECT_PLAYER_THRUSTER, THRUSTERS_OFFSET, 180, stage.player().getSpeed().module());
        // Refreshing player crosshair
        entityCenteredImageView(stage.player(), ImageID.OBJECT_CROSSHAIR, CROSSHAIR_OFFSET, 0, 1);

        // Adding elements to the panel
        nodes.forEach((component) -> panel.getChildren().add(component));
        graphics.forEach((component) -> panel.getChildren().add(component));
        list.forEach((component) -> panel.getChildren().add(component));

        // Refreshing score and level labels
        score.setText(String.valueOf(stage.player().getPlayerScore().getTotalScore()));
        level.setText("LEVEL  " + (stage.player().getPlayerScore().getLevelBeaten() + 1));

        // Refreshing player speed and health progress bars
        speed.setProgress(stage.player().getSpeed().module()
                / (((BasicPlayerShip) stage.player()).getMaxSpeed() - (((BasicPlayerShip) stage.player()).getDrag())));
        health.setProgress(((BasicPlayerShip) stage.player()).getHealth() / ((BasicPlayerShip) stage.player()).getMaxHealth());

        pauseMenuToFront();
    }

    /**
     * Create a new ImageView with position and angle read by the entity passed at the method and adds the ImageView to the ImageView list.
     * @param entity The entity
     * @param type The key corresponding to the image in the cache
     */
    private void refreshImageView(final MovingEntity entity, final ImageID imageID) {
        final ImageView component = new ImageView();
        component.setImage(CACHE.get(imageID).getImage());
        component.setFitWidth(component.getImage().getWidth() * ScreenUtilities.getCurrentScaleFactor());
        component.setFitHeight(component.getImage().getHeight() * ScreenUtilities.getCurrentScaleFactor());
        component.setX(ScreenUtilities.getCurrentWidth() * entity.getPosition().getX() / StageHandlerImpl.STAGE_WIDTH - component.getFitWidth() / 2 + background.getStrokeWidth());
        component.setY(ScreenUtilities.getCurrentHeight() * (StageHandlerImpl.STAGE_HEIGHT - entity.getPosition().getY()) / StageHandlerImpl.STAGE_HEIGHT
                - component.getFitHeight() / 2 + background.getStrokeWidth());
        component.setRotate(Math.toDegrees(-entity.getRotation()) + 90);
        list.add(component);
    }

    /**
     * Converts the entity position from stage reference system to the screen arena.
     * @param entity The entity
     * @return the position of the entity on the arena
     */
    private Point2D getEntityArenaPosition(final MovingEntity entity) {
        final Point2D position = new Point2DImpl();
        position.setX(ScreenUtilities.getCurrentWidth() * entity.getPosition().getX() / StageHandlerImpl.STAGE_WIDTH);
        position.setY(ScreenUtilities.getCurrentHeight() * (StageHandlerImpl.STAGE_HEIGHT - entity.getPosition().getY()) / StageHandlerImpl.STAGE_HEIGHT);
        return position;
    }

    /**
     * Adds an ImageView centered to the entity.
     * @param entity The entity
     * @param imageID The image ID
     * @param offset Distance of the ImageView from the center of the entity
     * @param angle Rotation of the ImageView
     * @param opacity Opacity of the ImageView
     */
    private void entityCenteredImageView(final MovingEntity entity, final ImageID imageID, final double offset, final double angle, final double opacity) {
        final ImageView component = new ImageView();
        component.setImage(CACHE.get(imageID).getImage());
        component.setX(getEntityArenaPosition(entity).getX() -  offset * Math.sin(entity.getRotation() + Math.toRadians(angle - 90)) - component.getImage().getWidth() / 2);
        component.setY(getEntityArenaPosition(entity).getY() -  offset * Math.cos(entity.getRotation() + Math.toRadians(angle - 90)) - component.getImage().getHeight() / 2);
        component.setRotate(Math.toDegrees(-entity.getRotation()) + 90);
        component.setOpacity(opacity);
        component.setScaleX(ScreenUtilities.getCurrentScaleFactor());
        component.setScaleY(ScreenUtilities.getCurrentScaleFactor());
        graphics.add(component);
    }

    /**
     * Adds a progress bar centered to the entity.
     * @param entity The entity
     * @param offset Distance of the ImageView from the center of the entity
     * @param value The progress of the bar
     */
    private void entityCenteredProgressBar(final MovingEntity entity, final double offset, final double value) {
        final ProgressBar component = new ProgressBar();
        component.getStyleClass().add("enemy-progress-bar");
        component.setPrefWidth(100 * ScreenUtilities.getCurrentScaleFactor());
        component.setPrefHeight(10 * ScreenUtilities.getCurrentScaleFactor());
        component.setLayoutX(getEntityArenaPosition(entity).getX() - component.getPrefWidth() / 2);
        component.setLayoutY(getEntityArenaPosition(entity).getY() - component.getPrefHeight() / 2 + offset);
        component.setProgress(value);
        nodes.add(component);
    }

    /**
     * Draws the pause menu if the value is true.
     * @param value A boolean representing if the pause menu should be drawn
     */
    public void drawPauseMenu(final Boolean value) {
        pauseBackground.setVisible(value);
        pauseText.setVisible(value);
        mainMenuButton.setVisible(value);
        resumeButton.setVisible(value);
        pauseMenuToFront();
    }

    /**
     * Move the pause menu to front.
     */
    private void pauseMenuToFront() {
        pauseBackground.toFront();
        pauseText.toFront();
        mainMenuButton.toFront();
        resumeButton.toFront();
    }

    /**
     * Loads all the images in the image cache and initialize player and enemy maps.
     */
    private static void initializeData() {
        CACHE.putAll(
                Set.<ImageID>of(
                        ImageID.DEFAULT,
                        ImageID.SPACESHIP_PLAYER_FIGHTER,
                        ImageID.SPACESHIP_PLAYER_JUGGERNAUT,
                        ImageID.SPACESHIP_PLAYER_CUTTER,
                        ImageID.SPACESHIP_ENEMY_FIGHTER,
                        ImageID.SPACESHIP_ENEMY_JUGGERNAUT,
                        ImageID.SPACESHIP_ENEMY_CUTTER,
                        ImageID.SPACESHIP_EASTER_EGG,
                        ImageID.PROJECTILE_PLAYER_1,
                        ImageID.PROJECTILE_ENEMY_1,
                        ImageID.OBJECT_PLAYER_THRUSTER,
                        ImageID.OBJECT_ENEMY_THRUSTER,
                        ImageID.OBJECT_BONUS,
                        ImageID.OBJECT_CROSSHAIR
                ).stream().map((imageID) -> Map.entry(imageID, view.image.Loader.loadResizeImageView(imageID)))
                .collect(Collectors.toMap((entry) -> entry.getKey(), (entry) -> entry.getValue()))
        );
        PLAYER_IMAGES.putAll(Map.ofEntries(
                Map.entry(EntityID.SPACESHIP_BASIC, ImageID.SPACESHIP_PLAYER_FIGHTER),
                Map.entry(EntityID.FIGHTER, ImageID.SPACESHIP_PLAYER_FIGHTER),
                Map.entry(EntityID.JUGGERNAUT, ImageID.SPACESHIP_PLAYER_JUGGERNAUT),
                Map.entry(EntityID.CUTTER, ImageID.SPACESHIP_PLAYER_CUTTER)
        ));
        ENEMY_IMAGES.putAll(Map.ofEntries(
                Map.entry(EntityID.SPACESHIP_BASIC, ImageID.SPACESHIP_ENEMY_FIGHTER),
                Map.entry(EntityID.FIGHTER, ImageID.SPACESHIP_ENEMY_FIGHTER),
                Map.entry(EntityID.JUGGERNAUT, ImageID.SPACESHIP_ENEMY_JUGGERNAUT),
                Map.entry(EntityID.CUTTER, ImageID.SPACESHIP_ENEMY_CUTTER)
        ));
    }

}
