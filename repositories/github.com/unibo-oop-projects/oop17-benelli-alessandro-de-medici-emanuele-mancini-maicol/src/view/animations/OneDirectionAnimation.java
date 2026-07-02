package view.animations;

import javafx.animation.AnimationTimer;
import utilities.Directions;

/**
 * Class that manages moving animations in one direction.
 *
 */
// CHECKSTYLE: MagicNumber OFF
public final class OneDirectionAnimation extends GeneralAnimationImpl {

    private long lastUpdate;

    /**
     * Constructor that creates a moving animation in one direction.
     * 
     * @param path
     *            of the image
     * @param wTile
     *            animation's width
     * @param hTile
     *            animation's height
     * @param translateX
     *            element's position on x axis
     * @param translateY
     *            element's position on y axis
     * @param rotateAngle
     *            rotation of the image
     * @param animationSpeed
     *            speed of animation
     */
    public OneDirectionAnimation(final String path, final double wTile, final double hTile, final double translateX,
            final double translateY, final double rotateAngle, final double animationSpeed) {
        super(path);
        this.setImageDimension(wTile, hTile);
        this.setDirectionImage(Directions.RIGHT);

        this.lastUpdate = 0;
        this.setX(translateX);
        this.setY(translateY);
        this.getImage().setTranslateX(translateX);
        this.getImage().setTranslateY(translateY);
        this.getImage().setRotate(rotateAngle);
        final AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(final long now) {
                if (now - lastUpdate >= animationSpeed) {
                    if (getDirectionImage() == Directions.RIGHT) {
                        setX(getX() + 0.1);
                        getImage().setTranslateX(getX());
                        getImage().setTranslateY(getY());
                        lastUpdate = now;
                        if (getX() >= (int) (translateX + 5)) {
                            setDirectionImage(Directions.LEFT);
                        }
                    } else if (getDirectionImage() == Directions.LEFT) {
                        setX(getX() - 0.1);
                        getImage().setTranslateX(getX());
                        getImage().setTranslateY(getY());
                        lastUpdate = now;
                        if (getX() <= (int) (translateX - 5)) {
                            setDirectionImage(Directions.RIGHT);
                        }
                    }
                }
            }
        };
        animationTimer.start();
    }
}
