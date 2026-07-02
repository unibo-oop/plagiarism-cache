package controllers;

import javafx.scene.Node;
import view.ObstacleView;
import model.obstacle.Obstacle;
import java.util.List;

/**
 * Interface to handle the Obstacle's Controller.
 */

public interface ObstacleController {

    /**
     * This method creates a list of Obstacles.
     * @return an ObstacleView list
     */
    List<ObstacleView> startObstacleView();

    /**
     * This method transforms the ObstacleView list in a Node list.
     * @return a Node list
     */
    List<Node> getNodeList();

    /**
     * This method return the list of obstacles objects as an ObstacleView list.
     * @return ObstacleView list
     */
    List<ObstacleView> getObstacleList();

    /**
     * This method allows to show the obstacles with a speed, call {@link view.MarioView#updatePosition(double)}.
     * @param speed , the value of the Speed
     */
    void moveObstacle(double speed);

    /**
     * This method generates a new Mushroom obstacle.
     * @return a Mushroom Obstacle
     */
    Obstacle generateMushroom();

    /**
     * This method generates a new Flower obstacle.
     * @return a Flower Obstacle
     */
    Obstacle generateFlower();

    /**
     * This method generates a new Cactus obstacle.
     * @return a Cactus Obstacle
     */
    Obstacle generateCactus();

    /**
     * @return Object of powerUp.
     */
    Obstacle generatePowerUpMushroom();
}
