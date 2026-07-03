package maingame.menu.listener;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import maingame.level.Difficulty;
import maingame.menu.listener.controller.ControllerMenu;
import maingame.menu.listener.controller.ControllerMenuImpl;
import maingame.menu.menu.Menu;
import maingame.menu.model.ModelMenu;
/**
 * listener per la classe menu.
 */
public class InputListener extends KeyAdapter {
    private final ControllerMenu controller;

    /**
     * @param menu
     *            menu a quale fa riferimento la classe InputListener
     * @param model
     *            model del menu
     */
    public InputListener(final Menu menu, final ModelMenu model) {
        this.controller = new ControllerMenuImpl(model, menu);
        model.setDifficulty(Difficulty.EASY);
    }

    @Override
    public void keyReleased(final KeyEvent e) {
        switch (e.getKeyCode()) {
        case KeyEvent.VK_UP:
            controller.myChangeSelected(true);
            break;
        case KeyEvent.VK_DOWN:
            controller.myChangeSelected(false);
            break;
        case KeyEvent.VK_ENTER:
            controller.enter();
            break;
        case KeyEvent.VK_ESCAPE:
            controller.escape();
        default:
            break;
        }
    }
}
