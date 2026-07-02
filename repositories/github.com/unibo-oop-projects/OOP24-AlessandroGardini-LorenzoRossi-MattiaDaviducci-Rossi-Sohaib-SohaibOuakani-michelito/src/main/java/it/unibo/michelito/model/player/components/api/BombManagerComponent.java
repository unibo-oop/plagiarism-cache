package it.unibo.michelito.model.player.components.api;

import it.unibo.michelito.model.bomb.api.BombType;
import it.unibo.michelito.model.maze.api.Maze;
import it.unibo.michelito.model.player.api.Player;
import it.unibo.michelito.util.Position;
import  it.unibo.michelito.model.bomb.api.Bomb;

/**
 * Interface modelling a {@link Bomb} manger for the {@link Player}.
 */
public interface BombManagerComponent {
    /**
     * Sets the limit of placeable {@link Bomb}.
     * @param limit the new maximum number of placeable {@link Bomb}
     */
    void setBombLimit(int limit);

    /**
     * Gets the limit of placeable {@link Bomb}.
     * @return the maximum number of placeable {@link Bomb}
     */
    int getBombLimit();

    /**
     * Method that adds a {@link Bomb} to {@link Maze} in to a {@link Position}.
     * @param maze the {@link Maze} where to place the {@link Bomb}.
     * @param position the {@link Position} of the placed {@link it.unibo.michelito.model.bomb.api.Bomb}
     * @param deltaTime thw time of the update to see if the bomb is in cooldown
     */
    void place(Maze maze, Position position, long deltaTime);

    /**
     * Notify the {@link BombManagerComponent} to place {@link Bomb}.
     */
    void notifyToPlace();

    /**
     * Sets the placeable {@link BombType}.
     * @param type the new {@link BombType} to place
     */
    void setBombType(BombType type);

    /**
     * Gets the placeable {@link BombType}.
     * @return the {@link BombType}.
     */
    BombType getBombType();

    /**
     * Aborts the {@link Bomb} placement.
     */
    void abortPlace();
}
