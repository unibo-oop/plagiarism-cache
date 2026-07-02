package model.enums;

/**
 * This enum models the different types of ship of the game
 * 
 */
public enum ShipType {

    CARRIER(5),
    BATTLESHIP(4),
    CRUISER(3),
    SUBMARINE(3),
    DESTROYER(2);

    private final int size;

    ShipType(final int size) {
        this.size = size;
    }

    /**
     * @return The file's name
     */
    public int getSize() {
        return this.size;
    }

}
