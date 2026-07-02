package model.obstacle;

import java.awt.Point;
import java.util.Map;

import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 * ObstacleImpl interface.
 *
 */
public interface Obstacle {

    /**
     * Sets obstacle's properties and adds it to level's grid.
     * 
     * @param filePath
     *            path of obstacle's image
     * @param width
     *            obstacle's width
     * @param height
     *            obstacle's height
     * @param col
     *            grid's column which the obstacle will be placed in
     * @param row
     *            grid's row which the obstacle will be placed in
     * @param grid
     *            grid which the obstacle will be added
     */
    void addObstacleToGrid(String filePath, double width, double height, int col, int row, GridPane grid);

    /**
     * Getter of obstacles' positions.
     * 
     * @return obstaclePositions
     */
    Map<Point, ImageView> getObstaclePositions();

    /**
     * Clears obstaclePositions Map.
     */
    void clearObstaclePositions();
}
