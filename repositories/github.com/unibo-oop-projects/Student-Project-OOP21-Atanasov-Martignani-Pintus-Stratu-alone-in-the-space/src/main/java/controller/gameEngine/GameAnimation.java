package controller.gameEngine;

import com.almasb.fxgl.core.math.Vec2;

import controller.gameController.GameControllerImpl;
import controller.gameSwitcher.SceneController;
import javafx.animation.AnimationTimer;
import javafx.stage.Stage;
import model.ship.EnemyFactory;
import model.ship.Ship;
import utilities.EnumInt;
import view.GameMap;
import view.GameMapImpl;
import view.WindowManager;
import view.WindowManagerImpl;

/**
 * 
 * Class that implements GameEngine interface.
 *
 */
public class GameAnimation extends AnimationTimer {

    private static final long SLEEP = 10_000_000;
    private static final int SLEEP_TIMER = 100_000;
    private static final double VALUE = 1e9;
    private GameControllerImpl game;
    private long enemyTimer;
    private static final long DELTAENEMY = 5000L;
    private double difficultFactor = 1;
    private Stage stage;
    private String playerName;
    private final GameMapImpl gameMap;
    private final SceneController sceneController;
    private final WindowManager windowManager;
    private long prevTime;

    public GameAnimation(final SceneController sceneController) {
        this.sceneController = sceneController;
        this.windowManager = new WindowManagerImpl(this.sceneController);
        this.stage = this.windowManager.getStage();
        this.gameMap = new GameMapImpl(EnumInt.WIDTH.getValue(), EnumInt.HEIGHT.getValue(), this);
        this.windowManager.addGameMap(this.gameMap);
        this.game = new GameControllerImpl(this.gameMap);
        this.game.setInputController(this.sceneController.getInputController());
        this.enemyTimer = 0;
    }

    @Override
    public void handle(long now) {
        try {
            Thread.sleep(0, SLEEP_TIMER);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (this.enemyTimer == 0) {
            this.enemyTimer = now;
        }
        if ((now - this.enemyTimer) / 1000000L > (DELTAENEMY / this.difficultFactor)
                && this.gameMap.getActiveEnemyShips().size() < 7) {

            this.enemyTimer = now;
            this.difficultFactor *= 1.02;
            Ship enemy = randomShip();
            this.gameMap.addEnemyShip(enemy);
        }
        this.game.update((now - prevTime));
        this.prevTime = now;
    }

    /**
     * @param delta value.
     * @return the actual frame rate based on value passed.
     */
    public double getFrameRateHertz(final long delta) {
        final double frameRate = 1d / delta;
        return frameRate * VALUE;
    }

    /**
     * @return time sleep value.
     */
    public long getTimeSleep() {
        return SLEEP;
    }

    private Ship randomShip() {
        int typeShip = (int) (Math.random() * 100) + 1;
        Vec2 spawnPosition = new Vec2(0, 0);

        spawnPosition.setFromAngle(Math.random() * 360);
        spawnPosition.set(spawnPosition.x * (this.gameMap.getWidth().floatValue() / 2 + 10),
                spawnPosition.y + (this.gameMap.getHeight().floatValue() / 2 + 10));
        spawnPosition.addLocal(this.gameMap.getWidth().floatValue() / 2, this.gameMap.getHeight().floatValue() / 2);

        Ship enemy = null;
        if (typeShip > 0 && typeShip <= 80) {
            enemy = EnemyFactory.basicEnemy(spawnPosition);
        } else if (typeShip > 80 && typeShip <= 95) {
            enemy = EnemyFactory.rifleEnemy(spawnPosition);
        } else {
            enemy = EnemyFactory.missileEnemy(spawnPosition);
        }
        enemy.setTarget(this.gameMap.getPlayer());
        return enemy;
    }

    /**
     * Sets player name.
     * 
     * @param name
     */
    public void setPlayerName(final String name) {
        this.playerName = name;
    }

    /**
     * @return player name.
     */
    public String getPlayerName() {
        return this.playerName;
    }

    /**
     * @return GameMap reference.
     */
    public GameMap getGameMap() {
        return this.gameMap;
    }

    /**
     * @return scene controller reference.
     */
    public SceneController getSceneController() {
        return this.sceneController;
    }

}
