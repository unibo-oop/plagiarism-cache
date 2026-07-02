package org.vise.controller;

import java.util.List;

import org.vise.model.playlist.Playlist;
import org.vise.model.playlist.song.Song;
import org.vise.model.user.CurrentUser;
import org.vise.model.user.FriendUser;

import javafx.util.Pair;

/**
 * 
 */
public interface UpdatableUIPlayer {

    /**
     * Updates the info about the song in reproduction.
     * @param song
     *          The song in reproduction.
     */
    void updateReproductionSongInfo(Song song);

    /**
     * Updates the position of the song in reproduction.
     * @param position
     *          The current position of the song in reproduction.
     */
    void updateReproductionSongPosition(Integer position);

    /**
     * 
     * @param playlists
     *          List of playlists.
     */
    void refreshListPlaylists(List<Playlist> playlists);

    /**
     * 
     * @param songs
     *          List of songs.
     */
    void refreshTableSongs(List<Song> songs);

    /**
     * 
     * @param user
     *          A logged in user.
     */
    void updateUserInfo(CurrentUser user);

    /**
     * Update the list di activities of the friends of the user (only in online mode).
     * @param activities
     *          The friends' activity.
     */
    void updateActivityFriends(List<Pair<String, String>> activities);

    /**
     * Update the view that displays the search results.
     * @param friendUsers
     *          A list of friend users to update.
     */
    void updateSearchView(List<FriendUser> friendUsers);
}
