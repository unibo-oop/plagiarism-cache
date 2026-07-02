package tmw.controller.audio;

import java.util.Observable;
import tmw.model.audio.AudioMasterImpl;
import tmw.model.audio.AudioSfx;
import tmw.model.audio.AudioTracks;
import tmw.model.world.WorldEvents;

/**
 * Class that manage audio feature in game.
 */
public class AudioControllerImpl implements AudioController {

    private static final AudioMasterImpl AUDIO_MASTER = new AudioMasterImpl();
    private static final double DEFAULT_VALUE = 0.1;

    /**
     * {@inheritDoc}
     */
    @Override
    public AudioMasterImpl getAudio() {
        return AUDIO_MASTER;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final Observable o, final Object arg) {

        if (arg.equals(WorldEvents.BULLET_HIT)) {
            this.getAudio().playSFX(AudioSfx.BULLET_HIT);
        }

        if (arg.equals(WorldEvents.ITEMPICK)) {
            this.getAudio().playSFX(AudioSfx.ITEMPICK);
        }

        if (arg.equals(WorldEvents.ENEMY_KILLED)) {
            this.getAudio().playSFX(AudioSfx.ENEMY_KILLED);
        }

        if (arg.equals(WorldEvents.OBSTACLE_DESTROYED)) {
            this.getAudio().playSFX(AudioSfx.OBSTACLE_DESTROYED);
        }

        if (arg.equals(WorldEvents.PLAYER_DEATH)) {
            this.getAudio().playSFX(AudioSfx.PLAYER_DEATH);
        }

        if (arg.equals(WorldEvents.SHOOT)) {
            this.getAudio().playSFX(AudioSfx.SHOOT);
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void muteVolume(final boolean bool) {
        if (bool) {
            AUDIO_MASTER.mutePlayer();
        } else {
            AUDIO_MASTER.unMute();
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setVolume(final double value) {
        AUDIO_MASTER.setVoulme(value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBackgroudMusic(final AudioTracks track) {
        AUDIO_MASTER.playBackMusic(track);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getDefaultVolume() {
        return DEFAULT_VALUE;
    }

}
