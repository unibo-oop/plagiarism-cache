package oop.lit.view.controller;

/**
 * Defines a finite state automata with only one state to avoid any kind of bugs
 * related to selection and geometry transforms.
 */
public class Status {
    /*
     * More in details: because when a user performs a click it is captured both
     * by a BoardElementView (or by one of its components to allow transforms)
     * and by the BoardView, it is needed to ignore the second one.
     */

    private boolean selectionStarted;

    /**
     * @return the selectionStarted.
     */
    public boolean isSelectionStarted() {
        return selectionStarted;
    }

    /**
     * @param selectionStarted
     *            the selectionStarted to set.
     */
    public void setSelectionStarted(final boolean selectionStarted) {
        this.selectionStarted = selectionStarted;
    }
}
