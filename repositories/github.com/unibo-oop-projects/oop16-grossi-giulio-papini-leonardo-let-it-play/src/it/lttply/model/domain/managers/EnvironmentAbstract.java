package it.lttply.model.domain.managers;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

/**
 * Provides an half-built structure in order to build a complete Environment
 * manager.
 */
public abstract class EnvironmentAbstract implements Environment {

    /**
     * The extensions supported by this Environment.
     */
    public static final List<String> SUPPORTED_EXTENSIONS = Collections
            .unmodifiableList(Arrays.asList("avi", "mkv", "mp4", "mpg"));

    /**
     * The cross-platform system separator.
     */
    public static final String SYSTEM_SEPARATOR = System.getProperty("file.separator");

    /**
     * The only allowed database extension.
     */
    public static final String DB_FINAL_EXT = "db";

    private static final String ERR_NOT_ACCESSIBLE = "The resource specified is not valid/accessible!";
    private static final String ERR_ENV_NOT_SET = "The environment has not been initialized yet!";
    private boolean initialized;
    private File moviesTrackingFolder;
    private File tvSeriesTrackingFolder;

    // =======================
    // === GENERAL PURPOSE ===
    // =======================
    /**
     * Wraps the {@link FileUtils#writeLines(File, String, Collection, String)}
     * and automatically sets the encoding to <code>UTF-8</code> and the line
     * ending to <code>null</code>, in order to use the system default values.
     * This is a general purpose method.
     * 
     * @param file
     *            the file where the lines will be written
     * @param lines
     *            the {@link Collection} to be written
     * @throws IOException
     *             thrown if an error occurs while writing the lines
     */
    public static void writeFile(final File file, final Collection<? extends String> lines) throws IOException {
        FileUtils.writeLines(file, "UTF-8", lines, null);
    }

    /**
     * Wraps the {@link FileUtils#readLines(File, String)} and automatically
     * sets the encoding to <code>UTF-8</code>. This is a general purpose
     * method.
     * 
     * @param file
     *            the file from which the lines will be read
     * @return the {@link List} which contains the lines read
     * @throws IOException
     *             thrown if an exception occurs while reading the file
     */
    public static List<String> readFile(final File file) throws IOException {
        return FileUtils.readLines(file, "UTF-8");
    }

    /**
     * Checks whether the {@link File} specified is a valid file. This is a
     * general purpose method.
     * 
     * @param file
     *            the {@link File} to be analyzed
     * @throws IllegalArgumentException
     *             if the parameter is not a valid {@link File}
     */
    public static void checkIfExistsAndIsFile(final File file) throws IllegalArgumentException {
        Objects.requireNonNull(file);
        if (!file.exists() || !file.isFile()) {
            throw new IllegalArgumentException(ERR_NOT_ACCESSIBLE);
        }
    }

    /**
     * Checks whether the {@link File} specified is a valid folder. This is a
     * general purpose method.
     * 
     * @param folder
     *            the {@link File} (folder) to be analyzed
     * @throws IllegalArgumentException
     *             if the parameter is not a valid {@link File} (folder)
     */
    public static void checkIfExistsAndIsFolder(final File folder) throws IllegalArgumentException {
        Objects.requireNonNull(folder);
        if (!folder.exists() || !folder.isDirectory()) {
            throw new IllegalArgumentException(ERR_NOT_ACCESSIBLE);
        }
    }

    /**
     * Extracts the base name from a {@link File}. This is a general purpose
     * method
     * 
     * @param file
     *            the file to be analyzed
     * @return the base name of the specified {@link File} without extension and
     *         eventually a year (that is located at the end of the file name
     *         after the underscore character, as indicated in the
     *         {@link EnvironmentStandard#getMovieYear(File)} javadoc)
     */
    public static String getFilenameFromFile(final File file) {
        String ret = FilenameUtils.getBaseName(file.toString());
        final Optional<Integer> optYear = getFileOrFolderYear(new File(ret));
        if (optYear.isPresent()) {
            ret = ret.replaceAll("_" + optYear.get(), "");
        }
        return ret;
    }

