package it.unibo.arkanoid.controller;

import java.util.Iterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import it.unibo.arkanoid.model.Level;

/**
 * This class represent an Iterator of Stream of Level.
 *
 */
public class StreamIteratorLevel implements Iterator<Level> {

    private final Stream<Level> levelStream;
    private final Iterator<Level> it;
    private int currentIndex;

    /**
     * Constructor for an Iterator of Stream of Level.
     * 
     * @param levelStream
     *            The Stream of Level to iterate.
     */
    public StreamIteratorLevel(final Stream<Level> levelStream) {
        this.levelStream = levelStream;
        this.it = levelStream.iterator();
    }

    /**
     * True if has another level to iterate, false otherwise.
     */
    @Override
    public boolean hasNext() {
        if (it.hasNext()) {
            currentIndex++;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Return the next level.
     */
    @Override
    public Level next() {
        it.next();
        return levelStream.limit(currentIndex).collect(Collectors.toList()).get(currentIndex);
    }
}
