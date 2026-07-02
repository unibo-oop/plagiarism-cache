package controller;

/**
 * It updates all the attached controllers and the main view.
 */
public interface MainControllerObserver extends Updater {
    /**
     * adds a controller on the controller list to be updated on the update call.
     * 
     * @param controller the controller to add
     */
    void addController(SecondaryController controller);
}
