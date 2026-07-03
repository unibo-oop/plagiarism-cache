package com.jlearn.controller.sound;

import java.net.URISyntaxException;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

/**
 * The {@link Thread} for the AudioAgent.
 */
public class AudioAgentImpl implements AudioAgent {
    private MediaPlayer mediaPlayer;
    private boolean     isMuted;
    private boolean     playing;

    private static final Logger LOG = Logger.getLogger(AudioAgentImpl.class);

    @Override
    public String toString() {
        return "AudioAgentImpl [mediaPlayer=" + this.mediaPlayer + ", isMuted=" + this.isMuted + ", playing="
                + this.playing + "]";
    }

    /**
     * The {@link AudioAgentImpl} constructor.
     */
    public AudioAgentImpl() {
        LOG.setLevel(Level.WARN);
        LOG.debug("Create audio thread");
    }

    /**
     * The {@link AudioAgentImpl} constructor.
     *
     * @param filename
     *            the {@link String} filename
     */
    public AudioAgentImpl(final String filename) {
        LOG.setLevel(Level.OFF);
        LOG.debug("Create audio thread");

        try {
            this.mediaPlayer = new MediaPlayer(new Media(this.getClass()
                    .getResource(filename)
                    .toURI()
                    .toString()));
        } catch (final URISyntaxException e) {
            e.printStackTrace();
        }
        this.mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
    }

    @Override
    public void changeTrack(final String filename) {
        if (this.mediaPlayer != null) {
            this.stopPlayer();
        }

        try {

            this.mediaPlayer = new MediaPlayer(new Media(this.getClass()
                    .getResource(filename)
                    .toURI()
                    .toString()));
        } catch (final URISyntaxException e) {
            LOG.warn("Ivalid Uri of Media", e);
        }
    }

    @Override
    public void setCycleCount() {
        this.mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
    }

    @Override
    public void pausePlayer() {
        this.mediaPlayer.pause();
        this.playing = false;
    }

    @Override
    public void startPlayer() {
        this.mediaPlayer.play();
        this.playing = true;
    }

    @Override
    public void cleanTrack() {
        this.mediaPlayer = null;
        this.playing = false;
    }

    @Override
    public void stopPlayer() {
        this.mediaPlayer.stop();
    }

    @Override
    public Duration getStartTime() {
        return this.mediaPlayer.getStartTime();
    }

    @Override
    public Duration getStopTime() {
        return this.mediaPlayer.getStopTime();
    }

    @Override
    public boolean canStart() {
        return this.mediaPlayer != null;

    }

    @Override
    public Duration getCurrentTime() {
        return this.mediaPlayer.getCurrentTime();
    }

    @Override
    public Duration getRemainingTime() {
        return Duration.seconds(this.getAudioDuration().toSeconds() - this.getCurrentTime().toSeconds());
    }

    @Override
    public Duration getAudioDuration() {
        return this.mediaPlayer.getTotalDuration();
    }

    @Override
    public void setVolume(final double volume) {
        this.mediaPlayer.setVolume(volume);
    }

    @Override
    public void toggleMute() {
        if (this.isMuted) {
            this.mediaPlayer.setMute(false);
        } else {
            this.mediaPlayer.setMute(true);
        }
        this.isMuted = !this.isMuted;
    }

    @Override
    public boolean isPlaying() {
        return this.playing;
    }

}
