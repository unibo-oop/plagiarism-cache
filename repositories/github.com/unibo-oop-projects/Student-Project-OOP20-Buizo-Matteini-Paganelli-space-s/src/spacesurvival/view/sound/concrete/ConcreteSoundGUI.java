package spacesurvival.view.sound.concrete;

import spacesurvival.model.gui.sound.EngineSound;
import spacesurvival.model.gui.sound.TypeUnitSound;
import spacesurvival.utilities.LinkActionGUI;
import spacesurvival.view.AbstractGUI;
import spacesurvival.view.sound.GUISound;
import spacesurvival.view.sound.utilities.ButtonSliderType;
import spacesurvival.view.sound.utilities.MixerSound;
import spacesurvival.view.utilities.ButtonLink;
import spacesurvival.view.sound.utilities.SliderType;

import java.awt.Color;
import java.awt.Font;
import java.util.List;
import java.util.Optional;

import javax.swing.JLabel;

/**
 * Implement every element the sound GUI must have.
 */
public class ConcreteSoundGUI extends AbstractGUI implements GUISound {
    private static final long serialVersionUID = 2236900141738077423L;

    private final JLabel lbTitle;
    private final MixerSound mixerSound;
    private final ButtonLink btnBack;

    /**
     * builds the sound GUI with all the components.
     */
    public ConcreteSoundGUI() {
        super();
        this.lbTitle = new JLabel();
        this.mixerSound = new MixerSound();
        this.btnBack = new ButtonLink();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ButtonLink> getBtnActionLinks() {
        return List.of(this.btnBack);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTextButtonBack(final String nameBtnBack) {
        this.btnBack.setText(nameBtnBack);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTypeUnitSound(final List<TypeUnitSound> listName) {
        this.mixerSound.setTypeUnitSound(listName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTitleUnitSound(final List<String> listTitle) {
        this.mixerSound.setTitleSlider(listTitle);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<SliderType> getSlidersSound() {
        return this.mixerSound.getSliders();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<SliderType> getSliderTypeofMixer(final TypeUnitSound typeUnitSound) {
        return this.mixerSound.getSliderType(typeUnitSound);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ButtonSliderType> getBtnSwitch(final TypeUnitSound typeUnitSound) {
        return this.mixerSound.getBtnSwitch(typeUnitSound);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setValueMixerSound(final int value) {
        this.mixerSound.setValueAllSlidersSound(value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setActionBtnBack(final LinkActionGUI actionMain, final LinkActionGUI action) {
        this.btnBack.setCurrentLink(actionMain);
        this.btnBack.setNextLink(action);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ButtonSliderType> getBtnSwitches() {
        return this.mixerSound.getBtnSwitches();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setIconBtnSwitches(final List<String> paths) {
        this.mixerSound.setIconBtnSwitches(paths, (int) EngineSound.RECTANGLE.getWidth());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setForegroundGUI(final Color color) {
        this.lbTitle.setForeground(color);
        this.mixerSound.setAllForeground(color);
        this.btnBack.setForeground(color);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFontTitleGUI(final Font font) {
        this.lbTitle.setFont(font);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFontGUI(final Font font) {
        this.mixerSound.setFontTitleSound(font);
        this.btnBack.setFont(font);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFontSpacingSlider(final Font font) {
        this.mixerSound.setFontSliderSound(font);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTitleGUI(final String title) {
        this.lbTitle.setText(title);
    }

    /**
     * Get label title for GUI.
     * @return label for title.
     */
    public JLabel getLabelTitle() {
        return this.lbTitle;
    }

    /**
     * Get back link button.
     * @return ButtonLink for link back.
     */
    public ButtonLink getBtnBack() {
        return this.btnBack;
    }

    /**
     * Get MixerSound that contain all unit sound.
     * @return MixerSound.
     */
    public MixerSound getMixerSound() {
        return this.mixerSound;
    }

    /**
     * Return descriptions of menu GUI concrete.
     */
    @Override
    public String toString() {
        return "GUISoundConcrete [lbTitle=" + lbTitle + ", mixerSound=" + mixerSound + ", btnBack=" + btnBack + "]";
    }

}
