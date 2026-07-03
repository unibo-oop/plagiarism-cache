package oop.lit.view.controller.selectionandaction;

import java.util.List;
import java.util.Optional;

import oop.lit.controller.Controller;
import oop.lit.model.Action;
import oop.lit.model.SelectableElementGroupModel;
import oop.lit.view.GameElementView;
import oop.lit.view.controller.Status;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;

/**
 * Completed implementation of all abstract methods.
 */
public class SelectionAndActionsImpl extends DefaultClicks {

    private ContextMenu contextMenu = new ContextMenu();

    /**
     * @param controller
     *            Controller.
     * @param status
     *            Status.
     */
    public SelectionAndActionsImpl(final Controller controller, final Status status) {
        super(controller, status);
    }

    @Override
    protected void singleClickPerformed(final MouseEvent e, final GameElementView gev,
            final SelectableElementGroupModel segm) {
        this.contextMenu.hide();
        this.contextMenu = new ContextMenu();
        getController().getSelectionAndAction().editSelection(gev.getGameElementModel(), segm,
                getKeyboard().isKeepOldSelection());
    }

    @Override
    protected void doubleClickPerformed(final MouseEvent e, final GameElementView gev) {
        getController().getSelectionAndAction().performMainAction(gev.getGameElementModel());
    }

    @Override
    protected void rightClickPerformed(final MouseEvent e, final GameElementView gev,
            final SelectableElementGroupModel segm) {
        final List<Action> actions = getController().getSelectionAndAction().editSelectionAndGetAction(
                Optional.of(gev.getGameElementModel()), segm);
        this.contextMenu.hide();
        if (!actions.isEmpty()) {
            this.contextMenu = new ContextMenu();
            for (final Action action : actions) {
                final MenuItem item = new MenuItem(action.getLabel());
                item.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(final ActionEvent e1) {
                        if (action.canBePerformed()) {
                            getController().getSelectionAndAction().performAction(action);
                        }
                    }
                });
                this.contextMenu.getItems().add(item);
                if (!action.canBePerformed()) {
                    item.setDisable(true);
                }
            }
            this.contextMenu.show(gev.getImageView(), e.getScreenX(), e.getScreenY());
        }
    }

}
