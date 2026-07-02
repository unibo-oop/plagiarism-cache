package talisman.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.LayoutManager;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import talisman.util.PathUtils;

public class ImagePanel extends JPanel {
    private static final long serialVersionUID = -4187586139530880986L;

    private Image backgroundImage;

    /**
     * Creates a new panel.
     * 
     * @param imagePath the path to the background image
     */
    public ImagePanel(final String imagePath) {
        this(imagePath, Color.DARK_GRAY);
    }

    /**
     * Creates a new panel.
     * 
     * @param imagePath  the path to the background image
     * @param background the color of the background
     */
    public ImagePanel(final String imagePath, final Color background) {
        final LayoutManager layout = new BorderLayout();
        this.setLayout(layout);
        this.setImage(imagePath);
        this.setBackground(background);
        this.setOpaque(background != null);
        this.setMinimumSize(new Dimension(100, 100));
    }

    /**
     * Sets the rendered background image.
     * 
     * @param imagePath the path to the image
     */
    public final void setImage(final String imagePath) {
        Image loadedImage = null;
        try {
            loadedImage = ImageIO.read(ClassLoader.getSystemResource(imagePath));
        } catch (final IllegalArgumentException | IOException ex) {
            // File not found, loading NO_IMAGE image
            try {
                loadedImage = ImageIO
                        .read(ClassLoader.getSystemResource(PathUtils.getDevImagePath(PathUtils.NO_IMAGE_NAME)));
            } catch (final IllegalArgumentException | IOException exi) {
                // Shouldn't happen, since the file in "*_NO_IMAGE_PATH" should always exist
                exi.printStackTrace();
            }
        }
        this.backgroundImage = loadedImage;
        this.repaint();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    // Needs to be overridden to display the background image
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        final int size = Math.min(this.getWidth(), this.getHeight());
        final Image scaledImage = this.backgroundImage.getScaledInstance(size, size, Image.SCALE_DEFAULT);
        final int x = this.getWidth() / 2 - size / 2;
        final int y = this.getHeight() / 2 - size / 2;
        g.drawImage(scaledImage, x, y, this);
    }
}
