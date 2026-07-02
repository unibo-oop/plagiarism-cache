package unibo.exiled.model.item.utilities;

import unibo.exiled.utilities.ConstantsAndResourceLoader;

import java.util.Optional;

import javax.swing.ImageIcon;

/**
 * Enumeration of item names.
 */
public enum ItemNames {
    /**
     * Health Potion item name.
     */
    HEALTH_POTION("Health Potion", "health_potion.png"),

    /**
     * Apple item name.
     */
    APPLE("Apple", "apple.png"),

    /**
     * Bandage item name.
     */
    BANDAGE("Bandage", "bandage.png"),

    /**
     * Strength Potion item name.
     */
    STRENGTH_POTION("Strength Potion", "strenght_potion.png"),

    /**
     * Defense Potion item name.
     */
    DEFENSE_POTION("Defense Potion", "defense_potion.png"),

    /**
     * Bolt Crystal item name.
     */
    BOLT_CRYSTAL("Bolt Crystal", "bolt_crystal.png"),

    /**
     * Water Crystal item name.
     */
    WATER_CRYSTAL("Water Crystal", "water_crystal.png"),

    /**
     * Grass Crystal item name.
     */
    GRASS_CRYSTAL("Grass Crystal", "grass_crystal.png"),

    /**
     * Fire Crystal item name.
     */
    FIRE_CRYSTAL("Fire Crystal", "fire_crystal.png");

    private final String name;
    private final String fileName;

    /**
     * Constructor for ItemNames enum.
     *
     * @param name     The name of the item.
     * @param fileName The file name associated with the item.
     */
    ItemNames(final String name, final String fileName) {
        this.name = name;
        this.fileName = fileName;
    }

    /**
     * Gets the image associated with the item name.
     *
     * @param itemName The name of the item.
     * @return The ImageIcon representing the image of the item name.
     */
    public static Optional<ImageIcon> getItemImage(final String itemName) {
        final Optional<ItemNames> item = getItemByName(itemName);
        if (item.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(new ImageIcon(ConstantsAndResourceLoader.getResourceURLFromPath(
                ConstantsAndResourceLoader.IMAGES_PATH
                        + "/items/" + item.get().fileName)));
    }

    private static Optional<ItemNames> getItemByName(final String itemName) {
        for (final ItemNames item : ItemNames.values()) {
            if (item.getName().equals(itemName)) {
                return Optional.of(item);
            }
        }
        return Optional.empty();
    }

    /**
     * Returns the name of the item.
     *
     * @return The name of the item.
     */
    public String getName() {
        return this.name;
    }
}
