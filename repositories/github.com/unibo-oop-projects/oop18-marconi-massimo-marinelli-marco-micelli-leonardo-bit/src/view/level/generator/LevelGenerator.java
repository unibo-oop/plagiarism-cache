package view.level.generator;

/**
 * Models the generation of the levels starting from a map trace.
 */
public interface LevelGenerator {

    /**
     * This method is called whenever it is necessary to generate a new level.
     * 
     * @param nLivello
     * 
     * @param nExitDoors
     * 
     * @param entryStairs
     * 
     * @param exitStairs
     */
    void levelGenerator(int nLivello, int nExitDoors, boolean entrances, boolean entryStairs, boolean exitStairs);
}
