package oop.lit.model;

import java.util.Set;

import oop.lit.util.Observable;

/**
 * Holds groups that need to be shown.
 * Notifies observers when group to be shown changes.
 */
public interface GroupViewerModel extends Observable {
    /**
     * @return
     *      all groups to be shown as normal ElementGroups.
     */
    Set<ElementGroupModel> getNonSelectableGroups();
    /**
     * @return
     *      all groups to be shown as SelectableElementGroups.
     */
    Set<SelectableElementGroupModel> getSelectableGroups();
    /**
     * Stops a group from being shown.
     * @param group
     *      the group to not be shown anymore.
     *
     * @return
     *      if the provided group was being shown (and it is not shown anymore).
     */
    boolean stopShowing(ElementGroupModel group);
}
