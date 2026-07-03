package org.gitgud.events.application;

/**
 * A simply listener used in the modal controller.
 */
public interface ModalListener {

    /**
     * Invoked when the cancel button is clicked.
     */
    void onCancelButtonAction();

    /**
     * Invoked when the primary button is clicked.
     */
    void onPrimaryButtonAction();

    /**
     * Invoked when the secondary button is clicked.
     */
    void onSecondaryButtonAction();

}
