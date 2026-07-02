package spacesurvival.utilities;

import spacesurvival.utilities.path.SoundPath;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Enumeration representing the type of sounds.
 *
 */
public enum SoundType {
    /**
     * Type of sound loop, when the clip is finished it restarts. 
     */
    LOOP,
    /**
     * Type of sound effect, no restarts when finished.
     */
    EFFECT;

    /**
     * Returns a soundPath list made up of all that have the same type as the passed one.
     * @return a List.
     */
    public List<SoundPath> getSoundPaths() {
        return Stream.of(SoundPath.values()).filter(path -> path.getType().equals(this)).collect(Collectors.toList());
    }
}
