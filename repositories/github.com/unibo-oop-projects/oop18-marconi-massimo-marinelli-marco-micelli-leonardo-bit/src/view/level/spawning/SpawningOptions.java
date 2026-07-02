package view.level.spawning;

import java.util.HashMap;
import java.util.Map;

/**
 * Links a spawn point type to it's spawning symbol.
 */
public class SpawningOptions {

    private final Map<SpawnPointTypes, String> spawningMap = new HashMap<>();

    /**
     * Initialises the map with all spawn point types.
     */
    public SpawningOptions() {

        spawningMap.put(SpawnPointTypes.KNIGHT, "PlayerKnight");
        spawningMap.put(SpawnPointTypes.ARCHER, "PlayerArcher");
        spawningMap.put(SpawnPointTypes.THIEF, "PlayerThief");
        spawningMap.put(SpawnPointTypes.SKELETON, "EnemySkeleton");
        spawningMap.put(SpawnPointTypes.GOBLIN, "EnemyGoblin");
        spawningMap.put(SpawnPointTypes.BOSS, "EnemyBoss");
        spawningMap.put(SpawnPointTypes.RED_POTION, "RedPotion");
        spawningMap.put(SpawnPointTypes.BLUE_POTION, "BluePotion");
    }

    /**
     * 
     * @return the spawning map.
     */
    public Map<SpawnPointTypes, String> getMap() {
        return spawningMap;
    }
}
