package it.lttply.model.domain.managers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import it.lttply.model.domain.Episode;
import it.lttply.model.domain.Movie;
import it.lttply.model.domain.TVSerie;
import it.lttply.model.utils.ConsolePrinter;
import it.lttply.model.utils.PictureType;
import javafx.util.Pair;

/**
 * Provides a manager of an {@link Environment}: permits to manage files in
 * secondary memory, wrapping some methods of {@link org.apache.commons.io}. It
 * includes some static methods useful to work on filenames and paths that you
 * can use anywhere and anytime. Finally it also includes static methods that
 * <b>must</b> be used only if {@link EnvironmentStandard} has been initialized
 * (see single method's doc for details): those methods are useful to understand
 * where the {@link EnvironmentStandard} sets its configuration files and
 * folders.
 */
public class EnvironmentStandard extends EnvironmentAbstract {

    // DEFAULTS
    private static final String PATH_TV_SERIE_INI = "pathTVSerie";
    private static final String PATH_MOVIES_INI = "pathMovies";

    // FOLDERS & FILES
    private static final String USER_HOME = System.getProperty("user.home");

    private static final String MAIN_FOLDER = "letitplay-data";
    private static final String MOVIES_FOLDER = "movies-data";
    private static final String TVSERIES_FOLDER = "tvserie-data";
    private static final String PERSONS_FOLDER = "persons-data";
    private static final String CONFIG_FILE = "config.ini";
    private static final String DEFAULT_PICTURE = "default.jpg";

    private static final String TVSERIES_SUB_FOLDER = "tvSeries";
    private static final String SEASONS_FOLDER = "seasons";
    private static final String EPISODES_FOLDER = "episodes";

    private static final String POSTERS_FOLDER = "posters";
    private static final String BACKDROPS_FOLDER = "backdrops";

    // ERRORS

    private static final String ERR_NOT_SET = "The resource specified has not been initialized yet!";
    private static final String ERR_ENV_ALREADY_SET = "The environment has already been initialized!";

    // DATABASE INFOs
    private static final String DB_FULL_NAME = "database";
    private static final String DB_INTERNAL_LOCATION = "/model/" + DB_FULL_NAME + "." + DB_FINAL_EXT;

    // ====================================
    // === ENVIRONMENT RESERVED METHODS ===
    // ====================================
    private static File getMoviesFolder() {
        return new File(
                new StringBuilder().append(getMainFolder()).append(SYSTEM_SEPARATOR).append(MOVIES_FOLDER).toString());
    }

    private static File getTVSeriesFolder() {
        return new File(new StringBuilder().append(getMainFolder()).append(SYSTEM_SEPARATOR).append(TVSERIES_FOLDER)
                .toString());
    }

    private static File getPersonsFolder() {
        return new File(
                new StringBuilder().append(getMainFolder()).append(SYSTEM_SEPARATOR).append(PERSONS_FOLDER).toString());
    }

    private static File getConfigFileLocation() {
        return new File(
                new StringBuilder().append(getMainFolder()).append(SYSTEM_SEPARATOR).append(CONFIG_FILE).toString());
    }

    private static File getMoviePictureFolder(final PictureType type) {
        if (type.equals(PictureType.IMAGE_BACKDROP)) {
            return new File(new StringBuilder().append(getMoviesFolder()).append(SYSTEM_SEPARATOR)
                    .append(BACKDROPS_FOLDER).toString());
        } else if (type.equals(PictureType.IMAGE_POSTER)) {
            return new File(new StringBuilder().append(getMoviesFolder()).append(SYSTEM_SEPARATOR)
                    .append(POSTERS_FOLDER).toString());
        } else {
            throw new IllegalArgumentException();
        }
    }

    private static File getTVSerieGlobalPictureFolder(final PictureType type) {
        if (type.equals(PictureType.IMAGE_BACKDROP)) {
            return new File(new StringBuilder().append(getTVSeriesFolder()).append(SYSTEM_SEPARATOR)
                    .append(BACKDROPS_FOLDER).toString());
        } else if (type.equals(PictureType.IMAGE_POSTER)) {
            return new File(new StringBuilder().append(getTVSeriesFolder()).append(SYSTEM_SEPARATOR)
                    .append(POSTERS_FOLDER).toString());
        } else {
            throw new IllegalArgumentException();
        }
    }

