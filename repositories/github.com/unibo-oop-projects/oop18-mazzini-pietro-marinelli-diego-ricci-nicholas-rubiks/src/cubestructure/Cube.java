package cubestructure;

import javafx.scene.paint.Color;
import movestructure.Face;
/**
 * Implementation of {@link Cubes}.
 */
public class Cube implements Cubes {
    private final int row;
    private final int column;
    private final int depth;
    private final int id;
    private Color top;
    private Color front;
    private Color left;
    private Color right;
    private Color back;
    private Color bottom;

    /**
     * This constructor initialize a Cube whit the following given parameters and setting all face color to GRAY.
     * @param idC - is the identifier of the cube inside RubikCube array of Cube 
     * @param r - is the row where Cube is in RubikCube array
     * @param c - is the column where Cube is in RubikCube array
     * @param d - is the depth where Cube is in RubikCube array
     */
    public Cube(final int idC, final int r, final int c, final int d) {
        this.row = r;
        this.column = c;
        this.depth = d;
        this.id = idC;
        this.top = Color.GRAY;
        this.front = Color.GRAY;
        this.left = Color.GRAY;
        this.right = Color.GRAY;
        this.back = Color.GRAY;
        this.bottom = Color.GRAY;
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public void setCube(final Color topC, final Color frontC, final Color leftC, final Color rightC, final Color backC, final Color bottomC) {
        this.top = topC;
        this.front = frontC;
        this.left = leftC;
        this.right = rightC;
        this.back = backC;
        this.bottom = bottomC;
    }
    @Override
    public final void setFromVisual(final Face face, final Color color) {
        switch (face) {
            case WHITE : this.setTop(color);
            break;
            case RED : this.setFront(color);
            break;
            case GREEN : this.setLeft(color);
            break;
            case BLUE : this.setRight(color);
            break;
            case ORANGE : this.setBack(color);
            break;
            case YELLOW : this.setBottom(color);
            break;
            default:
                break;
        }
    }
    @Override
    public final void setTop(final Color color) {
        this.top = color;
    }
    @Override
    public final void setFront(final Color color) {
        this.front = color;
    }
    @Override
    public final void setBack(final Color color) {
        this.back = color;
    }
    @Override
    public final void setRight(final Color color) {
        this.right = color;
    }
    @Override
    public final void setLeft(final Color color) {
        this.left = color;
    }
    @Override
    public final void setBottom(final Color color) {
        this.bottom = color;
    }
    @Override
    public final Cube getCube() {
        return this;
    }
    @Override
    public final Color getTop() {
        return this.top;
    }
    @Override
    public final Color getFront() {
        return this.front;
    }
    @Override
    public final Color getBack() {
        return this.back;
    }
    @Override
    public final Color getRight() {
        return this.right;
    }
    @Override
    public final Color getLeft() {
        return this.left;
    }
    @Override
    public final Color getBottom() {
        return this.bottom;
    }
    @Override
    public final int getID() {
        return this.id;
    }
    @Override
    public final int getRow() {
        return this.row;
    }
    @Override
    public final int getColumn() {
        return this.column;
    }
    @Override
    public final int getDepth() {
        return this.depth;
    }
    @Override
    public final Cube getCopyOf() {
        final Cube cube = new Cube(this.getID(), this.getRow(), this.getColumn(), this.getDepth());
        cube.setCube(this.getTop(), this.getFront(), this.getLeft(), this.getRight(), this.getBack(), this.getBottom());
        return cube;
    }
    //translate Color of javafx.scene.paint.Color that we use in a String (debug purpose only)
    private String colorToString(final Color c) {
        String color = null;
        if (Color.WHITE == c) {
            color = "WHITE";
        }
        if (Color.RED == c) {
            color = "RED";
        }
        if (Color.GREEN == c) {
            color = "GREEN";
        }
        if (Color.BLUE == c) {
            color = "BLUE";
        }
        if (Color.ORANGE == c) {
            color = "ORANGE";
        }
        if (Color.YELLOW == c) {
            color = "YELLOW";
        }
        if (Color.GRAY == c) {
            color = "GRAY";
        }
        return color;
    }
    @Override
    public final void cubeToString() {
        System.out.println("CUBE WITH ID: " + this.id);
        System.out.println("[" + this.row + "," + this.column + "," + this.depth + "]");
        System.out.println("top = " + colorToString(getTop()));
        System.out.println("front = " + colorToString(getFront()));
        System.out.println("left = " + colorToString(getLeft()));
        System.out.println("right = " + colorToString(getRight()));
        System.out.println("back = " + colorToString(getBack()));
        System.out.println("bottom = " + colorToString(getBottom()));
        System.out.println("-----------------------------------------------------------");
    }

}
