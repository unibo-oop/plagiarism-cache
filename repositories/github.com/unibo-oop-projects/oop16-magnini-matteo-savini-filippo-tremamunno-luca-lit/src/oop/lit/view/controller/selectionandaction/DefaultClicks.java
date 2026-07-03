package oop.lit.view.controller.selectionandaction;

import oop.lit.controller.Controller;
import oop.lit.model.SelectableElementGroupModel;
import oop.lit.view.GameElementView;
import oop.lit.view.controller.Status;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

/**
 * Partial implementation of clicks.
 */
public abstract class DefaultClicks extends AbstractSelectionAndActions {

    private final Controller controller;
    private final Status status;

    /**
     * @param controller
     *            Controller.
     * @param status
     *            Status.
     */
    protected DefaultClicks(final Controller controller, final Status status) {
        super();
        this.controller = controller;
        this.status = status;
    }

    /**
     * @return the controller
     */
    protected final Controller getController() {
        return controller;
    }

    @Override
    protected EventHandler<MouseEvent> getOnMousePressedSource() {
        return new EventHandler<MouseEvent>() {

            @Override
            public void handle(final MouseEvent e) {
                DefaultClicks.this.status.setSelectionStarted(false);
                e.consume();
            }
        };
    }

    @Override
    protected EventHandler<MouseEvent> getOnMouseReleasedSource(final GameElementView gev,
            final SelectableElementGroupModel segm) {
        return new EventHandler<MouseEvent>() {

            @Override
            public void handle(final MouseEvent e) {
                if (e.getButton().equals(MouseButton.PRIMARY)) {
                    if (e.getClickCount() == 1) {
                        singleClickPerformed(e, gev, segm);
                    }
                    if (e.getClickCount() == 2) {
                        doubleClickPerformed(e, gev);
                    }
                }
                if (e.getButton().equals(MouseButton.SECONDARY)) {
                    rightClickPerformed(e, gev, segm);
                }
                e.consume();
            }
        };
    }

    @Override
    protected EventHandler<MouseEvent> getOnMousePressedBoard() {
        return new EventHandler<MouseEvent>() {

            @Override
            public void handle(final MouseEvent e) {
                DefaultClicks.this.status.setSelectionStarted(true);
                e.consume();
            }
        };
    }

    @Override
    protected EventHandler<MouseEvent> getOnMouseReleasedBoard() {
        return new EventHandler<MouseEvent>() {

            @Override
            public void handle(final MouseEvent e) {
                if (DefaultClicks.this.status.isSelectionStarted()) {
                    controller.getSelectionAndAction().forceClearSelection();
                    DefaultClicks.this.status.setSelectionStarted(false);
                }
                e.consume();
            }
        };
    }

    /**
     * A specific Event Handler when a single click is performed.
     * 
     * @param e
     *            the relative MouseEvent.
     * @param gev
     *            GameElementView
     * @param segm
     *            SelectableElementGroupView.
     */
    protected abstract void singleClickPerformed(MouseEvent e, GameElementView gev, SelectableElementGroupModel segm);

    /**
     * A specific Event Handler when a double click is performed.
     * 
     * @param e
     *            the relative MouseEvent.
     * @param gev
     *            GameElementView
     */
    protected abstract void doubleClickPerformed(MouseEvent e, GameElementView gev);

    /**
     * A specific Event Handler when a right click is performed.
     * 
     * @param e
     *            the relative MouseEvent.
     * @param gev
     *            GameElementView.
     * @param segm
     *            SelectableElementGroupView.
     */
    protected abstract void rightClickPerformed(MouseEvent e, GameElementView gev, SelectableElementGroupModel segm);
}
