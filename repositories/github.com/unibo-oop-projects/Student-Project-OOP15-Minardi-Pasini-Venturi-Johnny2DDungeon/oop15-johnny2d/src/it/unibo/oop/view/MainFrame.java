package it.unibo.oop.view;

import it.unibo.oop.controller.AppState;

/**
 * Interface for a mainframe, which "contains" each panel.
 */
public interface MainFrame {

    /**
     * @param state
     *            state used to define the view to show.
     */
    void changeView(final AppState state);

    /**
     * @param val
     *            true-setVisible; false-setNotVisible.
     */
    void setVisible(final boolean val);
}
