package javawulf.model.map.factory;

import javawulf.model.map.Biome;

/**
 * A Factory used to build 4 different default biomes.
 */
public interface BiomeFactory {
    /**
     * 
     * @return a simple Biome with three 5x5 rooms.
     */
    Biome getBiomeA();
    /**
     * 
     * @return a quite simple biome with four 5x5 rooms.
     */
    Biome getBiomeB();
    /**
     * 
     * @return a quite simple biome with four 5x5 rooms.
     */
    Biome getBiomeC();
    /**
     * 
     * @return a particular biome with four 5x5 rooms, but one of it is linked only with other biomes.
     */
    Biome getBiomeD();

    /**
     * 
     * @return a biome used for testing player and other entities.
     */
    Biome getTestBiome();

    /**
     * 
     * @return a biome covered 100% with a room.
     **/
    Biome getRoomBiome();
}
