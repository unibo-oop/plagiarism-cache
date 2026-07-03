package oop.lit.util;

import java.util.Arrays;
import java.util.List;

/**
 * An enum used to describe various type of files.
 */
public enum FileType {
    /**
     * An image file.
     */
    IMAGE("Images", "png", "jpg", "gif"),
    /**
     * A LIT save file.
     */
    LITSAV("LIT save file", "litsav"),
    /**
     * A jar file.
     */
    JAR("JAR", "jar"),
    /**
     * Any type of file.
     */
    ANY("All files", "*");
    private final String typeName;
    private final List<String> nameExtensions;
    FileType(final String typeName, final String... nameExtensions) {
        this.typeName = typeName;
        this.nameExtensions = Arrays.asList(nameExtensions);
    }
    /**
     * @return a description of the file type.
     */
    public String getTypeName() {
        return typeName;
    }
    /**
     * @return a list of acceppted extensions for the file type.
     */
    public List<String> getNameExtensions() {
        return nameExtensions;
    }
}
