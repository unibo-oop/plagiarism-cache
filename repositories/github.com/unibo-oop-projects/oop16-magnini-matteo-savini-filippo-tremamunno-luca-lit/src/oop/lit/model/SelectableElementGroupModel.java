package oop.lit.model;

import java.util.Collections;
import java.util.List;

import oop.lit.model.groups.SelectableElementGroup;

/**
 * A wrapper class for a SelectableElementGroup, hiding unnecessary methods to view and controller.
 * Notifies observers when contained or selected elements change.
 */
public class SelectableElementGroupModel extends ElementGroupModel {
    private final SelectableElementGroup<? extends GameElementModel> group;

    /**
     * @param group
     *      the group to be wrapped.
     */
    public SelectableElementGroupModel(final SelectableElementGroup<? extends GameElementModel> group) {
        super(group);
        this.group = group;
    }

    /**
     * Adds an element to the selection.
     * Should notify observers.
     *
     * @param element
     *      the element to be selected.
     *
     * @return
     *      if the element was selected (it was in this group and was not selected)
     * @see oop.lit.model.groups.SelectableElementGroup#select(oop.lit.model.GameElementModel)
     */
    public boolean select(final GameElementModel element) {
        return group.select(element);
    }

    /**
     * Removes an element from the selection.
     * Should notify observers.
     *
     * @param element
     *      the element to be deselected.
     *
     * @return
     *      if the element was deselected (it was in this group and was selected)
     * @see oop.lit.model.groups.SelectableElementGroup#deselect(oop.lit.model.GameElementModel)
     */
    public boolean deselect(final GameElementModel element) {
        return group.deselect(element);
    }

    /**
     * Removes all elements from selection.
     * Should notify observers.
     * @see oop.lit.model.groups.SelectableElementGroup#clearSelection()
     */
    public void clearSelection() {
        group.clearSelection();
    }

    //Note sul perché list anziché set: in alcuni casi mi potrebbe interessare l'ordine in cui sono stati selezionati 
    /**
     * @return a unmodifiable list containing all selected board elements
     * @see oop.lit.model.groups.SelectableElementGroup#getSelected()
     */
    public List<GameElementModel> getSelected() {
        return Collections.unmodifiableList(group.getSelected());
    }

    /**
     * @param playingPlayer
     *      the player asking the actions
     * @param turnPlayers
     *      the players playing the current turn
     *
     * @return a list of action that can be performed by the selected elements
     * @see oop.lit.model.groups.SelectableElementGroup#getSelectedActions()
     */
    public List<Action> getSelectedActions(final PlayerModel playingPlayer, final List<PlayerModel> turnPlayers) {
        return group.getSelectedActions(playingPlayer, turnPlayers);
    }
}
