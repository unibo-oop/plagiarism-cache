package view.audio;

import java.util.Objects;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;

/**
 * Implementation of the interface AudioManager.
 */
public class AudioManagerImpl implements AudioManager {

    private MediaPlayer mediaPlayer;
    private Volume volume;
    private boolean ready;
    private boolean firstPlay;
    private boolean requestedPlay;
    private boolean isOff;
    private AudioTrack audioTrack;

    /**
     * It creates a new manager for each audio track.
     */
    public AudioManagerImpl() {
        this.volume = Volume.HIGH;
        this.ready = false;
        this.firstPlay = true;
        this.requestedPlay = false;
        this.isOff = true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void play(final AudioTrack music) {
        this.mediaPlayer = new MediaPlayer(new Media(ClassLoader.getSystemResource(music.getPath()).toExternalForm()));
        this.mediaPlayer.setVolume(this.volume.getVolume());
        this.audioTrack = music;
        this.firstPlay = false;
        this.isOff = false;
        if (music.getType() == AudioType.MUSIC) {
            this.mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        }
        this.mediaPlayer.setOnReady(() -> {
            if (this.requestedPlay) {
                this.mediaPlayer.play();
            }
            this.ready = true;
        });
        if (this.ready) {
            this.mediaPlayer.play();
        } else {
            this.requestedPlay = true;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stop() {
        if (Objects.nonNull(mediaPlayer) && this.mediaPlayer.getStatus() == Status.PLAYING) {
            this.mediaPlayer.stop();
        }
        this.requestedPlay = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setVolume(final Volume newVolume) {
        switch (newVolume) {
            case MUTE:
                this.mediaPlayer.setMute(true);
                this.volume = Volume.MUTE;
                break;
            case LOW:
                this.mediaPlayer.setMute(false);
                this.mediaPlayer.setVolume(newVolume.getVolume());
                this.volume = newVolume;
                break;
            case MEDIUM:
                this.mediaPlayer.setMute(false);
                this.mediaPlayer.setVolume(newVolume.getVolume());
                this.volume = newVolume;
                break;
            case HIGH:
                this.mediaPlayer.setMute(false);
                this.mediaPlayer.setVolume(newVolume.getVolume());
                this.volume = newVolume;
                break;
            default:
                this.mediaPlayer.setMute(false);
                this.mediaPlayer.setVolume(Volume.HIGH.getVolume());
                this.volume = Volume.HIGH;
                break;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Volume getVolume() {
        return this.volume;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isFirstPlay() {
        return this.firstPlay;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isOff() {
        return this.isOff;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setOff(final boolean newValue) {
        this.isOff = newValue;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AudioTrack getAudioTrack() {
        return this.audioTrack;
    }

}
