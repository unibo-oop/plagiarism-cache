package oop.lit.view;

import javafx.beans.value.ChangeListener;
import javafx.scene.Node;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import oop.lit.model.GameElementModel;
import oop.lit.util.Observer;

/**
 * The class keeps together an ImageView and a GameElementModel as a wrapper and also works as its own observer.
 */

public class GameElementView implements Observer {

    private static final double RECTANGLE_STROKE_WIDTH = 10.0;

    private final ImageView elementImage = new ImageView();
    private final GameElementModel gem;
    private final Rectangle rectangle;

    /**
     * Public constructor for initializing the GameElementModel field, attaching an observer to it
     * (the class itself) and setting the image of this class (method inherited by ImageView) by
     * taking it from the GameElementModel via its getter.
     * 
     * @param gem
     *          to initialize a field which has access to all the methods of the GameElementModel interface
     */
    public GameElementView(final GameElementModel gem) {
        this.gem = gem;
        this.rectangle = new Rectangle();
        this.rectangle.setFill(Color.TRANSPARENT);
        this.rectangle.setStrokeWidth(RECTANGLE_STROKE_WIDTH);
        this.rectangle.setStroke(Color.AQUAMARINE);
        this.rectangle.setVisible(false);
        this.rectangle.widthProperty().bind(this.elementImage.fitWidthProperty());
        this.elementImage.fitWidthProperty().addListener((ChangeListener<Number>) (observable, oldValue, newValue) -> {
            this.rectangle.setHeight(newValue.doubleValue() * this.elementImage.getImage().getHeight()
                    / this.elementImage.getImage().getWidth());
        });

        this.elementImage.setImage(FXImageConverter.get().convert(gem.getImage()));
        Tooltip.install(this.elementImage, new Tooltip(this.gem.getName().orElse("(no name)")));
        this.gem.attach(this);
    }

    @Override
    public void notifyChange() {
        this.elementImage.setImage(FXImageConverter.get().convert(gem.getImage()));
    }

    /**
     * When this method is called, it activates or de-activates the border around the imageView of 
     * this gameElementView.
     *
     * @param state
     *         the selection state of the gameElementView
     */
    public void setSelectionItems(final boolean state) {
        this.rectangle.setVisible(state);
    }

    /**
     * A simple getter for the main field of this class.
     * 
     * @return
     *         the GameElementModel.
     */
    public GameElementModel getGameElementModel() {
        return this.gem;
    }

    /**
     * @return the image associated to the ImageView field.
     */
    public ImageView getImageView() {
        return this.elementImage;
    }

    /**
     * @return the object used to see margin element as a Node.
     */
    public Node getSelectionVisualization() {
        return rectangle;
    }

    /**
     * 
     */
    public void removed() {
        this.gem.detach(this);
    }
}
