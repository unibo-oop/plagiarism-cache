package tmw.controller.level;

import java.util.Optional;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import tmw.common.Dim2D;
import tmw.common.P2d;
import tmw.controller.MainController;
import tmw.controller.StageController;
import tmw.controller.audio.AudioController;
import tmw.controller.entities.MilkController;
import tmw.controller.game.GameController;
import tmw.controller.game.GameControllerImpl;
import tmw.controller.menu.EndLevelControllerImpl;
import tmw.controller.menu.MenuControllerImpl;
import tmw.controller.menu.OptionsSettings;
import tmw.controller.tutorial.TutorialController;
import tmw.controller.tutorial.TutorialRoom;
import tmw.controller.world.BossRoom;
import tmw.controller.world.Room1;
import tmw.controller.world.RoomSwitcherPolicy;
import tmw.controller.world.RoomSwitcherRoutineImpl;
import tmw.controller.world.WorldControllerImpl;
import tmw.controller.world.WorldDispenser;
import tmw.model.audio.AudioTracks;
import tmw.model.world.Level;
import tmw.view.level.RoomView;
import tmw.view.menu.GameOverView;
import tmw.view.menu.GenericMenuView;
import tmw.view.menu.SelectLevelView;
import utils.Rooms;

/**
 * Class witch permit to chose between levels.
 * <p>
 * Implementation of {@link SelectLevelController}
 *
 */
public class SelectLevelControllerImpl implements SelectLevelController {

    private static final P2d PLAYER_POS = new P2d(180, 50);

    private final StageController stageController;
    private final MainController mainController;
    private final GenericMenuView levelView;
    private final GenericMenuView gameOverView;
    private Optional<GameController> gameController = Optional.empty();
    private final WorldControllerImpl worldController;
    private final RoomView roomView;

    private static final Dim2D TUTORIAL_DIMENSION = new Dim2D(1800, 500);

    private Thread gameLoop;
    @FXML
    private Button tutorialButton;
    @FXML
    private Button level1Button;
    @FXML
    private Button bossButton;
    @FXML
    private Button backButton;

