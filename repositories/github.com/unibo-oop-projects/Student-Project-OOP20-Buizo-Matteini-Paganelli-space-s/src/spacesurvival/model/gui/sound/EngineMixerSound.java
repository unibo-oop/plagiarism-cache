package spacesurvival.model.gui.sound;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Implements a sound mixer model that contains a list of sound unit models.
 */
public class EngineMixerSound {
    private final List<TypeUnitSound> listTypeUnitSound;
    private final Map<TypeUnitSound, EngineUnitSound> mapUnit;

    /**
     * Create a mixer by passing a list of sound unit models.
     * 
     * @param nameUnit
     */
    public EngineMixerSound(final List<TypeUnitSound> nameUnit) {
        this.listTypeUnitSound = nameUnit;
        this.mapUnit = new HashMap<>();
        this.listTypeUnitSound.forEach(name -> this.mapUnit.put(name, new EngineUnitSound()));
    }

    /**
     * Get value of unit sound from TypeUnitSound.
     * 
     * @param typeUnitSound for search unit sound.
     * @return value of unit sound.
     */
    public int getValueSound(final TypeUnitSound typeUnitSound) {
        return this.mapUnit.get(typeUnitSound).getValueSound();
    }

    /**
     * Set value of unit sound from TypeUnitSound.
     * 
     * @param typeUnitSound for search unit sound.
     * @param valueUnit for set unit sound.
     */
    public void setValueSound(final TypeUnitSound typeUnitSound, final int valueUnit) {
        this.mapUnit.get(typeUnitSound).setValueSound(valueUnit);
    }

    /**
     * Get StateSlider of unit sound from TypeUnitSound.
     * 
     * @param typeUnitSound for search StateSlider of unit sound.
     * @return StateSlider of unit sound.
     */
    public StateSlider getStateSound(final TypeUnitSound typeUnitSound) {
        return this.mapUnit.get(typeUnitSound).getStateSlider();
    }

    /**
     * Set StateSlider of unit sound from TypeUnitSound.
     * 
     * @param typeUnitSound for search StateSlider of unit sound.
     * @param stateUnit of unit sound
     */
    public void setStateSound(final TypeUnitSound typeUnitSound, final StateSlider stateUnit) {
        this.mapUnit.get(typeUnitSound).setStateSlider(stateUnit);
    }

    /**
     * Get path of unit sound from TypeUnitSound.
     * 
     * @param typeUnitSound for search unit sound.
     * @return String of path.
     */
    public String getPathIconState(final TypeUnitSound typeUnitSound) {
        return this.mapUnit.get(typeUnitSound).getPathIconState();
    }

    /**
     * Change path of unit sound from TypeUnitSound.
     * 
     * @param typeUnitSound for search unit sound.
     */
    public void changeStateSound(final TypeUnitSound typeUnitSound) {
        final StateSlider state = this.mapUnit.get(typeUnitSound).getStateSlider();
        this.mapUnit.get(typeUnitSound).setStateSlider(state == StateSlider.OFF ? StateSlider.ON : StateSlider.OFF);
    }

    /**
     * Get boolean of StateSlider if present.
     * 
     * @param typeUnitSound for search unit sound.
     * @return boolean
     */
    public boolean isActiveSound(final TypeUnitSound typeUnitSound) {
        return this.mapUnit.get(typeUnitSound).isActiveSlider();
    }

    /**
     * Get a list of of sound unit.
     * 
     * @return List of TypeUnitSound a list of TypeUnitSound
     */
    public List<TypeUnitSound> getListTypeUnitsSound() {
        return this.listTypeUnitSound;
    }

    /**
     * Get a list of text of sound unit.
     * @return List of String of text of sound unit.
     */
    public List<String> getTextUnitsSound() {
        return this.listTypeUnitSound.stream().map(TypeUnitSound::getNameUnitSound).collect(Collectors.toList());
    }

    /**
     * Get a list of path of state of sound unit.
     * @return List of String of path of state of sound unit.
     */
    public List<String> getPathsIconState() {
        return this.listTypeUnitSound.stream()
                .map(this.mapUnit::get)
                .map(EngineUnitSound::getPathIconState)
                .collect(Collectors.toList());
    }

}
