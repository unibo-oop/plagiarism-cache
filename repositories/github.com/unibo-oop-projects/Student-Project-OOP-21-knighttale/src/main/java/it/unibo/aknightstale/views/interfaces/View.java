package it.unibo.aknightstale.views.interfaces;

import it.unibo.aknightstale.controllers.interfaces.Controller;
import it.unibo.aknightstale.views.factories.ViewFactory;

public interface View<C extends Controller<? extends View<C>>> {
    /**
     * Sets the controller associated with this view.
     *
     * @param controller the controller to associate with this view.
     */
    void setController(C controller);

    /**
     * Shows the view.
     */
    void show();

    /**
     * Hides the view.
     */
    void hide();

    /**
     * Closes the view.
     */
    void close();

    /**
     * Gets the name of the view.
     *
     * @return the name of the view.
     */
    String getViewName();

    /**
     * Gets the window title.
     *
     * @return the window title.
     */
    String getWindowTitle();

    /**
     * Create a factory for a view.
     *
     * @param viewInterface The view interface to create the factory for.
     * @param <V>           The view interface.
     * @return The factory for the view.
     */
    static <V extends View<C>, C extends Controller<V>> ViewFactory<V> of(final Class<V> viewInterface) {
        return new ViewFactory<V>().fromInterface(viewInterface);
    }
}
