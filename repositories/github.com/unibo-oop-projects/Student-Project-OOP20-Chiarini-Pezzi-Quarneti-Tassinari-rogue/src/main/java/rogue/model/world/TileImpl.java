package rogue.model.world;

/**
 * the default tile implementation.
 */
class TileImpl implements Tile {
    private final int x, y;
    private final boolean isWall;
    private boolean isDoor;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public final boolean isWall() {
        return isWall;
    }

    public final void setDoor() {
        isDoor = true;
    }

    public boolean isDoor() {
        return isDoor;
    }

    /**
     * @param x the x coordinate
     * @param y the y coordinate
     * @param madeOf the tile's material
     * @param isWall if the tile is a wall
     */
    TileImpl(final int x, final int y, final boolean isWall) {
        this.x = x;
        this.y = y;
        this.isWall = isWall;
    }
}
