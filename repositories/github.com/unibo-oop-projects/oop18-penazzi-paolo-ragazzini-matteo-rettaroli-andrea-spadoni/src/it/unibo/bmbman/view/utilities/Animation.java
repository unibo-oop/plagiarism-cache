package it.unibo.bmbman.view.utilities;

import java.awt.Image;
import java.util.Optional;
/**
 * Used to model the concept of Animation in our game.
 */
public interface Animation {
    /**
     * Used to know how many frame the animation has.
     * @return number of image in the {@link Animation}
     */
    int size();
    /**
     * Used to get a specific image of the animation.
     * @param index the index of the image
     * @return an {@link Image}
     */
    Optional<Image> getImageAt(int index);
    /**
     * Used to create an {@link AnimationIterator} to show sequentialy each frame of the Animation.
     * @return an implementation of {@link AnimationIterator}.
     */
    AnimationIterator createInfiniteIterator();
}
