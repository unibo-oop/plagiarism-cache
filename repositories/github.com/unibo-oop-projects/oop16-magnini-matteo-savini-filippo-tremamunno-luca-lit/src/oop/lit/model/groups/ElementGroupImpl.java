package oop.lit.model.groups;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import oop.lit.model.GameElementModel;
import oop.lit.model.elements.GameElement;
import oop.lit.util.ObservableImpl;

/**
 * A partial implementation of an ElementGroup.
 * Complete implementantions need only to implement the "wrap" method.
 * @param <H>
 *         the type of gameElements held
 */
public class ElementGroupImpl<H extends GameElement> extends ObservableImpl implements ElementGroup<H> {
    /**
     * 
     */
    private static final long serialVersionUID = 3956854921222330770L;

    private final List<H> elements;
    private boolean visualizable = true;
    private final String name;

    /**
     * Creates an empty ElementGroup.
     *
     * @param name
     *      this group name.
     */
    public ElementGroupImpl(final Optional<String> name) {
        this(Collections.emptyList(), name);
    }

    /**
     * Creates an ElementGroup containing the specified elements.
     *
     * @param initialElements
     *      the elements this group will contain initially.
     * @param name
     *      this group name.
     */
    public ElementGroupImpl(final List<? extends H> initialElements, final Optional<String> name) {
        this.elements = new ArrayList<>(initialElements);
        this.name = name.orElse(null);
    }

    /**
     * @return the list used to store this group elements.
     * Any changes on the returned list will be reflected on the group and vice-versa
     */
    protected List<H> getElementList() {
        return this.elements;
    }

    @Override
    public List<H> getElements() {
        return new ArrayList<>(this.elements);
    }

    @Override
    public boolean addElement(final H element) {
        if (this.elements.contains(element)) {
            return false;
        } else {
            this.elements.add(element);
            this.notifyObservers();
            return true;
        }
    }

    @Override
    public final boolean removeElement(final GameElementModel element) {
        if (this.removeNoNotify(element)) {
            this.notifyObservers();
            return true;
        }
        return false;
    }

    @Override
    public final void clear() {
        if (!this.elements.isEmpty()) {
            this.getElements().forEach(this::removeNoNotify);
            this.notifyObservers();
        }
    }

    /**
     * Removes an element from the group without notifying observers.
     *
     * @param element
     *      the element to be removed.
     *
     * @return
     *      true if the element was removed (it was contained in this).
     */
    protected boolean removeNoNotify(final GameElementModel element) {
        return this.elements.remove(element);
    }

    @Override
    public boolean isVisualizable() {
        return this.visualizable;
    }

    @Override
    public void removed() {
        this.visualizable = false;
        this.elements.stream().forEach(GameElement::removed);
        this.elements.clear();
        this.notifyObservers();
    }

    @Override
    public Optional<String> getGroupName() {
        return Optional.ofNullable(this.name);
    }
}
