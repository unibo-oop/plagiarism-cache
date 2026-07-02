package org.vise.view;

import java.util.List;

import org.vise.model.playlist.Playlist;
import org.vise.model.playlist.song.Song;
import org.vise.model.user.CurrentUser;
import org.vise.model.user.FriendUser;

import javafx.util.Pair;

/**
 * 
 * Interface that represent the operations that the player has to performe.
 *
 */
public interface UIPlayer {

    /**
     * Update the info of the song in reproduction.
     * @param song
     *          The song in reproduction.
     */
    void updateReproductionSongInfo(Song song);

    /**
     * Update the position of the song in reproduction.
     * @param position
     *          The position of the song in reproduction.
     */
    void updateReproductionSongPosition(Integer position);

    /**
     * Refresh the list of the playlists that the user has.
     * @param playlists
     *          List of playlists.
     */
    void refreshListPlaylists(List<Playlist> playlists);

    /**
     * Refresh the list of the songs of the current playlist loaded into the Table View.
     * @param songs
     *          List of songs.
     */
    void refreshTableSongs(List<Song> songs);

    /**
     * Update the user info after the login.
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
