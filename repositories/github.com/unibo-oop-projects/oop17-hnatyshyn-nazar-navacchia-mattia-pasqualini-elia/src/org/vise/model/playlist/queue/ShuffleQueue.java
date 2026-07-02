package org.vise.model.playlist.queue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.vise.model.playlist.Playlist;

/**
 *  ShuffleQueue reproduce songs in random order.
 */
public class ShuffleQueue extends AbstractPlaylistQueue {

    private final List<Integer> list = new ArrayList<>();
    private int listIndex;
    private final int dimension;

    /**
     * 
     * @param currentPlaylist
     *          The playlist in reproduction
     */
    public ShuffleQueue(final Playlist currentPlaylist) {
        super(currentPlaylist);
        this.setSongIndex(0);
        this.listIndex = 0;
        this.dimension = this.getDimensionCurrentPlaylist();
        this.shuffle(this.getSongIndex());
    }

    /**
     * 
     * @param currentPlaylist
     *          The playlist in reproduction
     * @param songIndex
     *          The index of the song
     */
    public ShuffleQueue(final Playlist currentPlaylist, final int songIndex) {
        super(currentPlaylist, songIndex);
        this.listIndex = 0;
        this.dimension = this.getDimensionCurrentPlaylist();
        this.shuffle(songIndex);
    }

    /**
     * 
     */
    @Override
    public int next() {
        if (this.listIndex >= this.dimension - 1) {
            this.listIndex = 0;
        } else {
            this.listIndex++;
        }
        return this.list.get(this.listIndex);
    }

    /**
     * 
     */
    @Override
    public int prev() {
        if (this.listIndex <= 0) {
            this.listIndex = 0;
        } else {
            this.listIndex--;
        }
        return this.list.get(this.listIndex);
    }

    private void shuffle(final int index) {
        this.list.clear();
        for (int i = 0; i < this.dimension; i++) {
            this.list.add(i);
        }
        this.list.remove(index);
        Collections.shuffle(this.list);
        this.list.add(0, index);
    }

}
