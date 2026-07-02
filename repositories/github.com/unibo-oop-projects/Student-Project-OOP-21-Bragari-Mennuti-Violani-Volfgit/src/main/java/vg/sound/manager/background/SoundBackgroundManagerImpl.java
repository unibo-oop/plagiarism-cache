package vg.sound.manager.background;

import vg.sound.Sound;
import vg.sound.factory.StaticFactorySoundBackground;
import vg.sound.manager.ESoundBackground;

public class SoundBackgroundManagerImpl implements SoundBackgroundManager {
    private final Sound backgroundSound;

    public SoundBackgroundManagerImpl() {
        this.backgroundSound = StaticFactorySoundBackground.createStart();
    }

    @Override
    public void play(final ESoundBackground effect) {
        this.backgroundSound.changeSound(effect.getPath());
        this.backgroundSound.play();
    }

    @Override
    public void stop() {
        this.backgroundSound.stop();
    }
}
