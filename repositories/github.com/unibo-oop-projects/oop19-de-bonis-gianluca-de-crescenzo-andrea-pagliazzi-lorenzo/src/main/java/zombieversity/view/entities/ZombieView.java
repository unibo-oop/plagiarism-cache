package zombieversity.view.entities;

import java.util.Set;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.util.Pair;

/**
 * Used to manage zombies rendering.
 *
 */
public interface ZombieView {

    /**
     * Used to set zombies images parameters.
     * @param positions zombies' positions and directions.
     */
    void setPositionsAndDirections(Set<Pair<Point2D, Double>> positions);

    /**
     * Used to update the view.
     */
    void update();

    /**
     * 
     * @return Set of zombies images and positions
     */
    Set<Pair<Point2D, Image>> getSprites();

}
