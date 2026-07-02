package model;

import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import application.StealthNinja;
import model.gameObject.GameObject;
import model.player.Player;
import view.WindowFinish;

public class Door extends GameObject {
    private static final int SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
    private static final int SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
    private boolean doorPassed;

    /**
     * Constructor for Door.
     * 
     * @param id
     * @param posX
     * @param posY
     * @param velX
     * @param velY
     * @param image
     */
    public Door(final ID id, final int posX, final int posY, final double velX, final double velY,
            final BufferedImage image) {
        super(id, posX, posY, velX, velY, image);
        this.doorPassed = false;
    }

    @Override
    public void tick() {

    }

    private void finishLevelMenu() {
        this.doorPassed = true;
        StealthNinja.GUICONTROLLER.setName();
        final WindowFinish finish = new WindowFinish();
        finish.createWindow();
        finish.setDimensions(SCREEN_WIDTH / 3, SCREEN_HEIGHT / 3);
        finish.show();
    }

    /**
     * 
     * set door and GameOver true, set play visible false and creates window when
     * the level is completed.
     * 
     * @param player
     */

    public void effect(final Player player) {
        player.setGameOver(true);
        player.setVisible(false);
        this.finishLevelMenu();

    }

    /**
     * 
     * @return boolean, true if player has passed through the door and won.
     */
    public boolean isDoor() {
        return doorPassed;
    }

}
