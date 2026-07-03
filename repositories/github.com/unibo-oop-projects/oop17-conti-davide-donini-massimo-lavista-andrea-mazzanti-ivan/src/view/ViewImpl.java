package view;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.Semaphore;

import audio.AudioPlayer;
import audio.AudioPlayerFactoryImpl;
import audio.AudioPlayerFactory;
import audio.utils.AudioPaths;
import controller.Controller;
import controller.ControllerImpl;
import controller.input.CommandType;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.ModelImpl;
import model.data.Achievement;
import model.data.AchievementType;
import model.data.GameData;
import model.data.HighScore;
import model.entities.properties.Position;
import model.entities.properties.PositionImpl;
import settings.SettingsImpl;
import utility.GameModes;
import utility.StaticUtilities;
import view.controller.GameSceneController;
import view.entities.ViewEntity;
import view.scenefactory.SceneFactory;
import view.scenefactory.SceneFactoryImpl;

/**
 * 
 * Implements methods to initialize the Game.
 *
 */
public final class ViewImpl extends Application implements View {

    private static final int GREEN_SEMAPHORE = 1;

    private final Controller controller;
    private ViewRender viewRender;
    private List<ViewEntity> stateEntities;
    private GameData stateGameData;
    private Map<AchievementType, Achievement> achievements;
    private List<HighScore> highScores;
    private final Semaphore stateModelMutex;
    private final SceneFactory sceneFactory;
    private boolean gamePaused;
    private final AudioPlayerFactory audioFactory = new AudioPlayerFactoryImpl();
    private AudioPlayer backgroundAudio;

    /**
     * Initialize fields.
     */
    public ViewImpl() {
        super();
        this.controller = new ControllerImpl(this);
        this.stateModelMutex = new Semaphore(GREEN_SEMAPHORE);
        this.sceneFactory = new SceneFactoryImpl(this);
    }

    @Override
    public void start(final Stage stage) throws Exception {
        stage.setTitle("MAGNUM CHAOS");
        stage.setOnCloseRequest(e -> Runtime.getRuntime().exit(0));
        this.sceneFactory.setStage(stage);
        this.sceneFactory.openMenuScene();
        this.loadImages();
    }

    @Override
    public void viewLauncher() {
        launch();
    }

    @Override
    public void startGame(final GameSceneController gameSceneController, final GameModes gameMode) {
        this.viewRender = new ViewRender(gameSceneController, gameMode);
        this.viewRender.start();
        this.loadSounds();
        this.backgroundAudio.play();
    }

    @Override
    public void startViewRender() {
        this.viewRender.start();
    }

    @Override
    public void stopViewRender() {
        this.viewRender.stopRender();
        this.pauseSounds();
    }

    @Override
    public void closeGame(final GameData gameData, final boolean isHighScore) {
        this.viewRender.endGame(isHighScore);
        this.stateGameData = gameData;
        this.resetGame();
    }

    @Override
    public void resetGame() {
        this.gamePaused = false;
    }

