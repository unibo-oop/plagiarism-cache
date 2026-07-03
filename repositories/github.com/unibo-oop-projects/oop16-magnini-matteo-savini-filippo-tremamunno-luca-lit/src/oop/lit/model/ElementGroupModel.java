package oop.lit.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import oop.lit.model.groups.ElementGroup;
import oop.lit.util.Observable;
import oop.lit.util.Observer;

/**
 * A wrapper class for ElementGroup, hiding unnecessary methods to view and controller.
 * Notifies observers when contained elements change.
 */
public class ElementGroupModel implements Observable {
    private final ElementGroup<? extends GameElementModel> group;
    /**
     * @param group
     *      the group to be wrapped
     */
    public ElementGroupModel(final ElementGroup<? extends GameElementModel> group) {
        this.group = group;
    }

    /**
     * Adds an observer.
     * Holds a weak reference of the observed object.
     *
     * @param o
     *      an object that will be notified when this changes.
     */
    @Override
    public void attach(final Observer o) {
        this.group.attach(o);
    }
    /**
     * Removes an observer.
     *
     * @param o
     *      an object that does not want to be notified anymore.
     */
    @Override
    public void detach(final Observer o) {
        this.group.detach(o);
    }

    /**
     * @return this group name.
     * @see oop.lit.model.groups.ElementGroup#getGroupName()
     */
    public Optional<String> getName() {
        return this.group.getGroupName();
    }

    /**
     * @return a list containing all the elements in this group
     * @see oop.lit.model.groups.ElementGroup#getElements()
     */
    public List<GameElementModel> getElements() {
        return new ArrayList<>(this.group.getElements());
    }

    /**
     * @return if this group can be shown to the user. If this changes observers will be notified.
     * @see oop.lit.model.groups.ElementGroup#isVisualizable()
     */
    public boolean isVisualizable() {
        return group.isVisualizable();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.group == null) ? 0 : this.group.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof ElementGroupModel)) {
            return false;
        }
        final ElementGroupModel other = (ElementGroupModel) obj;
        if (this.group == null) {
            if (other.group != null) {
                return false;
            }
        } else if (!this.group.equals(other.group)) {
            return false;
        }
        return true;
    }


}
