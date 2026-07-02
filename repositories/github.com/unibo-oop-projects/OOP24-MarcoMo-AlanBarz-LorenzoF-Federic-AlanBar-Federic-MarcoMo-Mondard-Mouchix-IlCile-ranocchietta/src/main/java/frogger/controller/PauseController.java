package frogger.controller;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import frogger.common.GameState;
import frogger.common.input.MouseInput;
import frogger.model.implementations.MenuFactoryImpl;
import frogger.model.interfaces.Menu;
import frogger.model.interfaces.MenuFactory;
import frogger.view.GameScene;
import frogger.view.PausePanel;

/**
 * {@inheritDoc}.
 */
public class PauseController extends AbstractController implements MenuController {
    private final MenuFactory menuFactory = new MenuFactoryImpl();
    private PausePanel scenePanel;
    private Menu menu;
    private final Controller gc;

    private final MouseInput mouseInput = new MouseInput(this);

    /**
     * Initialize the field.
     * @param gc the game controller.
     */
    public PauseController(final Controller gc) {
        this.gc = gc;
    }

    /**
     * Return the game controller.
     * @return the game controller
     */
    public Controller getGameController() {
        return this.gc;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init(final GameScene gameScene) {
        menu = menuFactory.pauseMenu();
        scenePanel = new PausePanel();
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
    protected boolean loopCondition() {
        return GameState.getState() == GameState.PAUSE;
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

    /**
     * {@inheritDoc}
     */
    @Override
    public Menu getMenu() {
        return this.menu;
    }

}
