package view.HUD;

import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.Objects;

import controllers.camera.Camera;
import controllers.texture.GetTexture;
import model.player.Player;

public class HUD implements HUDInterface {

    private static final int MAX_LIFES = 3;
    private static final int MIN_LIFES = 0;
    private static final double SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    private static final double SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    private final BufferedImage lifeImage;
    private final Player player;

    /**
     * Constructor for HUD.
     * 
     * @param player
     */
    public HUD(final Player player) {
        this.lifeImage = new GetTexture().getLifeImage();
        this.player = Objects.requireNonNull(player);
    }

    @Override
    public void displayHUD(final Graphics g, final Camera camera) {
        if (player.getHealth() < MIN_LIFES || player.getHealth() > MAX_LIFES) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < player.getHealth(); i++) { 
            g.drawImage(this.lifeImage, this.getLifeX(i) + (int) camera.getX(), this.getLifeY() + (int) camera.getY(),
                    this.getSingleLifeWidth(), this.getSingleLifeHeight(), null);
        }
        if (this.player.getActivePowerUpDebuff() != null) {
            g.drawImage(this.player.getActivePowerUpDebuff().getImage(), this.getPowerUPX() + (int) camera.getX(),
                    this.getPowerUPY() + (int) camera.getY(), this.getPowerUPWidth(), this.getPowerUPHeight(), null);
        }
    }

    private int getLifeX(final int i) {
        return -(int) SCREEN_WIDTH / 100 + (int) SCREEN_WIDTH / 52 * i;
    }

    private int getLifeY() {
        return (int) SCREEN_HEIGHT / 58;
    }

    private int getSingleLifeWidth() {
        return (int) SCREEN_WIDTH / 20;
    }

    private int getSingleLifeHeight() {
        return this.getSingleLifeWidth();
    }

    private int getPowerUPWidth() {
        return (int) SCREEN_WIDTH / 60;
    }

    private int getPowerUPHeight() {
        return (int) SCREEN_HEIGHT / 33;
    }

    private int getPowerUPX() {
        return (int) SCREEN_WIDTH / 15 + (int) SCREEN_WIDTH / 52 * 0;
    }

    private int getPowerUPY() {
        return (int) SCREEN_HEIGHT / 20;
    }

}