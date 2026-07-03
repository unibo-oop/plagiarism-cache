package com.jlearn.view.ui;

import java.util.List;

import com.jlearn.model.users.User;
import com.jlearn.model.utilities.Pair;
import com.jlearn.view.screens.StatisticsScreenController;
import com.jlearn.view.utilities.ViewUtilities;
import com.jlearn.view.utilities.enums.NotificationType;
import com.jlearn.view.utilities.enums.NotificationType.Duration;

import eu.hansolo.enzo.notification.NotificationEvent;
import javafx.event.EventHandler;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.image.Image;

/**
 * Inteface only for {@link StatisticsScreenController}
 * <p>
 * The controllers of the {@link ViewUtilities} must implements this interface.
 */
public interface UIStatistics {
    /**
     * Set the achivemetns labels.
     *
     * @param easy
     *            the easy level*
     * @param medium
     *            the medium level*
     * @param hard
     *            the hard level
     */

    void setAchivements(int easy, int medium, int hard);

    /**
     * Set {@link User} bios.
     *
     * @param user
     *            the {@link User} user
     */
    void setPersonStatistics(User user);

    /**
     * Set the profile{@link Image}.
     *
     * @param image
     *            the {@link Image}
     */
    void setProfileImageView(Image image);

    /**
     * Update the {@link BarChart} Modality.
     *
     * @param lista
     *            the {@link List}
     */
    void updateBarChartModality(List<Pair<String, List<Pair<String, Integer>>>> lista);

    /**
     * Update the {@link LineChart}.
     *
     * @param lista
     *            the list of modality
     * @param level
     *            the {@link String} label
     */
    void updateLineChart(List<Pair<String, Integer>> lista, String level);

    /**
     * Update the {@link PieChart}.
     * 
     * @param lista
     *            the {@link List} of data
     */
    void updatePieChart(List<Pair<String, Integer>> lista);

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
