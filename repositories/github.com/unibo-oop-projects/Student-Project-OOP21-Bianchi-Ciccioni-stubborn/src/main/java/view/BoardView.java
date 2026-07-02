package view;

import java.util.List;

import models.Entity;
import models.Pair;
import models.Point2D;

/**
 * BoardView is the graphical interface counterpart to the WorldMap containted in models package.
 * As such, its goal is to recreate a graphic game map based on data offered by its model
 */
public interface BoardView {

    /**
     * Initialize the view, generating the necessary graphical map based on model data.
     * 
     * @param playerPos The initial position of the player in model map
     * @param allEntities The initial position of all the other entities and their types in model map
     */
    void initializeView(Point2D playerPos, List<Pair<Point2D, Class<? extends Entity>>> allEntities);

    /**
     * Update the view based on movement that just occurred.
     * 
     * @param playerPos The current position of the player in model map
     * @param numEntitiesRemaining The current number of entities remaining
     * @param allEntities The current position of all the other entities and their types in model mal
     */
    void updateWorldMap(Point2D playerPos, int numEntitiesRemaining, List<Pair<Point2D, Class<? extends Entity>>> allEntities);

    /**
     * Send user to main menu after a game over.
     */
    void gameOver();
}
