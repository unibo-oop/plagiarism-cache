package ballblast.model.physics.handlers;

import java.util.Map;
import java.util.function.BiConsumer;

import com.google.common.collect.ImmutableMap;

import ballblast.model.gameobjects.GameObject;
import ballblast.model.physics.Collidable;
import ballblast.model.physics.CollisionHandler;
import ballblast.model.physics.CollisionTag;

/**
 * Represents the handler for the behavior of a Wall after a collision.
 */
public class WallCollisionHandler implements CollisionHandler {

    private static final Map<CollisionTag, BiConsumer<Collidable, GameObject>> WALL_MAP;

    static {
        WALL_MAP = ImmutableMap.<CollisionTag, BiConsumer<Collidable, GameObject>>of();
    }

    @Override
    public final void execute(final Collidable coll, final GameObject obj) {
        if (WALL_MAP.containsKey(coll.getCollisionTag())) {
            WALL_MAP.get(coll.getCollisionTag()).accept(coll, obj);
        }
    }

}
