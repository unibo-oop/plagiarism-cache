package view.board;

/**
 * Enumeration that hold the address of the different shape image.
 */

public enum TileTypeAddressView {

    /**
     * The triangle Tile image address.
     */
    TRIANGLE("/triangleTile.png"),
    /**
     * The square Tile image address.
     */
    SQUARE("/squareTile.png"),
    /**
     * The pentagon Tile image address.
     */
    PENTAGON("/pentagonTile.png"),
    /**
     * The hexagon Tile image address.
     */
    HEXAGON("/hexagonTile.png"),
    /**
     * The empty Tile image address.
     */
    EMPTY("/emptyTile.png"),
    /**
     * The treasure group Tile image address.
     */
    TREASUREGROUP("/treasureGroupTile.png"),
    /**
     * The Boat Tile image address.
     */
    BOATTILE("/boat.png");
    private String imageAddress;

    /**
     * @param imageAddress
     *                         The address of image.
     */
    TileTypeAddressView(final String imageAddress) {
        this.imageAddress = imageAddress;
    }

    /**
     * @return A string rapresentation of image address.
     */
    public String getAddress() {
        return imageAddress;
    }
}
