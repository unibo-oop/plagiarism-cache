package org.gitgud.application;

import org.gitgud.icons.IconLoader;
import org.gitgud.utils.Utils;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

class NotificationManagerImpl implements NotificationManager {

    private static final int NOTIFICATION_FADE_TRANSITION = 4000;
    private static final int NOTIFICATION_LEFT_TRANSLATE = 400;
    private static final int NOTIFICATION_MIN_HEIGHT = 95;
    private static final int MAX_NOTIFICATION = 4;

    private final Pane pane = new VBox();
    private boolean taskProgressActive;
    private int currentNotificationsCount;

    private final Pane paneTask;
    private final Label lTaskName = new Label();
    private final Label lTaskDescription = new Label();

    NotificationManagerImpl() {
        VBox.setMargin(pane, new Insets(10));

        final ProgressBar pBar = new ProgressBar(ProgressIndicator.INDETERMINATE_PROGRESS);
        pBar.setMaxWidth(Double.MAX_VALUE);
        pBar.getStyleClass().add("notification-bar");
        lTaskName.setGraphic(new ImageView(new Image(IconLoader.class.getResourceAsStream("pause.png"))));
        lTaskName.getStyleClass().add("notification-title");
        lTaskName.getStyleClass().add("task");
        lTaskName.setGraphicTextGap(8);
        lTaskDescription.getStyleClass().add("notification-description");
        lTaskDescription.setWrapText(true);
        paneTask = new VBox(lTaskName, lTaskDescription, pBar);
        VBox.setMargin(paneTask, new Insets(10, 0, 0, 0));
        paneTask.getStyleClass().add("notification");
        paneTask.setMinHeight(NOTIFICATION_MIN_HEIGHT);
    }

    @Override
    public Pane getPane() {
        return pane;
    }

    @Override
    public void hideTaskProgress() {
        taskProgressActive = false;
        pane.getChildren().remove(paneTask);
    }

    @Override
    public boolean showError(final String title, final String description) {
        return showNotification(title, description, "error");
    }

    @Override
    public boolean showInfo(final String title, final String description) {
        return showNotification(title, description, "info");
    }

    @Override
    public boolean showSuccess(final String title, final String description) {
        return showNotification(title, description, "success");
    }

    @Override
    public void showTaskProgress(final String taskName, final String taskDescription) {
        if (!taskProgressActive) {
            taskProgressActive = true;
            pane.getChildren().add(paneTask);
        }

        lTaskName.setText(taskName.toUpperCase(Utils.getLocale()));
        lTaskDescription.setText(taskDescription);
    }

    @Override
    public boolean showWarning(final String title, final String description) {
        return showNotification(title, description, "warning");
    }

    private boolean showNotification(final String title, final String description, final String type) {
        if (taskProgressActive || currentNotificationsCount >= MAX_NOTIFICATION) {
            return false;
        }

        final Pane notification;
        final ImageView ivIcon = new ImageView(new Image(IconLoader.class.getResourceAsStream(type + ".png")));
        final Label lTitle = new Label(title.toUpperCase(Utils.getLocale()));
        final Label lDescription = new Label(description);
        lTitle.setGraphic(ivIcon);
        lTitle.getStyleClass().add("notification-title");
        lTitle.getStyleClass().add(type);
        lTitle.setGraphicTextGap(8);
        lDescription.getStyleClass().add("notification-description");
        lDescription.setWrapText(true);
        notification = new VBox(lTitle, lDescription);
        VBox.setMargin(notification, new Insets(10, 0, 0, 0));
        notification.getStyleClass().add("notification");
        notification.setMinHeight(NOTIFICATION_MIN_HEIGHT);

        final TranslateTransition startTransition = new TranslateTransition(Duration.millis(400), notification);
        startTransition.setFromX(-NOTIFICATION_LEFT_TRANSLATE);
        startTransition.setToX(0);
        startTransition.play();
        startTransition.setOnFinished(event -> {
            final FadeTransition endTransition = new FadeTransition(Duration.millis(800), notification);
            endTransition.setDelay(new Duration(NOTIFICATION_FADE_TRANSITION));
            endTransition.setFromValue(1.0);
            endTransition.setToValue(0.0);
            endTransition.play();

            endTransition.setOnFinished(event1 -> {
                pane.getChildren().remove(notification);
                currentNotificationsCount--;
            });
        });

        pane.getChildren().add(notification);
        currentNotificationsCount++;

        return true;
    }

}
