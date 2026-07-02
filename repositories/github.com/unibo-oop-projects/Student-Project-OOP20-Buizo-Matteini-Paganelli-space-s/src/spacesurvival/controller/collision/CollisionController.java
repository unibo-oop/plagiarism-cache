package spacesurvival.controller.collision;

import java.util.Optional;
import java.util.Set;
import spacesurvival.model.collision.CollisionChecker;
import spacesurvival.model.collision.bounding.BoundaryCollision;
import spacesurvival.model.collision.bounding.CircleBoundingBox;
import spacesurvival.model.collision.bounding.RectBoundingBox;
import spacesurvival.model.common.P2d;
import spacesurvival.model.gameobject.Edge;
import spacesurvival.model.gameobject.fireable.FireableObject;
import spacesurvival.model.gameobject.fireable.SpaceShipSingleton;
import spacesurvival.model.gameobject.main.MainObject;
import spacesurvival.model.gameobject.takeable.TakeableGameObject;
import spacesurvival.utilities.dimension.ScaleOf;

/**
 * Controller for the collision, it use the CollisionChecker.
 * 
 */
public class CollisionController {

    private final CollisionChecker collisionChecker = new CollisionChecker();

    /**
     * Check if an object has collided with a border.
     * @param position 
     * @param rectBoundingBox 
     * @return Optional of BoundaryCollision which contain all info of collision
     */
    public Optional<BoundaryCollision> checkWithBoundaries(final P2d position, final RectBoundingBox rectBoundingBox) {
        final P2d ul = rectBoundingBox.getULCorner();
        final P2d br = rectBoundingBox.getBRCorner();

        final double xShip = position.getX();
        final double yShip = position.getY();

        final double tollerance = ScaleOf.GAME_OBJECT;
        if (yShip < ul.getY() - tollerance) {
            return Optional.of(new BoundaryCollision(Edge.TOP, new P2d(xShip, ul.getY())));
        } else if (yShip > br.getY() + tollerance) {
            return Optional.of(new BoundaryCollision(Edge.BOTTOM, new P2d(position.getX(), br.getY())));
        } else if (xShip > br.getX() + tollerance) {
            return Optional.of(new BoundaryCollision(Edge.RIGHT, new P2d(br.getX(), position.getY())));
        } else if (xShip < ul.getX() - tollerance) {
            return Optional.of(new BoundaryCollision(Edge.LEFT, new P2d(ul.getX(), position.getY())));
        } else {
            return Optional.empty();
        }
    }

    /**
     * Check the collision with the ship.
     * 
     * @param ship the single ship
     * @param rectBoundingBox the rect bounding box to check
     * @return an optional of the ship
     */
    public Optional<SpaceShipSingleton> checkWithShip(final SpaceShipSingleton ship, final RectBoundingBox rectBoundingBox) {
        if (collisionChecker.rectangleToRectangle(rectBoundingBox, (RectBoundingBox) ship.getBoundingBox())) {
            return Optional.of(ship);
        }
        return Optional.empty();
    }

    /**
     * Check the collision with the ship.
     * 
     * @param ship the single ship
     * @param circleBoundingBox the rect bounding box to check
     * @return an optional of the ship
     */
    public Optional<SpaceShipSingleton> checkWithShip(final SpaceShipSingleton ship, final CircleBoundingBox circleBoundingBox) {
        if (collisionChecker.rectangleToCircle((RectBoundingBox) ship.getBoundingBox(), circleBoundingBox)) {
            return Optional.of(ship);
        }
        return Optional.empty();
    }

    /**
     * Check the collision with asteroids.
     * 
     * @param asteroids the set of asteroids
     * @param rectBoundingBox the rect bounding box to check
     * @return an optional of the object if the collision occurred, empty if not.
     */
    public Optional<MainObject> checkWithAsteroids(final Set<MainObject> asteroids, final RectBoundingBox rectBoundingBox) {
        for (final MainObject obj: asteroids) {
            if (collisionChecker.rectangleToCircle(rectBoundingBox, (CircleBoundingBox) obj.getBoundingBox())) {
                return Optional.of(obj);
            }
        }
        return Optional.empty();
    }

    /**
     * Check the collision with chase enemies.
     * 
     * @param chaseEnemies the set of chase enemies
     * @param rectBoundingBox the rect bounding box to check
     * @return an optional of the object if the collision occurred, empty if not.
     */
    public Optional<MainObject> checkWithChaseEnemies(final Set<MainObject> chaseEnemies, final RectBoundingBox rectBoundingBox) {
        for (final MainObject obj: chaseEnemies) {
            if (collisionChecker.rectangleToRectangle(rectBoundingBox, (RectBoundingBox) obj.getBoundingBox())) {
                return Optional.of(obj);
            }
        }
        return Optional.empty();
    }

    /**
     * Check the collision with fire enemies.
     * 
     * @param fireEnemies the set of fire enemies
     * @param rectBoundingBox the rect bounding box to check
     * @return an optional of the object if the collision occurred, empty if not.
     */
    public Optional<FireableObject> checkWithFireEnemies(final Set<FireableObject> fireEnemies, final RectBoundingBox rectBoundingBox) {
        for (final FireableObject obj: fireEnemies) {
            if (collisionChecker.rectangleToRectangle(rectBoundingBox, (RectBoundingBox) obj.getBoundingBox())) {
                return Optional.of(obj);
            }
        }
        return Optional.empty();
    }

    /**
     * Check the collision with boss.
     * 
     * @param boss the boss
     * @param rectBoundingBox the rect bounding box to check
     * @return an optional of the object if the collision occurred, empty if not.
     */
    public Optional<FireableObject> checkWithBoss(final Optional<FireableObject> boss, final RectBoundingBox rectBoundingBox) {
        if (boss.isPresent()) {
            final RectBoundingBox bossBoundingBox = (RectBoundingBox) boss.get().getBoundingBox();
            if (collisionChecker.rectangleToRectangle(rectBoundingBox, bossBoundingBox)) {
                return boss;
            }
        }
        return Optional.empty();
    }

    /**
     * Check the collision with pickable ammo.
     * 
     * @param ammo the set of the ammos
     * @param rectBoundingBox the rect bounding box to check
     * @return an optional of the object if the collision occurred, empty if not.
     */
    public Optional<TakeableGameObject> checkWithAmmo(final Set<TakeableGameObject> ammo, final RectBoundingBox rectBoundingBox) {
        for (final TakeableGameObject obj: ammo) {
            if (collisionChecker.rectangleToCircle(rectBoundingBox, (CircleBoundingBox) obj.getBoundingBox())) {
                return Optional.of(obj);
            }
        }
        return Optional.empty();
    }

    /**
     * Check the collision with pickable heart.
     * 
     * @param hearts the set of hearts
     * @param rectBoundingBox the rect bounding box to check
     * @return an optional of the object if the collision occurred, empty if not.
     */
    public Optional<TakeableGameObject> checkWithHearts(final Set<TakeableGameObject> hearts, final RectBoundingBox rectBoundingBox) {
        for (final TakeableGameObject obj: hearts) {
            if (collisionChecker.rectangleToCircle(rectBoundingBox, (CircleBoundingBox) obj.getBoundingBox())) {
                return Optional.of(obj);
            }
        }
        return Optional.empty();
    }
}
