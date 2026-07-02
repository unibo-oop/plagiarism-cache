package org.vise.model.player;


import org.vise.model.playlist.song.Song;

import ddf.minim.AudioPlayer;
import ddf.minim.Minim;

/**
 * Rapresent the Player.
 */
public class PlayerImpl implements Player {
    private AudioPlayer player;
    private final Minim minim;
    private static final float MAXGAIN = 5.0f;
    private static final float MINGAIN = -60.0f;
    private boolean isLoaded;

    /**
     * Constructor for the class PlayerImpl.
     */
    public PlayerImpl() {
        this.minim = new Minim(new FileSystemHandler());
        this.isLoaded = false;
    }

    /**
     * 
     */
    @Override
    public void loadSong(final Song song) {
        this.player = this.minim.loadFile(song.getAudioPath());
        this.isLoaded = true;
    }

    /**
     * 
     */
    @Override
    public void play() {
        this.player.play();
    }

    /**
     * 
     */
    @Override
    public void pause() {
        this.player.pause();
    }

    /**
     * 
     */
    @Override
    public void stop() {
        this.player.pause();
        this.player.rewind();
    }

    /**
     * 
     */
    @Override
    public void clearPlayer() {
        this.stop();
        this.player.close();
    }

    /**
     * 
     */
    @Override
    public int getLength() {
        return this.player.length();
    }

    /**
     * 
     */
    @Override
    public int getPosition() {
        return this.player.position();
    }

    /**
     * 
     */
    @Override
    public boolean isPlaying() {
        return this.player.isPlaying();
    }

    /**
     * 
     */
    @Override
    public boolean isLoadedSong() {
        return this.isLoaded;
    }

    /**
     * 
     */
    @Override
    public void setPosition(final int newPosition) {
        if (newPosition < 0 || newPosition > this.getLength()) {
            throw new IllegalArgumentException();
        }
        this.player.play(newPosition);
    }

   /**
    * 
    */
    @Override
    public void setVolume(final float newVolume) {
        this.setGain(newVolume);
        if (this.player.getGain() <= MINGAIN) {
            this.setGain(MINGAIN);
            this.player.mute();
        } else {
            this.player.unmute();
        }
          if (this.player.getGain() >= MAXGAIN) {
            this.setGain(MAXGAIN);
        }

    }

    /**
     * 
     */
    @Override
    public boolean isPlayerEmpty() {
        return this.player == null;
    }

    /**
     * Sets the gain of the player.
     * 
     * @param amount
     *          The amount of the gain.
     */
    private void setGain(final float amount) {
        this.player.setGain(amount);
    }

}
