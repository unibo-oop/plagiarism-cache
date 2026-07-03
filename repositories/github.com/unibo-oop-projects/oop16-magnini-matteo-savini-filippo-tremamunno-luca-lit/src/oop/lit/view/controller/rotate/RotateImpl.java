package oop.lit.view.controller.rotate;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import oop.lit.controller.Controller;
import oop.lit.util.Vector2D;
import oop.lit.util.Vector2DImpl;
import oop.lit.view.BoardElementView;
import oop.lit.view.controller.Status;

/**
 * Completed implementation of all abstract methods.
 */
public class RotateImpl extends AbstractRotate {

    private static final double ROUND_ANGLE = 360;
    private final Controller controller;
    private final Status status;
    private Vector2D origin = new Vector2DImpl();
    private Vector2D destination = new Vector2DImpl();

    /**
     * @param controller
     *            Controller.
     * @param status
     *            Status.
     */
    public RotateImpl(final Controller controller, final Status status) {
        super();
        this.controller = controller;
        this.status = status;
    }

    @Override
    protected EventHandler<MouseEvent> getOnMousePressed(final BoardElementView bev) {
        return new EventHandler<MouseEvent>() {

            @Override
            public void handle(final MouseEvent e) {
                origin = new Vector2DImpl(e.getX(), e.getY());
                destination = origin; /* For security business */
                bev.getRotateVisualization().setCursor(Cursor.CLOSED_HAND);
                bev.setResize(false);

                e.consume();
            }
        };
    }

    @Override
    protected EventHandler<MouseEvent> getOnMouseReleased(final BoardElementView bev) {
        return new EventHandler<MouseEvent>() {

            @Override
            public void handle(final MouseEvent e) {
                bev.getRotateVisualization().setCursor(Cursor.DEFAULT);
                bev.setResize(true);
                RotateImpl.this.status.setSelectionStarted(false);

                e.consume();
            }
        };
    }

    @Override
    protected EventHandler<MouseEvent> getOnMouseDragged(final BoardElementView bev) {
        return new EventHandler<MouseEvent>() {

            @Override
            public void handle(final MouseEvent e) {

                destination = new Vector2DImpl(e.getX(), e.getY());
                /*
                 * All these conditions are necessary to avoid a serious bug. If
                 * a user try to rotate an element putting the cursor extremely
                 * close to its center and making very short movements the image
                 * disappears.
                 */
                if (!destination.isVeryNearby(bev.getPosition())
                        && (bev.getPosition().getAngle(origin, destination)) < ROUND_ANGLE
                        && (bev.getPosition().getAngle(origin, destination)) > -ROUND_ANGLE) {
                    controller.getTransforms().rotateSelected((bev.getPosition().getAngle(origin, destination)));
                }
                origin = bev.getRotatePosition();

                e.consume();
            }
        };
    }
}
