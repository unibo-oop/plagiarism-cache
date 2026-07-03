package it.lttply.model.domain.managers;

import java.io.File;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.lttply.model.domain.Credits;
import it.lttply.model.domain.CreditsConcrete;
import it.lttply.model.domain.Episode;
import it.lttply.model.domain.EpisodeConcrete;
import it.lttply.model.domain.MediaContainer;
import it.lttply.model.domain.Movie;
import it.lttply.model.domain.MovieConcrete;
import it.lttply.model.domain.Person;
import it.lttply.model.domain.PersonConcrete;
import it.lttply.model.domain.Picture;
import it.lttply.model.domain.PictureConcrete;
import it.lttply.model.domain.PictureFormat;
import it.lttply.model.domain.Season;
import it.lttply.model.domain.SeasonConcrete;
import it.lttply.model.domain.TVSerie;
import it.lttply.model.domain.TVSerieConcrete;
import it.lttply.model.utils.APIUtils;
import it.lttply.model.utils.ConsolePrinter;
import it.lttply.model.utils.PictureType;

/**
 * Provides advanced functions to {@link SQLiteBasic} useful to manage media
 * files. It permits to run particular pre-built queries, wrapping the
 * {@link SQLiteBasic} in order to avoid the use of the standard methods
 * simplifying the operations. See the javadoc for every single method for major
 * details.
 */
@SuppressFBWarnings(value = "SQL_NONCONSTANT_STRING_PASSED_TO_EXECUTE", justification = "The seminar about how to properly interface java to an external db was held later than when I wrote this code. Anyway the protection from Sql-injection has been managed manually. In a near future I will fix it with the new specifications.")
public final class SQLiteAdvanced {
    private static final String SELECT_FROM_SEASONS_WHERE_ID_SERIE = "SELECT * FROM Seasons WHERE idSerie='";
    private static final String ADDING_SEASON_STRING = "Adding S";
    private static final String QUERY_FIELD_EMPTY = "'', ";
    private static final String QUERY_LAST_FIELD_EMPTY = "'')";
    private static final String QUERY_COMMA_SINGLE_APEX = "', ";
    private static final String SQL_ID_SEASON = "idSeason";
    private static final String SQL_ID_TAG = "idTag";
    private static final String SQL_AUTHOR = "author";
    private static final String SQL_FORMAT = "format";
    private static final String SQL_ID_PICTURE = "idPicture";
    private static final String SQL_ID_POSTER = "idPoster";
    private static final String SQL_NAME = "name";
    private static final String SQL_ID_PERSON = "idPerson";
    private static final String SQL_RATING = "rating";
    private static final String SQL_OVERVIEW = "overview";
    private static final String SQL_PHYSICAL_LOCATION = "physicalLocation";
    private static final String SQL_RELEASE_DATE = "releaseDate";
    private static final String SQL_TITLE = "title";
    private static final String SQL_SIZE = "size";
    private static final String QRY_COMMA_DOUBLE_APEX = "', '";
    private static final int QUERY_LENGHT = 1000;
    private static final String ERR_TYPE_NOT_VALID = "A Movie doesn't have a profile image picture!";

    private static final Object LOCK = new Object();

    private final SQLiteBasic manager;

    /**
     * Identifies the role of a {@link Person} into the {@link MediaContainer}
     * specified, this is just a conventional representation: indeed the
     * {@link String} values of the enumerators are used to identify the role of
     * a {@link Person} <b>only in the database and anywhere else</b>. Use it at
     * your own risk.
     */
    private enum RoleDB {
        /**
         * Identifies the {@link Person} as creator.
         */
        CREATOR("creator"),

        /**
         * Identifies the {@link Person} as actor.
         */
        ACTOR("actor");

        private final String value;

        /**
         * The private constructor of the enumerator
         * 
         * @param value
         *            the {@link String} value which represents the specific
         *            role
         */
        RoleDB(final String value) {
            this.value = value;
        }

        /**
         * @return the {@link String} value for the role: this parameter is used
         *         to identify the role of a {@link Person} for a specific
         *         {@link MediaContainer} <b>into the database</b>
         */
        public String getValue() {
            return this.value;
        }
    }

    private static class DBManHolder {
        private static final SQLiteAdvanced SINGLETON = new SQLiteAdvanced(
                EnvironmentStandard.getDatabasePreferredLocation());
    }

    /**
     * Constructs a new {@link SQLiteAdvanced} instance.
     * 
     * @param databaseFullPath
     *            the path which indicates where the database is located
     */
    private SQLiteAdvanced(final String databaseFullPath) {
        this.manager = new SQLiteBasicConcrete(databaseFullPath);
        this.manager.initialize();
    }

    /**
     * @return the instance manager of the database
     */
    public static SQLiteAdvanced getDBManager() {
        return DBManHolder.SINGLETON;
    }

    /**
     * Adds a new {@link TVSerie} to the database.
     * <p>
     * If the countries when the {@link Movie} was shot are not already present
     * into the database, they are automatically added.
     * <p>
     * The {@link Credits}, Backdrop ({@link Picture}), Poster ({@link Picture})
     * entries for the {@link Movie} are automatically created.
     * 
     * @param tvSerie
     *            the {@link TVSerie} to be added
     * @throws SQLException
     *             thrown if an exception occurs while querying the database
     */
    public void addTVSerie(final TVSerie tvSerie) throws SQLException {
        synchronized (LOCK) {

            final String tvSerieID = tvSerie.getID();

            // Aggiunta BACKDROP e POSTER
            if (tvSerie.getBackdrop().isPresent()) {
                final String backdropQuery = composeInsertPictureQuery(tvSerie.getBackdrop().get());
                this.manager.setInsertRemove(backdropQuery);
                if (tvSerie.getBackdrop().get().getTags().isPresent()) {
                    insertTagsIfNotPresent(tvSerie.getBackdrop().get().getTags().get());
                }
            }

            if (tvSerie.getPoster().isPresent()) {
                final String posterQuery = composeInsertPictureQuery(tvSerie.getPoster().get());
                this.manager.setInsertRemove(posterQuery);
                if (tvSerie.getPoster().get().getTags().isPresent()) {
                    insertTagsIfNotPresent(tvSerie.getPoster().get().getTags().get());
                }
            }

            // Aggiunta TVSerie
            final String tvSerieQuery = composeInsertTVSerieQuery(tvSerie);
            this.manager.setInsertRemove(tvSerieQuery);

            // Aggiunta TAGS
            if (tvSerie.getTags().isPresent()) {
                final Set<String> tagsIDs = insertTagsIfNotPresent(tvSerie.getTags().get());
                for (final String singleIDTag : tagsIDs) {
                    final String qry = "INSERT INTO TVSeries_Tags VALUES ('" + tvSerieID + QRY_COMMA_DOUBLE_APEX
                            + singleIDTag + "')";
                    this.manager.setInsertRemove(qry);
                }
            }

            // Aggiunta COUNTRIES
            if (tvSerie.getCountries().isPresent()) {
                final Set<String> countries = new HashSet<>(tvSerie.getCountries().get());

                for (final String singleCountry : countries) {
                    final String qry = "INSERT INTO Movies_Countries VALUES ('" + tvSerieID + QRY_COMMA_DOUBLE_APEX
                            + singleCountry + "')";
                    this.manager.setInsertRemove(qry);
                }
            }

            // Aggiunta CREDITS e PERSON
            if (tvSerie.getCredits().isPresent()) {
                final Credits crd = tvSerie.getCredits().get();

                final Set<String> actorsIDs = new HashSet<>();

                for (final Person tmp : crd.getCast()) {
                    actorsIDs.add(insertPersonIfNotPresent(tmp));
                }

                if (crd.getCreator() != null) {
                    final String creatorID = insertPersonIfNotPresent(crd.getCreator());
                    final String queryCreator = "INSERT INTO Credits_TVSeries VALUES ('" + tvSerieID
                            + QRY_COMMA_DOUBLE_APEX + creatorID + QRY_COMMA_DOUBLE_APEX + RoleDB.CREATOR.getValue()
                            + "')";
                    this.manager.setInsertRemove(queryCreator);
                }

                for (final String singleID : actorsIDs) {
                    final String qry = "INSERT INTO Credits_TVSeries VALUES ('" + tvSerieID + QRY_COMMA_DOUBLE_APEX
                            + singleID + QRY_COMMA_DOUBLE_APEX + RoleDB.ACTOR.getValue() + "')";
                    this.manager.setInsertRemove(qry);
                }
            }

            // Aggiunta SEASONS
            if (tvSerie.getElements().isPresent()) {
                final Set<Season> seasons = tvSerie.getElements().get();
                for (final Season ssn : seasons) {
                    if (ssn.getPoster().isPresent()) {
                        final String seasonPictureQuery = composeInsertPictureQuery(ssn.getPoster().get());
                        this.manager.setInsertRemove(seasonPictureQuery);
                    }

                    ConsolePrinter.getPrinter().printlnDebug(
                            ADDING_SEASON_STRING + ssn.getProgressiveNumber() + " to " + tvSerie.getTitle());

                    final String seasonQuery = composeInsertSeasonQuery(ssn, tvSerie.getID());
                    this.manager.setInsertRemove(seasonQuery);

                    if (ssn.getElements().isPresent()) {
                        for (final Episode eps : ssn.getElements().get()) {
                            if (eps.getPoster().isPresent()) {
                                final String episodePosterQuery = composeInsertPictureQuery(eps.getPoster().get());
                                this.manager.setInsertRemove(episodePosterQuery);
                            }
                            ConsolePrinter.getPrinter()
                                    .printlnDebug(ADDING_SEASON_STRING + ssn.getProgressiveNumber() + "E"
                                            + eps.getProgressiveNumber() + " (" + eps.getTitle() + ") to TV Serie "
                                            + tvSerieID);
                            final String episodeQuery = composeInsertEpisodeQuery(eps, ssn.getID());
                            this.manager.setInsertRemove(episodeQuery);
                        }
                    }

                }

            }
        }
    }

