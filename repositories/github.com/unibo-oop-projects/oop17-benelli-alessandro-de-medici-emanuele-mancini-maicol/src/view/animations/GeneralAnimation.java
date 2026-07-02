package view.animations;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import utilities.Directions;

/**
 * GeneralAnimationImpl interface.
 *
 */
public interface GeneralAnimation {

    /**
     * Setter of width and height for animations.
     * 
     * @param wTile
     *            animation's width
     * @param hTile
     *            animation's height
     */
    void setImageDimension(double wTile, double hTile);

    /**
     * Adds animations to the chosen Pane.
     * 
     * @param root
     *            the chosen Pane
     */
    void addImageToPane(Pane root);

    /**
     * Getter of the value of position on x axis of the animations.
     * 
     * @return x
     */
    double getX();

    /**
     * Setter of x.
     * 
     * @param x
     *            value of position on x axis of the animations
     */
    void setX(double x);

    /**
     * Getter of the value of position on y axis of the animations.
     * 
     * @return y
     */
    double getY();

    /**
     * Setter of y.
     * 
     * @param y
     *            value of position on y axis of the animations
     */
    void setY(double y);

    /**
     * Getter of the value from 0 to 3 that stands for north, east, south or west.
     * 
     * @return directionImage
     */
    Directions getDirectionImage();

    /**
     * Setter of directionImage.
     * 
     * @param directionImage
     *            value from 0 to 3 that stands for north, east, south or west
     */

    void setDirectionImage(Directions directionImage);

    /**
     * Getter of the image.
     * 
     * @return image
     */
    ImageView getImage();
}
