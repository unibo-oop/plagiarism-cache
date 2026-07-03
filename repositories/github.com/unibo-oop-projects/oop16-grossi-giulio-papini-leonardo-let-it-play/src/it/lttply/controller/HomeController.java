package it.lttply.controller;

/**
 * Interface for a {@link Controller} which will be qualified to direct the
 * fundamental administration of media files.
 */
public interface HomeController extends Controller {
    /**
     * Refreshes only the movies.
     *
     * @param type
     *            tells how to refresh
     */
    void refreshMovies(RefreshType type);

    /**
     * Refreshes only the tv series.
     * 
     * @param type
     *            the type which tells how to refresh
     */
    void refreshTvSeries(RefreshType type);

    /**
     * Return the tv series path.
     * 
     * @return the tv serie tracked folder
     */
    String getTVSeriePath();

    /**
     * Return Movies Path.
     *
     * @return the movie tracked folder
     */
    String getMoviePath();

    /**
     * Sets the tv series tracking folder.
     * 
     * @param tvSeriesPath
     *            the folder to be tracked
     */
    void setTVSeriePath(String tvSeriesPath);

    /**
     * Sets the movie tracking folder.
     * 
     * @param moviePath
     *            the folder to be tracked
     */
    void setMoviePath(String moviePath);

    /**
     * Searches for a movie and loads them to the graphic environment.
     * 
     * @param filter
     *            the optional string to filter movies
     */
    void searchMovie(String filter);

    /**
     * Searches for a tv series and loads them to the graphic environment.
     * 
     * @param filter
     *            the optional string to filter tv series
     */
    void searchTVSeries(String filter);

}
