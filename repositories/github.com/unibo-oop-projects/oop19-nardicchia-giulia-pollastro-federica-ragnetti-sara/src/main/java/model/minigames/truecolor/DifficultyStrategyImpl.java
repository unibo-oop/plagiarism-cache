package model.minigames.truecolor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import utility.Pair;

/**
 * Implementation of {@link DifficultyStrategy}.
 *
 */
public class DifficultyStrategyImpl implements DifficultyStrategy {

    private final Map<StatusColor, List<Pair<Colors, Colors>>> randomColorMap;
    private static final int NUM_COLOR = 4;
    private RandomColor colorRandom;

    /**
     * Constructor of {@link DifficultyStrategyImpl}.
     * 
     * @param difficulty
     *                  is a difficulty configuration.
     */
    public DifficultyStrategyImpl(final DifficultyConfiguration difficulty) {
        this.randomColorMap = new HashMap<>();
        if (difficulty.getNumColor() < NUM_COLOR) {
            this.colorRandom = new SimpleRandomColor(difficulty);
        } else {
            this.colorRandom = new ImprovedRandomColor(difficulty);
            this.createRandomColorMap();
        }
    }

    private void createRandomColorMap() {
        this.randomColorMap.put(StatusColor.MEANCOLOR, this.colorRandom.getMeanColorList());
        this.randomColorMap.put(StatusColor.TRUECOLOR, this.colorRandom.getTrueColorList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initMap() {
        this.randomColorMap.clear();
        this.colorRandom.initialize();
        this.createRandomColorMap();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<StatusColor, List<Pair<Colors, Colors>>> getRandomColorMap() {
        return this.randomColorMap;
    }
}
