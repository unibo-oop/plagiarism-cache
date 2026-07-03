package org.gitgud.model.utils;

/**
 * Represents the return status of a command.
 */
public enum CommandStatus {
    /**
     * Command executed with errors.
     */
    ERROR,
    /**
     * Command executed successfully.
     */
    SUCCESS,
    /**
     * Command executed successfully but there are not updates.
     */
    SUCCESS_NO_UPDATES,
    /**
     * Command executed successfully and there are updates.
     */
    SUCCESS_WITH_UPDATES
}