    /**
     * Deletes a {@link TVSerie} from the database.
     * <p>
     * The countries when the {@link TVSerie} was shot are not deleted.
     * <p>
     * The {@link Credits}, Backdrop ({@link Picture}), Poster ({@link Picture})
     * entries for the {@link TVSerie} are automatically deleted.
     * <p>
     * If the tv series specified does not exists, no changes are made to the
     * database
     * 
     * @param tvSerieID
     *            the identifier of the {@link TVSerie} to be deleted
     * @throws SQLException
     *             thrown if an exception occurs while querying the database
     */
    public void removeTVSerie(final String tvSerieID) throws SQLException {
        synchronized (LOCK) {
            final TVSerie tvSerie = this.getTVSerie(tvSerieID);

            if (tvSerie != null) {

                // Rimozione CREDITS
                if (tvSerie.getCredits().isPresent()) {
                    final String queryCredits = "DELETE FROM Credits_TVSeries WHERE idSerie='" + tvSerieID + "'";
                    this.manager.setInsertRemove(queryCredits);
                }

                // Rimozione POSTER
                final Picture poster = this.getTVSeriePicture(tvSerieID, PictureType.IMAGE_POSTER);
                if (poster != null) {
                    deletePicture(poster.getID());
                }

                // Rimozione BACKDROP
                final Picture backdrop = this.getTVSeriePicture(tvSerieID, PictureType.IMAGE_BACKDROP);
                if (backdrop != null) {
                    deletePicture(backdrop.getID());
                }

                // Rimozione TAGS
                if (!this.getTagsFromTVSerie(tvSerieID).isEmpty()) {
                    final String queryTags = "DELETE FROM TVSeries_Tags WHERE idSerie='" + tvSerieID + "'";
                    this.manager.setInsertRemove(queryTags);
                }

                // Rimozione COUNTRIES
                if (!this.getCountriesFromTVSerie(tvSerieID).isEmpty()) {
                    final String queryCountries = "DELETE FROM TVSeries_Countries WHERE idSerie='" + tvSerieID + "'";
                    this.manager.setInsertRemove(queryCountries);
                }

                // Rimozione EPISODE
                if (tvSerie.getElements().isPresent()) {
                    for (final Season ssn : tvSerie.getElements().get()) {
                        if (ssn.getElements().isPresent()) {
                            for (final Episode eps : ssn.getElements().get()) {
                                final Picture episodePicture = this.getEpisodePicture(eps.getID());
                                if (episodePicture != null) {
                                    deletePicture(episodePicture.getID());
                                }
                                final String episodeQuery = "DELETE FROM Episodes WHERE idEpisode='" + eps.getID()
                                        + "'";
                                this.manager.setInsertRemove(episodeQuery);
                            }
                        }

                        final Picture seasonPicture = this.getSeasonPicture(ssn.getID());
                        if (seasonPicture != null) {
                            deletePicture(seasonPicture.getID());
                        }
                        final String seasonQuery = "DELETE FROM Seasons WHERE idSeason='" + ssn.getID() + "'";
                        this.manager.setInsertRemove(seasonQuery);
                    }
                }

                // Rimozione SERIE_TV
                final String tvSerieQuery = "DELETE FROM TVSeries WHERE idSerie='" + tvSerieID + "'";
                this.manager.setInsertRemove(tvSerieQuery);

            } else {
                throw new IllegalStateException();
            }
        }

    }

    /**
     * Removes the specified episode which belongs to a specified season which
     * belongs to a specific tv serie. If the tv serie, season or episode
     * specified don't exist, no changes are made.
     * 
     * @param tvSerieID
     *            the tv serie id
     * @param seasonProgressiveNumber
     *            the season number
     * @param episodeProgressiveNumber
     *            the episode to be deleted
     * @throws SQLException
     *             thrown if an exception occurs while querying the database
     */
    public void removeEpisodeFrom(final String tvSerieID, final int seasonProgressiveNumber,
            final int episodeProgressiveNumber) throws SQLException {
        synchronized (LOCK) {
            if (!tvSeriesExists(tvSerieID)) {
                throw new IllegalStateException();
            }

            // Searching for the season
            final String query = SELECT_FROM_SEASONS_WHERE_ID_SERIE + tvSerieID + "' AND progressiveNumber='"
                    + seasonProgressiveNumber + "'";
            String idSeason = null;
            try (Statement smt = this.manager.connect().createStatement(); ResultSet rslt = smt.executeQuery(query)) {
                if (rslt.next()) {
                    idSeason = normalize(rslt.getString(SQL_ID_SEASON));
                    if (idSeason == null) {
                        throw new IllegalStateException();
                    }
                }
            }

            // Searching for the episode
            final String query2 = "SELECT * FROM Episodes WHERE idSeason='" + idSeason + "' AND progressiveNumber='"
                    + episodeProgressiveNumber + "'";
            String idEpisode = null;
            try (Statement smt = this.manager.connect().createStatement(); ResultSet rslt = smt.executeQuery(query2)) {
                if (rslt.next()) {
                    idEpisode = normalize(rslt.getString("idEpisode"));
                    if (idEpisode == null) {
                        throw new IllegalStateException();
                    }
                }
            }

            this.manager.setInsertRemove("DELETE FROM Episodes WHERE idEpisode='" + idEpisode + "'");

        }

    }

    /**
     * Adds the specified {@link Episode} to the specified season of a specific
     * tv serie.
     * 
     * @param tvSerieID
     *            the tv serie id
     * @param seasonProgressiveNumber
     *            the season number
     * @param episode
     *            the {@link Episode} to be added
     * @throws SQLException
     *             thrown if an exception occurs while querying the database
     */
    public void addEpisodeTo(final String tvSerieID, final int seasonProgressiveNumber, final Episode episode)
            throws SQLException {
        synchronized (LOCK) {
            if (!tvSeriesExists(tvSerieID)) {
                throw new IllegalStateException();
            }

            // Searching for the season
            final String query = SELECT_FROM_SEASONS_WHERE_ID_SERIE + tvSerieID + "' AND progressiveNumber='"
                    + seasonProgressiveNumber + "'";
            String idSeason = null;
            try (Statement smt = this.manager.connect().createStatement(); ResultSet rslt = smt.executeQuery(query)) {
                if (rslt.next()) {
                    idSeason = normalize(rslt.getString(SQL_ID_SEASON));
                    if (idSeason == null) {
                        throw new IllegalStateException();
                    }
                }
            }

            // Check if the episode already exists
            final String queryEpisode = "SELECT FROM Episodes WHERE progressiveNumber='"
                    + episode.getProgressiveNumber() + "' AND idSeason='" + idSeason + "'";

            try (Statement smt = this.manager.connect().createStatement();
                    ResultSet rslt = smt.executeQuery(queryEpisode)) {
                if (rslt.next()) {
                    throw new IllegalStateException();
                }
            }

            this.manager.setInsertRemove(composeInsertEpisodeQuery(episode, idSeason));
        }
    }

    /**
     * Adds the specified season to a specific tv serie. If the season is
     * already present, no changes are made.
     * 
     * @param tvSerieID
     *            the tv serie id
     * @param season
     *            the {@link Season} to be added
     * @throws SQLException
     *             thrown if an exception occurs while querying the database
     */
    public void addSeasonTo(final String tvSerieID, final Season season) throws SQLException {
        synchronized (LOCK) {
            if (!tvSeriesExists(tvSerieID)) {
                throw new IllegalStateException();
            }

            if (seasonExists(season.getID())) {
                throw new IllegalStateException();
            }

            this.manager.setInsertRemove(composeInsertSeasonQuery(season, tvSerieID));

            if (season.getElements().isPresent()) {
                for (final Episode eps : season.getElements().get()) {
                    this.manager.setInsertRemove(composeInsertEpisodeQuery(eps, season.getID()));
                }
            }

        }
    }

