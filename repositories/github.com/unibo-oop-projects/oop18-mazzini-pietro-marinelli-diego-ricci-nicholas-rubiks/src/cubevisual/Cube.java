package cubevisual;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import movestructure.Face;

/**
 * This code models a single cube (1X1X1) who has Rectangles as faces.
 * Those faces colors can be set and got to build a proper cube that can be retrieved as a JavaFX Group.
 */
public interface Cube {

    /**
     * @return The cube as a JavaFX Group.
     */
    Group getCube();

    /**
     * This method "converts" a given Face into the relative Rectangle.
     * @param face - Face to be converted.
     * @return The Face as a JavaFx Rectangle.
     */
    Rectangle getFace(Face face);

    /**
     * Back face color getter.
     * @return Back face's color.
     */
    Color getBackColor();

    /**
     * Bottom face color getter.
     * @return Bottom face's color.
     */
    Color getBottomColor();

    /**
     * Right face color getter.
     * @return Right face's color.
     */
    Color getRightColor();

    /**
     * Left face color getter.
     * @return Left face's color.
     */
    Color getLeftColor();

    /**
     * Top face color getter.
     * @return Top face's color.
     */
    Color getTopColor();

    /**
     * Front face color getter.
     * @return Front face's color.
     */
    Color getFrontColor();

    /**
     * Back face color setter.
     * @param color - Color to be set.
     */
    void setBackColor(Color color);

    /**
     * Bottom face color setter.
     * @param color - Color to be set.
     */
    void setBottomColor(Color color);

    /**
     * Right face color setter.
     * @param color - Color to be set.
     */
    void setRightColor(Color color);

    /**
     * Left face color setter.
     * @param color - Color to be set.
     */
    void setLeftColor(Color color);

    /**
     * Top face color setter.
     * @param color - Color to be set.
     */
    void setTopColor(Color color);

    /**
     * Front face color setter.
     * @param color - Color to be set.
     */
    void setFrontColor(Color color);

}
