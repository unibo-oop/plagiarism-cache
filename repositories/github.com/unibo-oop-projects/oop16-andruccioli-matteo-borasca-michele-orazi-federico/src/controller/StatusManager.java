package controller;

import utils.enumerations.Status;

/**
 * The responsible to manage the game state. It records the actual {@link Status} of the game
 * and allows the user to read or modify that. 
 */
public final class StatusManager {

    private Status actualStatus;
    private static final StatusManager SINGLETON = new StatusManager();

    private StatusManager() {
        this.actualStatus = Status.INITIALIZATION;
    }

    /**
     * Getter of the object as required in singleton pattern.
     * @return the singleton object.
     */
    public static StatusManager getStatusManager() {
        return SINGLETON;
    }

    /**
     * Gets the actual game status.
     * @return the actual game status.
     */
    public Status getGameStatus() {
        return this.actualStatus;
    }

    /**
     * Sets the game status.
     * @param status
     *              the status to be add.
     */
    public void setGameStatus(final Status status) {
        this.actualStatus = status;
    }

}
