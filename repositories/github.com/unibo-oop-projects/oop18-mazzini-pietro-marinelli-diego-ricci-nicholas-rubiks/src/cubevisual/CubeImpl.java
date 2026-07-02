package cubevisual;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.transform.Rotate;
import movestructure.Face;

/**
 * Implementation of {@link Cube}.
 * Thanks to <a href="http://javastorms.org/creating-3d-cube-in-javafx/">Javastorms</a> for the main concept of this class.
 */
public class CubeImpl implements Cube {

    private final Group cube;
    private final Rectangle back;
    private final Rectangle bottom;
    private final Rectangle right;
    private final Rectangle left;
    private final Rectangle top;
    private final Rectangle front;

    private static final double STROKE_WIDTH = 0.1;

    /**
     * The constructor creates a directly usable cube, nothing more needs to be set.
     * @param size - Cube's size.
     * @param backColor - Cube's back color.
     * @param bottomColor - Cube's bottom color.
     * @param rightColor - Cube's right color.
     * @param leftColor - Cube's left color.
     * @param topColor - Cube's top color.
     * @param frontColor - Cube's front color.
     */
    public CubeImpl(final double size, final Color backColor, final Color bottomColor, final Color rightColor,
            final Color leftColor, final Color topColor, final Color frontColor) {

        cube = new Group();

        back = new Rectangle(size, size, backColor);
        back.setTranslateX(-0.5 * size);
        back.setTranslateY(-0.5 * size);
        back.setTranslateZ(0.5 * size);
        back.setStrokeWidth(STROKE_WIDTH * size);
        back.setStrokeType(StrokeType.INSIDE);
        back.setStroke(Color.BLACK);
        bottom = new Rectangle(size, size, bottomColor);
        bottom.setTranslateX(-0.5 * size);
        bottom.setTranslateY(0);
        bottom.setRotationAxis(Rotate.X_AXIS);
        bottom.setRotate(90);
        bottom.setStrokeWidth(STROKE_WIDTH * size);
        bottom.setStrokeType(StrokeType.INSIDE);
        bottom.setStroke(Color.BLACK);
        left = new Rectangle(size, size, leftColor);
        left.setTranslateX(-1 * size);
        left.setTranslateY(-0.5 * size);
        left.setRotationAxis(Rotate.Y_AXIS);
        left.setRotate(90);
        left.setStrokeWidth(STROKE_WIDTH * size);
        left.setStrokeType(StrokeType.INSIDE);
        left.setStroke(Color.BLACK);
        right = new Rectangle(size, size, rightColor);
        right.setTranslateX(0);
        right.setTranslateY(-0.5 * size);
        right.setRotationAxis(Rotate.Y_AXIS);
        right.setRotate(90);
        right.setStrokeWidth(STROKE_WIDTH * size);
        right.setStrokeType(StrokeType.INSIDE);
        right.setStroke(Color.BLACK);
        top = new Rectangle(size, size, topColor);
        top.setTranslateX(-0.5 * size);
        top.setTranslateY(-1 * size);
        top.setRotationAxis(Rotate.X_AXIS);
        top.setRotate(90);
        top.setStrokeWidth(STROKE_WIDTH * size);
        top.setStrokeType(StrokeType.INSIDE);
        top.setStroke(Color.BLACK);
        front = new Rectangle(size, size, frontColor);
        front.setTranslateX(-0.5 * size);
        front.setTranslateY(-0.5 * size);
        front.setTranslateZ(-0.5 * size);
        front.setStrokeWidth(STROKE_WIDTH * size);
        front.setStrokeType(StrokeType.INSIDE);
        front.setStroke(Color.BLACK);

        cube.getChildren().addAll(back, bottom, right, left, top, front);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Group getCube() {
        return cube;
    }

    /**
     * {@inheritDoc}
     * @throws RuntimeException if the face passed to the constructor isn't contemplated.
     */
    @Override
    public Rectangle getFace(final Face face) {
        switch (face) {
            case BLUE:
                return right;
            case GREEN:
                return left;
            case ORANGE:
                return back;
            case RED:
                return front;
            case WHITE:
                return top;
            case YELLOW:
                return bottom;
            default:
                throw new RuntimeException("Unknown face: " + face.toString());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Color getBackColor() {
        return (Color) back.getFill();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Color getBottomColor() {
        return (Color) bottom.getFill();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Color getRightColor() {
        return (Color) right.getFill();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Color getLeftColor() {
        return (Color) left.getFill();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Color getTopColor() {
        return (Color) top.getFill();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Color getFrontColor() {
        return (Color) front.getFill();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBackColor(final Color color) {
        back.setFill(color);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBottomColor(final Color color) {
        bottom.setFill(color);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRightColor(final Color color) {
        right.setFill(color);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLeftColor(final Color color) {
        left.setFill(color);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTopColor(final Color color) {
        top.setFill(color);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFrontColor(final Color color) {
        front.setFill(color);
    }
}
