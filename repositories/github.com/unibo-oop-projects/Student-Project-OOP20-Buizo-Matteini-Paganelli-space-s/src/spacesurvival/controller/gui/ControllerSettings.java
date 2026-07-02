package spacesurvival.controller.gui;

import spacesurvival.controller.gui.command.SwitchGUI;
import spacesurvival.model.gui.EngineGUI;
import spacesurvival.model.gui.Visibility;
import spacesurvival.model.gui.settings.EngineSettings;
import spacesurvival.model.gui.settings.SkinSpaceShip;
import spacesurvival.utilities.LinkActionGUI;
import spacesurvival.view.GUI;
import spacesurvival.view.settings.GUISettings;

import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * Implements the controller for the settings GUI.
 */
public class ControllerSettings implements ControllerGUI {
    private final GUISettings gui;
    private final EngineSettings engine;

    private final SwitchGUI switchGUI;

    /**
     * Create a control settings GUI with its model and view.
     * @param engine of model.
     * @param gui of view.
     */
    public ControllerSettings(final EngineSettings engine, final GUISettings gui) {
        this.gui = gui;
        this.engine = engine;
        this.switchGUI = new SwitchGUI(this.engine, this.gui);

        this.assignSettings();
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
        this.gui.setUnitsTitle(this.engine.getListTextUnit());
        this.gui.setTextBtnBack(this.engine.getTextBtnBack());
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
     * Assign settings from model to view.
     */
    private void assignSettings() {
        this.gui.setSkinSpaceShip(this.engine.getEngineSkinShip());
        this.gui.getBtnUnitSkin().forEach(btn -> btn.addActionListener(this.changeSkin()));
    }

    /**
     * ActionListener that change skin.
     * 
     * @return ActionListener.
     */
    private ActionListener changeSkin() {
        return e -> {
            final JButton btn = (JButton) e.getSource();
            this.changeSkinWithDir(btn.getText());
            this.gui.setSkinSpaceShip(this.engine.getEngineSkinShip());
        };
    }

    /**
     * Change skin towards one direction.
     * 
     * @param direction for change skin.
     */
    public void changeSkinWithDir(final String direction) {
        if (direction.contentEquals(EngineSettings.DIR_SX)) {
            this.engine.changeSkinSx();
        } else {
            this.engine.changeSkinDx();
        }
    }

    /**
     * Get current Skin spaceShip.
     * 
     * @return SkinSpaceShip.
     */
    public SkinSpaceShip getCurrentSkin() {
        return this.engine.getSkinShip();
    }
}


