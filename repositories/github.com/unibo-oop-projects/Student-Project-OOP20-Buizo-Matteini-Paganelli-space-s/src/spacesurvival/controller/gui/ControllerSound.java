package spacesurvival.controller.gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.swing.JSlider;
import spacesurvival.controller.gui.command.SwitchGUI;
import spacesurvival.controller.sound.CallerAudio;
import spacesurvival.model.gui.EngineGUI;
import spacesurvival.model.gui.Visibility;
import spacesurvival.model.gui.sound.EngineSound;
import spacesurvival.model.gui.sound.StateSlider;
import spacesurvival.model.gui.sound.TypeUnitSound;
import spacesurvival.model.sound.category.SoundEffect;
import spacesurvival.model.sound.category.SoundLoop;
import spacesurvival.utilities.SoundUtils;
import spacesurvival.utilities.path.SoundPath;
import spacesurvival.utilities.SoundType;
import spacesurvival.utilities.LinkActionGUI;
import spacesurvival.utilities.CommandAudioType;
import spacesurvival.view.GUI;
import spacesurvival.view.sound.GUISound;
import spacesurvival.view.sound.utilities.ButtonSliderType;
import spacesurvival.view.utilities.FactoryGUIs;

/**
 * Implements the controller for the sound GUI.
 */
public class ControllerSound implements ControllerGUI {
    private final GUISound gui;
    private final EngineSound engine;

    private final SwitchGUI switchGUI;
    private final CallerAudio callerAudioLoop;
    private final List<CallerAudio> callerAudioEffect;

