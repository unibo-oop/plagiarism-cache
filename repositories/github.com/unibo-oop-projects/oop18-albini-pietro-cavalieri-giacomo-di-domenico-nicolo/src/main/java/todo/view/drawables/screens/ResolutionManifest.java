package todo.view.drawables.screens;

/**
 * This interface contains methods to fetch properties from a resolution
 * manifest, and other information derived from it.
 */
public interface ResolutionManifest {
    /**
     * @return the aspect ratio of this resolution
     */
    float getAspectRatio();

    /**
     * Get the original width of the room from which the sprites are created.
     *
     * @return the original width of the room
     */
    int getOriginalWidth();

    /**
     * Get the original height of the room from which the sprites are created.
     *
     * @return the original height of the room
     */
    int getOriginalHeight();

    /**
     * @return the size of a single square tile of the floor
     */
    float getFloorTileSize();

    /**
     * @return the scaled version of {@link #getFloorTileSize}.
     */
    float getScaledFloorTileSize();

    /**
     * @return the scaled size of a value box
     */
    float getScaledValueBoxSize();

    /**
     * @return the Y coordinate of the first full floor tile
     */
    float getFirstTileY();

    /**
     * Get a representation of the manifest queryable with XPath.
     *
     * @return a {@link XPathDocument} containing the manifest data
     */
    XPathDocument getQueryableManifest();
}
