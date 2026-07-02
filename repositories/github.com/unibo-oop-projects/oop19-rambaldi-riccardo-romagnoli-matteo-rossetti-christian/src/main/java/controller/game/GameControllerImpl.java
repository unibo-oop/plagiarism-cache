package controller.game;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javafx.scene.Group;
import model.character.Character;
import model.character.NoPower;
import model.obstacle.Obstacle;
import model.obstacle.ObstacleBehavior;
import model.score.Score;
import model.score.ScoreImpl;
import model.world.GameWorld;
import model.world.GameWorldImpl;
import model.world.MapLoader;
import model.world.MapLoaderImpl;

import org.apache.commons.lang3.tuple.Pair;

import controller.base.BaseController;
import view.renderer.BallLauncherRenderer;
import view.renderer.BallRenderer;
import view.renderer.BucketRenderer;
import view.renderer.ObstacleRenderer;
/**
 * The implementation of {@link GameController} interface.
 */
public class GameControllerImpl implements GameController {

    private static final int NUMBER_OF_BALLS = 12;

    private final BaseController baseController;
    private final String mapChosen;
    private final Character characterChosen;
    private GameWorld gameWorld;
    private final Score score;
    private final ObstacleRenderer obstacleRenderer;
    private final BallLauncherRenderer ballLauncherRenderer;
    private final BucketRenderer bucketRenderer;
    private final BallRenderer ballRenderer;
    private boolean gameOver;
    private Pair<Double, Double> mousePosition;
    private int ballsRemaining;
    private boolean powerActive;
    private int redsRemaining;
    private final MapLoader mapLoader;

    /**
     * GameController's constructor.
     * @param baseController
     *      The instance of {@link BaseController}.
     * @param mapChosen
     *      The map chosen by the player.
     * @param characterChosen
     *      The character chosen by the player.
     */
    public GameControllerImpl(final BaseController baseController, final String mapChosen, final String characterChosen) {
        this.baseController = baseController;
        this.characterChosen = this.initializeCharacter(characterChosen);
        this.mapChosen = mapChosen;
        this.score = new ScoreImpl();
        this.obstacleRenderer = new ObstacleRenderer();
        this.ballLauncherRenderer = new BallLauncherRenderer();
        this.bucketRenderer = new BucketRenderer();
        this.ballRenderer = new BallRenderer();
        this.gameOver = false;
        this.ballsRemaining = NUMBER_OF_BALLS;
        this.mousePosition = Pair.of(0.0, 0.0);
        this.powerActive = false;
        this.redsRemaining = 0;
        this.mapLoader = new MapLoaderImpl();
    }
    // Loads a specific character by searching it into the character folder
    private Character initializeCharacter(final String characterChosen) {
        Character character = null;
        try {
            final Class<?> c = Class.forName("model.character." + characterChosen);
            final Constructor<?> cons = c.getConstructor();
            character = (Character) cons.newInstance();
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        } catch (NoSuchMethodException nsme) {
            nsme.printStackTrace();
        } catch (SecurityException se) {
            se.printStackTrace();
        } catch (InstantiationException ie) {
            ie.printStackTrace();
        } catch (IllegalArgumentException iarge) {
            iarge.printStackTrace();
        } catch (IllegalAccessException iacce) {
            iacce.printStackTrace();
        } catch (InvocationTargetException ite) {
            ite.printStackTrace();
        }

        if (character == null) {
            character = new NoPower();
        }

        return character;
    }

    @Override
    public final void initialize() {
        final List<Obstacle> obstacleList = this.mapLoader.loadMap(this.mapChosen);
        this.gameWorld = new GameWorldImpl();
        this.gameWorld.addObstacles(obstacleList);
        this.gameWorld.setNewTurn();
    }

    @Override
    public final void update(final double delta) {
        this.gameWorld.update(delta, this.mousePosition);
        if (!this.powerActive) {
            this.gameWorld.getHitObstacles().forEach(o -> {
                if (o.getBehavior() == ObstacleBehavior.GREEN) {
                    this.powerActive = true;
                    this.characterChosen.usePower(this.gameWorld);
                }
            });
        }
        if (this.gameWorld.getBall().isStuck()) {
            this.cleanObstacles();
        }
        if (this.isTurnOver()) {
            if (!this.gameWorld.getHitObstacles().isEmpty()) {
                this.cleanObstacles();
            }
            if (this.powerActive) {
                this.powerActive = false;
                this.characterChosen.deletePower(this.gameWorld);
            }
            if (!this.gameWorld.getBucket().slamDunk()) {
                this.ballsRemaining -= 1;
            }
            if (this.ballsRemaining == 0 || this.allRedsAreGone()) {
                this.gameOver = true;
            } else {
                this.gameWorld.setNewTurn();
            }
        }
    }

    private void cleanObstacles() {
        this.score.updateScore(this.gameWorld.getHitObstacles());
        this.gameWorld.removeHitObstacles();
    }

    private boolean isTurnOver() {
        return this.gameWorld.getBall().isOut() || this.gameWorld.getBucket().slamDunk();
    }

    private boolean allRedsAreGone() {
        this.redsRemaining = 0;
        this.gameWorld.getNotHitObstacles().forEach(o -> {
            if (o.getBehavior() == ObstacleBehavior.RED) {
                this.redsRemaining += 1;
            }
        });
        return this.redsRemaining == 0;
    }

    @Override
    public final void endGame(final String playerName) {
        this.score.saveScore(playerName);
        this.baseController.startProgram();
    }

    @Override
    public final void render(final Group gameContainer) {
        gameContainer.getChildren().clear();
        gameContainer.getChildren().addAll(this.ballLauncherRenderer.render(this.gameWorld.getBallLauncher()));
        gameContainer.getChildren().addAll(this.ballRenderer.render(this.gameWorld.getBall()));
        gameContainer.getChildren().addAll(this.bucketRenderer.render(this.gameWorld.getBucket()));
        this.gameWorld.getNotHitObstacles().forEach(o -> gameContainer.getChildren().addAll(this.obstacleRenderer.render(o)));
        this.gameWorld.getHitObstacles().forEach(o -> gameContainer.getChildren().addAll(this.obstacleRenderer.render(o)));
    }

    @Override
    public final boolean isGameOver() {
        return this.gameOver;
    }

    @Override
    public final int getBallsRemaining() {
        return this.ballsRemaining;
    }

    @Override
    public final int getScore() {
        return this.score.getCurrentScore();
    }

    @Override
    public final int getMultiplier() {
        return this.score.getMultiplier();
    }

    @Override
    public final void setMousePosition(final Double posX, final Double posY) {
        this.mousePosition = Pair.of(posX, posY);
    }

    @Override
    public final void launchBall() {
        this.gameWorld.getBallLauncher().launch();
    }

    @Override
    public final boolean isGameWon() {
        return this.allRedsAreGone();
    }
}
