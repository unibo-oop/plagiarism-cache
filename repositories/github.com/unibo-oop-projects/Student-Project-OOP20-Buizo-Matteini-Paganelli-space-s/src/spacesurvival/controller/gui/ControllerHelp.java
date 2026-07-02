package spacesurvival.controller.gui;

import spacesurvival.controller.gui.command.SwitchGUI;
import spacesurvival.model.gui.EngineGUI;
import spacesurvival.model.gui.Visibility;
import spacesurvival.model.gui.help.EngineHelp;
import spacesurvival.utilities.LinkActionGUI;
import spacesurvival.view.GUI;
import spacesurvival.view.help.GUIHelp;

/**
 * Implements the controller for the help GUI.
 */
public class ControllerHelp implements ControllerGUI {
    private final GUIHelp gui;
    private final EngineHelp engine;

    private final SwitchGUI switchGUI;

    /**
     * Create a control help GUI with its model and view.
     * 
     * @param engine of model.
     * @param gui of view.
     */
    public ControllerHelp(final EngineHelp engine, final GUIHelp gui) {
        this.engine = engine;
        this.gui = gui;
        this.switchGUI = new SwitchGUI(this.engine, this.gui);

        this.switchGUI.turn(this.engine.getVisibility());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void assignLinks() {
        this.gui.setMainAction(this.engine.getMainLink());
        this.gui.setActionBtnBack(this.engine.getMainLink(), this.engine.getBackLink());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void assignTexts() {
        this.gui.setTitleGUI(this.engine.getTitle());
        this.gui.setTextUnit(this.engine.getListTitleUnits());
        this.gui.setBtnText(this.engine.getListTextButtons());
        this.engine.getListTitleUnits().forEach(nameUnit ->
                this.gui.addTextAndIconInUnit(nameUnit, this.engine.getPathIconUnit(nameUnit)));
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

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "CtrlHelp{" 
                + "gui=" + gui 
                + ", engine=" + engine 
                + ", switchGUI=" + switchGUI + '}';
    }
}
