package oop.lit.controller.base;

import java.util.List;
import java.util.Optional;

import oop.lit.model.Action;
import oop.lit.model.GameElementModel;
import oop.lit.model.SelectableElementGroupModel;

/**
 * Interface for selection and actions getter.
 */
public interface SelectionAndAction {

    /**
     * Clear the actual selection.
     * 
     * @param segm
     *            the SelectableElementGroupModel of the element.
     */
    void clearSelection(SelectableElementGroupModel segm);

    /**
     * Clear all selections.
     */
    void forceClearSelection();

    /**
     * Edit the actual selection.
     * 
     * @param gem
     *            the GameElementModel to be added or removed.
     * @param segm
     *            the SelectableElementGroupModel of the element.
     * @param keepOldSelection
     *            a status for multi-selection.
     */
    void editSelection(GameElementModel gem, SelectableElementGroupModel segm, boolean keepOldSelection);

    /**
     * @param gem
     *            the GameElementModel.
     * @param segm
     *            the possible SelectableElementGroupModel of the element.
     * @return the actions list related to the element group.
     */
    List<Action> editSelectionAndGetAction(Optional<GameElementModel> gem, SelectableElementGroupModel segm);

    /**
     * If possible perform the main action.
     * 
     * @param gem
     *            the GameElementModel.
     */
    void performMainAction(GameElementModel gem);

    /**
     * If possible perform the given action.
     * 
     * @param action
     *            to be performed.
     */
    void performAction(Action action);
}
