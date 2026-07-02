package org.vise.model.playlist.song;

import java.util.UUID;

/**
 * Interface that represent the properties of a song.
 */
public interface Song {


    /**
     * 
     * @return
     *          The path of the current song.
     */
    String getAudioPath();

    /**
     * 
     * @return
     *          The ID of the current song.
     */
    UUID getSongID();

    /**
     * 
     * @return
     *          The title of the current song.
     */
    String getTitle();

    /**
     * 
     * @return
     *          The artist of the current song.
     */
    String getArtist();

    /**
     * 
     * @return
     *          The album of the current song.
     */
    String getAlbum();

    /**
     * 
     * @return
     *          The duration of the current song.
     */
    String getDuration();


    /**
     * 
     * @param title
     *          The title of the current song.
     * @param artist
     *          The artist of the current song.
     * @param album
     *          The album of the current song.
     */
    void updateMeta(String title, String artist, String album);

}
