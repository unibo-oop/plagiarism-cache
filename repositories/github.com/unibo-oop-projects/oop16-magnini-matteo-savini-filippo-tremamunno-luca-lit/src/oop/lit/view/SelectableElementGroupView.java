package oop.lit.view;

import java.util.List;

import oop.lit.model.GameElementModel;
import oop.lit.model.SelectableElementGroupModel;
import oop.lit.view.controller.selectionandaction.AbstractSelectionAndActions;

/**
 * This class differs from ElementGroupView mostly because the objects of type
 * GameElementView which are going to be added must possess the EventHandler
 * that makes selection possible.
 */
public class SelectableElementGroupView extends ElementGroupView {
    private final SelectableElementGroupModel segm;
    private final AbstractSelectionAndActions selectionController;

    /**
     * The class constructor has to supply the parameter of type
     * SelectableElementGroupModel (which extends ElementGroupModel, so there
     * are no compatibility problems) to its counterpart in the superclass and
     * initialize the field of type Selection.
     * 
     * @param group
     *            the ElementGroupModel to be supplied to the constructor of the
     *            superclass
     * @param selection
     *            the instance of Selection that gives the class access to the
     *            method that allows us to attach the relative EventHandler
     */
    public SelectableElementGroupView(final SelectableElementGroupModel group, 
                                      final AbstractSelectionAndActions selection) {
        super(group);
        this.segm = group;
        this.selectionController = selection;
    }

    @Override
    public void notifyChange() {
        super.notifyChange();

        for (final GameElementView gev : super.getGevSet()) {
            gev.setSelectionItems(false);
        }
        final List<GameElementView> toSelect = this.retrieveGameElementView(this.segm.getSelected());
        for (final GameElementView gev : toSelect) {
            gev.setSelectionItems(true);
        }
    }

    // The method in ElementGroupView must be overridden so that it doesn't just
    // return a new, bare-bones
    // GameElementView, but rather one with the required eventHandlers attached.
    @Override
    public GameElementView getGameElementView(final GameElementModel element) {
        final GameElementView selectableGEV = super.getGameElementView(element);
        selectionController.editSelectionAndActionOnSource(selectableGEV, this.segm);
        return selectableGEV;
    }
}