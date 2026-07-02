package org.vise.model.playlist.song;

import java.io.IOException;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

/**
 * This is an implemetation of Metadata.
 */
public class MetadataImpl implements Metadata {

    private static final int SECONDS = 60;
    private Mp3File song;
    private String title;
    private String artist;
    private String album;
    private long duration;

    /**
     * Create Metadata for the song.
     * @param audioPath
     *          Path of the song.
     */
    public MetadataImpl(final String audioPath) {
        try {
            this.song = new Mp3File(audioPath);
            this.getMetadata();
        } catch (UnsupportedTagException | InvalidDataException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Check if a field is null.
     * @param condition
     *          Condition to be checked.
     * @param field
     *          THe field to be returned.
     */
    private String checkFields(final String field) {
        if (field == null) {
            return "Non disponibile";
        }
        return field;
    }

    /**
     * Fill the fields with metadate of the song or with default value.
     */
    private void getMetadata() {
        if (this.song.hasId3v2Tag()) {
            this.title = this.song.getId3v2Tag().getTitle();
            this.artist = this.song.getId3v2Tag().getArtist();
            this.album = this.song.getId3v2Tag().getAlbum();
            this.duration = this.song.getLengthInSeconds();
        } else {
            System.out.println("Metadati non pervenuti.");
            //this.fillMetadata();
        }
    }

    /**
     * 
     */
    @Override
    public String getTitle() {
        return this.checkFields(this.title);
    }

    /**
     * 
     */
    @Override
    public String getArtist() {
        return this.checkFields(this.artist);
    }

    /**
     * 
     */
    @Override
    public String getAlbum() {
        return this.checkFields(this.album);
    }

    /**
     * 
     */
    @Override
    public String getDuration() {
        return (this.duration % SECONDS < 10) ? this.duration / SECONDS + ":0" + this.duration % SECONDS : this.duration / SECONDS + ":" + this.duration % SECONDS;
    }

    /**
     * 
     */
    @Override
    public void modifyMeta(final String title, final String artist, final String album) {
        this.title = title;
        this.artist = artist;
        this.album = album;
    }

    /**
     * 
     */
    /*private void fillMetadata() {
        final Field [] fields = this.getClass().getDeclaredFields();
        for (final Field field : fields) {
            if (!field.getType().equals(Mp3File.class) && !field.getType().equals(long.class)) {
                try {
                    field.set(this.getClass(), "Non disponibile");
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }*/

}