    /**
     * Adds a new {@link Movie} to the database.
     * 
     * If the countries when the {@link TVSerie} was shot are not already
     * present into the database, they are automatically added.
     * <p>
     * The {@link Credits}, Backdrop ({@link Picture}), Poster ({@link Picture})
     * entries for the {@link Movie} are automatically created.
     * 
     * @param movie
     *            the {@link Movie} to be added
     * @throws SQLException
     *             thrown if an exception occurs while querying the database
     */
    public void addMovie(final Movie movie) throws SQLException {
        synchronized (LOCK) {

            final String movieID = movie.getID();

            // Aggiunta BACKDROP e POSTER
            if (movie.getPoster().isPresent()) {
                final String queryPoster = composeInsertPictureQuery(movie.getPoster().get());
                this.manager.setInsertRemove(queryPoster);
                if (movie.getPoster().get().getTags().isPresent()) {
                    insertTagsIfNotPresent(movie.getPoster().get().getTags().get());
                }
            }

            if (movie.getBackdrop().isPresent()) {
                final String queryBackdrop = composeInsertPictureQuery(movie.getBackdrop().get());
                this.manager.setInsertRemove(queryBackdrop);
                if (movie.getBackdrop().get().getTags().isPresent()) {
                    insertTagsIfNotPresent(movie.getBackdrop().get().getTags().get());
                }
            }

            // Aggiunta MOVIE
            final String queryMovie = composeInsertMovieQuery(movie);
            this.manager.setInsertRemove(queryMovie);

            // Aggiunta TAGS
            if (movie.getTags().isPresent()) {
                final Set<String> tagsIDs = insertTagsIfNotPresent(movie.getTags().get());
                for (final String singleIDTag : tagsIDs) {
                    final String qry = "INSERT INTO Movies_Tags VALUES ('" + movieID + QRY_COMMA_DOUBLE_APEX
                            + singleIDTag + "')";
                    this.manager.setInsertRemove(qry);
                }
            }

            // Aggiunta COUNTRIES
            if (movie.getCountries().isPresent()) {
                final Set<String> countries = new HashSet<>(movie.getCountries().get());

                for (final String singleCountry : countries) {
                    final String qry = "INSERT INTO Movies_Countries VALUES ('" + movieID + QRY_COMMA_DOUBLE_APEX
                            + singleCountry + "')";
                    this.manager.setInsertRemove(qry);
                }
            }

            // Aggiunta CREDITS e PERSON
            if (movie.getCredits().isPresent()) {
                final Credits crd = movie.getCredits().get();
                final Set<String> actorsIDs = new HashSet<>();

                for (final Person tmp : crd.getCast()) {
                    actorsIDs.add(insertPersonIfNotPresent(tmp));
                }

                if (crd.getCreator() != null) {
                    final String creatorID = insertPersonIfNotPresent(crd.getCreator());
                    final String queryCreator = "INSERT INTO Credits_Movie VALUES ('" + movieID + QRY_COMMA_DOUBLE_APEX
                            + creatorID + QRY_COMMA_DOUBLE_APEX + RoleDB.CREATOR.getValue() + "')";
                    this.manager.setInsertRemove(queryCreator);
                }

                for (final String singleID : actorsIDs) {
                    final String qry = "INSERT INTO Credits_Movie VALUES ('" + movieID + QRY_COMMA_DOUBLE_APEX
                            + singleID + QRY_COMMA_DOUBLE_APEX + RoleDB.ACTOR.getValue() + "')";
                    this.manager.setInsertRemove(qry);
                }
            }
        }

    }

    /**
     * Deletes a {@link Movie} from the database.
     * <p>
     * The countries when the {@link Movie} was shot are not deleted.
     * <p>
     * The {@link Credits}, Backdrop ({@link Picture}), Poster ({@link Picture})
     * entries for the {@link Movie} are automatically deleted.
     * <p>
     * If the movie specified does not exists, no changes are made to the
     * database.
     * 
     * @param movieID
     *            the identifier of the {@link Movie} to be deleted
     * @throws SQLException
     *             thrown if an exception occurs while querying the database
     */
    public void removeMovie(final String movieID) throws SQLException {
        synchronized (LOCK) {
            final Movie tmp = this.getMovie(movieID);
            if (tmp != null) {
                // Rimozione CREDITS
                final String queryCredits = "DELETE FROM Credits_Movie WHERE idMovie='" + movieID + "'";
                this.manager.setInsertRemove(queryCredits);

                // Rimozione POSTER
                final Picture poster = this.getMoviePicture(movieID, PictureType.IMAGE_POSTER);
                if (poster != null) {
                    final String queryPoster = "DELETE FROM Pictures WHERE idPicture='" + poster.getID() + "'";
                    this.manager.setInsertRemove(queryPoster);
                }

                // Rimozione BACKDROP
                final Picture backdrop = this.getMoviePicture(movieID, PictureType.IMAGE_BACKDROP);
                if (backdrop != null) {
                    final String queryBackdrop = "DELETE FROM Pictures WHERE idPicture='" + backdrop.getID() + "'";
                    this.manager.setInsertRemove(queryBackdrop);
                }

                // Rimozione TAGS
                if (!this.getTagsFromMovie(movieID).isEmpty()) {
                    final String queryTags = "DELETE FROM Movies_Tags WHERE idMovie='" + movieID + "'";
                    this.manager.setInsertRemove(queryTags);
                }

                // Rimozione COUNTRIES
                if (!this.getCountriesFromMovie(movieID).isEmpty()) {
                    final String queryCountries = "DELETE FROM Movies_Countries WHERE idMovie='" + movieID + "'";
                    this.manager.setInsertRemove(queryCountries);
                }

                // Rimozione MOVIE
                final String queryMovie = "DELETE FROM Movies WHERE idMovie='" + movieID + "'";
                this.manager.setInsertRemove(queryMovie);
            } else {
                throw new IllegalStateException();
            }
        }
    }

    /**
     * Gets the {@link Movie} which corresponds to the ID passed.
     * 
     * @param movieID
     *            the identifier of the {@link Movie}
     * @return the {@link Movie} found, if not present <code>null</code> is
     *         returned
     * @throws SQLException
     *             thrown if an exception occurs while querying the database
     */
    public Movie getMovie(final String movieID) throws SQLException {
        synchronized (LOCK) {

            final String query = "SELECT * FROM Movies WHERE idMovie='" + movieID + "'";

            try (Statement smt = this.manager.connect().createStatement(); ResultSet rslt = smt.executeQuery(query)) {
                if (rslt.next()) {
                    final MovieConcrete.MovieBuilderConcrete bld = new MovieConcrete.MovieBuilderConcrete();
                    bld.id(rslt.getString("idMovie"));
                    bld.duration(rslt.getInt("duration"));
                    bld.size(rslt.getInt(SQL_SIZE));
                    bld.title(rslt.getString(SQL_TITLE));
                    bld.releaseDate(LocalDate.parse(rslt.getString(SQL_RELEASE_DATE),
                            DateTimeFormatter.ofPattern(APIUtils.DEFAULT_DATE_PATTERN)));
                    bld.physicalLocation(Paths.get(rslt.getString(SQL_PHYSICAL_LOCATION)));
                    bld.overview(normalize(rslt.getString(SQL_OVERVIEW)));
                    bld.rating(rslt.getDouble(SQL_RATING));
                    bld.poster(this.getMoviePicture(movieID, PictureType.IMAGE_POSTER));
                    bld.backdrop(this.getMoviePicture(movieID, PictureType.IMAGE_BACKDROP));

                    final Set<String> cnt = this.getCountriesFromMovie(movieID);
                    if (!cnt.isEmpty()) {
                        bld.countries(cnt);
                    }

                    final Map<String, String> tgs = this.getTagsFromMovie(movieID);
                    if (!tgs.isEmpty()) {
                        bld.tags(tgs);
                    }

                    bld.credits(this.getCreditsFromMovie(movieID));

                    return bld.build();
                }
            }

            return null;
        }

    }

