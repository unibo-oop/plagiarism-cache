package com.biaren.utility.viewparts;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * Date time box to show current time update dynamically
 * @author nbrunetti
 *
 */
public class DateTimeBox extends VBox {

    private Label timeLabel;
    private Label dateLabel;
    
    /**
     * Create a new date time box to show current time and date
     */
    public DateTimeBox() {
        this.timeLabel = new Label();
        this.dateLabel = new Label();
        this.bindToTime();
        this.setLayout();
    }
    
    private void bindToTime() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0), new EventHandler<ActionEvent>() {
            
            @Override 
            public void handle(ActionEvent actionEvent) {
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                timeLabel.setText(timeFormat.format(calendar.getTime()));
                dateLabel.setText(dateFormat.format(new Date()));
            }
        }), new KeyFrame(Duration.seconds(1)));
        
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
    
    private void setLayout() {
        this.getChildren().addAll(this.dateLabel, this.timeLabel);
    }
}