    @Override
    public void setStateGame(final List<ViewEntity> gameEntities, final GameData gameData) {
        try {
            this.stateModelMutex.acquire();
            this.stateEntities = gameEntities;
            this.stateGameData = gameData;
            this.stateModelMutex.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setAchievements(final Map<AchievementType, Achievement> achievements) {
        this.achievements = achievements;
    }

    @Override
    public void setHighScores(final List<HighScore> highScores) {
        this.highScores = highScores;
    }

    @Override
    public Map<AchievementType, Achievement> getAchievements() {
        return this.achievements;
    }

    @Override
    public List<HighScore> getHighScores() {
        return this.highScores;
    }

    @Override
    public GameData getGameData() {
        return this.stateGameData;
    }

    @Override
    public SceneFactory getSceneFactory() {
        return this.sceneFactory;
    }

    private void loadImages() {
        try {
            Class.forName("view.entities.EntityType");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadSounds() {
        // Necessary to solve a bug when MediaPlayer switches from pause to stop status.
        if (!this.gamePaused) {
            if (Objects.nonNull(this.backgroundAudio)) {
                this.stopSounds();
            }
            this.backgroundAudio = this.audioFactory.createBackgroundAudio(AudioPaths.BACKGROUND);
        }
    }

    private void pauseSounds() {
        this.backgroundAudio.pause();
    }

    private void stopSounds() {
        this.backgroundAudio.stop();
    }

    /**
     * Thread that renders view.
     */
    private class ViewRender extends Thread {

        private static final int BACKGROUND_VELOCITY_DIVIDING_FACTOR = 8;
        private volatile boolean running, endGame, askName;
        private final int period;
        private List<ViewEntity> currentStateEntities;
        private GameData currentStateGameData;
        private final GameSceneController gameSceneController;
        private final GameModes gameMode;
        private final GraphicsContext gamecanvas;
        private final double backgroundVelocity;
        private Position backgroundPosition;
        private final ImageView backgroundImage;

        /**
         * 
         * @param canvas
         *            the game canvas object.
         * @param labels
         *            the labels that display the game data.
         * 
         */
        ViewRender(final GameSceneController gameSceneController, final GameModes gameMode) {
            super();
            this.period = 1000 / SettingsImpl.getSettings().getSelectedFPS();
            this.gameSceneController = gameSceneController;
            this.gameMode = gameMode;
            this.gamecanvas = this.gameSceneController.getCanvas().getGraphicsContext2D();
            this.running = true;
            this.backgroundVelocity = (double) period / BACKGROUND_VELOCITY_DIVIDING_FACTOR;
            this.backgroundPosition = new PositionImpl(0, 0);
            this.backgroundImage = new ImageView();
            this.backgroundImage
                    .setImage(new Image(getClass().getResourceAsStream(BackgroundPaths.RED_SPACE.getPath())));
            this.backgroundImage.setRotate(90);
            this.backgroundImage.setPreserveRatio(true);
            this.backgroundImage.setFitWidth(ModelImpl.GAME_WIDTH);

            ViewImpl.this.sceneFactory.getStage().getScene().addEventHandler(KeyEvent.KEY_PRESSED, e -> {
                Optional<CommandType> commandType = Optional.empty();
                switch (e.getCode()) {
                case UP:
                    commandType = Optional.of(CommandType.UP);
                    break;
                case DOWN:
                    commandType = Optional.of(CommandType.DOWN);
                    break;
                case RIGHT:
                    commandType = Optional.of(CommandType.RIGHT);
                    break;
                case LEFT:
                    commandType = Optional.of(CommandType.LEFT);
                    break;
                case X:
                    commandType = Optional.of(CommandType.SHOOT);
                    break;
                case ESCAPE:
                    commandType = Optional.of(CommandType.PAUSE);
                    break;
                default:
                    break;
                }
                commandType.ifPresent(command -> controller.notifyCommand(command));
            });

            ViewImpl.this.sceneFactory.getStage().getScene().addEventHandler(KeyEvent.KEY_RELEASED, e -> {
                Optional<CommandType> commandType = Optional.empty();
                switch (e.getCode()) {
                case UP:
                    commandType = Optional.of(CommandType.STOP_UP);
                    break;
                case DOWN:
                    commandType = Optional.of(CommandType.STOP_DOWN);
                    break;
                case RIGHT:
                    commandType = Optional.of(CommandType.STOP_RIGHT);
                    break;
                case LEFT:
                    commandType = Optional.of(CommandType.STOP_LEFT);
                    break;
                case X:
                    commandType = Optional.of(CommandType.STOP_SHOOT);
                    break;
                case ESCAPE:
                    commandType = Optional.of(CommandType.PAUSE);
                    break;
                default:
                    break;
                }
                commandType.ifPresent(command -> controller.notifyCommand(command));
            });
        }

        @Override
        public final void run() {
            if (gamePaused) {
                controller.startGame();
            } else {
                gamePaused = true;
                controller.initGame(this.gameMode);
                controller.startGame();
            }
            while (this.running) {
                final long current = System.currentTimeMillis();
                try {
                    stateModelMutex.acquire();
                    currentStateEntities = stateEntities;
                    currentStateGameData = stateGameData;
                    stateModelMutex.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater(() -> {

                    updateBackground();

                    this.gameSceneController.setGameData(currentStateGameData);

                    currentStateEntities.stream().forEach(ve -> {
                        if (ve.getShape() instanceof Circle) {
                            final Circle c = (Circle) ve.getShape();
                            this.gamecanvas.drawImage(ve.getPicture(), ve.getPosition().getX() - c.getRadius(),
                                    ve.getPosition().getY() - c.getRadius(), c.getRadius() * 2, c.getRadius() * 2);
                        } else if (ve.getShape() instanceof Rectangle) {
                            final Rectangle r = (Rectangle) ve.getShape();
                            this.gamecanvas.drawImage(ve.getPicture(), ve.getPosition().getX(), ve.getPosition().getY(),
                                    r.getWidth(), r.getHeight());
                        }
                    });

                });

                StaticUtilities.waitForNextFrame(period, current);
            }
            if (this.endGame) {
                if (this.askName) {
                    Platform.runLater(() -> {
                        controller.saveGame(this.gameSceneController.askPlayerName());
                    });
                }
                Platform.runLater(() -> {
                    sceneFactory.openGameOverScene();
                });
            }
        }

        public final void stopRender() {
            this.running = false;
        }

        public final void endGame(final boolean askName) {
            this.endGame = true;
            this.askName = askName;
        }

        @Override
        public void start() {
            this.running = true;
            super.start();
        }

        private void updateBackground() {
            this.backgroundPosition = new PositionImpl(this.backgroundPosition.getX(),
                    (this.backgroundPosition.getY() + backgroundVelocity)
                            % this.backgroundImage.getImage().getHeight());
            this.gamecanvas.drawImage(this.backgroundImage.getImage(), this.backgroundPosition.getX(),
                    this.backgroundPosition.getY());
            this.gamecanvas.drawImage(this.backgroundImage.getImage(), this.backgroundPosition.getX(),
                    this.backgroundPosition.getY() - this.backgroundImage.getImage().getHeight());

        }
    }

    private enum BackgroundPaths {

        RED_SPACE("red_space.jpg");

        private static final String IMAGES_PATH = "/view/backgroundimages/";
        private final String selectedBackground;

        BackgroundPaths(final String selectedBackground) {
            this.selectedBackground = selectedBackground;
        }

        /**
         * Provides background image paths.
         * 
         * @return the selected background image path as string.
         */
        public String getPath() {
            return BackgroundPaths.IMAGES_PATH + this.selectedBackground;
        }
    }

}
