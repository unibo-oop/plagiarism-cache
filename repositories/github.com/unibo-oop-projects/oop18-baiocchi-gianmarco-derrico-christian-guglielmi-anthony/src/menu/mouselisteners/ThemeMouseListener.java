package menu.mouselisteners;

import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import sounds.Sound;

/**
 * Mouse listener to use in choose-theme menu.
 */
public class ThemeMouseListener extends AbstractMouseListener {

    private final JButton button;
    private final ImageIcon noSelectionImage;
    private final ImageIcon selectionImage;

    /**
     * Mouse listener constructor for buttons in choose-theme menu.
     * @param sound to play in mouseEntered
     * @param button reference
     * @param image to show when mouse is on button
     */
    public ThemeMouseListener(final Sound sound, final JButton button, final ImageIcon image) {
        super(sound);
        this.button = button;
        this.selectionImage = image;
        this.noSelectionImage = (ImageIcon) this.button.getIcon();
    }

    /**
     * It plays the sound and changes the image when mouse is over button.
     */
    @Override
    public void mouseEntered(final MouseEvent e) {
        super.playSound();
        this.button.setIcon(this.selectionImage);
    }

    /**
     * Reset the initial image when mouse isn't over button anymore.
     */
    @Override
    public void mouseExited(final MouseEvent e) {
        this.button.setIcon(this.noSelectionImage);
    }

    @Override
    public void mouseClicked(final MouseEvent e) {
    }

    @Override
    public void mousePressed(final MouseEvent e) {
    }

    @Override
    public void mouseReleased(final MouseEvent e) {
    }
}
