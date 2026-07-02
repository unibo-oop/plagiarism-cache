package spacesurvival.controller.gui;

import spacesurvival.controller.gui.command.SwitchGUI;
import spacesurvival.model.gui.EngineGUI;
import spacesurvival.model.gui.Visibility;
import spacesurvival.model.gui.menu.EngineMenu;
import spacesurvival.utilities.LinkActionGUI;
import spacesurvival.view.GUI;
import spacesurvival.view.menu.GUIMenu;

/**
 * Implements the controller for the menu GUI.
 */
public class ControllerMenu implements ControllerGUI {
    private final GUIMenu gui;
    private final EngineMenu engine;

    private final SwitchGUI switchGUI;

    /**
     * Create a control menu GUI with its model and view.
     * @param engine of model.
     * @param gui of view.
     */
    public ControllerMenu(final EngineMenu engine, final GUIMenu gui) {
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
        this.gui.setBtnActions(this.engine.getMainLink(), this.engine.getLinks());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void assignTexts() {
        this.gui.setTitleGUI(this.engine.getTitleGUI());
        this.gui.setTextButtons(this.engine.getListTextLinks());
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
     * Describes the control via its engine and view.
     */
    @Override
    public String toString() {
        return "CtrlMenu [gui=" + gui + ", engine=" + engine + ", switchGUI=" + switchGUI + "]";
    }

    /**
     * Get text name's Player.
     * @return text of name's player.
     */
    public String getNamePlayer() {
        this.engine.setNamePlayer(this.gui.getTextNamePalyer());
        return this.engine.getNamePlayer();
    }
}
