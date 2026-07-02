package snakerunner.model.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import snakerunner.commons.Point2D;
import snakerunner.model.Collectible;
import snakerunner.model.Door;
import snakerunner.model.LevelData;
import snakerunner.model.VictoryCondition;

/**
 * The LevelDataImpl class implements the LevelData interface
 * and provides the data for a level.
 */
public final class LevelDataImpl implements LevelData {
    private final Set<Point2D<Integer, Integer>> obstacles;
    private final List<Collectible> collectibles;
    private final List<Door> doors;
    private final VictoryCondition victoryCondition;

    /**
     * Constructs a LevelDataImpl with the specified obstacles and collectibles.
     *
     * @param obstacles of the level.
     * @param collectibles of the level.
     * @param doors of the level.
     * @param victoryCondition of the level.
     */
    public LevelDataImpl(final Set<Point2D<Integer, Integer>> obstacles, 
                        final List<Collectible> collectibles, 
                        final List<Door> doors,
                        final VictoryCondition victoryCondition) {
        this.obstacles = new HashSet<>(obstacles);
        this.collectibles = new ArrayList<>(collectibles);
        this.doors = new ArrayList<>(doors);
        this.victoryCondition = victoryCondition;
    }

    /**
     * Returns the set of obstacles in the level.
     *
     * @return A set of Point2D representing the positions of the obstacles.
     */
    @Override
    public Set<Point2D<Integer, Integer>> getObstacles() {
        return new HashSet<>(obstacles);
    }

    /**
     * Returns the list of collectibles in the level.
     *
     * @return A list of Collectible objects.
     */
    @Override
    public List<Collectible> getCollectibles() {
        return new ArrayList<>(collectibles);
    }

    // ritorna copia per sicurezza e non la lista originale
    @Override
    public List<Door> getDoors() {
        return new ArrayList<>(doors);
    }

    @Override
    public VictoryCondition getVictoryCondition() {
        return victoryCondition;
    }
}
