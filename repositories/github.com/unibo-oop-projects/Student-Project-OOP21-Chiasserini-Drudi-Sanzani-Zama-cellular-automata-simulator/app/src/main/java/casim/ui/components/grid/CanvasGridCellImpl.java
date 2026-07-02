package casim.ui.components.grid;

import casim.utils.coordinate.Coordinates2D;
import javafx.scene.paint.Color;

/**
 * A {@link CanvasGridCell} implementation.
 */
//package-private
class CanvasGridCellImpl implements CanvasGridCell {

    private final Coordinates2D<Integer> topLeft;
    private final double size;
    private Color color;

    //package-private
    CanvasGridCellImpl(final Color color, final Coordinates2D<Integer> topLeft, final double size) {
        this.color = color;
        this.topLeft = topLeft;
        this.size = size;
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    @Override
    public void setColor(final Color color) {
        this.color = color;
    }

    @Override
    public Coordinates2D<Integer> getTopLeft() {
        return this.topLeft;
    }

    @Override
    public double getSize() {
        return this.size;
    }

    @Override
    public String toString() {
        return "CanvasGridCellImpl [color=" + color + ", size=" + size + ", topLeft=" + topLeft + "]";
    }
}
