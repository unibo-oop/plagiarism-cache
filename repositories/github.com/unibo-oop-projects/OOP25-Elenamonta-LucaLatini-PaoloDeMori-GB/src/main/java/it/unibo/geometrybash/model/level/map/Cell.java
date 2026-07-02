package it.unibo.geometrybash.model.level.map;

import java.util.Optional;

import it.unibo.geometrybash.model.core.GameObject;

/**
 * Represents a fundamental tile or unit of the {@link GameMap} grid.
 *
 * <p>
 * A cell acts as a spatial container that defines a specific location in the
 * game world.
 * It manages and provides acces to the game entity currently occupying that
 * space
 */
@FunctionalInterface
public interface Cell {

    /**
     * Return the {@link GameObject} currently residing in this cell.
     *
     * <p>
     * The optional indicate that the cell might be empty.
     *
     * @return an Optional containing the {@link GameObject} if the cell is
     *         occupied,
     *         or an empty optional is there isn't any game objcet in it
     */
    Optional<GameObject<?>> getGameObject();
}
