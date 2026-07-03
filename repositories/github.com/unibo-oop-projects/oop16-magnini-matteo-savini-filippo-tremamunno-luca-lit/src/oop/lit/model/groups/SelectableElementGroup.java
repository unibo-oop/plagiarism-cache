package oop.lit.model.groups;

import java.util.List;

import oop.lit.model.Action;
import oop.lit.model.GameElementModel;
import oop.lit.model.PlayerModel;
import oop.lit.model.elements.GameElement;

/**
 * A group of GameElements in which elements may be selected, and actions can be performed on selected elements.
 * @param <H>
 *      the type of gameElements held
 */
public interface SelectableElementGroup<H extends GameElement> extends ElementGroup<H> {
    /**
     * Adds an element to the selection.
     * Should notify observers.
     *
     * @param element
     *      the element to be selected.
     *
     * @return
     *      if the element was selected (it was in this group and was not selected)
     */
    boolean select(GameElementModel element);
    /**
     * Removes an element from the selection.
     * Should notify observers.
     *
     * @param element
     *      the element to be deselected.
     *
     * @return
     *      if the element was deselected (it was in this group and was selected)
     */
    boolean deselect(GameElementModel element);
    /**
     * Removes all elements from selection.
     * Should notify observers.
     */
    void clearSelection();

    /**
     * @return all selected board elements
     */
    List<H> getSelected();

    /**
     * @param playingPlayer
     *      the player asking the actions
     * @param turnPlayers
     *      the players playing the current turn
     *
     * @return a list of action that can be performed by the selected elements
     */
    List<Action> getSelectedActions(PlayerModel playingPlayer, List<? extends PlayerModel> turnPlayers);
}
