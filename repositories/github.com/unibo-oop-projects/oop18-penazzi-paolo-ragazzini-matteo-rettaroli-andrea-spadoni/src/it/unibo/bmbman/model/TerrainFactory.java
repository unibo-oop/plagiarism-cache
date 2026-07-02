package it.unibo.bmbman.model;
/**
 * Used to generate terrains.
 */
public interface TerrainFactory {
    /**
     * Used to generate a terrain.
     * @param blocksnumber number of blocks in the level
     * @return a terrain 
     */
    Terrain create(int blocksnumber);
}
