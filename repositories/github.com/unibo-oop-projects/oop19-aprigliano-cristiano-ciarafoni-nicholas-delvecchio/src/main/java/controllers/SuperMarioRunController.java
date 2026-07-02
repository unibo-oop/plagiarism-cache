package controllers;

import java.io.IOException;

import javafx.scene.Node;
import javafx.scene.shape.Rectangle;
import model.mario.MarioType;
import model.score.Score;

public interface SuperMarioRunController {

    /**
     *
     * @return an ObstacleController object
     */
    ObstacleController getObstacleController();

    /**
     *
     * @return a MarioController object
     */
    MarioController getMarioController();

    /**
     *
     * @return a FloorController object
     */
    FloorController getFloorController();

    /**
     * This function is used to call {@link view.SuperMarioRunView#addChildren(Node)} to add a
     * Node to the view.
     * @param n the Node that will be added to the view
     */
    void addNode(Node n);

    /**
     * This method check if there are some collisions between Mario and the floor and set Mario on
     * the floor and start the end game if there is a collision between Mario and an obstacle.
     * @throws IOException
     */
    void checkCollision() throws IOException;

    /**
     * This is the method that find the collision between Mario and an obstacle object.
     * @param mario Rectangle view
     * @return True if there is a collision between Mario and an obstacle object
     */
    boolean checkObstacleCollision(Rectangle mario);

    /**
     * This method change the skin of Mario, it calls {@link MarioController#changeMarioSkin(MarioType type)}.
     * @param type to control if Mario is jumping
     */
    void marioChangeSkin(MarioType type);

    /**
     * Call {@link Score#incrementScore()} to update the score
     * and call {@link view.SuperMarioRunView#setScore(int)} to print the updated score in the view.
     */
    void updateScore();
}
