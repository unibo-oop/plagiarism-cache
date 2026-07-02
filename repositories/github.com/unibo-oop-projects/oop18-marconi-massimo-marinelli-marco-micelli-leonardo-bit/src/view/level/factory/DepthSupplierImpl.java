package view.level.factory;

import java.util.HashMap;
import java.util.Map;

/**
 * Maps the appropriate texture pair to the dungeon level depth.
 */
public class DepthSupplierImpl implements DepthSupplier {

    private final Map<LevelDepth, TexturePair> depthMap = new HashMap<>();

    /**
     * Returns a DepthSupplier.
     */
    public DepthSupplierImpl() {
        depthMap.put(LevelDepth.ZERO, new TexturePair("floor_level0.jpg", "wall_level0.png"));
        depthMap.put(LevelDepth.ONE, new TexturePair("floor_level1.jpg", "wall_level1.jpg"));
        depthMap.put(LevelDepth.TWO, new TexturePair("floor_level2.jpg", "wall_level2.jpg"));
        depthMap.put(LevelDepth.BOSS, new TexturePair("boss_floor.jpg", "boss_wall.jpg"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<LevelDepth, TexturePair> getDepthMap() {
        return depthMap;
    }
}
