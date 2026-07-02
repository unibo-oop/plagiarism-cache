package view;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 * 
 */
public final class AnimationManager {

    private static final double TOY = -20;
    private static final double DURATION = 0.3;

    /**
     * 
     */
    private AnimationManager() {
        // Not used
    }

    /**
     * 
     * @param obj 
     */
    public static void jumpAnimation(final Node obj) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Timeline jump = new Timeline(new KeyFrame(Duration.seconds(0.5), ev -> {
                    TranslateTransition jumpUp = new TranslateTransition();
                    jumpUp.setDuration(Duration.seconds(DURATION));
                    jumpUp.setNode(obj);
                    jumpUp.setToY(TOY);
                    jumpUp.play();
                    jumpUp.setOnFinished(e -> {
                        TranslateTransition jumpDown = new TranslateTransition();
                        jumpDown.setDuration(Duration.seconds(DURATION));
                        jumpDown.setNode(obj);
                        jumpDown.setToY(10);
                        jumpDown.play();
                    });
                }));
                jump.setCycleCount(Animation.INDEFINITE);
                jump.play();
            }
        }).start();
    }

    /**
     * @param transition 
     * @param obj 
     * @param duration 
     * @param toX 
     * @param toY 
     * @param event 
     */
    public static void traslate(final TranslateTransition transition, final ImageView obj, final double duration,
            final double toX, final double toY, final EventHandler<ActionEvent> event) {
        transition.setDuration(Duration.seconds(duration));
        transition.setNode(obj);
        transition.setToY(toY);
        transition.setToX(toX);
        transition.play();
        transition.setOnFinished(event);
    }

    /**
     * @param rotate 
     * @param obj 
     * @param duration 
     * @param angle 
     * @param cycleCount 
     * @param event 
     */
    public static void rotate(final RotateTransition rotate, final ImageView obj, final double duration,
            final double angle, final int cycleCount, final EventHandler<ActionEvent> event) {
        rotate.setNode(obj);
        rotate.setDuration(Duration.seconds(duration));
        rotate.setByAngle(angle);
        rotate.setCycleCount(cycleCount);
        rotate.play();
        rotate.setOnFinished(event);
    }

    /**
     * 
     * @param fade 
     * @param obj 
     * @param duration 
     * @param fromValue 
     * @param toValue 
     * @param cycleCount 
     * @param event 
     */
    public static void fade(final FadeTransition fade, final ImageView obj, final double duration,
            final double fromValue, final double toValue, final int cycleCount, final EventHandler<ActionEvent> event) {
        fade.setNode(obj);
        fade.setDuration(Duration.millis(duration));
        fade.setFromValue(fromValue);
        fade.setToValue(toValue);
        fade.setCycleCount(cycleCount);
        fade.setAutoReverse(false);
        fade.setOnFinished(event);
        fade.play();
    }
}
