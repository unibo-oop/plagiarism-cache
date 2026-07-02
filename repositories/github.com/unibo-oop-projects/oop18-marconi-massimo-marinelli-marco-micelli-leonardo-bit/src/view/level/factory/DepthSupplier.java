package view.level.factory;

import java.util.Map;

/**
 * Determines the depth level of the dungeon.
 */
public interface DepthSupplier {

    /**
     * 
     * @return the level depth map.
     */
    Map<LevelDepth, TexturePair> getDepthMap();
}
