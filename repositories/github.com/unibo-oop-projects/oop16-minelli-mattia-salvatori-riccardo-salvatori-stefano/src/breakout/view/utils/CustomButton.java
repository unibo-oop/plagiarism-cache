package breakout.view.utils;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * A button with a given shape.
 */
public class CustomButton extends Group {

    private final Shape shape;
    private final Text text;

    /**
     * Creates a button with a desired shape and text.
     * 
     * @param shape
     *            The shape of the button
     * @param text
     *            the text inside
     */
    public CustomButton(final Shape shape, final String text) {
        super();
        this.shape = shape;
        this.text = new Text(text);
        this.getChildren().addAll(this.shape, this.text);
        this.text.relocate((this.getBoundsInLocal().getWidth() - this.text.getBoundsInLocal().getWidth()) / 2,
                (this.getBoundsInLocal().getHeight() - this.text.getBoundsInLocal().getHeight()) / 2);
    }

    /**
     * Sets the color of the shape.
     * 
     * @param color
     *            the color
     */
    public void setShapeColor(final Color color) {
        this.shape.setFill(color);
    }

    /**
     * Sets the stroke of the shape.
     * 
     * @param strokeType
     *            the StrokeType
     * @param width
     *            the width of the stroke
     * @param color
     *            the color
     */
    public void setShapeStroke(final StrokeType strokeType, final double width, final Color color) {
        this.shape.setStroke(color);
        this.shape.setStrokeType(strokeType);
        this.shape.setStrokeWidth(width);
    }

    /**
     * Sets an image as background of the shape.
     * 
     * @param image
     *            the image
     */
    public void setImage(final Image image) {
        this.shape.setFill(new ImagePattern(image));
        this.getChildren().forEach(c -> c.relocate(0, 0));
    }

    /**
     * Sets the color of the text.
     * 
     * @param color
     *            the color
     */
    public void setTextColor(final Color color) {
        this.text.setFill(color);
    }

    /**
     * Sets the font of the text.
     * 
     * @param font
     *            the font
     */
    public void setTextFont(final Font font) {
        this.text.setFont(font);
        this.getChildren().forEach(c -> c.relocate(0, 0));
        this.text.relocate((this.getBoundsInLocal().getWidth() - this.text.getBoundsInLocal().getWidth()) / 2,
                (this.getBoundsInLocal().getHeight() - this.text.getBoundsInLocal().getHeight()) / 2);
    }

    /**
     * Sets the style of the button when the mouse is pressed.
     * 
     * @param style
     *            The style
     */
    public void setStyleOnMousePressed(final String style) {
        this.setOnMousePressed(event -> {
            this.shape.setStyle(style);
        });
        this.setOnMouseReleased(e -> {
            this.shape.setStyle("");
        });
    }
}