package javagotchi.model;

import javagotchi.model.information.Information;
import javagotchi.model.needs.Needs;

/**
 * @author giulia, elisa
 */
public interface Javagotchi {

    /**
     * Getter for the information.
     * @return javagotchi's information.
     */
    Information getInformation();

    /**
     * Getter for the needs.
     * @return javagotchi's needs.
     */
    Needs getNeeds();

    /**
     * Method to feed the javagotchi. It increases the Hunger and the Happiness. 
     * 
     */
    void feed();

    /**
     * Method to cure the javagotchi. It increases the Health but makes the
     * Happiness drop.
     */
    void cure();

    /**
     * Method to clean the javagotchi. It increases the Cleanliness and the Happiness.
     */
    void clean();

    /**
     * Method to make the javagotchi sleep. It increases the Energy but makes the
     * Hunger drop.
     */
    void sleep();

    /**
     * Method to scold the javagotchi. It increases the Discipline 
     * but makes the Happiness drop.
     */
    void scold();

    /**
     * Method to play with the javagotchi. It increases the Happiness but makes the
     * Energy, the Cleanliness, the Discipline and the Hunger drop.
     */
    void play();

    /**
     * Method to check if the javagotchi is alive.
     * @return the value of the field 'alive'
     */
    boolean isAlive();

}

