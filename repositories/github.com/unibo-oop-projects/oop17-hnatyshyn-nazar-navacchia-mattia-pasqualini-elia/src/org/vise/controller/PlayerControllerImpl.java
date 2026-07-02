package org.vise.controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.json.simple.parser.ParseException;
import org.vise.controller.timer.TimerPlayer;
import org.vise.model.player.Player;
import org.vise.model.player.PlayerImpl;
import org.vise.model.playlist.Playlist;
import org.vise.model.playlist.PlaylistAdministrator;
import org.vise.model.playlist.PlaylistAdministratorImpl;
import org.vise.model.playlist.PlaylistUtility;
import org.vise.model.playlist.queue.LinearQueue;
import org.vise.model.playlist.queue.PlaylistQueue;
import org.vise.model.playlist.queue.ShuffleQueue;
import org.vise.model.playlist.song.Song;
import org.vise.model.playlist.song.SongImpl;
import org.vise.model.user.CurrentUser;
import org.vise.model.user.FriendUser;
import org.vise.view.UI;
import org.vise.view.UIPlayer;

import javafx.application.Platform;
import javafx.util.Pair;

/**
 * Implementation of {@link PlayerController}.
 */
public class PlayerControllerImpl implements PlayerController, UpdatableUI, UpdatableUIPlayer {

    /**
     * 
     */
    @SuppressWarnings("all")
    protected PlaylistAdministrator playlistAdministrator;
    /**
     * 
     */
    @SuppressWarnings("all")
    protected final Set<UIPlayer> uisPlayer;
    private Player player;
    private PlaylistQueue playlistQueue;
    private boolean shuffle;
    private final Set<UI> uis;
    private TimerPlayer playerClock;
    private boolean singleModality;

    /**
     * Constructor.
     */
    public PlayerControllerImpl() {
        this.player = new PlayerImpl();
        this.playlistAdministrator = new PlaylistAdministratorImpl();
        this.uisPlayer = new HashSet<>();
        this.uis = new HashSet<>();
        this.shuffle = false;
        this.playerClock = new TimerPlayer(this, this, this.player);
        this.singleModality = true;
    }