    /**
     * Gets the specified {@link Movie} searching for location.
     * 
     * @param moviePhysicalLocation
     *            the full path of the file
     * @return the {@link Movie} according to the path passed, <code>null</code>
     *         if not present or the path is not valid
     * @throws SQLException
     *             thrown if an exception occurs while querying the database
     */
    public Movie getMovieByLocation(final File moviePhysicalLocation) throws SQLException {
        synchronized (LOCK) {

            final String query = "SELECT * FROM Movies WHERE physicalLocation='" + moviePhysicalLocation.toString()
                    + "'";

            try (Statement smt = this.manager.connect().createStatement(); ResultSet rslt = smt.executeQuery(query)) {
                if (rslt.next()) {
                    final MovieConcrete.MovieBuilderConcrete bld = new MovieConcrete.MovieBuilderConcrete();
                    bld.id(rslt.getString("idMovie"));
                    bld.duration(rslt.getInt("duration"));
                    bld.size(rslt.getInt(SQL_SIZE));
                    bld.title(rslt.getString(SQL_TITLE));
                    bld.releaseDate(LocalDate.parse(rslt.getString(SQL_RELEASE_DATE),
                            DateTimeFormatter.ofPattern(APIUtils.DEFAULT_DATE_PATTERN)));
                    bld.physicalLocation(Paths.get(rslt.getString(SQL_PHYSICAL_LOCATION)));
                    bld.overview(normalize(rslt.getString(SQL_OVERVIEW)));
                    bld.rating(rslt.getDouble(SQL_RATING));
                    bld.poster(this.getMoviePicture(rslt.getString("idMovie"), PictureType.IMAGE_POSTER));
                    bld.backdrop(this.getMoviePicture(rslt.getString("idMovie"), PictureType.IMAGE_BACKDROP));

                    final Set<String> cnt = this.getCountriesFromMovie(rslt.getString("idMovie"));
                    if (!cnt.isEmpty()) {
                        bld.countries(cnt);
                    }

                    final Map<String, String> tgs = this.getTagsFromMovie(rslt.getString("idMovie"));
                    if (!tgs.isEmpty()) {
                        bld.tags(tgs);
                    }

                    bld.credits(this.getCreditsFromMovie(rslt.getString("idMovie")));

                    return bld.build();
                }
            }
            return null;
        }

    }

    /**
     * Gets the {@link TVSerie} which corresponds to the ID passed.
     * 
     * @param tvSerieID
     *            the identifier of the {@link TVSerie}
     * @return the {@link TVSerie} found, if not present <code>null</code> is
     *         returned
     * @throws SQLException
     *             thrown if an exception occurs whiòe querying the database
     */
    public TVSerie getTVSerie(final String tvSerieID) throws SQLException {
        synchronized (LOCK) {

            final String query = "SELECT * FROM TVSeries WHERE idSerie='" + tvSerieID + "'";

            try (Statement smt = this.manager.connect().createStatement(); ResultSet rslt = smt.executeQuery(query)) {
                if (rslt.next()) {
                    final TVSerieConcrete.TVSerieConcreteBuilder bld = new TVSerieConcrete.TVSerieConcreteBuilder();
                    bld.id(rslt.getString("idSerie"));
                    bld.title(rslt.getString(SQL_TITLE));
                    bld.releaseDate(LocalDate.parse(rslt.getString(SQL_RELEASE_DATE),
                            DateTimeFormatter.ofPattern(APIUtils.DEFAULT_DATE_PATTERN)));
                    bld.physicalLocation(Paths.get(rslt.getString(SQL_PHYSICAL_LOCATION)));
                    bld.overview(normalize(rslt.getString(SQL_OVERVIEW)));
                    bld.rating(rslt.getDouble(SQL_RATING));
                    bld.poster(this.getTVSeriePicture(tvSerieID, PictureType.IMAGE_POSTER));
                    bld.backdrop(this.getTVSeriePicture(tvSerieID, PictureType.IMAGE_BACKDROP));

                    final Set<String> cnt = this.getCountriesFromTVSerie(tvSerieID);
                    if (!cnt.isEmpty()) {
                        bld.countries(cnt);
                    }

                    final Map<String, String> tgs = this.getTagsFromTVSerie(tvSerieID);
                    if (!tgs.isEmpty()) {
                        bld.tags(tgs);
                    }

                    bld.credits(this.getCreditsFromTVSerie(tvSerieID));

                    final Set<Season> seasons = new HashSet<>();
                    final String querySeasons = SELECT_FROM_SEASONS_WHERE_ID_SERIE + tvSerieID + "'";
                    try (Statement smt2 = this.manager.connect().createStatement();
                            ResultSet rslt2 = smt.executeQuery(querySeasons)) {
                        while (rslt.next()) {
                            seasons.add(getSeason(rslt.getString(SQL_ID_SEASON)));
                        }

                    }
                    if (!seasons.isEmpty()) {
                        bld.elements(seasons);
                    }
                    bld.size(-1);
                    return bld.build();
                }
            }

            return null;
        }

    }

    /**
     * Gets the specified {@link TVSerie} searching for location.
     * 
     * @param tvSerieRootPhysicalLocation
     *            the full path of the root where the episodes are stored
     * @return the {@link TVSerie} according to the path passed,
     *         <code>null</code> if not present or the path is not valid
     * @throws SQLException
     *             thrown if an exception occurs while querying the database
     */
    public TVSerie getTVSerieByPhisicalLocation(final File tvSerieRootPhysicalLocation) throws SQLException {
        synchronized (LOCK) {
            final String query = "SELECT * FROM TVSeries WHERE physicalLocation='" + tvSerieRootPhysicalLocation + "'";

            try (Statement smt = this.manager.connect().createStatement(); ResultSet rslt = smt.executeQuery(query)) {
                if (rslt.next()) {
                    final TVSerieConcrete.TVSerieConcreteBuilder bld = new TVSerieConcrete.TVSerieConcreteBuilder();
                    bld.id(rslt.getString("idSerie"));
                    bld.title(rslt.getString(SQL_TITLE));
                    bld.releaseDate(LocalDate.parse(rslt.getString(SQL_RELEASE_DATE),
                            DateTimeFormatter.ofPattern(APIUtils.DEFAULT_DATE_PATTERN)));
                    bld.physicalLocation(Paths.get(rslt.getString(SQL_PHYSICAL_LOCATION)));
                    bld.overview(normalize(rslt.getString(SQL_OVERVIEW)));
                    bld.rating(rslt.getDouble(SQL_RATING));
                    bld.poster(this.getTVSeriePicture(rslt.getString("idSerie"), PictureType.IMAGE_POSTER));
                    bld.backdrop(this.getTVSeriePicture(rslt.getString("idSerie"), PictureType.IMAGE_BACKDROP));

                    final Set<String> cnt = this.getCountriesFromTVSerie(rslt.getString("idSerie"));
                    if (!cnt.isEmpty()) {
                        bld.countries(cnt);
                    }

                    final Map<String, String> tgs = this.getTagsFromTVSerie(rslt.getString("idSerie"));
                    if (!tgs.isEmpty()) {
                        bld.tags(tgs);
                    }

                    bld.credits(this.getCreditsFromTVSerie(rslt.getString("idSerie")));

                    final Set<Season> seasons = new HashSet<>();
                    final String querySeasons = SELECT_FROM_SEASONS_WHERE_ID_SERIE + rslt.getString("idSerie") + "'";
                    try (Statement smt2 = this.manager.connect().createStatement();
                            ResultSet rslt2 = smt.executeQuery(querySeasons)) {
                        while (rslt.next()) {
                            seasons.add(getSeason(rslt.getString(SQL_ID_SEASON)));
                        }

                    }
                    if (!seasons.isEmpty()) {
                        bld.elements(seasons);
                    }
                    bld.size(-1);
                    return bld.build();
                }
            }

            return null;
        }
    }

    /**
     * Removes every information which belongs to the movies.
     * 
     * @throws SQLException
     *             thrown if an exception occurs while querying the database
     */
    public void emptyMoviesOnly() throws SQLException {
        ConsolePrinter.getPrinter().printlnProcedureStarted("Deleting all movies...");
        for (final Movie mv : this.getAllMovies()) {
            this.removeMovie(mv.getID());
        }
    }

    /**
     * Removes every information which belongs to the tv series.
     * 
     * @throws SQLException
     *             thrown if an exception occurs while querying the database
     */
    public void emptyTVSeriesOnly() throws SQLException {

        ConsolePrinter.getPrinter().printlnProcedureStarted("Deleting all TV Series");
        for (final TVSerie tv : this.getAllTVSeries()) {
            this.removeTVSerie(tv.getID());
        }
    }

    /**
     * Deletes <b>everything</b> from the database.
     * 
     * @throws SQLException
     *             if an exception occurs while querying the database
     */
    public void empty() throws SQLException {
        synchronized (LOCK) {
            ConsolePrinter.getPrinter().printlnProcedureStarted("Emptying database...");
            this.manager.setInsertRemove("delete from Credits_Movie;");
            this.manager.setInsertRemove("delete from Credits_TVSeries;");
            this.manager.setInsertRemove("delete from Episodes;");
            this.manager.setInsertRemove("delete from Movies;");
            this.manager.setInsertRemove("delete from Movies_Countries;");
            this.manager.setInsertRemove("delete from Movies_Tags;");
            this.manager.setInsertRemove("delete from Pictures;");
            this.manager.setInsertRemove("delete from Pictures_Tags;");
            this.manager.setInsertRemove("delete from Persons;");
            this.manager.setInsertRemove("delete from Seasons;");
            this.manager.setInsertRemove("delete from TVSeries;");
            this.manager.setInsertRemove("delete from TVSeries_Countries;");
            this.manager.setInsertRemove("delete from TVSeries_Tags;");
            this.manager.setInsertRemove("delete from Tags;");
        }
    }

