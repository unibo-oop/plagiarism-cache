package it.unibo.aknightstale.controllers.interfaces;

import it.unibo.aknightstale.controllers.factories.ControllerFactory;
import it.unibo.aknightstale.views.interfaces.View;

public interface Controller<V extends View<? extends Controller<V>>> {
    void registerView(V view);

    void unregisterView();

    void showView();

    void hideView();

    void closeView();

    /**
     * Create a factory for a controller.
     *
     * @param controllerInterface The controller interface to create the factory for.
     * @param <C>                 The controller interface.
     * @param <V>                 The view interface.
     * @return The factory for the controller.
     */
    static <C extends Controller<V>, V extends View<C>> ControllerFactory<C, V> of(final Class<C> controllerInterface) {
        return of(controllerInterface, null);
    }

    /**
     * Create a factory for a controller.
     *
     * @param controllerInterface The controller interface to create the factory for.
     * @param viewInterface       The view interface to create the factory for.
     * @param <C>                 The controller interface.
     * @param <V>                 The view interface.
     * @return The factory for the controller.
     */
    static <C extends Controller<V>, V extends View<C>> ControllerFactory<C, V> of(final Class<C> controllerInterface, final Class<V> viewInterface) {
        return new ControllerFactory<C, V>().fromInterface(controllerInterface).viewInterface(viewInterface);
    }
}
