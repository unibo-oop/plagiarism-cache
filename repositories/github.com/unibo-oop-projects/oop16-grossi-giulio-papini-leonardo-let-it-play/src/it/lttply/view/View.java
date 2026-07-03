package it.lttply.view;

import java.io.IOException;

import it.lttply.controller.HomeController;

/**
 *
 * Interface that allows the controller to dial with the view.
 *
 */
public interface View {

    /**
     * String to complete the path.
     */
    String FILE = "file:///";

    /**
     * Initialize the view.
     *
     * @throws IOException
     *             thrown if an exception occurs when the view can't start
     */
    void initView() throws IOException;

    /**
     * Load and display the tv series poster, title and year.
     *
     * @param imagePath
     *            path of the tv series poster
     * @param text
     *            title of the tv series
     * @param seriesID
     *            id of a movie
     */
    void loadTvSeriesCover(String imagePath, String text, String seriesID);

    /**
     * Load and display the movie poster, title and year.
     *
     * @param imagePath
     *            path of the poster
     * @param text
     *            title of the movie
     * @param movieID
     *            id of a movie
     */
    void loadMoviesCover(String imagePath, String text, String movieID);

    /**
     * Set the controller for this view.
     *
     * @param contr
     *            controller
     */
    void setObserver(HomeController contr);

    /**
     * Clear movies view.
     */
    void clearMovies();

    /**
     * Clear tv series view.
     */
    void clearTvSeries();

    /**
     * Refresh movie info.
     */
    void refreshMovies();

    /**
     * Reload movie info.
     */
    void reloadMovies();

    /**
     * Reload tv series info.
     */
    void hardRefreshTvSeries();

    /**
     * Refresh tv series and movies info.
     */
    void refreshHome();

    /**
     * Reload tv series and movies info.
     */
    void hardrefreshHome();

    /**
     * Block the view and showing the alert box.
     */
    void blockView();

    /**
     * Unlock the view and closing the alert box.
     */
    void unlockView();

    /**
     * Shows errors with a notification.
     *
     * @param errorMessage
     *            text that must be shown
     */
    void showError(String errorMessage);

    /**
     * Set the movie path folder.
     *
     * @param moviesFolder
     *            the string of the folder.
     */
    void setMoviesPath(String moviesFolder);

}