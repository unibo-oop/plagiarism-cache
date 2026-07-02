package jwhale.commons;
/**
 * Item type constants. Item is a container characterizer.
 *
 */
public enum ItemType {
    /**
     * Network item type.
     */
    NETWORK("Network"),
    /**
     * Volume item type.
     */
    VOLUME("Volume"),
    /**
     * Image item type.
     */
    IMAGE("Image");
    private String name;

    ItemType(final String name) {
        this.name = name;
    }
    /**
     * Getter for item name.
     * @return section name as String
     */
    public String toString() {
        return this.name;
    }

}
