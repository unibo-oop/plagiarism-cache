package javagotchi.model.needs;

/**
 * Needs interface. It includes getter of each need.
 * @author elisa
 *
 */
public interface Needs {

    /**
     * Getter for the cleanliness.
     * @return the cleanliness.
     */
    Cleanliness getCleanliness();

    /**
     * Getter for the discipline.
     * @return the discipline.
     */
    Discipline getDiscipline();

    /**
     * Getter for the energy.
     * @return the energy.
     */
    Energy getEnergy();

    /**
     * Getter for the happiness.
     * @return the happiness.
     */
    Happiness getHappiness();

    /**
     * Getter for the health.
     * @return the health.
     */
    Health getHealth();

    /**
     * Getter for the hunger.
     * @return the hunger.
     */
    Hunger getHunger();

    /**
     * Method to check if one of the needs has 
     * reached the death level.
     * @return true if one the needs has reached the death level.
     */
    boolean somethingHasReachedTheDeathLevel();

}
