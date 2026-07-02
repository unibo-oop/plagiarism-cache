package javagotchi.controller.home;

import javafx.stage.Stage;
import javagotchi.model.Javagotchi;
import javagotchi.view.home.Home;

/**
 * Controller interface.
 * 
 * @author elisa
 *
 */
public interface HomeController {

    /**
     * Getter for the javagotchi.
     * @return the home controller javagotchi.
     */
    Javagotchi getJavagotchi();


    /**
     * Getter for the timers.
     * @return the home controller timers.
     */
    Timers getTimers();


    /**
     * Method to initialize and start timers.
     * @param homeview the home view that timers will update in the time
     */
    void startTimers(Home homeview);


    /**
     * Method to save the data on file.
     */
    void save();


    /**
     * Method to call when the action "play" is performed on the home view.
     */
    void playHandler();


    /**
     * Method to call when the action "go back" is performed on the home view.
     * It takes the Stage as an argument to close it.
     * @param stage  the stage to be closed
     */
    void backHandler(Stage stage);


    /**
     * Method to call when the action "show tutorial" is performed on the home view.
     */
    void tutorialHandler();


    /**
     * Method to check if the javagotchi is alive. 
     * @throws DeathException if the javagotchi is dead.
     */
    void checkLiveness() throws DeathException;


}
