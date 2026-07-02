package view.animations;

import java.util.Random;
import javafx.animation.Animation;
import javafx.animation.Animation.Status;
import javafx.animation.AnimationTimer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.effect.ColorAdjust;
import javafx.util.Duration;

/**
 * Class that manages fireworks' animation.
 *
 */
// CHECKSTYLE: MagicNumber OFF
public final class FireworksAnimation extends GeneralAnimationImpl {

    private double color;
    private final Random r;
    private final ColorAdjust colorAdjust;
    private final AnimationTimer fireworksTimer;
    private Animation fireworks;

    /**
     * Constructor that creates a moving animation.
     * 
     * @param wTile
     *            animation's width
     * @param hTile
     *            animation's height
     * @param startX
     *            fireworks' start position on x axis
     * @param startY
     *            fireworks' start position on y axis
     * @param duration
     *            of the animation
     */
    public FireworksAnimation(final double wTile, final double hTile, final double startX, final double startY,
            final int duration) {
        super("/fireworksAnimationFrame/fireworksFrame.png");
        this.r = new Random();
        this.colorAdjust = new ColorAdjust();
        this.setImageDimension(wTile, hTile);

        this.fireworksTimer = new AnimationTimer() {
            @Override
            public void handle(final long now) {
                setY(getY() + 0.5);
                getImage().setTranslateY(getY());
                getImage().setTranslateX(getX());
                getImage().setRotate(0);
            }
        };
        this.setFireworksAnimation(startX, startY, duration);
    }

    // Starts fireworks' animation and sets the down movement.
    private void setFireworksAnimation(final double startX, final double startY, final int duration) {
        this.setX(startX);
        this.setY(startY);

        this.fireworks = new SpriteAnimation(this.getImage(), Duration.millis(1), 1, 50, 5, 0, 0, 192, 192);
        this.fireworks.play();
        this.fireworks = new SpriteAnimation(this.getImage(), Duration.millis(duration), 1, 50, 5, 0, 0, 192, 192);
        this.fireworks.play();

        this.fireworks.statusProperty().addListener(new ChangeListener<Status>() {
            @Override
            public void changed(final ObservableValue<? extends Status> observableValue, final Status oldValue,
                    final Status newValue) {
                if (newValue == Status.STOPPED) {
                    color = -1 + (1 - (-1)) * r.nextDouble();
                    setY(startY);
                    getImage().setTranslateY(getY());
                    getImage().setTranslateX(getX());
                    fireworks.play();
                    colorAdjust.setHue(color);
                    getImage().setEffect(colorAdjust);
                }
            }
        });

        this.fireworksTimer.start();
    }
}
