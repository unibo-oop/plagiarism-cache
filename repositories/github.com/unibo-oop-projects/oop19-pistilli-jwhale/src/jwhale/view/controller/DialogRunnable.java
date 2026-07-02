package jwhale.view.controller;
/**
 * Interface used to perform operations via dialogs.
 */
@FunctionalInterface
public interface DialogRunnable {
    /**
     * Execute operation.
     * @param param
     *          operation parameter.
     */
    void execute(String param);
}
