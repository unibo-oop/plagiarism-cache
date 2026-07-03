package org.gitgud.events.utils;

/**
 * Classes implementing this interface receive notifications when the an unmanaged exception is throwed.
 */
public interface GlobalExceptionThrowedListener {

    /**
     * Invoked when an unmanaged exception is throwed.
     *
     * @param message
     *            the exception message
     */
    void onGlobalExceptionThrowed(String message);

}
