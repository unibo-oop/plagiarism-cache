package org.vise.model.playlist.queue;

/**
 * PlaylistQueue contains a strategy for scrolling a playlist.
 */
public interface PlaylistQueue {

    /**
     * 
     * @return
     *          The next index of the queue.
     */
    int next();

    /**
     * 
     * @return
     *          The prev index of the queue.
     */
    int prev();

    /**
     * 
     * @param index
     *          The index of the queue.
     */
    void setSongIndex(int index);
}
