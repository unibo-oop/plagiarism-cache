package it.unibo.plantsfarm.view.utility;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.ImageIcon;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

/**
 * Utility class for loading game textures.
 * It manages resources.
 */
public final class Texture {

    public static final String MENU_PATH = "icons/menuIcon/";
    public static final double MENU_ICON_RATIO = 0.10;
    public static final int MENU_ICON_SIZE = calculateSize(MENU_ICON_RATIO);

    public static final String PLANT_PATH = "icons/plantIcon/";

    public static final double PLANT_ICON_RATIO = 0.06;
    public static final int PLANT_ICON_SIZE = calculateSize(PLANT_ICON_RATIO);

    public static final double SHOP_PLANT_ICON_RATIO = 0.08;
    public static final int SHOP_PLANT_ICON_SIZE = calculateSize(SHOP_PLANT_ICON_RATIO);

    public static final double MYSTERY_ICON_RATIO = 0.30;
    public static final int MYSTERY_ICON_SIZE = calculateSize(MYSTERY_ICON_RATIO);

    public static final String STAGE_PATH = "plantsStage/";
    public static final double STAGE_ICON_RATIO = 0.25;
    public static final int STAGE_ICON_SIZE = calculateSize(STAGE_ICON_RATIO);

    public static final String STATUS_PATH = "plantStatus/";
    public static final double STATUS_ICON_RATIO = 0.03;
    public static final int STATUS_ICON_SIZE = calculateSize(STATUS_ICON_RATIO);

    public static final ImageIcon ENCYCLOPEDIA_ICON = loadMenuIcon("Encyclopedia.png");
    public static final ImageIcon SHOP_ICON = loadMenuIcon("Shop.png");
    public static final ImageIcon STORAGE_ICON = loadMenuIcon("Storage.png");
    public static final ImageIcon SETTINGS_ICON = loadMenuIcon("Settings.png");
    public static final ImageIcon GIFT_ICON = loadMenuIcon("Gift.png");
    public static final ImageIcon RARE_GIFT_ICON = loadMenuIcon("RareGift.png");
    public static final ImageIcon EPIC_GIFT_ICON = loadMenuIcon("EpicGift.png");
    public static final ImageIcon LEGENDARY_GIFT_ICON = loadMenuIcon("LegendaryGift.png");
    public static final ImageIcon COIN_ICON = loadMenuIcon("Coin.png");
    public static final ImageIcon INVENTORY_ICON = loadMenuIcon("Inventory.png");

    public static final ImageIcon RESUME_ICON = loadMenuIcon("Resume.png");
    public static final ImageIcon COMMANDS_ICON = loadMenuIcon("Commands.png");
    public static final ImageIcon RESET_ICON = loadMenuIcon("Reset.png");
    public static final ImageIcon EXTRA_ICON = loadMenuIcon("Extra.png");
    public static final ImageIcon CREDITS_ICON = loadMenuIcon("Credit.png");
    public static final ImageIcon EXIT_ICON = loadMenuIcon("Exit.png");

    public static final ImageIcon WATER_ICON = loadStatusIcon("Water.png");
    public static final ImageIcon READY_ICON = loadStatusIcon("Ready.png");
    public static final ImageIcon SPEED_ICON = loadStatusIcon("SpeedBoost.png");
    public static final ImageIcon HARVEST_ICON = loadStatusIcon("HarvestBoost.png");

    private static final String EXTENSION = ".png";

    private Texture() {
        // Utility class constructor
    }

    /**
     * Gets a plant icon dynamically.
     *
     * @param plantName The name of the plant.
     * @return The ImageIcon.
     */
    public static ImageIcon getPlantIcon(final String plantName) {
        final String fullPath = PLANT_PATH + plantName + EXTENSION;
        return loadAndScale(fullPath, PLANT_ICON_SIZE, PLANT_ICON_SIZE);
    }

    /**
     * Gets a larger plant icon for the Shop.
     *
     * @param plantName The name of the plant.
     * @return The ImageIcon.
     */
    public static ImageIcon getShopPlantIcon(final String plantName) {
        final String fullPath = PLANT_PATH + plantName + EXTENSION;
        return loadAndScale(fullPath, SHOP_PLANT_ICON_SIZE, SHOP_PLANT_ICON_SIZE);
    }

    /**
     * Gets a giant plant icon for Mystery Box.
     *
     * @param plantName The name of the plant.
     * @return The ImageIcon.
     */
    public static ImageIcon getMysteryPlantIcon(final String plantName) {
        final String fullPath = PLANT_PATH + plantName + EXTENSION;
        return loadAndScale(fullPath, MYSTERY_ICON_SIZE, MYSTERY_ICON_SIZE);
    }

    /**
     * Gets a plant stage icon dynamically.
     *
     * @param plantName The name of the plant.
     * @param stage The stage number.
     * @return The ImageIcon.
     */
    public static ImageIcon getPlantStageIcon(final String plantName, final int stage) {
        final String fullPath = STAGE_PATH + plantName + "Stage/" + plantName + stage + EXTENSION;
        return loadAndScale(fullPath, STAGE_ICON_SIZE, STAGE_ICON_SIZE);
    }

    /**
     * Calculates the icon size based on screen width.
     *
     * @param ratio the ratio of the screen width.
     * @return the calculated size.
     */
    private static int calculateSize(final double ratio) {
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        return (int) (screenSize.width * ratio);
    }

    /**
     * Loads an icon from the menu folder.
     *
     * @param fileName The name of the file.
     * @return The ImageIcon.
     */
    private static ImageIcon loadMenuIcon(final String fileName) {
        final String fullPath = MENU_PATH + fileName;
        return loadAndScale(fullPath, MENU_ICON_SIZE, MENU_ICON_SIZE);
    }

    /**
     * Loads an icon from the status folder.
     *
     * @param fileName The name of the file.
     * @return The ImageIcon.
     */
    private static ImageIcon loadStatusIcon(final String fileName) {
        final String fullPath = STATUS_PATH + fileName;
        return loadAndScale(fullPath, STATUS_ICON_SIZE, STATUS_ICON_SIZE);
    }

    /**
     * Generic method to load and scale an image from resources.
     *
     * @param path The path to the resource.
     * @param width The width.
     * @param height The  height.
     * @return The ImageIcon, or null if not found.
     */
    private static ImageIcon loadAndScale(final String path, final int width, final int height) {
        final URL imageURL = Texture.class.getResource("/" + path);

        if (imageURL == null) {
            return null;
        }

        final ImageIcon original = new ImageIcon(imageURL);
        if (width <= 0 || height <= 0) {
            return original;
        }

        final BufferedImage resizedImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        final Graphics2D g2 = resizedImg.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
        g2.drawImage(original.getImage(), 0, 0, width, height, null);
        g2.dispose();

        return new ImageIcon(resizedImg);
    }
}
