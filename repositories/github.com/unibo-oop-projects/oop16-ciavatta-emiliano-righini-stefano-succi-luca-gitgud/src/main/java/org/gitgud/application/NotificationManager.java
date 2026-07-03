package org.gitgud.application;

import org.gitgud.application.node.Panel;

interface NotificationManager extends Panel {

    void hideTaskProgress();

    boolean showError(String title, String description);

    boolean showInfo(String title, String description);

    boolean showSuccess(String title, String description);

    void showTaskProgress(String taskName, String taskDescription);

    boolean showWarning(String title, String description);

}
