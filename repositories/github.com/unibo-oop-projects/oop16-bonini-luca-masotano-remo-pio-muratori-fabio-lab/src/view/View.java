package view;

import java.util.List;
import java.util.Map;

import controller.ModelWrapper;
import util.Pair;
import util.ImageLoaderProxy.ImageID;

/**
 * Manage the game view providing methods used by the controller.
 */
public interface View {
    /**
     * Initialize the view.
     * 
     * @param savedGame
     *            true if there is a saved game to load, false otherwise
     * @param width
     *            the width of a generic moodel room
     * @param height
     *            the height of a generic moodel room
     */
    void init(boolean savedGame, double width, double height);

    /**
     * Set the list of objects modified each frame.
     *
     * @param collection
     *            the List of model objects adapted to be displayed
     */
    void setDynamicCollection(List<ModelWrapper> collection);

    /**
     * Set the list of objects modified during specific times of the game.
     *
     * @param collection
     *            the List of model objects adapted to be displayed
     */
    void setStaticCollection(List<ModelWrapper> collection);

    /**
     * Provides to the view the numeric value of player life; required to display
     * correctly player life in game.
     * 
     * @param life
     *            the player life
     */
    void setPlayerLife(double life);

    /**
     * Provides to the view a representation of the model map.
     * 
     * @param world
     *            a map containing coordinates of all the model rooms; each map
     *            entry key is a coordinate of a room, the value is the
     *            corresponding image to display.
     */
    void setMap(Map<Pair<Integer, Integer>, ImageID> world);

    /**
     * Method called as soon as a game ends; notify the game ended event to the
     * view.
     */
    void gameOver();

    /**
     * Method called as soon as a room change is detected.
     */
    void roomChanging();

    /**
     * Method used to notify the presence of a previously started game, necessary to
     * currectly set up view contents.
     *
     * @param value
     *            true if there is a saved game, false otherwise.
     */
    void foundSavedGame(boolean value);

    /**
     * Return the FPS selected in the view options scene to set the
     * {@link controller.GameLoop} loop speed. If there's no setted fps value, this
     * method return a default value of 60 fps.
     * 
     * @return the value at which the game will be rendered
     */
    int getFPSSelected();

    /**
     * Provides the game modality selected in the options scene.
     * 
     * @return true if "God Mode" is checked, false otherwise;
     */
    boolean isGodModeSelected();
}