    /**
     * Gets all {@link Movie}s stored into the database.
     * 
     * @return the {@link Set} which contains the movies. If the database is
     *         <code>empty</code>, an <code>empty</code> {@link Set} is returned
     * @throws SQLException
     *             thrown if an exception occurs while querying the database
     */
    public Set<Movie> getAllMovies() throws SQLException {
        synchronized (LOCK) {
            final String query = "SELECT * FROM Movies";

            final Set<Movie> movies = new HashSet<>();
            try (Statement smt = this.manager.connect().createStatement(); ResultSet rslt = smt.executeQuery(query)) {
                while (rslt.next()) {
                    movies.add(getMovie(rslt.getString("idMovie")));
                }
            }
            return movies;
        }
    }

    /**
     * Gets all {@link TVSerie}s stored into the database.
     * 
     * @return the {@link Set} which contains the tv series. If the database is
     *         <code>empty</code>, an <code>empty</code> {@link Set} is returned
     * @throws SQLException
     *             thrown if an exception occurs while querying the database
     */
    public Set<TVSerie> getAllTVSeries() throws SQLException {
        synchronized (LOCK) {

            ConsolePrinter.getPrinter().printlnProcedureStarted("DATABASE: Querying for all tv series, please wait...");
            final String query = "SELECT * FROM TVSeries";

            final Set<TVSerie> tvseries = new HashSet<>();
            try (Statement smt = this.manager.connect().createStatement(); ResultSet rslt = smt.executeQuery(query)) {
                while (rslt.next()) {
                    tvseries.add(getTVSerie(rslt.getString("idSerie")));
                }
            }
            return tvseries;
        }
    }

    // ======================================== //
    // =========== INTERNAL METHODS =========== //
    // ======================================== //
    private Season getSeason(final String seasonID) throws SQLException {
        final String query = "SELECT * FROM Seasons WHERE idSeason='" + seasonID + "'";

        try (Statement smt = this.manager.connect().createStatement(); ResultSet rslt = smt.executeQuery(query)) {
            if (rslt.next()) {
                final SeasonConcrete.SeasonConcreteBuilder bld = new SeasonConcrete.SeasonConcreteBuilder();
                bld.id(rslt.getString(SQL_ID_SEASON));
                bld.title(rslt.getString(SQL_TITLE));
                bld.releaseDate(LocalDate.parse(rslt.getString(SQL_RELEASE_DATE),
                        DateTimeFormatter.ofPattern(APIUtils.DEFAULT_DATE_PATTERN)));
                bld.overview(normalize(rslt.getString(SQL_OVERVIEW)));
                bld.progressiveNumber(rslt.getInt("progressiveNumber"));
                bld.poster(this.getSeasonPicture(seasonID));

                final Set<Episode> episodes = new HashSet<>();
                final String queryEpisodes = "SELECT * FROM Episodes WHERE idSeason='" + seasonID + "'";
                try (Statement smt2 = this.manager.connect().createStatement();
                        ResultSet rslt2 = smt.executeQuery(queryEpisodes)) {
                    while (rslt.next()) {
                        episodes.add(getEpisode(rslt.getString("idEpisode")));
                    }
                }

                bld.elements(episodes);
                return bld.build();
            }
        }

        return null;
    }

    private Episode getEpisode(final String episodeID) throws SQLException {
        final String query = "SELECT * FROM Episodes WHERE idEpisode='" + episodeID + "'";
        try (Statement smt = this.manager.connect().createStatement(); ResultSet rslt = smt.executeQuery(query)) {
            if (rslt.next()) {
                final EpisodeConcrete.EpisodeConcreteBuilder bld = new EpisodeConcrete.EpisodeConcreteBuilder();
                bld.id(rslt.getString("idEpisode"));
                bld.size(rslt.getInt(SQL_SIZE));
                bld.title(rslt.getString(SQL_TITLE));
                bld.releaseDate(LocalDate.parse(rslt.getString(SQL_RELEASE_DATE),
                        DateTimeFormatter.ofPattern(APIUtils.DEFAULT_DATE_PATTERN)));
                bld.physicalLocation(Paths.get(rslt.getString(SQL_PHYSICAL_LOCATION)));
                bld.duration(rslt.getInt("duration"));
                bld.progressiveNumber(rslt.getInt("progressiveNumber"));
                bld.overview(normalize(rslt.getString(SQL_OVERVIEW)));
                bld.rating(rslt.getDouble(SQL_RATING));
                bld.poster(this.getEpisodePicture(episodeID));
                return bld.build();
            }
        }
        return null;
    }

    /**
     * Normalizes a string to a common value, removing any leading and trailing
     * whitespace.
     * <p>
     * For example: passing the " " string <code>null</code> is returned;
     * passing the " hello " string "hello" is returned; passing the "" string
     * <code>null</code> is returned.
     * 
     * @param string
     *            the string to be analyzed
     * @return the normalized string
     */
    public static String normalize(final String string) {
        return (string == null || string.isEmpty() || string.trim().isEmpty()) ? null : string;
    }

    private Person getPerson(final String personID) throws SQLException {
        final String query = "SELECT * FROM Persons WHERE idPerson='" + personID + "'";

        try (Statement smt = this.manager.connect().createStatement(); ResultSet rslt = smt.executeQuery(query)) {
            if (rslt.next()) {
                return new PersonConcrete(rslt.getString(SQL_ID_PERSON), rslt.getString(SQL_NAME),
                        this.getPersonPicture(personID));
            }
        }
        return null;
    }

    private Picture getTVSeriePicture(final String tvSerieID, final PictureType type) throws SQLException {
        if (type.equals(PictureType.IMAGE_PROFILE_PHOTO)) {
            throw new IllegalArgumentException(ERR_TYPE_NOT_VALID);
        }

        if (tvSeriesExists(tvSerieID)) {
            // Retrieving the TVSERIE and its PICTUREs
            final String query = "SELECT * FROM TVSeries WHERE idSerie='" + tvSerieID + "'";
            String idPicture = null;

            try (Statement smt = this.manager.connect().createStatement(); ResultSet rslt = smt.executeQuery(query)) {
                rslt.next();
                if (type.equals(PictureType.IMAGE_BACKDROP)) {
                    idPicture = rslt.getString("idBackdrop");
                } else if (type.equals(PictureType.IMAGE_POSTER)) {
                    idPicture = rslt.getString(SQL_ID_POSTER);
                }
            }

            // If the TVSERIE has a BACKDROP and/or a POSTER, composes the
            // picture and returns it
            if (normalize(idPicture) != null) {
                final String queryPicture = "SELECT * FROM Pictures WHERE idPicture='" + idPicture + "'";

                try (Statement smt = this.manager.connect().createStatement();
                        ResultSet rslt = smt.executeQuery(queryPicture)) {
                    // Il controllo se la query non ha tornato nulla,
                    // non deve servire, poichè dalla query precedente risulta
                    // che la tv serie ha effettivamente un'immagine.
                    // Se l'immagine non esiste nella tabella delle immagini c'è
                    // un problema, e se tira un'eccezione lo sgamiamo
                    rslt.next();
                    final PictureConcrete.PictureConcreteBuilder bld = new PictureConcrete.PictureConcreteBuilder();
                    bld.id(rslt.getString(SQL_ID_PICTURE));
                    bld.size(rslt.getInt(SQL_SIZE));
                    bld.title(rslt.getString(SQL_TITLE));
                    bld.releaseDate(LocalDate.parse(rslt.getString(SQL_RELEASE_DATE),
                            DateTimeFormatter.ofPattern(APIUtils.DEFAULT_DATE_PATTERN)));
                    bld.physicalLocation(Paths.get(rslt.getString(SQL_PHYSICAL_LOCATION)));
                    bld.rating(rslt.getDouble(SQL_RATING));
                    bld.overview(normalize(rslt.getString(SQL_OVERVIEW)));
                    bld.format(getFormatFromString(rslt.getString(SQL_FORMAT)));
                    bld.author(normalize(rslt.getString(SQL_AUTHOR)));

                    final Map<String, String> tgs = this.getTagsFromPicture(idPicture);
                    if (!tgs.isEmpty()) {
                        bld.tags(tgs);
                    }

                    return bld.build();
                }
            }
        }
        return null;
    }

