package org.vise.view;

/**
 * 
 * The interface that contains the main methods of a Screen Controller.
 *
 */
public interface UI {

    /**
     * Display the main scene with the correct screen previously loaded.
     */
    void show();

    /**
     * Execute all the operation that a view has to do before its visualization.
     */
    void preShowOperation();

    /**
     * Show a pop-up message.
     * 
     * @param textNotification
     *          The text to be shown with the notification.
     */
    void showNotification(String textNotification);
}
