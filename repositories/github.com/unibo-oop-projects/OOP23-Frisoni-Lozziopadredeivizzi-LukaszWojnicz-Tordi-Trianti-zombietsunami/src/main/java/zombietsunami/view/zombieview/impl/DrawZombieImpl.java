package zombietsunami.view.zombieview.impl;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import zombietsunami.view.api.KeyHandler;
import zombietsunami.view.api.VController;
import zombietsunami.view.zombieview.api.DrawZombie;

/**
 * Implementation of the DrawZombie interface, responsible for drawing and
 * updating the zombie.
 */
public class DrawZombieImpl implements DrawZombie {
    private boolean sprite = true;
    private int spriteCounter;
    private static final int NUM = 4;
    private static final int FRAMESCHANGE = 15;
    private static final String SEP = "/";
    private static final String ROOT = SEP + "zombietsunami" + SEP + "zombie" + SEP;
    private static final String ZOMBIE_1 = ROOT + "Zombie.png";
    private static final String ZOMBIE_2 = ROOT + "Zombie2.png";

    /**
     * Draws the zombie on the graphics context based on the provided controller.
     * 
     * @param g2         The graphics context.
     * @param controller The controller with the game-related information.
     * @throws IOException
     */
    @Override
    public void drawZombieV(final Graphics2D g2, final VController controller) {
        g2.drawImage(getZombie(), controller.getZombieScreenX() / NUM, controller.getZombieScreenY(),
                controller.titleSizeC(), controller.titleSizeC(), null);
    }

    private int getCounter() {
        return spriteCounter;
    }

    private int increaseCounter() {
        return spriteCounter++;
    }

    private BufferedImage getZombie() {
        final Logger logger = Logger.getLogger(DrawZombieImpl.class.getName());
        BufferedImage image = null;
        try {
            if (sprite) {
                image = loadImage(ZOMBIE_1);
            } else {
                image = loadImage(ZOMBIE_2);
            }
            increaseCounter();
            if (getCounter() > FRAMESCHANGE) {
                sprite = !sprite;
                spriteCounter = 0;
            }
        } catch (IOException e) {
            logger.severe("Errore durante il caricamento dell'immagine dello zombie: " + e.getMessage());
        }
        return image;
    }

    private BufferedImage loadImage(final String imagePath) throws IOException {
        final Logger logger = Logger.getLogger(DrawZombieImpl.class.getName());
        BufferedImage image = null;
        try {
            image = ImageIO.read(DrawZombieImpl.class.getResource(imagePath));
        } catch (IOException e) {
            logger.severe("Errore durante il caricamento dell'immagine della bomba: " + e.getMessage());
        }
        return image;
    }

    private void updateZombie(final VController controller) {
        controller.updateZombie();
    }

    /**
     * Handles key presses related to the zombie .
     * 
     * @param controller The controller with the game-related information.
     * @param keyH       The key handler for processing key events.
     */
    @Override
    public void handleKeyPress(final VController controller, final KeyHandler keyH) {
        if (keyH.isPressed() && !controller.isJumping()) {
            controller.jumpPress();
        }
        if (controller.isJumping()) {
            controller.updateJumpZombie();
        }
        updateZombie(controller);
    }
}
