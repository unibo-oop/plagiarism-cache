package movestructure;

import javafx.geometry.Point3D;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;

/**
 * This enumerator contains face colors and the way (Axis, Left rotation angle,
 * Right rotation angle) the cubes that are contained in a specific face should
 * move.
 */
public enum Face {

    /**
     * Green face, left.
     */
    GREEN(Rotate.X_AXIS, -90, 90, Color.GREEN),
    /**
     * White face, top.
     */
    WHITE(Rotate.Y_AXIS, -90, 90, Color.WHITE),
    /**
     * Blue face, right.
     */
    BLUE(Rotate.X_AXIS, 90, -90, Color.BLUE),
    /**
     * Red face, front.
     */
    RED(Rotate.Z_AXIS, -90, 90, Color.RED),
    /**
     * Yellow face, bottom.
     */
    YELLOW(Rotate.Y_AXIS, 90, -90, Color.YELLOW),
    /**
     * Orange face, back.
     */
    ORANGE(Rotate.Z_AXIS, 90, -90, Color.ORANGE);

    private final Point3D axis;
    private final double left;
    private final double right;
    private final Color color;

    Face(final Point3D axis, final double left, final double right, final Color color) {
        this.axis = axis;
        this.left = left;
        this.right = right;
        this.color = color;
    }

    /**
     * Axis getter.
     * 
     * @return Axis of the selected face as a {@link Point3D}.
     */
    public Point3D getAxis() {
        return this.axis;
    }

    /**
     * Left angle getter.
     * 
     * @return Angle of the selected face.
     */
    public double getLeft() {
        return this.left;
    }

    /**
     * Right angle getter.
     * 
     * @return Angle of the selected face.
     */
    public double getRight() {
        return this.right;
    }

    /**
     * Color getter.
     * 
     * @return Color of the selected face as a {@link Color}.
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Face getter by color.
     * 
     * @param color - Color of the face to be retrieved.
     * @return Face matching with color.
     * @throws RuntimeException if the color passed isn't contemplated.
     */
    public static Face getFaceByColor(final Color color) {
        for (final Face face : Face.values()) {
            if (face.getColor().equals(color)) {
                return face;
            }
        }
        throw new RuntimeException("Unknown color: " + color.toString());
    }

}
