package controller.audio;

import java.io.BufferedInputStream;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import model.settings.SettingsManager;

/**
 *
 * Piero Sanchi. This class implements an AudioManager.
 *
 */
public final class AudioManagerImpl implements AudioManager {

    private final SettingsManager settings = SettingsManager.getLog();
    private Clip stepClip;
    private Clip evilClip;
    private Clip tadaClip;
    private Clip winClip;
    private Clip wallClip;
    private Clip diceClip;

    private int vol;

    /**
     * constructor of AudioManagerImpl.
     */
    public AudioManagerImpl() {

        try {

            this.stepClip = AudioSystem.getClip();
            this.evilClip = AudioSystem.getClip();
            this.tadaClip = AudioSystem.getClip();
            this.winClip = AudioSystem.getClip();
            this.wallClip = AudioSystem.getClip();
            this.diceClip = AudioSystem.getClip();

            this.stepClip.open(AudioSystem.getAudioInputStream(
                    new BufferedInputStream(this.getClass().getResourceAsStream("/audio/step.wav"))));
            this.evilClip.open(AudioSystem.getAudioInputStream(
                    new BufferedInputStream(this.getClass().getResourceAsStream("/audio/evil.wav"))));
            this.tadaClip.open(AudioSystem.getAudioInputStream(
                    new BufferedInputStream(this.getClass().getResourceAsStream("/audio/tada.wav"))));
            this.winClip.open(AudioSystem.getAudioInputStream(
                    new BufferedInputStream(this.getClass().getResourceAsStream("/audio/winTheme.wav"))));
            this.wallClip.open(AudioSystem.getAudioInputStream(
                    new BufferedInputStream(this.getClass().getResourceAsStream("/audio/wall.wav"))));
            this.diceClip.open(AudioSystem.getAudioInputStream(
                    new BufferedInputStream(this.getClass().getResourceAsStream("/audio/dice.wav"))));
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isAudio() {
        return this.settings.getSettings().isMusicOn();
    }

    @Override
    public void play(final Audio audio) {
        this.startClip(this.whatAudio(audio));
    }

    @Override
    public void setAudio(final Boolean b) {
        this.settings.getSettings().setMusicOn(b);
    }

    @Override
    public void setVolume(final int volume) {
        this.vol = volume;
    }

    private void startClip(final Clip clip) {
        // max 107, min 85
        if (this.settings.getSettings().isMusicOn()) {
            final FloatControl control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            final int volume = this.vol;
            final float range = control.getMinimum();
            final float result = range * (1 - (volume / 100.0f));
            control.setValue(result);
            clip.setFramePosition(0);
            clip.start();
        }
    }

    @Override
    public void stop(final Audio audio) {
        this.stopClip(this.whatAudio(audio));
    }

    private void stopClip(final Clip clip) {
        clip.stop();
    }

    private Clip whatAudio(final Audio audio) {
        switch (audio) {
        case DICE:
            return this.diceClip;
        case EVIL:
            return this.evilClip;
        case TADA:
            return this.tadaClip;
        case WALL:
            return this.wallClip;
        case WIN:
            return this.winClip;
        default:
            return this.stepClip;
        }
    }

}
