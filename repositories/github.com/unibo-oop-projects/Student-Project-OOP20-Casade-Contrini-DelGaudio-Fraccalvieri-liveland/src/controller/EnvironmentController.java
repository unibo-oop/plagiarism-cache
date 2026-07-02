package controller;

import view.menu.EmptyEnvironmentException;
import view.menu.VisitorsOutOfBoundException;
import view.model.activity.ActivityAlreadyPresentException;
import view.model.activity.ViewActivityImpl;

public interface EnvironmentController {
    /**
     * Interface showing EnvironmentControllerImpl's behaviour: first, it sets the environment
     * of the application, then it is responsible for changes in the simulation state,
     * due to some action performed by user.
     */

    /**
     * Starts the simulation.
     * @throws EmptyEnvironmentException if button Start is pressed without 
     * adding any activity. 
     */
    void start() throws EmptyEnvironmentException;

    /**
     * Stops the simulation.
     */
    void stop();

    /**
     * Adds a new activity to the environment, based on user's choices.
     * @param activity which stores user's choices
     * @throws ActivityAlreadyPresentException if an activity with same parameters
     * has already been added.
     */
    void addNewActivity(ViewActivityImpl activity) throws ActivityAlreadyPresentException;

    /**
     * Sets the visitors number as selected by the user in the gui menu.
     * @param visitorsNum visitors number
     * @throws VisitorsOutOfBoundException if the number chosen by the user is 
     * less than 1 or greater than 100
     */
    void setVisitorsNumber(int visitorsNum) throws VisitorsOutOfBoundException;

    /**
     * @return the visitors number.
     */
    int getVisitorsNumber();

    /**
     * Shows the analysis inferred from simulation.
     */
    void showAnalysis();

    /**
     * Resets activity list previously set.
     */
    void resetActivityLists();


}
