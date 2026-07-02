package org.vise.model.playlist;

import java.util.List;
import java.util.UUID;

import org.vise.model.playlist.song.Song;
/**
 * Interface that represent the properties of the playlist.
 */
public interface Playlist {

    /**
     * Add a new song to the current playlist.
     * 
     * @param newSong
     *          The song to be added.
     */
    void addSong(Song newSong);

    /**
     * Remove a song from the current playlist.
     * 
     * @param song
     *          The song to be removed.
     * @throws IllegalArgumentException
     *          Exception if the song is not stored.
     */
    void removeSong(Song song) throws IllegalArgumentException;

    /**
     * Get the UUID of the current playlist.
     * 
     * @return
     *          The ID of the current playlist.
     */
    UUID getPlaylistID();

    /**
     * Get the name of the current playlist.
     * 
     * @return
     *          The name of the current playlist.
     */
    String getName();

    /**
     * Get the author of the current playlist.
     * 
     * @return
     *          The author of the current playlist.
     */
    String getAuthor();

    /**
     * Check if the current playlist is empty.
     * 
     * @return
     *          If the current playlist is empty.
     */
    boolean isEmpty();

    /**
     * Get every song of the current playlist.
     * 
     * @return
     *          The list of the song in the current playlist
     */
    List<Song> getPlaylistContent();

    /**
     * Set a new name for the current playlist.
     * 
     * @param name
     *          The new name of the current playlist.
     */
    void setName(String name);

    /**
     * Get the dimension of the current playlist.
     * 
     * @return
     *          The dimension of the playlist.
     */
    int getDimension();
}
