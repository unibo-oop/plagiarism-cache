package it.unibo.bmbman.view.utilities;

import java.awt.Image;
/**
 * An implementation of {@link AnimationIterator} to create an infinte iterator of Image.
 */
public class InfiniteAnimationIterator implements AnimationIterator {

    private final Animation animation;
    private int frame;
    /**
     * Construct an {@link InfiniteAnimationIterator}.
     * @param animation the {@link Animation}
     */
    public InfiniteAnimationIterator(final Animation animation) {
        this.animation = animation;
        this.frame = -1;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasNext() {
        return true;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Image getNextImage() {
        frame++;
        if (frame >= this.animation.size()) {
            frame = 0;
        }
        return this.animation.getImageAt(frame).get();
    }

}
