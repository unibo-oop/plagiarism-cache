package org.vise.model.playlist;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.vise.model.playlist.song.Song;

/**
 * This is an implementation of Playlist.
 */
public class PlaylistImpl implements Playlist {

    private final List<Song> content;
    private final UUID playlistID;
    private String name;
    private final String author;

    /**
     * Constructor.
     * 
     * @param name
     *          The name of the playlist.
     */
    public PlaylistImpl(final String name) {
        this.playlistID = UUID.randomUUID();
        this.content = new ArrayList<>();
        this.name = name;
        this.author = "Tu";
    }

    /**
     * Constructor.
     * 
     * @param id
     *          Playlist UUID.
     * @param name
     *          Playlist name.
     */
    public PlaylistImpl(final UUID id, final String name) {
        this.playlistID = id;
        this.content = new ArrayList<>();
        this.name = name;
        this.author = "Te";
    }

    /**
     * Constructor.
     * 
     * @param id
     *          Playlist UUID.
     * @param name
     *          Playlist name.
     * @param author
     *          Playlist author.
     */
    public PlaylistImpl(final UUID id, final String name, final String author) {
        this.playlistID = id;
        this.content = new ArrayList<>();
        this.name = name;
        this.author = author;
    }

    /**
     * Constructor.
     * 
     * @param name
     *          The name of the playlist.
     * @param author
     *          The name of the author.
     */
    public PlaylistImpl(final String name, final String author) {
        this.playlistID = UUID.randomUUID();
        this.content = new ArrayList<>();
        this.name = name;
        this.author = author;
    }

    /**
     * 
     */
    @Override
    public void addSong(final Song newSong) {
        this.content.add(newSong);
    }

    /**
     * 
     */
    @Override
    public void removeSong(final Song song) throws IllegalArgumentException {
        if (!checkSong(song)) {
            throw new IllegalArgumentException("Song not found");
        }
        this.content.remove(song);
    }

    /**
     * 
     */
    @Override
    public UUID getPlaylistID() {
        return this.playlistID;
    }

    /**
     * 
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * 
     */
    @Override
    public String getAuthor() {
        return this.author;
    }

    /**
     * 
     */
    @Override
    public boolean isEmpty() {
        return this.content.isEmpty();
    }

    /**
     * 
     */
    @Override
    public List<Song> getPlaylistContent() {
        return this.content;
    }

    /**
     * 
     */
    @Override
    public void setName(final String newName) {
        this.name = newName;
    }

    /**
     * 
     */
    @Override
    public int getDimension() {
        return this.content.size();
    }

    /**
     * Check if a song is present on a playlist.
     * 
     * @param song
     *          The song to search in the playlist.
     * @return
     *          True if the song is present.
     */
    private boolean checkSong(final Song song) {
        return this.content.contains(song);
    }
}
