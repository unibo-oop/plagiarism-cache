package oop.lit.view.controller.draganddrop;

import javafx.event.EventHandler;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import oop.lit.controller.Controller;
import oop.lit.model.BoardModel;
import oop.lit.util.Vector2D;
import oop.lit.util.Vector2DImpl;
import oop.lit.view.BoardElementView;
import oop.lit.view.controller.Vertex;

/**
 * Completed implementation of all abstract methods.
 */
public class DragAndDropImpl extends AbstractDragAndDrop {

    private final Controller controller;
    private Vector2D origin;
    private final Vertex vertex = new Vertex();

    /**
     * @param controller
     *            Controller.
     */
    public DragAndDropImpl(final Controller controller) {
        super();
        this.controller = controller;
    }

    @Override
    protected EventHandler<MouseEvent> getOnDragDetected(final BoardElementView bev, final BoardModel boardModel) {
        return new EventHandler<MouseEvent>() {

            @Override
            public void handle(final MouseEvent e) {

                /* Allow any transfer mode */
                if (boardModel.getSelected().contains(bev.getGameElementModel()) && bev.isDragAndDrop()
                        && !vertex.isInResizeZone(e, bev)) {
                    final Dragboard db = bev.getImageView().startDragAndDrop(TransferMode.ANY);
                    final ClipboardContent content = new ClipboardContent();
                    content.putString("");
                    db.setContent(content);
                    bev.setResize(false);

                    /* Keep trace of the origin */
                    origin = new Vector2DImpl(e.getSceneX(), e.getSceneY());
                }
                e.consume();
            }
        };
    }

    @Override
    protected EventHandler<DragEvent> getOnDragDone(final BoardElementView bev) {
        return new EventHandler<DragEvent>() {

            @Override
            public void handle(final DragEvent e) {

                bev.setResize(true);
                e.consume();
            }
        };
    }

    @Override
    protected EventHandler<DragEvent> getOnDragOver() {
        return new EventHandler<DragEvent>() {

            @Override
            public void handle(final DragEvent e) {

                /* Allow for both copying and moving, whatever user chooses */
                if (e.getDragboard().hasImage() || e.getDragboard().hasString()) {
                    e.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                    controller.getTransforms().moveSelected(new Vector2DImpl(e.getSceneX(), e.getSceneY()).sub(origin));
                    origin = new Vector2DImpl(e.getSceneX(), e.getSceneY());
                }
                e.consume();
            }
        };
    }

    @Override
    protected EventHandler<DragEvent> getOnDragDropped() {
        return new EventHandler<DragEvent>() {

            @Override
            public void handle(final DragEvent e) {

                controller.getTransforms().moveSelected(new Vector2DImpl(e.getSceneX(), e.getSceneY()).sub(origin));

                /*
                 * let the source know whether the image was successfully
                 * transferred and used
                 */
                e.setDropCompleted(true);
                e.consume();
            }
        };
    }
}