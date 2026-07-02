package view.rendering;

import static view.utils.MappingUtils.computeOffset;
import static view.utils.MappingUtils.mapPhaseToColor;
import static view.utils.MappingUtils.mapToGrid;
import static view.utils.MappingUtils.mapToPixel;

import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import constraints.VehicleConstraints.Status;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import model.entities.trafficlight.PhaseManager;
import model.entities.trafficlight.TrafficLight;
import model.entities.trafficlight.TrafficLight.Phases;
import model.entities.vehicle.Vehicle;
import model.environment.Point;
import model.environment.cell.Cell;
import model.environment.grid.Grid;
import view.utils.images.ImageManager;
import view.utils.images.Tiles;

/**
 * Concrete implementation of the logics used to update the view.
 */
public class UpdateViewStrategyImpl implements UpdateViewStrategy {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateViewStrategyImpl.class);
    private static final ImageManager IMAGE_MANAGER = ImageManager.getImageLoaderInstance();
    private static final double ZOOM_SCALE = 0.25;
    private static final int TRAFFIC_LIGHT_OFFSET = 4;

    private final int canvasWidth;
    private final int canvasHeight;
    private final int cellWidth;
    private final int cellHeight;

    private final int zoomedWidth;
    private final int zoomedHeight;

    private final WritableImage backgroundImage;
    private boolean imageBuilt;
    private final GraphicsContext gc;
    private final WritableImage zoom;
    private final WritableImage zoomFrame;
    private final int backgroundWidth;
    private final int backgroundHeight;
    private final Grid<Cell> grid;

    /**
     * Creates an object that encapsulates the strategy needed to create and update
     * the visual representation of the simulation.
     *
     * @param canvas the canvas on which images should be drawn
     * @param grid   the grid from which the background is created
     */
    public UpdateViewStrategyImpl(final Canvas canvas, final Grid<Cell> grid) {
        gc = canvas.getGraphicsContext2D();
        imageBuilt = false;
        this.grid = grid;

        canvasWidth = (int) canvas.getWidth();
        canvasHeight = (int) canvas.getHeight();
        backgroundWidth = canvasWidth * 2;
        backgroundHeight = canvasHeight * 2;

        backgroundImage = new WritableImage(backgroundWidth, backgroundHeight);
        zoom = new WritableImage(canvasWidth, canvasHeight);
        zoomFrame = new WritableImage(canvasWidth, canvasHeight);

        cellWidth = backgroundWidth / grid.getNumberColumns();
        cellHeight = backgroundHeight / grid.getNumberRows();
        zoomedWidth = (int) Math.round(backgroundWidth * ZOOM_SCALE);
        zoomedHeight = (int) Math.round(backgroundHeight * ZOOM_SCALE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createBackground(final Grid<Cell> cellGrid) {
        final PixelWriter writer = backgroundImage.getPixelWriter();
        drawEnvironment(Color.GREEN);

        final Drawable<Point> roadDraw = (w, r) -> {
            final Point point = mapToGrid(r, backgroundHeight).andThen(mapToPixel(r, cellWidth, cellHeight)).apply(r,
                    backgroundHeight);
            writer.setPixels(r.getX() * cellWidth, point.getY(), cellWidth, cellHeight,
                    IMAGE_MANAGER.getTileReader(Tiles.ROAD, cellWidth, cellHeight), 0, 0);
        };
        roadDraw.drawAll(writer, cellGrid.getKeyStream().collect(Collectors.toSet()));
        zoom.getPixelWriter().setPixels(0, 0, backgroundWidth / 2, backgroundHeight / 2,
                backgroundImage.getPixelReader(), zoomedWidth, zoomedHeight);
        imageBuilt = true;
    }

    private void drawEnvironment(final Color color) {
        gc.setFill(color);
        gc.fillRect(0, 0, canvasWidth, canvasHeight);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void renderBackground() {
        if (!imageBuilt) {
            LOGGER.error("A background image must be created first", new IllegalStateException());
        }
        gc.drawImage(zoom, 0, 0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void composeFrame(final Set<Vehicle> vehicles, final Set<TrafficLight> trafficLights) {
        final WritableImage frame = new WritableImage(backgroundWidth, backgroundHeight);
        final PixelWriter writer = frame.getPixelWriter();

        final Drawable<TrafficLight> trafficLightdraw = (w, t) -> {
            final int offsetX = computeOffset(t.getSense(), TRAFFIC_LIGHT_OFFSET).getX();
            final int offsetY = computeOffset(t.getSense(), TRAFFIC_LIGHT_OFFSET).getY();
            final Point point = t.getPosition();
            w.setPixels((point.getX() + offsetX) * cellWidth, (point.getY() + offsetY) * cellHeight, cellWidth,
                    cellHeight,
                    IMAGE_MANAGER.getTileReader(mapPhaseToColor(t.getCurrentPhase()), cellWidth, cellHeight), 0, 0);
        };

        trafficLightdraw.drawAll(writer, trafficLights);

        final Drawable<Vehicle> vehicleDraw = (w, v) -> {
            final Point position = v.getPosition();
            final Point mappedPoint = mapToGrid(position, grid.getNumberColumns())
                    .andThen(mapToPixel(position, cellWidth, cellHeight)).apply(position, backgroundHeight);
            writer.setPixels(position.getX() * cellWidth, mappedPoint.getY(), cellWidth, cellHeight,
                    v.getStatus().equals(Status.IDLE)
                            ? IMAGE_MANAGER.getTileReader(Tiles.FLASHING_VEHICLE, cellWidth, cellHeight)
                            : IMAGE_MANAGER.getTileReader(Tiles.VEHICLE, cellWidth, cellHeight),
                    0, 0);
        };

        vehicleDraw.drawAll(writer, vehicles);

        zoomFrame.getPixelWriter().setPixels(0, 0, backgroundWidth / 2, backgroundHeight / 2, frame.getPixelReader(),
                zoomedWidth, zoomedHeight);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void renderCanvas() {
        gc.drawImage(zoom, 0, 0);
        gc.drawImage(zoomFrame, 0, 0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clearCanvas() {
        gc.clearRect(0, 0, canvasWidth, canvasHeight);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String updateVehiclesWidget(final Set<Vehicle> vehicles) {
        return String.valueOf(vehicles.stream().filter(v -> v.getStatus().equals(Status.IDLE)).count());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String updateTrafficLightWidget(final PhaseManager<Phases> phMan) {
        return String.valueOf(phMan.getCurrentPhaseDuration());
    }

}
