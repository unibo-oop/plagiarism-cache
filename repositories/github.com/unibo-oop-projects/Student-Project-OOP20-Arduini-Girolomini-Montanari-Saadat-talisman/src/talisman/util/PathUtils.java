package talisman.util;

import talisman.model.board.TalismanCellType;
import talisman.model.cards.CardType;

/**
 * Utility class for generic view related constants and functions.
 * 
 * @author Alberto Arduini
 *
 */
public final class PathUtils {
    /**
     * The path to the images folder.
     */
    public static final String IMAGES_PATH = "imgs/";
    /**
     * The path to the cells images folder.
     */
    public static final String CELLS_IMAGE_PATH = PathUtils.IMAGES_PATH + "cells/";
    /**
     * The path to the cards images folder.
     */
    public static final String CARDS_IMAGE_PATH = PathUtils.IMAGES_PATH + "cards/";
    /**
     * The path to the character icons images folder.
     */
    public static final String CHARACTER_ICONS_IMAGE_PATH = PathUtils.IMAGES_PATH + "char_icons/";

    /**
     * The path to the "image not found" image file as a resource path.
     */
    public static final String NO_IMAGE_NAME = "noimage";
    /**
     * The path to the "image not found" image file as a resource path.
     */
    public static final String DEV_PAWN_IMAGE_NAME = "player_pawn";

    private PathUtils() {
    }

    /**
     * Constructs the path to a cell image by its properties.
     * 
     * @param type     the type of cell
     * @param name     the name of the cell
     * @return the generated path
     */
    public static String getPathToCell(final TalismanCellType type, final String name) {
        return PathUtils.CELLS_IMAGE_PATH + type.toString() + "_" + name + ".png";
    }

    public static String getPathToCard(final CardType type, final String name) {
        return PathUtils.CARDS_IMAGE_PATH + type.toString() + "_" + name + ".png";
    }

    /**
     * Gets the path to a character icon.
     * 
     * @param character the character index
     * @return the path to the image
     */
    public static String getPathToCharacterIcon(final int character) {
        return PathUtils.CHARACTER_ICONS_IMAGE_PATH + character + ".png";
    }

    /**
     * Gets a developer placeholder texture by its name.
     * 
     * @param imageName the image name
     * @return the image path
     */
    public static String getDevImagePath(final String imageName) {
        return PathUtils.getDevImagePath(imageName, ".png");
    }

    /**
     * Gets a developer placeholder texture by its name and extension.
     * 
     * @param imageName the image name
     * @param extension the image file extension
     * @return the image path
     */
    public static String getDevImagePath(final String imageName, final String extension) {
        return PathUtils.IMAGES_PATH + "dev/" + imageName + extension;
    }
}
