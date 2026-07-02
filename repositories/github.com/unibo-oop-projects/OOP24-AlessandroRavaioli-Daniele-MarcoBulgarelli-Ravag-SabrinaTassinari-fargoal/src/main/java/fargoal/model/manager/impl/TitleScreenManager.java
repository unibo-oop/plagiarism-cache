package fargoal.model.manager.impl;

import java.awt.Color;
import java.awt.Font;

import fargoal.controller.input.api.MenuInputComponent;
import fargoal.model.core.GameEngine;
import fargoal.model.manager.api.AbstractMenuManager;
import fargoal.model.manager.api.MatchType;
import fargoal.view.api.Renderer;
import fargoal.view.api.View;
import fargoal.view.impl.SwingRendererMiddle;
import fargoal.view.impl.SwingRendererTop;
import fargoal.view.impl.SwingView;

/**
 * Class that work and implements methods to allow the Title screen
 * to work correctly.
 */
public class TitleScreenManager extends AbstractMenuManager {

    private static final int NUMBER_OF_OPTIONS = 3;
    private static final int POSSIBILITIES_DIVISOR_WIDTH = 50;
    private static final int POSSIBILITIES_DIVISOR_HEIGHT = 7;
    private static final int GAME_START_MULTIPLIER_WIDTH = 21;
    private static final int GAME_EXIT_MULTIPLIER_WIDTH = 23;
    private static final int TITLE_DIVISION_WIDTH = 5;
    private static final int DIVISOR_FONT_TOP_HEIGHT = 4;
    private static final int DIVISOR_FONT_MIDDLE_HEIGHT = 100;
    private static final int DEFAULT_VALUE = 1;
    private static final int EXIT_HEIGHT = 5;

    private Renderer menu;
    private Renderer title;
    private boolean timeToQuit;
    private boolean start;
    private MatchType length;

    /**
     * Constructor that set all the local fields to the starting values.
     * 
     * @param engine - to get the priority in the scene
     */
    public TitleScreenManager(final GameEngine engine) {
        super(engine, new MenuInputComponent());
        this.timeToQuit = false;
        this.start = false;
        createRenderers(engine.getView());
    }

    /** {@inheritDoc} */
    @Override
    public void update(final GameEngine engine) {
        if (timeToQuit) {
            engine.stop();
        } else if (start) {
            setSceneManager(engine);
        }
        super.update(engine);
        this.menu.render();
        this.title.render();
    }

    /** {@inheritDoc} */
    @Override
    public void setSceneManager(final GameEngine engine) {
        engine.setSceneManager(new FloorManagerImpl(engine, this.length));
    }

    /** {@inheritDoc} */
    @Override
    public void select() {
        if (this.getSelected() == 1) {
            this.start = true;
            this.length = MatchType.NORMAL;
        } else if (this.getSelected() == 2) {
            this.start = true;
            this.length = MatchType.SHORT;
        } else {
            this.timeToQuit = true;
        }
    }

    /**
     * Method that create and set the renderer of the top and middle part about
     * what they have to display.
     * 
     * @param view - the view of entire game
     */
    private void createRenderers(final View view) {
        final SwingView sView = (SwingView) view;
        this.menu = new SwingRendererMiddle(g2d -> {
                g2d.setFont(new Font("Arial", Font.BOLD, sView.getFrame().getBounds().height * 3 / DIVISOR_FONT_MIDDLE_HEIGHT));
                g2d.setColor((this.getSelected() == 1) ? Color.cyan : Color.red);
                g2d.drawString("START GAME",
                        sView.getMapWidth() * GAME_START_MULTIPLIER_WIDTH / POSSIBILITIES_DIVISOR_WIDTH,
                        sView.getMapHeight() * 1 / POSSIBILITIES_DIVISOR_HEIGHT);
                g2d.setColor((this.getSelected() == 2) ? Color.cyan : Color.red);
                g2d.drawString("SHORT GAME",
                        sView.getMapWidth() * GAME_START_MULTIPLIER_WIDTH / POSSIBILITIES_DIVISOR_WIDTH,
                        sView.getMapHeight() * 3 / POSSIBILITIES_DIVISOR_HEIGHT);
                g2d.setColor(this.getSelected() == 3 ? Color.cyan : Color.red);
                g2d.drawString("EXIT",
                        sView.getMapWidth() * GAME_EXIT_MULTIPLIER_WIDTH / POSSIBILITIES_DIVISOR_WIDTH,
                        sView.getMapHeight() * EXIT_HEIGHT / POSSIBILITIES_DIVISOR_HEIGHT);
        }, view);
        this.title = new SwingRendererTop(g2d -> {
            g2d.setFont(new Font("Arial", Font.BOLD, sView.getEventPanel().getBounds().height * 1 / DIVISOR_FONT_TOP_HEIGHT));
            g2d.setColor(Color.WHITE);
            g2d.drawString("FARGOAL",
                    sView.getMapWidth() * 2 / TITLE_DIVISION_WIDTH,
                    sView.getEventPanel().getBounds().height / 2);
        }, view);
    }

    /** {@inheritDoc} */
    @Override
    public boolean loopCondition() {
        return this.getSelected() < DEFAULT_VALUE || this.getSelected() > NUMBER_OF_OPTIONS;
    }

    /** {@inheritDoc} */
    @Override
    public void loopSelected() {
        this.setSelected(this.getSelected() < DEFAULT_VALUE ? NUMBER_OF_OPTIONS : DEFAULT_VALUE);
    }
}
