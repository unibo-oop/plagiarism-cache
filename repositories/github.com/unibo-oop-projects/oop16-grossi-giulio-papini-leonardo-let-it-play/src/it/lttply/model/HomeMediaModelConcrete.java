package it.lttply.model;

import static it.lttply.model.domain.managers.SQLiteAdvanced.getDBManager;
import static it.lttply.model.domain.managers.SQLiteAdvanced.normalize;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import com.google.common.base.Stopwatch;
import com.google.common.collect.Lists;

import it.lttply.model.domain.Movie;
import it.lttply.model.domain.MovieConcrete;
import it.lttply.model.domain.Source;
import it.lttply.model.domain.TVSerie;
import it.lttply.model.domain.TVSerieConcrete;
import it.lttply.model.domain.managers.Environment;
import it.lttply.model.domain.managers.EnvironmentStandard;
import it.lttply.model.domain.managers.ParserStandard;
import it.lttply.model.utils.ConsolePrinter;
import it.lttply.model.utils.InternetConnectionException;
import it.lttply.model.utils.ThreadException;

/**
 * Manages {@link Movie}s and {@link TVSerie}s.
 */
public class HomeMediaModelConcrete implements HomeMediaModel {

    private static final String NOT_SUPPORTED = "Not supported";
    private static final int CPUS = Runtime.getRuntime().availableProcessors();
    private static final Object LOCK = new Object();
    private List<Movie> cachedMovies;
    private final List<TVSerie> cachedTVSeries;
    private final Environment environment;

    // Thread fields
    private final Thread.UncaughtExceptionHandler handler;
    private List<Thread> movieThreads;
    private final List<Thread> tvThreads;
    private boolean exceptionOccurred;
    private Exception lastException;

    /**
     * Constructs a new instance of {@link HomeMediaModelConcrete}.
     * 
     * @throws IOException
     *             thrown if an exception occurs while setting the
     *             {@link Environment}
     */
    public HomeMediaModelConcrete() throws IOException {
        this.cachedMovies = Collections.synchronizedList(new LinkedList<>());
        this.cachedTVSeries = Collections.synchronizedList(new LinkedList<>());
        this.environment = new EnvironmentStandard();
        this.environment.initialize();
        getDBManager();

        this.exceptionOccurred = false;
        this.movieThreads = new LinkedList<>();
        this.tvThreads = new LinkedList<>();
        this.lastException = null;
        this.handler = (th, ex) -> {
            synchronized (LOCK) {
                ConsolePrinter.getPrinter().printlnError(
                        "An error occurred while processing the movies! " + ex.getClass() + ": " + ex.getMessage());
                this.exceptionOccurred = true;
                if (ex.getCause() != null) {
                    this.lastException = (Exception) (ex.getCause());
                } else {
                    this.lastException = (Exception) ex;
                }

                movieThreads.forEach(t -> t.interrupt());
                tvThreads.forEach(t -> t.interrupt());
            }
        };
    }

    @Override
    public void reload(final Source source, final boolean overwrite)
            throws SQLException, InterruptedException, ThreadException, IOException {
        this.reloadMoviesOnly(source, overwrite);
        // this.reloadTVSeriesOnly(source, overwrite);
    }

    @Override
    public List<Movie> getMovies(final String filter) {
        final String filterN = normalize(filter);
        if (filterN != null) {
            return Collections.unmodifiableList(this.cachedMovies.stream().filter(
                    mv -> mv.getTitle().toLowerCase(Locale.ENGLISH).contains(filterN.toLowerCase(Locale.ENGLISH)))
                    .sorted((m1, m2) -> m1.getTitle().compareTo(m2.getTitle())).collect(Collectors.toList()));
        }

        return Collections.unmodifiableList(this.cachedMovies.stream()
                .sorted((m1, m2) -> m1.getTitle().compareTo(m2.getTitle())).collect(Collectors.toList()));
    }

    @Override
    public List<TVSerie> getTVSeries(final String filter) {
        final String filterN = normalize(filter);
        if (filterN != null) {
            return Collections.unmodifiableList(this.cachedTVSeries.stream().filter(
                    tv -> tv.getTitle().toLowerCase(Locale.ENGLISH).startsWith(filterN.toLowerCase(Locale.ENGLISH)))
                    .sorted((tv1, tv2) -> tv1.getTitle().compareTo(tv2.getTitle())).collect(Collectors.toList()));
        }

        return Collections.unmodifiableList(this.cachedTVSeries.stream()
                .sorted((tv1, tv2) -> tv1.getTitle().compareTo(tv2.getTitle())).collect(Collectors.toList()));
    }

