package view.game;

import javafx.beans.value.ObservableDoubleValue;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.transform.Rotate;

/**
 * The default implementation of LaneView.
 * Heavily in development.
 */
public class LaneView extends AnchorPane {

    /*
     * LaneView is an extension of an AnchorPane where the background image is drawn.
     * All other entities are drawn on a canvas right on top of the the AnchorPane.
     */
    private final Canvas canvas;

    /**
     * The constructor for the visual lane. 
     * By default a lane is an AnchorPane with a background image 
     * and a Canvas draws the images in place
     * @param bg Background image for the lane
     * @param height Observable height of the grid cell that contains the lane
     * @param width Observable width of the grid cell that contains the lane
     */
    public LaneView(final ImageView bg, final ObservableDoubleValue height, final ObservableDoubleValue width) {

        super();
        bg.fitHeightProperty().bind(height);
        bg.fitWidthProperty().bind(width);
        bg.setSmooth(true);

        canvas = new ResizableCanvas();
        canvas.heightProperty().bind(height);
        canvas.widthProperty().bind(width);

        this.getChildren().add(bg);
        this.getChildren().add(canvas);
    }

    /**
     * The prepare method is void and is called every time something needs to be drawn. 
     * It clears the canvas from the drawings of all the previous Obstacles.
     */
    public void prepare() {
        getGC().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    /**
     * This method draws the correct entities in the correct position upon the lane.
     * @param width The model-width of the entity to be drawn.
     * @param position The position of the top-left corner of the rectangle that contains the drawing of the entity.
     * @param img The texture of the entity to draw on the lane.
     * @param angle The rotation degrees this image will be drawn with.
     */
    public void drawEntity(final Image img, final Double position, final Double width,
                            final double angle) {
        getGC().save();
        Rotate r = new Rotate(angle, (canvas.getWidth() / 100 * position)
                                      + (canvas.getWidth() / 100 * width / 2),
                                        canvas.getHeight() / 2);
        getGC().setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
        getGC().drawImage(img, canvas.getWidth() / 100 * position, 0, 
                            canvas.getWidth() / 100 * width, canvas.getHeight());
        getGC().restore();
    }

    private GraphicsContext getGC() {
        return canvas.getGraphicsContext2D();
    }

    private class ResizableCanvas extends Canvas {

        @Override
        public boolean isResizable() {
            return true;
        }

        @Override
        public double prefHeight(final double width) {
            return getHeight();
        }

        @Override
        public double prefWidth(final double height) {
            return getWidth();
        }
    }
}
