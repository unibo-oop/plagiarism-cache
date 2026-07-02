package snakerunner.graphics.hud;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import snakerunner.core.GameConfiguration;

/**
 * ScoreView is a HUD component and is used to show score in GamePanel.
 */
public final class ScoreView extends AbstractBaseView {

    private static final long serialVersionUID = 1L;
    private static final int WIDTH_HUD = GameConfiguration.WIDTH_HUD;
    private static final int HEIGHT_HUD = GameConfiguration.HEIGHT_HUD;
    private static final String SCORE_TEXT = "Score : %02d";
    private static final int X = GameConfiguration.X_HUD;
    private static final int Y = GameConfiguration.Y_HUD;

    private int score;

    /**
     * Constructor for ScoreView.
     */
    public ScoreView() {
        initBaseView();
        setOpaque(false);
        setPreferredSize(new Dimension(WIDTH_HUD, HEIGHT_HUD));
    }

    @Override
    public void setValue(final int value) {
        this.score = value;
        repaint();
    }

    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);

        final String scoreText = String.format(SCORE_TEXT, score);

        g.setColor(Color.BLACK);
        g.drawString(scoreText, X, Y);
    }

}
