package org.vise.model.player;

import org.vise.model.playlist.song.Song;

/**
 * Interface that represent the operations that the player has to performe.
 */
public interface Player {

    /**
     * 
     * @param song
     *          The song to be loaded.
     */
    void loadSong(Song song);

    /**
     * Starts reproducing audio file.
     */
    void play();

    /**
     * Pauses audio reproduction.
     */
    void pause();

    /**
     * Stops audio reproduction, rewinds the loaded audio file.
     */
    void stop();

    /**
     * Clears the player.
     */
    void clearPlayer();

    /**
     * 
     * @return
     *          The length of the current song.
     */
    int getLength();

    /**
     * 
     * @return
     *          The position of the current song.
     */
    int getPosition();

    /**
     * 
     * @return
     *          If the player is playing a song.
     */
    boolean isPlaying();

    /**
     * 
     * @return
     *          If one song has been loaded.
     */
    boolean isLoadedSong();

    /**
     * 
     * @param newPosition
     *          The position of the current song.
     */
    void setPosition(int newPosition);

    /**
     * 
     * @param newVolume
     *          The value of the volume.
     */
    void setVolume(float newVolume);

    /**
     * 
     * @return
     *          Return if there aren't song already loaded.
     */
    boolean isPlayerEmpty();

}
