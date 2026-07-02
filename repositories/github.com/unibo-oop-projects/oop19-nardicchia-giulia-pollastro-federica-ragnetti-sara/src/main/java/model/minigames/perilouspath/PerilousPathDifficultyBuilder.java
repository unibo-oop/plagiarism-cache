package model.minigames.perilouspath;

/**
 * Represents the perilous path difficulty builder.
 */
public interface PerilousPathDifficultyBuilder extends DifficultyBuilder<PerilousPathDifficultyBuilder> {

    /**
     * Modify the default numMines in a specific difficulty.
     * 
     * @param numMines
     *                the amount of mines
     * @return {@link PerilousPathDifficultyBuilder} which is the specific difficulty builder 
     */
    PerilousPathDifficultyBuilder setNumMines(int numMines); //NOPMD

    /**
     * Build the perilous path difficulty.
     * 
     * @return {@link PerilousPathDifficulty} which is the game's difficulty
     */
    PerilousPathDifficulty build();
}
