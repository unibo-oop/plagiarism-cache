package it.unibo.oop.crossline.game.world.arena;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;

/**
 * This class represents the arena.
 */
public class ArenaImpl implements Arena {

    private final TiledMap tiledMap;

    /**
     * Initialize the arena.
     * @param arena the arena file path
     */
    public ArenaImpl(final String arena) {
        tiledMap = new TmxMapLoader().load(arena);
    }

    @Override
    public final List<Vector2> getPlayerSpawnPositons() {
        return null;
    }

    @Override
    public final Vector2 getCenter() {
        final MapProperties mapProperties = tiledMap.getProperties();
        final int mapTileWidth = mapProperties.get("width", Integer.class);
        final int mapTileHeight = mapProperties.get("height", Integer.class);
        final int tilePixelWidth = mapProperties.get("tilewidth", Integer.class);
        final int tilePixelHeight = mapProperties.get("tileheight", Integer.class);
        final int mapPixelWidth = mapTileWidth * tilePixelWidth;
        final int mapPixelHeight = mapTileHeight * tilePixelHeight;
        return new Vector2(mapPixelWidth / 2, mapPixelHeight / 2);
    }

    @Override
    public final TiledMapTileLayer getLayer(final String layer) {
        return (TiledMapTileLayer) tiledMap.getLayers().get(layer);
    }

    @Override
    public final TiledMap getTiledMap() {
        return tiledMap;
    }

    @Override
    public final List<Vector2> getLayerTilesPositions(final TiledMapTileLayer layer) {
        final List<Vector2> positions = new ArrayList<>();
        final int columns = layer.getWidth();
        final int rows = layer.getHeight();
        for (int x = 0; x < columns; x++) {
            for (int y = 0; y < rows; y++) {
                if (layer.getCell(x, y) != null) {
                    positions.add(new Vector2(x, y));
                }
            }
        }
        return positions;
    }

}
