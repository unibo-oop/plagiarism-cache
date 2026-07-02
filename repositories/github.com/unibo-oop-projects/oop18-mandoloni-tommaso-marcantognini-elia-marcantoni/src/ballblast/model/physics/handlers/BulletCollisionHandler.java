package ballblast.model.physics.handlers;

import java.util.Map;
import java.util.function.Consumer;

import com.google.common.collect.ImmutableMap;

import ballblast.model.gameobjects.GameObject;
import ballblast.model.physics.Collidable;
import ballblast.model.physics.CollisionHandler;
import ballblast.model.physics.CollisionTag;

/**
 * Represents the handler for the behavior of a Bullet after a
 * collision.
 */
public class BulletCollisionHandler implements CollisionHandler {

    private static final Map<CollisionTag, Consumer<GameObject>> BULLET_MAP;

    static {
        BULLET_MAP = ImmutableMap.<CollisionTag, Consumer<GameObject>>builder()
                .put(CollisionTag.BALL, BulletCollisionHandler::bulletCollision)
                .put(CollisionTag.WALL, BulletCollisionHandler::bulletCollision)
                .build();
    }

    @Override
    public final void execute(final Collidable coll, final GameObject obj) {
        // obj is a Bullet object.
        if (BULLET_MAP.containsKey(coll.getCollisionTag())) {
            BULLET_MAP.get(coll.getCollisionTag()).accept(obj);
        }
    }

    private static void bulletCollision(final GameObject obj) {
        obj.destroy();
    }

}
