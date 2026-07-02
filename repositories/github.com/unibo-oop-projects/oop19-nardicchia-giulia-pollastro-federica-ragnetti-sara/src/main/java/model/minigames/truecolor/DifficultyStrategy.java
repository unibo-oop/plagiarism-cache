package model.minigames.truecolor;

import java.util.List;
import java.util.Map;

import utility.Pair;

/**
 * Interface DifficultyStrategy.
 *
 */
public interface DifficultyStrategy {

    /**
     * Initialize the random color map.
     */
    void initMap();

    /**
     * Getter for the random color map.
     * 
     * @return the created random color map
     */
    Map<StatusColor, List<Pair<Colors, Colors>>> getRandomColorMap();
}
