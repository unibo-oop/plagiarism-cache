package vg.view;

import javafx.stage.Stage;

/**
 * Manages scenes and their controller.
 */
public interface ViewManager {

    /**
     * Add new scene to the stack of views and show it.
     * Ther first view you add is considered as home view and cannot be removed.
     * @param view new scene view to be shown.
     */
    void addView(View view);

    /**
     * Removed last-added scene view from stack and show the previous one;
     * If there is only one view nothing happens.
     */
    void popView();

    /**
     * Remove all views in stack and keep first one and show it.
     */
    void backHome();

    /**
     * @return Primary stage of application
     */
    Stage getStage();


}
