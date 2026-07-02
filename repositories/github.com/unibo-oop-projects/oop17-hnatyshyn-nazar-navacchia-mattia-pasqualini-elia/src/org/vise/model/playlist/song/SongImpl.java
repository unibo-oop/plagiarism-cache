package org.vise.model.playlist.song;

import java.util.UUID;

/**
 * This is an implementation of Song.
 */
public class SongImpl implements Song {

    private final UUID songID;
    private final String audioPath;
    private final transient Metadata meta;

    /**
     * Create Song object.
     * @param audioPath
     *          Path of the song.
     */
    public SongImpl(final String audioPath) {
        this.songID = UUID.randomUUID();
        this.audioPath = audioPath;
        this.meta = new MetadataImpl(this.audioPath);
    }

    /**
     * Create Song object.
     * @param songID
     *          Song UUID.
     * @param audioPath
     *          Path of the song.
     */
    public SongImpl(final UUID songID, final String audioPath) {
        this.songID = songID;
        this.audioPath = audioPath;
        this.meta = new MetadataImpl(this.audioPath);
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
     */
    @Override
    public UUID getSongID() {
        return this.songID;
    }

    /**
     * 
     */
    @Override
    public String getTitle() {
        return this.meta.getTitle();
    }

    /**
     * 
     */
    @Override
    public String getArtist() {
        return this.meta.getArtist();
    }

    /**
     * 
     */
    @Override
    public String getAlbum() {
        return this.meta.getAlbum();
    }

    /**
     * 
     */
    @Override
    public String getDuration() {
        return this.meta.getDuration();
    }

    /**
     * 
     */
    @Override
    public void updateMeta(final String title, final String artist, final String album) {
        this.meta.modifyMeta(title, artist, album);
    }
}
