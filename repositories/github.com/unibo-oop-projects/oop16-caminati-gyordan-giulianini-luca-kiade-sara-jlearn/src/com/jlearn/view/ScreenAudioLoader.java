package com.jlearn.view;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.jlearn.controller.fileio.FileManager;
import com.jlearn.controller.fileio.FileManagerImpl;
import com.jlearn.controller.sound.AudioAgent;
import com.jlearn.view.screens.FXMLScreens;
import com.jlearn.view.utilities.enums.SoundFX;

import javafx.scene.media.AudioClip;

/**
 * Screen Audio loader that give sound behaviour to {@link FXEnvironment}.
 */
public final class ScreenAudioLoader {
    // SINGLETON
    private static ScreenAudioLoader singleton;
    // FILE MANAGER
    private final FileManager fileManager;
    // CACHE
    private final Map<FXMLScreens, AudioAgent> agents;
    // AUDIO FLAGS
    private boolean isAudioClipMuted;
    private double volume = 1;

    private static final double DELTA_VOLUME = 0.6;

    private static final Logger LOG = Logger.getLogger(ScreenAudioLoader.class);

    private ScreenAudioLoader() {
        this.fileManager = FileManagerImpl.getInstance();
        this.agents = new HashMap<>();
        LOG.setLevel(Level.WARN);
    }

    /**
     * Get the {@link ScreenAudioLoader}.
     *
     * @return The only instance of {@link ScreenAudioLoader}
     */
    public static ScreenAudioLoader getInstance() {
        synchronized (ScreenAudioLoader.class) {
            if (singleton == null) {
                singleton = new ScreenAudioLoader();
            }
        }
        return singleton;
    }

    /**
     * Play low latency audio.
     *
     * @param path
     *            the paht of audio
     */
    public void playLowLatencyAudio(final SoundFX path) {
        if (!this.isAudioClipMuted) {
            AudioClip aud1;
            try {
                aud1 = new AudioClip(this.getClass()
                        .getResource(this.fileManager.getPathAudFx(path))
                        .toURI()
                        .toString());
                aud1.setVolume(this.volume);
                aud1.play();
            } catch (final URISyntaxException e) {
                LOG.error("Wrong Uri : ", e);
            }
        }
    }

    /**
     * Add {@link AudioAgent}.
     *
     * @param screen
     *            the {@link FXMLScreens}
     * @param agent
     *            the {@link AudioAgent}
     */
    public void addAgentSong(final FXMLScreens screen, final AudioAgent agent) {
        agent.setVolume(1 - DELTA_VOLUME);
        this.agents.put(screen, agent);
    }

    /**
     * Get the {@link AudioAgent}.
     *
     * @param screen
     *            the {@link FXMLScreens}
     * @return the {@link AudioAgent}
     */
    public AudioAgent getAgent(final FXMLScreens screen) {
        return this.agents.get(screen);
    }

    /**
     * Play and pause other players.
     *
     * @param screen
     *            the {@link FXMLScreens}
     * @param fromStart
     *            if <code>true</code> plays from start
     */
    public void startAndPauseOtherPlayers(final FXMLScreens screen, final boolean fromStart) {
        final AudioAgent agent = this.agents.get(screen);
        if (fromStart && (agent.getStartTime() != javafx.util.Duration.INDEFINITE)) {
            agent.stopPlayer();
            agent.startPlayer();
        }
        // agent.setVolume(this.volume);
        agent.startPlayer();
        this.agents.entrySet()
                .stream()
                .filter(t -> !t.getKey().equals(screen))
                .forEach(t -> t.getValue().pausePlayer());
    }

    /**
     * Stop all the players.
     */
    public void stopAllAgents() {
        this.agents.entrySet()
                .stream()
                .forEach(t -> t.getValue().stopPlayer());
    }

    /**
     * Pause all players.
     */
    public void pauseAllAgents() {
        this.agents.entrySet()
                .stream()
                .forEach(t -> t.getValue().pausePlayer());
    }

    /**
     * Toogle mute.
     *
     * @param screen
     *            the {@link FXMLScreens}
     */
    public void muteAgent(final FXMLScreens... screen) {
        for (final FXMLScreens screenz : screen) {
            this.agents.get(screenz).toggleMute();
        }
    }

    /**
     * Toogle mute.
     *
     * @param volume
     *            the volume
     * @param screen
     *            the {@link FXMLScreens}
     */
    public void valumeAgent(final double volume, final FXMLScreens... screen) {
        for (final FXMLScreens screenz : screen) {
            this.agents.get(screenz).setVolume(volume);
        }
    }

    /**
     * Mute all audio.
     */
    public void muteAll() {
        this.muteFX();
        this.muteSongs();
    }

    /**
     * Mute Fxs.
     */
    public void muteFX() {
        this.isAudioClipMuted = !this.isAudioClipMuted;
    }

    /**
     * Mute All the songs.
     */
    public void muteSongs() {
        this.agents.entrySet()
                .stream()
                .forEach(k -> k.getValue().toggleMute());
    }

    /**
     * Mute playing song.
     */
    public void mutePlayingSong() {
        this.agents.entrySet()
                .stream()
                .filter(t -> t.getValue().isPlaying())
                .forEach(k -> k.getValue().toggleMute());
    }

    /**
     * Set the main volume.
     *
     * @param volume
     *            the volume
     */
    public void setVolume(final double volume) {
        this.volume = volume;
        this.agents.entrySet()
                .stream()
                .forEach(t -> t.getValue().setVolume(this.volume - DELTA_VOLUME));
    }
}
