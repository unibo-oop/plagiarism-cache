package spacesurvival.view.settings.concrete;

import spacesurvival.model.EngineImage;
import spacesurvival.utilities.LinkActionGUI;
import spacesurvival.view.AbstractGUI;
import spacesurvival.view.settings.GUISettings;
import spacesurvival.view.settings.utilities.PanelSkin;
import spacesurvival.view.utilities.ButtonLink;
import spacesurvival.view.utilities.FactoryGUIs;

import java.awt.Color;
import java.awt.Font;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;

/**
 * Implement every element the settings GUI must have.
 */
public class ConcreteSettingsGUI extends AbstractGUI implements GUISettings {
    private static final long serialVersionUID = 1L;

    private final JLabel lbTitle;
    private final PanelSkin panelSkin;
    private final ButtonLink btnBack;

    /**
     * Constructor of all settings GUI items.
     */
    public ConcreteSettingsGUI() {
        super();
        this.lbTitle = new JLabel();
        this.panelSkin = new PanelSkin();
        this.btnBack = new ButtonLink();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final List<ButtonLink> getBtnActionLinks() {
        return List.of(this.btnBack);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final List<JButton> getBtnUnitSkin() {
        return List.of(this.panelSkin.getBtSX(), this.panelSkin.getBtDX());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setUnitsTitle(final List<String> listName) {
        final int i = 0;
        this.panelSkin.setLabelTitle(listName.get(i));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setTextBtnBack(final String nameBtnBack) {
        this.btnBack.setText(nameBtnBack);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setSkinSpaceShip(final EngineImage imageEngine) {
        this.panelSkin.setPnImage(imageEngine.getPath());
        this.panelSkin.setRateImg(imageEngine.getScaleOf(), imageEngine.getRespectTo());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setBtnBackID(final LinkActionGUI mainAction, final LinkActionGUI action) {
        this.btnBack.setCurrentLink(mainAction);
        this.btnBack.setNextLink(action);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setForegroundGUI(final Color color) {
        this.lbTitle.setForeground(color);
        this.panelSkin.setAllForeground(color);
        this.btnBack.setForeground(color);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setFontTitleGUI(final Font font) {
        this.lbTitle.setFont(font);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setFontTitleUnit(final Font font) {
        this.panelSkin.setFontLabelTitle(font);
        this.btnBack.setFont(font);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setFontGUI(final Font font) {
        this.panelSkin.setFontButtons(font);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setTitleGUI(final String title) {
        this.lbTitle.setText(title);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setTransparentComponent() {
        this.panelSkin.setTransparentButton();
        FactoryGUIs.setTransparentJButton(this.btnBack);
    }

    /**
     * Get label for title setting GUI.
     * 
     * @return label for title.
     */
    public final JLabel getLabeTitle() {
        return this.lbTitle;
    }

    /**
     * Get back link button.
     * @return ButtonLink for link back.
     */
    public final ButtonLink getBtnBack() {
        return this.btnBack;
    }

    /**
     * Get unit settings of skin.
     * @return PanelSkin of unit settings.
     */
    public final PanelSkin getPanelSkin() {
        return this.panelSkin;
    }

}
