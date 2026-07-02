package org.vise.model.playlist.queue;

import org.vise.model.playlist.Playlist;

/**
 *  LinearQueue reproduce songs following their playlist's order.
 */
public class LinearQueue extends AbstractPlaylistQueue {

    /**
     * 
     * @param currentPlaylist
     *          The playlist in reproduction.
     */
    public LinearQueue(final Playlist currentPlaylist) {
        super(currentPlaylist);
        this.setSongIndex(0);
    }

    /**
     * 
     * @param currentPlaylist
     *          The playlist in reproduction.
     * @param songIndex
     *          The index of the song.
     */
    public LinearQueue(final Playlist currentPlaylist, final int songIndex) {
        super(currentPlaylist, songIndex);
    }

    /**
     * 
     */
    @Override
    public int next() {
        if (this.getSongIndex() >= this.getDimensionCurrentPlaylist() - 1) {
            this.setSongIndex(0);
        } else {
            this.inc();
        }
        return this.getSongIndex();
    }

    /**
     * 
     */
    @Override
    public int prev() {
        if (this.getSongIndex() <= 0) {
            this.setSongIndex(0);
        } else {
            this.dec();
        }
        return this.getSongIndex();
    }
}
