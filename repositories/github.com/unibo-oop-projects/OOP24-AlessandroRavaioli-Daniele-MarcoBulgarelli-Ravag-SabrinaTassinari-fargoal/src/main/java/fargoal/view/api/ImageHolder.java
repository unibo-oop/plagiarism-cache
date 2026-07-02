package fargoal.view.api;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * Class that has the objective to memorize in a static way
 * images, to not have to read them at every frame.
 */
@SuppressFBWarnings(
    value = {
        "MS"
    },
    justification = "No field can be declared as final"
)
public final class ImageHolder {
    private static BufferedImage playerImage;
    private static BufferedImage chestImage;
    private static BufferedImage downstairsImage;
    private static BufferedImage hiddenGoldImage;
    private static BufferedImage sackOfGoldImage;
    private static BufferedImage swordImage;
    private static BufferedImage assassinImage;
    private static BufferedImage barbarianImage;
    private static BufferedImage mageImage;
    private static BufferedImage monkImage;
    private static BufferedImage rogueImage;
    private static BufferedImage spiderImage;
    private static BufferedImage warLordImage;
    private static BufferedImage wallImage;
    private static BufferedImage upStairsImage;
    private static BufferedImage tileImage;
    private static BufferedImage templeImage;
    private static BufferedImage beaconImage;

    private ImageHolder() { }

    static {
            playerImage = loadImage("/entity/Player.png");
            chestImage = loadImage("/tiles/Chest.png");
            downstairsImage = loadImage("/tiles/DownStairs.png");
            hiddenGoldImage = loadImage("/tiles/HiddenGold.png");
            sackOfGoldImage = loadImage("/tiles/SackOfGold.png");
            swordImage = loadImage("/tiles/Sword.png");
            assassinImage = loadImage("/entity/Assassin.png");
            barbarianImage = loadImage("/entity/Barbarian.png");
            mageImage = loadImage("/entity/Mage.png");
            monkImage = loadImage("/entity/Monk.png");
            rogueImage = loadImage("/entity/Rogue.png");
            spiderImage = loadImage("/entity/Spider.png");
            warLordImage = loadImage("/entity/WarLord.png");
            wallImage = loadImage("/tiles/Wall.png");
            upStairsImage = loadImage("/tiles/UpStairs.png");
            tileImage = loadImage("/tiles/Tile.png");
            templeImage = loadImage("/tiles/Temple.png");
            beaconImage = loadImage("/tiles/BeaconPlaced.png");
    }

    private static BufferedImage loadImage(final String path) {
        try (InputStream is = ImageHolder.class.getResourceAsStream(path)) {
            return ImageIO.read(is); 
        } catch (IOException e) {
            Logger.getAnonymousLogger().warning("IOException, unable to load image" + path + " - " + e.getMessage());
            return null;
        } catch (IllegalArgumentException e) {
            Logger.getAnonymousLogger().warning("Image was null");
            return null;
        }
    }

    /**
     * Method that returns the image for the player.
     * 
     * @return - the image of the player
     */
    public static BufferedImage player() {
        return playerImage;
    }


    /**
     * Method that returns the image for the chest.
     * 
     * @return - the image of the chest
     */
    public static BufferedImage chest() {
        return chestImage;
    }


    /**
     * Method that returns the image for the downstairs.
     * 
     * @return - the image of the downstairs
     */
    public static BufferedImage downstairs() {
        return downstairsImage;
    }


    /**
     * Method that returns the image for the hiddengold.
     * 
     * @return - the image of the hiddengold
     */
    public static BufferedImage hiddenGold() {
        return hiddenGoldImage;
    }


    /**
     * Method that returns the image for the sack of gold.
     * 
     * @return - the image of the sack of gold
     */
    public static BufferedImage sackOfGold() {
        return sackOfGoldImage;
    }


    /**
     * Method that returns the image for the sword.
     * 
     * @return - the image of the sword
     */
    public static BufferedImage sword() {
        return swordImage;
    }


    /**
     * Method that returns the image for the assassin.
     * 
     * @return - the image of the assassin
     */
    public static BufferedImage assassin() {
        return assassinImage;
    }


    /**
     * Method that returns the image for the barbarian.
     * 
     * @return - the image of the barbarian
     */
    public static BufferedImage barbarian() {
        return barbarianImage;
    }


    /**
     * Method that returns the image for the mage.
     * 
     * @return - the image of the mage
     */
    public static BufferedImage mage() {
        return mageImage;
    }


    /**
     * Method that returns the image for the monk.
     * 
     * @return - the image of the monk
     */
    public static BufferedImage monk() {
        return monkImage;
    }


    /**
     * Method that returns the image for the rogue.
     * 
     * @return - the image of the rogue
     */
    public static BufferedImage rogue() {
        return rogueImage;
    }


    /**
     * Method that returns the image for the spider.
     * 
     * @return - the image of the spider
     */
    public static BufferedImage spider() {
        return spiderImage;
    }


    /**
     * Method that returns the image for the war lord.
     * 
     * @return - the image of the war lord
     */
    public static BufferedImage warlord() {
        return warLordImage;
    }


    /**
     * Method that returns the image for the wall.
     * 
     * @return - the image of the wall
     */
    public static BufferedImage wall() {
        return wallImage;
    }


    /**
     * Method that returns the image for the tile.
     * 
     * @return - the image of the tile
     */
    public static BufferedImage tile() {
        return tileImage;
    }


    /**
     * Method that returns the image for the temple.
     * 
     * @return - the image of the temple
     */
    public static BufferedImage temple() {
        return templeImage;
    }


    /**
     * Method that returns the image for the upstairs.
     * 
     * @return - the image of the upstairs
     */
    public static BufferedImage upStair() {
        return upStairsImage;
    }

    /**
     * Method that returns the image for the upstairs.
     * 
     * @return - the image of the upstairs
     */
    public static BufferedImage beaconPlaced() {
        return beaconImage;
    }
}