    @Override
    public void setMovieTrackingFolder(final File folder) throws IOException, SQLException {
        if (this.environment.getMoviesTrackingFolder() == null || folder == null
                || !this.environment.getMoviesTrackingFolder().toString().equals(folder.toString())) {
            // Folder changed
            getDBManager().emptyMoviesOnly();
            this.environment.deleteAllMoviesFiles();
            this.environment.setMoviesTrackingFolder(folder);
            this.environment.saveChanges();
        }

        if (folder != null) {
            renameFiles(folder);
        }
    }

    @Override
    public void setTVSeriesTrackingFolder(final File folder) throws IOException, SQLException {
        throw new UnsupportedOperationException(NOT_SUPPORTED);
        /*
         * if (this.environment.getTVSerieTrackingFolder() == null || folder ==
         * null ||
         * !this.environment.getTVSerieTrackingFolder().toString().equals(folder
         * .toString())) { // Folder changed
         * this.environment.setTVSerieTrackingFolder(folder);
         * 
         * // Emptying database getDBManager().emptyTVSeriesOnly(); }
         */

    }

    @Override
    public void reloadTVSeriesOnly(final Source source, final boolean overwrite) throws SQLException {
        throw new IllegalStateException(NOT_SUPPORTED);

    }

    @Override
    public void reloadMoviesOnly(final Source source, final boolean overwrite)
            throws SQLException, InterruptedException, ThreadException, IOException {
        resetFailureCheck();
        this.movieThreads = new LinkedList<>();

        // Retrieves all movies files from secondary memory
        renameFiles(this.environment.getMoviesTrackingFolder());
        final List<File> movieFiles = Collections.synchronizedList(this.environment.getTrackedMovies());
        final int portSize = movieFiles.size() <= CPUS ? 1 : (int) Math.ceil(movieFiles.size() / (CPUS * 1.0));

        if (source.equals(Source.DATABASE)) {
            this.cachedMovies = Collections.synchronizedList(new LinkedList<>(getDBManager().getAllMovies()));
        } else if (source.equals(Source.SECONDARY) && overwrite) {
            getDBManager().empty();

            ConsolePrinter.getPrinter().printlnProcedureStarted(
                    movieFiles.size() + " movies found, starting deep-processing with up to " + CPUS + " threads...");

            final Stopwatch watch = Stopwatch.createStarted();
            Lists.partition(movieFiles, portSize).forEach(t -> {
                final Thread th = new MovieThread(t);
                th.setUncaughtExceptionHandler(this.handler);
                this.movieThreads.add(th);
                th.start();
            });

            for (final Thread t : this.movieThreads) {
                t.join();
            }

            watch.stop();
            ConsolePrinter.getPrinter().printlnSuccess("Finished deep-processing " + movieFiles.size() + " movies in "
                    + new Double(watch.elapsed(TimeUnit.MILLISECONDS)) / 1000 + " seconds!");
        } else if (source.equals(Source.SECONDARY) && !overwrite) {

            ConsolePrinter.getPrinter().printlnProcedureStarted(movieFiles.size()
                    + " movies found, starting shallow-processing with up to " + CPUS + " threads...");

            int skipped = 0;
            int removed = 0;
            int added = 0;

            final Stopwatch watch = Stopwatch.createStarted();

            // Rimuove tutti i film dal DB che sull'hard disk non esistono più
            final Set<File> moviesFromHD = new HashSet<>(movieFiles);
            for (final Movie mvDB : getDBManager().getAllMovies()) {
                if (!moviesFromHD.contains(new File(mvDB.getPhysicalLocation().toString()))) {
                    deleteAllPhysicalMovieFiles(mvDB);
                    getDBManager().removeMovie(mvDB.getID());
                    removed++;
                }
            }

            // Aggiunge al DB tutti i film che ancora non ci sono
            final Set<File> movieToAdd = new HashSet<>();
            final Set<File> moviesFromDB = new HashSet<>();
            getDBManager().getAllMovies().forEach(t -> moviesFromDB.add(new File(t.getPhysicalLocation().toString())));
            for (final File mvHD : moviesFromHD) {
                if (!moviesFromDB.contains(mvHD)) {
                    movieToAdd.add(mvHD);
                    added++;
                } else {
                    skipped++;
                }
            }

            Collections.synchronizedList(Lists.partition(new LinkedList<>(movieToAdd), portSize)).forEach(t -> {
                final Thread th = new MovieThread(t);
                th.setUncaughtExceptionHandler(this.handler);
                this.movieThreads.add(th);
                th.start();
            });

            for (final Thread t : this.movieThreads) {
                t.join();
            }

            watch.stop();
            final double elapsed = new Double(watch.elapsed(TimeUnit.MILLISECONDS)) / 1000;
            final double avgPerMovie = elapsed / movieFiles.size();
            ConsolePrinter.getPrinter()
                    .printlnSuccess("Finished shallow-processing " + movieFiles.size() + " movies (skipped: " + skipped
                            + ", removed: " + removed + ", added: " + added + ") in " + elapsed + " seconds ("
                            + avgPerMovie + " secs per movie)!");
        }

        checkFailure();

        // Getting all movies
        this.cachedMovies = Collections.synchronizedList(new LinkedList<>(getDBManager().getAllMovies()));

    }

