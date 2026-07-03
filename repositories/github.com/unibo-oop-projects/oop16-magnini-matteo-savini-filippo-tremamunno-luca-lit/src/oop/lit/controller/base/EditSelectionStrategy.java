package oop.lit.controller.base;

import oop.lit.model.GameElementModel;
import oop.lit.model.SelectableElementGroupModel;

/**
 * A functional interface.
 */
@FunctionalInterface
public interface EditSelectionStrategy {

    /**
     * Defines a specific strategy.
     * 
     * @param gem
     *            a GameElementView.
     * @param segm
     *            a SelectableElementGroupModel.
     * @param keepOldSelection
     *            a boolean.
     */
    void editSelection(GameElementModel gem, SelectableElementGroupModel segm, boolean keepOldSelection);
}
