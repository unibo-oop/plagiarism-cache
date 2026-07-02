package it.unibo.oop.utilities;

/**
 * An interface for a music player.
 */
public interface MusicPlayer {

    /**
     * Sets the music on or off.
     * 
     * @param musicOn
     *            true to set the music on, false to set it off
     */
    void setMusic(final boolean musicOn);

    /**
     * Indicates if the music is on or not.
     * 
     * @return true if the music is on, false otherwise
     */
    boolean isMusicOn();

    /**
     * Plays the audio file.
     * 
     * @param musicName
     *            the audio file to reproduce
     */
    void play(final String musicName);

    /**
     * Plays the audio file in loop.
     * 
     * @param musicName
     *            the audio file to reproduce
     */
    void playLoop(final String musicName);

    /**
     * Stops the reproduction of the audio file.
     * 
     * @param musicName
     *            the audio file to stop
     */
    void stop(final String musicName);

    /**
     * Stops the reproduction of all the audio files.
     */
    void stopAll();

    /**
     * Closes the music player.
     */
    void closeMusicPlayer();
}