package it.unibo.pacman.view.utilities;

import java.awt.Graphics;
import java.awt.LayoutManager;

import javax.swing.JPanel;
/**
 * A class that extends {@link JPanel} and used to put an image in JPanel BackGrounds.
 */
public class BackImagePanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private final String path;
    /**
     * Create a {@link BackImagePanel}.
     * @param lmg layout of panel.
     * @param path the path of image.
     */
    public BackImagePanel(final LayoutManager lmg, final String path) {
        super(lmg);
        this.path = path;
    }

    @Override
    protected final void paintComponent(final Graphics g) {
        super.paintComponent(g);
        g.drawImage(BufferedImageLoader.loadImage(path), 0, 0, null);
    }
}
