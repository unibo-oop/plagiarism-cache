package it.unibo.pacman.model.entities;

import it.unibo.pacman.model.utilities.Difficulty;
import it.unibo.pacman.model.utilities.Position;
/**
 * An interface for creates {@link Entity}. 
 */
public interface EntityFactory {
    /**
     * Used to build a pill.
     * 
     * @param position the position of the pill
     * @return the pill
     */
    Entity createPill(Position position);
    /**
     * Used to build a power pill.
     * 
     * @param position the position of the power pill
     * @return the power pill
     */
    Entity createPowerPill(Position position);
    /**
     * Used to build a wall.
     * 
     * @param position the position of the wall
     * @return the wall
     */
    Entity createWall(Position position);
    /**
     * Used to build a Inky ghost.
     * 
     * @param position the position of the ghost
     * @param difficulty the difficulty of the game 
     * @return the ghost
     */
    Movable createInky(Position position, Difficulty difficulty);
    /**
     * Used to build a Blinky ghost.
     * 
     * @param position the position of the ghost
     * @param difficulty the difficulty of the game 
     * @return the ghost
     */
    Movable createBlinky(Position position, Difficulty difficulty);
    /**
     * Used to build a Pinky ghost.
     * 
     * @param position the position of the ghost
     * @param difficulty the difficulty of the game 
     * @return the ghost
     */
    Movable createPinky(Position position, Difficulty difficulty);
    /**
     * Used to build a Clyde ghost.
     * 
     * @param position the position of the ghost
     * @param difficulty the difficulty of the game 
     * @return the ghost
     */
    Movable createClyde(Position position, Difficulty difficulty);
    /**
     * Used to build a pacman.
     * 
     * @param position the position of the pacman
     * @return the pacman
     */
    Mortal createPacMan(Position position);
}
