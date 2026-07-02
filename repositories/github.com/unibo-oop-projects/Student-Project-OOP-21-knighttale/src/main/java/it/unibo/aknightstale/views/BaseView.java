package it.unibo.aknightstale.views;

import it.unibo.aknightstale.controllers.interfaces.Controller;
import it.unibo.aknightstale.views.interfaces.View;

public abstract class BaseView<C extends Controller<? extends View<C>>> implements View<C> {
    /**
     * Controller that is associated with this view.
     */
    private C controller;
    /**
     * Window associated to this view.
     */
    private Window window;
    private final String windowTitle;

    protected BaseView(final String windowTitle) {
        this.windowTitle = windowTitle;
    }

    /**
     * Gets the controller associated with this view.
     *
     * @return the controller associated with this view.
     */
    protected C getController() {
        return controller;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setController(final C controller) {
        this.controller = controller;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void show() {
        this.getWindow().switchTo(this);
        this.getWindow().open();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void hide() {
        this.getWindow().hide(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void close() {
        this.getWindow().close(this);
    }

    /**
     * Gets the window associated with this view.
     * @return the window associated with this view.
     */
    public Window getWindow() {
        return window;
    }

    /**
     * Sets the window associated with this view.
     * @param window the window to associate with this view.
     */
    public void setWindow(final Window window) {
        this.window = window;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getViewName() {
        return this.getClass().getSimpleName();
    }

    /**
     * Gets the window title.
     *
     * @return the window title.
     */
    @Override
    public String getWindowTitle() {
        return this.windowTitle;
    }
}
