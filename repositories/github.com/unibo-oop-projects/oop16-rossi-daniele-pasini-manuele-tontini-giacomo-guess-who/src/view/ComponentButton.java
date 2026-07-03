package view;

import javax.swing.ImageIcon;

/**
 * Represents the button of the game.
 *
 */
@SuppressWarnings("serial")
public class ComponentButton extends MyButton {

    /**
     * @param imgIcon
     *            Represent the button image
     * @param width
     *            The correct width of the button image
     * @param height
     *            The correct height of the button image
     */
    public ComponentButton(final ImageIcon imgIcon, final int width, final int height) {
        super();
        final ImageIcon icon = getScaledIcon(imgIcon, width, height);
        setIcon(icon);
    }

    /**
     * Changed button icon.
     * 
     * @param imgIcon
     *            the new button icon
     */
    public void changeIcon(final ImageIcon imgIcon) {
        final ImageIcon icon = getScaledIcon(imgIcon, getIcon().getIconWidth(), getIcon().getIconHeight());
        setIcon(icon);
    }

}
