package oop.lit.model.groups;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import oop.lit.model.Action;
import oop.lit.model.actions.AbstractAction;
import oop.lit.model.elements.GameElement;
import oop.lit.util.IllegalInputException;

/**
 * A selectable group, where contained elements order can be easilly specified. 
 * Provides two actions for changing the contained elements order, and methods to add/remove elements to/from specific position.
 *
 * @param <H>
 *      the type of elements held.
 */
public abstract class OrderedSelectableGroup<H extends GameElement> extends AbstractSelectableElementGroup<H> {
    /**
     * 
     */
    private static final long serialVersionUID = 6135889637917306794L;
    private transient Action moveToHead;
    private transient Action moveToTail;

    /**
     * @param initialElements
     *      the initial elements contained by this group.
     * @param name
     *      this group name.
     */
    protected OrderedSelectableGroup(final List<? extends H> initialElements, final Optional<String> name) {
        super(initialElements, name);
    }

    /**
     * @param name
     *      this group name.
     */
    public OrderedSelectableGroup(final Optional<String> name) {
        super(name);
    }

    /**
     * Randomize contained elements position.
     * Notifies observers.
     */
    public void shuffle() {
        Collections.shuffle(this.getElementList()); //non serve settare il random.
        this.notifyObservers();
    }

    /**
     * Removes the element in the specified position, without calling its removed method, and returns it.
     * Notifies observers.
     * @param position
     *      the position of the element you want.
     * @return
     *      the element.
     *
     * @throws IllegalArgumentException
     *      if there is no element at the specified position.
     */
    public H drawElement(final int position) {
        try {
            final H res = this.getElementList().get(position);
            this.removeElement(res);
            return res;
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("No element matches the specified position");
        }
    }

    /**
     * Adds the provided element to this group, at the specified position, if it was not already contained in this.
     * Moves the element currently at that position and any subsequent elements to the tail.
     * Notifies observers.
     * @param element
     *      the element to be added.
     * @param position
     *      the position the provided element is to be added in.
     * @return
     *      if the element was added.
     * @throws IllegalArgumentException
     *      if the element can't be added in the specified position.
     */
    public boolean addInPosition(final H element, final int position) {
        if (this.getElements().contains(element)) {
            return false;
        }
        try {
            this.getElementList().add(position, element);
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Element can't be added in the provided position");
        }
        this.notifyObservers();
        return true;
    }

    /**
     * @return
     *      an action used to move selected elements to the head (lower indexes).
     */
    protected Action getMoveToHeadAction() {
        if (this.moveToHead == null) {
            this.moveToHead = new AbstractAction("Move to head") {
                @Override
                public boolean canBePerformed() {
                    return !getSelected().isEmpty() && !getSelected().contains(getElementList().get(0));
                }
                @Override
                public void perform() throws IllegalInputException {
                    this.checkPerformable();
                    final List<Integer> selectedIndex = getSortedSelectedIndex();
                    for (int i = 0; i < selectedIndex.size(); i++) {
                        final int index = selectedIndex.get(i);
                        Collections.swap(getElementList(), index, index - 1);
                    }
                    notifyObservers();
                }
            };
        }
        return this.moveToHead;
    }

    /**
     * @return
     *      an action used to move selected elements to the tail (higher indexes).
     */
    protected Action getMoveToTailAction() {
        if (this.moveToTail == null) {
            this.moveToTail = new AbstractAction("Move to head") {
                @Override
                public boolean canBePerformed() {
                    return !getSelected().isEmpty() && !getSelected().contains(getElementList().get(getElementList().size() - 1));
                }
                @Override
                public void perform() throws IllegalInputException {
                    this.checkPerformable();
                    final List<Integer> selectedIndex = getSortedSelectedIndex();
                    for (int i = selectedIndex.size() - 1; i >= 0; i--) {
                        final int index = selectedIndex.get(i);
                        Collections.swap(getElementList(), index, index + 1);
                    }
                    notifyObservers();
                }
            };
        }
        return this.moveToTail;
    }

    //get a sorted list of indexes of the selected elements.
    private List<Integer> getSortedSelectedIndex() {
        final List<Integer> selectedIndex = getSelected().stream().map(getElementList()::indexOf)
                .collect(Collectors.toList());
        selectedIndex.sort(Integer::compare);
        return selectedIndex;
    }
}
