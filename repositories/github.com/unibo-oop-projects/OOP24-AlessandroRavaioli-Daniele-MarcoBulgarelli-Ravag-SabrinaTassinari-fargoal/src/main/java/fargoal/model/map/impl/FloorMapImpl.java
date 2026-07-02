package fargoal.model.map.impl;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import fargoal.commons.api.Position;
import fargoal.model.map.api.Dimension;
import fargoal.model.map.api.FloorMap;
import fargoal.view.api.Renderer;

/**
 * A class that models a floor.
 */
public class FloorMapImpl implements FloorMap {

    private final Map<Position, Renderer> tiles;
    private final Map<Position, Renderer> walls;
    private final Dimension size;

    /**
     * The constructor that defines the map.
     * 
     * @param tiles - a Map that contains all the Positions of the tiles and their renderers
     * @param walls - a Map that constains all positions of the walls and their renderers
     * @param width - the width of the map
     * @param height - the height of the map
     * @see Map
     * @see Position
     * @see Renderer
     */
    protected FloorMapImpl(final Map<Position, Renderer> tiles,
            final Map<Position, Renderer> walls,
            final int width,
            final int height) {
        this.tiles = Map.copyOf(tiles);
        this.walls = Map.copyOf(walls);
        this.size = new Dimension(width, height);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isTile(final Position pos) {
        return this.tiles.containsKey(pos);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Dimension getSize() {
        return size;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Position getRandomTile() {
        final List<Position> list = new LinkedList<>(this.tiles.keySet());
        Collections.shuffle(list);
        return list.getFirst();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render(final Position pos) {
        (this.isTile(pos) ? this.tiles : this.walls).get(pos).render();
    }
}
