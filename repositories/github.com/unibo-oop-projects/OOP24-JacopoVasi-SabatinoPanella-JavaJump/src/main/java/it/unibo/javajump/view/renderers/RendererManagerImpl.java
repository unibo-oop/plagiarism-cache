package it.unibo.javajump.view.renderers;

import it.unibo.javajump.model.GameModel;
import it.unibo.javajump.model.entities.character.Character;
import it.unibo.javajump.model.entities.collectibles.Coin;
import it.unibo.javajump.model.entities.platforms.Platform;
import it.unibo.javajump.view.graphics.GameGraphics;
import it.unibo.javajump.view.renderers.sub.BackgroundRenderer;
import it.unibo.javajump.view.renderers.sub.BackgroundRendererImpl;
import it.unibo.javajump.view.renderers.sub.CoinRenderer;
import it.unibo.javajump.view.renderers.sub.CoinRendererImpl;
import it.unibo.javajump.view.renderers.sub.PlatformRenderer;
import it.unibo.javajump.view.renderers.sub.PlatformRendererImpl;
import it.unibo.javajump.view.renderers.sub.PlayerRenderer;
import it.unibo.javajump.view.renderers.sub.PlayerRendererImpl;
import it.unibo.javajump.view.renderers.sub.ScoreUIRenderer;
import it.unibo.javajump.view.renderers.sub.ScoreUIRendererImpl;
import it.unibo.javajump.view.sound.sfx.SoundEffectsManager;

import java.awt.Graphics2D;

import static it.unibo.javajump.utility.Constants.RENDER_MANAGER_BACKGROUND_ONE_PARALLAX_MODIFIER;
import static it.unibo.javajump.utility.Constants.RENDER_MANAGER_BACKGROUND_ONE_X_SPEED;
import static it.unibo.javajump.utility.Constants.RENDER_MANAGER_BACKGROUND_TWO_PARALLAX_MODIFIER;
import static it.unibo.javajump.utility.Constants.RENDER_MANAGER_BACKGROUND_TWO_X_SPEED;
import static it.unibo.javajump.utility.Constants.RENDER_MANAGER_COIN_FRAME_DURATION;
import static it.unibo.javajump.utility.Constants.RENDER_MANAGER_COIN_HEIGHT;
import static it.unibo.javajump.utility.Constants.RENDER_MANAGER_COIN_WIDTH;
import static it.unibo.javajump.utility.Constants.RENDER_MANAGER_PLATFORM_OUTLINE_THICKNESS;
import static it.unibo.javajump.utility.Constants.RENDER_MANAGER_PLATFORM_ROUND_CORNER_HEIGHT;
import static it.unibo.javajump.utility.Constants.RENDER_MANAGER_PLATFORM_ROUND_CORNER_WIDTH;
import static it.unibo.javajump.utility.Constants.RENDER_MANAGER_PLAYER_FRAME_DURATION;
import static it.unibo.javajump.utility.Constants.RENDER_MANAGER_PLAYER_HEIGHT;
import static it.unibo.javajump.utility.Constants.RENDER_MANAGER_PLAYER_WIDTH;
import static it.unibo.javajump.utility.Constants.TRANSITION_DURATION_1;
import static it.unibo.javajump.utility.Constants.TRANSITION_DURATION_2;

/**
 * Implementation of the RenderManager interface, used for graphical rendering of the elements of the gameplay.
 */
public class RendererManagerImpl implements RenderManager {
    /**
     * Field to store the platform renderer.
     */
    private final PlatformRenderer platformRenderer;
    /**
     * Field to store the coin renderer.
     */
    private final CoinRenderer coinRenderer;
    /**
     * Field to store the character renderer.
     */
    private final PlayerRenderer playerRenderer;
    /**
     * Field to store the first background renderer.
     */
    private final BackgroundRenderer backgroundRenderer1;
    /**
     * Field to store the second background renderer.
     */
    private final BackgroundRenderer backgroundRenderer2;
    /**
     * Field to store the UI&score renderer.
     */
    private final ScoreUIRenderer scoreUIRenderer;

