package view.scene;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import controller.Controller;
import controller.GameStatus;
import controller.soundmanager.SoundManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Pair;
import model.battlefield.BattlefieldComponent;
import model.battlefield.Fluid;
import model.battlefield.Location;
import model.player.Player;
import model.projectile.ProjectileType;
import model.resolution.Resolution;
import view.renderer.Renderer;
import view.renderer.RendererImpl;

/**
 * Controller for the View that controls all the scenes that have to be loaded.
 *
 * @author Andrea Manoni
 *
 */
public class SceneMainController extends Application {

    private static final double OFFSETRECTBUTTON = 150.0;
    private static final double OFFSETLBLPROJECTILE = 15.0;
    private static final double STARTING_VOLUME = 0.15;
    private static final double OFFSET_PANETOT = 0.15;
    private static final String CSS = "/all.css";
    private GameController gameController;
    private Stage primaryStage;
    private AnchorPane rootAnchorPane;
    private boolean fullScreen;
    private final Screen screen = Screen.getPrimary();
    private final Rectangle2D bounds = screen.getVisualBounds();
    private Image background;
    private Controller controller;
    private Resolution resolution;
    private NewGameController controllerNewGame;
    private String sceneOn;
    private Renderer renderer;
    private final SoundManager soundManager = new SoundManager();
    private MediaPlayer themeSong;
    private MediaPlayer themeSongGame;
    private URL imgURL;
    private boolean loadDone;

    private List<BattlefieldComponent> locationsList;
    private List<Resolution> resList;

    @Override
    public final void start(final Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setResizable(false);
        this.primaryStage.setTitle("Tank Fury");
        imgURL = SceneMainController.class.getResource("/icon.png");
        this.primaryStage.getIcons().add(new Image(imgURL.toString()));
        this.primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        locationsList = controller.getLocationsJSON().getLocationList();
        resList = IntStream.range(0, controller.getResolutinsJSON().getResolutionList().size())
                .mapToObj(i -> controller.getResolutinsJSON().getResolutionList().get(i))
                .filter(res -> res.getX() <= bounds.getWidth() && res.getY() <= bounds.getHeight())
                .collect(Collectors.toList());
        resolution = resList.get(0);
        soundManager.setVolumeMusic(STARTING_VOLUME);
        soundManager.setVolumeSound(STARTING_VOLUME);
        themeSong = soundManager.getMusicTheme();
        themeSongGame = soundManager.getMusicGame();
        themeSong.setOnEndOfMedia(() -> {
            themeSong = soundManager.getMusicTheme();
            themeSong.play();
        });
        themeSongGame.setOnEndOfMedia(() -> {
            themeSongGame = soundManager.getMusicGame();
            themeSongGame.play();
        });
        themeSong.setVolume(soundManager.getMusicVolume());
        themeSongGame.setVolume(soundManager.getMusicVolume());
        sceneOn = "Menu.fxml";
        themeSong.play();
        sceneMenu();
    }

    /**
     *
     * @return fullScreen
     *
     */
    public Boolean isFullScreen() {
        return fullScreen;
    }

    /**
     * Close the primaryStage.
     */
    public void closePrimaryStage() {
        primaryStage.close();
    }

    /**
     *
     * @return primaryStage
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     *
     * @return locationList
     */
    public List<BattlefieldComponent> getLocationsList() {
        return Collections.unmodifiableList(locationsList);
    }

    /**
     *
     * @return resList
     */
    public List<Resolution> getResolutionList() {
        return Collections.unmodifiableList(resList);
    }

