package it.unibo.the100dayswar.view.backgroundpanel;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import it.unibo.the100dayswar.commons.utilities.impl.IconLoader;

/**
 * Class that represents a panel with a background image.
 */
public class BackgroundPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private final String imagePath;

    /**
     * Constructor of the class.
     * It loads the Image using the IconLoader utility class.
     * 
     * @param imagePath
     */
    public BackgroundPanel(final String imagePath) {
        this.imagePath = imagePath;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void paintComponent(final Graphics graphic) {
        super.paintComponent(graphic);
        final ImageIcon icon = (ImageIcon) IconLoader.loadIcon(imagePath);
        if (icon != null && icon.getImage() != null) {
            graphic.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), this);
        } else {
            throw new IllegalArgumentException("Image not found");
        }
    }
}
