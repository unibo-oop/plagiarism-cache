package org.vise.controller;

import java.util.List;

import org.vise.model.playlist.Playlist;
import org.vise.model.playlist.song.Song;
import org.vise.view.UI;
import org.vise.view.UIPlayer;

/**
 * 
 * Interface of the Player.
 *
 */
public interface PlayerController {

    /**
     * 
     * @param songPath
     *          The song to be played.
     */
    void loadSong(Song songPath);

    /**
     * Play the song loaded.
     */
    void play();

    /**
     * Pause the song loaded.
     */
    void pause();

    /**
     * Stop the song loaded.
     */
    void stop();

    /**
     * Skip to the next song.
     */
    void next();

    /**
     * Skip to the previous song.
     */
    void prev();

    /**
     * 
     * @return
     *          If the player is playing a song.
     */
    boolean isPlaying();

    /**
     * 
     * @param index
     *          The index of the queue.
     */
    void setQueueIndex(int index);

    /**
     * 
     * @param name
     *          The name of the new playlist.
     */
    void addPlaylist(String name);

    /**
     * 
     * @param playlist
     *          The name of the removed playlist.
     */
    void removePlaylist(Playlist playlist);

    /**
     * 
     * @param songPath
     *          The path of the song to be added.
     */
    void addSongToPlaylist(String songPath);

    /**
     * 
     * @param song
     *          The song to be removed.
     */
    void removeSongFromPlaylist(Song song);

    /**
     * 
     * @param playlist
     *          The current playlist.
     */
    void setCurrentPlaylist(Playlist playlist);

    /**
     * 
     * @return playlist
     *          The current playlist.
     */
    Playlist getCurrentPlaylist();

    /**
     * 
     * @param newPosition
     *          The new Position of the playing song.
     */
    void setPosition(int newPosition);

    /**
     * 
     * @param newVolume
     *          The new Volume of the playing song.
     */
    void setVolume(float newVolume);

    /**
     * 
     * @return
     *          The playlists of the user.
     */
    List<Playlist> getPlaylists();

    /**
     * 
     * @param ui
     *          The UI attached to the Controller.
     */
    void bindPlayerUI(UIPlayer ui);

    /**
     * 
     * @param ui
     *          The UI attached to the Controller.
     */
    void bindUI(UI ui);

    /**
     * 
     * @return
     *          True if the playlist is in shuffle mode.
     */
    boolean isShuffle();

    /**
     * 
     * @param bool
     *          True if the playlist is in shuffle mode.
     * @param i
     *          The index. 
     */
    void setShuffle(boolean bool, int i);

    /**
     * 
     * @return
     *          The lenght of the song loaded.
     */
    int getLength();

    /**
     * 
     * @return
     *          Return if the player has a song loaded or not.
     */
    boolean isLoaded();

    /**
     * Save the playlist on a local JSON file.
     */
    void savePlaylist();

    /**
     * Modify the metadata of the song.
     * 
     * @param index
     *          The index of the song.
     * @param title
     *          The new title of the song.
     * @param artist
     *          The new artist of the song.
     * @param album
     *          The new album of the song.
     */
    void modifyMetaSong(int index, String title, String artist, String album);

    /**
     * 
     * @param newName
     *          The new name of the playlist.
     */
    void setNamePlaylist(String newName);

    /**
     * 
     * @param isSingleModality
     *          If the user want to play only a single song.
     */
    void setSingleReproductionModality(boolean isSingleModality);

    /**
     * 
     * @return
     *          The type (true if it's single and false if it's queue) of reproduction.
     */
    boolean isSingleReproductionModality();

    /**
     * Load the saved playlists based on the modality (offline or online).
     */
    void loadPlaylists();

    /**
     * Release the resources if the user decided to logout from the current session.
     */
    void logout();
}