    /**
     * Load the Menu.fxml Scene inside the Stage.
     */
    public void sceneMenu() {
        try {
            themeSongGame.stop();
            themeSong.play();
            final FXMLLoader loader = new FXMLLoader();
            final MenuController menuController = new MenuController();
            loader.setController(menuController);
            loader.setLocation(SceneMainController.class.getResource("/Menu.fxml"));
            rootAnchorPane = loader.load();
            final Scene scene = new Scene(rootAnchorPane);
            scene.getStylesheets().add(SceneMainController.class.getResource(CSS).toString());
            primaryStage.setScene(scene);
            primaryStage.centerOnScreen();
            primaryStage.show();
            sceneOn = "Menu.fxml";
            menuController.setMainApp(this);
            setLayout(menuController);
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * Change the values of the options of the game.
     *
     * @param resolution
     *            resolution
     * @param fullScreen
     *            fullScreen
     * @param musicVolume
     *            musicVolume
     * @param soundVolume
     *            soundVolume
     */
    public void changeOptions(final String resolution, final boolean fullScreen, final double musicVolume,
            final double soundVolume) {
        this.fullScreen = fullScreen;
        resList.stream().filter(r -> r.getName().equals(resolution)).forEach(r -> this.resolution = r);
        soundManager.setVolumeMusic(musicVolume / 100);
        soundManager.setVolumeSound(soundVolume / 100);
        themeSong.setVolume(soundManager.getMusicVolume());
        themeSongGame.setVolume(soundManager.getMusicVolume());
        sceneMenu();
    }

    /**
     * Load the NewGame.fxml Scene inside the Stage.
     */
    public void sceneNewGame() {
        try {
            themeSongGame.stop();
            themeSong.play();
            final FXMLLoader loader = new FXMLLoader();
            controllerNewGame = new NewGameController();
            loader.setController(controllerNewGame);
            loader.setLocation(SceneMainController.class.getResource("/NewGame.fxml"));
            rootAnchorPane = loader.load();
            final Scene scene = new Scene(rootAnchorPane);
            scene.getStylesheets().add(SceneMainController.class.getResource(CSS).toString());
            primaryStage.setScene(scene);
            setLayout(controllerNewGame);
            primaryStage.centerOnScreen();
            primaryStage.show();
            sceneOn = "NewGame.fxml";

            controllerNewGame.setMainApp(this);
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Location selected for the match.
     *
     * @return location
     */
    public Location getLocation() {
        return new Location(controllerNewGame.getLocation().getName(), controllerNewGame.getLocation().getGravity(),
                controllerNewGame.getLocation().getWidth(), controllerNewGame.getLocation().getHeigth(),
                controllerNewGame.getLocation().getWavelength(), controllerNewGame.getLocation().getChild());
    }

    /**
     * Fluid selected for the match.
     *
     * @return fluid
     */
    public Fluid getFluid() {
        return new Fluid(controllerNewGame.getFluid().getName(), controllerNewGame.getFluid().getDensity(),
                controllerNewGame.getFluid().getTerrainColor(), controllerNewGame.getFluid().getMaxFluidSpeed(),
                controllerNewGame.getFluid().getMaxProjectileInitialSpeed(),
                controllerNewGame.getFluid().getBackgroundImage());
    }

    /**
     * Returns the seed selected or generated.
     *
     * @return seed
     */
    public long getSeed() {
        return controllerNewGame.getSeed();
    }

    /**
     * Return the fluidStationary decision.
     *
     * @return fluidStationary
     */
    public boolean isFluidStationary() {
        return controllerNewGame.isFluidStationary();
    }

    /**
     *
     * @return list of players and colors
     */
    public List<Pair<String, Color>> getPlayersList() {
        return controllerNewGame.getPlayersList();
    }

    /**
     * Load the Game.fxml Scene inside the Stage.
     */
    public void sceneGame() {
        try {
            themeSong.stop();
            themeSongGame.play();
            final FXMLLoader loader = new FXMLLoader();
            gameController = new GameController();
            gameController.setController(controller);
            loader.setController(gameController);
            loader.setLocation(SceneMainController.class.getResource("/Game.fxml"));
            rootAnchorPane = loader.load();
            final Scene scene = new Scene(rootAnchorPane);
            scene.getStylesheets().add(SceneMainController.class.getResource(CSS).toString());
            primaryStage.setScene(scene);
            sceneOn = "Game.fxml";
            setLayoutGame(gameController);
            primaryStage.centerOnScreen();
            primaryStage.show();
            gameController.setMainApp(this);
            controller.update(GameStatus.START_GAME);
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Load the Game.fxml Scene inside the Stage.
     */
    public void sceneDemo() {
        try {
            themeSong.stop();
            themeSongGame.play();
            final FXMLLoader loader = new FXMLLoader();
            gameController = new GameController();
            gameController.setController(controller);
            loader.setController(gameController);
            loader.setLocation(SceneMainController.class.getResource("/Game.fxml"));
            rootAnchorPane = loader.load();
            final Scene scene = new Scene(rootAnchorPane);
            scene.getStylesheets().add(SceneMainController.class.getResource(CSS).toString());
            primaryStage.setScene(scene);
            sceneOn = "Demo.fxml";
            setLayoutGame(gameController);
            primaryStage.centerOnScreen();
            primaryStage.show();
            gameController.setMainApp(this);
            controller.update(GameStatus.START_DEMO);
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param name
     *            name
     * @param color
     *            color
     */
    public void setNameTurn(final String name, final Color color) {
        gameController.setNameTurn(name, color);
    }

    /**
     *
     * @param fluidVelocity
     *            fluidVelocity
     */
    public void setWindTurn(final double fluidVelocity) {
        gameController.setWindTurn(fluidVelocity);
    }

    /**
     * GameController for the game scene.
     *
     * @return gameController
     */
    public GameController getGameController() {
        return gameController;
    }

    /**
     *
     * @param inventory
     *            inventory
     * @param projectileSelected
     *            projectileSelected
     */
    public void setComboInventory(final List<Pair<ProjectileType, Integer>> inventory,
            final ProjectileType projectileSelected) {
        gameController.setComboInventory(inventory, projectileSelected);

    }

    /**
     *
     * @return selected type of projectile to shoot.
     */
    public ProjectileType getInventoryValue() {
        return gameController.getInventoryValue();
    }

    /**
     *
     * @param lastAngle
     *            last angle
     */
    public void setSliderAngle(final double lastAngle) {
        gameController.setSliderAngle(lastAngle);

    }

    /**
     *
     * @param lastShootPower
     *            last shoot power
     */
    public void setSliderPower(final double lastShootPower) {
        gameController.setSliderPower(lastShootPower);

    }

    /**
     * Enable certain Nodes.
     */
    public void enableGameNodes() {
        gameController.enableNodes();
    }

    /**
     *
     * @return angle of the turret in degres.
     */
    public double getAngleValue() {
        return gameController.getAngleValue();
    }

    /**
     *
     * @return speed of the projectile to shoot.
     */
    public double getPowerValuePerc() {
        return gameController.getPowerValuePerc();
    }

    /**
     * Load the VicotryScreen.fxml Scene inside the Stage.
     *
     * @param playersList
     *            playersList
     */
    public void sceneVictoryScreen(final List<Player> playersList) {
        try {
            themeSongGame.stop();
            themeSong.play();
            final FXMLLoader loader = new FXMLLoader();
            final VictoryScreenController controllerVictoryScreen = new VictoryScreenController(playersList);
            sceneOn = "VictoryScreen.fxml";
            loader.setController(controllerVictoryScreen);
            loader.setLocation(SceneMainController.class.getResource("/VictoryScreen.fxml"));
            rootAnchorPane = loader.load();
            final Scene scene = new Scene(rootAnchorPane);
            scene.getStylesheets().add(SceneMainController.class.getResource(CSS).toString());
            if (!loadDone) {
                loadDone = true;
                primaryStage.setScene(scene);
                setLayout(controllerVictoryScreen);
            }
            primaryStage.centerOnScreen();
            primaryStage.show();
            controllerVictoryScreen.setMainApp(this);
        } catch (final IOException e) {
            e.printStackTrace();
        }
        loadDone = false;
    }

    private void setLayout(final SceneController controller) {
        final Pane paneTot = (Pane) controller.getNode(this, "paneTot");
        final ImageView backgroundImage = (ImageView) controller.getNode(this, "backgroundImage");
        final Rectangle screenBackGround = (Rectangle) controller.getNode(this, "screenBackGround");
        final Rectangle rectExitScreen = (Rectangle) controller.getNode(this, "rectExitScreen");
        final Pane paneDialog = (Pane) controller.getNode(this, "paneDialog");
        imgURL = SceneMainController.class.getResource("/background.png");
        if (!fullScreen) {
            paneTot.setScaleX(getResolution().getX() / getResolution().getX());
            paneTot.setScaleY(getResolution().getY() / getResolution().getY());
            background = new Image(imgURL.toString(), getResolution().getX(), getResolution().getY(), false, false);
            backgroundImage.setFitWidth(getResolution().getX());
            backgroundImage.setFitHeight(getResolution().getY());
            primaryStage.setWidth(getResolution().getX());
            primaryStage.setHeight(getResolution().getY());
            paneTot.setLayoutX(0.0);
            paneTot.setLayoutY(0.0);
            paneTot.setTranslateX(getResolution().getX() / 2 - paneTot.getPrefWidth() / 2);
            paneTot.setTranslateY(getResolution().getY() / 2 - paneTot.getPrefHeight() / 2);
            paneDialog.setLayoutX(getResolution().getX() / 2 - paneDialog.getPrefWidth() / 2);
            paneDialog.setLayoutY(getResolution().getY() / 2 - paneDialog.getPrefHeight() / 2);
            screenBackGround.setWidth(getResolution().getX());
            screenBackGround.setHeight(getResolution().getY());
            rectExitScreen.setWidth(getResolution().getX());
            rectExitScreen.setHeight(getResolution().getY());
            primaryStage.centerOnScreen();
        } else {
            primaryStage.setFullScreen(true);
            background = new Image(imgURL.toString(), bounds.getWidth(), bounds.getHeight(), false, false);
            backgroundImage.setFitWidth(bounds.getWidth());
            backgroundImage.setFitHeight(bounds.getHeight());
            primaryStage.setWidth(bounds.getWidth());
            primaryStage.setHeight(bounds.getHeight());
            paneTot.setLayoutX(0.0);
            paneTot.setLayoutY(0.0);
            paneTot.setScaleX(bounds.getWidth() / getResolution().getX());
            paneTot.setScaleY(bounds.getHeight() / getResolution().getY());
            paneTot.setTranslateX(bounds.getWidth() / 2 - paneTot.getPrefWidth() / 2);
            paneTot.setTranslateY(bounds.getHeight() / 2 - paneTot.getPrefHeight() / 2);
            paneDialog.setLayoutX(bounds.getWidth() / 2 - paneDialog.getPrefWidth() / 2);
            paneDialog.setLayoutY(bounds.getHeight() / 2 - paneDialog.getPrefHeight() / 2);
            screenBackGround.setWidth(bounds.getWidth());
            screenBackGround.setHeight(bounds.getHeight());
            rectExitScreen.setWidth(bounds.getWidth());
            rectExitScreen.setHeight(bounds.getHeight());
        }
        backgroundImage.setImage(background);
    }

    private void setLayoutGame(final SceneController controller) {

        final Canvas canvas = (Canvas) controller.getNode(this, "canvasGame");
        final Pane paneTot = (Pane) controller.getNode(this, "paneTot");
        final Rectangle rectButtons = (Rectangle) controller.getNode(this, "rectButtons");
        final Rectangle recInfo = (Rectangle) controller.getNodeNested(this, "recInfo", "paneUp");
        final Label lblWind = (Label) controller.getNodeNested(this, "lblWind", "paneUp");
        final Rectangle rectExitScreen = (Rectangle) controller.getNode(this, "rectExitScreen");
        final Label lblProjectileNumber = (Label) controller.getNodeNested(this, "lblProjectileNumber", "paneUp");

        if (!fullScreen) {
            primaryStage.setWidth(getResolution().getX());
            primaryStage.setHeight(getResolution().getY());

            rectButtons.setWidth(getResolution().getX());
            rectButtons.setLayoutX(0.0);
            rectButtons.setLayoutY(getResolution().getY() - rectButtons.getHeight());
            recInfo.setWidth(getResolution().getX());
            recInfo.setLayoutX(0.0);
            recInfo.setLayoutY(0.0);
            paneTot.setLayoutX(rectButtons.getWidth() / 2 - paneTot.getPrefWidth() / 2);
            paneTot.setLayoutY(getResolution().getY() - paneTot.getPrefHeight() - OFFSET_PANETOT);
            gameController.getComboInventory()
                    .setLayoutX(recInfo.getWidth() / 2 - gameController.getComboInventory().getPrefWidth() / 2);
            lblProjectileNumber.setLayoutX(gameController.getComboInventory().getLayoutX()
                    + gameController.getComboInventory().getPrefWidth() + OFFSETLBLPROJECTILE);
            lblWind.setLayoutX(getResolution().getX() - 100);
            lblWind.setLayoutX(getResolution().getX() - 100);
            lblWind.toFront();
            rectExitScreen.setWidth(getResolution().getX());
            rectExitScreen.setHeight(getResolution().getY());

            canvas.setWidth(getResolution().getX());
            canvas.setHeight(getResolution().getY() - rectButtons.getHeight() - recInfo.getHeight());
            canvas.setLayoutX(0);
            canvas.setLayoutY(recInfo.getHeight());
            canvas.autosize();
            rectButtons.setHeight(OFFSETRECTBUTTON);
            canvas.toBack();

        } else {
            primaryStage.setFullScreen(true);
            rectButtons.setWidth(bounds.getWidth());
            rectButtons.setLayoutX(0.0);
            rectButtons.setLayoutY(bounds.getHeight() - rectButtons.getHeight());
            rectButtons.toFront();
            recInfo.setWidth(bounds.getWidth());
            recInfo.setLayoutX(0.0);
            recInfo.setLayoutY(0.0);
            gameController.getComboInventory()
                    .setLayoutX(recInfo.getWidth() / 2 - gameController.getComboInventory().getWidth() / 2);
            lblProjectileNumber.setLayoutX(gameController.getComboInventory().getLayoutX()
                    + gameController.getComboInventory().getWidth() + OFFSETLBLPROJECTILE);
            lblWind.setLayoutX(bounds.getWidth() - 100);
            lblWind.toFront();
            rectExitScreen.setWidth(bounds.getWidth());
            rectExitScreen.setHeight(bounds.getHeight());
            paneTot.setLayoutX(rectButtons.getWidth() / 2 - paneTot.getPrefWidth() / 2);
            paneTot.setLayoutY(bounds.getHeight() - paneTot.getPrefHeight() - OFFSET_PANETOT);
            paneTot.toFront();
            canvas.setWidth(bounds.getWidth());
            canvas.setHeight(bounds.getHeight() - rectButtons.getHeight() - recInfo.getHeight());
            canvas.autosize();
            canvas.setLayoutX(0);
            canvas.setLayoutY(recInfo.getHeight());

            canvas.toBack();
            rectButtons.setHeight(OFFSETRECTBUTTON);
        }
        if (sceneOn.equals("Game.fxml")) {
            imgURL = SceneMainController.class.getResource("/" + getFluid().getBackgroundImage());
            renderer = new RendererImpl(canvas, new Image(imgURL.toString()), getFluid().getTerrainColor());
        } else {
            imgURL = SceneMainController.class.getResource("/earth_air.png");
            renderer = new RendererImpl(canvas, new Image(imgURL.toString()), Color.FORESTGREEN);
        }
    }

    /**
     *
     * @return resolution.
     */
    public Resolution getResolution() {
        return resolution;
    }

    /**
     * Sets the controller.
     *
     * @param controller
     *            controller
     */
    public void setController(final Controller controller) {
        this.controller = controller;
    }

    /**
     *
     * @return sceneOn
     */
    public String getSceneOn() {
        return sceneOn;
    }

    /**
     * The bounds of the screen.
     *
     * @return bounds
     */
    public Rectangle2D getBounds() {
        return bounds;
    }

    /**
     * Return the Renderer.
     *
     * @return renderer
     */
    public Renderer getRenderer() {
        return renderer;
    }

    /**
     *
     * @return soundManager
     */
    public SoundManager getSoundManager() {
        return soundManager;
    }

    /**
     *
     * @return shoot audio
     */
    public MediaPlayer getSoundShoot() {
        return soundManager.getSoundShoot();
    }

    /**
     *
     * @return reload audio
     */
    public MediaPlayer getSoundReload() {
        return soundManager.getSoundReload();
    }

    /**
     *
     *
     * @return explosion audio
     */
    public MediaPlayer getSoundExplosion() {
        return soundManager.getSoundExplosion();
    }

    /**
     *
     * @return falling rocks audio
     */
    public MediaPlayer getSoundFallingRocks() {
        return soundManager.getSoundFallingRocks();
    }

}
