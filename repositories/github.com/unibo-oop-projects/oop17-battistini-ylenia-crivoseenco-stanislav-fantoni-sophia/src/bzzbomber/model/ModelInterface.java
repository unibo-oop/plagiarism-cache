package bzzbomber.model;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import bzzbomber.controller.Controller;
import bzzbomber.model.entities.Block;
import bzzbomber.model.entities.Bomb;
import bzzbomber.model.entities.BzzBomber;
import bzzbomber.model.entities.Door;
import bzzbomber.model.entities.Entity;
import bzzbomber.model.entities.Explosion;
import bzzbomber.model.entities.HealthPoint;
import bzzbomber.model.entities.Insects;

/**
 * interface of the model.
 */
public interface ModelInterface {

    /**
     * Method to reset the game for a new match.
     * 
     * @param rt
     *            The reset type
     */
    void reset(ResetType rt);

    /**
     * Method to change current level to the next.
     */
    void nextLevel();

    /**
     * Method to notify the change of life.
     */
    void changeLife();

    /**
     * Method to notify the change of the monsters.
     */
    void changeEnemy();

    /**
     * Getter for the current level.
     * 
     * @return the level
     */
    Level getCurrentLevel();

    /**
     * Getter for BomberMan.
     * 
     * @return the bomberman
     */
    BzzBomber getBomber();

    /**
     * Getter for all entities.
     * 
     * @return a list of all entities.
     */
    List<Entity> getEntities();

    /**
     * Getter for the bombs.
     * 
     * @return a set of bombs
     */
    Set<Bomb> getAllBombs();

    /**
     * Getter for the healthPoint.
     * 
     * @return a set of heart
     */
    Set<HealthPoint> getAllHeart();

    /**
     * Getter for the blocks.
     * 
     * @return a set of blocks
     */
    Set<Block> getAllBlock();

    /**
     * Getter for the insects.
     * 
     * @return a set of insects
     */
    Set<Insects> getAllInsects();

    /**
     * Getter for the explosions.
     * 
     * @return a set of explosions
     */
    Set<Explosion> getAllExplosions();

    /**
     * Getter for the door.
     * 
     * @return an @Optional of door
     */
    Optional<Door> getDoor();

    /**
     * Manage the bomb. Decrement timer for every bomb and it allow to add
     * explosion.
     */
    void manageBombExplosion();

    /**
     * Manage the insect in game field.
     */
    void manageInsects();

    /**
     * Manage the health in game field.
     */
    void manageHealth();

    /**
     * Setter for controller.
     * 
     * @param c
     *            The controller
     */
    void setController(Controller c);

    /**
     * Getter for timer.
     * 
     * @return the timer
     */
    Timer getTimer();

    /**
     * Setter for game win.
     */
    void heroWin();

    /**
     * Getter for the number of current level.
     * 
     * @return the number of current level
     */
    int getLevelInt();

}