    private static File getTVSeriePosterFolder() {
        return new File(new StringBuilder().append(getTVSerieGlobalPictureFolder(PictureType.IMAGE_POSTER))
                .append(SYSTEM_SEPARATOR).append(TVSERIES_SUB_FOLDER).toString());
    }

    private static File getTVSerieBackdropFolder() {
        return new File(
                new StringBuilder().append(getTVSerieGlobalPictureFolder(PictureType.IMAGE_BACKDROP)).toString());
    }

    private static File getTVSerieSeasonsPosterFolder() {
        return new File(new StringBuilder().append(getTVSerieGlobalPictureFolder(PictureType.IMAGE_POSTER))
                .append(SYSTEM_SEPARATOR).append(SEASONS_FOLDER).toString());
    }

    private static File getTVSerieEpisodePosterFolder() {
        return new File(new StringBuilder().append(getTVSerieGlobalPictureFolder(PictureType.IMAGE_POSTER))
                .append(SYSTEM_SEPARATOR).append(EPISODES_FOLDER).toString());
    }

    /**
     * @return the physical location of the default picture for {@link Movie}s
     *         and {@link TVSerie}s. This is <b>not</b> a general purpose method
     *         and should be used only if the {@link EnvironmentStandard} is
     *         initialized, otherwise the requested resource may not be
     *         available.
     */
    public static File getDefaultPictureLocation() {
        return new File(new StringBuilder().append(getMainFolder()).append(SYSTEM_SEPARATOR).append(DEFAULT_PICTURE)
                .toString());
    }

    /**
     * @return the physical location of the root folder. This is <b>not</b> a
     *         general purpose method and should be used only if the
     *         {@link EnvironmentStandard} is initialized, otherwise the
     *         requested resource may not be available.
     */
    public static File getMainFolder() {
        return new File(new StringBuilder().append(USER_HOME).append(SYSTEM_SEPARATOR).append(MAIN_FOLDER).toString());
    }

    /**
     * Downloads the picture specified by the {@link URL} into the movie folder.
     * This is <b>not</b> a general purpose method and should be used only if
     * the {@link EnvironmentStandard} is initialized, otherwise the requested
     * resource may not be available.
     * 
     * @param imageLocation
     *            the {@link URL} which points to the picture
     * @param picType
     *            the {@link PictureType} of the picture (determines the folder)
     * @return the physical location where the picture has been placed
     * @throws IOException
     *             thrown if the download operation fails
     */
    public static synchronized File downloadMoviePicture(final URL imageLocation, final PictureType picType)
            throws IOException {
        checkIfExistsAndIsFolder(getMoviePictureFolder(picType));
        final File tmp = new File(
                getMoviePictureFolder(picType) + SYSTEM_SEPARATOR + FilenameUtils.getName(imageLocation.toString()));
        if (!tmp.exists()) {
            FileUtils.copyURLToFile(imageLocation, tmp);
        }
        return tmp;
    }