    /**
     * 
     */
    @Override
    public void loadSong(final Song songPath) {
        if (!songPath.getAudioPath().isEmpty()) {
            if (!this.player.isPlayerEmpty()) {
                this.player.clearPlayer();
            }
            this.player.loadSong(songPath);
            this.playerClock.setLenghtCurrentSong();
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    updateReproductionSongInfo(songPath);
                }
            });
        }
    }

    /**
     * 
     */
    @Override
    public void setSingleReproductionModality(final boolean isSingleModality) {
        this.singleModality = isSingleModality;
    }

    /**
     * 
     */
    @Override
    public boolean isSingleReproductionModality() {
        return this.singleModality;
    }

    /**
     * 
     */
    @Override
    public void play() {
        if (!this.checkPlayer()) {
            this.player.play();
        }
        this.playerClock.start();
        this.playerClock.setLenghtCurrentSong();
        this.uisPlayer.forEach(u -> u.updateReproductionSongPosition(0));
    }

    /**
     * 
     */
    @Override
    public void pause() {
        if (!this.checkPlayer()) {
            this.player.pause();
            this.playerClock.setRunning(false);
        }
    }

    /**
     * 
     */
    @Override
    public void stop() {
        if (!this.checkPlayer()) {
            this.player.stop();
            this.playerClock.setRunning(false);
        }
    }

    /**
     * 
     */
    @Override
    public void next() {
        if (!this.checkPlayer()) {
            this.loadSong(this.playlistAdministrator.getSongAtIndex(this.playlistQueue.next()));
            this.play();
        }
    }

    /**
     * 
     */
    @Override
    public void prev() {
        if (!this.checkPlayer()) {
            this.loadSong(this.playlistAdministrator.getSongAtIndex(this.playlistQueue.prev()));
            this.play();
        }
    }

    /**
     * 
     */
    @Override
    public boolean isPlaying() {
        return this.player.isPlaying();
    }

    /**
     * 
     */
    @Override
    public int getLength() {
        return this.player.getLength();
    }

    /**
     * 
     */
    @Override
    public void modifyMetaSong(final int index, final String title, final String artist, final String album) {
        this.playlistAdministrator.modifyMetaSongAtIndex(index, title, artist, album);
        this.refreshTableSongs(this.playlistAdministrator.getContentPlaylist());
    }

    /**
     * 
     */
    @Override
    public void setQueueIndex(final int index) {
        this.playlistQueue.setSongIndex(index);
    }

    /**
     * 
     */
    @Override
    public void addPlaylist(final String name) {
        final String trimmed = name.trim();
        if (!trimmed.isEmpty()) {
            this.playlistAdministrator.setCurrentPlaylist(this.playlistAdministrator.newPlaylist(name));
            this.refreshListPlaylists(this.playlistAdministrator.getPlaylists());
        } else {
            this.showNotification("Inserisci un nome valido per la Playlist.");
        }
    }

    /**
     * 
     */
    @Override
    public void addSongToPlaylist(final String songPath) {
        this.playlistAdministrator.addSongToPlaylist(new SongImpl(songPath));
        this.refreshTableSongs(this.playlistAdministrator.getContentPlaylist());
    }

    /**
     * 
     */
    @Override
    public void removeSongFromPlaylist(final Song song) {
        this.playlistAdministrator.removeSongToPlaylist(song);
        this.refreshTableSongs(this.playlistAdministrator.getContentPlaylist());
    }

    /**
     * 
     */
    @Override
    public void setCurrentPlaylist(final Playlist playlist) {
        this.playlistAdministrator.setCurrentPlaylist(playlist);
        this.refreshTableSongs(this.playlistAdministrator.getContentPlaylist());
        this.setShuffle(false, 0);
    }

    /**
     * 
     */
    @Override
    public Playlist getCurrentPlaylist() {
        return this.playlistAdministrator.getCurrentPlaylist();
    }

    /**
     * 
     */
    @Override
    public void setPosition(final int newPosition) {
        this.player.setPosition(newPosition);
        this.playerClock.setLenghtCurrentSong();
        this.playerClock.setRunning(true);
    }

    /**
     * 
     */
    @Override
    public void setVolume(final float newVolume) {
        this.player.setVolume(newVolume);
    }

    /**
     * 
     */
    @Override
    public List<Playlist> getPlaylists() {
        return this.playlistAdministrator.getPlaylists();
    }

    /**
     * 
     */
    @Override
    public void bindPlayerUI(final UIPlayer ui) {
        this.uisPlayer.add(ui);
    }

    /**
     * 
     */
    @Override
    public void bindUI(final UI ui) {
        this.uis.add(ui);
    }

    /**
     * 
     */
    @Override
    public void showNotification(final String textNotification) {
        this.uis.forEach(u -> u.showNotification(textNotification));
    }

    /**
     * 
     */
    @Override
    public void updateReproductionSongInfo(final Song song) {
        this.uisPlayer.forEach(u -> u.updateReproductionSongInfo(song));
    }

    /**
     * 
     */
    @Override
    public void updateReproductionSongPosition(final Integer position) {
        this.uisPlayer.forEach(u -> u.updateReproductionSongPosition(position));
    }

    /**
     * 
     */
    @Override
    public boolean isShuffle() {
        return this.shuffle;
    }

    /**
     * 
     */
    @Override
    public void setShuffle(final boolean bool, final int i) {
        if (this.getCurrentPlaylist() != null) {
            this.shuffle = bool;
            if (this.shuffle) {
                this.playlistQueue = new ShuffleQueue(this.getCurrentPlaylist(), i);
            } else {
                this.playlistQueue = new LinearQueue(this.getCurrentPlaylist());
            }
        } else {
            this.showNotification("Nessuna playlist creata.");
        }
    }

    /**
     * 
     */
    @Override
    public void removePlaylist(final Playlist playlist) {
        this.playlistAdministrator.removePlaylist(playlist.getPlaylistID());
        this.refreshListPlaylists(this.playlistAdministrator.getPlaylists());
    }

    /**
     * 
     */
    @Override
    public boolean isLoaded() {
        return this.player.isLoadedSong();
    }

    /**
     * 
     */
    @Override
    public void savePlaylist() {
        try {
            PlaylistUtility.save(this.playlistAdministrator.getPlaylists());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 
     */
    @Override
    public void refreshListPlaylists(final List<Playlist> playlists) {
        this.uisPlayer.forEach(u -> u.refreshListPlaylists(playlists));
    }

    /**
     * 
     */
    @Override
    public void refreshTableSongs(final List<Song> songs) {
        this.uisPlayer.forEach(u -> u.refreshTableSongs(songs));
    }

    /**
     *
     */
    @Override
    public void setNamePlaylist(final String newName) {
        this.playlistAdministrator.setNamePlaylist(newName);
        this.refreshListPlaylists(this.playlistAdministrator.getPlaylists());
    }

    /**
     * 
     */
    @Override
    public void loadPlaylists() {
        try {
            PlaylistUtility.readJSON(playlistAdministrator);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.refreshListPlaylists(this.playlistAdministrator.getPlaylists());
    }

    /**
     * 
     */
    @Override
    public void logout() {
        this.stop();
        this.player = new PlayerImpl();
        this.playlistAdministrator = new PlaylistAdministratorImpl();
        this.playerClock = new TimerPlayer(this, this, this.player);
        this.shuffle = false;
        this.singleModality = true;
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

    private boolean checkPlayer() {
        return this.player.isPlayerEmpty();
    }
}
