package oop.lit.model.elements;

import java.io.Serializable;
import java.util.List;

import oop.lit.model.Action;
import oop.lit.model.GameElementModel;
import oop.lit.model.PlayerModel;

/**
 * A generic element of the game, which can perform some actions.
 */
public interface GameElement extends GameElementModel, Serializable {
    /**
     * @param playingPlayer
     *      the player asking the actions
     * @param turnPlayers
     *      the players playing the current turn
     *
     * @return the list of actions that can be performed by this element, by the given player, in this moment.
     */
    List<Action> getActions(PlayerModel playingPlayer, List<? extends PlayerModel> turnPlayers);

    /**
     * A method to be called when the element needs to be removed.
     * When this method is called the element should not be used anymore.
     */
    void removed();
}
