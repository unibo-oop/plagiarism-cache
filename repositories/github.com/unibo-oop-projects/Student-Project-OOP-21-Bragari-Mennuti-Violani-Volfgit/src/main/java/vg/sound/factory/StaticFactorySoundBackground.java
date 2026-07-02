package vg.sound.factory;

import vg.sound.Sound;
import vg.sound.SoundBackgroundImpl;
import vg.utils.path.PathSound;

public class StaticFactorySoundBackground {

    public static Sound createStart() {
        return new SoundBackgroundImpl(PathSound.BACKGROUND_START);
    }
}