    /**
     * Constructor for the RendererManagerImpl class. Associates the different renderers implementations
     * with their respective fields.
     *
     * @param soundEffectsManager the sound effects manager
     * @param graphics            the graphics
     */
    public RendererManagerImpl(final SoundEffectsManager soundEffectsManager, final GameGraphics graphics) {

        this.platformRenderer = new PlatformRendererImpl(
                RENDER_MANAGER_PLATFORM_OUTLINE_THICKNESS,
                RENDER_MANAGER_PLATFORM_ROUND_CORNER_WIDTH,
                RENDER_MANAGER_PLATFORM_ROUND_CORNER_HEIGHT,
                soundEffectsManager);

        this.coinRenderer = new CoinRendererImpl(
                graphics.getCoinSheet(),
                RENDER_MANAGER_COIN_WIDTH,
                RENDER_MANAGER_COIN_HEIGHT,
                RENDER_MANAGER_COIN_FRAME_DURATION,
                soundEffectsManager);

        this.playerRenderer = new PlayerRendererImpl(
                graphics.getPlayerSheet(),
                RENDER_MANAGER_PLAYER_WIDTH,
                RENDER_MANAGER_PLAYER_HEIGHT,
                RENDER_MANAGER_PLAYER_FRAME_DURATION);

        this.backgroundRenderer1 = new BackgroundRendererImpl(
                graphics.getBackgroundEasy(),
                graphics.getBackgroundMedium(),
                graphics.getBackgroundDifficult(),
                RENDER_MANAGER_BACKGROUND_ONE_PARALLAX_MODIFIER,
                RENDER_MANAGER_BACKGROUND_ONE_X_SPEED,
                TRANSITION_DURATION_1);

        this.backgroundRenderer2 = new BackgroundRendererImpl(
                graphics.getCloudsEasy(),
                graphics.getCloudsMedium(),
                graphics.getCloudsDifficult(),
                RENDER_MANAGER_BACKGROUND_TWO_PARALLAX_MODIFIER,
                RENDER_MANAGER_BACKGROUND_TWO_X_SPEED,
                TRANSITION_DURATION_2);

        this.scoreUIRenderer = new ScoreUIRendererImpl(
                graphics.getScoreContainer(),
                graphics.getGameFont2(),
                graphics.getGameFont3());
    }

    /**
     * {@inheritDoc}
     * The implemented method calls the drawBackground method of the backgroundRenderer1 field
     * to draw the first background.
     */
    @Override
    public void drawBackground1(final Graphics2D g2, final GameModel model, final float deltaTime) {
        backgroundRenderer1.drawBackground(g2, model, deltaTime);
    }

    /**
     * {@inheritDoc}
     * The implemented method calls the drawBackground method of the backgroundRenderer2 field
     * to draw the second background.
     */
    @Override
    public void drawBackground2(final Graphics2D g2, final GameModel model, final float deltaTime) {
        backgroundRenderer2.drawBackground(g2, model, deltaTime);
    }

    /**
     * {@inheritDoc}
     * The implemented method calls the drawPlayer method of the playerRenderer field
     * to draw the playable character.
     */
    @Override
    public void drawPlayer(final Graphics2D g2, final Character player, final float offsetY, final float deltaTime) {
        playerRenderer.drawPlayer(g2, player, offsetY, deltaTime);
    }

    /**
     * {@inheritDoc}
     * The implemented method calls the drawCoin method of the coinRenderer field
     * to draw a coin.
     */
    @Override
    public void drawCoin(final Graphics2D g2, final Coin coinImpl, final float offsetY, final float deltaTime) {
        coinRenderer.drawCoin(g2, coinImpl, offsetY, deltaTime);
    }

    /**
     * {@inheritDoc}
     * The implemented method calls the drawPlatform method of the platformRenderer field
     * to draw a platform.
     */
    @Override
    public void drawPlatform(final Graphics2D g2, final Platform platformImpl, final float offsetY) {
        platformRenderer.drawPlatform(g2, platformImpl, offsetY);
    }

    /**
     * {@inheritDoc}
     * The implemented method calls the drawScoreAndUI method of the scoreUIRenderer field
     * to draw the score and UI elements.
     */
    @Override
    public void drawScoreUI(final Graphics2D g2, final GameModel model,
                            final boolean isNewHighScore, final boolean showHighScoreMessage) {
        scoreUIRenderer.drawScoreAndUI(g2, model, isNewHighScore, showHighScoreMessage);
    }
}
