package menu.mouselisteners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import sounds.Sound;

/**
 * It models an abstract mouse listener to add on a button.
 */
public abstract class AbstractMouseListener implements MouseListener {

    private final Sound selectSound;

    /**
     * Constructor.
     * @param sound to play when the mouse pass over a button
     */
    public AbstractMouseListener(final Sound sound) {
        this.selectSound = sound;
    }

    /**
     * Method to play the selection sound.
     */
    protected void playSound() {
        this.selectSound.play();
    }

    @Override
    public abstract void mouseEntered(MouseEvent e);

    @Override
    public abstract void mouseExited(MouseEvent e);
}
