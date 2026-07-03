package it.lttply.model.domain.managers;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import it.lttply.model.domain.Movie;
import it.lttply.model.domain.TVSerie;

/**
 * This interface provides an architecture to build a manager for an
 * "environment" in secondary memory: it permits to manage media files.
 */
public interface Environment {

    /**
     * Creates the default files and setting for the {@link Environment}. If the
     * Environment is already present in secondary memory, the internal fields
     * will be filled with actual settings. <b>This method must be called only
     * before setting any options, otherwise some settings may be lost</b>.
     * 
     * @throws IOException
     *             thrown if an input/output exception occurs while creating the
     *             environment on secondary memory
     * 
     */
    void initialize() throws IOException;

    /**
     * Sets the tracking path for the {@link Movie}. If a folder is already set,
     * will be overwritten.
     * 
     * @param path
     *            the folder which contains the {@link Movie}s to be tracked
     * @throws IOException
     *             thrown if an input/output exception occurs while writing the
     *             path
     */
    void setMoviesTrackingFolder(File path) throws IOException;

    /**
     * @return the folder which contains the {@link Movie}s tracked.
     * 
     */
    File getMoviesTrackingFolder();

    /**
     * Sets the tracking path for the {@link TVSerie}. If a folder is already
     * set, will be overwritten.
     * 
     * @param path
     *            the folder which contains the {@link TVSerie}s to be tracked
     * @throws IOException
     *             thrown if an input/output exception occurs while writing the
     *             path
     */
    void setTVSerieTrackingFolder(File path) throws IOException;

    /**
     * @return the folder which contains the {@link TVSerie}s tracked.
     */
    File getTVSerieTrackingFolder();

    /**
     * @return the {@link List} of <b>movies</b> ({@link File}) tracked. It
     *         wraps the {@link FileUtils#listFiles(File, String[], boolean)}:
     *         it means that this method is able to scan all files, even if they
     *         are located in sub-directories into the movies tracking
     *         directory. Otherwise, if the folder is empty, an empty
     *         {@link List} is returned.
     */
    List<File> getTrackedMovies();

    /**
     * @return a complex {@link Map} which represents the structure of files in
     *         secondary memory. The <b>keys</b> {@link File} represents the
     *         directories found at the first level into the tv series tracking
     *         folder. The <b>values</b> of the {@link Map} are composed by a
     *         {@link List} of {@link File} which contains all files located in
     *         the folder stored in the key ({@link File}). Otherwise, if the tv
     *         series tracked folder is empty, an empty {@link Map} is returned.
     *         <p>
     *         The folder, in order to be scanned properly, <b>must</b> have
     *         this hierarchy:
     *         <p>
     *         <code>
     *         Tracked_Folder_1
     *         <ul>
     *         <li>TTxTT-EpisodeName.ext</li>
     *         <li>TTxTT-EpisodeName2.ext</li>
     *         <li>TTxTT-EpisodeName2.ext</li>
     *         </ul>
     *         </code>
     *         <p>
     *         <code>
     *         Tracked_Folder_N
     *         <ul>
     *         <li>TTxTT-EpisodeName.ext</li>
     *         <li>TTxTT-EpisodeName2.ext</li>
     *         <li>TTxTT-EpisodeName2.ext</li>
     *         </ul>
     *         </code> See the
     *         {@link Environment#getSeasonAndEpisodeValuesFromEpisode(File)}
     *         for major details about the episode's name formatting.
     * 
     */
    Map<File, List<File>> getTrackedTVSeries();

    /**
     * Deletes all movies files from secondary memory.
     * 
     * @throws IOException
     *             thrown if an exception occurs while deleting files
     */
    void deleteAllMoviesFiles() throws IOException;

    /**
     * Deletes all tv series files from secondary memory.
     * 
     * @throws IOException
     *             thrown if an exception occurs while deleting filed
     */
    void deleteAllTVSeriesFiles() throws IOException;

    /**
     * Saves the changes made to the tracking directories.
     * 
     * @throws IOException
     *             thrown if an exception occurs while writing the configuration
     *             file
     */
    void saveChanges() throws IOException;
}
