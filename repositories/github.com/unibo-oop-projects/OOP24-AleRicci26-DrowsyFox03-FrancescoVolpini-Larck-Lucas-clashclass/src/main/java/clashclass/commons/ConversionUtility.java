package clashclass.commons;

/**
 * Represents a static class for coordinates conversion utilities.
 */
public final class ConversionUtility {
    private ConversionUtility() { }

    /**
     * Converts from grid to world position in isometric view.
     *
     * @param gridPosition the grid position
     * @param rowSpan the width
     * @param colSpan the height
     *
     * @return the position in world space
     */
    public static Vector2D convertGridToWorldPosition(final VectorInt2D gridPosition, final int rowSpan, final int colSpan) {
        final var centerGridPosition = gridPosition;
        final double step = GameConstants.TILE_PIXEL_SIZE * (GameConstants.TILE_SCALE / 2.0);
        final double screenOffsetX = GameConstants.SCREEN_WIDTH / 2.0;
        final double screenOffsetY = -(GameConstants.SCREEN_HEIGHT * 0.7) * (GameConstants.TILE_SCALE / 2.0);
        return new Vector2D(
                (centerGridPosition.x() - centerGridPosition.y()) * step + screenOffsetX,
                (centerGridPosition.x() + centerGridPosition.y()) * step + screenOffsetY
        );
    }

    /**
     * Converts from world to grid position in isometric view.
     *
     * @param worldPosition the world position
     *
     * @return the position in grid space
     */
    public static VectorInt2D convertWorldToGridPosition(final Vector2D worldPosition) {
        final double step = GameConstants.TILE_PIXEL_SIZE * (GameConstants.TILE_SCALE / 2.0);
        final double screenOffsetX = GameConstants.SCREEN_WIDTH / 2.0;
        final double screenOffsetY = -(GameConstants.SCREEN_HEIGHT * 0.7) * (GameConstants.TILE_SCALE / 2.0);

        final double isoX = (worldPosition.x() - screenOffsetX) / step;
        final double isoY = (worldPosition.y() - screenOffsetY) / step;

        final int gridX = (int) Math.floor((isoY + isoX) / 2);
        final int gridY = (int) Math.floor((isoY - isoX) / 2);

        return new VectorInt2D(gridX, gridY);
    }
}
