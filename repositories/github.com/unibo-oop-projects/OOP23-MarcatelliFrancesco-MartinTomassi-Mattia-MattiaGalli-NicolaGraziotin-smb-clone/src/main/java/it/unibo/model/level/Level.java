package it.unibo.model.level;

import java.util.List;
import java.util.Optional;

import it.unibo.commons.Point2D;
import it.unibo.model.entity.obstacles.CircularSaw;
import it.unibo.model.entity.obstacles.Platform;
import it.unibo.model.entity.player.MeatBoy;
import it.unibo.model.entity.target.BandageGirl;
import it.unibo.model.tiles.Tile;

/**
 * Interface that reads a tmx file and creates a level from it.
 */
public interface Level {

    /**
     * Retrieves a two-dimensional list representing stationary tiles in the game.
     *
     * @return A two-dimensional list of stationary tiles.
     */
    List<List<Optional<Tile>>> getStationary();

    /**
     * Retrieves a list of platforms available.
     *
     * @return A List of Platform objects representing the available platforms.
     */
    List<Platform> getPlatforms();

    /**
     * Retrieves a list of saws.
     *
     * @return A List of Saws.
     */
    List<CircularSaw> getSaws();

    /**
     * Retrieves the bandage girl.
     *
     * @return The bandage girl.
     */
    BandageGirl getBandageGirl();

    /**
     * Gets the width level of the platform.
     *
     * @return An integer representing the width level of the platform.
     */
    int getNumCols();

    /**
     * Gets the height level of the platform.
     *
     * @return An integer representing the height level of the platform.
     */
    int getNumRows();

    /**
     * Retrieves the meat boy.
     *
     * @return The meat boy.
     */
    MeatBoy getMeatBoy();

    /**
     * Retrieves the Starting Coordinates of MeatBoy.
     *
     * @return The Starting Coordinates of MeatBoy.
     */
    Point2D<Double, Double> getMeatBoyStartCoord();

}
