package oop.lit.model.groups;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import oop.lit.model.GameElementModel;
import oop.lit.model.elements.GameElement;
import oop.lit.util.Observable;

/**
 * @param <H>
 *         the type of gameElements held
 */
public interface ElementGroup<H extends GameElement> extends Observable, Serializable {
    /**
     * @return this group name.
     */
    Optional<String> getGroupName();

    /**
     * @return all elements contained.
     */
    List<H> getElements();

    /**
     * Adds an element only if not already contained.
     * Notifies observers.
     *
     * @param element
     *      the element to be added.
     *
     * @return
     *      true if the element was added (it was not contained in this)
     */
    boolean addElement(H element);

    /**
     * Removes an element from the group. Does not call its remove method.
     * Notifies observers.
     *
     * @param element
     *      the element to be removed.
     *
     * @return
     *      true if the element was removed (it was contained in this).
     */
    boolean removeElement(GameElementModel element);

    /**
     * Removes all elements from this group. 
     */
    void clear();

    /**
     * @return
     *      if this group can be shown to the user. If this changes observers will be notified.
     */
    boolean isVisualizable();

    /**
     * A method to be called when the group needs to be removed.
     * When this method is called the group should not be used anymore.
     *
     * @throws UnsupportedOperationException
     *      if the group can't be removed.
     */
    void removed();
}