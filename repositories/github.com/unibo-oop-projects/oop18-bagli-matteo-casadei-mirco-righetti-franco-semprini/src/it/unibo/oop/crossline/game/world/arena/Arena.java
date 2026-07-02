package it.unibo.oop.crossline.game.world.arena;

import java.util.List;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;

/**
 * This is the interface that represents the arena, the world where the player is in.
 */
public interface Arena {

    /**
     * Get the coordinates of the arena center.
     * @return the coordinates of the arena center
     */
    Vector2 getCenter();

    /**
     * Get available player spawn points.
     * @return the spawn points positions
     */
    List<Vector2> getPlayerSpawnPositons();

    /**
     * Get the map layer by its name.
     * @param layer the name layer
     * @return the map layer
     */
    TiledMapTileLayer getLayer(String layer);

    /**
     * Get the arena tiled map.
     * @return the tiled map
     */
    TiledMap getTiledMap();

    /**
     * Get the tiles positions of a give layer.
     * @param layer the layer
     * @return the tiles positions
     */
    List<Vector2> getLayerTilesPositions(TiledMapTileLayer layer);

}
