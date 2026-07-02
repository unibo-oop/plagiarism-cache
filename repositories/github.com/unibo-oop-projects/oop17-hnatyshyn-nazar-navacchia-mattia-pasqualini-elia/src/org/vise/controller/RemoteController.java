package org.vise.controller;

import org.vise.model.playlist.song.Song;
import org.vise.model.user.CurrentUser;

/**
 * This class models a controller with operations associated to the online modality.
 *
 */
public interface RemoteController extends PlayerController {

    /**
     * 
     * @param searchString
     *          A string to search into the Server.
     */
    void search(String searchString);

    /**
     * 
     * @param song
     *          The song to be searched.
     */
    void searchOnYouTube(Song song);

    /**
     * Modify the friendship of two friends.
     * @param friend
     *          If the two users are friend.
     * @param friendID
     *          User ID.
     */
    void modifyFriendship(Boolean friend, int friendID);

    /**
    *
    */
   void close();

   /**
    * Start the timer that update the activity friends.
    */
    void startTimerActivity();

    /**
     * Update the list of actvities of the friends.
     */
    void updateActivityFriends();

    /**
     * 
     * @param isOffline
     *          The mode.
     */
    void setPlayerMode(boolean isOffline);

    /**
     * 
     * @return
     *          The modality of the player.
     */
    boolean isPlayerOnlineMode();

    /**
     * 
     * @return
     *          Current user.
     */
    CurrentUser getCurrentUser();
}
