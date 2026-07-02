package ballblast.model.helpers;


import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.math.Vector2D;

import ballblast.model.components.CollisionComponent;
import ballblast.model.components.GravityComponent;
import ballblast.model.components.InputComponent;
import ballblast.model.components.MovementComponent;
import ballblast.model.components.ShooterComponent;
import ballblast.model.components.SplitterComponent;
import ballblast.model.data.GameDataManager;
import ballblast.model.events.GameEventManager;
import ballblast.model.gameobjects.Ball;
import ballblast.model.gameobjects.BallType;
import ballblast.model.gameobjects.Bullet;
import ballblast.model.gameobjects.GameObjectManager;
import ballblast.model.gameobjects.Player;
import ballblast.model.gameobjects.Wall;
import ballblast.model.inputs.InputManager;
import ballblast.model.inputs.InputManager.PlayerTag;
import ballblast.model.physics.CollisionManager;
import ballblast.model.physics.CollisionTag;
import ballblast.model.physics.handlers.BallCollisionHandler;
import ballblast.model.physics.handlers.BulletCollisionHandler;
import ballblast.model.physics.handlers.PlayerCollisionHandler;
import ballblast.model.physics.handlers.WallCollisionHandler;

/**
 * Represents a factory used to instantiate new GameObjects.
 */
public final class GameObjectHelper {
    private GameObjectHelper() { }

    /**
     * Creates {@link Player} game object.
     * 
     * @param gameObjectManager the {@link GameObjectManager}.
     * @param inputManager      the {@link InputManager}.
     * @param gameDataManager   the {@link GameDataManager}.
     * @param tag               the {@link PlayerTag}.
     * @param collisionManager  the {@link CollisionManager}.
     * @param velocity          the {@link Player}'s velocity.
     * @param position          the {@link Player},s position.
     * @param eventManager      the {@link GameEventManager}.
     * @return the {@link Player} created.
     */
    public static Player createPlayer(final GameObjectManager gameObjectManager, final InputManager inputManager,
            final PlayerTag tag, final CollisionManager collisionManager, final Vector2D velocity, 
            final Coordinate position, final GameDataManager gameDataManager, final GameEventManager eventManager) {
        return new Player.Builder()
                .setVelocity(velocity)
                .setPosition(position)
                .setCollisionHandler(new PlayerCollisionHandler())
                .addComponent(new InputComponent(inputManager, tag))
                .addComponent(new CollisionComponent(collisionManager, CollisionTag.PLAYER))
                .addComponent(new MovementComponent())
                .addComponent(new ShooterComponent(gameObjectManager, collisionManager, gameDataManager, eventManager))
                .build();
    }

    /**
     * Creates {@link Wall} game object.
     * 
     * @param collisionManager the {@link CollisionManager}.
     * @param height           the {@link Wall}'s height.
     * @param width            the {@link Wall}'s width.
     * @param velocity         the {@link Wall}'s velocity.
     * @param position         the {@link Wall}'s position.
     * @return the {@link Wall} created.
     */
    public static Wall createWall(final double height, final double width, final Coordinate position,
            final Vector2D velocity, final CollisionManager collisionManager) {
        return new Wall.Builder()
                .setHeight(height)
                .setWidth(width)
                .setPosition(position)
                .setVelocity(velocity)
                .setCollisionHandler(new WallCollisionHandler())
                .addComponent(new CollisionComponent(collisionManager, CollisionTag.WALL))
                .build();
    }

    /**
     * Creates {@link Bullet} game object.
     * 
     * @param collisionManager the {@link CollisionManager}.
     * @param position         the {@link Bullet}'s position.
     * @param velocity         the {@link Bullet}'s velocity.
     * @return the {@link Bullet} created.
     */
    public static Bullet createBullet(final Coordinate position, final Vector2D velocity,
            final CollisionManager collisionManager) {
        return new Bullet.Builder()
                .setPosition(position)
                .setVelocity(velocity)
                .setCollisionHandler(new BulletCollisionHandler())
                .addComponent(new CollisionComponent(collisionManager, CollisionTag.BULLET))
                .addComponent(new MovementComponent())
                .build();
    }

    /**
     * Creates {@link Ball} game object.
     * 
     * @param collisionManager  the {@link CollisionManager}.
     * @param gameObjectManager the {@link GameObjectManager}.
     * @param gameDataManager   the {@link GameDataManager}.
     * @param ballType          the {@link BallType}.
     * @param life              the {@link Ball}'s life.
     * @param position          the {@link Ball}'s position.
     * @param velocity          the {@link Ball}'s velocity.
     * @param eventManager      the {@link GameEventManager}.
     * @return the {@link Ball created}.
     */
    public static Ball createBall(final BallType ballType, final int life, final Coordinate position,
            final Vector2D velocity, final CollisionManager collisionManager, final GameObjectManager gameObjectManager, 
            final GameDataManager gameDataManager, final GameEventManager eventManager) {
        return new Ball.Builder()
                .setBallType(ballType)
                .setLife(life)
                .setPosition(position)
                .setVelocity(velocity)
                .setCollisionHandler(new BallCollisionHandler())
                .addComponent(new GravityComponent())
                .addComponent(new CollisionComponent(collisionManager, CollisionTag.BALL))
                .addComponent(new SplitterComponent(gameObjectManager, collisionManager, gameDataManager, eventManager))
                .addComponent(new MovementComponent())
                .build();
    }
}
