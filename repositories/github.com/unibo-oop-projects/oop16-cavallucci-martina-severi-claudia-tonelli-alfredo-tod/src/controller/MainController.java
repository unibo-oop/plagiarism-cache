package controller;

import java.util.Map;

import view.View;

/**
 * Main Controller.
 *
 */
public interface MainController {
    /**
     * Start the application, initialize everything.
     */
    void startApp();
    /**
     * Show a determinate view.
     * 
     * @param view
     * view to show
     */
    void show(View view);
    /**
     * Replace new view in a old tab when something changed.
     * 
     * @param view
     * new view to replace
     * 
     * @param old
     * view to be replaced
     */
    void updateView(View view, View old);
    /**
     * do the login with user name and password that user has insert.
     * 
     * @param map
     * formChooses
     */
    void confirmLogin(Map<UserInfo, String> map);
    /**
     * register user.
     * 
     */
    void registerUserCliecked();
    /**
     * logout.
     * logout
     */
    void logout();
    /**
     * writeValues.
     * write on file
     */
    void writeValues();
    /**
     * .
     */
    void changePassword();
    /**
     * .
     */
    void cancelUser();
}
