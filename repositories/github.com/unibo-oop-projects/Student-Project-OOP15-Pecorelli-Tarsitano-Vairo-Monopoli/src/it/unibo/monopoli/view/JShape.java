package it.unibo.monopoli.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * 
 * Class to create and draw pictures to be used as pawns and houses.
 *
 */
public class JShape extends JPanel {
    /**
     * 
     * enum that defines the types of possible forms.
     *
     */
    public enum Shapes {

        TRIANGLE, RECTANGLE, CIRCLE, ROMBO;
    }

    private final Shapes shape;
    private Color shapeFillColor = Color.GREEN;
    private Color shapeBorderColor = Color.BLACK;

    /**
     * builder.
     * 
     * @param color
     *            Color
     */
    public JShape(final Color color) {
        this(Shapes.ROMBO, color);
    }

    /**
     * builder.
     * 
     * @param shape
     *            Shapes
     * @param color
     *            Color
     */
    public JShape(final Shapes shape, final Color color) {
        this.shape = shape;
        this.shapeFillColor = color;
        this.setPreferredSize(new Dimension(C.JSHAPE_PREFERRED_SIZE, C.JSHAPE_PREFERRED_SIZE));
    }

    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);

        int[] xs;
        int[] ys;

        switch (this.shape) {
        case TRIANGLE:
            xs = new int[] { 0, getWidth() - 1, (getWidth() / 2) - 1 };
            ys = new int[] { getHeight() - 1, getHeight() - 1, 0 };
            g.setColor(shapeFillColor);
            g.fillPolygon(xs, ys, 3);
            g.setColor(shapeBorderColor);
            g.drawPolygon(xs, ys, 3);
            break;

        case RECTANGLE:
            g.setColor(shapeFillColor);
            g.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
            g.setColor(shapeBorderColor);
            g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
            break;

        case CIRCLE:
            g.setColor(shapeFillColor);
            g.fillOval(0, 0, getWidth() - 1, getHeight() - 1);
            g.setColor(shapeBorderColor);
            g.drawOval(0, 0, getWidth() - 1, getHeight() - 1);
            break;

        case ROMBO:
            xs = new int[] { (getWidth() / 2), 0, (getWidth() / 2), getWidth() };
            ys = new int[] { 0, (getWidth() / 2), getHeight(), (getHeight() / 2) };
            g.setColor(shapeFillColor);
            g.fillPolygon(xs, ys, 4);
            g.setColor(shapeBorderColor);
            g.drawPolygon(xs, ys, 4);
            break;
        default:
            break;
        }

    }

    /**
     * return Shape fill color.
     * 
     * @return Color
     */
    public Color getShapeFillColor() {
        return shapeFillColor;
    }

    /**
     * 
     * method to define the fill color.
     * 
     * @param shapeFillColor
     *            Color
     */
    public void setShapeFillColor(final Color shapeFillColor) {
        this.shapeFillColor = shapeFillColor;
    }

    /**
     * return Shape border color.
     * 
     * @return Color
     */
    public Color getShapeBorderColor() {
        return shapeBorderColor;
    }

    /**
     * 
     * method to define the border color.
     * 
     * @param shapeBorderColor
     *            Border Color
     */
    public void setShapeBorderColor(final Color shapeBorderColor) {
        this.shapeBorderColor = shapeBorderColor;
    }

    /**
     * return Shape.
     * 
     * @return Shape
     */
    public Shapes getShape() {
        return shape;
    }

}