    /**
     * Public constructor.
     * 
     * @param stage          {@link StageController}
     * @param maincontroller {@link MainController}
     */
    public SelectLevelControllerImpl(final StageController stage, final MainController maincontroller) {
        this.stageController = stage;
        this.mainController = maincontroller;
        this.roomView = new RoomView(this.mainController.getView(),
                this.mainController.getView().getDefaultGameResolution());

        final Rectangle dim = new Rectangle(this.mainController.getView().getDefaultGameResolution().getWidth(),
                this.mainController.getView().getDefaultGameResolution().getHeight());

        this.worldController = new WorldControllerImpl(new Level(dim), roomView);
        levelView = new SelectLevelView();
        gameOverView = new GameOverView();
        levelView.getLoader().setController(this);
        levelView.init();
        gameOverView.getLoader().setController(this);
        gameOverView.init();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
        this.stageController.setScene(levelView.getScene());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void toTutorial() {

        this.setAudioSettings(this.mainController.getOptionsSettings(), AudioTracks.TUTORIAL_TRACK);

        final RoomView tutorialView = new RoomView(mainController.getView(), TUTORIAL_DIMENSION);

        final TutorialController tutorialController = new TutorialController(
                new Level(new Rectangle(TUTORIAL_DIMENSION.getWidth(), TUTORIAL_DIMENSION.getHeight())), tutorialView);

        tutorialView.setBackground(utils.ImageUtils.getRoomBackground(Rooms.TUTORIAL));
        tutorialController.init();
        tutorialController.getGameWorld().addObserver(tutorialController);
        tutorialController.getView().addObserver(tutorialController);
        tutorialController.getGameWorld().addObserver(this.mainController.getAudioController());
        tutorialController.addObserver(new RoomSwitcherRoutineImpl(tutorialController, this));
        this.roomView.setBackground(utils.ImageUtils.getRoomBackground(Rooms.ROOM1));
        tutorialController.loadRoom(new TutorialRoom(tutorialController, Rooms.TUTORIAL));
        tutorialController.createPlayer(Optional.empty());
        tutorialController.initializeRoomSwitcher(Optional.empty());
        tutorialController.getHud().updateHudValue();
        this.stageController.setScene(tutorialController.getScene());
        this.gameController = Optional.ofNullable(new GameControllerImpl(tutorialController));

        this.gameLoop = new Thread(gameController.get());
        gameLoop.start();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void toLevelOne() {

        this.setAudioSettings(this.mainController.getOptionsSettings(), AudioTracks.LEVELS_TRACK);
        this.initializeWorldProperties();

        this.roomView.setBackground(utils.ImageUtils.getRoomBackground(Rooms.ROOM1));
        worldController.loadRoom(new Room1(worldController, Rooms.ROOM1));
        worldController.createPlayer(Optional.empty());
        worldController.getHud().updateHudValue();

        this.stageController.setScene(worldController.getScene());
        this.gameController = Optional.ofNullable(new GameControllerImpl(worldController));

        this.gameLoop = new Thread(gameController.get());
        gameLoop.start();

    }

    @Override
    public final void changeRoom(final MilkController playerController, final WorldDispenser dispenser,
            final Image levelBackground) {

        this.gameController.get().stop();
        this.gameController = Optional.empty();
        Platform.runLater(new Runnable() {

            @Override
            public void run() {

                roomView.setBackground(utils.ImageUtils.getRoomBackground(dispenser.getRoomType()));
                worldController.loadRoom(dispenser);
                worldController.createPlayer(Optional.ofNullable(playerController));
                playerController.getEntity().setPos(PLAYER_POS);
                worldController.getHud().updateHudValue();
                worldController.initializeRoomSwitcher(Optional.empty());
                stageController.setScene(worldController.getScene());
                gameController = Optional.ofNullable(new GameControllerImpl(worldController));
                gameLoop = new Thread(gameController.get());
                gameLoop.start();
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void toBossLevel() {

        this.setAudioSettings(this.mainController.getOptionsSettings(), AudioTracks.BOSS_TRACK);
        final Optional<MilkController> player = Optional.ofNullable(worldController.getPlayer());

        if (!player.isPresent()) {
            this.initializeWorldProperties();
        } else {

            this.gameController = Optional.empty();
        }
        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                roomView.setBackground(utils.ImageUtils.getRoomBackground(Rooms.BOSS_ROOM));
                worldController.loadRoom(new BossRoom(worldController, Rooms.BOSS_ROOM));
                worldController.createPlayer(player);
                worldController.getHud().updateHudValue();
                stageController.setScene(worldController.getScene());
                gameController = Optional.ofNullable(new GameControllerImpl(worldController));
                final RoomSwitcherPolicy sw = () -> {
                    if (worldController.getEscapeDoor().isTriggered()
                            && worldController.getEscapeDoor().intersect(worldController.getPlayer().getEntity())) {
                        if (gameController.isPresent()) {
                            gameController.get().stop();
                            gameController = Optional.empty();
                        }
                        goToEndLevel();
                    }

                    if (!worldController.getGameWorld().getPlayer().isPresent()) {
                        goToGamerOver();
                    }
                };
                worldController.initializeRoomSwitcher(Optional.ofNullable(sw));

                gameLoop = new Thread(gameController.get());
                gameLoop.start();

            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void goBack() {
        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                if (gameController.isPresent()) {
                    gameController.get().stop();
                    gameController = Optional.empty();
                }
                new MenuControllerImpl(stageController, mainController).start();
                setAudioSettings(mainController.getOptionsSettings(), AudioTracks.MAINMENU_TRACK);

            }
        });

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initializeWorldProperties() {

        worldController.init();
        worldController.getGameWorld().addObserver(worldController);
        worldController.getView().addObserver(worldController);
        worldController.getGameWorld().addObserver(this.mainController.getAudioController());
        worldController.initializeRoomSwitcher(Optional.empty());
        worldController.addObserver(new RoomSwitcherRoutineImpl(worldController, this));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void goToGamerOver() {
        this.gameController.get().stop();
        this.gameController = Optional.empty();
        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                stageController.setScene(gameOverView.getScene());
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    public void goToEndLevel() {
        final SelectLevelController selectLevel = this;
        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                if (gameController.isPresent()) {
                    gameController.get().stop();
                    gameController = Optional.empty();
                }
                new EndLevelControllerImpl(stageController, worldController, mainController, selectLevel).start();

            }
        });
    }

    /**
     * Setter for audio settings.
     * 
     * @param settings {@link OptionsSettings}
     * @param track    {@link AudioTracks}
     */
    private void setAudioSettings(final OptionsSettings settings, final AudioTracks track) {
        final AudioController audio = this.mainController.getAudioController();
        audio.setBackgroudMusic(track);
        audio.setVolume(settings.getVolume());
        if (settings.isMute()) {
            audio.muteVolume(true);
        }
    }

}
