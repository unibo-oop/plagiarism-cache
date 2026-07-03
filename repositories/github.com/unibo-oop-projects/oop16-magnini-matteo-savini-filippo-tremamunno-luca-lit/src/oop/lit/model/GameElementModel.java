package oop.lit.model;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Optional;

import oop.lit.util.Observable;

/**
 * A generic element of the game, which can perform some actions.
 * Observers will be notified when this element image changes.
 * This interface is used to hide unnecessary methods to view and controller.
 */
public interface GameElementModel extends Observable {
    /**
     * @return the name of this element.
     */
    Optional<String> getName();

    /**
     * Returns (if present) the main action that can be performed by this element by the given player in this moment (this way it can be executed by other means).
     * This action can also be present in the actions returned by method getActions().
     * 
     * @param playingPlayer
     *      the player asking the actions
     * @param turnPlayers
     *      the players playing the current turn
     * 
     * @return
     *      an optional containing the main action or an empty optional if this element has no main action.
     */
    Optional<Action> getMainAction(PlayerModel playingPlayer, List<? extends PlayerModel> turnPlayers);

    /**
     * @return
     *      an optional containing a buffered image representing the object or an empty optional if this was not specified.
     */
    Optional<BufferedImage> getImage();
}
