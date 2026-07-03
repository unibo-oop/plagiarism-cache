package maingame.input.keyboardinput;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import maingame.input.ModelInput;

/**
 * Implementazione interfaccia KeyboardInput.
 */
public class KeyBoardInputImpl extends KeyAdapter {

    private final ModelInput modeInput;

    /**
     * @param model
     *            model al quale fa riferimento l'input di gioco.
     */
    public KeyBoardInputImpl(final ModelInput model) {
        this.modeInput = model;
    }
    @Override
    public void keyPressed(final KeyEvent e) {
        update(e, true);
    }

    @Override
    public void keyReleased(final KeyEvent e) {
        update(e, false);
    }

    private void update(final KeyEvent e, final boolean isPressed) {
        switch (e.getKeyCode()) {
        case KeyEvent.VK_W:
        case KeyEvent.VK_UP:
            modeInput.isUp(isPressed);
            break;
        case KeyEvent.VK_A:
        case KeyEvent.VK_LEFT:
            modeInput.setLeft(isPressed);
            break;
        case KeyEvent.VK_D:
        case KeyEvent.VK_RIGHT:
            modeInput.setRight(isPressed);
            break;
        case KeyEvent.VK_S:
        case KeyEvent.VK_DOWN:
            modeInput.setDown(isPressed);
            break;
        case KeyEvent.VK_SPACE:
            modeInput.setSpace(isPressed);
            break;
        case KeyEvent.VK_ENTER:
            modeInput.setEnter(isPressed);
            break;
        case KeyEvent.VK_ESCAPE:
            modeInput.setEsc(isPressed);
            break;
        default:
            break;
        }
    }

}
