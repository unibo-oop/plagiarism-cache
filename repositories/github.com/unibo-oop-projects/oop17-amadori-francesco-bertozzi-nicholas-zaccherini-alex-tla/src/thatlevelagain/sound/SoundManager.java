package thatlevelagain.sound;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 
 */
public final class SoundManager {
    private static List<Sound> loader = new LinkedList<>();

    /**
     * 
     */
    private SoundManager() {
    }

    /**
     * Inizialize all soound inside the enum SoundPath.
     */
    public static void init() {
        final List<String> soundList = Stream.of(SoundPath.values())
                    .map(SoundPath::getPathSound)
                    .collect(Collectors.toList());
        soundList.forEach(path -> {
            loader.add(new Sound(path));
        });
    }

    /**
     * @return the list of all Sound.
     */
    public static List<Sound> getListLoader() {
        return loader;
    }

}
