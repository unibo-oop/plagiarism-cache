package com.jlearn.view.ui;

import com.jlearn.view.screens.TheoryScreenController;
import com.jlearn.view.utilities.ViewUtilities;
import com.jlearn.view.utilities.enums.NotificationType;
import com.jlearn.view.utilities.enums.NotificationType.Duration;

import eu.hansolo.enzo.notification.NotificationEvent;
import javafx.event.EventHandler;

/**
 * Inteface only for {@link TheoryScreenController}.
 * <p>
 * The controllers of the {@link ViewUtilities} must implements this interface.
 */
public interface UITheory {
    /**
     * Show the pdf in the browser.
     *
     * @param path
     *            the {@link String} path
     */
    void showPdf(String path);

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
