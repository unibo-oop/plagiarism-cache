package it.lttply.controller;

import it.lttply.model.Model;

/**
 * Functional interface which provides only the main operation that an
 * almost-decent Controller should do.
 */
public interface Controller {
    /**
     * Refreshes the environment managed by the {@link Model}.
     * 
     * @param type
     *            tells how to refresh
     */
    void refresh(RefreshType type);
}
