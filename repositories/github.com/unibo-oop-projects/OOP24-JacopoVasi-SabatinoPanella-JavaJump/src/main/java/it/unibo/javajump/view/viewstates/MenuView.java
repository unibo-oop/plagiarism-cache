package it.unibo.javajump.view.viewstates;

import it.unibo.javajump.model.GameModel;
import it.unibo.javajump.view.graphics.GameGraphics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.image.BufferedImage;

import static it.unibo.javajump.utility.Constants.BACKGROUND_DEFAULT_COLOR;
import static it.unibo.javajump.utility.Constants.GOLD_TEXT_COLOR;
import static it.unibo.javajump.utility.Constants.MENU_VIEW_BEST_SCORE_TEXT;
import static it.unibo.javajump.utility.Constants.MENU_VIEW_BEST_SCORE_X_DIV;
import static it.unibo.javajump.utility.Constants.MENU_VIEW_BEST_SCORE_Y_OFF;
import static it.unibo.javajump.utility.Constants.MENU_VIEW_CENTER_DIV;
import static it.unibo.javajump.utility.Constants.MENU_VIEW_QUIT_TEXT;
import static it.unibo.javajump.utility.Constants.MENU_VIEW_QUIT_TEXT_Y_OFFSET;
import static it.unibo.javajump.utility.Constants.MENU_VIEW_RECT_X;
import static it.unibo.javajump.utility.Constants.MENU_VIEW_RECT_Y;
import static it.unibo.javajump.utility.Constants.MENU_VIEW_ROUND_RECT_ARC_H;
import static it.unibo.javajump.utility.Constants.MENU_VIEW_ROUND_RECT_ARC_W;
import static it.unibo.javajump.utility.Constants.MENU_VIEW_ROUND_RECT_HEIGHT;
import static it.unibo.javajump.utility.Constants.MENU_VIEW_ROUND_RECT_WIDTH;
import static it.unibo.javajump.utility.Constants.MENU_VIEW_ROUND_RECT_X_DIV;
import static it.unibo.javajump.utility.Constants.MENU_VIEW_ROUND_RECT_X_OFF;
import static it.unibo.javajump.utility.Constants.MENU_VIEW_ROUND_RECT_Y_OFF;
import static it.unibo.javajump.utility.Constants.MENU_VIEW_START_TEXT;
import static it.unibo.javajump.utility.Constants.MENU_VIEW_START_TEXT_Y_OFFSET;
import static it.unibo.javajump.utility.Constants.MENU_VIEW_STOKE_WIDTH;
import static it.unibo.javajump.utility.Constants.MENU_VIEW_STROKE_ARRAY;
import static it.unibo.javajump.utility.Constants.MENU_VIEW_STROKE_DASH;
import static it.unibo.javajump.utility.Constants.MENU_VIEW_STROKE_MITER_LIMIT;
import static it.unibo.javajump.utility.Constants.MENU_VIEW_TEXT_X_CENTER;
import static it.unibo.javajump.utility.Constants.MENU_VIEW_TILE_H_SCALAR;
import static it.unibo.javajump.utility.Constants.MENU_VIEW_TILE_W_SCALAR;
import static it.unibo.javajump.utility.Constants.MENU_VIEW_TITLE_IMG_HEIGHT_SCALAR;
import static it.unibo.javajump.utility.Constants.MENU_VIEW_TITLE_IMG_WIDTH_SCALAR;

/**
 * The type Menu view.
 */
public final class MenuView implements GameViewState {
    private final Font font1;
    private final Font font2;
    private final BufferedImage title;

    /**
     * Instantiates a new Menu view.
     *
     * @param graphics the graphics
     */
    public MenuView(final GameGraphics graphics) {
        font1 = graphics.getGameFont1();
        font2 = graphics.getGameFont2();
        title = graphics.getTitle();
    }

    @Override
    public void draw(final Graphics g, final GameModel model) {

        final int width = model.getScreenWidth();
        final int height = model.getScreenHeight();

        final int centerX = width / MENU_VIEW_CENTER_DIV;
        final int centerY = height / MENU_VIEW_CENTER_DIV;

        final int bestScore = model.getScoreManager().getBestScore();

        g.setColor(Color.decode(BACKGROUND_DEFAULT_COLOR));
        g.fillRect(MENU_VIEW_RECT_X, MENU_VIEW_RECT_Y, width, height);


        g.drawImage(title, (int) (width * MENU_VIEW_TITLE_IMG_WIDTH_SCALAR),
                height / MENU_VIEW_TITLE_IMG_HEIGHT_SCALAR,
                title.getWidth() * MENU_VIEW_TILE_W_SCALAR,
                title.getHeight() * MENU_VIEW_TILE_H_SCALAR, null);

        g.setColor(Color.WHITE);
        g.setFont(font1);
        g.drawString(MENU_VIEW_START_TEXT, centerX / MENU_VIEW_TEXT_X_CENTER,
                centerY + MENU_VIEW_START_TEXT_Y_OFFSET);
        g.drawString(MENU_VIEW_QUIT_TEXT, centerX / MENU_VIEW_TEXT_X_CENTER,
                centerY + MENU_VIEW_QUIT_TEXT_Y_OFFSET);

        g.setColor(Color.decode(GOLD_TEXT_COLOR));
        final Stroke dashed = new BasicStroke(MENU_VIEW_STOKE_WIDTH, BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_BEVEL, MENU_VIEW_STROKE_MITER_LIMIT,
                new float[]{MENU_VIEW_STROKE_ARRAY}, MENU_VIEW_STROKE_DASH);
        if (g instanceof Graphics2D g2d) {
            g2d.setStroke(dashed);
            g2d.drawRoundRect((centerX / MENU_VIEW_ROUND_RECT_X_DIV) - MENU_VIEW_ROUND_RECT_X_OFF,
                    height - MENU_VIEW_ROUND_RECT_Y_OFF, MENU_VIEW_ROUND_RECT_WIDTH, MENU_VIEW_ROUND_RECT_HEIGHT,
                    MENU_VIEW_ROUND_RECT_ARC_W, MENU_VIEW_ROUND_RECT_ARC_H);
            g.setFont(font2);
            g.drawString(MENU_VIEW_BEST_SCORE_TEXT + bestScore, centerX / MENU_VIEW_BEST_SCORE_X_DIV,
                    height - MENU_VIEW_BEST_SCORE_Y_OFF);
        }
    }

    @Override
    public void startFade() {

    }

    @Override
    public void update() {

    }

    @Override
    public void stopFade() {

    }
}
