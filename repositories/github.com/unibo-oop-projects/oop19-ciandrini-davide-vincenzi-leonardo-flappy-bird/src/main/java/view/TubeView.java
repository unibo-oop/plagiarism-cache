package view;

import javafx.scene.shape.Rectangle;
import util.Pair;

/**
 * Interface for the view of tube.
 */
public interface TubeView {

    /**
     * Initializes new pair of javafx.scene.shape.Rectangle.
     */
    void createRectangles();

    /**
     * Set top rectangle's position.
     * @param x coordinate X
     * @param y coordinate Y
     */
    void setTubeUpPosition(double x, double y);

    /**
     * Set top rectangle's size.
     * @param width Top rectangle's width
     * @param height Top rectangle's height
     */
    void setTubeUpDimension(double width, double height);

    /**
     * Set bottom rectangle's position.
     * @param x coordinate X
     * @param y coordinate Y
     */
    void setTubeDownPosition(double x, double y);

    /**
     * Set bottom rectangle's size.
     * @param width Bottom rectangle's width
     * @param height Bottom rectangle's height
     */
    void setTubeDownDimension(double width, double height);

    /**
     * Set the top rectangle's image.
     * @param image TubeUp image
     */
    void seTubeUpImage(String image);

    /**
     * Set the bottom rectangle's image.
     * @param image TubeDown image
     */
    void seTubeDownImage(String image);

    /**
     * Return the pair of rectangle.
     * @return Rectangle's pair
     */
    Pair<Rectangle, Rectangle> getRectangles();

}
