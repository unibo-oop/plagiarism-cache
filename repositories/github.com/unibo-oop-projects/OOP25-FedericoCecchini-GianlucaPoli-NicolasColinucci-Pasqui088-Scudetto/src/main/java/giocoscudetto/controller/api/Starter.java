package giocoscudetto.controller.api;

/**
 * Interface to set the starter view and change it depending by the user's action.
 */
public interface Starter {

    /**
     * this method is for starting the game and show the home view.
     */
    void startGame();

    /**
     * this is a method for change the panel in the frame.
     * 
     * @param namePanel the name of the panel.
     */
    void changeView(String namePanel);

    /**
     * this is a method to close the game.
     */
    void closeGame();

    //void addObserver(GameObserver ob);

    //void removeObserver(GameObserver ob);

    //void notifyViews();

}
