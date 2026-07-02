package javagotchi.model.needs;

import java.io.Serializable;
/**
 * Class that implements a group of needs. It includes a method to check if levels are ok.
 * @author elisa
 *
 */
public class NeedsImpl implements Needs, Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -1097864417714871360L;
    private final Cleanliness cleanliness;
    private final Discipline discipline;
    private final Energy energy;
    private final Happiness happiness;
    private final Health health;
    private final Hunger hunger;

    /**
     * Needs constructor.
     */
    public NeedsImpl() {
        this.cleanliness = new Cleanliness();
        this.discipline = new Discipline();
        this.energy = new Energy();
        this.happiness = new Happiness();
        this.health = new Health();
        this.hunger = new Hunger();
    }

    /**
     * Getter for the cleanliness.
     * @return the cleanliness.
     */
    public Cleanliness getCleanliness() {
        return this.cleanliness;
    }

    /**
     * Getter for the discipline.
     * @return the discipline.
     */
    public Discipline getDiscipline() {
        return this.discipline;
    }

    /**
     * Getter for the energy.
     * @return the energy.
     */
    public Energy getEnergy() {
        return this.energy;
    }

    /**
     * Getter for the happiness.
     * @return the happiness.
     */
    public Happiness getHappiness() {
        return this.happiness;
    }

     /**
     * Getter for the health.
     * @return the health.
     */
    public Health getHealth() {
        return this.health;
    }

    /**
     * Getter for the hunger.
     * @return the hunger.
     */
    public Hunger getHunger() {
        return this.hunger;
    }

     /**
     * Method to check if one of the needs has 
     * reached the death level.
     * @return true if one the needs has reached the death level.
     */
    public final boolean somethingHasReachedTheDeathLevel() {
        return (this.cleanliness.hasReachedTheDeathLevel() 
            || this.discipline.hasReachedTheDeathLevel()
            || this.energy.hasReachedTheDeathLevel()
            || this.happiness.hasReachedTheDeathLevel()
            || this.health.hasReachedTheDeathLevel()
            || this.hunger.hasReachedTheDeathLevel());
    }
}
