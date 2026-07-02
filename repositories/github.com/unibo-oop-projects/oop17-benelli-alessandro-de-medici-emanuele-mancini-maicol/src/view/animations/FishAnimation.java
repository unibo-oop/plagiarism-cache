package view.animations;

import java.util.Random;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.util.Duration;
import utilities.Directions;

/**
 * Class that manages fish's animation.
 *
 */
// CHECKSTYLE: MagicNumber OFF
public final class FishAnimation extends GeneralAnimationImpl {

    private int idx;
    private Directions random;

    /**
     * Constructor that creates a moving animation.
     * 
     * @param wTile
     *            animation's width
     * @param hTile
     *            animation's height
     * @param southLimit
     *            south bound for animation
     * @param northLimit
     *            north bound for animation
     * @param eastLimit
     *            east bound for animation
     * @param westLimit
     *            west bound for animation
     */
    public FishAnimation(final double wTile, final double hTile, final double southLimit, final double northLimit,
            final double eastLimit, final double westLimit) {
        super("/fishAnimationFrame/fishFrame.png");
        this.setX(westLimit);
        this.setY(northLimit);

        this.setDirectionImage(Directions.UP);
        this.setImageDimension(wTile, hTile);

        final AnimationTimer fishTimer = new AnimationTimer() {
            @Override
            public void handle(final long now) {

                if (getDirectionImage() == Directions.UP) {
                    setY(getY() + 0.7);
                    getImage().setTranslateY(getY());
                    getImage().setTranslateX(getX());
                    getImage().setRotate(0);
                    if (getY() >= (int) southLimit) {
                        idx = new Random().nextInt(Directions.values().length);
                        random = Directions.values()[idx];
                        setDirectionImage(random);
                    }

                } else if (getDirectionImage() == Directions.DOWN) {
                    setY(getY() - 0.7);
                    getImage().setTranslateY(getY());
                    getImage().setTranslateX(getX());
                    getImage().setRotate(180);
                    if (getY() <= (int) northLimit) {
                        idx = new Random().nextInt(Directions.values().length);
                        random = Directions.values()[idx];
                        setDirectionImage(random);
                    }
                } else if (getDirectionImage() == Directions.RIGHT) {
                    setX(getX() + 0.7);
                    getImage().setTranslateY(getY());
                    getImage().setTranslateX(getX());
                    getImage().setRotate(270);
                    if (getX() >= (int) eastLimit) {
                        idx = new Random().nextInt(Directions.values().length);
                        random = Directions.values()[idx];
                        setDirectionImage(random);
                    }
                } else if (getDirectionImage() == Directions.LEFT) {
                    setX(getX() - 0.7);
                    getImage().setTranslateY(getY());
                    getImage().setTranslateX(getX());
                    getImage().setRotate(90);
                    if (getX() <= (int) westLimit) {
                        idx = new Random().nextInt(Directions.values().length);
                        random = Directions.values()[idx];
                        setDirectionImage(random);
                    }
                }
            }
        };

        Animation fishAnimation = new SpriteAnimation(getImage(), Duration.millis(1), 0, 8, 8, 0, 0, 26, 28);
        fishAnimation.play();
        fishAnimation = new SpriteAnimation(getImage(), Duration.millis(1500), Animation.INDEFINITE, 8, 8, 0, 0, 26,
                28);
        fishAnimation.play();

        fishTimer.start();
    }

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
     */
    public FishAnimation(final double wTile, final double hTile, final double translateX, final double translateY) {
        super("/fishAnimationFrame/fishFrame.png");
        this.setDirectionImage(Directions.UP);
        this.setImageDimension(wTile, hTile);
        this.setFishAnimationNoMove(translateX, translateY);
    }

    private void setFishAnimationNoMove(final double translateX, final double tranlasteY) {
        Animation fishStaticAnimation = new SpriteAnimation(this.getImage(), Duration.millis(1), 0, 8, 8, 0, 0, 26, 26);
        fishStaticAnimation.play();
        fishStaticAnimation = new SpriteAnimation(this.getImage(), Duration.millis(1500), Animation.INDEFINITE, 8, 8, 0,
                0, 26, 26);
        fishStaticAnimation.play();

        this.getImage().setTranslateX(translateX);
        this.getImage().setTranslateY(tranlasteY);
        this.getImage().setRotate(270);

        final FadeTransition fishStaticFadeIn = new FadeTransition(Duration.millis(3000), this.getImage());
        fishStaticFadeIn.setFromValue(0.0);
        fishStaticFadeIn.setToValue(1.0);
        fishStaticFadeIn.setCycleCount(Animation.INDEFINITE);
        fishStaticFadeIn.setAutoReverse(true);
        fishStaticFadeIn.play();
    }
}
