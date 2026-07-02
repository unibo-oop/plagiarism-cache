package view.interfaces;

/**
 * Defines some method for a UI that permits to confirm something.
 */
public interface ConfermableView {

    /**
     * Confirms the current data.
     */
    void confirm();

    /**
     * Enables the user to request a confirm.
     */
    void enableConfirm();

    /**
     * Prevent the user to confirm the data.
     */
    void disableConfirm();
}
