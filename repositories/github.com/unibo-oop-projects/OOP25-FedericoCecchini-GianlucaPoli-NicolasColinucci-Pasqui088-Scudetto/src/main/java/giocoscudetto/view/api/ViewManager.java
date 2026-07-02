package giocoscudetto.view.api;

import javax.swing.JPanel;

/**
 * This interface represents the view manager of the game,
 *  it has methods to add views, show views,
 *  get the container of the views and quit the game.
 */
public interface ViewManager {

    /**
     * This method adds a view to the view manager.
     * 
     * @param panel the panel of the view to add.
     * @param name the name of the view to add.
     */
    void addView(JPanel panel, String name);

    /**
     * This method shows the view with the given name.
     * 
     * @param name the name of the view to show.
     */
    void showView(String name);

    /**
     * This method returns the container of the views.
     * 
     * @return the container of the views.
     */
    JPanel getContainer();

    /**
     * method for quit the game.
     */
    void quit();

}
