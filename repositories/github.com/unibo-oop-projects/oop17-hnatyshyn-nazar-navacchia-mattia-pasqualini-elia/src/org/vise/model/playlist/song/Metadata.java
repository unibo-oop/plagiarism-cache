package org.vise.model.playlist.song;

/**
 * Interface that represent the properties of the metadata.
 */
public interface Metadata {

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
    void modifyMeta(String title, String artist, String album);
}
