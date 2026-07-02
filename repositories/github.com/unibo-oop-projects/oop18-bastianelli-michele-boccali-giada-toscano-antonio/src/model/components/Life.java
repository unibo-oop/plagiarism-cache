package model.components;

/**
 * Represents the entity's life.
 */
public interface Life extends Component {

    /**
     * Return the life.
     * 
     * @return the life associated to an entity
     */
    int getLife();

    /**
     * Set the life.
     * 
     * @param life the entity's life
     */
    void setLife(int life);

    /**
     * Increase the entity's life (not more than the max life allowed).
     * 
     * @param lifeToAdd
     */
    void increaseLife(int lifeToAdd);

    /**
     * Decrease the entity's life.
     * 
     * @param lifeToRemove how much life to remove
     */
    void decreaseLife(int lifeToRemove);

    /**
     * @return
     */
    boolean isAlive();
    
    /**
     * Make the entity die.
     */
    void setDead();
}
