package view.animations;

import javafx.animation.Animation;
import javafx.util.Duration;

/**
 * Class that manages character's animation.
 *
 */
// CHECKSTYLE: MagicNumber OFF
public final class CharAnimation extends GeneralAnimationImpl {

    /**
     * Constructor that creates a static animation.
     * 
     * @param wTile
     *            animation's width
     * @param hTile
     *            animation's height
     * @param translateX
     *            element's position on x axis
     * @param translateY
     *            element's position on y axis
     * @param path
     *            of the image
     */
    public CharAnimation(final int wTile, final int hTile, final double translateX, final double translateY,
            final String path) {
        super(path);
        this.setImageDimension(wTile, hTile);
        this.setCharAnimationNoMove(translateX, translateY);
    }

    private void setCharAnimationNoMove(final double translateX, final double translateY) {
        Animation charAnim = new SpriteAnimation(this.getImage(), Duration.millis(1), 0, 4, 1, 0, 0, 28, 48);
        charAnim.play();
        charAnim = new SpriteAnimation(this.getImage(), Duration.millis(10000), Animation.INDEFINITE, 4, 1, 0, 0, 28,
                48);
        charAnim.play();
        this.getImage().setTranslateX(translateX);
        this.getImage().setTranslateY(translateY);
    }
}
