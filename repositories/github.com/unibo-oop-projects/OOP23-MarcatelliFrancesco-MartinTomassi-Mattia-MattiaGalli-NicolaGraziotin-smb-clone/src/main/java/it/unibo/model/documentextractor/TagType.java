package it.unibo.model.documentextractor;

/**
 * Enum representing different types of tags used in a tileset.
 */
public enum TagType {

    /**
     * Represents the "name" tag.
     */
    NAME("name"),

    /**
     * Represents the "gid" tag.
     */
    GID("gid"),

    /**
     * Represents the "x" tag.
     */
    X("x"),

    /**
     * Represents the "y" tag.
     */
    Y("y"),

    /**
     * Represents the "tileset" tag.
     */
    TILESET("tileset"),

    /**
     * Represents the "tile" tag.
     */
    TILE("tile"),

    /**
     * Represents the "image" tag.
     */
    IMAGE("image"),

    /**
     * Represents the "width" tag.
     */
    WIDTH("width"),

    /**
     * Represents the "height" tag.
     */
    HEIGHT("height"),

    /**
     * Represents the "map" tag.
     */
    MAP("map"),

    /**
     * Represents the "objectgroup" tag.
     */
    OBJECTGROUP("objectgroup"),

    /**
     * Represents the "object" tag.
     */
    OBJECT("object"),

    /**
     * Represents the "source" tag.
     */
    SOURCE("source");

    private final String tagName;

    /**
     * Constructs a TagType enum with the given tag name.
     *
     * @param tagName The name of the tag.
     */
    TagType(final String tagName) {
        this.tagName = tagName;
    }

    /**
     * Returns the string representation of this TagType.
     *
     * @return The string representation of this TagType.
     */
    @Override
    public String toString() {
        return this.tagName;
    }

}
