package org.gitgud.application;

import org.gitgud.application.modal.ModalBoxController;
import org.gitgud.events.EscPressedListener;
import org.gitgud.events.ExitActionListener;

/**
 * The controller that manage the login of the entire application.
 */
public interface ApplicationController {

    /**
     * Add the listener for the escape key press event.
     *
     * @param epl
     *            the object that has to be notified
     */
    void addEscPressedListener(EscPressedListener epl);

    /**
     * Add the listener for the application exit event.
     *
     * @param eal
     *            the object that has to be notified
     */
    void addExitActionListener(ExitActionListener eal);

    /**
     * Close the about box.
     */
    void closeAboutBox();

    /**
     * Close the modal box.
     */
    void closeModalBox();

    /**
     * Close the repository box.
     */
    void closeRepositoryBox();

    /**
     * @return the modal box controller
     */
    ModalBoxController getModalBox();

    /**
     * Open the about box.
     */
    void openAboutBox();

    /**
     * Open the modal box.
     */
    void openModalBox();

    /**
     * @param menu
     *            the section to open
     */
    void openRepositoryBox(String menu);

    /**
     * Close the application.
     */
    void quit();

    /**
     * Reset the footer progress bar.
     */
    void resetTaskProgress();

    /**
     * Set the footer progress bar progress.
     *
     * @param progress
     *            the progress, in percentage
     */
    void setTaskProgress(double progress);

    /**
     * @param isWaiting
     *            true to set the application in waiting state
     */
    void setWaitingState(boolean isWaiting);

}
