package view.level.factory;

/**
 * 
 * Packs a texture pair.
 *
 */
public class TexturePair {

    private final String floorTexture;
    private final String wallTexture;

    /**
     * Returns a pair of textures.
     * @param floor
     *                  the floor texture to be used.
     * @param wall
     *                  the wall texture to be used.
     */
    public TexturePair(final String floor, final String wall) {
        this.floorTexture = floor;
        this.wallTexture = wall;
    }

    /**
     * @return the floorTexture.
     */
    public String getFloorTexture() {
        return floorTexture;
    }

    /**
     * @return the wallTexture.
     */
    public String getWallTexture() {
        return wallTexture;
    }
}
