package view.animations;

import javafx.animation.Animation;
import javafx.util.Duration;

/**
 * Class that manages cat's animation.
 *
 */
// CHECKSTYLE: MagicNumber OFF
public final class CatAnimation extends GeneralAnimationImpl {

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
    public CatAnimation(final double wTile, final double hTile, final double translateX, final double translateY,
            final String path) {
        super(path);

        this.setImageDimension(wTile, hTile);
        this.setCatAnimationNoMove(translateX, translateY);
    }

    private void setCatAnimationNoMove(final double translateX, final double translateY) {
        Animation catAnim = new SpriteAnimation(this.getImage(), Duration.millis(1), 0, 3, 3, 0, 0, 32, 32);
        catAnim.play();
        catAnim = new SpriteAnimation(this.getImage(), Duration.millis(2500), Animation.INDEFINITE, 3, 3, 0, 0, 32, 32);
        catAnim.play();
        catAnim.setAutoReverse(true);
        this.getImage().setTranslateX(translateX);
        this.getImage().setTranslateY(translateY);
    }
}
