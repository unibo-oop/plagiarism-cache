package model.player;

import java.awt.Point;

import javafx.scene.image.ImageView;
import utilities.Directions;

/**
 * PlayerImpl interface.
 *
 */
public interface Player {

    /**
     * Moves the player to the given direction.
     * 
     * @param direction
     *            which player has to move to
     */
    void move(Directions direction);

    /**
     * Getter of the current player's position.
     * 
     * @return a point which contains player's coordinates
     */
    Point getPlayerPosition();

    /**
     * Setter of position.
     * 
     * @param x
     *            coordinate of player's position
     * @param y
     *            coordinate of player's position
     */
    void setPlayerPosition(int x, int y);

    /**
     * Setter of charFrame.
     * 
     * @param charFrame
     *            player's animation's image
     */
    void setCharFrame(ImageView charFrame);
}
