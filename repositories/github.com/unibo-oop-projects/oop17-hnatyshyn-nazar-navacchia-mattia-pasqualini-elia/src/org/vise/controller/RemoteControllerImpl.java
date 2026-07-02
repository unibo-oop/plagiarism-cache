package org.vise.controller;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.json.JSONException;
import org.json.simple.parser.ParseException;
import org.vise.controller.timer.TimerActivity;
import org.vise.model.playlist.Playlist;
import org.vise.model.playlist.song.Song;
import org.vise.model.user.CurrentUser;
import org.vise.model.user.FriendUser;
import org.vise.network.NetworkUtility;
import org.vise.network.PlayerNetwork;

import javafx.util.Pair;

/**
 * Implementation of {@link RemoteController}.
 */
public class RemoteControllerImpl extends PlayerControllerImpl implements RemoteController {

    private CurrentUser user;
    private final TimerActivity timerActivity;
    private boolean onlineMode;

    /**
     * Constructor.
     */
    public RemoteControllerImpl() {
        super();
        this.onlineMode = false;
        this.timerActivity = new TimerActivity(this);
    }

    /**
     * 
     */
    @Override
    public void loadSong(final Song songPath) {
        super.loadSong(songPath);
        if (this.isPlayerOnlineMode()) {
            this.user.setActivity(songPath.getTitle());
        }
    }

    /**
     * 
     */
    @Override
    public void play() {
        super.play();
        if (this.isPlayerOnlineMode()) {
            try {
                PlayerNetwork.addActivity(this.user.getUserID(), this.user.getActivity());
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 
     */
    @Override
    public void pause() {
        super.pause();
        if (this.isPlayerOnlineMode()) {
            try {
                PlayerNetwork.removeActivity(this.user.getUserID());
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 
     */
    @Override
    public void stop() {
        super.stop();
        if (this.isPlayerOnlineMode()) {
            try {
                PlayerNetwork.removeActivity(this.user.getUserID());
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 
     */
    @Override
    public void addPlaylist(final String name) {
        super.addPlaylist(name);
        if (this.isPlayerOnlineMode()) {
            try {
                PlayerNetwork.addPlaylist(this.playlistAdministrator.getCurrentPlaylist(),
                                          this.user.getUserID());
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 
     */
    @Override
    public void addSongToPlaylist(final String songPath) {
        super.addSongToPlaylist(songPath);
        if (this.isPlayerOnlineMode()) {
            final Song song = this.playlistAdministrator.getLastSongEditated();
            try {
                PlayerNetwork.addSongToPlaylist(song, this.playlistAdministrator.getCurrentPlaylist());
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 
     */
    @Override
    public void removeSongFromPlaylist(final Song song) {
        super.removeSongFromPlaylist(song);
        if (this.isPlayerOnlineMode()) {
            try {
                PlayerNetwork.removeSongFromPlaylist(song.getSongID(), this.playlistAdministrator.getCurrentPlaylist().getPlaylistID());
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 
     */
    @Override
    public void removePlaylist(final Playlist playlist) {
        super.removePlaylist(playlist);
        if (this.isPlayerOnlineMode()) {
            try {
                PlayerNetwork.removePlaylist(playlist.getPlaylistID().toString(), this.user.getUserID());
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 
     */
    @Override
    public void search(final String searchString) {
        try {
            final Pair<Boolean, Pair<String, List<FriendUser>>> latestSearch = PlayerNetwork.search(this.user.getUserID(), searchString);
            if (latestSearch.getKey()) {
                super.updateSearchView(latestSearch.getValue().getValue());
            }
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 
     */
    @Override
    public void searchOnYouTube(final Song song) {
        if (Desktop.isDesktopSupported() && NetworkUtility.isNetworkingAvailable()) {
            try {
                final String request = "https://www.youtube.com/results?search_query=" + song.getTitle() + "+" + song.getArtist();
                Desktop.getDesktop().browse(new URI(request.replace(" ", "+")));
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 
     */
    @Override
    public void modifyFriendship(final Boolean friend, final int friendID) {
        try {
            if (friend) {
                PlayerNetwork.removeFriendship(this.user.getUserID(), friendID);
            } else {
                PlayerNetwork.addFriendship(this.user.getUserID(), friendID);
            }
            super.updateActivityFriends(PlayerNetwork.getActivityFriends(Integer.toString(this.user.getUserID())));
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 
     */
    @Override
    public void startTimerActivity()  {
        this.timerActivity.start();
    }

    /**
     * 
     */
    @Override
    public void loadPlaylists() {
        if (this.isPlayerOnlineMode()) {
            try {
                PlayerNetwork.getPlaylists(Integer.toString(this.user.getUserID()), this.playlistAdministrator);
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        } else {
            super.loadPlaylists();
        }
        this.refreshListPlaylists(this.playlistAdministrator.getPlaylists());
    }

    /**
     * 
     */
    @Override
    public void logout() {
        //If true - unable to delete cache
        if (!NetworkUtility.delete() && NetworkUtility.isFilePresent()) {
            showNotification("Logout error!");
        }
        super.logout();
        this.onlineMode = false;
        this.close();
    }

    /**
     * 
     */
    @Override
    public void close() {
        this.removePlayerActivity();
    }

    /**
     * 
     */
    @Override
    public void setPlayerMode(final boolean isOnline) {
        this.onlineMode = isOnline;
        if (isOnline) {
            try {
                this.getLoggedInUser();
                this.updateActivityFriends(PlayerNetwork.getActivityFriends(Integer.toString(this.user.getUserID())));
                this.startTimerActivity();
            } catch (JSONException | IOException | ParseException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 
     */
    @Override
    public boolean isPlayerOnlineMode() {
        return this.onlineMode;
    }

    /**
     * 
     */
    @Override
    public void updateActivityFriends() {
        try {
            this.updateActivityFriends(PlayerNetwork.getActivityFriends(Integer.toString(this.user.getUserID())));
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 
     */
    @Override
    public CurrentUser getCurrentUser() {
        return this.user;
    }

    /**
     * 
     */
    @Override
    public void updateUserInfo(final CurrentUser user) {
        this.uisPlayer.forEach(u -> u.updateUserInfo(user));
    }

    /**
     * 
     */
    @Override
    public void updateActivityFriends(final List<Pair<String, String>> activities) {
        this.uisPlayer.forEach(u -> u.updateActivityFriends(activities));
    }

    /**
     * 
     */
    @Override
    public void updateSearchView(final List<FriendUser> friendUsers) {
        this.uisPlayer.forEach(u -> u.updateSearchView(friendUsers));
    }

    private void removePlayerActivity() {
        if (this.isPlayerOnlineMode()) {
            try {
                PlayerNetwork.removeActivity(this.user.getUserID());
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void getLoggedInUser() throws JSONException, IOException, ParseException {
        if (NetworkUtility.isFilePresent()) {
            this.user = NetworkUtility.readJSON();
            this.updateUserInfo(this.user);
        }
    }
}
