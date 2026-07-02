package view.animations;

import java.util.Random;
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
import utilities.Utilities;

/**
 * Class that manages moving down animations.
 *
 */
// CHECKSTYLE: MagicNumber OFF
public final class MovingDownAnimation extends GeneralAnimationImpl {

    private final LevelManager levelManager;
    private final int levelNumberStart;
    private int levelNumberNext;
    private double startMovingDownX;
    private double startMovingDownY;
    private final Random r;
    private final FadeTransition movingDownFadeOut;
    private final AnimationTimer movingDownTimer;

    /**
     * Constructor that creates a moving animation for a leaf.
     * 
     * @param wTile
     *            animation's width
     * @param hTile
     *            animation's height
     * @param startX
     *            leaf's start position on x axis
     * @param startY
     *            leaf's start position on y axis
     * @param duration
     *            of the animation
     * @param rotateAngle
     *            rotation of the image
     * @param path
     *            of the image
     */
    public MovingDownAnimation(final double wTile, final double hTile, final double startX, final double startY,
            final int duration, final double rotateAngle, final String path) {
        super(path);
        this.levelManager = LevelManagerImpl.get();
        this.levelNumberStart = this.levelManager.getLevelNumber();
        this.r = new Random();
        this.setImageDimension(wTile, hTile);
        this.setDirectionImage(Directions.RIGHT);

        this.movingDownFadeOut = new FadeTransition(Duration.millis(duration), this.getImage());
        this.movingDownFadeOut.setFromValue(1.0);
        this.movingDownFadeOut.setToValue(0.0);

        this.movingDownTimer = new AnimationTimer() {

            @Override
            public void handle(final long now) {
                levelNumberNext = levelManager.getLevelNumber();

                if (levelNumberStart == levelNumberNext) {
                    if (getDirectionImage() == Directions.RIGHT) {
                        setY(getY() + 0.2);
                        setX(getX() + 0.1);
                        getImage().setTranslateY(getY());
                        getImage().setTranslateX(getX());
                        if (getX() >= (int) (startX + 3)) {
                            setDirectionImage(Directions.LEFT);
                        }
                    } else if (getDirectionImage() == Directions.LEFT) {
                        setY(getY() + 0.2);
                        setX(getX() - 0.1);
                        getImage().setTranslateY(getY());
                        getImage().setTranslateX(getX());
                        if (getX() <= (int) (startX - 3)) {
                            setDirectionImage(Directions.RIGHT);
                        }
                    }

                    if (getY() > startY + 30 && movingDownFadeOut.getStatus() != Animation.Status.RUNNING) {
                        movingDownFadeOut.play();
                    }
                }
            }
        };

        this.setMovingDownAnimation(startX, startY, rotateAngle);
    }

    /**
     * Constructor that creates a moving animation for snow.
     * 
     * @param wTile
     *            animation's width
     * @param hTile
     *            animation's height
     * @param duration
     *            of the animation
     * @param rotateAngle
     *            rotation of the image
     * @param path
     *            of the image
     */
    public MovingDownAnimation(final double wTile, final double hTile, final int duration, final double rotateAngle,
            final String path) {
        super(path);
        this.levelManager = LevelManagerImpl.get();
        this.levelNumberStart = this.levelManager.getLevelNumber();
        this.r = new Random();
        this.setImageDimension(wTile, hTile);
        this.getImage().setRotate(rotateAngle);
        this.setDirectionImage(Directions.RIGHT);

        this.movingDownFadeOut = new FadeTransition(Duration.millis(duration), this.getImage());
        this.movingDownFadeOut.setFromValue(1.0);
        this.movingDownFadeOut.setToValue(0.0);

        this.setX(-0 + (Utilities.W - (-0)) * r.nextDouble());
        this.setY(-0 + (Utilities.H - (-0)) * r.nextDouble());
        while (this.getX() > ((Utilities.W / 21) * 4) && this.getX() < ((Utilities.W / 21) * 16)
                && this.getY() > ((Utilities.H / 21) * 4) && this.getY() < ((Utilities.H / 21) * 14)) {
            this.setX(-0 + (Utilities.W - (-0)) * r.nextDouble());
            this.setY(-0 + (Utilities.H - (-0)) * r.nextDouble());
        }

        this.startMovingDownX = this.getX();
        this.startMovingDownY = this.getY();

        movingDownTimer = new AnimationTimer() {
            @Override
            public void handle(final long now) {
                levelNumberNext = levelManager.getLevelNumber();

                if (levelNumberStart == levelNumberNext) {
                    if (getDirectionImage() == Directions.RIGHT) {
                        setY(getY() + 0.2);
                        setX(getX() + 0.1);
                        getImage().setTranslateY(getY());
                        getImage().setTranslateX(getX());
                        if (getX() >= (int) (startMovingDownX + 3)) {
                            setDirectionImage(Directions.LEFT);
                        }
                    } else if (getDirectionImage() == Directions.LEFT) {
                        setY(getY() + 0.2);
                        setX(getX() - 0.1);
                        getImage().setTranslateY(getY());
                        getImage().setTranslateX(getX());
                        if (getX() <= (int) (startMovingDownX - 3)) {
                            setDirectionImage(Directions.RIGHT);
                        }
                    }

                    if (getY() > startMovingDownY + 50 && movingDownFadeOut.getStatus() != Animation.Status.RUNNING) {
                        movingDownFadeOut.play();
                    }
                }
            }
        };

        this.setMovingDownAnimationRandom(duration);
    }

    private void setMovingDownAnimation(final double startX, final double startY, final double rotateAngle) {
        this.setX(startX);
        this.setY(startY);
        this.getImage().setRotate(rotateAngle);

        this.movingDownFadeOut.statusProperty().addListener(new ChangeListener<Status>() {
            @Override
            public void changed(final ObservableValue<? extends Status> observableValue, final Status oldValue,
                    final Status newValue) {
                if (newValue == Status.STOPPED) {
                    setY(startY);
                    getImage().setTranslateY(getY());
                    getImage().setTranslateX(getX());
                    getImage().setOpacity(1.0);
                }
            }
        });
        this.movingDownTimer.start();
    }

    private void setMovingDownAnimationRandom(final double duration) {
        final FadeTransition movingDownFadeIn = new FadeTransition(Duration.millis(duration), this.getImage());
        movingDownFadeIn.setFromValue(0.0);
        movingDownFadeIn.setToValue(1.0);

        this.movingDownFadeOut.statusProperty().addListener(new ChangeListener<Status>() {
            @Override
            public void changed(final ObservableValue<? extends Status> observableValue, final Status oldValue,
                    final Status newValue) {
                if (newValue == Status.STOPPED) {
                    setX(-0 + (Utilities.W - (-0)) * r.nextDouble());
                    setY(-0 + (Utilities.H - (-0)) * r.nextDouble());
                    while (getX() > ((Utilities.W / 21) * 4) && getX() < ((Utilities.W / 21) * 16)
                            && getY() > ((Utilities.H / 21) * 4) && getY() < ((Utilities.H / 21) * 14)) {
                        setX(-0 + (Utilities.W - (-0)) * r.nextDouble());
                        setY(-0 + (Utilities.H - (-0)) * r.nextDouble());
                    }
                    startMovingDownX = getX();
                    startMovingDownY = getY();
                    getImage().setTranslateY(getY());
                    getImage().setTranslateX(getX());
                    movingDownFadeIn.play();
                }
            }
        });
        this.movingDownTimer.start();
    }
}
