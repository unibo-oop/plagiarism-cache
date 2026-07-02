package zombieversity.model.entities.zombie;

import java.util.Set;

import javafx.geometry.BoundingBox;
import zombieversity.model.entities.Player;

/**
 * Interface to manage zombies' AI.
 *
 */
public interface ZombieAI {

    /**
     * Computes the next positions of all the zombies in the horde.
     * @param zombies to move.
     * @param player
     */
    void computeNextMovement(Set<Zombie> zombies, Player player);

    /**
     * Used to set obstacles at the beginning of the game.
     * @param obstacles set.
     */
    void setObstacles(Set<BoundingBox> obstacles);


}
