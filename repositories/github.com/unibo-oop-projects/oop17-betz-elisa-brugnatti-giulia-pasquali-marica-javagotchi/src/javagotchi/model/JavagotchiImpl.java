package javagotchi.model;

import java.io.Serializable;
import javagotchi.model.information.Avatar;
import javagotchi.model.information.Gender;
import javagotchi.model.information.Information;
import javagotchi.model.information.InformationImpl;
import javagotchi.model.needs.Needs;
import javagotchi.model.needs.NeedsImpl;
/**
 * Class that implements the Javagotchi interface. It manages the liveness,
 * information and needs of a pet.
 * 
 * @author elisa,giulia
 *
 */
public class JavagotchiImpl implements Javagotchi, Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -3415353151607677561L;
    private final Information information;
    private final Needs needs;
    private boolean alive;

    /**
     * Javagotchi constructor.
     * @param name its name
     * @param gender its gender
     * @param avatar its avatar
     */
    public JavagotchiImpl(final String name, final Gender gender, final Avatar avatar) {
        this.information = new InformationImpl(name, gender, avatar);
        this.needs = new NeedsImpl();
        this.alive = true;
    }

    /**
     * Getter for the information.
     * @return javagotchi's information.
     */
    public Information getInformation() {
        return this.information;
    }

    /**
     * Getter for the needs.
     * @return javagotchi's needs.
     */
    public Needs getNeeds() {
        return this.needs;
    }

    /**
     * Method to feed the javagotchi. It increases the Hunger and the Happiness. 
     * 
     */
    public void feed() {
        this.needs.getHunger().raise();
        this.needs.getHappiness().raise();
    }

    /**
     * Method to cure the javagotchi. It increases the Health 
     * but makes the Happiness drop.
     */
    public void cure() {
        this.needs.getHealth().raise();
        this.needs.getHappiness().reduce();
    }

    /**
     * Method to clean the javagotchi. It increases the Cleanliness and the Happiness.
     */
    public void clean() {
        this.needs.getCleanliness().raise();
        this.needs.getHappiness().raise();
    }

    /**
     * Method to make the javagotchi sleep. It increases the Energy 
     * but makes the Hunger drop
     */
    public void sleep() {
        this.needs.getEnergy().raise();
        this.needs.getHunger().reduce();
    }

    /**
     * Method to scold the javagotchi. It increases the Discipline 
     * but makes the Happiness drop.
     */
    public void scold() {
        this.needs.getDiscipline().raise();
        this.needs.getHappiness().reduce();
    }

    /**
     * Method to play with the javagotchi. It increases the Happiness 
     * but makes the Energy, the Cleanliness, the Discipline and the Hunger drop.
     */
    public void play() {
        this.needs.getEnergy().reduce();
        this.needs.getCleanliness().reduce();
        this.needs.getDiscipline().reduce();
        this.needs.getHunger().reduce();
        this.needs.getHappiness().raise();
    }

    private void checkNeedsLevels() {
        if (this.needs.somethingHasReachedTheDeathLevel()) {
            this.alive = false;
        } else {
            this.alive = true;
        }
    }

    /**
     * Method to check if the javagotchi is alive.
     * @return the value of the field 'alive'
     */
    public boolean isAlive() {
        this.checkNeedsLevels();
        return this.alive;
    }
}
