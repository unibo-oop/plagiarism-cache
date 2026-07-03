package oop.lit.model.simplegame.elements;

import java.util.List;
import java.util.Set;

import oop.lit.model.PlayerModel;
import oop.lit.model.elements.GameElement;

/**
 * A GameElement for a simpleGame.
 */
public interface SimpleElement extends GameElement {
    /**
     * An enum defining the action types that can be performed by many SimpleElements at once.
     */
    enum GroupActionTypes { //non contiene azioni che non voglio/posso eseguire in gruppo tipo quelle di modifica permessi, o drawFromVari.
        SEND_TO_HAND,
        SEND_TO_BOARD,
        SEND_TO_GROUP_TOP,
        SEND_TO_GROUP_BOTTOM,
        SEND_TO_GROUP_RANDOM,
        SEND_TO_GROUP_SPECIFIC,
        SHUFFLE,
        FLIP,
        REMOVE
    }
    /**
     * @param playingPlayer
     *      the player asking the actions
     * @param turnPlayers
     *      the players playing the current turn
     *
     * @return the list of group actions types that can be performed by this element, by the given player, in this moment.
     */
    Set<GroupActionTypes> getPossibleGroupActionsTypes(PlayerModel playingPlayer, List<? extends PlayerModel> turnPlayers);
}