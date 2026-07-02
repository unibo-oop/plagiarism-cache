package it.unibo.aknightstale.controllers;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.aknightstale.controllers.interfaces.Controller;
import it.unibo.aknightstale.views.interfaces.View;

public abstract class BaseController<V extends View<? extends Controller<V>>> implements Controller<V> {
    /**
     * The view associated to this controller.
     */
    private V view;

    /**
     * Registers a view with this controller.
     * @param view the view to register.
     */
    @SuppressFBWarnings("EI_EXPOSE_REP") // View must be passed as reference to allow view loader caching.
    @Override
    public void registerView(final V view) {
        this.view = view;
    }

    /**
     * Removes the view from the controller.
     */
    @Override
    public void unregisterView() {
        this.view = null;
    }

    /**
     * Shows the view.
     */
    @Override
    public void showView() {
        this.getView().show();
    }

    /**
     * Hides the view.
     */
    @Override
    public void hideView() {
        this.getView().hide();
    }

    /**
     * Closes the view.
     */
    @Override
    public void closeView() {
        this.getView().close();
    }

    /**
     * Returns the view.
     *
     * @return the view.
     */
    @SuppressFBWarnings("EI_EXPOSE_REP") // View must be returned as reference to edit it.
    public V getView() {
        return this.view;
    }
}
