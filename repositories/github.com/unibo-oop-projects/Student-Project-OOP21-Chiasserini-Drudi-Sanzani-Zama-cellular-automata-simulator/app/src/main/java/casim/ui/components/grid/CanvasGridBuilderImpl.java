package casim.ui.components.grid;

import casim.utils.BaseBuilder;
import casim.utils.Colors;
import javafx.scene.paint.Color;

/**
 * Builder class for {@link CanvasGrid}.
 */
public class CanvasGridBuilderImpl implements CanvasGridBuilder {

    private static final String INVALID_ROWS_NUMBER = "The number of rows must be greater than zero.";
    private static final String INVALID_COLUMNS_NUMBER = "The number of columns must be greater than zero.";
    private static final String INVALID_SEPARATOR_WIDTH = "The separator width must be equal or greater than zero.";
    private static final String SEPARATOR_COLOR_NULL = "The separator color cannot be null.";

    private final BaseBuilder base = new BaseBuilder();

    private int rows;
    private int columns;
    private Color separatorColor = Colors.BLACK;
    private double separatorWidth;

    @Override
    public CanvasGridBuilderImpl separatorWidth(final double separatorWidth) {
        this.base.registerCall();
        this.separatorWidth = this.base.checkValue(separatorWidth, x -> x >= 0, INVALID_SEPARATOR_WIDTH);
        return this;
    }

    @Override
    public CanvasGridBuilderImpl separatorColor(final Color separatorColor) {
        this.base.registerCall();
        this.separatorColor = this.base.checkNonNullValue(separatorColor, SEPARATOR_COLOR_NULL);
        return this;
    }

    @Override
    public CanvasGrid build(final int rows, final int columns) {
        this.base.registerCall();
        this.rows(rows)
            .columns(columns);
        return new CanvasGridImpl(
            this.rows, 
            this.columns, 
            this.separatorColor, 
            this.separatorWidth, 
            this.getSeparatorOffset());
    }

    private double getSeparatorOffset() {
        return this.separatorWidth / 2;
    }

    private CanvasGridBuilderImpl rows(final int rows) {
        this.base.registerCall();
        this.rows = this.base.checkValue(rows, x -> x > 0, INVALID_ROWS_NUMBER);
        return this;
    }

    private CanvasGridBuilderImpl columns(final int columns) {
        this.base.registerCall();
        this.columns = this.base.checkValue(columns, x -> x > 0, INVALID_COLUMNS_NUMBER);
        return this;
    }
}
