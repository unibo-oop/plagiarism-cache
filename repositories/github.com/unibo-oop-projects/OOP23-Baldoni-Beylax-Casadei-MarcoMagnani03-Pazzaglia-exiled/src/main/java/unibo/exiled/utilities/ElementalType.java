package unibo.exiled.utilities;

import java.awt.Color;
import java.net.URL;

import javax.swing.ImageIcon;

/**
 * The ElementalType enum represents the elemental types of player class and
 * also the magical moves in the game.
 * Each elemental type has specific strengths and weaknesses against other
 * types.
 */
public enum ElementalType {
    /**
     * Represents the Fire elemental type.
     */
    FIRE("Fire", "fire.png"),

    /**
     * Represents the Bolt elemental type.
     */
    BOLT("Bolt", "bolt.png"),

    /**
     * Represents the Water elemental type.
     */
    WATER("Water", "water.png"),

    /**
     * Represents the Grass elemental type.
     */
    GRASS("Grass", "grass.png"),

    /**
     * Represents the Normal elemental type.
     */
    NORMAL("Normal", "normal.png");

    private final String name;
    private final String fileImageName;

    /**
     * Constructs an ElementalType with the specified name.
     *
     * @param name The name of the elemental type.
     * @param fileImageName the name of the file which contains the image of the element
     */
    ElementalType(final String name, final String fileImageName) {
        this.name = name;
        this.fileImageName = fileImageName;
    }

    /**
     * Gets the name of the elemental type.
     *
     * @return The name of the elemental type.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the image associated with the elemental type.
     *
     * @return The ImageIcon representing the image of the elemental type.
     */
    public ImageIcon getElementalImage() {
        final String imagePath = ConstantsAndResourceLoader.IMAGES_PATH + "/class/" + this.fileImageName;
        final URL imageURL = ClassLoader.getSystemResource(imagePath);
        return new ImageIcon(imageURL);
    }

    /**
     * Checks if the current elemental type is strong against the specified
     * elemental type.
     *
     * @param secondMoveType The elemental type to check against.
     * @return true if the current type is strong against the specified type, false
     *         otherwise.
     * @throws IllegalArgumentException if the current elemental type is not valid.
     */
    public boolean isStrongAgainst(final ElementalType secondMoveType) {
        switch (this) {
            case FIRE:
                return secondMoveType.equals(GRASS);
            case BOLT:
                return secondMoveType.equals(WATER);
            case GRASS:
                return secondMoveType.equals(BOLT);
            case WATER:
                return secondMoveType.equals(FIRE);
            case NORMAL:
                return false;
            default:
                throw new IllegalArgumentException("Invalid elemental type: " + this);
        }
    }

    /**
     * Method that associates each elemental type with a color.
     * @return The color of the ElementalType.
     */
    public Color getElementalColor() {
        switch (this) {
            case FIRE:
                return Color.RED;
            case WATER:
                return Color.BLUE;
            case BOLT:
                return Color.YELLOW;
            case GRASS:
                return Color.GREEN;
            default:
                return Color.GRAY;
        }
    }
}
