package ballblast.model.components;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.math.Vector2D;

import com.google.common.collect.ImmutableList;

import ballblast.commons.events.EventType;
import ballblast.model.data.GameDataManager;
import ballblast.model.events.GameEventManager;
import ballblast.model.gameobjects.GameObject;
import ballblast.model.gameobjects.GameObjectManager;
import ballblast.model.helpers.GameObjectHelper;
import ballblast.model.helpers.SpawnHelper;
import ballblast.model.physics.CollisionManager;

/**
 * Adds the shotting ability to a {@link GameObject}.
 */
public class ShooterComponent extends AbstractComponent {
    private static final double DEFAULT_SHOT_INTERVAL = 0.15;
    private static final Coordinate BULLET_OFFSET = new Coordinate(2.3, 2);
    private static final Vector2D BULLET_VELOCITY = Vector2D.create(0, -50);

    private final GameObjectManager gameObjectManager;
    private final CollisionManager collisionManager;
    private final GameDataManager gameDataManager;
    private final GameEventManager eventManager;
    private boolean isShooting;
    private double shotInterval;
    private double currentShotInterval;

    /**
     * Class constructor.
     * 
     * @param gameObjectManager the {@link GameObjectManager} used to add
     *                          Bullets.
     * @param collisionManager  the {@link CollisionManager} used to create the
     *                          Bullet's {@link CollisionComponent}.
     * @param gameDataManager   the {@link GameDataManager} used to increment the
     *                          spawned bullets counter.
     * @param eventManager      the {@link GameEventManager}.
     */
    public ShooterComponent(final GameObjectManager gameObjectManager, final CollisionManager collisionManager,
            final GameDataManager gameDataManager, final GameEventManager eventManager) {
        super(ComponentType.SHOOTER);
        this.gameObjectManager = gameObjectManager;
        this.collisionManager = collisionManager;
        this.gameDataManager = gameDataManager;
        this.isShooting = false;
        this.shotInterval = DEFAULT_SHOT_INTERVAL;
        this.eventManager = eventManager;
    }

    @Override
    public final void update(final double elapsed) {
        if (this.isEnabled() && this.isShooting && this.currentShotInterval <= 0) {
            this.shoot(this.spawnBullet());
            this.currentShotInterval = this.shotInterval;
            this.eventManager.addGameEvent(EventType.SHOT);
        }
        this.currentShotInterval -= elapsed;
    }

    /**
     * Enables the component to fire.
     */
    public void startShooting() {
        this.isShooting = true;
    }

    /**
     * Disables the component to fire.
     */
    public void stopShooting() {
        this.isShooting = false;
    }

    /**
     * Sets a custom shot interval.
     * 
     * @param interval The custom shot interval.
     */
    public void setShotInterval(final double interval) {
        this.shotInterval = interval;
    }

    /**
     * Gets the current shot interval.
     * 
     * @return The current shot interval.
     */
    public double getShotInterval() {
        return this.shotInterval;
    }

    private void shoot(final GameObject bullet) {
        SpawnHelper.activeComponents(bullet);
        this.gameObjectManager.addGameObjects(ImmutableList.of(bullet));
        this.gameDataManager.incrementSpawnedBullets();
    }

    private GameObject spawnBullet() {
        return GameObjectHelper.createBullet(this.getSpawnPosition(), BULLET_VELOCITY, collisionManager);
    }

    private Coordinate getSpawnPosition() {
        final double middleY = this.getParent().getPosition().getY() - BULLET_OFFSET.getY();
        final double middleX = this.getParent().getPosition().getX() + BULLET_OFFSET.getX();
        return new Coordinate(middleX, middleY);
    }

}
