package it.unibo.bmbman.view.utilities;

import java.awt.Image;

/**
 * An interface for an iterator of Animation
 * each element is an Image.
 */
public interface AnimationIterator {
    /**
     * Used to know if there is another image next.
     * @return true if there is.
     */
    boolean hasNext();
    /**
     * Used to get the next frame of the {@link Animation}.
     * @return next {@link Image} of the {@link Animation}
     */
    Image getNextImage();

}
