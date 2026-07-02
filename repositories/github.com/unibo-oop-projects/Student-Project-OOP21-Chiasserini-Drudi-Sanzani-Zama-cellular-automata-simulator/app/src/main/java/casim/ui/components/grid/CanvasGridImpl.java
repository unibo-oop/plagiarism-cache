package casim.ui.components.grid;

import java.util.ArrayDeque;
import java.util.Queue;

import casim.utils.ViewUtils;
import casim.utils.coordinate.Coordinates2D;
import casim.utils.coordinate.CoordinatesUtil;
import casim.utils.grid.Grid2D;
import casim.utils.grid.Grid2DImpl;
import casim.utils.grid.GridUtils;
import casim.utils.range.Ranges;
import javafx.beans.value.ChangeListener;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;

/**
 * Implementation of {@link CanvasGrid} to be used in the GUI.
 */
public class CanvasGridImpl extends Canvas implements CanvasGrid {
    private final int rows;
    private final int columns;
    private final Color separatorColor;
    private final double separatorWidth;
    private final double separatorOffset;

    private final Grid2D<CanvasGridCell> cells;
    private final Queue<Coordinates2D<Integer>> renderQueue = new ArrayDeque<>();

    private double width;
    private double height;
    private double cellSize;

    /**
     * Construct a new {@link CanvasGridImpl}.
     * 
     * @param rows number of rows of the grid (cells, not pixels).
     * @param columns number of columns of the grid (cells, not pixels).
     * @param separatorColor separator color.
     * @param separatorWidth separator width.
     * @param separatorOffset separator offset.
     */
    public CanvasGridImpl(final int rows, final int columns, final Color separatorColor, final double separatorWidth,
            final double separatorOffset) {
        this.rows = rows;
        this.columns = columns;
        this.separatorColor = separatorColor;
        this.separatorWidth = separatorWidth;
        this.separatorOffset = separatorOffset;
        this.cells = new Grid2DImpl<>(rows, columns);
        this.init();
    }

    @Override
    public void onCellClick(final MouseButton button, final CanvasGridCell cell, final Coordinates2D<Integer> coord) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void onCellHover(final CanvasGridCell cell, final Coordinates2D<Integer> coord) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getColumns() {
        return this.columns;
    }

    @Override
    public int getRows() {
        return this.rows;
    }

    @Override
    public void draw() {
        final var graphics = this.getGraphicsContext2D();
        this.drawCells(graphics);
        this.drawGrid(graphics);
    }

    @Override
    public double getCellSize() {
        return this.cellSize;
    }

    @Override
    public void setCells(final Grid2D<Color> cellColors) {
        GridUtils.get2dCoordStream(this.getRows(), this.getColumns())
            .forEach(coords -> {
                final var topLeft 
                    = CoordinatesUtil.of(coords.getX() * ((int) this.cellSize), coords.getY() * ((int) this.cellSize));
                final var cell = new CanvasGridCellImpl(cellColors.get(coords), topLeft, this.cellSize);
                this.cells.set(coords, cell);
                this.renderQueue.add(coords);
            });
    }

    @Override
    public CanvasGridCell getCell(final Coordinates2D<Integer> coord) {
        return this.cells.get(coord);
    }

    @Override
    public void handleSizeChange(final double width, final double height) {
        if (width == 0 || height == 0) {
            return;
        }
        this.updateDimensions(width, height);
        this.drawAfterResize();
    }

    private void init() {
        ViewUtils.fitToAnchorPane(this);
        final ChangeListener<Number> sizeListener = (observable, oldValue, newValue) -> {
            this.handleSizeChange(this.getWidth(), this.getHeight());
        };
        this.widthProperty().addListener(sizeListener);
        this.heightProperty().addListener(sizeListener);
    }

    private void updateDimensions(final double width, final double height) {
        this.width = width;
        this.height = height;
        this.cellSize = this.getNewPixelSize(width, height);
        super.setWidth(width);
        super.setHeight(height);
    }

    private void drawAfterResize() {
        this.clearCanvas();
        this.resizeCells();
        this.draw();
    }

    private void drawCell(final GraphicsContext graphics, final CanvasGridCell cell) {
        graphics.setFill(cell.getColor());
        graphics.fillRect(cell.getTopLeft().getX(), cell.getTopLeft().getY(), this.getCellSize(), this.getCellSize());
    }

    private void drawCells(final GraphicsContext graphics) {
        this.renderQueue.stream()
            .forEach(coord -> this.drawCell(graphics, this.cells.get(coord)));
        this.renderQueue.clear();
    }

    private void drawGrid(final GraphicsContext graphics) {
        if (this.separatorWidth == 0) {
            return;
        }
        graphics.setStroke(this.separatorColor);
        graphics.setLineWidth(this.separatorWidth);
        Ranges.of(0.0, this.width, this.getCellSize()).stream().forEach(
            x -> graphics.strokeLine(x + this.separatorOffset, 0, x + this.separatorOffset, height));
        Ranges.of(0.0, this.height, this.getCellSize()).stream().forEach(
            y -> graphics.strokeLine(0, y + this.separatorOffset, width, y + this.separatorOffset));
    }

    private double getNewPixelSize(final double width, final double height) {
        return Math.min(width / this.getColumns(), height / this.getRows());
    }

    private void resizeCells() {
        this.setCells(this.cells.map(CanvasGridCell::getColor));
    }

    private void clearCanvas() {
        final var graphics = this.getGraphicsContext2D();
        graphics.clearRect(0, 0, this.getWidth(), this.getHeight());
    }
}
