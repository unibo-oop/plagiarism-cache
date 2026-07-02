package zombieversity.controller.core;

import java.util.Set;

import javafx.geometry.BoundingBox;
import javafx.geometry.Point2D;
import zombieversity.controller.CameraController;
import zombieversity.controller.entities.AttackController;
import zombieversity.controller.entities.PlayerController;
import zombieversity.controller.entities.ZombieController;
import zombieversity.controller.input.InputHandler;
import zombieversity.model.score.Score;
import zombieversity.view.GameView;

/**
 * 
 * Inteface to manage general aspect of controller game.
 *
 */
public interface GameWorld {

    /**
     * Used to update the game.
     */
    void handle();

    /**
     * 
     * @return the InputHandler.
     */
    InputHandler getInputHandler();

    /**
     * 
     * @return the PlayerController.
     */
    PlayerController getPlayerController();

    /**
     * 
     * @return player position.
     */
    Point2D getPlayerPos();

    /**
     * 
     * @return ZombieController.
     */
    ZombieController getZombieController();

    /**
     * 
     * @return the game view.
     */
    GameView getGameView();

    /**
     * 
     * @return AttackController.
     */
    AttackController getAttackController();

    /**
     * 
     * @return the camera.
     */
    CameraController getCamera();

    /**
     * 
     * @return the set of obstacles contenairs. 
     */
    Set<BoundingBox> getObstacles();

    /**
     * 
     */
    void increaseScore();

    /**
     * 
     * @return true if the player is Alive, otherwise false.
     */
    boolean playerIsAlive();

    /**
     * 
     * @return the score.
     */
    Score getScore();
}
