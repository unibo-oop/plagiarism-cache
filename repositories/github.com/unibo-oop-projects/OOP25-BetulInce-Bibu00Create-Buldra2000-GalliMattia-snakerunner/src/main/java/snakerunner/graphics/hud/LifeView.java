package snakerunner.graphics.hud;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import snakerunner.core.GameConfiguration;

/**
 * LifeView is a HUD component and is used to show the remaining life in GamePanel.
 */
public final class LifeView extends AbstractBaseView {

    private static final long serialVersionUID = 1L;
    private static final String LIFE_TEXT = "Life : %1d";
    private static final int X = GameConfiguration.X_HUD;
    private static final int Y = GameConfiguration.Y_HUD;
    private static final int WIDTH_HUD = GameConfiguration.WIDTH_HUD;
    private static final int HEIGHT_HUD = GameConfiguration.HEIGHT_HUD;

    private int life;

    /**
     * Constructor for LifeView.
     */
    public LifeView() {
        initBaseView();
        setOpaque(false);
        setPreferredSize(new Dimension(WIDTH_HUD, HEIGHT_HUD));
    }

    @Override
    public void setValue(final int value) {
        this.life = value;
        repaint();
    }

    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);

        final String lifeText = String.format(LIFE_TEXT, life);

        g.setColor(Color.BLACK);
        g.drawString(lifeText, X, Y);
    }

}
