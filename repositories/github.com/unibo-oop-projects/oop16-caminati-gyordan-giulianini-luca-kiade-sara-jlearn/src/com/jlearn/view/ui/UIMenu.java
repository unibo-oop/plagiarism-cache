package com.jlearn.view.ui;

import com.jfoenix.controls.JFXListView;
import com.jlearn.model.users.User;
import com.jlearn.view.screens.MenuScreenController;
import com.jlearn.view.utilities.ViewUtilities;
import com.jlearn.view.utilities.enums.NotificationType;
import com.jlearn.view.utilities.enums.NotificationType.Duration;

import eu.hansolo.enzo.notification.NotificationEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;

/**
 * Inteface only for {@link MenuScreenController}.
 * <p>
 * The controllers of the {@link ViewUtilities} must implements this interface.
 */
public interface UIMenu {
    /**
     * Set the login nickname.
     *
     * @param loginNickname
     *            the {@link String} nickname
     */
    void setProfileLoginNickname(String loginNickname);

    /**
     * Set the profile{@link Image}.
     *
     * @param image
     *            the {@link Image}
     */
    void setProfileImageView(Image image);

    /**
     * Add a {@link User} into the {@link JFXListView}.
     *
     * @param nickname
     *            the {@link String} nickname
     * @param image
     *            the {@link Image}
     */
    void addPlayerListView(String nickname, Image image);

    /**
     * Clear the {@link JFXListView}.
     */
    void clearListOfPlayers();

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