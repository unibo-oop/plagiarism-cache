package it.unibo.oop.view;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.swing.JPanel;

/**
 * A class representing a {@link javax.swing.JPanel} with a background. It can
 * be extended by every other {@link javax.swing.JPanel} that has his own
 * background.
 */
public class BackgroundPanel extends JPanel {

    private static final long serialVersionUID = -5152861659918746222L;
    private Image background;

    /**
     * Builds the {@link javax.swing.JPanel} with the specified background.
     * 
     * @param name
     *            the name of the background to use
     */
    public BackgroundPanel(final String name) {
        try {
            this.background = ImageLoader.load(name);
        } catch (IOException e) {
            System.out.println("Error loading the background");
        }
    }

    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.background, this.getX(), this.getY(), this.getWidth(), this.getHeight(), this);
    }
}