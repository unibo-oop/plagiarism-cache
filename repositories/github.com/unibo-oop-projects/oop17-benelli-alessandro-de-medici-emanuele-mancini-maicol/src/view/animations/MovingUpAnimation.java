package view.animations;

import controller.LevelManager;
import controller.LevelManagerImpl;
import javafx.animation.Animation;
import javafx.animation.Animation.Status;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.util.Duration;
import utilities.Directions;

/**
 * Class that manages moving up animations.
 *
 */
// CHECKSTYLE: MagicNumber OFF
public final class MovingUpAnimation extends GeneralAnimationImpl {

    private final LevelManager levelManager;
    private final int levelNumberStart;
    private final FadeTransition movingUpFadeOut;
    private final AnimationTimer movingUpTimer;

    /**
     * Constructor that creates a moving animation for smoke.
     * 
     * @param wTile
     *            animation's width
     * @param hTile
     *            animation's height
     * @param startX
     *            smoke's start position on x axis
     * @param startY
     *            smoke's start position on y axis
     * @param duration
     *            of the animation
     * @param rotateAngle
     *            rotation of the image
     * @param path
     *            of the image
     */
    public MovingUpAnimation(final double wTile, final double hTile, final double startX, final double startY,
            final int duration, final double rotateAngle, final String path) {
        super(path);
        this.levelManager = LevelManagerImpl.get();
        this.levelNumberStart = this.levelManager.getLevelNumber();
        this.setImageDimension(wTile, hTile);
        this.setDirectionImage(Directions.RIGHT);

        this.movingUpFadeOut = new FadeTransition(Duration.millis(duration), this.getImage());
        this.movingUpFadeOut.setFromValue(1.0);
        this.movingUpFadeOut.setToValue(0.0);

        this.movingUpTimer = new AnimationTimer() {

            @Override
            public void handle(final long now) {
                final int levelNumberNext = levelManager.getLevelNumber();

                if (levelNumberStart == levelNumberNext) {
                    if (getDirectionImage() == Directions.RIGHT) {
                        setY(getY() - 0.1);
                        setX(getX() + 0.025);
                        getImage().setTranslateY(getY());
                        getImage().setTranslateX(getX());
                        if (getX() >= (int) (startX + 1)) {
                            setDirectionImage(Directions.LEFT);
                        }
                    } else if (getDirectionImage() == Directions.LEFT) {
                        setY(getY() - 0.1);
                        setX(getX() - 0.025);
                        getImage().setTranslateY(getY());
                        getImage().setTranslateX(getX());
                        if (getX() <= (int) (startX - 1)) {
                            setDirectionImage(Directions.RIGHT);
                        }
                    }

                    if (getY() < startY - 5 && movingUpFadeOut.getStatus() != Animation.Status.RUNNING) {
                        movingUpFadeOut.play();
                    }
                }
            }
        };

        this.setMovingUpAnimation(startX, startY, rotateAngle);
    }

    private void setMovingUpAnimation(final double startX, final double startY, final double rotateAngle) {
        this.setX(startX);
        this.setY(startY);
        this.getImage().setRotate(rotateAngle);

        final FadeTransition movingUpFadeIn = new FadeTransition(Duration.millis(1000), this.getImage());
        movingUpFadeIn.setFromValue(0.0);
        movingUpFadeIn.setToValue(1.0);

        this.movingUpFadeOut.statusProperty().addListener(new ChangeListener<Status>() {
            @Override
            public void changed(final ObservableValue<? extends Status> observableValue, final Status oldValue,
                    final Status newValue) {
                if (newValue == Status.STOPPED) {
                    setY(startY);
                    getImage().setTranslateY(getY());
                    getImage().setTranslateX(getX());
                    movingUpFadeIn.play();
                }
            }
        });
        this.movingUpTimer.start();
    }
}
