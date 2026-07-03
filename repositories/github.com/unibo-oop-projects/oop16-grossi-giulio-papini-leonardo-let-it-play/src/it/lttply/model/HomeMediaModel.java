package it.lttply.model;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import it.lttply.model.domain.Movie;
import it.lttply.model.domain.Source;
import it.lttply.model.domain.TVSerie;
import it.lttply.model.utils.ThreadException;

/**
 * Gives a common structure to build a model adapt to manage {@link Movie}s and
 * {@link TVSerie}s.
 */
public interface HomeMediaModel extends Model {

    /**
     * Gets all the {@link Movie}s previously extracted with the reload method,
     * sorted in ascending order by movie's name.
     * 
     * @param filter
     *            if not <code>null</code> is used to filter the stored
     *            {@link Movie}s basing on the title, if <code>null</code> or
     *            <code>empty</code> all the saved {@link Movie}s will be
     *            selected
     * @return the read-only {@link List} of {@link Movie}
     */
    List<Movie> getMovies(String filter);

    /**
     * Gets all the {@link TVSerie}s extracted with the "reload" method, sorted
     * in ascending order by movie's name.
     * 
     * @param filter
     *            if not <code>null</code> is used to filter the stored tv
     *            series basing on the name, if <code>null</code> or
     *            <code>empty</code> all the saved {@link TVSerie}s will be
     *            selected
     * @return the read-only {@link List} of {@link TVSerie}
     */
    List<TVSerie> getTVSeries(String filter);

    /**
     * Gets the movie which corresponds to the specified id.
     * 
     * @param movieID
     *            the id to be searched
     * @return the movie which corresponds to the movieID, <code>null</code>
     *         otherwise
     */
    Movie getMovieFromID(String movieID);

    /**
     * Gets the tv series which corresponds to the specified id.
     * 
     * @param tvSerieID
     *            the id to be searched
     * @return the tv series which corresponds to the tvSerieID,
     *         <code>null</code> otherwise
     */
    TVSerie getTVSerieFromID(String tvSerieID);

    /**
     * Changes the {@link Movie} tracking folder. This methods deletes all
     * {@link Movie}s stored into the database, in order to rebuild it with the
     * new tracked files. If the specified folder is equals to the actual one,
     * no changes are made.
     * 
     * @param folder
     *            the new folder to be tracked
     * @throws IOException
     *             if an exception occurs while working on the configuration
     *             file
     * @throws SQLException
     *             thrown if an exception occurs while querying the database
     * @throws InterruptedException
     *             thrown if a {@link Thread} gets interrupted
     * @throws ThreadException
     *             thrown if an exception occurs when a thread/s is/are doing
     *             his/them work
     */
    void setMovieTrackingFolder(File folder) throws IOException, SQLException;

    /**
     * Changes the {@link TVSerie} tracking folder. This methods deletes all
     * {@link TVSerie}s stored into the database, in order to rebuild it with
     * the new tracked files. If the specified folder is equals to the actual
     * one, no changes are made.
     * 
     * @param folder
     *            the new folder to be tracked
     * @throws IOException
     *             thrown if an exception occurs while working on the
     *             configuration file
     * @throws SQLException
     *             thrown if an exception occurs while querying tht database
     */
    void setTVSeriesTrackingFolder(File folder) throws IOException, SQLException;

    /**
     * Reloads the {@link TVSerie}s only.
     * 
     * @param source
     *            the {@link Source} from which the data must be retrieved
     * @param overwrite
     *            if this flag is <code>true</code> indicates that the
     *            informations retrieved must be overwritten to the informations
     *            already present or not if <code>false</code>
     * @throws SQLException
     *             thrown if an exception occurs while querying the database
     */
    void reloadTVSeriesOnly(Source source, boolean overwrite) throws SQLException;

    /**
     * Reloads the {@link Movie}s only.
     * 
     * @param source
     *            the {@link Source} from which the data must be retrieved
     * @param overwrite
     *            if this flag is <code>true</code> indicates that the
     *            informations retrieved must be overwritten to the informations
     *            already present or not if <code>false</code>
     * @throws SQLException
     *             thrown if an exception occurs while querying tht database
     * @throws InterruptedException
     *             thrown if a {@link Thread} gets interrupted
     * @throws ThreadException
     *             thrown if an exception occurs when a thread/s is/are doing
     *             his/them work
     * @throws IOException
     *             thrown if an exception occurs while reading/writing in
     *             seconday memory
     */
    void reloadMoviesOnly(Source source, boolean overwrite)
            throws SQLException, InterruptedException, ThreadException, IOException;

    /**
     * Gets the movies tracking folder.
     * 
     * @return the folder tracked
     */
    String getMoviesTrackingFolder();

    /**
     * Gets the tv series tracking folder.
     * 
     * @return the folder tracked
     */
    String getTVSeriesTrackingFolder();

    /**
     * @return the default picture location
     */
    String getDefaultPictureLocation();

}
