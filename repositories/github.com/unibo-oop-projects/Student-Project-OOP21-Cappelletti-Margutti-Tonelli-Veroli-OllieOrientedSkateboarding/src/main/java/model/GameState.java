package model;

import java.util.List;

import model.entity.DynamicEntity;
import model.player.Player;

/**
 * 
 * Interface that identifies the data of the game.
 *
 */
public interface GameState {

    /**
     * Gets the {@link Player}.
     * @return the {@link Player}.
     */
    Player getPlayer();

    /**
     * Gets the list of {@link DynamicEntity}.
     * @return the list of {@link DynamicEntity}.
     */
    List<DynamicEntity> getEntities();

    /**
     * Checks if is game over.
     * @return true if game over verified, false otherwise.
     */
    boolean isGameOver();

    /**
     * Updates the GameState data.
     * Updates Player and EntityManager.
     * 
     */
    void update();

    /**
     * Sets the speed the entities.
     * @param difficulty the size of the movement that entities have to do at every update. 
     */
    void setVelocity(double difficulty);

}
