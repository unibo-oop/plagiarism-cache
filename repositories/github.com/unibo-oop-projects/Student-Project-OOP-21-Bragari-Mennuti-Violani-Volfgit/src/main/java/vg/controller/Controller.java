package vg.controller;

import vg.view.AdaptableView;
import vg.view.SceneController;
import vg.view.ViewManager;

/**
 * Logic Controller that is a conjunction between model and view. Pattern MVC.
 * @param <T> subtype of {@link AdaptableView}
 */
public abstract class Controller<T extends AdaptableView<?>> implements SceneController {
    /**
     * View to be controlled.
     */
    private final T view;
    /**
     * View manager used to add new view to application window.
     */
    private final ViewManager viewManager;

    public Controller(final T view, final ViewManager viewManager) {
        this.view = view;
        this.viewManager = viewManager;
    }

    /**
     * @return view that depends on this controller
     */
    public T getView() {
        return this.view;
    }

    /**
     * @return viewManager used by this controller to add new views to application's window
     */
    public ViewManager getViewManager() {
        return viewManager;
    }
}
