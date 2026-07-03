package oop.lit.controller;

import oop.lit.controller.base.SelectionAndAction;
import oop.lit.controller.base.SelectionAndActionImpl;
import oop.lit.controller.geometry.Transforms;
import oop.lit.controller.geometry.TransformsImpl;
import oop.lit.model.GameModel;
import oop.lit.view.ViewRequests;

/**
 * Controller receives user commands from View and it changes Model.
 */
public class ControllerImpl implements Controller {

    private final SelectionAndAction selectionAndAction;
    private final Transforms transforms;

    /**
     * Constructor.
     * @param gm
     *            needed to initialize the relative field.
     * @param view
     *            a view.
     */
    public ControllerImpl(final GameModel gm, final ViewRequests view) {
        this.selectionAndAction = new SelectionAndActionImpl(gm, view, (gem, segm, keepOldSelection) -> {
            if (!segm.getSelected().contains(gem)) {
                if (keepOldSelection) {
                    segm.select(gem);
                } else {
                    segm.clearSelection();
                    segm.select(gem);
                }
            } else {
                if (keepOldSelection) {
                    segm.deselect(gem);
                }
            }
        }, (gem, segm, keepOldSelection) -> {
            segm.clearSelection();
            segm.select(gem);
        });
        this.transforms = new TransformsImpl(gm);
    }

    @Override
    public SelectionAndAction getSelectionAndAction() {
        return this.selectionAndAction;
    }

    @Override
    public Transforms getTransforms() {
        return this.transforms;
    }
}