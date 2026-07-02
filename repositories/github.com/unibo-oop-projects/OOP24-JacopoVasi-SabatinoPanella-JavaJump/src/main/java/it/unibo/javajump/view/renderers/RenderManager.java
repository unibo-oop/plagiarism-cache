package it.unibo.javajump.view.renderers;

import it.unibo.javajump.model.GameModel;
import it.unibo.javajump.model.entities.character.Character;
import it.unibo.javajump.model.entities.collectibles.Coin;
import it.unibo.javajump.model.entities.platforms.Platform;

import java.awt.Graphics2D;

/**
 * The interface Render manager.
 */
public interface RenderManager {
    /**
     * Draws the first background on the screen during gameplay.
     *
     * @param g2        the Graphics2D context
     * @param model     the GameModel
     * @param deltaTime the time passed since the last update
     */
    void drawBackground1(Graphics2D g2, GameModel model, float deltaTime);

    /**
     * Draws the second background on the screen during gameplay.
     *
     * @param g2        the Graphics2D context
     * @param model     the GameModel
     * @param deltaTime the time passed since the last update
     */
    void drawBackground2(Graphics2D g2, GameModel model, float deltaTime);

    /**
     * Draws the player.
     *
     * @param g2        the Graphics2D context
     * @param player    the Player to draw
     * @param offsetY   the vertical offset
     * @param deltaTime the time passed since the last update (used for animation)
     */
    void drawPlayer(Graphics2D g2, Character player, float offsetY, float deltaTime);

    /**
     * Draws a single Coin.
     *
     * @param g2        the Graphics2D context
     * @param coin      the Coin to draw
     * @param offsetY   the vertical offset
     * @param deltaTime the time passed since the last update (used for animation)
     */
    void drawCoin(Graphics2D g2, Coin coin, float offsetY, float deltaTime);

    /**
     * Draws a single Platform.
     *
     * @param g2       the Graphics2D context
     * @param platform the Platform to draw
     * @param offsetY  the vertical offset
     */
    void drawPlatform(Graphics2D g2, Platform platform, float offsetY);

    /**
     * Draws the score UI.
     *
     * @param g2                   the Graphics2D context
     * @param model                the GameModel
     * @param isNewHighScore       true if the current score is a new high score
     * @param showHighScoreMessage true if the high score message should be shown
     */
    void drawScoreUI(Graphics2D g2, GameModel model, boolean isNewHighScore, boolean showHighScoreMessage);
}