    private Picture getSeasonPicture(final String seasonID) throws SQLException {
        if (seasonExists(seasonID)) {
            // Retrieving the SEASON and her PICTURE
            final String query = "SELECT * FROM Seasons WHERE idSeason='" + seasonID + "'";
            String idPicture = null;

            try (Statement smt = this.manager.connect().createStatement(); ResultSet rslt = smt.executeQuery(query)) {
                rslt.next();
                idPicture = rslt.getString(SQL_ID_POSTER);

            }

            // If the SEASON has a a POSTER, composes the
            // picture and returns it
            if (normalize(idPicture) != null) {
                final String queryPicture = "SELECT * FROM Pictures WHERE idPicture='" + idPicture + "'";

                try (Statement smt = this.manager.connect().createStatement();
                        ResultSet rslt = smt.executeQuery(queryPicture)) {
                    // Il controllo se la query non ha tornato nulla,
                    // non deve servire, poichè dalla query precedente risulta
                    // che la season ha effettivamente un'immagine.
                    // Se l'immagine non esiste nella tabella delle immagini c'è
                    // un problema, e se tira un'eccezione lo sgamiamo
                    rslt.next();
                    final PictureConcrete.PictureConcreteBuilder bld = new PictureConcrete.PictureConcreteBuilder();
                    bld.id(rslt.getString(SQL_ID_PICTURE));
                    bld.size(rslt.getInt(SQL_SIZE));
                    bld.title(rslt.getString(SQL_TITLE));
                    bld.releaseDate(LocalDate.parse(rslt.getString(SQL_RELEASE_DATE),
                            DateTimeFormatter.ofPattern(APIUtils.DEFAULT_DATE_PATTERN)));
                    bld.physicalLocation(Paths.get(rslt.getString(SQL_PHYSICAL_LOCATION)));
                    bld.rating(rslt.getDouble(SQL_RATING));
                    bld.overview(normalize(rslt.getString(SQL_OVERVIEW)));
                    bld.format(getFormatFromString(rslt.getString(SQL_FORMAT)));
                    bld.author(normalize(rslt.getString(SQL_AUTHOR)));

                    final Map<String, String> tgs = this.getTagsFromPicture(idPicture);
                    if (!tgs.isEmpty()) {
                        bld.tags(tgs);
                    }

                    return bld.build();
                }
            }
        }
        return null;
    }

    private Picture getEpisodePicture(final String episodeID) throws SQLException {
        if (episodeExists(episodeID)) {
            // Retrieving the EPISODE and his PICTURE
            final String query = "SELECT * FROM Episodes WHERE idEpisode='" + episodeID + "'";
            String idPicture = null;

            try (Statement smt = this.manager.connect().createStatement(); ResultSet rslt = smt.executeQuery(query)) {
                rslt.next();
                idPicture = rslt.getString(SQL_ID_POSTER);

            }

            // If the SEASON has a a POSTER, composes the
            // picture and returns it
            if (normalize(idPicture) != null) {
                final String queryPicture = "SELECT * FROM Pictures WHERE idPicture='" + idPicture + "'";

                try (Statement smt = this.manager.connect().createStatement();
                        ResultSet rslt = smt.executeQuery(queryPicture)) {
                    // Il controllo se la query non ha tornato nulla,
                    // non deve servire, poichè dalla query precedente risulta
                    // che la season ha effettivamente un'immagine.
                    // Se l'immagine non esiste nella tabella delle immagini c'è
                    // un problema, e se tira un'eccezione lo sgamiamo
                    rslt.next();
                    final PictureConcrete.PictureConcreteBuilder bld = new PictureConcrete.PictureConcreteBuilder();
                    bld.id(rslt.getString(SQL_ID_PICTURE));
                    bld.size(rslt.getInt(SQL_SIZE));
                    bld.title(rslt.getString(SQL_TITLE));
                    bld.releaseDate(LocalDate.parse(rslt.getString(SQL_RELEASE_DATE),
                            DateTimeFormatter.ofPattern(APIUtils.DEFAULT_DATE_PATTERN)));
                    bld.physicalLocation(Paths.get(rslt.getString(SQL_PHYSICAL_LOCATION)));
                    bld.rating(rslt.getDouble(SQL_RATING));
                    bld.overview(normalize(rslt.getString(SQL_OVERVIEW)));
                    bld.format(getFormatFromString(rslt.getString(SQL_FORMAT)));
                    bld.author(normalize(rslt.getString(SQL_AUTHOR)));

                    final Map<String, String> tgs = this.getTagsFromPicture(idPicture);
                    if (!tgs.isEmpty()) {
                        bld.tags(tgs);
                    }

                    return bld.build();
                }
            }
        }
        return null;
    }

    private Picture getMoviePicture(final String movieID, final PictureType type) throws SQLException {
        if (type.equals(PictureType.IMAGE_PROFILE_PHOTO)) {
            throw new IllegalArgumentException(ERR_TYPE_NOT_VALID);
        }

        if (movieExists(movieID)) {
            // Retrieving the MOVIE and its PICTUREs
            final String query = "SELECT * FROM Movies WHERE idMovie='" + movieID + "'";
            String idPicture = null;

            try (Statement smt = this.manager.connect().createStatement(); ResultSet rslt = smt.executeQuery(query)) {
                rslt.next();
                if (type.equals(PictureType.IMAGE_BACKDROP)) {
                    idPicture = rslt.getString("idBackdrop");
                } else if (type.equals(PictureType.IMAGE_POSTER)) {
                    idPicture = rslt.getString(SQL_ID_POSTER);
                }
            }

            // If the MOVIE has a BACKDROP and/or a POSTER, composes the picture
            // and returns it
            if (normalize(idPicture) != null) {
                final String queryPicture = "SELECT * FROM Pictures WHERE idPicture='" + idPicture + "'";

                try (Statement smt2 = this.manager.connect().createStatement();
                        ResultSet rslt2 = smt2.executeQuery(queryPicture)) {
                    // Il controllo se la query non ha tornato nulla,
                    // non deve servire, poichè dalla query precedente risulta
                    // che il movie ha effettivamente un'immagine.
                    // Se l'immagine non esiste nella tabella delle immagini c'è
                    // un problema, e se tira un'eccezione lo sgamiamo
                    rslt2.next();
                    final PictureConcrete.PictureConcreteBuilder bld = new PictureConcrete.PictureConcreteBuilder();
                    bld.id(rslt2.getString(SQL_ID_PICTURE));
                    bld.size(rslt2.getInt(SQL_SIZE));
                    bld.title(rslt2.getString(SQL_TITLE));
                    bld.releaseDate(LocalDate.parse(rslt2.getString(SQL_RELEASE_DATE),
                            DateTimeFormatter.ofPattern(APIUtils.DEFAULT_DATE_PATTERN)));
                    bld.physicalLocation(Paths.get(rslt2.getString(SQL_PHYSICAL_LOCATION)));
                    bld.rating(rslt2.getDouble(SQL_RATING));
                    bld.overview(normalize(rslt2.getString(SQL_OVERVIEW)));
                    bld.format(getFormatFromString(rslt2.getString(SQL_FORMAT)));
                    bld.author(normalize(rslt2.getString(SQL_AUTHOR)));

                    final Map<String, String> tgs = this.getTagsFromPicture(idPicture);
                    if (!tgs.isEmpty()) {
                        bld.tags(tgs);
                    }

                    return bld.build();
                }
            }
        }
        return null;
    }

    private Picture getPersonPicture(final String personID) throws SQLException {
        final String query = "SELECT * FROM Persons WHERE idPerson='" + personID + "'";

        try (Statement smt = this.manager.connect().createStatement(); ResultSet rslt = smt.executeQuery(query)) {
            if (rslt.next()) {
                final String query2 = "SELECT * FROM Pictures WHERE idPicture='" + rslt.getString(SQL_ID_PICTURE) + "'";
                try (Statement smt2 = this.manager.connect().createStatement();
                        ResultSet rslt2 = smt.executeQuery(query2)) {
                    if (rslt2.next()) {
                        final PictureConcrete.PictureConcreteBuilder bld = new PictureConcrete.PictureConcreteBuilder();
                        bld.id(rslt.getString(SQL_ID_PICTURE));
                        bld.size(rslt.getInt(SQL_SIZE));
                        bld.title(rslt.getString(SQL_TITLE));
                        bld.releaseDate(LocalDate.parse(rslt.getString(SQL_RELEASE_DATE),
                                DateTimeFormatter.ofPattern(APIUtils.DEFAULT_DATE_PATTERN)));
                        bld.physicalLocation(Paths.get(rslt.getString(SQL_PHYSICAL_LOCATION)));
                        bld.rating(rslt.getDouble(SQL_RATING));
                        bld.overview(normalize(rslt.getString(SQL_OVERVIEW)));
                        bld.format(getFormatFromString(rslt.getString(SQL_FORMAT)));
                        bld.author(normalize(rslt.getString(SQL_AUTHOR)));

                        final Map<String, String> tgs = this.getTagsFromPicture(rslt.getString(SQL_ID_PICTURE));
                        if (!tgs.isEmpty()) {
                            bld.tags(tgs);
                        }

                        return bld.build();
                    }
                }
            }
        }
        return null;
    }

