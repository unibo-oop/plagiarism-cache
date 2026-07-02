package org.vise.model.playlist.queue;


import org.vise.model.playlist.Playlist;

/**
 * This is an implementation of PlaylistQueue.
 */
public abstract class AbstractPlaylistQueue implements PlaylistQueue {

    private final Playlist currentPlaylist;
    private int songIndex;

    /**
     * 
     * @param currentPlaylist
     *          The playlist in reproduction.
     * @param songIndex
     *          The index of the song.
     */
    public AbstractPlaylistQueue(final Playlist currentPlaylist, final int songIndex) {
        this.currentPlaylist = currentPlaylist;
        this.songIndex = songIndex;
    }

    /**
     * 
     * @param currentPlaylist
     *          The playlist in reproduction.
     */
    public AbstractPlaylistQueue(final Playlist currentPlaylist) {
        this.currentPlaylist = currentPlaylist;
        //this.songIndex = new Random().nextInt(this.getDimensionCurrentPlaylist() - 1);
    }

    /**
     * 
     */
    @Override
    public abstract int next();

    /**
     * 
     */
    @Override
    public abstract int prev();

    /**
     * 
     * @return
     *          The index of the song.
     */
    protected int getSongIndex() {
        return this.songIndex;
    }

    /**
     * 
     * @param index
     *          The new index of the song.
     */
    public void setSongIndex(final int index) {
        this.songIndex = index;
    }

    /**
     * Increment current index.
     */
    protected void inc() {
        this.modifySongIndex(1);
    }

    /**
     * Decrement current index.
     */
    protected void dec() {
        this.modifySongIndex(-1);
    }

    /**
     * 
     * @return
     *          The dimension of the playlist.
     */
    protected int getDimensionCurrentPlaylist() {
        return this.currentPlaylist.getDimension();
    }

    /**
     * 
     * @return
     *          The playlist in reproduction
     */
    protected Playlist getCurrentPlaylist() {
        return this.currentPlaylist;
    }

    /**
     * 
     * @param value
     *          The index to add at current index.
     */
    private void modifySongIndex(final int value) {
        this.songIndex = this.songIndex + value;
    }
}
