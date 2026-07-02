package javawulf.model.map;

/**
 * TilePosition is a kind of coordinate used to individuate tiles inside a bidimensional space (Map)
 * For example: TilePosition (1,2)  of TILE_A it means that TILE_A is in the second column and the third row of my tile-matrix.
 */
public final class TilePosition {

    private final int x;
    private final int y;
    /**
     * 
     * @param tileX column position
     * @param tileY row position
     */
    public TilePosition(final Integer tileX, final Integer tileY) {
        this.x = tileX;
        this.y = tileY;
    }

    /**
     * 
     * @return x tile position
     */
    public Integer getX() {
        return this.x;
    }

    /**
     * 
     * @return y tile position
     */
    public Integer getY() {
        return this.y;
    }

    @Override
    public String toString() {
        return "TilePosition [x:" + getX() + " y:" + getY() + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + x;
        result = prime * result + y;
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final TilePosition other = (TilePosition) obj;
        return x == other.x && y == other.y;
    }

}
