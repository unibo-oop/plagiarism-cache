package zombietsunami.view.obstacleview.impl;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.Graphics2D;

import java.util.List;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import zombietsunami.Pair;
import zombietsunami.view.api.VController;
import zombietsunami.view.obstacleview.api.DrawObstacle;

/**
 * Class definining how an obstacle should be printed
 * to video.
 */
public class DrawObstacleImpl implements DrawObstacle {

    private static final String SEP = "/";
    private static final String ROOT_BREAKABLE = SEP + "zombietsunami" + SEP + "obstacles" + SEP + "breakable" + SEP;
    private static final String ROOT_BOMB = SEP + "zombietsunami" + SEP + "obstacles" + SEP + "bomb" + SEP;
    private static final String BOMB_1 = ROOT_BOMB + "bomb1.png";
    private static final String BREAKABLE = ROOT_BREAKABLE + "barrel.png";

    /**
     * Draws all the obstacles inside the obstacle list.
     * @param g2 graphics.
     * @param obstacleIndexList obstacle list.
     * @param screenTilePos coordinates.
     * @param titleSize tile size.
     * @param controller the controller.
     */
    @Override
    public void drawObstacleV(final Graphics2D g2, final List<Integer> obstacleIndexList,
        final List<Pair<Integer, Integer>> screenTilePos, final int titleSize, final VController controller) {
        for (int i = 0; i < obstacleIndexList.size(); i++) {
            if (obstacleIndexList.get(i) == 1 && screenTilePos.get(i) != null) {
                controller.fillBombListFromMapC();
                draw(getBomb(), g2, screenTilePos, titleSize, i);
            }
            if (obstacleIndexList.get(i) == 2 && screenTilePos.get(i) != null) {
                controller.fillBreakableListFromMapC();
                draw(getBreakable(), g2, screenTilePos, titleSize, i);
            }
        }
    }

    /**
     * Draws the exact object in the i position of the list.
     * @param image the image.
     * @param g2 graphics.
     * @param screenTilePos the coordinates.
     * @param titleSize tile size.
     * @param i index of the item in the list.
     */
    private void draw(final BufferedImage image, final Graphics2D g2,
        final List<Pair<Integer, Integer>> screenTilePos, final int titleSize, final int i) {
        g2.drawImage(image, 
                screenTilePos.get(i).getX(), 
                screenTilePos.get(i).getY(),
                titleSize,
                titleSize,
                null);
    }

    /**
     * Gets the image representation of the breakable.
     * 
     * @return The BufferedImage representing the breakable.
     */
    @Override
    public BufferedImage getBreakable() {
        final Logger logger = Logger.getLogger(DrawObstacleImpl.class.getName());
        BufferedImage image = null;
        try {
            image = ImageIO.read(DrawObstacleImpl.class.getResource(BREAKABLE));
        } catch (IOException e) {
            logger.severe("Errore durante il caricamento dell'immagine del breakable: " + e.getMessage());
        }
        return image;
    }

    /**
     * Gets the image representation of the bomb.
     * 
     * @return The BufferedImage representing the bomb.
     */
    @Override
    public BufferedImage getBomb() {
        final Logger logger = Logger.getLogger(DrawObstacleImpl.class.getName());
        BufferedImage image = null;
        try {
            image = ImageIO.read(DrawObstacleImpl.class.getResource(BOMB_1));
        } catch (IOException e) {
            logger.severe("Errore durante il caricamento dell'immagine della bomba: " + e.getMessage());
        }
        return image;
    }
}
