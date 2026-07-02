package vg.sound.manager;

public interface SoundManager {
    void playEffect(ESoundEffect eSoundBackground);
    void playBackground(ESoundBackground eSoundBackground);

    void stopBackground();
}
