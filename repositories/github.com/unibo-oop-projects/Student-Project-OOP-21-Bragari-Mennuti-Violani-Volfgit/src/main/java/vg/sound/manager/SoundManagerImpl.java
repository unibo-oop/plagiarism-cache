package vg.sound.manager;

import vg.sound.manager.background.SoundBackgroundManager;
import vg.sound.manager.background.SoundBackgroundManagerImpl;
import vg.sound.manager.effect.SoundEffectManager;
import vg.sound.manager.effect.SoundEffectManagerImpl;

public class SoundManagerImpl implements SoundManager {
    private final SoundEffectManager soundEffectManager;
    private final SoundBackgroundManager soundBackgroundManager;

    public SoundManagerImpl() {
        this.soundEffectManager = new SoundEffectManagerImpl();
        this.soundBackgroundManager = new SoundBackgroundManagerImpl();
    }

    @Override
    public void playEffect(final ESoundEffect eSoundBackground) {
        this.soundEffectManager.play(eSoundBackground);
    }

    @Override
    public void playBackground(final ESoundBackground eSoundBackground) {
        this.soundBackgroundManager.play(eSoundBackground);
    }

    @Override
    public void stopBackground() {
        this.soundBackgroundManager.stop();
    }
}
