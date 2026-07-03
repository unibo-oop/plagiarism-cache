package org.gitgud.events;

/**
 * Classes implementing this interface receive updates when the escape key is pressed.
 */
public interface EscPressedListener {

    /**
     * Invoked when the escape key is pressed.
     */
    void onEscPressed();

}
