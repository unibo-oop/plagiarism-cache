package zombieversity.controller.entities;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import zombieversity.model.entities.Player;
import zombieversity.view.entities.PlayerView;

/**
 * 
 * Used to create a controller for player.
 *
 */
public interface PlayerController {

    /**
     * Use to update the player based on keyboard and mouse input.
     * 
     * @param offset
     * 
     */
    void updateInput(Point2D offset);

    /**
     * 
     * @return player model.
     */
    Player getEntity();

    /**
     * 
     * @return player view.
     */
    PlayerView getEntityView();

    /**
     * Used to update view and model.
     */
    void update();

    /**
     * 
     * @return player image.
     */
    Image getView();
}
