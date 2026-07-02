package controller.stagehandler;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import controller.Binder;
import controller.ControllerEvent;
import controller.stagehandler.collisionhandler.CollisionHandler;
import controller.stagehandler.collisionhandler.CollisionHandlerImpl;
import controller.stagehandler.enemygenerator.AbstractEnemyGenerator;
import controller.stagehandler.enemygenerator.RandomEnemyGenerator;
import controller.stagehandler.opponentbehaviour.OpponentHandler;
import controller.stagehandler.opponentbehaviour.OpponentHandlerImpl;
import controller.stagehandler.playerhandler.PlayerHandler;
import controller.stagehandler.playerhandler.PlayerHandlerImpl;
import javafx.application.Platform;
import model.Stage;
import model.StageImpl;
import model.UnmodifiableStage;
import model.entity.CollidableEntity;
import model.entity.EntityID;
import model.entity.MovingEntity;
import model.ship.EnemyShip;
import model.ship.PlayerShip.PlayerScore;
import model.ship.basic.BasicSpaceShipFactory;
import model.weapon.Weapon.Projectile;
import utilities.math.Point2D;
import utilities.math.Point2DImpl;

/**
 * A StageHandler implementation.
 */
public class StageHandlerImpl implements StageHandler {

    /** The width of the playable stage within the StageHandler. */
    public static final double STAGE_WIDTH = 160;
    /** The height of the playable stage within the StageHandler. */
    public static final double STAGE_HEIGHT = 90;
    /** The center of the playable stage within the StageHandler. */
    public static final Point2DImpl STAGE_CENTER = new Point2DImpl(STAGE_WIDTH / 2, STAGE_HEIGHT / 2);

    private static final int COMPLETED_STAGE_SCOREFACTOR = 50;
    private static final int MIN_ENEMYQUANTITY = 1;
    private static final int MAX_ENEMYQUANTITY = MIN_ENEMYQUANTITY + 6;
    private static final double MIN_SPAWNING_DISTANCE = Math.min(STAGE_WIDTH, STAGE_HEIGHT) / 4;

    private static final BasicSpaceShipFactory FACTORY = new BasicSpaceShipFactory();

    private final PlayerHandler playerHandler;
    private final OpponentHandler opponentHandler;
    private final CollisionHandler collisionHandler;

    private final Stage stage = new StageImpl();
    private final AbstractEnemyGenerator enemyGenerator = new RandomEnemyGenerator();
    private final Set<EnemyShip> despawnedEnemyList = new HashSet<>();
    private final Set<Projectile> despawnedProjectileList = new HashSet<>();

    private int currentLevel;

    public StageHandlerImpl(final String playerName, final EntityID modelID) {
        this.stage.spawnPlayer(FACTORY.buildPlayerShip(modelID, STAGE_CENTER, playerName));
        this.playerHandler = new PlayerHandlerImpl(this.stage.player());
        this.opponentHandler = new OpponentHandlerImpl();
        this.collisionHandler = new CollisionHandlerImpl();
        this.currentLevel = 0;
        this.loadNewLevel();
        Binder.getView().setPlayerHandler(this.playerHandler);
        Platform.runLater(() -> Binder.getView().refreshArena(new UnmodifiableStage(stage)));
    }

    /**
     * {@inheritDoc}
     * Each turn let the player, the enemies and the projectiles
     * move in this order, checking possible collision at the end of all
     * movements. After a collision, an entity may despawn. When all the
     * enemies in the stage have been despawned, it loads a new level.
     */
    @Override
    public void nextTurn() {
        this.startTurn();
        this.moveEntities();
        this.collisionHandler.checkCollisions(this.stage);
        this.analyzeStageChanges();
        this.terminateTurn();
        Time.elapse();
        Platform.runLater(() -> Binder.getView().refreshArena(new UnmodifiableStage(stage)));
    }

    /**
     * {@inheritDoc}
     * For a StageHandlerImpl, one level is completed when there are 
     * no more enemies on the stage.
     */
    @Override
    public boolean isLevelCompleted() {
        return this.stage.enemies().isEmpty();
    }

