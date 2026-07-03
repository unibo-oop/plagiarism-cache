package controller;

import view.View;

/**
 * This class represent the main controller that starts the application,
 * shows a {@link View} and updates a {@link View}.
 *
 */
public interface MainController {
    /**
     * This method starts the application and initializes fields.
     * 
     */
    void startApp();

    /**
     * This method shows the passed {@link View}.
     * 
     * @param view
     *          {@link View} to show
     *
     */
    void show(View view);

    /**
     * This method replaces an old {@link View} with a new one.
     * 
     * @param oldView
     *          previous {@link View}
     * @param newView
     *          new {@link View}
     * 
     */
    void updateView(View newView, View oldView);
}
