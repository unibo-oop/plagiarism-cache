package ballblast.model.physics.handlers;

import java.util.Map;
import java.util.function.BiConsumer;

import org.locationtech.jts.geom.Coordinate;

import com.google.common.collect.ImmutableMap;

import ballblast.model.gameobjects.GameObject;
import ballblast.model.levels.Boundary;
import ballblast.model.physics.Collidable;
import ballblast.model.physics.CollisionHandler;
import ballblast.model.physics.CollisionTag;
import ballblast.model.gameobjects.Player;

/**
 * Represents the handler for the behavior of a {@link Player} after a
 * collision.
 */
public class PlayerCollisionHandler implements CollisionHandler {

    private static final Map<CollisionTag, BiConsumer<Collidable, GameObject>> PLAYER_MAP;

    static {
        PLAYER_MAP = ImmutableMap.<CollisionTag, BiConsumer<Collidable, GameObject>>builder()
                .put(CollisionTag.BALL, (c, g) -> playerCollidesWithBall(g))
                .put(CollisionTag.WALL, PlayerCollisionHandler::playerCollidesWithWall)
                .build();
    }

    @Override
    public final void execute(final Collidable coll, final GameObject obj) {
        // obj is a Player object.
        if (PLAYER_MAP.containsKey(coll.getCollisionTag())) {
            PLAYER_MAP.get(coll.getCollisionTag()).accept(coll, obj);
        }
    }

    private static void checkBoundLimit(final GameObject bound, final GameObject obj) {
        if (Boundary.isRight(bound.getPosition())) {
            obj.setPosition(new Coordinate(bound.getPosition().getX() - obj.getWidth(), obj.getPosition().getY()));
        } else if (Boundary.isLeft(bound.getPosition())) {
            obj.setPosition(new Coordinate(bound.getPosition().getX() + bound.getWidth(), obj.getPosition().getY()));
        }
    }

    private static void playerCollidesWithBall(final GameObject obj) {
        if (!((Player) obj).isImmune()) {
            obj.destroy();
        }
    }

    private static void playerCollidesWithWall(final Collidable coll, final GameObject obj) {
        final GameObject boundary = coll.getAttachedGameObject();
        checkBoundLimit(boundary, obj);
    }
}
