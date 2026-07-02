package model.minigames.truecolor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import utility.Pair;

/**
 * This represent the Abstract Class for random color algorithms.
 *
 */
public abstract class RandomColor {

    private final Map<StatusColor, List<Pair<Colors, Colors>>> randomColorMap;

    RandomColor() {
        this.randomColorMap = new HashMap<>();
    }

    /**
     * Get the random color map. 
     *
     * @return
     *          the random color
     */
    public final Map<StatusColor, List<Pair<Colors, Colors>>> getRandomColorMap() {
        this.randomColorMap.put(StatusColor.MEANCOLOR, getMeanColorList());
        this.randomColorMap.put(StatusColor.TRUECOLOR, getTrueColorList());
        return randomColorMap;
    }

    /**
     * Get the random color from the list passed. 
     * 
     * @param list
     *          the list of Colors from which get Colors
     * @return
     *          the random color
     */
    public Colors getRandomColor(final List<Colors> list) {
        return (Colors) list.get(new Random().nextInt(list.size()));
    }

    /**
     * Get the random color list for mean color.
     * @return
     *          the random color list
     */
    protected abstract List<Pair<Colors, Colors>> getMeanColorList();

    /**
     * Get the random color list for true color.
     * @return
     *          the random color list
     */
    protected abstract List<Pair<Colors, Colors>> getTrueColorList();

    /**
     * Initialize the list color.
     */
    protected abstract void initialize();
}
