package model.physics;

import model.entities.GameBoardImpl;
import model.entities.GameObject;

/**
 * Interface that updates the physical gameObject.
 */
public interface ComponentPhysics {

    /**
     * Updates the physical component of selected entity.
     * @param timeElapsed time elapsed from game loop
     * @param gameObject entity of the game
     * @param board Gameboard
     */
    void update(int timeElapsed, GameObject gameObject, GameBoardImpl board);
}
