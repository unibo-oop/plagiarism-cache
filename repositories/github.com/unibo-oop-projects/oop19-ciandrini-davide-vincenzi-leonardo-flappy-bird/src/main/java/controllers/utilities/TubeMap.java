package controllers.utilities;

import javafx.scene.shape.Rectangle;
import util.Pair;

/**
 * This is the interfaces of the class that manage the map where tubes' rectangles are saved.
 */
public interface TubeMap {
    /**
     * Add to the treeMap the pair of tubes' rectangles.
     * @param tubePair Pair<Rectangle,Rectangle>
     */
    void addToMap(Pair<?, ?> tubePair);

    /**
     *
     * @return the last Pair added to the map
     */
    Pair<?, ?> getLastValue();

    /**
     * Scroll all the tubes' rectangles in the map.
     */
    void scrollTubePair();

    /**
     * Check if the tubes' rectangles are out of the window.
     */
    void checkWindowEnd();

    /**
     * Check if there is a collision between bird and map's tubes.
     * @param bird the bird's rectangle
     * @return true if there is a collision, false if there isn't a collision
     */
    boolean checkCollision(Rectangle bird);

    /**
     * Add to the view new nodes which are the elements of the Pair of tubes' rectangles.
     * @param tubePair Pair<Rectangle,Rectangle>
     */
    void printPairTube(Pair<?, ?> tubePair);


}
