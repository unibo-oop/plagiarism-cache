package boxhead.view.entities;

import java.util.Set;

import boxhead.model.entities.utils.Direction;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.util.Pair;

public interface ZombieView {

    /**
     * Set parameters to zombie sprites
     * @param positions zombie positions and directions
     */
    void setPositionsAndDirections(Set<Pair<Point2D, Direction>> positions);

    /**
     * 
     * @return zombies sprites
     */
    Set<Pair<Point2D, Image>> getSprites();
        
    /**
     * View update
     */
    void update();
}

