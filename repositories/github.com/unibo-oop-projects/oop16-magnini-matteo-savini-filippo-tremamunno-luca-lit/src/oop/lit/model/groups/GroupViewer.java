package oop.lit.model.groups;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import oop.lit.model.ElementGroupModel;
import oop.lit.model.GroupViewerModel;
import oop.lit.model.SelectableElementGroupModel;
import oop.lit.util.ObservableImpl;
import oop.lit.util.Observer;

/**
 * Implementation of a groupViewer.
 */
public class GroupViewer extends ObservableImpl implements GroupViewerModel, Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 888680232505863325L;
    private transient Set<ElementGroupModel> groups = new HashSet<>();
    private transient Set<SelectableElementGroupModel> selectableGroups = new HashSet<>();
    private transient Set<GroupObserver> observers = new HashSet<>(); 

    /**
     * Shows a group as a non selectable group (if it is not already showing).
     * If the group was being shown as a SelectableElementGroup it will be now shown as a non selectable element group.
     * @param group
     *      the group to be shown
     * @return
     *      if the group is added to the shown (nonSelectable)Groups.
     * @throws IllegalArgumentException
     *      if the provided group is not visualizable.
     */
    public boolean showGroup(final ElementGroup<?> group) {
        return this.showGroup(new ElementGroupModel(group));
    }
    /**
     * Shows a group as a non selectable group (if it is not already showing).
     * If the group was being shown as a SelectableElementGroup it will be now shown as a non selectable element group.
     * @param group
     *      the group to be shown
     * @return
     *      if the group is added to the shown (nonSelectable)Groups.
     * @throws IllegalArgumentException
     *      if the provided group is not visualizable.
     */
    public boolean showGroup(final ElementGroupModel group) {
        if (!group.isVisualizable()) {
            throw new IllegalArgumentException();
        }
        if (this.groups.contains(group)) {
            return false;
        }
        this.selectableGroups.remove(group);
        this.addGroupObserver(group);
        this.groups.add(group);
        this.notifyObservers();
        return true;
    }

    /**
     * Shows a group as a SelectableElementGroup (if it is not already showing).
     * If the group was being shown as a non selectable element group it will be now shown as a SelectableElementGroup.
     * @param group
     *      the group to be shown
     * @return
     *      if the group is added to the shown selectableGroups.
     * @throws IllegalArgumentException
     *      if the provided group is not visualizable.
     */
    public boolean showSelectable(final SelectableElementGroup<?> group) {
        return this.showSelectable(new SelectableElementGroupModel(group));
    }
    /**
     * Shows a group as a SelectableElementGroup (if it is not already showing).
     * If the group was being shown as a non selectable element group it will be now shown as a SelectableElementGroup.
     * @param group
     *      the group to be shown
     * @return
     *      if the group is added to the shown selectableGroups.
     * @throws IllegalArgumentException
     *      if the provided group is not visualizable.
     */
    public boolean showSelectable(final SelectableElementGroupModel group) {
        if (!group.isVisualizable()) {
            throw new IllegalArgumentException();
        }
        if (this.selectableGroups.contains(group)) {
            return false;
        }
        this.groups.remove(group);
        this.addGroupObserver(group);
        this.selectableGroups.add(group);
        this.notifyObservers();
        return true;
    }

    @Override
    public Set<ElementGroupModel> getNonSelectableGroups() {
        return new HashSet<>(this.groups);
    }

    @Override
    public Set<SelectableElementGroupModel> getSelectableGroups() {
        return new HashSet<>(this.selectableGroups);
    }

    @Override
    public boolean stopShowing(final ElementGroupModel group) {
        if (this.groups.remove(group) || this.selectableGroups.remove(group)) {
            this.notifyObservers();
            return true;
        }
        return false;
    }

    /**
     * Stops a group from being shown.
     * @param group
     *      the group to not be shown anymore
     *
     * @return
     *      if the provided group was being shown (and it is not shown anymore)
     */
    public boolean stopShowing(final ElementGroup<?> group) {
        return this.stopShowing(new ElementGroupModel(group));
    }

    /**
     * Stops all groups from being shown.
     */
    public void stopShowingAll() {
        groups.clear();
        selectableGroups.clear();
        this.notifyObservers();
    }
    //adds a group observer.
    private void addGroupObserver(final ElementGroupModel group) {
        this.observers.add(this.new GroupObserver(group));
    }
    //used by group observer to notify when a group is no longer visualizable
    private void notifyNotVisualizable(final ElementGroupModel group, final GroupObserver observer) {
        this.groups.remove(group);
        this.selectableGroups.remove(group);
        this.observers.remove(observer);
        this.notifyObservers();
    }

    //used to monitor groups, in order to stop showing them if they can't be shown anymore.
    private class GroupObserver implements Observer {
        private final ElementGroupModel group; 
        protected GroupObserver(final ElementGroupModel group) {
            this.group = group;
            group.attach(this);
        }
        @Override
        public void notifyChange() {
            if (!group.isVisualizable()) {
                GroupViewer.this.notifyNotVisualizable(this.group, this);
            }
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
            if (!(obj instanceof GroupObserver)) {
                return false;
            }
            final GroupObserver other = (GroupObserver) obj;
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

    private void readObject(final ObjectInputStream in) throws ClassNotFoundException, IOException {
        in.defaultReadObject();
        this.groups = new HashSet<>();
        this.selectableGroups = new HashSet<>();
        this.observers = new HashSet<>(); 
    }
}
