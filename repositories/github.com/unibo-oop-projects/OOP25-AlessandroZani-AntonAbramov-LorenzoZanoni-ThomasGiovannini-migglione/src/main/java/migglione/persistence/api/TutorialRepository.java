package migglione.persistence.api;

/**
 * Interface used to handle the tutorial on start-up.
 */
public interface TutorialRepository {
    /**
     * Checks if it's the first time the app has been opened.
     * 
     * @return true if it is, and is related to the tutorial
     *         because if the user opens the app for the first
     *         time then it means they have to choose if they
     *         want to see the tutorial
     */
    boolean haveTutorialBeenSeen();

    /**
     * Method to write on the tutorial.txt file.
     * 
     * <p>
     * This method is only invoked after the user
     * opens the app the first time, and will write
     * on the file so that from the second time onwards
     * the method haveTutorialBeenSeen will return false
     */
    void writeOnTutorial();
}