    /**
     * Downloads the picture specified by the {@link URL} into the tv series
     * folder. This is <b>not</b> a general purpose method and should be used
     * only if the {@link EnvironmentStandard} is initialized, otherwise the
     * requested resource may not be available.
     * 
     * @param imageLocation
     *            the {@link URL} which points to the picture
     * @param picType
     *            the {@link PictureType} of the picture (determines the folder)
     * @return the physical location where the picture has been placed
     * @throws IOException
     *             thrown if the download operation fails
     */
    public static synchronized File downloadTVSeriePicture(final URL imageLocation, final PictureType picType)
            throws IOException {
        if (picType.equals(PictureType.IMAGE_POSTER)) {
            checkIfExistsAndIsFolder(getTVSeriePosterFolder());
            final File tmp = new File(
                    getTVSeriePosterFolder() + SYSTEM_SEPARATOR + FilenameUtils.getName(imageLocation.toString()));
            if (!tmp.exists()) {
                FileUtils.copyURLToFile(imageLocation, tmp);
            }
            return tmp;
        } else if (picType.equals(PictureType.IMAGE_BACKDROP)) {
            checkIfExistsAndIsFolder(getTVSerieBackdropFolder());
            final File tmp = new File(
                    getTVSerieBackdropFolder() + SYSTEM_SEPARATOR + FilenameUtils.getName(imageLocation.toString()));
            if (!tmp.exists()) {
                FileUtils.copyURLToFile(imageLocation, tmp);
            }
            return tmp;
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Downloads the picture specified by the {@link URL} into the season
     * folder. This is <b>not</b> a general purpose method and should be used
     * only if the {@link EnvironmentStandard} is initialized, otherwise the
     * requested resource may not be available.
     * 
     * @param imageLocation
     *            the {@link URL} which points to the picture
     * 
     * @return the physical location where the picture has been placed
     * @throws IOException
     *             thrown if the download operation fails
     */
    public static synchronized File downloadSeasonPoster(final URL imageLocation) throws IOException {
        checkIfExistsAndIsFolder(getTVSerieSeasonsPosterFolder());
        final File tmp = new File(
                getTVSerieSeasonsPosterFolder() + SYSTEM_SEPARATOR + FilenameUtils.getName(imageLocation.toString()));
        if (!tmp.exists()) {
            FileUtils.copyURLToFile(imageLocation, tmp);
        }
        return tmp;
    }

    /**
     * Downloads the picture specified by the {@link URL} into the episode
     * folder. This is <b>not</b> a general purpose method and should be used
     * only if the {@link EnvironmentStandard} is initialized, otherwise the
     * requested resource may not be available.
     * 
     * @param imageLocation
     *            the {@link URL} which points to the picture
     * 
     * @return the physical location where the picture has been placed
     * @throws IOException
     *             thrown if the download operation fails
     */
    public static synchronized File downloadEpisodePoster(final URL imageLocation) throws IOException {
        checkIfExistsAndIsFolder(getTVSerieEpisodePosterFolder());
        final File tmp = new File(
                getTVSerieEpisodePosterFolder() + SYSTEM_SEPARATOR + FilenameUtils.getName(imageLocation.toString()));
        if (!tmp.exists()) {
            FileUtils.copyURLToFile(imageLocation, tmp);
        }
        return tmp;
    }

    /**
     * @return the preferred location for the database.
     */
    public static String getDatabasePreferredLocation() {
        return getMainFolder().toString() + SYSTEM_SEPARATOR + DB_FULL_NAME + "." + DB_FINAL_EXT;
    }

    /**
     * Downloads the picture specified by the {@link URL} into the person
     * folder. This is <b>not</b> a general purpose method and should be used
     * only if the {@link EnvironmentStandard} is initialized, otherwise the
     * requested resource may not be available.
     * 
     * 
     * @param imageLocation
     *            the {@link URL} which points to the picture
     * 
     * @return the physical location where the picture has been placed
     * @throws IOException
     *             thrown if the download operation fails
     */
    public static synchronized File downloadPersonProfileImage(final URL imageLocation) throws IOException {
        checkIfExistsAndIsFolder(getPersonsFolder());
        final File tmp = new File(
                getPersonsFolder() + SYSTEM_SEPARATOR + FilenameUtils.getName(imageLocation.toString()));
        FileUtils.copyURLToFile(imageLocation, tmp);
        return tmp;
    }

    /**
     * Creates a folder. This is a general purpose method.
     * 
     * @param folder
     *            the folder to be created
     * @return <code>true</code> if the folder has been created,
     *         <code>false</code> otherwise.
     * @throws IOException
     *             if the folder cannot be created
     */
    public static boolean createFolderIfNotExistsOrNotValid(final File folder) throws IOException {
        Objects.requireNonNull(folder);
        if (!folder.exists()) {
            FileUtils.forceMkdir(folder);
            ConsolePrinter.getPrinter().printlnDebug("Creating folder " + folder.toString());
            return true;
        } else if (!folder.isDirectory()) {
            ConsolePrinter.getPrinter().printlnDebug("Folder " + folder.toString() + " not valid, rebuilding");
            FileUtils.forceDelete(folder);
            FileUtils.forceMkdir(folder);
            return true;
        } else {
            ConsolePrinter.getPrinter().printlnDebug("Folder " + folder.toString() + " already present");
            return false;
        }
    }

    /**
     * This is a parser for file names: it permits to extract the
     * {@link Episode}'s season number and progressive number from the episode's
     * file name.
     * <p>
     * This method supports only files with this syntax (<code>S</code> is the
     * {@link Integer} value which represents the season number, <code>E</code>
     * is the {@link Integer} value which represents the episode number):
     * <p>
     * <b>With Spaces</b> <code>
     * <ul>
     * <li>SSxEE - Filename.ext</li>
     * <li>SxE - Filename.ext</li>
     * <li>SxE Filename.ext</li>
     * <li>SSxEE Filename.ext</li>
     * </ul>
     * </code>
     * <p>
     * <b>Without Spaces</b> <code>
     * <ul>
     * <li>SSxEE.ext</li>
     * <li>SxE.ext</li>
     * <li>SSxEE-Filename.ext</li>
     * <li>SxE-Filename.ext</li>
     * </ul>
     * </code>
     * 
     * If you decide to use other syntaxes, the final result is unchecked.
     * <p>
     * 
     * @param episode
     *            the {@link File} which points to the episode
     * @return the {@link javafx.utils.Pair} which contains the season and
     *         episode number
     * @throws NumberFormatException
     *             probably thrown if you decide to adopt your own syntax for
     *             the file names
     */
    public static Pair<Integer, Integer> getSeasonAndEpisodeValuesFromEpisode(final File episode) {
        Objects.requireNonNull(episode);
        checkIfExistsAndIsFile(episode);

        final String fileName = FilenameUtils.getBaseName(episode.toString());
        final String[] firstSplitting = fileName.split("x");
        final int seasonNumber = Integer.parseInt(firstSplitting[0]);
        int episodeNumber = -1;
        String[] internSplit = null;
        int totale = 0;
        for (final char ch : firstSplitting[1].toCharArray()) {
            try {
                Integer.parseInt(String.valueOf(ch));
                totale++;
            } catch (NumberFormatException ex) {
                internSplit = firstSplitting[1].split(String.valueOf(ch));
                episodeNumber = Integer.parseInt(internSplit[0]);
                break;
            }
        }

        if (totale == firstSplitting[1].toCharArray().length) {
            episodeNumber = Integer.parseInt(firstSplitting[1]);
        }

        return new Pair<>(seasonNumber, episodeNumber);
    }

    // =====================
    // === CLASS METHODS ===
    // =====================
    @Override
    public void saveChanges() throws IOException {
        ConsolePrinter.getPrinter()
                .printlnProcedureStarted("Trying to rewrite config file to set new tracking folders...");
        checkIfExistsAndIsFile(getConfigFileLocation());
        final String pathMovies = super.getMoviesTrackingFolder() == null ? "0"
                : super.getMoviesTrackingFolder().toString();
        final String pathTVSerie = super.getTVSerieTrackingFolder() == null ? "0"
                : super.getTVSerieTrackingFolder().toString();
        writeFile(getConfigFileLocation(),
                Arrays.asList(PATH_MOVIES_INI + ";" + pathMovies, PATH_TV_SERIE_INI + ";" + pathTVSerie));
        ConsolePrinter.getPrinter().printlnSuccess("Config file written successfully.");
    }

    @Override
    public void initialize() throws IOException {
        if (super.isInitialized()) {
            throw new IllegalStateException(ERR_ENV_ALREADY_SET);
        }

        ConsolePrinter.getPrinter().printlnProcedureStarted("Initializing Environment...");

        // Creating default directories
        createFolderIfNotExistsOrNotValid(getMainFolder());
        //createFolderIfNotExistsOrNotValid(getMoviesFolder());
        //createFolderIfNotExistsOrNotValid(getTVSeriesFolder());
        createFolderIfNotExistsOrNotValid(getPersonsFolder());
        createFolderIfNotExistsOrNotValid(getMoviePictureFolder(PictureType.IMAGE_BACKDROP));
        createFolderIfNotExistsOrNotValid(getMoviePictureFolder(PictureType.IMAGE_POSTER));
        //createFolderIfNotExistsOrNotValid(getTVSerieGlobalPictureFolder(PictureType.IMAGE_BACKDROP));
        //createFolderIfNotExistsOrNotValid(getTVSerieGlobalPictureFolder(PictureType.IMAGE_POSTER));
        //createFolderIfNotExistsOrNotValid(getTVSerieBackdropFolder());
        //createFolderIfNotExistsOrNotValid(getTVSeriePosterFolder());
        //createFolderIfNotExistsOrNotValid(getTVSerieSeasonsPosterFolder());
        //createFolderIfNotExistsOrNotValid(getTVSerieEpisodePosterFolder());

        // Creating default files
        if (!getDefaultPictureLocation().exists()) {
            ConsolePrinter.getPrinter().printlnDebug("Default picture not present, creating...");
            FileUtils.copyInputStreamToFile(
                    EnvironmentStandard.class.getResourceAsStream(
                            new StringBuilder().append("/model/").append(DEFAULT_PICTURE).toString()),
                    getDefaultPictureLocation());
        } else if (!getDefaultPictureLocation().isFile()) {
            ConsolePrinter.getPrinter().printlnError("Default picture not valid, rebuilding...");
            FileUtils.forceDelete(getDefaultPictureLocation());
            FileUtils.copyInputStreamToFile(
                    EnvironmentStandard.class.getResourceAsStream(
                            new StringBuilder().append("/model/").append(DEFAULT_PICTURE).toString()),
                    getDefaultPictureLocation());
        } else {
            ConsolePrinter.getPrinter().printlnDebug("Default picture OK.");
        }

        if (!getConfigFileLocation().exists()) {
            ConsolePrinter.getPrinter().printlnDebug("Config file not present, creating...");
            writeFile(getConfigFileLocation(), Arrays.asList(PATH_MOVIES_INI + ";0", PATH_TV_SERIE_INI + ";0"));
        } else if (!getConfigFileLocation().isFile()) {
            ConsolePrinter.getPrinter().printlnError("Config file not valid, rebuilding...");
            FileUtils.forceDelete(getConfigFileLocation());
            writeFile(getConfigFileLocation(), Arrays.asList(PATH_MOVIES_INI + ";0", PATH_TV_SERIE_INI + ";0"));
        } else {
            ConsolePrinter.getPrinter().printlnDebug("Config file OK, reading...");
            final List<String> tmp = readFile(getConfigFileLocation());
            super.setMoviesTrackingFolder(new File(new ArrayList<>(tmp).get(0).split(";")[1]));
            super.setTVSerieTrackingFolder(new File(new ArrayList<>(tmp).get(1).split(";")[1]));
        }

        if (!(new File(getDatabasePreferredLocation()).exists())) {
            ConsolePrinter.getPrinter().printlnDebug("Database not present, creating...");
            FileUtils.copyInputStreamToFile(SQLiteBasicConcrete.class.getResourceAsStream(DB_INTERNAL_LOCATION),
                    new File(getDatabasePreferredLocation()));
        } else if (!(new File(getDatabasePreferredLocation()).isFile())) {
            ConsolePrinter.getPrinter().printlnError("Database not valid, rebuilding...");
            FileUtils.forceDelete(new File(getDatabasePreferredLocation()));
            FileUtils.copyInputStreamToFile(SQLiteBasicConcrete.class.getResourceAsStream(DB_INTERNAL_LOCATION),
                    new File(getDatabasePreferredLocation()));
        } else {
            ConsolePrinter.getPrinter().printlnDebug("Database OK.");
        }

        super.setInitialized();

        ConsolePrinter.getPrinter().printlnSuccess("Environment created.");
    }

    @Override
    public Map<File, List<File>> getTrackedTVSeries() {
        super.checkInitialization();
        final Map<File, List<File>> tmp = new HashMap<>();
        final File tvtrack = super.getTVSerieTrackingFolder();
        final String[] folders = tvtrack.list();
        if (folders == null) {
            return tmp;
        }

        Arrays.asList(folders).forEach(t -> {
            final File internal = new File(tvtrack.toString() + SYSTEM_SEPARATOR + t);
            if (internal.isDirectory()) {
                tmp.put(internal, new LinkedList<>(FileUtils.listFiles(internal,
                        SUPPORTED_EXTENSIONS.toArray(new String[SUPPORTED_EXTENSIONS.size()]), false)));
            }
        });

        return tmp;
    }

    @Override
    public List<File> getTrackedMovies() {
        this.checkInitialization();

        if (super.getMoviesTrackingFolder() == null) {
            throw new IllegalStateException(ERR_NOT_SET);
        }
        return new LinkedList<>(FileUtils.listFiles(super.getMoviesTrackingFolder(),
                SUPPORTED_EXTENSIONS.toArray(new String[SUPPORTED_EXTENSIONS.size()]), true));
    }

    @Override
    public void deleteAllMoviesFiles() throws IOException {
        FileUtils.forceDelete(getMoviePictureFolder(PictureType.IMAGE_BACKDROP));
        FileUtils.forceDelete(getMoviePictureFolder(PictureType.IMAGE_POSTER));
        createFolderIfNotExistsOrNotValid(getMoviePictureFolder(PictureType.IMAGE_BACKDROP));
        createFolderIfNotExistsOrNotValid(getMoviePictureFolder(PictureType.IMAGE_POSTER));
    }

    @Override
    public void deleteAllTVSeriesFiles() {
        throw new UnsupportedOperationException();
    }

}