    private Set<String> getCountriesFromTVSerie(final String tvSerieID) throws SQLException {
        final String query = "SELECT * FROM TVSeries_Countries WHERE idSerie='" + tvSerieID + "'";

        final Set<String> countries = new HashSet<>();

        try (Statement smt = this.manager.connect().createStatement(); ResultSet rslt = smt.executeQuery(query)) {
            while (rslt.next()) {
                countries.add(rslt.getString("country"));
            }
        }
        return countries;
    }

    private Set<String> getCountriesFromMovie(final String movieID) throws SQLException {
        final String query = "SELECT * FROM Movies_Countries WHERE idMovie='" + movieID + "'";

        final Set<String> countries = new HashSet<>();

        try (Statement smt = this.manager.connect().createStatement(); ResultSet rslt = smt.executeQuery(query)) {
            while (rslt.next()) {
                countries.add(rslt.getString("country"));
            }
        }
        return countries;
    }

    private Map<String, String> getTagsFromMovie(final String movieID) throws SQLException {
        final Map<String, String> tags = new HashMap<>();

        // Check if movie exists
        if (movieExists(movieID)) {
            final String query = "SELECT * FROM Movies_Tags WHERE idMovie='" + movieID + "'";
            final Set<String> tagsIDs = new HashSet<>();

            try (Statement smt = this.manager.connect().createStatement(); ResultSet rslt = smt.executeQuery(query)) {
                while (rslt.next()) {
                    tagsIDs.add(rslt.getString(SQL_ID_TAG));
                }
            }

            if (tagsIDs.isEmpty()) {
                return tags;
            }

            for (final String tagSingleID : tagsIDs) {
                final String query2 = "SELECT * FROM Tags WHERE idTag='" + tagSingleID + "'";
                try (Statement smt = this.manager.connect().createStatement();
                        ResultSet rslt = smt.executeQuery(query2)) {
                    if (rslt.next()) {
                        tags.put(rslt.getString(SQL_ID_TAG), rslt.getString(SQL_NAME));
                    }
                }
            }
        }

        return tags;
    }

    private Map<String, String> getTagsFromTVSerie(final String tvSerieID) throws SQLException {
        final Map<String, String> tags = new HashMap<>();

        // Check if tvseries exists
        if (tvSeriesExists(tvSerieID)) {
            final String query = "SELECT * FROM TVSeries_Tags WHERE idSerie='" + tvSerieID + "'";
            final Set<String> tagsIDs = new HashSet<>();

            try (Statement smt = this.manager.connect().createStatement(); ResultSet rslt = smt.executeQuery(query)) {
                while (rslt.next()) {
                    tagsIDs.add(rslt.getString(SQL_ID_TAG));
                }
            }

            if (tagsIDs.isEmpty()) {
                return tags;
            }

            for (final String tagSingleID : tagsIDs) {
                final String query2 = "SELECT * FROM Tags WHERE idTag='" + tagSingleID + "'";
                try (Statement smt = this.manager.connect().createStatement();
                        ResultSet rslt = smt.executeQuery(query2)) {
                    if (rslt.next()) {
                        tags.put(rslt.getString(SQL_ID_TAG), rslt.getString(SQL_NAME));
                    }
                }
            }
        }

        return tags;
    }

    private Map<String, String> getTagsFromPicture(final String pictureID) throws SQLException {
        final Map<String, String> tags = new HashMap<>();

        // Check if pictures exists
        if (pictureExists(pictureID)) {
            final String query = "SELECT * FROM Pictures_Tags WHERE idPicture='" + pictureID + "'";
            final Set<String> tagsIDs = new HashSet<>();

            try (Statement smt = this.manager.connect().createStatement(); ResultSet rslt = smt.executeQuery(query)) {
                while (rslt.next()) {
                    tagsIDs.add(rslt.getString(SQL_ID_TAG));
                }
            }

            if (tagsIDs.isEmpty()) {
                return tags;
            }

            for (final String tagSingleID : tagsIDs) {
                final String query2 = "SELECT * FROM Tags WHERE idTag='" + tagSingleID + "'";
                try (Statement smt = this.manager.connect().createStatement();
                        ResultSet rslt = smt.executeQuery(query2)) {
                    if (rslt.next()) {
                        tags.put(rslt.getString(SQL_ID_TAG), rslt.getString(SQL_NAME));
                    }
                }
            }
        }

        return tags;
    }

    private Credits getCreditsFromMovie(final String movieID) throws SQLException {

        final String query = "SELECT * FROM Credits_Movie WHERE idMovie='" + movieID + "'";

        Person creator = null;
        final Set<Person> actors = new HashSet<>();

        try (Statement smt = this.manager.connect().createStatement(); ResultSet rslt = smt.executeQuery(query)) {
            while (rslt.next()) {
                if (rslt.getString("role").equals(RoleDB.CREATOR.getValue())) {
                    creator = this.getPerson(rslt.getString(SQL_ID_PERSON));
                } else {
                    final Person actor = this.getPerson(rslt.getString(SQL_ID_PERSON));
                    if (actor != null) {
                        actors.add(actor);
                    }
                }
            }
        }

        if (creator != null || !actors.isEmpty()) {
            return new CreditsConcrete(creator, actors);
        }
        return null;
    }

    private Credits getCreditsFromTVSerie(final String tvSerieID) throws SQLException {
        final String query = "SELECT * FROM Credits_TVSeries WHERE idSerie='" + tvSerieID + "'";

        Person creator = null;
        final Set<Person> actors = new HashSet<>();

        try (Statement smt = this.manager.connect().createStatement(); ResultSet rslt = smt.executeQuery(query)) {
            while (rslt.next()) {
                if (rslt.getString("role").equals(RoleDB.CREATOR.getValue())) {
                    creator = this.getPerson(rslt.getString(SQL_ID_PERSON));
                } else {
                    final Person actor = this.getPerson(rslt.getString(SQL_ID_PERSON));
                    if (actor != null) {
                        actors.add(actor);
                    }
                }
            }
        }

        if (creator != null || !actors.isEmpty()) {
            return new CreditsConcrete(creator, actors);
        }
        return null;
    }

    private Set<String> insertTagsIfNotPresent(final Map<String, String> tags) throws SQLException {
        final Set<String> tagsIDs = new HashSet<>();

        if (tags != null) {
            for (final Entry<String, String> singleTag : tags.entrySet()) {

                final String queryTag = "SELECT * FROM Tags WHERE idTag='" + singleTag.getKey() + "'";
                try (Statement smt = this.manager.connect().createStatement();
                        ResultSet rslt = smt.executeQuery(queryTag)) {
                    if (!rslt.next()) {
                        final String tagsQuery = "INSERT INTO Tags VALUES ('" + singleTag.getKey()
                                + QRY_COMMA_DOUBLE_APEX + unquote(singleTag.getValue()) + "')";
                        this.manager.setInsertRemove(tagsQuery);

                    }
                    tagsIDs.add(singleTag.getKey());
                }
            }
        }

        return tagsIDs;
    }

    private String insertPersonIfNotPresent(final Person person) throws SQLException {
        final Person tmp = this.getPerson(person.getID());

        if (tmp == null) {
            String queryProfilePicture = null;
            final StringBuilder bld = new StringBuilder(QUERY_LENGHT);
            bld.append("INSERT INTO Persons VALUES ('" + person.getID() + QRY_COMMA_DOUBLE_APEX
                    + unquote(person.getName()) + QUERY_COMMA_SINGLE_APEX);

            if (person.getPicture().isPresent()) {
                bld.append("'" + person.getPicture().get().getID() + "')");
                queryProfilePicture = composeInsertPictureQuery(person.getPicture().get());
            } else {
                bld.append(QUERY_LAST_FIELD_EMPTY);
            }

            if (queryProfilePicture != null) {
                this.manager.setInsertRemove(queryProfilePicture);
            }

            this.manager.setInsertRemove(bld.toString());

        }
        return person.getID();
    }

    private String composeInsertMovieQuery(final Movie movie) {
        final StringBuilder bld = new StringBuilder(QUERY_LENGHT).append("INSERT INTO Movies VALUES (");
        bld.append("'" + movie.getID() + QUERY_COMMA_SINGLE_APEX);
        bld.append("'" + movie.getDuration() + QUERY_COMMA_SINGLE_APEX);
        bld.append("'" + movie.getSize() + QUERY_COMMA_SINGLE_APEX);
        bld.append("'" + unquote(movie.getTitle()) + QUERY_COMMA_SINGLE_APEX);
        bld.append("'" + movie.getReleaseDate().toString() + QUERY_COMMA_SINGLE_APEX);
        bld.append("'" + movie.getPhysicalLocation().toString() + QUERY_COMMA_SINGLE_APEX);

        if (movie.getOverview().isPresent()) {
            bld.append("'" + unquote(movie.getOverview().get()) + QUERY_COMMA_SINGLE_APEX);
        } else {
            bld.append(QUERY_FIELD_EMPTY);
        }

        if (movie.getRating().isPresent()) {
            bld.append("'" + movie.getRating().get() + QUERY_COMMA_SINGLE_APEX);
        } else {
            bld.append(QUERY_FIELD_EMPTY);
        }

        if (movie.getPoster().isPresent()) {
            bld.append("'" + movie.getPoster().get().getID() + QUERY_COMMA_SINGLE_APEX);
        } else {
            bld.append(QUERY_FIELD_EMPTY);
        }

        if (movie.getBackdrop().isPresent()) {
            bld.append("'" + movie.getBackdrop().get().getID() + "')");
        } else {
            bld.append(QUERY_LAST_FIELD_EMPTY);
        }

        return bld.toString();
    }