    /**
     * Retrieves the year when the movie was shot, taking it from the file name.
     * <p>
     * The syntax allowed for the file name is:
     * <b><i>myFileName_xxxx.ext</i></b>, where xxxx represents the year with 4
     * digits, spaces in the name are allowed.
     * 
     * @param movie
     *            the {@link File} to be analyzed
     * @return an {@link Optional} {@link Integer} which represents the year of
     *         the movie
     */
    public static Optional<Integer> getFileOrFolderYear(final File movie) {
        final String reversedYear = new StringBuilder().append(FilenameUtils.getBaseName(movie.toString())).reverse()
                .toString().split("_")[0];

        Integer number = null;
        try {
            number = Integer.parseInt(new StringBuilder().append(reversedYear).reverse().toString());
        } catch (NumberFormatException ex) {
            return Optional.empty();
        }
        return Optional.ofNullable(number);
    }

    /**
     * Calculates the size in bytes of a file.
     * 
     * @param file
     *            the file to be analyzed
     * @return the size of the file in bytes. If the argument is a directory, an
     *         exception is thrown.
     */
    public static long getSizeFromFile(final File file) {
        checkIfExistsAndIsFile(file);
        return file.length();
    }

    /**
     * Calculates the length of a video in seconds. Only some formats are
     * supported, see {@link EnvironmentStandard#SUPPORTED_EXTENSIONS}
     * 
     * @param video
     *            the video to be analyzed
     * @return the length in seconds of the video
     * @throws IOException
     *             thrown if an error occurs while reading the file
     * @throws IllegalArgumentException
     *             thrown if the file passed is not valid/supported
     * 
     */
    public static synchronized long getVideoDuration(final File video) throws IOException {
        checkIfExistsAndIsFile(video);
        if (!SUPPORTED_EXTENSIONS.contains(FilenameUtils.getExtension(video.toString()))) {
            throw new IllegalArgumentException();
        }

        // We had to do this because the Xuggler library is bugged and doesn't
        // work correctly on both windows and linux. So we decided to renounce
        // to implement this feature, in a near future we'll decide what to do.
        return 0;

    }

    /**
     * Default constructor for an environment.
     */
    public EnvironmentAbstract() {
        this.initialized = false;
        this.moviesTrackingFolder = null;
        this.tvSeriesTrackingFolder = null;
    }

    @Override
    public abstract void initialize() throws IOException;

    @Override
    public void setMoviesTrackingFolder(final File path) throws IOException {
        if (path != null) {
            this.moviesTrackingFolder = path;
        } else {
            this.moviesTrackingFolder = null;
        }

    }

    @Override
    public void setTVSerieTrackingFolder(final File path) throws IOException {

        if (path != null) {
            this.tvSeriesTrackingFolder = path;
        } else {
            this.tvSeriesTrackingFolder = null;
        }

    }

    @Override
    public File getTVSerieTrackingFolder() {
        this.checkInitialization();
        if (this.tvSeriesTrackingFolder == null) {
            return null;
        }
        return new File(this.tvSeriesTrackingFolder.toString());
    }

    @Override
    public File getMoviesTrackingFolder() {
        this.checkInitialization();

        if (this.moviesTrackingFolder == null) {
            return null;
        }
        return new File(this.moviesTrackingFolder.toString());
    }

    @Override
    public abstract List<File> getTrackedMovies();

    @Override
    public abstract Map<File, List<File>> getTrackedTVSeries();

    @Override
    public abstract void deleteAllMoviesFiles() throws IOException;

    @Override
    public abstract void deleteAllTVSeriesFiles() throws IOException;

    @Override
    public abstract void saveChanges() throws IOException;

    /**
     * @return <code>true</code> if the environment is initialized,
     *         <code>false</code> otherwise.
     */
    protected boolean isInitialized() {
        return this.initialized;
    }

    /**
     * Sets the environment status to "initialized".
     */
    protected void setInitialized() {
        this.initialized = true;
    }

    /**
     * Checks if the environment is initialized.
     * 
     * @throws IllegalStateException
     *             if is not initialized
     */
    protected void checkInitialization() {
        if (!this.isInitialized()) {
            throw new IllegalStateException(ERR_ENV_NOT_SET);
        }
    }
}
