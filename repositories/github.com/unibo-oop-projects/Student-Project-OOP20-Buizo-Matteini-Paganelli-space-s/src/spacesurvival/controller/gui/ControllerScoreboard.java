package spacesurvival.controller.gui;

import spacesurvival.controller.gui.command.SwitchGUI;
import spacesurvival.model.gui.EngineGUI;
import spacesurvival.model.gui.Visibility;
import spacesurvival.model.gui.scoreboard.EngineScoreboard;
import spacesurvival.utilities.LinkActionGUI;
import spacesurvival.view.GUI;
import spacesurvival.view.scoreboard.GUIScoreboard;

/**
 * Implements the controller for the Scoreboard GUI.
 */
public class ControllerScoreboard implements ControllerGUI {
    private final GUIScoreboard gui;
    private final EngineScoreboard engine;

    private final SwitchGUI switchGUI;

    /**
     * Create a control Scoreboard GUI with its model and view.
     * @param engine of model.
     * @param gui of view.
     */
    public ControllerScoreboard(final EngineScoreboard engine, final GUIScoreboard gui) {
        this.gui = gui;
        this.engine = engine;
        this.switchGUI = new SwitchGUI(this.engine, this.gui);

        this.switchGUI.turn(this.engine.getVisibility());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void assignLinks() {
        this.gui.setMainAction(this.engine.getMainLink());
        this.gui.setBtnBackID(this.engine.getMainLink(), this.engine.getBackLink());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void assignTexts() {
        this.gui.setTitleGUI(this.engine.getTitleGUI());
        this.gui.setTextButtons(this.engine.getListText());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void assignBounds() {
        this.gui.setBounds(this.engine.getRectangle());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LinkActionGUI getMainLink() {
        return this.engine.getMainLink();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GUI getGUI() {
        return this.gui;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EngineGUI getEngine() {
        return this.engine;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isVisibility() {
        return this.engine.isVisible();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void turn(final Visibility visibility) {
        this.switchGUI.turn(visibility);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void changeVisibility() {
        this.switchGUI.changeVisibility();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void closeGUI() {
        this.gui.close();
    }

}
