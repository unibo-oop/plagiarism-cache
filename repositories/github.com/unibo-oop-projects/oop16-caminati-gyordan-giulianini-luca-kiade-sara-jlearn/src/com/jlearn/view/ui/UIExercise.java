package com.jlearn.view.ui;

import com.jfoenix.controls.JFXListView;
import com.jlearn.controller.timer.TimerEventImpl;
import com.jlearn.view.screens.ExerciseScreenController;
import com.jlearn.view.utilities.QuestionToView;
import com.jlearn.view.utilities.ViewUtilities;
import com.jlearn.view.utilities.enums.NotificationType;
import com.jlearn.view.utilities.enums.NotificationType.Duration;

import eu.hansolo.enzo.notification.NotificationEvent;
import javafx.event.EventHandler;

/**
 * The interface for timer Listener to receive the change. The controllers of the {@link ViewUtilities} must implements
 * this interface.
 */
public interface UIExercise {

    /**
     * The method to implement the reaction for the change.
     *
     * @param ev
     *            the {@link TimerEventImpl} changed
     */
    void updateTimerView(TimerEventImpl ev);

    /**
     * Pass to the {@link ExerciseScreenController} all the data.
     *
     * @param dataOutToView
     *            the {@link QuestionToView} data structure.
     */
    void setViewModule(QuestionToView dataOutToView);

    /**
     * Reset the {@link JFXListView}.
     */
    void resetScreen();

    /**
     * Show a popup message with title and gravity.
     *
     * @param title
     *            the {@link String} title
     * @param message
     *            the {@link String} message
     * @param secondsDuration
     *            the {@link Duration} duration.
     * @param notiType
     *            the {@link NotificationType} duration
     * @param ev
     *            the {@link NotificationEvent} event
     */
    void showNotificationPopup(String title, String message, NotificationType.Duration secondsDuration,
            NotificationType notiType, EventHandler<NotificationEvent> ev);

}
