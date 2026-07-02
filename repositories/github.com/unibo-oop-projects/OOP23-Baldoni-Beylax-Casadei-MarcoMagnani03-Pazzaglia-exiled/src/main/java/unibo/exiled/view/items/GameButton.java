package unibo.exiled.view.items;

import unibo.exiled.utilities.FontManager;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JButton;

/**
 * Custom JButton with a specific style for the game.
 */
public final class GameButton extends JButton {
    private static final long serialVersionUID = 6L;
    private static final int ROUNDED_PARAM = 10;

    private static final int PRIMARY_RED = 51;
    private static final int PRIMARY_GREEN = 102;
    private static final int PRIMARY_BLUE = 255;
    private final Color primaryColor = new Color(PRIMARY_RED, PRIMARY_GREEN, PRIMARY_BLUE);

    private static final int DARKER_FACTOR = 90;

    /**
     * Constructs a GameButton with the specified text.
     *
     * @param text The text to be displayed on the button.
     */
    public GameButton(final String text) {
        super(text);
        setButtonStyle();
    }

    private void setButtonStyle() {

        setFont(FontManager.getCustomFont());
        setForeground(Color.WHITE);
        setBackground(primaryColor);

        setBorderPainted(false);
        setContentAreaFilled(false);
    }

    @Override
    protected void paintComponent(final Graphics g) {
        if (getModel().isArmed()) {
            final int red = Math.max(0, getBackground().getRed() - DARKER_FACTOR);
            final int green = Math.max(0,  getBackground().getGreen() - DARKER_FACTOR);
            final int blue = Math.max(0,  getBackground().getBlue() - DARKER_FACTOR);
            final Color secondaryColor = new Color(red, green, blue);
            g.setColor(secondaryColor);
        } else {
            g.setColor(getBackground());
        }

        final int width = getWidth();
        final int height = getHeight();
        final Graphics2D graphics = (Graphics2D) g;
        graphics.fill(new RoundRectangle2D.Double(0, 0, width, height, ROUNDED_PARAM, ROUNDED_PARAM));

        super.paintComponent(g);
    }

    /**
     * Change the font size of the button.
     * @param newsize new font size of the button.
     */
    public void setFontSize(final int newsize) {
        setFont(FontManager.getCustomFont(newsize));
    }
}
