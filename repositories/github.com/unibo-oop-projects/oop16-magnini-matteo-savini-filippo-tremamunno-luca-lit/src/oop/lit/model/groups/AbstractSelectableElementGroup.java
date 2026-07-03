package oop.lit.model.groups;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import oop.lit.model.Action;
import oop.lit.model.GameElementModel;
import oop.lit.model.PlayerModel;
import oop.lit.model.elements.GameElement;

/**
 * A partial implementation of a SelectableElementGroup.
 * Full implementation need only to implement the getSelectedActions method.
 *
 * @param <H>
 *         the type of gameElements held
 */
public abstract class AbstractSelectableElementGroup<H extends GameElement>
        extends ElementGroupImpl<H> implements SelectableElementGroup<H> {
    /**
     * 
     */
    private static final long serialVersionUID = -1781801851476421954L;

    private final List<H> selectedElements = new ArrayList<>();
    /**
     * Creates an empty ElementGroup.
     *
     * @param name
     *      this group name.
     */
    protected AbstractSelectableElementGroup(final Optional<String> name) {
        super(name);
    }

    /**
     * Creates an ElementGroup containing the specified elements.
     *
     * @param initialElements
     *      the elements this group will contain initially.
     * @param name
     *      this group name.
     */
    protected AbstractSelectableElementGroup(final List<? extends H> initialElements, final Optional<String> name) {
        super(initialElements, name);
    }

    @Override
    protected boolean removeNoNotify(final GameElementModel element) {
        if (super.removeNoNotify(element)) {
            this.selectedElements.remove(element);
            return true;
        }
        return false;
    }

    @Override
    public boolean select(final GameElementModel element) {
        if (this.selectedElements.contains(element)) {
            return false;
        }
        final List<H> elements = this.getElements();
        final int index = elements.indexOf(element);
        if (index == -1) {
            return false;
        }
        selectedElements.add(elements.get(index));
        this.notifyObservers();
        return true;
    }

    @Override
    public boolean deselect(final GameElementModel element) {
        final boolean res = this.selectedElements.remove(element);
        if (res) {
            this.notifyObservers();
        }
        return res;
    }

    @Override
    public void clearSelection() {
        if (!this.selectedElements.isEmpty()) {
            this.selectedElements.clear();
            this.notifyObservers();
        }
    }

    @Override
    public List<H> getSelected() {
        return new ArrayList<>(this.selectedElements);
    }

    @Override
    public void removed() {
        super.removed();
        this.selectedElements.clear();
        this.notifyObservers();
    }

    /**
     * If there is only one element selected returns the actions from that element getActions method, else this will return an empty list. 
     * @param playingPlayer
     *      the player asking the actions
     * @param turnPlayers
     *      the players playing the current turn
     * @return
     *      a list of actions.
     */
    protected List<Action> getSingleSelectionActions(final PlayerModel playingPlayer, final List<? extends PlayerModel> turnPlayers) {
        if (this.selectedElements.size() != 1) {
            return Collections.emptyList();
        }
        return this.selectedElements.get(0).getActions(playingPlayer, turnPlayers);
    }
}
