package boxhead.model.entities.zombies;

import boxhead.model.entities.Player;
import java.util.Set;
import javafx.geometry.BoundingBox;


/**
 * Zombies AI interface
 *
 */
public interface ZombieAI {

	/**
     * Set obstacles
     * @param obstacles set
     */
    void setWalls(Set<BoundingBox> walls);
    
    /**
     * Determine zombie's group next position.
     * @param zombies
     * @param player
     */
    void determineNextMovement(Set<Zombie> zombies, Player player);
}
