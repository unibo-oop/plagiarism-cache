package it.unibo.model.api;

import java.util.List;
import java.util.Set;
import it.unibo.common.Pair;
import javafx.scene.input.KeyCode;
/**
 * Interface that manages the game performance with all actions and elements present in the game play. 
 */
public interface GamePerformance {
    /**
     * Method that initialize the entities.
     */
    void initialize();
    /**
     * Getter for the list of entities.
     * 
     * @return a list of entities present.
     */
    Set<Entity> getEntity();
    /**
     * Getter for the list of input.
     * 
     * @return a list of inputs.
     */
    List<KeyCode> getInputs();
    /**
     * Method that adds a new entity to the game play.
     * 
     * @param e the entity to add.
     */
    void addEntity(Entity e);
    /**
     * Method that removes a entity from the game play.
     * 
     * @param e the entity to remove.
     */
    void removeEntity(Entity e);
    /**
     * Method that adds the new key pressed from keyboard.
     * 
     * @param e the key pressed
     */
    void addKey(KeyCode e);
    /**
     * Method that removes tha brick when he got to the bottom of the field.
     * 
     * @param pos the position of the brick.
     */
    void removeBrick(Pair<Double, Double> pos);
    /**
     * Get all the power ups currently present in the game.
     * 
     * @return the list of power ups.
     */
    List<Entity> getPowerUpsPresent();
    /**
     * Get all the windows in the entities list.
     * 
     * @return the list of windows.
     */
    List<Entity> getWindows();
    /**
     * Get the level.
     * 
     * @return the game controller.
     */
    int getLevel();
    /**
     * Get all the brick currently present in the game.
     * 
     * @return the list of bricks.
     */
    Set<Entity> getBricks();
    /**
     * Get all the birds currently present in the game.
     * 
     * @return the list of birds.
     */
    Set<Entity> getBirds();
    /**
     * Removes birds from the game based on their position.
     *
     * @param pos the position of the birds to be removed
     */
    void removeBirds(Pair<Double, Double> pos);
    /**
     * Returns the set of cakes in the game.
     *
     * @return the set of cakes
     */
    Set<Entity> getCakes();
    /**
     * Removes cakes from the game at the specified position.
     *
     * @param pos the position of the cakes to be removed
     */
    void removeCakes(Pair<Double, Double> pos);
}
