package casim.ui.components.grid;

import javafx.scene.paint.Color;

/**
 * Builder interface for {@link CanvasGridBuilder}.
 */
public interface CanvasGridBuilder {
    /**
     * Set the {@link CanvasGrid} separator width in pixels.
     *
     * @param separatorWidth the separator width.
     * @return a {@link CanvasGridBuilderImpl}.
     */
    CanvasGridBuilder separatorWidth(double separatorWidth);

    /**
     * Set the {@link CanvasGrid} separator color.
     * 
     * @param separatorColor {@link Color} to be set as separator color
     * @return a {@link CanvasGridBuilderImpl}.
     */
    CanvasGridBuilder separatorColor(Color separatorColor);

    /**
     * Build the {@link CanvasGrid} object, setting the mandatory fields.
     *
     * Throws an IllegalArgumentException if the values are invalid.
     * 
     * @param rows the number of rows in the {@link CanvasGrid}
     * @param columns the number of columns in the {@link CanvasGrid}
     * @return a {@link CanvasGrid} if the parameters are valid.
     */
    CanvasGrid build(int rows, int columns);
}
