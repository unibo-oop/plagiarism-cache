package view;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * Represents a generic button of the game.
 *
 */
@SuppressWarnings("serial")
public abstract class MyButton extends JButton {

    /**
     * 
     */
    public MyButton() {
        setBorderPainted(false);
        setOpaque(false);
        setContentAreaFilled(false);
        setFocusPainted(false);
    }

    /**
     * @param icon
     *            the icon to scale
     * @param newWidth
     *            the width of scaled icon
     * @param newHeight
     *            the height of scaled icon
     * @return a scaled icon
     */
    protected ImageIcon getScaledIcon(final ImageIcon icon, final int newWidth, final int newHeight) {
        final Image image = icon.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_DEFAULT);
        return new ImageIcon(image);
    }
}
