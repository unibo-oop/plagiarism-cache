package fargoal.view.api;

/**
 * Interface that models the graphic part of the game.
 */
public interface View {
    /**
     * Method that is called to update 
     * every single graphic detail.
     */
    void update();

    /**
     * Method that sets up all the elements that need to be set up
     * not at construction.
     */
    void setUp();
}
