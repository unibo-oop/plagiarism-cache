package snakerunner.graphics.hud;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import snakerunner.core.GameConfiguration;

/**
 * TimerView is a HUD component and is used to show level in GamePanel.
 */
public final class LevelView extends AbstractBaseView {

    private static final long serialVersionUID = 1L;
    private static final String LEVEL_TEXT = "Level : %1d";
    private static final int WIDTH_HUD = GameConfiguration.WIDTH_HUD;
    private static final int HEIGHT_HUD = GameConfiguration.HEIGHT_HUD;
    private static final int X = GameConfiguration.X_HUD;
    private static final int Y = GameConfiguration.Y_HUD;

    private int level;

    /**
     * Constructor for LevelView.
     */
    public LevelView() {
        initBaseView();
        setOpaque(false);
        setPreferredSize(new Dimension(WIDTH_HUD, HEIGHT_HUD));
    }

    @Override
    public void setValue(final int value) {
        this.level = value;
        repaint();
    }

    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);

        final String levelText = String.format(LEVEL_TEXT, level);

        g.setColor(Color.BLACK);
        g.drawString(levelText, X, Y);
    }
}
