package spacesurvival.view.sound.utilities;

import spacesurvival.model.gui.sound.EngineSound;
import spacesurvival.model.gui.sound.TypeUnitSound;
import spacesurvival.view.utilities.FactoryGUIs;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.JPanel;

/**
 * Implement a sound mixer that has a list of sound units by incorporating them into a JPanel.
 */
public class MixerSound extends JPanel {
    private static final long serialVersionUID = 1L;
    private final List<UnitSound> sdlSounds;

    /**
     * Builds a mixer sound by initializing its components and graphics's components.
     */
    public MixerSound() {
        super(new GridBagLayout());
        super.setOpaque(false);
        this.sdlSounds = Stream.generate(UnitSound::new)
                .limit(EngineSound.N_UNIT_SOUND).collect(Collectors.toList());
        this.graphics();
    }

    /**
     * Create graphics's component of the Mixer sound.
     */
    private void graphics() {
        GridBagConstraints limit = FactoryGUIs.createGBConstraintsBase();
        super.add(FactoryGUIs.getJComponentEmpty());
        for (final UnitSound unit : this.sdlSounds) {
            super.add(unit, limit);
            limit.gridy++;
        }
    }

    /**
     * Set font title's unit sound.
     * @param font for title's unit sound.
     */
    public void setFontTitleSound(final Font font) {
        this.sdlSounds.forEach(sound -> sound.setFontTitleUnit(font));
    }

    /**
     * Set font slider's unit sound.
     * @param font for slider's unit sound.
     */
    public void setFontSliderSound(final Font font) {
        this.sdlSounds.forEach(sound -> sound.setFontSlider(font));
    }

    /**
     * Set to all the title to the sliders.
     * @param titleSlider is list of title for sliders.
     */
    public void setTitleSlider(final List<String> titleSlider) {
        int i = 0;
        for (final UnitSound unit : this.sdlSounds) {
           unit.setLbTitle(titleSlider.get(i++));
        }
    }

    /**
     * Set to all the TypeUnitSound to the sliders.
     * @param typeSlider is list of TypeUnitSound for sliders.
     */
    public void setTypeUnitSound(final List<TypeUnitSound> typeSlider) {
        int i = 0;
        for (final UnitSound unit : this.sdlSounds) {
            unit.setType(typeSlider.get(i++));
        }
    }

    /**
     * Set foreground color.
     * @param color for foreground.
     */
    public void setAllForeground(final Color color) {
        this.sdlSounds.forEach(sound -> sound.setForegroundUnit(color));
    }

    /**
     * Get all Slider from mixer.
     * @return List of SliderType.
     */
    public List<SliderType> getSliders() {
        return this.sdlSounds.stream().map(UnitSound::getSliderSound)
                .collect(Collectors.toList());
    }

    /**
     * Set value all unit sound from mixer.
     * @param value for all unit sound.
     */
    public void setValueAllSlidersSound(final int value) {
        this.sdlSounds.forEach(sound -> sound.setValueSliderSound(value));
    }

    /**
     * Get all ButtonSliderType from mixer.
     * @return List of ButtonSliderType
     */
    public List<ButtonSliderType> getBtnSwitches() {
        return this.sdlSounds.stream().map(UnitSound::getBtnSwitch).collect(Collectors.toList());
    }

    /**
     * Set icon to all ButtonSliderType. 
     * @param paths a list of path of image.
     * @param widthScreen for scale of.
     */
    public void setIconBtnSwitches(final List<String> paths, final int widthScreen) {
        final AtomicInteger i = new AtomicInteger();
        this.sdlSounds.forEach(sound -> sound.setIconBtnSwitch(paths.get(i.getAndIncrement()), widthScreen));
    }

    /**
     * Get a slider from the TypeUnitSound if present.
     * @param typeUnitSound for search.
     * @return SliderType if present.
     */
    public Optional<SliderType> getSliderType(final TypeUnitSound typeUnitSound) {
        for (final UnitSound unit : this.sdlSounds) {
            if (typeUnitSound == unit.getType()) {
                return Optional.of(unit.getSliderSound());
            }
        }
        return Optional.empty();
    }

    /**
     * Get a ButtonSliderType from the TypeUnitSound if present.
     * @param typeUnitSound for search.
     * @return ButtonSliderType if present.
     */
    public Optional<ButtonSliderType> getBtnSwitch(final TypeUnitSound typeUnitSound) {
        for (final UnitSound unit : this.sdlSounds) {
            if (typeUnitSound == unit.getType()) {
                return Optional.of(unit.getBtnSwitch());
            }
        }
        return Optional.empty();
    }
}
