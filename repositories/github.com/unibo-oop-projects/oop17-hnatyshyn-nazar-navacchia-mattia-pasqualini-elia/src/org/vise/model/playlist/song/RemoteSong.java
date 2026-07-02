package org.vise.model.playlist.song;

import java.util.UUID;

/**
 * 
 * @author nazarhnatyshyn
 *
 */
public class RemoteSong implements Song {
    private final UUID songID;
    private final String audioPath;
    private String title;
    private String artist;
    private String album;

    /**
     * 
     * @param songID
     *          The ID of the remote song.
     * @param title
     *          The title of the remote song.
     * @param artist
     *          The artist of the remote song.
     * @param album
     *          The album of the remote song.
     */
    public RemoteSong(final UUID songID, final String title, final String artist, final String album) {
        this.songID = songID;
        this.title = title;
        this.artist = artist;
        this.album = album;
        //never used
        this.audioPath = "";
    }

    /**
     * 
     */
    @Override
    public UUID getSongID() {
        return songID;
    }

    /**
     * 
     */
    @Override
    public String getTitle() {
        return title;
    }

    /**
     * 
     */
    @Override
    public String getArtist() {
        return artist;
    }

    /**
     * 
     */
    @Override
    public String getAlbum() {
        return album;
    }

    /**
     * 
     */
    @Override
    public void updateMeta(final String title, final String artist, final String album) {
        this.title = title;
        this.artist = artist;
        this.album = album;
    }

    /**
     * 
     */
    @Override
    public String getAudioPath() {
        return this.audioPath;
    }

    /**
     * 
     * @return
     *          The duration of the current song.
     */
    @Override
    public String getDuration() {
        return "none";
    }

}