    private String composeInsertPictureQuery(final Picture picture) {
        final StringBuilder bld = new StringBuilder(QUERY_LENGHT).append("INSERT INTO Pictures VALUES (");
        bld.append("'" + picture.getID() + QUERY_COMMA_SINGLE_APEX);
        bld.append("'" + picture.getSize() + QUERY_COMMA_SINGLE_APEX);
        bld.append("'" + unquote(picture.getTitle()) + QUERY_COMMA_SINGLE_APEX);
        bld.append("'" + picture.getReleaseDate().toString() + QUERY_COMMA_SINGLE_APEX);
        bld.append("'" + picture.getPhysicalLocation().toString() + QUERY_COMMA_SINGLE_APEX);

        if (picture.getRating().isPresent()) {
            bld.append("'" + picture.getRating().get() + QUERY_COMMA_SINGLE_APEX);
        } else {
            bld.append(QUERY_FIELD_EMPTY);
        }

        if (picture.getOverview().isPresent()) {
            bld.append("'" + unquote(picture.getOverview().get()) + QUERY_COMMA_SINGLE_APEX);
        } else {
            bld.append(QUERY_FIELD_EMPTY);
        }

        bld.append("'" + picture.getFormat().getValue() + QUERY_COMMA_SINGLE_APEX);

        if (picture.getAuthor().isPresent()) {
            bld.append("'" + unquote(picture.getAuthor().get()) + "')");
        } else {
            bld.append(QUERY_LAST_FIELD_EMPTY);
        }

        return bld.toString();
    }

    private String composeInsertTVSerieQuery(final TVSerie tvSerie) {
        final StringBuilder bld = new StringBuilder(QUERY_LENGHT).append("INSERT INTO TVSeries VALUES (");
        bld.append("'" + tvSerie.getID() + QUERY_COMMA_SINGLE_APEX);
        bld.append("'" + tvSerie.getTitle() + QUERY_COMMA_SINGLE_APEX);
        bld.append("'" + tvSerie.getReleaseDate().toString() + QUERY_COMMA_SINGLE_APEX);
        bld.append("'" + tvSerie.getPhysicalLocation().toString() + QUERY_COMMA_SINGLE_APEX);

        if (tvSerie.getOverview().isPresent()) {
            bld.append("'" + tvSerie.getOverview().get() + QUERY_COMMA_SINGLE_APEX);
        } else {
            bld.append(QUERY_FIELD_EMPTY);
        }

        if (tvSerie.getRating().isPresent()) {
            bld.append("'" + tvSerie.getRating().get() + QUERY_COMMA_SINGLE_APEX);
        } else {
            bld.append(QUERY_FIELD_EMPTY);
        }

        if (tvSerie.getPoster().isPresent()) {
            bld.append("'" + tvSerie.getPoster().get().getID() + QUERY_COMMA_SINGLE_APEX);
        } else {
            bld.append(QUERY_FIELD_EMPTY);
        }

        if (tvSerie.getBackdrop().isPresent()) {
            bld.append("'" + tvSerie.getBackdrop().get().getID() + "')");
        } else {
            bld.append(QUERY_LAST_FIELD_EMPTY);
        }

        return bld.toString();
    }

    private String composeInsertSeasonQuery(final Season season, final String tvSerieID) {
        final StringBuilder bld = new StringBuilder(QUERY_LENGHT).append("INSERT INTO Seasons VALUES (");
        bld.append("'" + season.getID() + QUERY_COMMA_SINGLE_APEX);
        bld.append("'" + season.getTitle() + QUERY_COMMA_SINGLE_APEX);
        bld.append("'" + season.getReleaseDate().toString() + QUERY_COMMA_SINGLE_APEX);

        if (season.getOverview().isPresent()) {
            bld.append("'" + season.getOverview().get() + QUERY_COMMA_SINGLE_APEX);
        } else {
            bld.append(QUERY_FIELD_EMPTY);
        }

        bld.append("'" + season.getProgressiveNumber() + QUERY_COMMA_SINGLE_APEX);

        if (season.getPoster().isPresent()) {
            bld.append("'" + season.getPoster().get().getID() + QUERY_COMMA_SINGLE_APEX);
        } else {
            bld.append(QUERY_FIELD_EMPTY);
        }

        bld.append("'" + tvSerieID + "')");

        return bld.toString();
    }

    private String composeInsertEpisodeQuery(final Episode episode, final String seasonID) throws SQLException {
        final StringBuilder bld = new StringBuilder(QUERY_LENGHT).append("INSERT INTO Episodes VALUES (");
        bld.append("'" + episode.getID() + QUERY_COMMA_SINGLE_APEX);
        bld.append("'" + episode.getSize() + QUERY_COMMA_SINGLE_APEX);
        bld.append("'" + episode.getTitle() + QUERY_COMMA_SINGLE_APEX);
        bld.append("'" + episode.getReleaseDate().toString() + QUERY_COMMA_SINGLE_APEX);
        bld.append("'" + episode.getPhysicalLocation().toString() + QUERY_COMMA_SINGLE_APEX);
        bld.append("'" + episode.getDuration() + QUERY_COMMA_SINGLE_APEX);

        if (episode.getOverview().isPresent()) {
            bld.append("'" + episode.getOverview().get() + QUERY_COMMA_SINGLE_APEX);
        } else {
            bld.append(QUERY_FIELD_EMPTY);
        }

        bld.append("'" + episode.getProgressiveNumber() + QUERY_COMMA_SINGLE_APEX);

        if (episode.getRating().isPresent()) {
            bld.append("'" + episode.getRating().get() + QUERY_COMMA_SINGLE_APEX);
        } else {
            bld.append(QUERY_FIELD_EMPTY);
        }

        if (episode.getPoster().isPresent()) {
            bld.append("'" + episode.getPoster().get().getID() + QUERY_COMMA_SINGLE_APEX);
        } else {
            bld.append(QUERY_FIELD_EMPTY);
        }

        bld.append("'" + seasonID + "')");

        return bld.toString();

    }

    private PictureFormat getFormatFromString(final String format) {
        for (final PictureFormat tmp : Arrays.asList(PictureFormat.H_632.getDeclaringClass().getEnumConstants())) {
            if (tmp.getValue().equals(format)) {
                return tmp;
            }
        }
        return null;
    }

    private boolean movieExists(final String movieID) throws SQLException {
        final String query = "SELECT * FROM Movies WHERE idMovie='" + movieID + "'";
        try (Statement smt = this.manager.connect().createStatement(); ResultSet rslt = smt.executeQuery(query)) {
            if (rslt.next()) {
                return true;
            }
        }
        return false;
    }

    private boolean tvSeriesExists(final String tvSerieID) throws SQLException {
        final String query = "SELECT * FROM TVSeries WHERE idSerie='" + tvSerieID + "'";
        try (Statement smt = this.manager.connect().createStatement(); ResultSet rslt = smt.executeQuery(query)) {
            if (rslt.next()) {
                return true;
            }
        }
        return false;
    }

    private boolean seasonExists(final String seasonID) throws SQLException {
        final String query = "SELECT * FROM Seasons WHERE idSeason='" + seasonID + "'";
        try (Statement smt = this.manager.connect().createStatement(); ResultSet rslt = smt.executeQuery(query)) {
            if (rslt.next()) {
                return true;
            }
        }
        return false;
    }

    private boolean episodeExists(final String episodeID) throws SQLException {
        final String query = "SELECT * FROM Episodes WHERE idEpisode='" + episodeID + "'";
        try (Statement smt = this.manager.connect().createStatement(); ResultSet rslt = smt.executeQuery(query)) {
            if (rslt.next()) {
                return true;
            }
        }
        return false;
    }

    private boolean pictureExists(final String pictureID) throws SQLException {
        final String query = "SELECT * FROM Pictures WHERE idPicture='" + pictureID + "'";
        try (Statement smt = this.manager.connect().createStatement(); ResultSet rslt = smt.executeQuery(query)) {
            if (rslt.next()) {
                return true;
            }
        }
        return false;
    }

    private void deletePicture(final String pictureID) throws SQLException {
        if (!pictureExists(pictureID)) {
            throw new IllegalStateException();
        }
        final String query = "DELETE FROM Pictures WHERE idPicture='" + pictureID + "'";
        this.manager.setInsertRemove(query);
    }

    private String unquote(final String string) {
        return string.replace("'", "");
    }
}
