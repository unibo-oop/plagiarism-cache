package it.unibo.superpeach.game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import it.unibo.superpeach.graphics.Texturer;

/**
 * Graphic class which renders lives and coins count on screen.
 * 
 * @author  Maurizio Capuano
 */
public final class Scoreboard {

    private boolean[] hearts;
    private boolean[] coins;
    private final int gameScale;
    private final Texturer texturer = new Texturer();
    private final BufferedImage[] sprites = texturer.getScoreboard();

    /**
     * @param lives total number of players' lives
     * @param totalCoins total number of collectable coins
     * @param scale game scale
     */
    public Scoreboard(final int lives, final int totalCoins, final int scale) {
        hearts = new boolean[lives];
        coins = new boolean[totalCoins];
        for (int i = 0; i < lives; i++) {
            hearts[i] = true;
        }
        for (int i = 0; i < totalCoins; i++) {
            coins[i] = false;
        }
        gameScale = scale;
    }

    /**
     * @param g graphic component
     * @param peachX player's x position to make the scoreboard match the player movement
     */
    public void render(final Graphics g, final int peachX) {
        for (int i = 0; i < hearts.length; i++) {
            if (hearts[i]) {
                g.drawImage(sprites[0], peachX + (i - (hearts.length / 2)) * 16 * gameScale + 2 * gameScale,
                        2 * gameScale + 16 * gameScale + gameScale, 16 * gameScale, 16 * gameScale, null);
            } else {
                g.drawImage(sprites[1], peachX + (i - (hearts.length / 2)) * 16 * gameScale + 2 * gameScale,
                        2 * gameScale + 16 * gameScale + gameScale, 16 * gameScale, 16 * gameScale, null);
            }
        }
        for (int i = 0; i < coins.length; i++) {
            if (coins[i]) {
                g.drawImage(sprites[2], peachX + (i - (coins.length / 2)) * 16 * gameScale + 2 * gameScale,
                        2 * gameScale, 16 * gameScale, 16 * gameScale, null);
            } else {
                g.drawImage(sprites[3], peachX + (i - (coins.length / 2)) * 16 * gameScale + 2 * gameScale,
                        2 * gameScale, 16 * gameScale, 16 * gameScale, null);
            }
        }
    }

    /**
     *  Removes a heart from visualization.
     */
    public void removeHeart() {
        if (hearts[hearts.length - 1]) {
            hearts[hearts.length - 1] = false;
        } else {
            int i = 0;
            while (i < hearts.length - 1 && hearts[i + 1]) {
                i++;
            }
            hearts[i] = false;
        }
    }

    /**
     * Restores a heart when collecting life powerup.
     */
    public void restoreHeart() {
        for (int i = 0; i < hearts.length; i++) {
            if (!hearts[i]) {
                hearts[i] = true;
                return;
            }
        }
    }

    /**
     * Collection of a coin with sprite modification.
     */
    public void collectCoin() {
        for (int i = 0; i < coins.length; i++) {
            if (!coins[i]) {
                coins[i] = true;
                return;
            }
        }
    }

}
