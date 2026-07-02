package model.obstacle;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import javafx.geometry.VPos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import utilities.Utilities;

/**
 * Class that manages obstacles.
 *
 */
public final class ObstacleImpl implements Obstacle {

    private static final ObstacleImpl SINGLETON = new ObstacleImpl();
    private final Map<Point, ImageView> obstaclePositions;

    /**
     * Constructor.
     */
    private ObstacleImpl() {
        this.obstaclePositions = new HashMap<Point, ImageView>();
    }

    @Override
    public void addObstacleToGrid(final String filePath, final double width, final double height, final int col,
            final int row, final GridPane grid) {
        final ImageView setImage = new ImageView(new Image(filePath));
        setImage.setFitWidth(Utilities.W / width);
        setImage.setFitHeight(Utilities.H / height);
        GridPane.setValignment(setImage, VPos.BOTTOM);
        grid.add(setImage, col, row);
        this.obstaclePositions.put(new Point(col, row), setImage);
    }

    @Override
    public Map<Point, ImageView> getObstaclePositions() {
        return this.obstaclePositions;
    }

    @Override
    public void clearObstaclePositions() {
        this.obstaclePositions.clear();
    }

    /**
     * Returns an instance of ObstacleImpl.
     * 
     * @return an instance of ObstacleImpl
     */
    public static ObstacleImpl get() {
        return SINGLETON;
    }
}
