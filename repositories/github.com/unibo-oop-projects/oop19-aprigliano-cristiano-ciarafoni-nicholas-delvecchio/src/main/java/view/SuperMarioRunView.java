package view;

import java.awt.Dimension;
import javafx.scene.Node;

/**
 * This interface models the principal scene of SuperMarioRun game.
 */
public interface SuperMarioRunView {

    /**
     * This function set the screen size and the background of SuperMarioRun.
     */
    void start();

    /**
     *  This function is used to set a View Observer.
     * @param observer object of the Class SuperMarioRunGameViewObserver
     */
    void setObserver(SuperMarioRunGameViewObserver observer);

    /**
     * This function is used to add a Node to the stage.
     * @param n the Node
     */
    void addChildren(Node n);

    /**
     *  This function Returns the Screen width.
     *  @return ScreenSize height.
     */
    double getHeight();

    /**
     * This function Returns the Screen width.
     * @return ScreenSize width.
     */
    double getWidth();

    /**
     * This function is used to set the background of the principal scene.
     * @param screensize the Dimension of the Screen
     */
    void setBackgroundGame(Dimension screensize);

    /**
     * This function stop the game and call the endgame Controller on view, and passes the score of the player.
     * @param score , The Score reached by the player
     */
    void endGame(int score);

    /**
     * Set score in game view.
     * @param n New Score
     */
    void setScore(int n);
}
