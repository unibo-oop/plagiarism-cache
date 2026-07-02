package justanotherchessgame.view.game;

import javafx.animation.Animation;
import javafx.animation.Animation.Status;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

/**
 * Class used to create and manage the timer in the view.
 */
public class TimerViewImpl extends Label implements TimerView {

    private final boolean way;
    private final int limit;
    private Timeline timeline;
    private final IntegerProperty timeSeconds;

    /**
     * Class constructor.
     * @param way boolean used to set if the timer will be ascending or descending.
     * @param limit the max or min, depending on the way, value the timer can have.
     */
    public TimerViewImpl(final boolean way, final int limit) {
        this.limit = limit;
        this.way = way;
        this.setTextFill(Color.RED);
        this.setContentDisplay(ContentDisplay.CENTER);
        timeSeconds = new SimpleIntegerProperty(limit);
        //this.textProperty().bind(timeSeconds.asString());
        this.getStyleClass().add("Timer");
        initializeTimer();
    }

    /**
     * Function used to update the timer time.
     */
    private void updateTime() {
        // increment seconds
        final int seconds = timeSeconds.get();
        timeSeconds.set(way ? seconds + 1 : seconds - 1);
        this.setText(Integer.toString(timeSeconds.get() / 3600) + ": " + Integer.toString(timeSeconds.get() / 60) + " : " + Integer.toString(timeSeconds.get() % 60));
    }

    @Override
    public final void initializeTimer() {
        //loading the font used for the timer
        Font.loadFont(getClass().getResourceAsStream("digital-7.ttf"), 10);
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), evt -> updateTime())); 
        // repeat over and over again
        timeline.setCycleCount(Animation.INDEFINITE); 
        timeSeconds.set(limit);
    }

    @Override
    public final void startTimer() {
        timeline.play();
    }

    @Override
    public final void stopTimer() {
        timeline.stop();
    }

    @Override
    public final boolean isActive() {
        return this.timeline.getStatus() == Status.RUNNING;
    }

}
