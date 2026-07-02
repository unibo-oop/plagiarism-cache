package org.vise.model.playlist;

import java.util.List;
import java.util.UUID;

import org.vise.model.playlist.song.Song;

/**
 * Interface that rapresent the operation that the playlist can do.
 */
public interface PlaylistAdministrator {

    /**
     * Create a new playlist.
     * 
     * @param name
     *          The name of the new playlist.
     * @return 
     *          Current playlist.
     */
    Playlist newPlaylist(String name);

    /**
     * Remove a playlist.
     * 
     * @param id
     *          The id of the playlist to remove.
     */
    void removePlaylist(UUID id);

    /**
     * Add a song to the current playlist.
     * 
     * @param song
     *          The song to add to the playlist.
     * @return
     *          Current song.
     */
    Song addSongToPlaylist(Song song);

    /**
     * Remove a song from the current playlist.
     * 
     * @param song
     *          The song to remove to the playlist.
     */
    void removeSongToPlaylist(Song song);

    /**
     * Get a song from his index in the current playlist.
     * 
     * @param index
     *          The index of the song in the current playlist.
     * @return
     *          The song at the index.
     */
    Song getSongAtIndex(int index);

    /**
     * Get every song of the current playlist.
     * 
     * @return
     *          The list of the song in the current playlist.
     */
    List<Song> getContentPlaylist();

    /**
     * Get the current playlist.
     * 
     * @return playlist
     *          The current playlist.
     */
    Playlist getCurrentPlaylist();

    /**
     * Set a playlist as current.
     * 
     * @param playlist
     *          The current playlist.
     */
    void setCurrentPlaylist(Playlist playlist);

    /**
     * Set a new name for the current playlist.
     * 
     * @param newName
     *          The new name of the current playlist.
     */
    void setNamePlaylist(String newName);

    /**
     * Get every playlist.
     * 
     * @return
     *          The list of all the playlist.
     */
    List<Playlist> getPlaylists();

    /**
     * Modify the metadata of a song based on his index.
     * 
     * @param index
     *          The index of the song to modify.
     * @param title
     *          The new title of the song.
     * @param artist
     *          The new artist of the song.
     * @param album
     *          The new album of the song.
     */
    void modifyMetaSongAtIndex(int index, String title, String artist, String album);

    /**
     * Add a new playlist from his UUID and his name.
     * 
     * @param id
     *          The id of the playlist.
     * @param name
     *          The name of the playlist.
     */
    void addPlaylist(UUID id, String name);

    /**
     * Get the last song editated.
     * 
     * @return
     *          The last song editated.
     */
    Song getLastSongEditated();

    /**
     * Set the song that has to be setted as the last editated.
     * 
     * @param lastSongEditated
     *          The song that has to be setted as last editated.
     */
    void setLastSongEditated(Song lastSongEditated);

}