    @Override
    public String getMoviesTrackingFolder() {
        if (this.environment.getMoviesTrackingFolder() == null) {
            return null;
        }
        return this.environment.getMoviesTrackingFolder().toString();
    }

    @Override
    public String getTVSeriesTrackingFolder() {
        throw new UnsupportedOperationException(NOT_SUPPORTED);
        // return this.environment.getTVSerieTrackingFolder().toString();
    }

    @Override
    public String getDefaultPictureLocation() {
        return EnvironmentStandard.getDefaultPictureLocation().toString();
    }

    // ===============
    // === PRIVATE ===
    // ===============
    private void checkFailure() throws ThreadException {
        if (this.exceptionOccurred) {
            throw new ThreadException(this.lastException);
        }
    }

    private void resetFailureCheck() {
        this.exceptionOccurred = false;
        this.lastException = null;
    }

    private void deleteAllPhysicalMovieFiles(final Movie movie) throws IOException {
        if (movie.getBackdrop().isPresent()) {
            FileUtils.forceDelete(new File(movie.getBackdrop().get().getPhysicalLocation().toString()));
        }
        if (movie.getPoster().isPresent()) {
            FileUtils.forceDelete(new File(movie.getPoster().get().getPhysicalLocation().toString()));
        }
    }

    // ===============
    // === THREADS ===
    // ===============
    private final class MovieThread extends Thread implements Runnable {

        private final List<File> movies;

        MovieThread(final List<File> movies) {
            this.movies = movies;
        }

        @Override
        public void run() {
            int moviesNotFound = 0;
            try {
                final Set<Movie> moviesSet = new HashSet<>();

                for (final File singleMovie : this.movies) {
                    ConsolePrinter.getPrinter().printlnDebug(Thread.currentThread().getName() + " is processing "
                            + FilenameUtils.getBaseName(singleMovie.toString()) + "...");
                    if (Thread.currentThread().isInterrupted()) {
                        return;
                    }

                    final Movie dwnldMovie = ParserStandard.getMovieFromJSON(singleMovie);
                    if (dwnldMovie != null) {
                        moviesSet.add(dwnldMovie);
                        getDBManager().addMovie(dwnldMovie);
                    } else {
                        moviesNotFound++;
                    }
                }

            } catch (SQLException | InternetConnectionException | InterruptedException | IOException e) {
                ConsolePrinter.getPrinter().printlnError(
                        Thread.currentThread().getName() + ": " + e.getClass().getName() + " --> " + e.getMessage());
                throw new RuntimeException(e);
            }
            ConsolePrinter.getPrinter().printlnDebug(Thread.currentThread().getName()
                    + " has finished processing movies (" + moviesNotFound + " not found).");
        }

    }

    @Override
    public Movie getMovieFromID(final String movieID) {
        for (final Movie tmp : this.cachedMovies) {
            if (tmp.getID().equals(movieID)) {
                return new MovieConcrete.MovieBuilderConcrete().buildFrom(tmp);
            }
        }
        return null;
    }

    @Override
    public TVSerie getTVSerieFromID(final String tvSerieID) {
        for (final TVSerie tmp : this.cachedTVSeries) {
            if (tmp.getID().equals(tvSerieID)) {
                return new TVSerieConcrete.TVSerieConcreteBuilder().buildFrom(tmp);
            }
        }
        return null;
    }

    private void renameFiles(final File folder) throws IOException {
        if (folder != null) {
            for (final File tmp : FileUtils.listFiles(folder, EnvironmentStandard.SUPPORTED_EXTENSIONS
                    .toArray(new String[EnvironmentStandard.SUPPORTED_EXTENSIONS.size()]), true)) {
                final String finalpath = FilenameUtils.getFullPath(tmp.toString());
                String finalFileName = FilenameUtils.getName(tmp.toString()).replace("'", " ");
                finalFileName = finalFileName.replace("&", "and");
                final File finalFile = new File(
                        finalpath + EnvironmentStandard.SYSTEM_SEPARATOR + finalFileName.trim());
                if (!tmp.renameTo(finalFile)) {
                    throw new IOException();
                }
            }
        }
    }
}
