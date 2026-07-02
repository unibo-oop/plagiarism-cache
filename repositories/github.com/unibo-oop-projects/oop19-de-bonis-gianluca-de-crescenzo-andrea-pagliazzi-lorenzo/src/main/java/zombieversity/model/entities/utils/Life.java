package zombieversity.model.entities.utils;

/**
 * 
 * The interface to manage an HealthPoints object.
 *
 */
public interface Life {

    /**
     * 
     * @return HealthPoints object.
     */
    int getHP();

    /**
     * 
     * @param decrease of how much to decrease life
     */
    void decreaseHP(int decrease);

    /**
     * 
     * @return true if the entity is alive, otherwise false.
     */
    boolean isAlive();
}
