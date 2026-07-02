package org.vise.model.playlist;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.vise.model.playlist.song.Song;

/**
 * This is an implementation of PlaylistAdministrator.
 */
public class PlaylistAdministratorImpl implements PlaylistAdministrator {

    private Playlist currentPlaylist;
    private final List<Playlist> playlists = new ArrayList<>();
    private Song lastSongEditated;

    /**
     * Constructor.
     */
    public PlaylistAdministratorImpl() {
        this.currentPlaylist = null;
    }

    /**
     * 
     */
    @Override
    public void addPlaylist(final UUID id, final String name) {
        final Playlist playlist = new PlaylistImpl(id, name);
        this.playlists.add(playlist);
        this.setCurrentPlaylist(playlist);
    }

    /**
     * 
     */
    @Override
    public Playlist newPlaylist(final String name) {
        final Playlist newPlaylist = new PlaylistImpl(name);
        playlists.add(newPlaylist);
        return newPlaylist;
    }

    /**
     * 
     */
    @Override
    public void removePlaylist(final UUID id) {
        this.checkPlaylistID(id);
        this.playlists.removeIf(p -> p.getPlaylistID() == id);
    }

   /**
    * 
    */
    @Override
    public Song addSongToPlaylist(final Song song) {
        this.checkPlaylist();
        this.currentPlaylist.addSong(song);
        this.setLastSongEditated(song);
        return song;
    }

    /**
     * 
     */
    @Override
    public void removeSongToPlaylist(final Song song) {
        this.checkPlaylist();
        this.currentPlaylist.removeSong(song);
        this.setLastSongEditated(song);
    }

    /**
     * 
     */
    @Override
    public List<Song> getContentPlaylist() {
        this.checkPlaylist();
        return this.currentPlaylist.getPlaylistContent();
    }

    /**
     * 
     */
    @Override
    public Playlist getCurrentPlaylist() {
        return this.currentPlaylist;
    }

    /**
     * 
     */
    @Override
    public void setCurrentPlaylist(final Playlist playlist) {
        this.currentPlaylist = playlist;
    }

    /**
     * 
     */
    @Override
    public void setNamePlaylist(final String newName) {
        this.checkPlaylist();
        this.currentPlaylist.setName(newName);
    }

    /**
     * 
     */
    @Override
    public List<Playlist> getPlaylists() {
        return this.playlists;
    }

    /**
     * 
     * @param id
     *          The id of the playlist to search.
     */
    private void checkPlaylistID(final UUID id) {
        if (!this.playlists.stream().anyMatch(p -> p.getPlaylistID() == id)) {
            throw new IllegalArgumentException("The playlist is not present");
        }
    }

    /**
     * Checks current playlist.
     */
    private void checkPlaylist() {
        if (this.currentPlaylist == null) {
            throw new IllegalArgumentException("Optional is empty");
        }

    }

    /**
     * 
     */
    @Override
    public Song getSongAtIndex(final int index) {
        return this.getCurrentPlaylist().getPlaylistContent().get(index);
    }

    /**
     * 
     */
    @Override
    public void modifyMetaSongAtIndex(final int index, final String title, final String artist, final String album) {
        this.getCurrentPlaylist().getPlaylistContent().get(index).updateMeta(title, artist, album);
    }

    /**
     * 
     */
    @Override
    public Song getLastSongEditated() {
        return lastSongEditated;
    }

    /**
     * 
     */
    @Override
    public void setLastSongEditated(final Song lastSongEditated) {
        this.lastSongEditated = lastSongEditated;
    }
}
