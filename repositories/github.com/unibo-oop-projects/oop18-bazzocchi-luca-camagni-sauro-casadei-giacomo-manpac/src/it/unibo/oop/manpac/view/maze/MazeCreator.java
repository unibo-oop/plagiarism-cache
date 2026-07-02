package it.unibo.oop.manpac.view.maze;

import java.util.List;

import com.badlogic.gdx.math.Vector2;

import it.unibo.oop.manpac.view.maze.entities.Pacman;
import it.unibo.oop.manpac.view.maze.entities.PhantomImpl;

/**
 * The interface that create the whole maze.
 *
 */
public interface MazeCreator {

    /**
     * Get the X position of the left tunnel.
     * 
     * @return The x of the left tunnel
     */
    float getLeftPortalX();

    /**
     * Get the X position of the right tunnel.
     * 
     * @return The x of the right tunnel
     */
    float getRightPortalX();

    /**
     * Get the position (as a Vector2) of the pacman spawn point.
     * 
     * @return The pacman spawn point position
     */
    Vector2 getPacmanSpawn();

    /**
     * Get the list with positions (as a Vector2) of all phantoms spawn points.
     * 
     * @return The list with phantoms spawn points
     */
    List<Vector2> getPhantomsSpawns();

    /**
     * Get the total pills number generated in the maze.
     * 
     * @return the total pills in the maze
     */
    int getTotalPills();

    /**
     * Get the Pacman object created in the world.
     * 
     * @return the pacman
     */
    Pacman getPacman();

    /**
     * Get the Phantoms list created in the world.
     * 
     * @return the phantoms
     */
    List<PhantomImpl> getPhantoms();
}