    /**
     * {@inheritDoc}
     * For a StageHandlerImpl, the game is over when the player is
     * destroyed.
     */
    @Override
    public boolean isGameOver() {
        return this.stage.player().isnt();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PlayerScore getFinalScore() {
        if (isGameOver()) {
            return this.stage.player().getPlayerScore();
        } else {
            throw new IllegalStateException("Impossible to request a final score if the game hasn't ended yet.");
        }
    }

    /*TURN EXECUTION UTILITIES--------------------------------------------------------------*/
    /**
     * Starts this turn, the remaining opponents and their strategies 
     * and executing the queued player's actions.
     */
    private void startTurn() {
        this.opponentHandler.invoke(this.stage.player());
        this.playerHandler.computeAction();
        this.stage.spawnProjectiles(this.playerHandler.getFiredProjectiles());
        this.stage.spawnProjectiles(this.opponentHandler.getProjectiles());
    }
    /**
     * Moves the player, each enemy and each projectile.
     */
    private void moveEntities() {
        moveWithinStage(this.stage.player());
        for (final EnemyShip enemy : this.stage.enemies()) {
            moveWithinStage(enemy);
        }
        for (final Projectile projectile : this.stage.projectiles()) {
            projectile.move();
        }
    }

    /**
     * Analizes the stage entities deciding which entities are to be despawned.
     */
    private void analyzeStageChanges() {
        for (final EnemyShip enemy : this.stage.enemies()) {
            if (enemy.isnt()) {
                this.despawnedEnemyList.add(enemy);
                this.playerHandler.addScore(enemy.getScorePoints());
                Platform.runLater(() -> Binder.getView().receiveEvent(ControllerEvent.EXPLOSION));
            }
        }
        for (final Projectile projectile : this.stage.projectiles()) {
            if (projectile.isnt() || !isEntityInStage(projectile)) {
                this.despawnedProjectileList.add(projectile);
                Platform.runLater(() -> Binder.getView().receiveEvent(ControllerEvent.PROJECTILE_EXPLOSION));
            }
        }
    }

    /**
     * Makes effective the changes on the stage and load a new level if needed.
     */
    private void terminateTurn() {
        this.stage.despawnEnemies(this.despawnedEnemyList);
        this.opponentHandler.update(this.despawnedEnemyList);
        this.despawnedEnemyList.clear();
        this.stage.despawnProjectiles(this.despawnedProjectileList);
        this.despawnedProjectileList.clear();
        if (this.isLevelCompleted()) {
            this.playerHandler.addScore(this.currentLevel * COMPLETED_STAGE_SCOREFACTOR);
            this.playerHandler.beatLevel();
            this.loadNewLevel();
            Platform.runLater(() -> Binder.getView().receiveEvent(ControllerEvent.NEXT_LEVEL));
        }
    }
    /*--------------------------------------------------------------------------------------*/

    /*STAGE LOADING UTILITIES---------------------------------------------------------------*/
    /**
     * Loads a new level, incrementing the level counter.
     * Randomly generates enemies given by the EnemyGenerator around the player.
     */
    private void loadNewLevel() {
        final Random randomGenerator = new Random();
        Point2D enemyPosition;
        for (int i = 0; this.enemyGenerator.hasNext() && i < this.evaluateEnemyQuantity(); i++) {
            do {
                enemyPosition = new Point2DImpl(randomGenerator.nextDouble() * STAGE_WIDTH,
                                                randomGenerator.nextDouble() * STAGE_HEIGHT);
            } while (enemyPosition.distance(this.stage.player().getPosition()) < MIN_SPAWNING_DISTANCE);
            this.stage.spawnEnemy(FACTORY.buildEnemyShip(this.enemyGenerator.next(), enemyPosition));
        }
        this.opponentHandler.reset(this.stage.enemies());
        this.currentLevel++;
    }
    /**
     * Returns the number of enemies that have to be spawned in the current level.
     * @return the number of enemies that have to be spawned in the current level.
     */
    private int evaluateEnemyQuantity() {
        return Math.min(MIN_ENEMYQUANTITY + this.currentLevel, MAX_ENEMYQUANTITY);
    }
    /*--------------------------------------------------------------------------------------*/

    /*OTHER UTILITIES-----------------------------------------------------------------------*/
    /**
     * Returns true if the specified entity is within the playable stage.
     * @param entity : the specified entity.
     * @return true if the specified entity is within the playable stage; false otherwise.
     */
    private boolean isEntityInStage(final CollidableEntity entity) {
        return entity.getPosition().getX() >= entity.getRadialHitbox() && entity.getPosition().getX() <= STAGE_WIDTH - entity.getRadialHitbox()
            && entity.getPosition().getY() >= entity.getRadialHitbox() && entity.getPosition().getY() <= STAGE_HEIGHT - entity.getRadialHitbox();
    }

    /**
     * Moves an entity, correcting its position if it would move outside the playable stage.
     * @param entity : the entity to be moved.
     */
    private void moveWithinStage(final MovingEntity entity) {
        entity.move();
        if (!isEntityInStage(entity)) {
            correctPosition(entity);
        }
    }
    /**
     * Corrects the position of the specified entity overriding its coordinates with 
     * the lower bound in case of underflow and with the upper bound in case of overflow.
     * @param entity : the specified entity.
     */
    private void correctPosition(final CollidableEntity entity) {
        final Point2DImpl correctedPosition = new Point2DImpl(entity.getPosition().getX(), entity.getPosition().getY());
        final double entityRadius = entity.getRadialHitbox();
        if (entity.getPosition().getX() < entityRadius) {
            correctedPosition.setX(entityRadius);
        } else if (entity.getPosition().getX() > STAGE_WIDTH - entityRadius) {
            correctedPosition.setX(STAGE_WIDTH - entityRadius);
        }
        if (entity.getPosition().getY() < entityRadius) {
            correctedPosition.setY(entityRadius);
        } else if (entity.getPosition().getY() > STAGE_HEIGHT - entityRadius) {
            correctedPosition.setY(STAGE_HEIGHT - entityRadius);
        }
        entity.resetPosition(correctedPosition);
    }
    /*--------------------------------------------------------------------------------------*/

}
