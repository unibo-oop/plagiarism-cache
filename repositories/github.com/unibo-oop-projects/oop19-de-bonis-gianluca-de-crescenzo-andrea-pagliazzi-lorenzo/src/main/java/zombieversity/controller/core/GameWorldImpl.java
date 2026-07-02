package zombieversity.controller.core;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javafx.geometry.BoundingBox;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Pair;
import zombieversity.controller.CameraController;
import zombieversity.controller.MapController;
import zombieversity.controller.RoundController;
import zombieversity.controller.RoundControllerImpl;
import zombieversity.controller.entities.AttackController;
import zombieversity.controller.entities.AttackControllerImpl;
import zombieversity.controller.entities.PlayerController;
import zombieversity.controller.entities.PlayerControllerImpl;
import zombieversity.controller.entities.ZombieController;
import zombieversity.controller.entities.ZombieControllerImpl;
import zombieversity.controller.input.InputHandler;
import zombieversity.controller.input.InputHandlerImpl;
import zombieversity.model.score.Score;
import zombieversity.model.score.ScoreImpl;
import zombieversity.view.GameView;

/**
 * 
 * The Implemantation of {@link GameWorld}.
 *
 */
public class GameWorldImpl implements GameWorld {

    private static final int TILESZ = 32;
    private static final int MAP_W = 35;
    private static final int MAP_H = 35;

    private final AttackController attackController;
    private final InputHandler inputHandler;
    private final PlayerController playerController;
    private final ZombieController zombieController;
    private final RoundController roundController;
    private final MapController map;
    private final CameraController cams;
    private final GameView gameView;
    private final Score score;
    private final Game game;

    /**
     * Construct a {@link GameWorld}.
     * 
     * @param gameView the game view.
     * @param game
     */
    public GameWorldImpl(final GameView gameView, final Game game) {
        final int scale;

        this.gameView = gameView;

        scale = (int) Math.ceil(gameView.getWidth() / 1000);
        this.map = new MapController(MAP_W, MAP_H, TILESZ, scale);
        this.cams = new CameraController(0, 0, MAP_W * TILESZ * scale, MAP_H * TILESZ * scale, gameView.getWidth(),
                gameView.getHeight());

        this.inputHandler = new InputHandlerImpl();
        this.playerController = new PlayerControllerImpl(this);

        this.zombieController = new ZombieControllerImpl(this);
        this.roundController = new RoundControllerImpl(this);
        this.attackController = new AttackControllerImpl(this);

        this.playerController.getEntity().setPosition(this.cams.getCenter());

        this.score = new ScoreImpl();
        this.game = game;
        this.initHandlers(game.getScene());
        this.initializeModel();
    }

    /**
     * Used to add event handlers of the game.
     * 
     * @param root the scene.
     * 
     */
    private void initHandlers(final Scene root) {

        root.addEventHandler(KeyEvent.KEY_PRESSED, inputHandler.keyBoard());
        root.addEventHandler(KeyEvent.KEY_RELEASED, inputHandler.keyBoard());
        root.addEventHandler(MouseEvent.MOUSE_PRESSED, inputHandler.mouseClicked());
        root.addEventHandler(MouseEvent.MOUSE_MOVED, inputHandler.mouseMoved());
        root.addEventHandler(MouseEvent.MOUSE_RELEASED, inputHandler.mouseReleased());

        root.addEventHandler(KeyEvent.KEY_PRESSED, (event) -> {
                if (event.getCode().equals(KeyCode.ESCAPE)) {
                    game.getLeaderboard().handleScore(score);
                    GameState.change = true;
                    GameState.state = GameState.GameStateEnum.NICKNAME;
                }
        });

        root.addEventHandler(KeyEvent.KEY_PRESSED, (event) -> {
                if (event.getCode().equals(KeyCode.R)) {
                    playerController.getEntity().getFirstWeapon().startRecharging();
                }
        });
    }

    private void initializeModel() {
        this.zombieController.getZombieModel().setObstacles(this.getObstacles());
        this.zombieController.getZombieModel().setSpawnPoints(this.map.getMap().getZombieSpawnPoints().stream()
                .map(s -> s.getPosition()).collect(Collectors.toSet()));
        this.zombieController.getZombieModel().setPlayer(playerController.getEntity());
        this.playerController.getEntity().setObstacles(this.getObstacles());
        this.attackController.setObstacles(this.getObstacles());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void handle() {
        this.inputHandle();
        this.gameUpdate();
        this.gameRender();
    }

    /**
     * Used to update player based on mouse and keyboard input.
     */
    private void inputHandle() {
        this.playerController.updateInput(this.cams.getOffset());
    }

    /**
     * Used to update game model.
     */
    private void gameUpdate() {
        this.playerController.update();
        this.cams.centerOnEntity(this.playerController.getEntity());
        this.zombieController.update();
        this.roundController.update();
        this.attackController.update();
    }

    /**
     * Used to update game view.
     */
    private void gameRender() {
        this.playerController.getEntityView().render();
        final Set<Pair<Point2D, Image>> result = new HashSet<>();
        this.gameView.clear();

        this.gameView.completeRender(this.map.getWorldView().renderBackground(), this.cams.getCamera().start(),
                this.cams.getCamera().end(), this.cams.getOffset(), TILESZ);

        this.gameView.completeRender(this.map.render(), this.cams.getCamera().start(), this.cams.getCamera().end(),
                this.cams.getOffset(), TILESZ);

        result.add(new Pair<>(this.playerController.getEntity().getPosition(), this.playerController.getView()));
        result.addAll(this.zombieController.getZombieView().getSprites());
        this.gameView.completeRender(result, this.cams.getCamera().start(), this.cams.getCamera().end(),
                this.cams.getOffset(), TILESZ);

        this.attackController.getAttacks().forEach((a, i) -> {
            this.gameView.render(i.getSprite().getImage(), a.getPosition().subtract(this.cams.getOffset()));
        });
        this.gameView.renderAmmoLabel(String.valueOf(this.playerController.getEntity().getFirstWeapon().getActualAmmo()));
        this.gameView.renderHPLabel(String.valueOf(this.playerController.getEntity().getLifeManager().getHP()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final InputHandler getInputHandler() {
        return this.inputHandler;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final PlayerController getPlayerController() {
        return this.playerController;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Point2D getPlayerPos() {
        return this.playerController.getEntity().getPosition();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final ZombieController getZombieController() {
        return this.zombieController;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final GameView getGameView() {
        return this.gameView;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final AttackController getAttackController() {
        return this.attackController;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final CameraController getCamera() {
        return this.cams;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Set<BoundingBox> getObstacles() {
        return this.map.getMap().getObstacles().stream().map(s -> s.getBBox()).collect(Collectors.toSet());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void increaseScore() {
        this.score.addKill();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean playerIsAlive() {
        return this.playerController.getEntity().getLifeManager().isAlive();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Score getScore() {
        return this.score;
    }

}
