package oop.lit.model.simplegame.elements.actions;

import java.io.Serializable;
import java.util.List;

import oop.lit.model.Action;
import oop.lit.model.util.permission.ActionsManager;

/**
 * An action factory providing basic actions for elements using a permission system.
 */
public interface SBEActionFactory extends Serializable {

    /**
     * Get a list containing all PermissionEditActions for all non-gm players,
     * only if the playing player is a gm.
     * If the playing player is not a gm (or is not present), 
     * or there are no non-gm players returns an empty list.
     * @param manager
     *      an ActionsManager.
     * @return
     *      the list.
     */
    List<Action> getPermissionEditActions(ActionsManager manager);

}