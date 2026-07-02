package view.game;

import java.util.Collection;

/**
 * This interface contains all the callable methods of the GameScene.
 */
public interface Game {

    /**
     * This method receives a collection of all elements to draw and calls the drawEntites() method
     * in each lane with the correct parameters.
     * @param toDraw collection of entities to be drawn.
     *          First Pair contains width and center of entity.
     *          Second Pair contains lane number and texture location.
     */
    void drawEntities(Collection<ViewableEntity> toDraw);

    /**
     * Updates the score.
     * @param newScore the updated score.
     */
    void updateScore(Integer newScore);

    /**
     * Updates the number of lives.
     * @param livesNum the number of lives to be shown.
     */
    void updateLives(Integer livesNum);

}
