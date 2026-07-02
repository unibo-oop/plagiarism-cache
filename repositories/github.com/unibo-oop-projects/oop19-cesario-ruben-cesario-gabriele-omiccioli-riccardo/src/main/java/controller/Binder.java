package controller;

import view.View;

/**
 * Binds one view to one controller.
 */
public final class Binder {

    private static View view;
    private static Controller controller;
    private static boolean hasBound;

    private Binder() { }

    /**
     * Binds the specified view with the specified controller.
     * @param view : the specified view.
     * @param controller : the specified controller.
     */
    public static void bind(final View view, final Controller controller) {
        Binder.requireState(!Binder.hasBound, "Binder.bind : the binder has already bound a view to a controller.");
        Binder.view = view;
        Binder.controller = controller;
        Binder.hasBound = true;
    }

    /**
     * Returns the view of this bound.
     * @return the view of this bound.
     */
    public static View getView() {
        Binder.requireState(Binder.hasBound, "Binder.getView : the binder hasn't bound a view to a controller yet.");
        return Binder.view;
    }

    /**
     * Returns the controller of this bound.
     * @return the controller of this bound.
     */
    public static Controller getController() {
        Binder.requireState(Binder.hasBound, "Binder.getController : the binder hasn't bound a view to a controller yet.");
        return Binder.controller;
    }

    /**
     * @throws IllegalStateException with the specified message if the specified state isn't met.
     * @param state : the specified state.
     * @param message : the specified message.
     */
    private static void requireState(final boolean state, final String message) {
        if (!state) {
            throw new IllegalStateException(message);
        }
    }

}
