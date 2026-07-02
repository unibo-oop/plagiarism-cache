package boxhead.view.level;

import java.util.Set;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.util.Pair;
import boxhead.view.world.tile.Tile;
/**
 * Interface to model the view of the level
 *
 */
public interface LevelView {
    
	/**
     * Get the full map as a collection of tiles
     * @return Set<Tile>
     */
    Set<Tile> getLevelMap();
    
    /**
     * Get all images representing each elements tile it's position
     * @return Set<Pair<Point2D, Image>
     */
    Set<Pair<Point2D, Image>> renderLevelMap();
    
    /**
     * Get all images representing each background tile it's position
     * @return Set<Pair<Point2D, Image>
     */
    //Set<Pair<Point2D, Image>> renderLevelBackground();
    
    /**
     * Set rendering scale
     * @param scale
     */
    void setScale(double scale);

}