    /**
     * Create a control sound GUI with its model and view.
     * @param engine of model.
     * @param gui of view.
     */
    public ControllerSound(final EngineSound engine, final GUISound gui) {
        this.engine = engine;
        this.gui = gui;
        this.switchGUI = new SwitchGUI(this.engine, this.gui);

        this.callerAudioLoop = new CallerAudio(new SoundLoop());
        this.callerAudioEffect = new ArrayList<>();

        SoundType.EFFECT.getSoundPaths().forEach(path -> 
            this.callerAudioEffect.add(new CallerAudio(new SoundEffect(path)))
        );

        this.assignSound();
        this.linksCallerAudio();
        this.turn(this.engine.getVisibility());
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
        this.gui.setTextButtonBack(this.engine.getTextBtnBack());
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
     * Assign the model of each sound unit to its view.
     */
    private void assignSound() {
        this.gui.setTypeUnitSound(this.engine.getListTypeUnitSound());
        this.gui.setTitleUnitSound(this.engine.getListTextSlider());
        this.gui.setValueMixerSound(SoundUtils.DEFAULT_VALUE_SOUND);
        this.gui.setIconBtnSwitches(this.engine.getIconStateSounds());
    }

    /**
     * Connect all sound effects to the caller audio effects.
     */
    private void linksCallerAudio() {
        Arrays.stream(TypeUnitSound.values()).forEach(type -> {
            this.setChangeListenerSliderFromType(type);
            this.setActionListenerChangeSwitchSoundFromType(type);
        });
    }

    /**
     * Assign to the unit sound loop, with the connection of the GUI.
     * @param linkActionGUI of link GUI.
     */
    public void setSoundLoop(final LinkActionGUI linkActionGUI) {
        this.callerAudioLoop.setSound(new SoundLoop(linkActionGUI.getSound()));
    }

    /**
     * Assign the command to the sound loop controller.
     * @param cmdAudioLoop is command.
     */
    public void setCmdAudioLoop(final CommandAudioType cmdAudioLoop) {
        this.callerAudioLoop.execute(cmdAudioLoop);
    }

    /**
     * Get callerAudio effect from sound path.
     * @param soundPath of sound.
     * @return CallerAudio with optional.
     */
    public Optional<CallerAudio> getCallerAudioEffectFromSoundPath(final SoundPath soundPath) {
        for (final CallerAudio callerAudio : this.callerAudioEffect) {
            if (callerAudio.getSound().getSoundType().equals(soundPath)) {
                return Optional.of(callerAudio);
            }
        }
        return Optional.empty();
    }

    /**
     * Get value sound loop.
     * @return value sound loop.
     */
    public int getLoopVolume() {
        return this.engine.getValueUnitSound(TypeUnitSound.SLIDER_BACKGROUND);
    }

    /**
     * Get if Unit sound loop is active.
     * @return if unit sound loop is active.
     */
    public boolean isActiveLoopUnitSound() {
        return this.engine.isActiveUnitSound(TypeUnitSound.SLIDER_BACKGROUND);
    }

    /**
     * Set ChangeListener to Slider from TypeUnitSound.
     * @param type for search Slider.
     */
    public void setChangeListenerSliderFromType(final TypeUnitSound type) {
        this.gui.getSliderTypeofMixer(type).ifPresent(slider -> slider.addChangeListener(l -> {
            final Optional<ButtonSliderType> btnType = this.gui.getBtnSwitch(type);
            final JSlider sld = (JSlider) l.getSource();

            this.engine.setValueUnitSound(type, sld.getValue());
            this.engine.setStateUnitSound(type, this.isVolumeZero(type) ? StateSlider.OFF : StateSlider.ON);
            btnType.ifPresent(btn -> FactoryGUIs.setIconJButtonFromRate(btn, this.engine.getEngineImageUnitSound(type)));

            if (type.equals(TypeUnitSound.SLIDER_BACKGROUND)) {
                this.callerAudioLoop.changeVolume(this.engine.getValueUnitSound(type));
            } else if (type.equals(TypeUnitSound.SLIDER_EFFECT)) {
                this.callerAudioEffect.forEach(callerAudioEffect -> callerAudioEffect.changeVolume(this.engine.getValueUnitSound(type)));
            }
        }));
    }

    /**
     * Set ActionListener to button from TypeUnitSound.
     * @param type for search button.
     */
    public void setActionListenerChangeSwitchSoundFromType(final TypeUnitSound type) {
        this.gui.getBtnSwitch(type).ifPresent(btn -> btn.addActionListener(l -> {
            final ButtonSliderType btnType = (ButtonSliderType) l.getSource();

            this.engine.changeStateUnitSound(type);
            FactoryGUIs.setIconJButtonFromRate(btnType, this.engine.getEngineImageUnitSound(type));

            if (type.equals(TypeUnitSound.SLIDER_BACKGROUND)) {
                this.callerAudioLoop.changeVolume(this.getValueIfActive(btnType.getTypeSlider()));
            } else if (type.equals(TypeUnitSound.SLIDER_EFFECT)) {
                this.callerAudioEffect.forEach(callerAudioEffect -> callerAudioEffect.changeVolume(this.getValueIfActive(btnType.getTypeSlider())));
            }
        }));
    }

    /**
     * Check if a unit of sound has zero value.
     * @param type for search unit sound.
     * @return if it has the value zero.
     */
    public boolean isVolumeZero(final TypeUnitSound type) {
        return this.engine.getValueUnitSound(type) == SoundUtils.SOUND_ZERO;
    }

    /**
     * Check if a unit of sound is active.
     * @param typeUnitSound for search unit sound.
     * @return value of unit sound.
     */
    public int getValueIfActive(final TypeUnitSound typeUnitSound) {
        return this.engine.isActiveUnitSound(typeUnitSound) 
                ? this.engine.getValueUnitSound(typeUnitSound) : SoundUtils.SOUND_ZERO;
    }

    /**
     * Assign sound loop if the sound loop has changed from LinkActionGUI.
     * @param linkActionGUI to understand the song of the GUI.
     */
    public void checkChangeSoundLoop(final LinkActionGUI linkActionGUI) {
        if (this.isNewLoopSound(linkActionGUI)) {
            this.changeNewLoopSound(linkActionGUI);
        }
    }

    /**
     * Check if the sound loop has changed from LinkActionGUI.
     * @param linkActionGUI to understand the song of the GUI.
     * @return boolean if new sound loop.
     */
    public boolean isNewLoopSound(final LinkActionGUI linkActionGUI) {
        return this.callerAudioLoop.isNewSound(linkActionGUI.getSound());
    }

    /**
     * Change sound loop from LinkActionGUI.
     * @param linkActionGUI to understand the song of the GUI.
     */
    public void changeNewLoopSound(final LinkActionGUI linkActionGUI) {
        this.callerAudioLoop.execute(CommandAudioType.AUDIO_OFF);
        this.callerAudioLoop.setSound(new SoundLoop(linkActionGUI.getSound()));
        this.callerAudioLoop.changeVolume(this.isActiveLoopUnitSound() ? this  .getLoopVolume() : SoundUtils.SOUND_ZERO);
        this.callerAudioLoop.execute(CommandAudioType.AUDIO_ON);
    }

    /**
     * Describes the control via its engine and view and callersAudioEffect and callerAudioLoop.
     */
    @Override
    public String toString() {
        return "CtrlSound [gui=" + gui + ", engine=" + engine + ", switchGUI=" + switchGUI + ", callerAudioLoop="
                + callerAudioLoop + ", callerAudioEffect=" + callerAudioEffect + "]";
    }
}
