package it.unibo.bmbman.view.utilities;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * An implementation of {@link Animation}.
 */
public final class AnimationImpl implements Animation {

    private final List<Image> animation = new ArrayList<>();
    /**
     * Construct {@link Animation}.
     */
    private AnimationImpl() {
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Image> getImageAt(final int index) {
        return index < this.animation.size() ? Optional.of(this.animation.get(index)) : Optional.empty();
    }

   private void addFrame(final Image frame) {
        this.animation.add(frame);
    }
   /**
    * Create an {@link Animation} from path of a spriteSheet which has just one row of image.
    * @param path of {@link SpriteSheet}
    * @param frame number of image in the animation
    * @param dimension dimension of each image
    * @return {@link Animation}
    */
    public static Animation createAnimation(final String path, final int frame, final int dimension) {
        final AnimationImpl anim = new AnimationImpl();
        final SpriteSheet ss = new SpriteSheet(path);
        for (int i = 1; i <= frame; i++) {
            anim.addFrame(new Sprite(ss, i, 1, dimension).getImage());
        }
        return anim;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public AnimationIterator createInfiniteIterator() {
        return new InfiniteAnimationIterator(this);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public int size() {
        return this.animation.size();
    }
}
