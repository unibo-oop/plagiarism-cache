package it.unibo.michelito.controller.gamecontroller.api;

/**
 * This interface is used to request exception management.
 */
@FunctionalInterface
public interface GameExceptionHandler {
    /**
     * @param e the exception that needs to be managed.
     */
    void gameControllerHandleException(Exception e);
}
