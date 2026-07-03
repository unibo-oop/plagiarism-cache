package oop.lit.view.controller.resize;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import oop.lit.controller.Controller;
import oop.lit.util.Vector2D;
import oop.lit.util.Vector2DImpl;
import oop.lit.view.BoardElementView;
import oop.lit.view.controller.Status;
import oop.lit.view.controller.Vertex;

/**
 * Completed implementation of all abstract methods.
 */
public class ResizeImpl extends AbstractResize {

    /**
     * All possible states for cursor.
     */
    public enum S {
        /**
         * Default, north-west, south-west, north-east, south-east, east, west,
         * north, south.
         */
        DEFAULT, NW, SW, NE, SE;
    }

    private S state = S.DEFAULT;
    private BoardElementView bevCopy;
    private final Controller controller;
    private final Status status;
    private final Vertex vertex = new Vertex();

    /**
     * @param controller
     *            Controller.
     * @param status
     *            Status.
     */
    public ResizeImpl(final Controller controller, final Status status) {
        super();
        this.controller = controller;
        this.status = status;
    }

    @Override
    protected EventHandler<MouseEvent> getOnMousePressed() {
        return new EventHandler<MouseEvent>() {

            @Override
            public void handle(final MouseEvent e) {

                if (vertex.isInResizeZone(e, bevCopy)) {
                    state = currentMouseState(e);
                    bevCopy.setDragAndDrop(false);
                } else {
                    state = S.DEFAULT;
                }
            }
        };
    }

    @Override
    protected EventHandler<MouseEvent> getOnMouseDragged() {
        return new EventHandler<MouseEvent>() {

            @Override
            public void handle(final MouseEvent e) {

                double newH = bevCopy.getHeight();
                double newW = bevCopy.getWidth();
                final Vector2D mousePosition = new Vector2DImpl(e.getSceneX(), e.getSceneY());

                if (!mousePosition.isVeryNearby(bevCopy.getScenePosition())) {
                    if (bevCopy.isResize()) {
                        /* Top-Right Resize */
                        if (state == S.NE) {
                            if (vertex.getPointB(bevCopy).isPositiveAngle(bevCopy.getScenePosition(), mousePosition)) {
                                newH = 2 * mousePosition.getPointRectDistance(bevCopy.getScenePosition(), -getAlfa());
                                newW = newH * getRatio();
                            } else {
                                newW = 2 * mousePosition.getPointRectDistance(bevCopy.getScenePosition(), Math.PI / 2
                                        - getAlfa());
                                newH = newW / getRatio();
                            }
                        }

                        /* Down-Right Resize */
                        if (state == S.SE) {
                            if (!vertex.getPointC(bevCopy).isPositiveAngle(bevCopy.getScenePosition(), mousePosition)) {
                                newH = 2 * mousePosition.getPointRectDistance(bevCopy.getScenePosition(), -getAlfa());
                                newW = newH * getRatio();
                            } else {
                                newW = 2 * mousePosition.getPointRectDistance(bevCopy.getScenePosition(), Math.PI / 2
                                        - getAlfa());
                                newH = newW / getRatio();
                            }
                        }

                        /* Down-Left Resize */
                        if (state == S.SW) {
                            if (!vertex.getPointD(bevCopy).isPositiveAngle(bevCopy.getScenePosition(), mousePosition)) {
                                newW = 2 * mousePosition.getPointRectDistance(bevCopy.getScenePosition(), Math.PI / 2
                                        - getAlfa());
                                newH = newW / getRatio();
                            } else {
                                newH = 2 * mousePosition.getPointRectDistance(bevCopy.getScenePosition(), -getAlfa());
                                newW = newH * getRatio();
                            }
                        }

                        /* Down-Left Resize */
                        if (state == S.NW) {
                            if (vertex.getPointA(bevCopy).isPositiveAngle(bevCopy.getScenePosition(), mousePosition)) {
                                newW = 2 * mousePosition.getPointRectDistance(bevCopy.getScenePosition(), Math.PI / 2
                                        - getAlfa());
                                newH = newW / getRatio();
                            } else {
                                newH = 2 * mousePosition.getPointRectDistance(bevCopy.getScenePosition(), -getAlfa());
                                newW = newH * getRatio();
                            }
                        }
                    }
                    if (!bevCopy.isDragAndDrop()) {
                        controller.getTransforms().resizeSelected(newW / bevCopy.getWidth());
                    }
                }
            }
        };
    }

    @Override
    protected EventHandler<MouseEvent> getOnMouseMoved(final BoardElementView bev) {
        return new EventHandler<MouseEvent>() {

            @Override
            public void handle(final MouseEvent e) {
                bevCopy = bev;
                state = currentMouseState(e);
                bev.getSelectionVisualization().setCursor(getCursorForState(state));
            }
        };
    }

    @Override
    protected EventHandler<MouseEvent> getOnMouseReleased() {
        return new EventHandler<MouseEvent>() {

            @Override
            public void handle(final MouseEvent e) {
                bevCopy.getSelectionVisualization().setCursor(Cursor.DEFAULT);
                state = S.DEFAULT;
                bevCopy.setDragAndDrop(true);
                ResizeImpl.this.status.setSelectionStarted(false);
            }
        };
    }

    private double getRatio() {
        return bevCopy.getWidth() / bevCopy.getHeight();
    }

    private double getAlfa() {
        return Math.toRadians(bevCopy.getBoardElement().getRotation());
    }

    /*
     * ==================== CURSOR PRIVATE METHODS =========================
     */

    private S currentMouseState(final MouseEvent e) {
        state = S.DEFAULT;

        if (vertex.isNWResizeZone(e, bevCopy)) {
            state = S.NW;
        } else if (vertex.isSWResizeZone(e, bevCopy)) {
            state = S.SW;
        } else if (vertex.isNEResizeZone(e, bevCopy)) {
            state = S.NE;
        } else if (vertex.isSEResizeZone(e, bevCopy)) {
            state = S.SE;
        }

        return state;
    }

    private static Cursor getCursorForState(final S state) {
        switch (state) {
        case NW:
            return Cursor.NW_RESIZE;
        case SW:
            return Cursor.SW_RESIZE;
        case NE:
            return Cursor.NE_RESIZE;
        case SE:
            return Cursor.SE_RESIZE;
        default:
            return Cursor.DEFAULT;
        }
    }
}