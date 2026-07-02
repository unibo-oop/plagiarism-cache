package it.unibo.javajump.view.viewstates;

import it.unibo.javajump.model.GameModel;
import it.unibo.javajump.view.graphics.GameGraphics;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import static it.unibo.javajump.utility.Constants.BACKGROUND_DEFAULT_COLOR;
import static it.unibo.javajump.utility.Constants.GAME_OVER_ALPHA;
import static it.unibo.javajump.utility.Constants.GAME_OVER_ALPHA_INIT;
import static it.unibo.javajump.utility.Constants.GAME_OVER_BEST_TEXT;
import static it.unibo.javajump.utility.Constants.GAME_OVER_CENTER_DIV;
import static it.unibo.javajump.utility.Constants.GAME_OVER_CONTINUE_TEXT;
import static it.unibo.javajump.utility.Constants.GAME_OVER_DURATION_INIT;
import static it.unibo.javajump.utility.Constants.GAME_OVER_IMG_H_OFF;
import static it.unibo.javajump.utility.Constants.GAME_OVER_IMG_SCALE_OFF;
import static it.unibo.javajump.utility.Constants.GAME_OVER_IMG_W_OFF;
import static it.unibo.javajump.utility.Constants.GAME_OVER_NEW_TEXT;
import static it.unibo.javajump.utility.Constants.GAME_OVER_NEW_TEXT_ESC;
import static it.unibo.javajump.utility.Constants.GAME_OVER_RECT_X;
import static it.unibo.javajump.utility.Constants.GAME_OVER_RECT_Y;
import static it.unibo.javajump.utility.Constants.GAME_OVER_SCORE_TEXT;
import static it.unibo.javajump.utility.Constants.GAME_OVER_TEXT_BEST_Y_OFF;
import static it.unibo.javajump.utility.Constants.GAME_OVER_TEXT_CONTINUE_Y_OFF;
import static it.unibo.javajump.utility.Constants.GAME_OVER_TEXT_NEW_Y_OFF;
import static it.unibo.javajump.utility.Constants.GAME_OVER_TEXT_SCORE_Y_OFF;
import static it.unibo.javajump.utility.Constants.GAME_OVER_TEXT_X_OFF;
import static it.unibo.javajump.utility.Constants.GAME_OVER_TIME_INIT;
import static it.unibo.javajump.utility.Constants.GOLD_TEXT_COLOR;
import static it.unibo.javajump.utility.Constants.RED_TEXT_COLOR;

/**
 * The type GameOverView.
 */
public final class GameOverView implements GameViewState {

    private float fadeAlpha = GAME_OVER_ALPHA_INIT;
    private float elapsedTime = GAME_OVER_TIME_INIT;
    private boolean fading;
    private float deltaTime;

    private final Font gameFont2;
    private final Font gameFont3;
    private final BufferedImage gameoverImage;

    /**
     * Instantiates a new Game over view.
     *
     * @param gameGraphics the game graphics
     */
    public GameOverView(final GameGraphics gameGraphics) {
        gameFont2 = gameGraphics.getGameFont2();
        gameFont3 = gameGraphics.getGameFont3();
        gameoverImage = gameGraphics.getGameOver();
    }

    @Override
    public void startFade() {
        this.fadeAlpha = GAME_OVER_ALPHA_INIT;
        this.elapsedTime = GAME_OVER_TIME_INIT;
        this.fading = true;
    }

    @Override
    public void stopFade() {
        this.fadeAlpha = GAME_OVER_ALPHA;
        this.fading = false;
    }

    @Override
    public void update() {
        if (fading) {
            elapsedTime += deltaTime;
            fadeAlpha = Math.min(GAME_OVER_ALPHA, elapsedTime / GAME_OVER_DURATION_INIT);
            if (fadeAlpha >= GAME_OVER_ALPHA) {
                fading = false;
            }
        }
    }


    @Override
    public void draw(final Graphics g, final GameModel model) {
        this.deltaTime = model.getDeltaTime();
        if (g instanceof Graphics2D g2) {
            final Composite oldComposite = g2.getComposite();

            final int w = model.getScreenWidth();
            final int h = model.getScreenHeight();

            final int centerX = w / GAME_OVER_CENTER_DIV;
            final int centerY = h / GAME_OVER_CENTER_DIV;

            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fadeAlpha));
            g2.setColor(Color.decode(BACKGROUND_DEFAULT_COLOR));
            g2.fillRect(GAME_OVER_RECT_X, GAME_OVER_RECT_Y, w, h);

            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, GAME_OVER_ALPHA));
            g2.drawImage(gameoverImage, (int) (centerX - gameoverImage.getWidth() / GAME_OVER_IMG_W_OFF),
                    (int) (centerY - h * GAME_OVER_IMG_H_OFF),
                    (int) (gameoverImage.getWidth() * GAME_OVER_IMG_SCALE_OFF),
                    (int) (gameoverImage.getHeight() * GAME_OVER_IMG_SCALE_OFF), null);

            if (fadeAlpha >= GAME_OVER_ALPHA) {
                if (model.getScoreManager().isBestScoreReached()) {
                    g.setColor(Color.decode(GOLD_TEXT_COLOR));
                    g.setFont(gameFont2);
                    g.drawString(
                            GAME_OVER_NEW_TEXT + model.getScoreManager().getBestScore() + GAME_OVER_NEW_TEXT_ESC,
                            (int) (centerX * GAME_OVER_TEXT_X_OFF), centerY + GAME_OVER_TEXT_NEW_Y_OFF);
                } else {
                    g.setColor(Color.WHITE);
                    g.setFont(gameFont2);
                    g.drawString(GAME_OVER_SCORE_TEXT + model.getScore(), (int) (centerX * GAME_OVER_TEXT_X_OFF),
                            centerY + GAME_OVER_TEXT_SCORE_Y_OFF);

                    g.setColor(Color.decode(RED_TEXT_COLOR));
                    g.setFont(gameFont3);
                    g.drawString(GAME_OVER_BEST_TEXT + model.getScoreManager().getBestScore(),
                            (int) (centerX * GAME_OVER_TEXT_X_OFF), centerY + GAME_OVER_TEXT_BEST_Y_OFF);
                }

                g2.setColor(Color.decode(RED_TEXT_COLOR));
                g2.setFont(gameFont3);
                g2.drawString(GAME_OVER_CONTINUE_TEXT, (int) (centerX * GAME_OVER_TEXT_X_OFF),
                        centerY + GAME_OVER_TEXT_CONTINUE_Y_OFF);
            }
            g2.setComposite(oldComposite);
        }
    }
}
