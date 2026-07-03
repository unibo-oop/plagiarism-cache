package oop.lit.model.simplegame.actions;

import java.io.Serializable;
import java.util.List;

import oop.lit.model.Action;
import oop.lit.model.PlayerModel;
import oop.lit.model.groups.SelectableElementGroup;
import oop.lit.model.simplegame.elements.SimpleElement;

/**
 * An action factory for groups of simple board elements.
 */
public interface GroupActionFactory extends Serializable {

    /**
     * Get a list of actions based on the provided group selected elements list:
     * -If it is empty returns an empty list.
     * -If it contains exactly one element returns the actions that can be performed by the provided player on that element (or more).
     * -If it contains more elements the returned actions list depends on the elements getPossibleGroupActionsTypes method.
     * 
     * @param group
     *            a group.
     * @param playingPlayer
     *            the player asking the actions
     * @param turnPlayers
     *            the players playing the current turn
     * @return the actions list.
     */
    List<Action> getGroupActions(SelectableElementGroup<? extends SimpleElement> group, PlayerModel playingPlayer,
            List<? extends PlayerModel> turnPlayers);

}