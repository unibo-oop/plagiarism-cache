package breakout.model.levels;

import java.util.List;

import breakout.model.entities.Brick;
import breakout.model.entities.Wall;
import breakout.view.graphics.Backgrounds;
import breakout.view.graphics.Colors;
import javafx.scene.image.Image;
import javafx.util.Pair;

/**
 * Interface for a level that can be used in editor or during game loop.
 *
 */
public interface DecoratedLevel extends BasicLevel {

    /**
     * 
     * @return the name of the level
     */
    String getName();

    /**
     * @return the list fo bricks with respective colors
     */
    List<Pair<Brick, Colors>> getBricksWithColors();

    /**
     * @return the list of walls with thir respective color
     */
    List<Pair<Wall, Colors>> getWallsWithColors();

    /**
     * @return the background image
     */
    Image getBackgroundImage();

    /**
     * @return the background of the level
     */
    Backgrounds getBackground();

}
