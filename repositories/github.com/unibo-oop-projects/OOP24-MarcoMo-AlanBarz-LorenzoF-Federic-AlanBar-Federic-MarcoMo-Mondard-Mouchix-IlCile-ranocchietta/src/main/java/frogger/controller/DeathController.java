package frogger.controller;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import frogger.common.GameState;
import frogger.common.input.MouseInput;
import frogger.model.implementations.MenuFactoryImpl;
import frogger.model.interfaces.Menu;
import frogger.model.interfaces.MenuFactory;
import frogger.view.DeathPanel;
import frogger.view.GameScene;

/**
 * Implementation of the {@link MenuController} interface.
 * Manages the death logic showing the menu whene the game come to an end.
 */
public class DeathController extends AbstractController implements MenuController {
    private DeathPanel scenePanel;
    private final MenuFactory menuFactory = new MenuFactoryImpl();
    private Menu menu;
    private final int score;

    private final MouseInput mouseInput = new MouseInput(this);

    /**
     * Inizialize the fild.
     * @param score the score made in the last match
     */
    public DeathController(final int score) {
        this.score = score;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init(final GameScene gameScene) {
        menu = menuFactory.deathMenu();
        scenePanel = new DeathPanel(score);
        scenePanel.setController(this);
        gameScene.setPanel(scenePanel);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void core() {
        this.getMenu().update();
        this.scenePanel.repaint();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Menu getMenu() {
        return this.menu;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean loopCondition() {
        return GameState.getState() == GameState.DEAD;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MouseMotionListener getMouseMotionListener() {
        return this.mouseInput;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MouseListener getMouseListener() {
        return this.mouseInput;
    }

}
