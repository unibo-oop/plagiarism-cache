package org.vise.model.user;

import java.util.List;

import org.vise.model.playlist.Playlist;

/**
 * Represents the user of a research in online modality.
 *
 */
public interface FriendUser extends User {
    /**
     * 
     * @return
     *          The playlist of user.
     */
    List<Playlist> getPlaylists();

    /**
     * 
     * @return
     *          true if current user is following current FriendUser.
     */
    Boolean isFriend();

    /**
     * Add as friend.
     */
    void addAsFriend();

    /**
     * Remove from friends list.
     */
    void deleteFriend();
}
