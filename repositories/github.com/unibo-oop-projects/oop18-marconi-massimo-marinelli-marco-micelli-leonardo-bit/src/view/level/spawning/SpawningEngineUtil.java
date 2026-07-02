package view.level.spawning;

import java.util.List;

import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.entity.Entity;

/**
 * This class is responsible for spawning the proper entities in the game world
 * according to their spawning points coordinates.
 */
public final class SpawningEngineUtil {

    private static final SpawningOptions SPAWN_MAP = new SpawningOptions();

    private SpawningEngineUtil() { }

    /**
     * Spawns entities in the game world according to their spawnpoints.
     */
    public static void spawnEntities() {
        final Entity playerSpawnPoint = FXGL.getApp().getGameWorld().getEntitiesByType(SpawnPointTypes.KNIGHT, SpawnPointTypes.ARCHER, SpawnPointTypes.THIEF).get(0);
        FXGL.getApp().getGameWorld().spawn(SPAWN_MAP.getMap().get(playerSpawnPoint.getType()), playerSpawnPoint.getX(), playerSpawnPoint.getY());

        final List<Entity> entitySpawnPoints = FXGL.getApp().getGameWorld().getEntitiesByType(SpawnPointTypes.BOSS, SpawnPointTypes.SKELETON, SpawnPointTypes.GOBLIN, SpawnPointTypes.RED_POTION, SpawnPointTypes.BLUE_POTION);

        for (final Entity spawnPoint : entitySpawnPoints) {
           FXGL.getApp().getGameWorld().spawn(SPAWN_MAP.getMap().get(spawnPoint.getType()), spawnPoint.getX(), spawnPoint.getY());
        }
    }
}
