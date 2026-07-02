package ballblast.model.physics.handlers;

import ballblast.model.levels.Boundary;

import java.util.Map;
import java.util.function.BiConsumer;

import org.locationtech.jts.geom.Coordinate;

import com.google.common.collect.ImmutableMap;

import ballblast.model.gameobjects.Ball;
import ballblast.model.gameobjects.GameObject;
import ballblast.model.physics.Collidable;
import ballblast.model.physics.CollisionHandler;
import ballblast.model.physics.CollisionTag;
import ballblast.model.physics.utilities.Bounce;

/**
 * Represents the handler for the behavior of a {@link Ball} after a collision.
 */
public class BallCollisionHandler implements CollisionHandler {

    private static final Map<CollisionTag, BiConsumer<Collidable, GameObject>> BALL_MAP;
    private static final int DEC_LIFE = 1;

    static {
        BALL_MAP = ImmutableMap.<CollisionTag, BiConsumer<Collidable, GameObject>>builder()
                .put(CollisionTag.WALL,   BallCollisionHandler::ballCollidesWithWall)
                .put(CollisionTag.BULLET, (c, g) -> ballCollidesWithBullet(g))
                .build();
    }

    @Override
    public final void execute(final Collidable coll, final GameObject obj) {
        // obj is a Ball object.
        if (BALL_MAP.containsKey(coll.getCollisionTag())) {
            BALL_MAP.get(coll.getCollisionTag()).accept(coll, obj);
        }
    }

    private static void decrementLife(final GameObject ball, final int decrementBy) {
        ((Ball) ball).setCurrentLife(((Ball) ball).getCurrentLife() - decrementBy);
    }

    private static void ballCollidesWithWall(final Collidable coll, final GameObject obj) {
        final Coordinate boundaryPos = coll.getAttachedGameObject().getPosition();
        if (Boundary.isFloor(boundaryPos)) {
            obj.setPosition(
                    new Coordinate(obj.getPosition().getX(), Boundary.BOTTOM.getPosition().getY() - obj.getHeight()));
            Bounce.floorBounce(obj);
        } else if (Boundary.isRoof(boundaryPos)) {
            obj.setPosition(new Coordinate(obj.getPosition().getX(),
                    Boundary.TOP.getPosition().getY() + Boundary.TOP.getHeight()));
            Bounce.floorBounce(obj);
        } else {
            Bounce.wallBounce(obj);
        }
    }

    private static void ballCollidesWithBullet(final GameObject obj) {
        decrementLife(obj, DEC_LIFE);
        if (((Ball) obj).getCurrentLife() <= 0) {
            obj.destroy();
        }
    }
}
