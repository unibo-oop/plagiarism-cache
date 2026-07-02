package view;

import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 * Interface for view components' resizing logic.
 *
 */
public interface DimensionCalculator {

    /**
     * Set the nodes' dimension when resizing scene.
     * @param grid 
     *           the grid that contains components to resize
     * @param percent 
     *           the resizing percentage
     * @param size 
     *           the new size value
     */
    void setNodeFontStyle(GridPane grid, Double percent, Double size);

    /**
     * Set the imageView dimension.
     * @param img
     *         the image to set
     * @param percent
     *         the resizing percentage
     * @param size
     *         the new size value
     */
    void setImageViewDimension(ImageView img, Double percent, Double size);

}
